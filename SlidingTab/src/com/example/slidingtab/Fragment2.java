package com.example.slidingtab;

import com.example.slidingtab.R;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts.Data;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Fragment2 extends BaseFragment{
	 
    private ListView mListView;//拍场列表
    private ListAdapter mAdapter;//拍场适配器
    int mTag;
 
    public static Fragment2 newInstance(int tag) {
        Fragment2 f = new Fragment2();
        f.mTag = tag; 
     
        return f;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment2, null);
 
        return v;
    }
 
}
