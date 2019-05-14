package com.mind.user;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;


import java.util.ArrayList;

public class SearchPointActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ListView search_point_list;
    SearchPointAdapter search_point_adapter;
    ArrayList<Point> search_list = new ArrayList<>();

    EditText search;

    boolean check_start = false;

    HttpUtil httpUtil = new HttpUtil();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_point);



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


        check_start = getIntent().getBooleanExtra("start", false);


        final ArrayList<Point> data_list = new ArrayList<>();

        data_list.add(new Point("강남역 2호선", "서울 마포구 창전동 39-1", new LatLng(37.497985, 127.027632)));

        data_list.add(new Point("강남역 2호선 9번출구", "서울 마포구 창전동 39-1", new LatLng(37.497925, 127.026902)));

        data_list.add(new Point("강남구청역 7호선", "서울 마포구 창전동 39-1", new LatLng(37.517172, 127.041221)));


        data_list.add(new Point("스타벅스 강남역점", "서울 마포구 창전동 39-1", new LatLng(37.500092, 127.025560)));
        data_list.add(new Point("이니스프리 강남역전점", "서울 마포구 창전동 39-1", new LatLng(37.500264, 127.026065)));


//        for(int i=0; i<10; i++) {
//            current_data_list.add(new SearchPointData("한국폴리텍1대학 서울 강서캠퍼스", "서울특별시 강서구 화곡동 산60-1"));
//            current_data_list.add(new SearchPointData("교보문고 강남점", "서울특별시 서초구 서초4동 강남대로 465"));
//            current_data_list.add(new SearchPointData("도로교통공단 서울지부", "서울특별시 서초구 내곡동 양재대로 242"));
//        }

        search = (EditText) findViewById(R.id.search_edit);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                search_list.clear();
//                String url = "https://apis.skplanetx.com/tmap/pois?appKey=ad198215-d00d-3aed-a5e9-652d17dfa1ea" +
//                        "&version=1" +
//                        "&page=1" +
//                        "&count=2" +
//                        "&searchKeyword=" + charSequence +
//                        "&areaLLCode=11" +
//                        "&areaLMCode=000" +
//                        "&resCoordType=WGS84GEO" +
//                        "&searchType=name" +
//                        "&searchtypCd=A" +
//                        "&radius=0" +
//                        "&reqCoordType=WGS84GEO";

//                httpUtil.setUrl(url)
//                        .setMethod(httpUtil.HTTP_METHOD_GET)
//                        .setCallback(new Callback() {
//                            @Override
//                            public void onFailure(Call call, IOException e) {
//
//                            }
//
//                            @Override
//                            public void onResponse(Call call, Response response) throws IOException {
////                                Log.e("Res", response.body().string());
//                                System.out.println(response.body().string());
////                        Toast.makeText(SearchPointActivity.this, String.valueOf(response.body()), Toast.LENGTH_SHORT).show();
//
//
//                                JSONObject jObject = null;   // JSONArray 생성
//                                try {
//                                    jObject = new JSONObject(response.body().string());
//                                    JSONObject searchPoiInfo = jObject.getJSONObject("searchPoiInfo");
//                                    JSONObject pois = searchPoiInfo.getJSONObject("pois");
//                                    JSONArray poi = pois.getJSONArray("poi");
//                                    for (int i = 0; i < poi.length(); i++) {
//                                        jObject = poi.getJSONObject(i);
//                                        String name = jObject.getString("name");
//                                        String upperAddrName = jObject.getString("upperAddrName");
//                                        String middleAddrName = jObject.getString("middleAddrName");
//                                        String lowerAddrName = jObject.getString("lowerAddrName");
//                                        Double noorLat = jObject.getDouble("noorLat");
//                                        Double noorLon = jObject.getDouble("noorLon");
//
//                                        search_list.add(new Point(name, upperAddrName+middleAddrName+lowerAddrName, new LatLng(noorLat, noorLon)));
//                                    }
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//
//                        })
//                        .execute();
//
//
                for (Point data : data_list) {
                    if (data != null && data.name.contains(charSequence)){
                        //something here
//                        Log.e("SEARCH", data.title);
                        search_list.add(data);
                    }

                }
//
            }


            @Override
            public void afterTextChanged(Editable editable) {
                search_point_adapter.notifyDataSetChanged();
            }
        });

        search_point_adapter = new SearchPointAdapter(search_list, SearchPointActivity.this);


        search_point_list = (ListView)

                findViewById(R.id.search_point_list_view);
        search_point_list.setAdapter(search_point_adapter);
        search_point_list.setDividerHeight(0);


        search_point_list.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (check_start) {
                    MainActivity.start_point = search_list.get(i);
                } else {
                    MainActivity.end_point = search_list.get(i);
                }
                finish();
            }
        });


    }


    public class SearchPointAdapter extends BaseAdapter {
        ArrayList<Point> data_list;
        LayoutInflater inflater;
        Context con;

        SearchPointAdapter(ArrayList<Point> data_list, Context con) {
            this.data_list = data_list;
            this.con = con;
            this.inflater = (LayoutInflater) this.con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            return data_list.size();
        }

        @Override
        public Object getItem(int i) {
            return data_list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convert_view, ViewGroup viewGroup) {
            convert_view = inflater.inflate(R.layout.search_list_view_item, viewGroup, false);
            TextView name = (TextView) convert_view.findViewById(R.id.name);
            TextView address = (TextView) convert_view.findViewById(R.id.address);

            name.setText(data_list.get(i).name);
            address.setText(data_list.get(i).address);

            return convert_view;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
