package com.uninorte.carlos.track_admin;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1 ;
    private GoogleMap mMap;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    String key,fi,ff;
    Date fei,fef;
    private String TAG= "FirebaseLog";
    boolean sw=false;
    ArrayList<String> latit=new ArrayList();
    ArrayList<String> longt=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        final SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        key=getIntent().getStringExtra("name");
        fi=getIntent().getStringExtra("keyi").toString();
        ff=getIntent().getStringExtra("keyf").toString();
        try {
            fei=formatter.parse(fi);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            fef=formatter.parse(ff);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        database= FirebaseDatabase.getInstance();
        myRef= database.getReference(getIntent().getStringExtra("name"));
        ArrayList<Track> tracks = new ArrayList<>();
        final PolylineOptions rectOptions = new PolylineOptions();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LatLng latLng = new LatLng(10.98, -74.83);

                for (DataSnapshot child :
                        dataSnapshot.getChildren()) {
                    String x=child.getKey().substring(0,child.getKey().length()-3);

                    Date date1;
                    try {
                        date1=formatter.parse(x);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        date1=null;
                    }
                    if(date1.before(fef) && date1.after(fei)){
                        String lat=child.child("lat").getValue().toString();
                        String lon=child.child("lon").getValue().toString();
                        //Log.d("FirebaseLog", "onDataChange: " + child.getKey());
                        rectOptions.add(new LatLng(Double.parseDouble(lat), Double.parseDouble(lon)));
                        latLng = new LatLng(Double.parseDouble(lat), Double.parseDouble(lon));
                    }
                }

                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);

                mMap.animateCamera(cameraUpdate);
                Polyline polyline = mMap.addPolyline(rectOptions);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()
                        ) {
                    if(data.getKey().equals(key)) {
                        Log.d(TAG, "onDataChange: " + data.getValue());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            LatLng latLng = new LatLng(10.98, -74.83);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
            googleMap.animateCamera(cameraUpdate);
            googleMap.addMarker(new MarkerOptions().position(latLng).title("Here"));



    }
}
