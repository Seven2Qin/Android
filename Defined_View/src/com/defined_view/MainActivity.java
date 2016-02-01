/*******************************************************************************
 *
 * Copyright (c) Baina Info Tech Co. Ltd
 *
 * PictureUploadActivity
 *
 * app.ui.activity.PictureUploadActivity.java
 * TODO: File description or class description.
 *
 * @author: qixiao
 * @since:  Apr 27, 2014
 * @version: 1.0.0
 *
 * @changeLogs:
 *     1.0.0: First created this class.
 *
 ******************************************************************************/
package com.defined_view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.R.string;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

/**
 * PictureUploadActivity of Shizhenbao
 *
 */
public class MainActivity extends Activity implements OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    //final GenericDataManager mGenericDataManager = GenericDataManager.getInstance();
    //final UserConfigManager mUserConfigManager  = UserConfigManager.getInstance();

    private ViewGroup mLayout;
    private List<ImageButton> mImageButtonList;
    private List<String> mPicturePathList;
    private List<String> mPictureUrlList;

    private String mUrlPrefix;

    /** 当前操作的是哪个按钮 */
    private int mCurrent;

    private Dialog mDialog;

    /* (non-Javadoc)
     * @see app.ui.TitleActivity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayManager.initialize(this);

        mImageButtonList = new ArrayList<ImageButton>();
        mPicturePathList = new ArrayList<String>();
        mPictureUrlList = new ArrayList<String>();

        //GlobalConfigManager manager = GlobalConfigManager.getInstance();
        //mUrlPrefix = manager.getUrlPrefix();
        mCurrent = 0;
        //mDialog = DialogUtils.createLoadingDialog(this, "loading...");
        setupViews();
    }

    /* (non-Javadoc)
     * @see app.ui.TitleActivity#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        if (v.getTag() != null) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, 0);
        } else {
            //super.onClick(v);
        }
    }


    /* (non-Javadoc)
     * @see app.ui.TitleActivity#onForward(android.view.View)
     */
    /*    @Override
    protected void onForward(View forwardView) {
        uploadPictures();
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data != null) {
            final Uri uri = data.getData();
            if (uri != null) {
                processPicture(uri);
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setupViews() {
        setContentView(R.layout.activity_main);
        //setTitle(R.string.button_service_upload_picture);
        //showBackwardView(R.string.button_backward, true);
        //showForwardView(R.string.button_upload,true);

        //最顶层父布局
        mLayout = (ViewGroup) findViewById(R.id.layout_container);

        final int count = 9;    //9格
        final int rowCount = (count + 2) / 3;

        for (int i = 0; i < rowCount; i++) {

            if (i != 0) {
                //加载横向布局线条
                View.inflate(this, R.layout.layout_line_horizonal, mLayout);
            }
            //创建布局对象，设置按下颜色
            final LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setBackgroundResource(R.drawable.row_selector);

            for (int j = 0; j < 3; j++) {

                if (j != 0) {
                    //加载内层纵向布局线条
                    View.inflate(this, R.layout.layout_line_vertical, linearLayout);
                }

                ImageButton imageButton = new ImageButton(this);
                imageButton.setBackgroundResource(R.drawable.row_selector);
                imageButton.setTag(TAG);
                imageButton.setOnClickListener(this);
                imageButton.setEnabled(false);
                LinearLayout.LayoutParams layoutParams =
                        new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1.0f);
                //添加到linearLayout布局中
                linearLayout.addView(imageButton, layoutParams);

                //将imageButton对象添加到列表
                mImageButtonList.add(imageButton);
            }

            DisplayManager manager = DisplayManager.getInstance();
            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, manager.dipToPixel(100));
            mLayout.addView(linearLayout, layoutParams);
        }

        final ImageButton currentImageButton = mImageButtonList.get(mCurrent);
        currentImageButton.setImageResource(R.drawable.ic_add_picture);
        currentImageButton.setScaleType(ScaleType.CENTER);
        currentImageButton.setEnabled(true);

    }

    private void processPicture(Uri uri) {
        final String[] projection = {MediaStore.Images.Media.DATA };
        final Cursor cursor = managedQuery(uri, projection, null, null, null);
        cursor.moveToFirst();
        final int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        final String path = cursor.getString(columnIndex);

        final String PATH_HOME = "/.view/picture/";
        final String targetPath = PATH_HOME + StringUtils.toRegularHashCode(path) + ".jpg";
        BitmapUtils.compressBitmap(path, targetPath, 640);
        mPicturePathList.add(targetPath);

        Bitmap bitmap = BitmapUtils.decodeBitmap(path, 150);
        final ImageButton imageButton = mImageButtonList.get(mCurrent);
        imageButton.setImageBitmap(bitmap);
        imageButton.setScaleType(ScaleType.FIT_XY);
        imageButton.setEnabled(false);

        if (mCurrent < mImageButtonList.size() - 1) {
            mCurrent = mCurrent + 1;
            final ImageButton nextImageButton = mImageButtonList.get(mCurrent);
            nextImageButton.setImageResource(R.drawable.ic_add_picture);
            nextImageButton.setScaleType(ScaleType.CENTER);
            nextImageButton.setEnabled(true);
        } else {
            //ApplicationUtils.showToast(this, R.string.toast_picture_count_limit);
        }
    }

    private void uploadPictures() {
        final UploadPictureTask task = new UploadPictureTask();
        task.execute(mPicturePathList);
        if (mDialog != null) {
            mDialog.show();
        }
    }

    /*    private void addPictures() {
        final GenericDataManager manager = GenericDataManager.getInstance();
        final PictureAddRequest request = new PictureAddRequest(0, "Default", mPictureUrlList);
        manager.retrieveData(request, new ResultParser(), this);
    }*/

    /* (non-Javadoc)
     * @see android.app.Activity#onDestroy()
     */
    @Override
    protected void onDestroy() {
        if (mDialog != null) {
            mDialog.cancel();
        }
        super.onDestroy();
    }

    /* (non-Javadoc)
     * @see app.backend.network.IRequestCallback#onRequestStart(int)
     */
    /*    @Override
    public void onRequestStart(int requestId) {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }*/

    /* (non-Javadoc)
     * @see app.backend.network.IRequestCallback#onRequestSuccess(int, app.backend.network.Result)
     */
    /*    @Override
    public void onRequestSuccess(int requestId, Result<?> result) {
        if (result != null && result.isOK()) {
            ApplicationUtils.showToast(this, "上传图片成功");
            finish();
        } else {
            ApplicationUtils.showToast(this, "上传图片失败");
        }
    }*/

    /* (non-Javadoc)
     * @see app.backend.network.IRequestCallback#onRequestError(int, int)
     */
    /*   @Override
    public void onRequestError(int requestId, int errorCode) {
        if (mDialog != null) {
            mDialog.cancel();
        }
    }*/

    private class UploadPictureTask extends AsyncTask<List<String>, Integer, String> {

        /* (non-Javadoc)
         * @see android.os.AsyncTask#doInBackground(Params[])
         */
        @Override
        protected String doInBackground(List<String>... params) {
            final List<String> pictureList = params[0];
            for (int i = 0, len = pictureList.size(); i < len; i++) {
                final File file = new File(pictureList.get(i));
                //final String response = ApacheHttpUtils.post(mUrlPrefix + "/upload", new File[] {file});
                // 解析，存储
                //final UploadInfo upload = new UploadParser().parse(response).getData();
                /*if (upload != null) {
                    final String url = upload.getUrl();
                    if (url != null) {
                        mPictureUrlList.add(url);
                    }
                }*/
                publishProgress(i);
            }
            return null;
        }

        /* (non-Javadoc)
         * @see android.os.AsyncTask#onProgressUpdate(Progress[])
         */
        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        /* (non-Javadoc)
         * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
         */
        @Override
        protected void onPostExecute(String result) {
            //addPictures();
            super.onPostExecute(result);
        }
    }

}
