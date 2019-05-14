package com.mind.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {
    private GoogleMap mMap;

    EditText search_start_point;
    EditText search_end_point;

    static Point start_point;
    static Point end_point;

    Button call;

    Marker start_marker;
    Marker end_marker;

    PolylineOptions polylineOptions;
    Polyline polyline;
    Button safecar_button;
    boolean safecar_button_check =false;

    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setCustomActionbar();


//
//        safecar_button = (Button) findViewById(R.id.button2);
//        safecar_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(safecar_button_check){
//                    safecar_button_check=false;
//                    safecar_button.setBackgroundResource(R.drawable.btn_use_selector);
//                }else{
//                    safecar_button_check=true;
//                    safecar_button.setBackgroundResource(R.drawable.btn_use_on);
//                }
//
//
//            }
//        });


        //Google Map

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.btn_ham_dark_selector));

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        search_start_point = (EditText) findViewById(R.id.button_start_search);
        search_start_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchPointActivity.class);
                intent.putExtra("start", true);
                startActivity(intent);
            }
        });

        search_start_point.setFocusable(false);
        search_start_point.setClickable(false);

        search_end_point = (EditText) findViewById(R.id.button_destination_search);
        search_end_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchPointActivity.class);
                intent.putExtra("start", false);
                startActivity(intent);
            }
        });

        search_end_point.setFocusable(false);
        search_end_point.setClickable(false);

        call = (Button) findViewById(R.id.call_taxi);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(start_point==null || end_point==null) {
                    Toast.makeText(MainActivity.this, "정보가 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(MainActivity.this, LoadingActivity.class);

                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (start_point != null) {
            search_start_point.setText(start_point.name);

            if (start_marker != null) {
                start_marker.remove();

            }
            start_marker = mMap.addMarker(new MarkerOptions().position(start_point.latlng).title(start_point.name));
        }

        if (end_point != null) {
            search_end_point.setText(end_point.name);
            if (end_marker != null) {
                end_marker.remove();
            }
            end_marker = mMap.addMarker(new MarkerOptions().position(end_point.latlng).title(end_point.name));
        }

        if(start_point !=null && end_point !=null){
            if(polyline !=null){
                polyline.remove();
            }
            polylineOptions = new PolylineOptions();
            polylineOptions.color(Color.RED);
            polylineOptions.width(5);
            polylineOptions.add(start_point.latlng);
            polylineOptions.add(end_point.latlng);
            polyline = mMap.addPolyline(polylineOptions);
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if (mMap != null) {
            return;
        }
        mMap = map;

        startMaps();
    }

    protected void startMaps() {
//        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.507446, 127.034525), 10));

    }

//    private void setCustomActionbar() {
//        ActionBar actionBar = getSupportActionBar();
//        // Custom Actionbar를 사용하기 위해 CustomEnabled을 true 시키고 필요 없는 것은 false 시킨다
//        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(false);
//        actionBar.setDisplayShowTitleEnabled(false);
//        // Set custom view layout
//        View mCustomView = LayoutInflater.from(this).inflate(R.layout.actionbar_main, null);
//        actionBar.setCustomView(mCustomView);
//        // Set no padding both side
//        Toolbar parent = (Toolbar) mCustomView.getParent(); // first get parent toolbar of current action bar
//        parent.setContentInsetsAbsolute(0, 0);              // set padding programmatically to 0dp
//        // Set actionbar background image
//
//        // Set actionbar layout layoutparams
//        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
//        actionBar.setCustomView(mCustomView, params);
//    }
}
