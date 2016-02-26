package com.example.amosmadalinneculau.objects.VOIP;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.sip.*;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.amosmadalinneculau.voicecallsip.R;

/**
 * Created by Amos Madalin Neculau on 25/02/2016.
 */
public class Master extends Activity {

    public SipManager mSipManager = null;
    public SipProfile mSipProfile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(mSipManager == null){
            mSipManager = SipManager.newInstance(this);
        }

        SipProfile.Builder builder = new SipProfile.Builder("","");
        builder.setPassword("");
        mSipProfile = builder.build();
    }

    public void openLocalProfile(){
        Intent intent = new Intent();
        intent.setAction("android.SipDemo.INCOMING_CALL");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, Intent.FILL_IN_DATA);
        mSipManager.open(mSipProfile, pendingIntent, null);
    }

    public void registration(){
        mSipManager.setRegistrationListener(mSipProfile.getUriString(), new SipRegistrationListener() {

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
        }
    }

    public void closeLocalProfile() {
        if (mSipManager == null) {
            return;
        }
        try {
            if (mSipProfile != null) {
                mSipManager.close(mSipProfile.getUriString());
            }
        } catch (Exception ee) {
            Log.d("WalkieTalkieActivity/onDestroy", "Failed to close local profile.", ee);
        }
    }

    /*
    example of call
     call = mSipManager.makeAudioCall(mSipProfile.getUriString(), sipAddress, listener, 30);
     */
    public void makeAudioCall(){
        SipAudioCall.Listener listener = new SipAudioCall.Listener() {

            @Override
            public void onCallEstablished(SipAudioCall call) {
                call.startAudio();
                call.setSpeakerMode(true);
                call.toggleMute();

            }

            @Override
            public void onCallEnded(SipAudioCall call) {
                // Do something.
            }
        };
    }


}
