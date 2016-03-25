package com.example.draggridview;

/**
 * Created by su on 2015/11/12.
 */


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 通用 ViewHolder 缓存类
 *
 */
public class ViewHolder{

    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;

    private ViewHolder(Context context, ViewGroup parent, int layoutId,int position)
    {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,false);
        mConvertView.setTag(this);   // setTag
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView,ViewGroup parent, int layoutId, int position)
    {
        if (convertView == null){
            //创建ViewHolder对象 ,并做View缓存
            return new ViewHolder(context, parent, layoutId, position);
        }
        return (ViewHolder)convertView.getTag();
    }

    public View getConvertView()
    {
        return mConvertView;
    }


    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId){

        View view = mViews.get(viewId);
        if (view == null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T)view;
    }


    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setEditText(int viewId, String text)
    {
        EditText view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text)
    {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }


    public ViewHolder setText(int viewId, int strId)
    {
        TextView view = getView(viewId);
        view.setText(strId);
        return this;
    }

    public ViewHolder setTextDrawLeft(int viewId,Drawable drawable){
        TextView view = getView(viewId);
        view.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        return this;
    }

    public ViewHolder setTextDrawRight(int viewId,Drawable drawable){
        TextView view = getView(viewId);
        view.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        return this;
    }

    public ViewHolder setTextColor(int textViewId,  int color) {
        TextView view = getView(textViewId);
        view.setTextColor(color);
        return this;
    }


    /**
     * 为ImageView设置图片
     *  setImageResource
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int drawableId)
    {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }


    public ViewHolder setTextViewBackgroundColor(int viewId, int color) {
        TextView textView = getView(viewId);
        if (textView != null) {
            textView.setBackgroundColor(color);
        }
        return this;
    }

    /**
     * 为ImageView设置图片
     *  setImageBitmap
     * @param viewId
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm)
    {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    /**
     * 为ImageView设置图片
     * setImageByUrl
     *
     * @return
     */
   /* public ViewHolder setImageByUrl(int viewId, String url)
    {
        ImageLoader.getInstance(3, Type.LIFO).loadImage(url,(ImageView) getView(viewId));
        return this;
    }*/

    /**
     * 设置view的可见性
     * @param viewId
     * @param visibility
     */
    public void setViewVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        if (view != null) {
            view.setVisibility(visibility);
        }
    }

    /**
     * 为view设置监听事件
     * @param viewId
     * @param listener
     */
    public void setViewClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnClickListener(listener);
        }
    }

    public int getPosition(){

        return mPosition;
    }

}
