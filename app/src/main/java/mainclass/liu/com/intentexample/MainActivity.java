package mainclass.liu.com.intentexample;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity {

    private LocationManager locationManager = null;
    private TextView positionTextView;
    private String provider;
    private LocationListener locationlistener = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        Log.w("mylog","in use");
        positionTextView = (TextView)findViewById(R.id.text_view);
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        List<String> providerList = locationManager.getProviders(true);
        if(providerList.contains(locationManager.GPS_PROVIDER))
        {
            provider = LocationManager.GPS_PROVIDER;
        }
        else if(providerList.contains(LocationManager.NETWORK_PROVIDER))
        {
            provider = LocationManager.NETWORK_PROVIDER;
        }
        else
        {
            Toast.makeText(this,"no provider to use",Toast.LENGTH_LONG).show();
        }

        Location location = null;
        try {
            location = locationManager.getLastKnownLocation(provider);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        if(location!= null)
        {

        }
        try {
            locationManager.requestLocationUpdates(provider,5000,1,locationlistener);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        Log.w("mylog","in dependence");
    }

    protected void onDestroy()
    {
        super.onDestroy();
        if(!locationManager.equals(null))
        {
            try {
                locationManager.removeUpdates(locationlistener);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }

        locationlistener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                showLocation(location);
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
        };
    }


    private void showLocation(Location location)
    {
        String currentPosition = "latitude is "+location.getAltitude()+"\n"+"longitude is "+location.getLongitude();
        positionTextView.setText(currentPosition);
    }
}

