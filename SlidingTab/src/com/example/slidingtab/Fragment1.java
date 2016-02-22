package com.example.slidingtab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.slidingtab.R;

public class Fragment1 extends BaseFragment{
	 
    private ListView mListView;//拍场列表
    private ListAdapter mAdapter;//拍场适配器
    int mTag;
 
 
    public static Fragment1 newInstance(int tag) {
        Fragment1 f = new Fragment1();
        f.mTag = tag; 
     
        return f;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment, null);
 
        return v;
    }
 
}
