package com.example.test_11.doctorappointmentapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PlaceListActivity extends AppCompatActivity {
    ListView lv;
   // ArrayList<LocationModal> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);

        lv = (ListView) findViewById(R.id.listView1);
        MyAdapter adapter=new MyAdapter(this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(PlaceListActivity.this, MapActivity.class);
                intent.putExtra("Position",position);
                startActivity(intent);
            }
        });
        findViewById(R.id.backBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
            }
        });
    }

    public class MyAdapter extends BaseAdapter {
        public MyAdapter(Context context)

        {

        }


       @Override
       public int getCount() {
           // TODO Auto-generated method stub
           return 10;
       }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            TextView tv1, tv2,tv3;
            LayoutInflater inflater = getLayoutInflater();
            View row1 = inflater.inflate(R.layout.row, parent, false);

           return row1;
        }

    }
}
