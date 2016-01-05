package com.example.listviewpagerdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnScrollListener {

    private ListView mNewsListView;
    private CustomAdapter newsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.newslist_main);

        mNewsListView = (ListView) findViewById(R.id.newslist);

        newsAdapter = new CustomAdapter(this);
        mNewsListView.setAdapter(newsAdapter);
        mNewsListView.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
            int totalItemCount) {
        // TODO Auto-generated method stub
        View itemView = view.getAdapter().getView(0, null, null);
        EditText edit = (EditText) itemView.findViewById(R.id.edit);
        Toast.makeText(getApplicationContext(), edit.getText(), 1000).show();
        RelativeLayout relativeLayout = (RelativeLayout) itemView.findViewById(R.id.bg_edit);
        int[] location = new int[2];
        relativeLayout.getLocationOnScreen(location);
        android.util.Log.i("seven", "" + edit.getText() + "    " + location[0] + "   "
                + location[1]);
        if (location[1] < 140) {
            relativeLayout.setBackgroundColor(Color.TRANSPARENT);
        } else {
            relativeLayout.setBackgroundColor(Color.WHITE);
        }

    }

}
