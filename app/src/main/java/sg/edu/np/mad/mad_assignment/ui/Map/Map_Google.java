package sg.edu.np.mad.mad_assignment.ui.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.telecom.ConnectionRequest;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import sg.edu.np.mad.mad_assignment.R;

public class Map_Google extends AppCompatActivity implements OnMapReadyCallback {
    boolean isPermissionGranter;

    GoogleMap googleMap;
    ImageView imageViewSearch;
    EditText inputLocation;
    Menu menu;
    LocationRequest locationRequest;
    private static final int LOCATION_REQUEST = 500;
    FusedLocationProviderClient fusedLocationProviderClient;
    ArrayList<LatLng> listpoints;
    Polyline mPolyline;
    LatLng mOrigin;
    LatLng mDestination;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_google);

        imageViewSearch = (ImageView) findViewById(R.id.imageViewSearch);
        inputLocation = (EditText) findViewById(R.id.inputlocation);
        listpoints = new ArrayList<>();

        checkPermission();


        if (checkGooglePlayService()) {
            SupportMapFragment supportMapFragment = SupportMapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.container, supportMapFragment).commit();
            supportMapFragment.getMapAsync(this);
            if (isPermissionGranter) {
                CheckGPS();
            }

        } else {
            Toast.makeText(this, "Google Playservices Not Available", Toast.LENGTH_SHORT).show();
        }

        imageViewSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(listpoints.size() == 2)
                {
                    listpoints.clear();
                    googleMap.clear();
                }
                String Location = inputLocation.getText().toString();

                if (Location == null) {
                    Toast.makeText(Map_Google.this, "Type any location name", Toast.LENGTH_SHORT).show();
                }
                else {

                    new AsyncTask<String, Void, Address>()
                    {
                        @SuppressLint("WrongThread")
                        @Override
                        protected Address doInBackground(String...params)
                        {
                            Geocoder geocoder = new Geocoder(Map_Google.this, Locale.getDefault());
                            try {
                                List<Address> listAddress = geocoder.getFromLocationName(Location, 1);
                                LatLng latLng1 = new LatLng(listAddress.get(0).getLatitude(), listAddress.get(0).getLongitude());
                                if (listAddress.size() > 0) {
                                    listpoints.add(latLng1);
                                    MarkerOptions markerOptions = new MarkerOptions();
                                    markerOptions.position(latLng1);

                                    if(listpoints.size()==1){
                                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                                        markerOptions.title("Start Location");
                                    }else if(listpoints.size()==2){
                                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                                        markerOptions.title("Destination");
                                    }
                                    Map_Google.this.runOnUiThread(new Runnable(){
                                        public void run(){
                                            googleMap.addMarker(markerOptions);
                                            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng1, 16);
                                            googleMap.animateCamera(cameraUpdate);

                                            if (listpoints.size() == 2)
                                            {
                                                String url = getRequestUrl(listpoints.get(0), listpoints.get(1));
                                                TaskRequestDirections taskRequestDirections = new TaskRequestDirections();
                                                taskRequestDirections.execute(url);
                                            }
                                        }
                                    });
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Address address)
                        {
                            // do whatever you want/need to do with the address found
                            // remember to check first that it's not null
                        }
                    }.execute();
                }
            }
        });
    }

    private String getRequestUrl(LatLng origin, LatLng dest) {
        // Value Of Origin
        String str_org = "origin=" + origin.latitude +","+ origin.longitude;
        // Value Of Destination
        String str_destination = "destination=" + dest.latitude +","+ dest.longitude;
        // Set Value enable the Sensor
        String key = "&key="+ "AIzaSyCcTgH2IOJx_ysHPNSnnl42k7soGPnGqfE";
        String sensor = "sensor=false";
        // Mode for find direction
        String mode = "mode=driving";
        // Full Param
        String Param = str_org +"&" + str_destination + "&" +sensor+"&" +mode +key;
        //Output Format
        String output = "json";
        //Create URl to request
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + Param;
        return url;
    }

    private String requestDirection(String reqUrl) throws IOException {
        String responseString = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try{
            URL url = new URL(reqUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            // Get the response result
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            while((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line);
            }

            responseString = stringBuffer.toString();
            bufferedReader.close();
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                inputStream.close();
            }
            httpURLConnection.disconnect();
        }
        return responseString;
    }

    private boolean checkGooglePlayService() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int result = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (result == ConnectionResult.SUCCESS) {
            return true;
        } else if (googleApiAvailability.isUserResolvableError(result)) {
            Dialog dialog = googleApiAvailability.getErrorDialog(this, result, 201, new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    Toast.makeText(Map_Google.this, "User Cancelled Dialoge", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
        }

        return false;

    }

    private void checkPermission() {
        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                isPermissionGranter = true;
                Toast.makeText(Map_Google.this, "permission Granter", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), "");
                intent.setData(uri);
                startActivity(intent);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng latLng = new LatLng(1.3321, 103.7743);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        markerOptions.title("Ngee Ann Polytechnic");
        markerOptions.position(latLng);
        googleMap.addMarker(markerOptions);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16);
        googleMap.animateCamera(cameraUpdate);

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setScrollGesturesEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(false);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_REQUEST );
            return;
        }
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setMyLocationEnabled(true);
    }

    @SuppressLint({"MissingPermission", "MissingSuperCall"})
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case LOCATION_REQUEST:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    googleMap.setMyLocationEnabled(true);
                }
                break;
        }
    }

    private void CheckGPS() {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
                .setAlwaysShow(true);
        Task<LocationSettingsResponse> locationSettingsResponseTask = LocationServices.getSettingsClient(getApplicationContext())
                .checkLocationSettings(builder.build());
        locationSettingsResponseTask.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(Map_Google.this, "GPS is already enabled", Toast.LENGTH_SHORT).show();
                    // Request Device Location
                    GetCurrentUpdate();

                } catch (ApiException e) {
                    if (e.getStatusCode() == LocationSettingsStatusCodes.RESOLUTION_REQUIRED) {
                        ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                        try {
                            resolvableApiException.startResolutionForResult(Map_Google.this, 101);

                        } catch (IntentSender.SendIntentException sendIntentException) {
                            sendIntentException.printStackTrace();
                        }

                    }
                    if (e.getStatusCode() == LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE) {
                        Toast.makeText(Map_Google.this, "Settings not available in device", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void GetCurrentUpdate() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Map_Google.this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Toast.makeText(Map_Google.this, "Location"+ locationResult.getLastLocation().getLongitude()+ ":"+ locationResult.getLastLocation().getLatitude(), Toast.LENGTH_SHORT).show();
            }
        }, Looper.getMainLooper());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.noneMap)
        {
            googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
            return true;
        }
        if(item.getItemId()==R.id.NormalMap)
        {
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            return true;
        }
        if(item.getItemId()==R.id.SatelliteMap)
        {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            return true;
        }
        if(item.getItemId()==R.id.MapHybrid)
        {
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            return true;
        }
        if(item.getItemId()==R.id.MapTerrain)
        {
            googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class TaskRequestDirections extends AsyncTask<String, Void,String >{
        @Override
        protected String doInBackground(String... strings) {
            String responsestring = "";
            try {
                responsestring = requestDirection(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return responsestring;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // Parse json here
            TaskParser taskParser = new TaskParser();
            taskParser.execute(s);
        }
    }
    public class TaskParser extends AsyncTask<String, Void,List<List<HashMap<String, String>>> >
    {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... strings) {
            JSONObject jsonObject = null;
            List<List<HashMap<String, String>>> routes = null;
            try {
                jsonObject = new JSONObject(strings[0]);
                DirectionParsers directionParsers = new DirectionParsers();
                routes = directionParsers.parse(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> lists) {
            //Get list route and display it into the map
            ArrayList points = null;

            PolylineOptions polylineOptions = null;

            for(List<HashMap<String, String>> path: lists)
            {
                points = new ArrayList();
                polylineOptions = new PolylineOptions();

                for(HashMap<String, String> point :path){
                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));

                    points.add(new LatLng(lat, lng));
                }
                polylineOptions.addAll(points);
                polylineOptions.width(15);
                polylineOptions.color(Color.BLUE);
                polylineOptions.geodesic(true);
            }
            if(polylineOptions != null)
            {
                googleMap.addPolyline(polylineOptions);
            }else{
                Toast.makeText(getApplicationContext(), "Direction not found!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101)
        {
            if(resultCode == RESULT_OK)
            {
                Toast.makeText(this, "Now GPS is Enabled", Toast.LENGTH_SHORT).show();
            }
            if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            }
        }
    }

}