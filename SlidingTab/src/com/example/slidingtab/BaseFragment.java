package com.example.slidingtab;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment{
	private static String DATA = "data";



	public static Fragment newInstance(int type){
		Fragment fragment = new BaseFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(DATA, type);
		fragment.setArguments(bundle);
		return fragment;
	}

}
