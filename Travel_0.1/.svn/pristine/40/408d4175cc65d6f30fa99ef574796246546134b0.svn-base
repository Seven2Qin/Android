/**
 * 项目名：     Travel
 * 文件名：     AnimationUtils.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月18日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.view.anim;

import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * 类名称：     AnimationUtils
 * 作者：         Administrator
 * 创建时间：  2013年7月18日 上午10:07:52
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月18日 上午10:07:52
 *
 */
public class AnimationUtils
{

    public AnimationUtils()
    {
    }

    public static void closeAnimateAlpha(View view, int i,
            android.view.animation.Animation.AnimationListener animationlistener)
    {
        AnimationSet animationset = new AnimationSet(true);
        AlphaAnimation alphaanimation = new AlphaAnimation(1F, 0F);
        long l = i;
        alphaanimation.setDuration(l);
        animationset.addAnimation(alphaanimation);
        animationset.setAnimationListener(animationlistener);
        view.startAnimation(animationset);
    }

    public static void closeAnimateMenu(final View view,
            final android.view.animation.Animation.AnimationListener listener)
    {
        AnimationSet animationset = new AnimationSet(true);
        ScaleAnimation scaleanimation = new ScaleAnimation(1F, 0.9F, 1F, 1.1F);
        scaleanimation.setDuration(100L);
        animationset.addAnimation(scaleanimation);
        TranslateAnimation translateanimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0F, Animation.RELATIVE_TO_SELF, 0F,
                Animation.RELATIVE_TO_SELF, 0F, Animation.RELATIVE_TO_SELF,
                -0.1F);
        translateanimation.setDuration(100L);
        animationset.addAnimation(translateanimation);

        _cls1 _lcls1 = new _cls1(view, listener);
        animationset.setAnimationListener(_lcls1);
        view.startAnimation(animationset);
    }

    public static void closeAnimateReport(final View view,
            final android.view.animation.Animation.AnimationListener listener)
    {
        AnimationSet animationset = new AnimationSet(true);
        ScaleAnimation scaleanimation = new ScaleAnimation(1F, 0.9F, 1F, 1.1F);
        scaleanimation.setDuration(100L);
        animationset.addAnimation(scaleanimation);
        int i = 1;
        int j = 1;
        float f = 0F;
        int k = 1;
        TranslateAnimation translateanimation = new TranslateAnimation(1, 0F,
                i, 0.1F, j, f, k, -0.1F);
        translateanimation.setDuration(100L);
        animationset.addAnimation(translateanimation);
        _cls2 _lcls2 = new _cls2(view, listener);
        animationset.setAnimationListener(_lcls2);
        view.startAnimation(animationset);
    }
    
    public static void closeAnimateLeft3(final View view,
            final android.view.animation.Animation.AnimationListener listener)
    {
        AnimationSet animationset = new AnimationSet(true);
        ScaleAnimation scaleanimation = new ScaleAnimation(1F, 0.9F, 1F, 1.1F);
        scaleanimation.setDuration(100L);
        animationset.addAnimation(scaleanimation);
        TranslateAnimation translateanimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0F, Animation.RELATIVE_TO_SELF, 0F,
                Animation.RELATIVE_TO_SELF, 0F, Animation.RELATIVE_TO_SELF,
                -0.1F);
        translateanimation.setDuration(100L);
        animationset.addAnimation(translateanimation);

        _clsLeft3Close _lcls1 = new _clsLeft3Close(view, listener);
        animationset.setAnimationListener(_lcls1);
        view.startAnimation(animationset);
    }

    public static void closeAnimateRight2(final View view,
            final android.view.animation.Animation.AnimationListener listener)
    {
        AnimationSet animationset = new AnimationSet(true);
        ScaleAnimation scaleanimation = new ScaleAnimation(1F, 0.9F, 1F, 1.1F);
        scaleanimation.setDuration(100L);
        animationset.addAnimation(scaleanimation);
        int i = 1;
        int j = 1;
        float f = 0F;
        int k = 1;
        TranslateAnimation translateanimation = new TranslateAnimation(1, 0F,
                i, 0.1F, j, f, k, -0.1F);
        translateanimation.setDuration(100L);
        animationset.addAnimation(translateanimation);
        _clsRight2Close _lcls2 = new _clsRight2Close(view, listener);
        animationset.setAnimationListener(_lcls2);
        view.startAnimation(animationset);

    }

    public static void closeAnimateToPoint(View view, float f, float f1, int i,
            android.view.animation.Animation.AnimationListener animationlistener)
    {
        AnimationSet animationset = new AnimationSet(true);
        ScaleAnimation scaleanimation = new ScaleAnimation(1F, 0F, 1F, 0F);
        long l = i;
        scaleanimation.setDuration(l);
        animationset.addAnimation(scaleanimation);
        AlphaAnimation alphaanimation = new AlphaAnimation(1F, 0.2F);
        long l1 = i;
        alphaanimation.setDuration(l1);
        animationset.addAnimation(alphaanimation);
        float f2 = f;
        float f3 = f1;
        TranslateAnimation translateanimation = new TranslateAnimation(0, 0F,
                0, f2, 0, 0F, 0, f3);
        long l2 = i;
        translateanimation.setDuration(l2);
        animationset.addAnimation(translateanimation);
        android.view.animation.Animation.AnimationListener animationlistener1 = animationlistener;
        animationset.setAnimationListener(animationlistener1);
        view.startAnimation(animationset);
    }

    public static void openAnimateAlert(View view,
            android.view.animation.Animation.AnimationListener animationlistener)
    {
        AnimationSet animationset = new AnimationSet(true);
        int i = 1;
        float f = 0F;
        int j = 1;
        float f1 = 0F;
        int k = 1;
        TranslateAnimation translateanimation = new TranslateAnimation(1, 0F,
                i, f, j, f1, k, -2F);
        translateanimation.setDuration(150L);
        AccelerateInterpolator accelerateinterpolator = new AccelerateInterpolator();
        animationset.setInterpolator(accelerateinterpolator);
        animationset.addAnimation(translateanimation);
        animationset.setAnimationListener(animationlistener);
        view.setDrawingCacheEnabled(true);
        view.startAnimation(animationset);
    }

    public static void openAnimateAlert2(View view,
            android.view.animation.Animation.AnimationListener animationlistener)
    {
        AnimationSet animationset = new AnimationSet(true);
        int i = 1;
        float f = 0F;
        int j = 1;
        int k = 1;
        float f1 = 0F;
        TranslateAnimation translateanimation = new TranslateAnimation(1, 0F,
                i, f, j, -2F, k, f1);
        translateanimation.setDuration(400L);
        DecelerateInterpolator decelerateinterpolator = new DecelerateInterpolator();
        animationset.setInterpolator(decelerateinterpolator);
        animationset.addAnimation(translateanimation);
        animationset.setAnimationListener(animationlistener);
        view.setDrawingCacheEnabled(true);
        view.startAnimation(animationset);
    }

    public static void openAnimateFromPoint(View view, float f, float f1, int i)
    {
        openAnimateFromPoint(view, f, f1, i, null);
    }

    public static void openAnimateFromPoint(View view, float f, float f1,
            int i,
            android.view.animation.Animation.AnimationListener animationlistener)
    {
        AnimationSet animationset = new AnimationSet(true);
        ScaleAnimation scaleanimation = new ScaleAnimation(0F, 1F, 0F, 1F);
        long l = i;
        scaleanimation.setDuration(l);
        animationset.addAnimation(scaleanimation);
        AlphaAnimation alphaanimation = new AlphaAnimation(0.2F, 1F);
        long l1 = i;
        alphaanimation.setDuration(l1);
        animationset.addAnimation(alphaanimation);
        float f2 = f;
        float f3 = f1;
        TranslateAnimation translateanimation = new TranslateAnimation(0, f2,
                0, 0F, 0, f3, 0, 0F);
        long l2 = i;
        translateanimation.setDuration(l2);
        animationset.addAnimation(translateanimation);
        if (animationlistener != null)
        {
            android.view.animation.Animation.AnimationListener animationlistener1 = animationlistener;
            animationset.setAnimationListener(animationlistener1);
        }
        view.setDrawingCacheEnabled(true);
        view.startAnimation(animationset);
    }

    public static void openAnimateMenu(final View view,
            final android.view.animation.Animation.AnimationListener listener)
    {
        AnimationSet animationset = new AnimationSet(true);
        ScaleAnimation scaleanimation = new ScaleAnimation(0F, 0.95F, 0F, 1.03F);
        scaleanimation.setDuration(100L);
        animationset.addAnimation(scaleanimation);
        int i = 1;
        int j = 1;
        int k = 1;
        TranslateAnimation translateanimation = new TranslateAnimation(1, 0.1F,
                i, 0F, j, 1F, k, -0.03F);
        translateanimation.setDuration(100L);
        animationset.addAnimation(translateanimation);
        _cls3 _lcls3 = new _cls3(view, listener);
        animationset.setAnimationListener(_lcls3);
        view.setDrawingCacheEnabled(true);
        view.startAnimation(animationset);
    }

    public static void openAnimateReport(final View view,
            final android.view.animation.Animation.AnimationListener listener)
    {
        AnimationSet animationset = new AnimationSet(true);
        ScaleAnimation scaleanimation = new ScaleAnimation(0F, 0.95F, 0F, 1.03F);
        scaleanimation.setDuration(100L);
        animationset.addAnimation(scaleanimation);
        int i = 1;
        int j = 1;
        int k = 1;
        TranslateAnimation translateanimation = new TranslateAnimation(1, 0.9F,
                i, 0.05F, j, 1F, k, -0.03F);
        translateanimation.setDuration(100L);
        animationset.addAnimation(translateanimation);
        _cls4 _lcls4 = new _cls4(view, listener);
        animationset.setAnimationListener(_lcls4);
        view.setDrawingCacheEnabled(true);
        view.startAnimation(animationset);
    }
    
    public static void openAnimateLeft3Menu(final View view,
            final android.view.animation.Animation.AnimationListener listener)
    {
        AnimationSet animationset = new AnimationSet(true);
        ScaleAnimation scaleanimation = new ScaleAnimation(0F, 0.95F, 0F, 1.03F);
        scaleanimation.setDuration(100L);
        animationset.addAnimation(scaleanimation);
        int i = 1;
        int j = 1;
        int k = 1;
        TranslateAnimation translateanimation = new TranslateAnimation(1, 0.1F,
                i, 0F, j, 0F, k, -0.03F);
        translateanimation.setDuration(100L);
        animationset.addAnimation(translateanimation);
        _clsLeft3 _lclsLeft3 = new _clsLeft3(view, listener);
        animationset.setAnimationListener(_lclsLeft3);
        view.setDrawingCacheEnabled(true);
        view.startAnimation(animationset);
    }

    public static void openAnimateRight2(final View view,
            final android.view.animation.Animation.AnimationListener listener)
    {
        AnimationSet animationset = new AnimationSet(true);
        ScaleAnimation scaleanimation = new ScaleAnimation(0F, 0.95F, 0F, 1.03F);
        scaleanimation.setDuration(100L);
        animationset.addAnimation(scaleanimation);
        int i = 1;
        int j = 1;
        int k = 1;
        TranslateAnimation translateanimation = new TranslateAnimation(1, 0.9F,
                i, 0.05F, j, 0F, k, -0.03F);
        translateanimation.setDuration(100L);
        animationset.addAnimation(translateanimation);
        _clsRight2 _lcls4 = new _clsRight2(view, listener);
        animationset.setAnimationListener(_lcls4);
        view.setDrawingCacheEnabled(true);
        view.startAnimation(animationset);
    }

    //    public static void overshootCustomPopView(View view)
    //    {
    //        Animation animation = android.view.animation.AnimationUtils.loadAnimation(AppService.getAppContext(), 0x7f04000a);
    //        animation.setFillAfter(true);
    //        view.startAnimation(animation);
    //    }
    //
    //    public static void overshootNativePopView(View view)
    //    {
    //        Animation animation = android.view.animation.AnimationUtils.loadAnimation(AppService.getAppContext(), 0x7f04000b);
    //        view.startAnimation(animation);
    //    }

    public static void stretchViewHorizontally(View view, int i)
    {
        AnimationSet animationset = new AnimationSet(false);
        ScaleAnimation scaleanimation = new ScaleAnimation(0F, 1F, 1F, 1F);
        long l = i;
        scaleanimation.setDuration(l);
        animationset.addAnimation(scaleanimation);
        view.startAnimation(animationset);
    }

    private static class _cls1 implements
            android.view.animation.Animation.AnimationListener
    {

        public void onAnimationEnd(Animation animation)
        {
            AnimationSet animationset = new AnimationSet(true);
            ScaleAnimation scaleanimation = new ScaleAnimation(0.9F, 0F, 1.1F,
                    0F);
            long l = 200;
            scaleanimation.setDuration(l);
            animationset.addAnimation(scaleanimation);
            int i = 1;
            int j = 1;
            int k = 1;
            TranslateAnimation translateanimation = new TranslateAnimation(1,
                    0F, i, 0.1F, j, -0.1F, k, 1F);
            long l1 = 200;
            translateanimation.setDuration(l1);
            animationset.addAnimation(translateanimation);
            android.view.animation.Animation.AnimationListener animationlistener = listener;
            animationset.setAnimationListener(animationlistener);
            view.startAnimation(animationset);
        }

        public void onAnimationRepeat(Animation animation)
        {
        }

        public void onAnimationStart(Animation animation)
        {
        }

        private final android.view.animation.Animation.AnimationListener listener;
        private final View view;

        _cls1(View view1,
                android.view.animation.Animation.AnimationListener animationlistener)
        {
            listener = animationlistener;
            view = view1;

        }
    }

    private static class _cls2 implements
            android.view.animation.Animation.AnimationListener
    {

        public void onAnimationEnd(Animation animation)
        {
            AnimationSet animationset = new AnimationSet(true);
            ScaleAnimation scaleanimation = new ScaleAnimation(0.9F, 0F, 1.1F,
                    0F);
            long l = 200;
            scaleanimation.setDuration(l);
            animationset.addAnimation(scaleanimation);
            int i = 1;
            int j = 1;
            int k = 1;
            TranslateAnimation translateanimation = new TranslateAnimation(1,
                    0.1F, i, 0.9F, j, -0.1F, k, 1F);
            long l1 = 200;
            translateanimation.setDuration(l1);
            animationset.addAnimation(translateanimation);
            android.view.animation.Animation.AnimationListener animationlistener = listener;
            animationset.setAnimationListener(animationlistener);
            view.startAnimation(animationset);
        }

        public void onAnimationRepeat(Animation animation)
        {
        }

        public void onAnimationStart(Animation animation)
        {
        }

        private final android.view.animation.Animation.AnimationListener listener;
        private final View view;

        _cls2(View view1,
                android.view.animation.Animation.AnimationListener animationlistener)
        {
            super();
            listener = animationlistener;
            view = view1;
        }
    }

    private static class _cls3 implements
            android.view.animation.Animation.AnimationListener
    {

        public void onAnimationEnd(Animation animation)
        {
            AnimationSet animationset = new AnimationSet(true);
            ScaleAnimation scaleanimation = new ScaleAnimation(0.95F, 1.02F,
                    1.03F, 0.97F);
            scaleanimation.setDuration(100L);
            animationset.addAnimation(scaleanimation);
            int i = 1;
            float f = 0F;
            int j = 1;
            int k = 1;
            TranslateAnimation translateanimation = new TranslateAnimation(1,
                    0F, i, f, j, -0.03F, k, 0.03F);
            translateanimation.setDuration(100L);
            animationset.addAnimation(translateanimation);
            //            _cls1 _lcls1 = new _cls1(view, listener);
            animationset.setAnimationListener(listener);
            view.startAnimation(animationset);
        }

        public void onAnimationRepeat(Animation animation)
        {
        }

        public void onAnimationStart(Animation animation)
        {
        }

        private final android.view.animation.Animation.AnimationListener listener;
        private final View view;

        _cls3(View view1,
                android.view.animation.Animation.AnimationListener animationlistener)
        {
            super();
            view = view1;
            listener = animationlistener;

        }
    }

    private static class _cls4 implements
            android.view.animation.Animation.AnimationListener
    {

        public void onAnimationEnd(Animation animation)
        {
            AnimationSet animationset = new AnimationSet(true);
            ScaleAnimation scaleanimation = new ScaleAnimation(0.95F, 1.02F,
                    1.03F, 0.97F);
            scaleanimation.setDuration(100L);
            animationset.addAnimation(scaleanimation);
            int i = 1;
            int j = 1;
            int k = 1;
            TranslateAnimation translateanimation = new TranslateAnimation(1,
                    0.05F, i, -0.02F, j, -0.03F, k, 0.03F);
            translateanimation.setDuration(100L);
            animationset.addAnimation(translateanimation);
            //            _cls1 _lcls1 = new _cls1(view, listener);
            animationset.setAnimationListener(listener);
            view.startAnimation(animationset);
        }

        public void onAnimationRepeat(Animation animation)
        {
        }

        public void onAnimationStart(Animation animation)
        {
        }

        private final android.view.animation.Animation.AnimationListener listener;
        private final View view;

        _cls4(View view1,
                android.view.animation.Animation.AnimationListener animationlistener)
        {
            view = view1;
            listener = animationlistener;
        }
    }
    
    private static class _clsLeft3Close implements
    android.view.animation.Animation.AnimationListener
    {
        public void onAnimationEnd(Animation animation)
        {
            AnimationSet animationset = new AnimationSet(true);
            ScaleAnimation scaleanimation = new ScaleAnimation(0.9F, 0F, 1.1F,
                    0F);
            long l = 200;
            scaleanimation.setDuration(l);
            animationset.addAnimation(scaleanimation);
            int i = 1;
            int j = 1;
            int k = 1;
            TranslateAnimation translateanimation = new TranslateAnimation(1,
                    0F, i, 0.1F, j, -0.1F, k, 0F);
            
            long l1 = 200;
            translateanimation.setDuration(l1);
            animationset.addAnimation(translateanimation);
            android.view.animation.Animation.AnimationListener animationlistener = listener;
            animationset.setAnimationListener(animationlistener);
            view.startAnimation(animationset);
        }

        public void onAnimationRepeat(Animation animation)
        {
        }

        public void onAnimationStart(Animation animation)
        {
        }

        private final android.view.animation.Animation.AnimationListener listener;
        private final View view;

        _clsLeft3Close(View view1,
                android.view.animation.Animation.AnimationListener animationlistener)
        {
            listener = animationlistener;
            view = view1;

        }
    }
    
    private static class _clsLeft3 implements
    android.view.animation.Animation.AnimationListener
    {
        public void onAnimationEnd(Animation animation)
        {
            AnimationSet animationset = new AnimationSet(true);
            ScaleAnimation scaleanimation = new ScaleAnimation(0.95F, 1.02F,
                    1.03F, 0.97F);
            scaleanimation.setDuration(100L);
            animationset.addAnimation(scaleanimation);
            int i = 1;
            int j = 1;
            int k = 1;
            TranslateAnimation translateanimation = new TranslateAnimation(1,
                    0F, i, 0F, j, -0.03F, k, 0.03F);
            translateanimation.setDuration(100L);
            animationset.addAnimation(translateanimation);
            //            _cls1 _lcls1 = new _cls1(view, listener);
            animationset.setAnimationListener(listener);
            view.startAnimation(animationset);
        }

        public void onAnimationRepeat(Animation animation)
        {
        }

        public void onAnimationStart(Animation animation)
        {
        }

        private final android.view.animation.Animation.AnimationListener listener;
        private final View view;

        _clsLeft3(View view1,
                android.view.animation.Animation.AnimationListener animationlistener)
        {
            super();
            view = view1;
            listener = animationlistener;

        }
    }
    
    private static class _clsRight2Close implements
    android.view.animation.Animation.AnimationListener
    {
        public void onAnimationEnd(Animation animation)
        {
            AnimationSet animationset = new AnimationSet(true);
            ScaleAnimation scaleanimation = new ScaleAnimation(0.9F, 0F, 1.1F,
                    0F);
            long l = 200;
            scaleanimation.setDuration(l);
            animationset.addAnimation(scaleanimation);
            int i = 1;
            int j = 1;
            int k = 1;
            TranslateAnimation translateanimation = new TranslateAnimation(1,
                    0.1F, i, 0.9F, j, -0.1F, k, 0F);
            long l1 = 200;
            translateanimation.setDuration(l1);
            animationset.addAnimation(translateanimation);
            android.view.animation.Animation.AnimationListener animationlistener = listener;
            animationset.setAnimationListener(animationlistener);
            view.startAnimation(animationset);
        }

        public void onAnimationRepeat(Animation animation)
        {
        }

        public void onAnimationStart(Animation animation)
        {
        }

        private final android.view.animation.Animation.AnimationListener listener;
        private final View view;

        _clsRight2Close(View view1,
                android.view.animation.Animation.AnimationListener animationlistener)
        {
            super();
            listener = animationlistener;
            view = view1;
        }
    }

    private static class _clsRight2 implements
            android.view.animation.Animation.AnimationListener
    {

        public void onAnimationEnd(Animation animation)
        {
            AnimationSet animationset = new AnimationSet(true);
            ScaleAnimation scaleanimation = new ScaleAnimation(0.95F, 1.02F,
                    1.03F, 0.97F);
            scaleanimation.setDuration(100L);
            animationset.addAnimation(scaleanimation);
            int i = 1;
            int j = 1;
            int k = 1;
            TranslateAnimation translateanimation = new TranslateAnimation(1,
                    0.05F, i, -0.02F, j, -0.03F, k, 0.03F);
            translateanimation.setDuration(100L);
            animationset.addAnimation(translateanimation);
            //            _cls1 _lcls1 = new _cls1(view, listener);
            animationset.setAnimationListener(listener);
            view.startAnimation(animationset);
        }

        public void onAnimationRepeat(Animation animation)
        {
        }

        public void onAnimationStart(Animation animation)
        {
        }

        private final android.view.animation.Animation.AnimationListener listener;
        private final View view;

        _clsRight2(View view1,
                android.view.animation.Animation.AnimationListener animationlistener)
        {
            view = view1;
            listener = animationlistener;
        }
    }
}
