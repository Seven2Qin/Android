package com.example.draggridview;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

public class MainActivity extends Activity {

	private ArrayList<ShortCutItem> mDatas;
	private BaseAdapter mAdapter;
	private DragGridView grid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		grid = (DragGridView) findViewById(R.id.grid);
		mDatas = new ArrayList<ShortCutItem>();
		initShortCuts();
	}

	private ArrayList<ShortCutItem> getPreset() {
		ArrayList<ShortCutItem> temp = new ArrayList<ShortCutItem>();
		/* 默认显示网点管理、代客下单 */
		temp.add(new ShortCutItem(true, Constant.ShortCutType.STORE_MGR));
		temp.add(new ShortCutItem(true, Constant.ShortCutType.STORE_TAG));
		temp.add(new ShortCutItem(true, Constant.ShortCutType.STORE_MEET));
		temp.add(new ShortCutItem(true, Constant.ShortCutType.CALL_AGAIN));
		temp.add(new ShortCutItem(true, Constant.ShortCutType.ORDER_SEARCH));
		temp.add(new ShortCutItem(true, Constant.ShortCutType.ORDER_BOOKING));
		temp.add(new ShortCutItem(true, Constant.ShortCutType.STORE_REG));
		temp.add(new ShortCutItem(true, Constant.ShortCutType.PLAN_MEET));
		temp.add(new ShortCutItem(true, Constant.ShortCutType.ADD_MORE));

		return temp;
	}

	private void initShortCuts() {
		
		for (ShortCutItem item : getPreset()) {
			if (item.isAddedToShortCut()) {
				mDatas.add(item);
			}
		}

		// set up grid view
		grid.setAdapter(mAdapter = new CommonAdapter<ShortCutItem>(this, mDatas, R.layout.common_main_grid) {
			@Override
			public void convert(ViewHolder helper, final ShortCutItem item, int position) {

				switch (item.getType()) {
				// add more
				case Constant.ShortCutType.ADD_MORE:
					helper.setImageResource(R.id.iv_item, R.drawable.shortcut_add_more);
					helper.setText(R.id.tv_item, getString(R.string.short_cut_add_more));
					break;
				case Constant.ShortCutType.STORE_MGR:
					// 网点管理
					helper.setImageResource(R.id.iv_item, R.drawable.icon_func_store_mgr);
					helper.setText(R.id.tv_item, getString(R.string.func_store_mgr));
					break;
				case Constant.ShortCutType.STORE_TAG:
					// 网点标注
					helper.setImageResource(R.id.iv_item, R.drawable.icon_func_store_tag);
					helper.setText(R.id.tv_item, getString(R.string.func_store_tag));
					break;
				case Constant.ShortCutType.STORE_MEET:
					// 网点拜访
					helper.setImageResource(R.id.iv_item, R.drawable.icon_func_store_meet);
					helper.setText(R.id.tv_item, getString(R.string.func_store_meet));
					break;
				case Constant.ShortCutType.CALL_AGAIN:
					// 电话回访
					helper.setImageResource(R.id.iv_item, R.drawable.icon_func_call_again);
					helper.setText(R.id.tv_item, getString(R.string.func_call_again));
					break;
				case Constant.ShortCutType.ORDER_SEARCH:
					// 订单查询
					helper.setImageResource(R.id.iv_item, R.drawable.icon_func_order_search);
					helper.setText(R.id.tv_item, getString(R.string.func_order_search));
					break;
				case Constant.ShortCutType.ORDER_BOOKING:
					// 代客下单
					helper.setImageResource(R.id.iv_item, R.drawable.icon_func_order_booking);
					helper.setText(R.id.tv_item, getString(R.string.func_order_booking));
					break;
				case Constant.ShortCutType.STORE_REG:
					// 网点注册
					helper.setImageResource(R.id.iv_item, R.drawable.icon_func_store_reg);
					helper.setText(R.id.tv_item, getString(R.string.func_store_reg));
					break;
				case Constant.ShortCutType.PLAN_MEET:
					// 计划拜访
					helper.setImageResource(R.id.iv_item, R.drawable.index_icon_plan);
					helper.setText(R.id.tv_item, getString(R.string.plan_visit_title));
					break;
				}

			}
		});

		grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				ShortCutItem item = mDatas.get(i);
				if (item != null) {
					switch (item.getType()) {
					// add more
					case Constant.ShortCutType.ADD_MORE:
						android.util.Log.i("seven","ADD_MORE");
						break;
					// normal short cut
					case Constant.ShortCutType.STORE_MGR:
						android.util.Log.i("seven","STORE_MGR");
						// 网点管理
						break;
					case Constant.ShortCutType.STORE_TAG:
						android.util.Log.i("seven","STORE_TAG");
						// 网点标注
						break;
					case Constant.ShortCutType.STORE_MEET:
						android.util.Log.i("seven","STORE_MEET");
						// 网点拜访
						break;
					case Constant.ShortCutType.CALL_AGAIN:
						android.util.Log.i("seven","CALL_AGAIN");
						// 电话回访
						break;
					case Constant.ShortCutType.ORDER_SEARCH:
						android.util.Log.i("seven","ORDER_SEARCH");
						// 订单查询
						break;
					case Constant.ShortCutType.ORDER_BOOKING:
						android.util.Log.i("seven","ORDER_BOOKING");
						// 代客下单
						break;
					case Constant.ShortCutType.STORE_REG:
						android.util.Log.i("seven","STORE_REG");
						// 网点注册
						break;
					case Constant.ShortCutType.PLAN_MEET:
						android.util.Log.i("seven","PLAN_MEET");
						// 计划拜访
						break;
					}
				}
			}
		});

		// 设置拖拽数据交换
		grid.setOnChangeListener(new DragGridView.OnChangeListener() {
			@Override
			public void onChange(int from, int to) {
				ShortCutItem temp = mDatas.get(from);

				if (from < to) {
					for (int i = from; i < to; i++) {
						Collections.swap(mDatas, i, i + 1);
					}
				} else if (from > to) {
					for (int i = from; i > to; i--) {
						Collections.swap(mDatas, i, i - 1);
					}
				}

				mDatas.set(to, temp);
				mAdapter.notifyDataSetChanged();
				android.util.Log.i("seven","onChange");
			}
		});

		grid.setOnDragStartListener(new DragGridView.OnDragStartListener() {
			@Override
			public void onDragStart() {
				//scroller.requestDisallowInterceptTouchEvent(true);
				//this.setViewpagerNoSCroll(true);
				grid.getParent().requestDisallowInterceptTouchEvent(true);
				android.util.Log.i("seven","setOnDragStartListener");
			}
		});
		grid.setOnDragEndListener(new DragGridView.OnDragEndListener() {
			@Override
			public void onDragEnd() {
				//scroller.requestDisallowInterceptTouchEvent(false);
				//this.setViewpagerNoSCroll(false);
				android.util.Log.i("seven","setOnDragEndListener");
				grid.postInvalidate();
			}
		});
	}
}
