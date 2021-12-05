package com.malkang.mitier.a105_minesweeper02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ConfirmRankActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    Button exitButton;

    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_rank);

        listView = findViewById(R.id.listview);
        exitButton = findViewById(R.id.button7);

        ItemAdapter adapter1 = new ItemAdapter();
        adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.list_item);
        listView.setAdapter(adapter1);

        pref = getSharedPreferences("pref",MODE_PRIVATE);

        // 저장되어 있는 값을 가지고 와서 대입 시켜야 한다.

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


//        for(int i = 0 ; i < 10; i++)
//        {
//            String data =  pref.getString("saveData"+i,"이름없음/999");
//        }
    }

    class ItemAdapter extends BaseAdapter
    {
        ArrayList<ListItem> items = new ArrayList<ListItem>();

        @Override
        public int getCount() {
            Log.d("@@@@@@@@@@@@" , ""+items.size());
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ItemListView itemListView = null;
            if(view == null)
            {
                itemListView = new ItemListView(getApplicationContext());
            }
            else
            {
                itemListView = (ItemListView)view;
            }
            ListItem item = items.get(i);
            Log.d("@@@@@@@@@@@@" , ""+item.getTime());
            itemListView.setRank(""+i);
            itemListView.setName(item.getName());
            itemListView.setScore(""+item.getTime());


            return itemListView;
        }
    }

}