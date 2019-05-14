//package safecall.safecall;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//public class CurrentStartPointActivity extends AppCompatActivity {
//    ListView current_start_point_list;
//    CurrentStartPointAdapter current_start_point_adapter;
//    Button back;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
//        setContentView(R.layout.activity_current_start_point);
//
//        back = (Button) findViewById(R.id.back);
//
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//
//
//        ArrayList<CurrentStartPointData> current_data_list = new ArrayList<>();
//        for(int i=0; i<10; i++) {
//            current_data_list.add(new CurrentStartPointData("한국폴리텍1대학 서울 강서캠퍼스", "서울특별시 강서구 화곡동 산60-1"));
//            current_data_list.add(new CurrentStartPointData("교보문고 강남점", "서울특별시 서초구 서초4동 강남대로 465"));
//            current_data_list.add(new CurrentStartPointData("도로교통공단 서울지부", "서울특별시 서초구 내곡동 양재대로 242"));
//        }
//        current_start_point_adapter = new CurrentStartPointAdapter(current_data_list, CurrentStartPointActivity.this);
//        current_start_point_list = (ListView) findViewById(R.id.current_start_point_listview);
//        current_start_point_list.setAdapter(current_start_point_adapter);
//
//
//    }
//
//    public class CurrentStartPointData{
//        String name;
//        String address;
//        CurrentStartPointData(String name, String address){
//            this.name=name;
//            this.address=address;
//        }
//    }
//
//    public class CurrentStartPointAdapter extends BaseAdapter {
//        ArrayList<CurrentStartPointData> data_list;
//        LayoutInflater inflater;
//        Context con;
//        CurrentStartPointAdapter(ArrayList<CurrentStartPointData> data_list, Context con){
//            this.data_list = data_list;
//            this.con = con;
//            this.inflater = (LayoutInflater) this.con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        }
//        @Override
//        public int getCount() {
//            return data_list.size();
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return data_list.get(i);
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return i;
//        }
//
//        @Override
//        public View getView(int i, View convert_view, ViewGroup viewGroup) {
//            convert_view = inflater.inflate(R.layout.activity_current_start_point_item, viewGroup, false);
//            TextView name = (TextView) convert_view.findViewById(R.id.name);
//            TextView address = (TextView) convert_view.findViewById(R.id.address);
//
//            name.setText(data_list.get(i).name);
//            address.setText(data_list.get(i).address);
//
//            return convert_view;
//        }
//    }
//
//}
