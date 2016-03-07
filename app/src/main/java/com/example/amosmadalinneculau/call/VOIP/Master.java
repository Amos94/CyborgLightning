package com.example.amosmadalinneculau.call.VOIP;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.sip.SipAudioCall;
import android.net.sip.SipException;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;
import android.net.sip.SipRegistrationListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ToggleButton;

import com.example.amosmadalinneculau.call.R;

import java.text.ParseException;

/**
 * Created by Amos Madalin Neculau on 25/02/2016.
 */
public class Master extends Activity implements View.OnTouchListener{

    public String sipAddress = null;

    public SipManager manager = null;
    public SipProfile me = null;
    public SipAudioCall call = null;
    public IncomingCallReceiver callReceiver;
    public Chronometer chronometer;

    private static final int CALL_ADDRESS = 1;
    private static final int SET_AUTH_INFO = 2;
    private static final int UPDATE_SETTINGS_DIALOG = 3;
    private static final int HANG_UP = 4;
    private Button hangUp;
    private ToggleButton mute;
    private ToggleButton speaker;
    private Button makeNewCall;

    private UserInformation caller;
    private String callee;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //this.caller = caller;
        caller = new UserInformation("amosneculau1", "testpassword","sip.antisip.com");
        //this.callee = callee;
        callee = "sip:amosneculau2@sip.antisip.com";

        hangUp = (Button) findViewById(R.id.HangUpBtn);
        speaker = (ToggleButton) findViewById(R.id.speakerBtn);
        mute = (ToggleButton) findViewById(R.id.muteBtn);
        makeNewCall = (Button) findViewById(R.id.initiateCall);


        ToggleButton pushToTalkButton = (ToggleButton) findViewById(R.id.pushToTalk);
        pushToTalkButton.setOnTouchListener(this);


        // Set up the intent filter.  This will be used to fire an
        // IncomingCallReceiver when someone calls the SIP address used by this
        // application.
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.SipDemo.INCOMING_CALL");
        callReceiver = new IncomingCallReceiver();
        this.registerReceiver(callReceiver, filter);


        chronometer = (Chronometer) findViewById(R.id.chronometer);

        // "Push to talk" can be a serious pain when the screen keeps turning off.
        // Let's prevent that.
        /*getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);*/

        initializeManager();
        initializeLocalProfile();
    }

    @Override
    public void onStart() {
        super.onStart();
        // When we get back from the preference setting Activity, assume
        // settings have changed, and re-login with new auth info.
        initializeManager();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (call != null) {
            call.close();
        }

        closeLocalProfile();

        if (callReceiver != null) {
            this.unregisterReceiver(callReceiver);
        }
    }

    public void initializeManager() {
        if(manager == null) {
            manager = SipManager.newInstance(this);
        }
        Log.d("Reached here", "initializeManager()");
        initializeLocalProfile();
        Log.d("Reached here", "initializeManager() fin");
    }

    /**
     * Logs you into your SIP provider, registering this device as the location to
     * send SIP calls to for your SIP address.
     */
    public void initializeLocalProfile() {
        /*if (manager == null) {
            return;
        }*/

        if (me != null) {
            closeLocalProfile();
        }

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String username = prefs.getString("namePref", "");
        String domain = prefs.getString("domainPref", "");
        String password = prefs.getString("passPref", "");

        /*if (username.length() == 0 || domain.length() == 0 || password.length() == 0) {
            showDialog(UPDATE_SETTINGS_DIALOG);
            return;
        }*/

        try {
            Log.d("caller username: ", caller.getUsername());
            Log.d("caller password: ", caller.getPassword());
            Log.d("caller server: ", caller.getServer());
            SipProfile.Builder builder = new SipProfile.Builder(caller.getUsername(), caller.getServer());
            builder.setPassword(caller.getPassword());
            me = builder.build();

            Intent i = new Intent();
            i.setAction("android.SipDemo.INCOMING_CALL");
            PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, Intent.FILL_IN_DATA);
            manager.open(me, pi, null);


            // This listener must be added AFTER manager.open is called,
            // Otherwise the methods aren't guaranteed to fire.

            manager.setRegistrationListener(me.getUriString(), new SipRegistrationListener() {
                public void onRegistering(String localProfileUri) {
                    updateStatus("Registering with SIP Server...");
                }

                public void onRegistrationDone(String localProfileUri, long expiryTime) {
                    updateStatus("Ready");
                }

                public void onRegistrationFailed(String localProfileUri, int errorCode,
                                                 String errorMessage) {
                    updateStatus("Registration failed.  Please check settings.");
                }
            });
        } catch (ParseException pe) {
            updateStatus("Connection Error.");
        } catch (SipException se) {
            updateStatus("Connection error.");
        }
    }

    /**
     * Closes out your local profile, freeing associated objects into memory
     * and unregistering your device from the server.
     */
    @SuppressLint("LongLogTag")
    public void closeLocalProfile() {
        if (manager == null) {
            return;
        }
        try {
            if (me != null) {
                manager.close(me.getUriString());
            }
        } catch (Exception ee) {
            Log.d("onDestroy", "Failed to close local profile.", ee);
        }
    }

    /**
     * Make an outgoing call.
     */
    @SuppressLint("LongLogTag")
    public void initiateCall() {

        updateStatus(sipAddress);


        try {
            SipAudioCall.Listener listener = new SipAudioCall.Listener() {
                // Much of the client's interaction with the SIP Stack will
                // happen via listeners.  Even making an outgoing call, don't
                // forget to set up a listener to set things up once the call is established.
                @Override
                public void onCallEstablished(SipAudioCall call) {
                    call.startAudio();

                    updateStatus(call);

                    chronometer.start();
                }

                @Override
                public void onCallEnded(SipAudioCall call) {
                    updateStatus("Ready.");
                    chronometer.stop();
                }

                @Override
                public void onCallHeld(SipAudioCall call){
                    chronometer.stop();
                    call.toggleMute();
                }

                @Override
                public void onCallBusy(SipAudioCall call){
                    try {
                        call.endCall();
                    } catch (SipException e) {
                        e.printStackTrace();
                    }
                }
            };

            Log.i("Reached","here");
            call = manager.makeAudioCall(me.getUriString(), callee, listener, 30);
            Log.i("Reached","here after call");

        }
        catch (Exception e) {
            Log.i("InitiateCall", "Error when trying to close manager.", e);
            if (me != null) {
                try {
                    manager.close(me.getUriString());
                } catch (Exception ee) {
                    Log.i("InitiateCall",
                            "Error when trying to close manager.", ee);
                    ee.printStackTrace();
                }
            }
            if (call != null) {
                call.close();
            }
        }
    }

    public void endCall(View view){
        try {
            call.endCall();
        } catch (SipException e) {
            e.printStackTrace();
        }
    }

    public void muteListener(View view){

        if(mute.isActivated() == false){
            call.toggleMute();
            mute.toggle();
        }
        else{
            call.toggleMute();
            mute.toggle();
        }
    }

    public void speakerListener(View views){
        if(speaker.isActivated() == false){
            call.setSpeakerMode(true);
            speaker.toggle();
        }
        else{
            call.setSpeakerMode(false);
            speaker.toggle();
        }
    }

    public void makeCall(View view){
        initiateCall();
    }

    /**
     * Updates the status box at the top of the UI with a messege of your choice.
     * @param status The String to display in the status box.
     */
    public void updateStatus(final String status) {
        // Be a good citizen.  Make sure UI changes fire on the UI thread.
        this.runOnUiThread(new Runnable() {
            public void run() {
               /* TextView labelView = (TextView) findViewById(R.id.sipLabel);
                labelView.setText(status);*/
            }
        });
    }

    /**
     * Updates the status box with the SIP address of the current call.
     * @param call The current, active call.
     */
    public void updateStatus(SipAudioCall call) {
        String useName = call.getPeerProfile().getDisplayName();
        if(useName == null) {
            useName = call.getPeerProfile().getUserName();
        }
        updateStatus(useName + "@" + call.getPeerProfile().getSipDomain());
    }

    public void updatePreferences() {
        Intent settingsActivity = new Intent(getBaseContext(),
                SipSettings.class);
        startActivity(settingsActivity);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (call == null) {
            return false;
        } else if (event.getAction() == MotionEvent.ACTION_DOWN && call != null && call.isMuted()) {
            call.toggleMute();
        } else if (event.getAction() == MotionEvent.ACTION_UP && !call.isMuted()) {
            call.toggleMute();
        }
        return false;
    }
}
