package com.example.xenon.gps;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener{

    public LocationManager lm;
    public Criteria cr;
    public Location loc;
    public String mojdostawca;
    TextView tw1;
    TextView tw2;
    TextView tw3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tw1=(TextView)findViewById(R.id.dostawca);
        tw2=(TextView)findViewById(R.id.dlugosc);
        tw3=(TextView)findViewById(R.id.szerokosc);

        cr=new Criteria();

        lm=(LocationManager)getSystemService(LOCATION_SERVICE);
        mojdostawca=lm.getBestProvider(cr, true);

        loc=lm.getLastKnownLocation(mojdostawca);
        DisplayLoc(mojdostawca, loc);
    }
    public void DisplayLoc(String mojdostawca, Location loc){
        tw1.setText(mojdostawca);
        String temp=String.valueOf(loc.getLongitude());
        tw2.setText(temp);
        temp=String.valueOf(loc.getLatitude());
        tw3.setText(temp);
    }

    @Override
    protected void onPause() {
        super.onPause();
        lm.removeUpdates(this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        lm.requestLocationUpdates(mojdostawca, 400, 1, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        mojdostawca=lm.getBestProvider(cr, true);
        loc=lm.getLastKnownLocation(mojdostawca);
        DisplayLoc(mojdostawca, loc);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
