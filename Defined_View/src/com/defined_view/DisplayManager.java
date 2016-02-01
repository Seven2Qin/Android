/*******************************************************************************
 *
 * Copyright (c) Weaver Info Tech Co. Ltd
 *
 * DisplayManager
 *
 * app.backend.manager.DisplayManager.java
 * 管理屏幕分辨率
 *
 * @author: qixiao
 * @since:  Aug 27, 2013
 * @version: 1.0.0
 *
 * @changeLogs:
 *     1.0.0: First created this class.
 *
 ******************************************************************************/
package com.defined_view;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * @author gao_chun
 *
 */
public class DisplayManager {

    private static final String TAG = DisplayManager.class.getSimpleName();

    public static int SQUARE_IMAGE_WIDTH;
    public static int SQUARE_IMAGE_HEIGHT;

    public static int MERCHANT_IMAGE_SIZE;
    public static int PRODUCT_IMAGE_SIZE;
    public static int AVATAR_IMAGE_SIZE;
    public static int SHARE_IMAGE_SIZE;

    private static final int SQUARE_IMAGE_WIDTH_DP = 320;
    private static final int SQUARE_IMAGE_HEIGHT_DP = 200;

    private static final int MERCHANT_IMAGE_SIZE_DP = 72;
    private static final int PRODUCT_IMAGE_SIZE_DP = 72;
    private static final int AVATAR_IMAGE_SIZE_DP = 72;
    private static final int SHARE_IMAGE_SIZE_DP = 72;

    private static DisplayManager sInstance;

    private float mDensity = 1.0f;

    private DisplayManager(float density) {
        mDensity = density;

        SQUARE_IMAGE_WIDTH = (int) (SQUARE_IMAGE_WIDTH_DP * mDensity + 0.5);
        SQUARE_IMAGE_HEIGHT = (int) (SQUARE_IMAGE_HEIGHT_DP * mDensity + 0.5);

        MERCHANT_IMAGE_SIZE = (int) (MERCHANT_IMAGE_SIZE_DP * mDensity + 0.5);
        PRODUCT_IMAGE_SIZE = (int) (PRODUCT_IMAGE_SIZE_DP * mDensity + 0.5);
        AVATAR_IMAGE_SIZE = (int) (AVATAR_IMAGE_SIZE_DP * mDensity + 0.5);
        SHARE_IMAGE_SIZE = (int) (SHARE_IMAGE_SIZE_DP * mDensity + 0.5);
    }

    public static void initialize(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        sInstance = new DisplayManager(displayMetrics.density);
    }

    public static DisplayManager getInstance() {
        // 防止在初始化之前被调用
        if (sInstance != null) {
            return sInstance;
        } else {
            throw new IllegalStateException("DisplayManager not initialized.");
        }
    }

    public int dipToPixel(float dip) {
        return (int) (dip * mDensity + 0.5);
    }

    public int convertSize(int size) {
        return (int) (size * mDensity + 0.5);
    }
}
