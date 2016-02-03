package com.mendong.travel.ui;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.mendong.travel.R;

public class CircleView extends View {

    private final static float NI_BASIC_NUM = 10;
    private final static int NI_TOUCH_SIZE = 2;
    private final static int NI_PAINT_TEXT_SIZE = 48;
    private Context context;
    private Bitmap bmpBg;
    private Bitmap bmpCircle;
    private Bitmap[] arrBmpCircle;
    private CircleRect[] arrCircleRect;
    private int screenW;
    private int screenH;
    private String[] ARR_VIEW_NAME;
    private int niCircleCount;
    private float m_fScale;
    private Rect rectTouch;
    private Paint paint;
    private int getRandomLeft;
    private int getRandomTop;

    public CircleView(Context context, int screenW, int screenH, String[] ARR_VIEW_NAME,
            int niCircleCount) {
        super(context);
        this.context = context;
        this.screenW = screenW;
        this.screenH = screenH;
        this.ARR_VIEW_NAME = ARR_VIEW_NAME;
        this.niCircleCount = niCircleCount;
        bmpBg = BitmapFactory.decodeResource(this.getResources(), R.drawable.circle_bg);
        bmpCircle = BitmapFactory.decodeResource(this.getResources(), R.drawable.circle);
        arrCircleRect = new CircleRect[niCircleCount];
        arrBmpCircle = new Bitmap[niCircleCount];
        for (int i = 0; i < arrCircleRect.length; i++) {
            m_fScale = getRandomInt(8, 10) / NI_BASIC_NUM;

            arrBmpCircle[i] = getScaleBitmap(bmpCircle, m_fScale);

            getRandomLeft = getRandomPosition(i, arrBmpCircle[i]).x;
            getRandomTop = getRandomPosition(i, arrBmpCircle[i]).y;

            arrCircleRect[i] = new CircleRect(getRandomLeft, getRandomTop, getRandomLeft
                    + arrBmpCircle[i].getWidth(), getRandomTop + arrBmpCircle[i].getHeight());

            arrCircleRect[i].setDirection(getRandomInt(0, 7));
            arrCircleRect[i].setSpeed(getRandomInt(1, 2));
            arrCircleRect[i].setText(ARR_VIEW_NAME[i]);
        }

        setPaint();

    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bmpBg, 0, 0, null);
        for (int i = 0; i < arrCircleRect.length; i++) {
            canvas.drawBitmap(arrBmpCircle[i], arrCircleRect[i].getLeft(),
                    arrCircleRect[i].getTop(), null);

            canvas.drawText(
                    arrCircleRect[i].getText(),
                    (arrCircleRect[i].getLeft() + arrCircleRect[i].getRight()) / 2
                            - paint.measureText(arrCircleRect[i].getText()) / 2,
                    (arrCircleRect[i].getTop() + arrCircleRect[i].getBottom()) / 2
                            + NI_PAINT_TEXT_SIZE / 2, paint);
        }
        this.postInvalidate();
    }

    /**
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    private boolean isCollsion(Rect rectF, Rect rectS) {

        int x1, y1, x2, y2, r1, r2;
        x1 = (rectF.left + rectF.right) / 2;
        y1 = (rectF.top + rectF.bottom) / 2;
        x2 = (rectS.left + rectS.right) / 2;
        y2 = (rectS.top + rectS.bottom) / 2;
        r1 = (rectF.right - rectF.left) / 2;
        r2 = (rectS.right - rectS.left) / 2;
        if (Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) <= r1 + r2) {
            return true;
        }

        return false;

    }

    public String onTouch(MotionEvent event) {
        setTouchRect(event);
        for (int i = 0; i < arrCircleRect.length; i++) {
            if (rectTouch.intersect(arrCircleRect[i].getInstance())) {
                return arrCircleRect[i].getText();
            }
        }

        return null;
    }

    public void logic() {
        for (int i = 0; i < arrCircleRect.length; i++) {
            Rect rect1 = new Rect();
            for (int j = 0; j < arrCircleRect.length; j++) {
                if (!arrCircleRect[i].getText().equals(arrCircleRect[j].getText())) {
                    Rect rect2 = new Rect();
                    if (isCollsion(getNewRect(rect1, arrCircleRect[i]),
                            getNewRect(rect2, arrCircleRect[j]))) {
                        arrCircleRect[i].resetIntersectDirection(arrCircleRect[i].getDirection());
                        arrCircleRect[j].resetIntersectDirection(arrCircleRect[j].getDirection());
                    }
                }
            }
        }

        for (int k = 0; k < arrCircleRect.length; k++) {
            arrCircleRect[k].move();
        }
    }

    public Rect getNewRect(Rect rect, CircleRect rectOri) {
        switch (rectOri.getDirection()) {
        case CircleInfo.NI_DIRECTION_UP:
            rect.left = rectOri.getLeft();
            rect.top = rectOri.getTop() - rectOri.getSpeed();
            rect.right = rectOri.getRight();
            rect.bottom = rectOri.getBottom() - rectOri.getSpeed();
            break;
        case CircleInfo.NI_DIRECTION_DOWN:
            rect.left = rectOri.getLeft();
            rect.top = rectOri.getTop() + rectOri.getSpeed();
            rect.right = rectOri.getRight();
            rect.bottom = rectOri.getBottom() + rectOri.getSpeed();
            break;
        case CircleInfo.NI_DIRECTION_LEFT:
            rect.left = rectOri.getLeft() - rectOri.getSpeed();
            rect.top = rectOri.getTop();
            rect.right = rectOri.getRight() - rectOri.getSpeed();
            rect.bottom = rectOri.getBottom();
            break;
        case CircleInfo.NI_DIRECTION_RIGHT:
            rect.left = rectOri.getLeft() + rectOri.getSpeed();
            rect.top = rectOri.getTop();
            rect.right = rectOri.getRight() + rectOri.getSpeed();
            rect.bottom = rectOri.getBottom();
            break;
        case CircleInfo.NI_DIRECTION_LU:
            rect.left = rectOri.getLeft() - rectOri.getSpeed();
            rect.top = rectOri.getTop() - rectOri.getSpeed();
            rect.right = rectOri.getRight() - rectOri.getSpeed();
            rect.bottom = rectOri.getBottom() - rectOri.getSpeed();
            break;
        case CircleInfo.NI_DIRECTION_RU:
            rect.left = rectOri.getLeft() + rectOri.getSpeed();
            rect.top = rectOri.getTop() - rectOri.getSpeed();
            rect.right = rectOri.getRight() + rectOri.getSpeed();
            rect.bottom = rectOri.getBottom() - rectOri.getSpeed();
            break;
        case CircleInfo.NI_DIRECTION_LD:
            rect.left = rectOri.getLeft() - rectOri.getSpeed();
            rect.top = rectOri.getTop() + rectOri.getSpeed();
            rect.right = rectOri.getRight() - rectOri.getSpeed();
            rect.bottom = rectOri.getBottom() + rectOri.getSpeed();
            break;
        case CircleInfo.NI_DIRECTION_RD:
            rect.left = rectOri.getLeft() + rectOri.getSpeed();
            rect.top = rectOri.getTop() + rectOri.getSpeed();
            rect.right = rectOri.getRight() + rectOri.getSpeed();
            rect.bottom = rectOri.getBottom() + rectOri.getSpeed();
            break;
        }

        return rect;
    }

    private void setTouchRect(MotionEvent event) {

        if (rectTouch == null)
            rectTouch = new Rect();
        rectTouch.left = (int) (event.getX() - NI_TOUCH_SIZE);
        rectTouch.right = rectTouch.left + 2 * NI_TOUCH_SIZE;
        rectTouch.top = (int) (event.getY() - NI_TOUCH_SIZE);
        rectTouch.bottom = rectTouch.top + 2 * NI_TOUCH_SIZE;
    }

    private void setPaint() {
        if (paint == null)
            paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(NI_PAINT_TEXT_SIZE);
        paint.setFakeBoldText(true);
    }

    private Bitmap getScaleBitmap(Bitmap bitmap, float scale) {
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
                matrix, true);
        return newBitmap;
    }

    /**
	 */
    public Point getRandomPosition(int niNum, Bitmap bitmap) {
        Point point = new Point();
        int niCircleBasic = niCircleCount / 2;

        switch (niNum) {
        case 0:
            point.x = getRandomInt(0, screenW / 2 - bitmap.getWidth());
            point.y = getRandomInt(0, screenH / niCircleBasic - bitmap.getHeight());
            break;
        case 1:
            point.x = getRandomInt(screenW / 2, screenW - bitmap.getWidth());
            point.y = getRandomInt(0, screenH / niCircleBasic - bitmap.getHeight());
            break;
        case 2:
            point.x = getRandomInt(0, screenW / 2 - bitmap.getWidth());
            point.y = getRandomInt(screenH / niCircleBasic,
                    screenH / niCircleBasic * 2 - bitmap.getHeight());
            break;
        case 3:
            point.x = getRandomInt(screenW / 2, screenW - bitmap.getWidth());
            point.y = getRandomInt(screenH / niCircleBasic,
                    screenH / niCircleBasic * 2 - bitmap.getHeight());
            break;
        case 4:
            point.x = getRandomInt(0, screenW / 2 - bitmap.getWidth());
            point.y = getRandomInt(screenH / niCircleBasic * 2, screenH / niCircleBasic * 3
                    - bitmap.getHeight());
            break;
        case 5:
            point.x = getRandomInt(screenW / 2, screenW - bitmap.getWidth());
            point.y = getRandomInt(screenH / niCircleBasic * 2, screenH / niCircleBasic * 3
                    - bitmap.getHeight());
            break;
        case 6:
            point.x = getRandomInt(0, screenW / 2 - bitmap.getWidth());
            point.y = getRandomInt(screenH / niCircleBasic * 3, screenH - bitmap.getHeight());
            break;
        case 7:
            point.x = getRandomInt(screenW / 2, screenW - bitmap.getWidth());
            point.y = getRandomInt(screenH / niCircleBasic * 3, screenH - bitmap.getHeight());
            break;
        }

        return point;
    }

    private int getRandomInt(int min, int max) {
        return Math.abs(new Random().nextInt()) % (max - min) + min;
    }

}
