/**
 * 项目名：     Travel
 * 文件名：     ImageControl.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月16日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package org.jump.utils.view;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * 类名称：     ImageControl
 * 作者：         Administrator
 * 创建时间：  2013年7月16日 下午10:31:46
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月16日 下午10:31:46
 *
 */
public class ImageControl extends ImageView
{
    public ImageControl(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub  
    }

    public ImageControl(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        // TODO Auto-generated constructor stub  
    }

    public ImageControl(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub  
    }

    // ImageView img;  
    Matrix imgMatrix = null; // 定义图片的变换矩阵  

    static final int DOUBLE_CLICK_TIME_SPACE = 300; // 双击时间间隔  
    static final int DOUBLE_POINT_DISTANCE = 10; // 两点放大两点间最小间距  
    static final int NONE = 0;
    static final int DRAG = 1; // 拖动操作  
    static final int ZOOM = 2; // 放大缩小操作  
    private int mode = NONE; // 当前模式  

    float bigScale = 3f; // 默认放大倍数  
    Boolean isBig = false; // 是否是放大状态  
    long lastClickTime = 0; // 单击时间  
    float startDistance; // 多点触摸两点距离  
    float endDistance; // 多点触摸两点距离  

    float topHeight; // 状态栏高度和标题栏高度  
    Bitmap primaryBitmap = null;

    float contentW; // 屏幕内容区宽度  
    float contentH; // 屏幕内容区高度  

    float primaryW; // 原图宽度  
    float primaryH; // 原图高度  

    float scale; // 适合屏幕缩放倍数  
    Boolean isMoveX = true; // 是否允许在X轴拖动  
    Boolean isMoveY = true; // 是否允许在Y轴拖动  
    float startX;
    float startY;
    float endX;
    float endY;
    float subX;
    float subY;
    float limitX1;
    float limitX2;
    float limitY1;
    float limitY2;
    ICustomMethod mCustomMethod = null;
    
    private float screenPX;   //选中的图片坐标
    
    private float screenPY;   //选中的图片坐标
    
    private String[] area;
    
    private int areaOffset;
    
    private int areaLen;
    
//    private float totalTransX;
//    
//    private float totalTransY;
    
//    private float fillScreenPX;
//    
//    private float fillScreenPY;

    /** 
     * 初始化图片 
     *  
     * @param bitmap 
     *            要显示的图片 
     * @param contentW 
     *            内容区域宽度 
     * @param contentH 
     *            内容区域高度 
     * @param topHeight 
     *            状态栏高度和标题栏高度之和 
     */
    public void imageInit(Bitmap bitmap, int contentW, int contentH,
            int topHeight, ICustomMethod iCustomMethod)
    {
        this.primaryBitmap = bitmap;
        this.contentW = contentW;
        this.contentH = contentH;
        this.topHeight = topHeight;
        mCustomMethod = iCustomMethod;
        primaryW = primaryBitmap.getWidth();
        primaryH = primaryBitmap.getHeight();
        float scaleX = (float) contentW / primaryW;
        float scaleY = (float) contentH / primaryH;
        scale = scaleX < scaleY ? scaleX : scaleY;
        if (scale < 1 && 1 / scale < bigScale)
        {
            bigScale = (float) (1 / scale + 0.5);
        }

        imgMatrix = new Matrix();
        subX = (contentW - primaryW * scale) / 2;
        subY = (contentH - primaryH * scale) / 2;
        this.setImageBitmap(primaryBitmap);
        this.setScaleType(ScaleType.MATRIX);
        imgMatrix.postScale(scale, scale);
        imgMatrix.postTranslate(subX, subY);
        this.setImageMatrix(imgMatrix);
    }

    /** 
     * 初始化图片 
     *  
     * @param bitmap 
     *            要显示的图片 
     * @param contentW 
     *            内容区域宽度 
     * @param contentH 
     *            内容区域高度 
     * @param topHeight 
     *            状态栏高度和标题栏高度之和 
     */
    public void imageInit(Bitmap bitmap, int contentW, int contentH,
            int topHeight, ICustomMethod iCustomMethod, int destHeight)
    {
        this.primaryBitmap = bitmap;
        this.contentW = contentW;
        this.contentH = contentH;
        this.topHeight = topHeight;
        mCustomMethod = iCustomMethod;
        primaryW = primaryBitmap.getWidth();
        primaryH = primaryBitmap.getHeight();
        
        scale = (float) destHeight / primaryH;

        if (scale < 1 && 1 / scale < bigScale)
        {
            bigScale = (float) (1 / scale + 0.5);
        }

        imgMatrix = new Matrix();
        subX = (contentW - primaryW * scale) / 2;
        subY = (contentH - primaryH * scale) / 2;
        this.setImageBitmap(primaryBitmap);
        this.setScaleType(ScaleType.MATRIX);
        imgMatrix.postScale(scale, scale);
        imgMatrix.postTranslate(subX, subY);
        this.setImageMatrix(imgMatrix);
        
//        totalTransX = subX;
//        totalTransY = subY;
        float currentWidth = primaryW * scale; // 放大后图片大小  
        float currentHeight = primaryH * scale;
        //提到一个方法中
        calcuMoveValue(scale, currentWidth, currentHeight, subX, subY);
    }
    
    
    /**
     * 方法名称：  getImageX
     * 作者：         Administrator
     * 方法描述：  获取相对于图片的坐标
     * 输入参数：  @return
     * 返回类型：  float
     */
    public float getImageX()
    {
        return (screenPX - getTranslateXY(imgMatrix)[0]) / (scale * (isBig ? bigScale : 1));
    }
    
    /**
     * 方法名称：  getImageY
     * 作者：         Administrator
     * 方法描述：  获取相对于图片的坐标
     * 输入参数：  @return
     * 返回类型：  float
     */
    public float getImageY()
    {
        return (screenPY - getTranslateXY(imgMatrix)[1]) / (scale * (isBig ? bigScale : 1));
    }
    
    /**
     * 方法名称：  getScreenPX
     * 作者：         Administrator
     * 方法描述：  获取相对于屏幕的坐标
     * 输入参数：  @return
     * 返回类型：  float
     */
    public float getScreenPX()
    {
        return screenPX;
    }
    
    /**
     * 方法名称：  getScreenPY
     * 作者：         Administrator
     * 方法描述：  获取相对于屏幕的坐标
     * 输入参数：  @return
     * 返回类型：  float
     */
    public float getScreenPY()
    {
        return screenPY;
    }

    /**
     * 方法名称：  real2ScreenX
     * 作者：         Administrator
     * 方法描述：  图片坐标转屏幕坐标
     * 输入参数：  @param value
     * 输入参数：  @return
     * 返回类型：  float
     */
    public float real2ScreenX(float value)
    {
        return value * (scale * (isBig ? bigScale : 1)) + getTranslateXY(imgMatrix)[0];
    }
    
    /**
     * 方法名称：  real2ScreenY
     * 作者：         Administrator
     * 方法描述：  图片坐标转屏幕坐标
     * 输入参数：  @param value
     * 输入参数：  @return
     * 返回类型：  float
     */
    public float real2ScreenY(float value)
    {
        return value * (scale * (isBig ? bigScale : 1)) + getTranslateXY(imgMatrix)[1];
    }

    /**
     * 方法名称：  getArea
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  String[]
     * 返回字段：  area
     * 备注：
     */
    public String[] getArea()
    {
        return area;
    }

    /**
     * 方法名称： setArea
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  String[]
     * 设置字段：  设置 area 给 area
     * 备注：
     */
    public void setArea(String[] area)
    {
        this.area = area;
    }

    /**
     * 方法名称：  getAreaOffset
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  int
     * 返回字段：  areaOffset
     * 备注：
     */
    public int getAreaOffset()
    {
        return areaOffset;
    }

    /**
     * 方法名称： setAreaOffset
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  int
     * 设置字段：  设置 areaOffset 给 areaOffset
     * 备注：
     */
    public void setAreaOffset(int areaOffset)
    {
        this.areaOffset = areaOffset;
    }

    /**
     * 方法名称：  getAreaLen
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  int
     * 返回字段：  areaLen
     * 备注：
     */
    public int getAreaLen()
    {
        return areaLen;
    }

    /**
     * 方法名称： setAreaLen
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  int
     * 设置字段：  设置 areaLen 给 areaLen
     * 备注：
     */
    public void setAreaLen(int areaLen)
    {
        this.areaLen = areaLen;
    }

    /** 
     * 按下操作 
     *  
     * @param event 
     */
    public void mouseDown(MotionEvent event)
    {
        mode = NONE;

        startX = event.getX();
        startY = event.getY();
        if (event.getPointerCount() == 1)
        {
            // 如果两次点击时间间隔小于一定值，则默认为双击事件  
            if (event.getEventTime() - lastClickTime < DOUBLE_CLICK_TIME_SPACE)
            {
                changeSize(startX, startY);
            }
            else //if (isBig)
            {
                mode = DRAG;
            }
        }

        lastClickTime = event.getEventTime();
        //this.postInvalidate();
    }

    /** 
     * 非第一个点按下操作 
     *  
     * @param event 
     */
    public void mousePointDown(MotionEvent event)
    {
        
        startDistance = getDistance(event);
        if (startDistance > DOUBLE_POINT_DISTANCE)
        {
            mode = ZOOM;
        }
        else
        {
            mode = NONE;
        }
    }

    /** 
     * 移动操作 
     *  
     * @param event 
     */
    public void mouseMove(MotionEvent event)
    {
        if ((mode == DRAG) && (isMoveX || isMoveY))
        {
            float[] XY = getTranslateXY(imgMatrix);
            float transX = 0;
            float transY = 0;
            if (isMoveX)
            {
                endX = event.getX();
                transX = endX - startX;
                if ((XY[0] + transX) <= limitX1)
                {
                    transX = limitX1 - XY[0];
                }
                if ((XY[0] + transX) >= limitX2)
                {
                    transX = limitX2 - XY[0];
                }
            }
            if (isMoveY)
            {
                endY = event.getY();
                transY = endY - startY;
                if ((XY[1] + transY) <= limitY1)
                {
                    transY = limitY1 - XY[1];
                }
                if ((XY[1] + transY) >= limitY2)
                {
                    transY = limitY2 - XY[1];
                }
            }

            imgMatrix.postTranslate(transX, transY);
            startX = endX;
            startY = endY;
            this.setImageMatrix(imgMatrix);
            
//            totalTransX = XY[0];
//            totalTransY = XY[1];
            System.out.println("transX = " + XY[0] + ", transY = " + XY[1]);
        }
        else if (mode == ZOOM && event.getPointerCount() > 1)
        {
            endDistance = getDistance(event);
            float dif = endDistance - startDistance;
            if (Math.abs(endDistance - startDistance) > DOUBLE_POINT_DISTANCE)
            {
                if (isBig)
                {
                    if (dif < 0)
                    {
                        changeSize(0, 0);
                        mode = NONE;
                    }
                }
                else if (dif > 0)
                {
                    float x = event.getX(0) / 2 + event.getX(1) / 2;
                    float y = event.getY(0) / 2 + event.getY(1) / 2;
                    changeSize(x, y);
                    mode = NONE;
                }
            }
        }
    }

    /** 
     * 鼠标抬起事件 
     */
    public void mouseUp(MotionEvent event)
    {
        mode = NONE;
        screenPX = event.getX();
        screenPY = event.getY();
        
    }

    private void calcuMoveValue(float bigScale, float currentWidth,
            float currentHeight, float transX, float transY)
    {
        // 如果图片放大后超出屏幕范围处理  
        if (currentHeight > contentH)
        {
            limitY1 = -(currentHeight - contentH); // 平移限制  
            limitY2 = 0;
            isMoveY = true; // 允许在Y轴上拖动  
            float currentSubY = bigScale * subY; // 当前平移距离  
            // 平移后，内容区域上部有空白处理办法  
            if (-transY < currentSubY)
            {
                transY = -currentSubY;
            }
            // 平移后，内容区域下部有空白处理办法  
            if (currentSubY + transY < limitY1)
            {
                transY = -(currentHeight + currentSubY - contentH);
            }
            mode = DRAG;
        }
        else
        {
            // 如果图片放大后没有超出屏幕范围处理，则不允许拖动  
            isMoveY = false;
        }

        if (currentWidth > contentW)
        {
            limitX1 = -(currentWidth - contentW);
            limitX2 = 0;
            isMoveX = true;
            float currentSubX = bigScale * subX;
            if (-transX < currentSubX)
            {
                transX = -currentSubX;
            }
            if (currentSubX + transX < limitX1)
            {
                transX = -(currentWidth + currentSubX - contentW);
            }
            mode = DRAG;
        }
        else
        {
            isMoveX = false;
        }
    }

    /** 
     * 图片放大缩小 
     *  
     * @param x 
     *            点击点X坐标 
     * @param y 
     *            点击点Y坐标 
     */
    private void changeSize(float x, float y)
    {
        if (isBig)
        {
            // 如果处于最大状态，则还原  
            imgMatrix.reset();
            imgMatrix.postScale(scale, scale);
            imgMatrix.postTranslate(subX, subY);
            isBig = false;
            
            float currentWidth = primaryW * scale; // 放大后图片大小  
            float currentHeight = primaryH * scale;
            //提到一个方法中
            calcuMoveValue(scale, currentWidth, currentHeight, x, y);
        }
        else
        {
            imgMatrix.postScale(bigScale, bigScale); // 在原有矩阵后乘放大倍数  
            float currentWidth = primaryW * scale * bigScale; // 放大后图片大小  
            float currentHeight = primaryH * scale * bigScale;
            float transX = -((bigScale - 1) * x);
            float transY = -((bigScale - 1) * (y - topHeight)); // (bigScale-1)(y-statusBarHeight-subY)+2*subY;  

//            totalTransX = transX;
//            totalTransY = transY;
            
            //提到一个方法中
            calcuMoveValue(bigScale, currentWidth, currentHeight, x, y);

            //            // 如果图片放大后超出屏幕范围处理  
            //            if (currentHeight > contentH)
            //            {
            //                limitY1 = -(currentHeight - contentH); // 平移限制  
            //                limitY2 = 0;
            //                isMoveY = true; // 允许在Y轴上拖动  
            //                float currentSubY = bigScale * subY; // 当前平移距离  
            //                // 平移后，内容区域上部有空白处理办法  
            //                if (-transY < currentSubY)
            //                {
            //                    transY = -currentSubY;
            //                }
            //                // 平移后，内容区域下部有空白处理办法  
            //                if (currentSubY + transY < limitY1)
            //                {
            //                    transY = -(currentHeight + currentSubY - contentH);
            //                }
            //            }
            //            else
            //            {
            //                // 如果图片放大后没有超出屏幕范围处理，则不允许拖动  
            //                isMoveY = false;
            //            }
            //
            //            if (currentWidth > contentW)
            //            {
            //                limitX1 = -(currentWidth - contentW);
            //                limitX2 = 0;
            //                isMoveX = true;
            //                float currentSubX = bigScale * subX;
            //                if (-transX < currentSubX)
            //                {
            //                    transX = -currentSubX;
            //                }
            //                if (currentSubX + transX < limitX1)
            //                {
            //                    transX = -(currentWidth + currentSubX - contentW);
            //                }
            //            }
            //            else
            //            {
            //                isMoveX = false;
            //            }

            imgMatrix.postTranslate(transX, transY);
            isBig = true;
        }

        this.setImageMatrix(imgMatrix);
        if (mCustomMethod != null)
        {
            mCustomMethod.customMethod(isBig);
        }
    }

    /** 
     * 获取变换矩阵中X轴偏移量和Y轴偏移量 
     *  
     * @param matrix 
     *            变换矩阵 
     * @return 
     */
    private float[] getTranslateXY(Matrix matrix)
    {
        float[] values = new float[9];
        matrix.getValues(values);
        float[] floats = new float[2];
        floats[0] = values[Matrix.MTRANS_X];
        floats[1] = values[Matrix.MTRANS_Y];
        return floats;
    }

    /** 
     * 获取两点间的距离 
     *  
     * @param event 
     * @return 
     */
    private float getDistance(MotionEvent event)
    {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return FloatMath.sqrt(x * x + y * y);
    }

    /* (non-Javadoc)
     * (覆盖方法)
     * 方法名称：  onDraw
     * 作者：         Administrator
     * 方法描述：  	
     * @see android.widget.ImageView#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas)
    {
        // TODO Auto-generated method stub
        
        super.onDraw(canvas);
        if(null != imgMatrix)
        {
            fillShape(canvas, area, areaOffset, areaLen);
        }

        //fillDownArea(canvas);
    }
    
    private void fillDownArea(Canvas canvas)
    {
        Paint paint = new Paint();
        Path path = new Path();
        path.moveTo(screenPX, screenPY);
        path.lineTo(screenPX + 10, screenPY);
        path.lineTo(screenPX + 10, screenPY + 10);
        path.lineTo(screenPX, screenPY + 10);
        path.setFillType(FillType.EVEN_ODD);
        path.close();
        paint.setColor(Color.BLUE);
        canvas.drawPath(path, paint);
    }

    private synchronized void fillShape(Canvas canvas, String[] area, int offset, int len)
    {
        if(null == area || area.length <= 0 || area.length % 2 != 0)
        {
            return;
        }
        if(offset < 0)
        {
            return;
        }

        Paint paint = new Paint();
        Path path = new Path();
        float x = real2ScreenX(1600);
        float y = real2ScreenY(400);
        System.out.println("x == " + x + ", y == " + y);
        path.moveTo(real2ScreenX(Integer.valueOf(area[offset])), real2ScreenY(Integer.valueOf(area[offset + 1])));
        
      for(int i = offset + 2; i < offset + len; i+=2)
      {
          
          path.lineTo(real2ScreenX(Integer.valueOf(area[i])), real2ScreenY(Integer.valueOf(area[i+1])));
      }
        
//        path.lineTo(real2ScreenX(2000), real2ScreenY(400));
//        path.lineTo(real2ScreenX(2000), real2ScreenY(600));
//        path.lineTo(real2ScreenX(1600), real2ScreenY(600));
        path.setFillType(FillType.EVEN_ODD);
        path.close();
        paint.setColor(Color.RED);
//        paint.setStyle(Style.FILL);
        paint.setAlpha(50);
        canvas.drawPath(path, paint);
        
    }
    
    public synchronized void clearFillShape()
    {
        area = null;
        areaOffset = 0;
        areaLen = 0;
        this.postInvalidate();
    }

//    public void fillPolygon(Canvas canvas, int[] x, int[] y, int offset, int len) {
//        if (x == null || y == null) {
//            return;
//        }
//        Path path = new Path();
//        path.moveTo(x[offset], y[offset]);
//        for (int i = offset + 1; i < offset + len; i++) {
//            path.lineTo(x, y);
//        }
//        // path.setFillType(FillType.EVEN_ODD);
//        canvas.drawPath(path, paint);
//    }

//    public void fillPolygon(int[] x, int[] y) {
//        fillPolygon(x, y, 0, x.length);
//    }
    
    boolean isFillShape = false;
    
    public void setFillShape(boolean flag)
    {
        isFillShape = flag;
    }
    
    public void release()
    {
        if(null != imgMatrix)
        {
            imgMatrix.reset();
            imgMatrix = null;
        }
        
        if(null != primaryBitmap && !primaryBitmap.isRecycled())
        {
            primaryBitmap.recycle();
            primaryBitmap = null;
        }
        mCustomMethod = null;
        this.setImageBitmap(null);
    }
    
    /** 
     * @author Administrator 用户自定义方法 
     */
    public interface ICustomMethod
    {
        public void customMethod(Boolean currentStatus);
    }
}
