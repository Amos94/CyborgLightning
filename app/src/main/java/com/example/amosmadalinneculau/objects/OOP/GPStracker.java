package com.example.amosmadalinneculau.objects.OOP;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

/**
 * Created by Name on 01/02/2016.
 */
public class GPStracker extends Service implements LocationListener {
    private final Context mContext;

    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean isLocationAccessible = false;

    Location location;
    double latitude;
    double longitude;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES =10;
    private static final long MIN_TIME_BW_UPDATES = 1000*60*1;

    protected LocationManager locationManager;

    public GPStracker(Context context){
        this.mContext = context;
        getLocation();

    }

    /**
     * GET LOCATION FUNCTION NOT FULLY FUNCTIONAL, MISSING CODE !!!!
     * @return location
     */
    public Location getLocation(){
        try{
            locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);

            //getting gps status
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            //getting network status
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if(!isGPSEnabled && isNetworkEnabled){
                //no network provider is enabled
            }else{
                this.isLocationAccessible = true;
                //first get location from provider
                if(isNetworkEnabled){
//----------------------TODO MISSING SOME PERMISSIONS--------------------------------------------------
 /*Check permission    locationManager.requestLocationUpdates(
                       LocationManager.NETWORK_PROVIDER,
                       MIN_TIME_BW_UPDATES,
                       MIN_DISTANCE_CHANGE_FOR_UPDATES, this); */
                    Log.d("NETWORK","NETWORK");
                    if(locationManager!=null){
 //Check permission     location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if(location !=null){
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
                //if gos enabled get lattitude and longitude using GPS Services
                if(isGPSEnabled){
                    if(location == null){
//---------------------TODO MISSING SOME PERMISSIONS--------------------------------------------------
/*Check permission     locationManager.requestLocationUpdates(
                       LocationManager.GPS_PROVIDER,
                       MIN_TIME_BW_UPDATES,
                       MIN_DISTANCE_CHANGE_FOR_UPDATES,this);*/
                    Log.d("GPS ENABLED","GPS ENABLED");
                        if(locationManager !=null){
//Check permission      location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if(location !=null){
                                longitude = location.getLongitude();
                                latitude = location.getLatitude();
                            }
                        }
                    }
                }
            }
        }catch (Exception e){e.printStackTrace();}
        return location;
    }


    /**
     * GET LATITUDE
     * @return this.latitude
     * GET LONGITUDE
     * @return this.longitude
     */
    public double getLatitude(){
        if(location != null){
            latitude = location.getLatitude();
        }
        return latitude;
    }

    public double getLongitude(){
        if(location != null){
            longitude = location.getLongitude();
        }
        return longitude;
    }

    public boolean isLocationAccessible() {
        return this.isLocationAccessible;
    }
    //prompt to switch on gps
    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        //Dialog title
        alertDialog.setTitle("GPS is settings");
        //setting dialog msg
        alertDialog.setMessage("GPS is not enabled, Go to settings?");
        //Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.delete);

        //on pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });

        //on pressing Cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }

        });
        //showAlertDialog
        alertDialog.show();

    }

//--------------------------TODO PERMISSIONS ----------------------------------------
    //Stoping GPS listener
    public void stopUsingGPS(){
        if(locationManager !=null){
//            locationManager.removeUpdates(GPStracker.this);
        }
    }














    @Override
    public void onLocationChanged(Location location){
        //body
    }

    @Override
    public void onProviderDisabled(String provider) {
        //body
    }

    @Override
    public void onProviderEnabled(String provider) {
        //body
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //body
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
}
