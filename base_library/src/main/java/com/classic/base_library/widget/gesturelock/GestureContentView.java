package com.classic.base_library.widget.gesturelock;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.classic.base_library.R;
import com.classic.base_library.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * 手势密码容器类
 *
 */
public class GestureContentView extends ViewGroup {

    private int baseNum = 6;

    private int[] screenDispaly;

    /**
     * 每个点区域的宽度
     */
    private int blockWidth;
    /**
     * 声明一个集合用来封装坐标集合
     */
    private List<GesturePoint> list;
    private Context context;
    private boolean isVerify;
    private GestureDrawline gestureDrawline;
    private int prDrawable;
    private int color;

    /**
     * 包含9个ImageView的容器，初始化
     * @param context
     * @param isVerify 是否为校验手势密码
     * @param passWord 用户传入密码
     * @param callBack 手势绘制完毕的回调
     */
    public GestureContentView(Context context, boolean isVerify, String passWord,
                              int drawable , int color , int errColor, GestureDrawline.GestureCallBack callBack) {
        super(context);
        this.prDrawable = drawable;
        screenDispaly = AppUtil.getScreenDispaly(context);
        blockWidth = (int) (screenDispaly[0]/3.5f);
        setBackgroundColor(Color.TRANSPARENT);
        this.color = color;
        this.list = new ArrayList<GesturePoint>();
        this.context = context;
        this.isVerify = isVerify;
        // 添加9个图标
        addChild();
        // 初始化一个可以画线的view
        gestureDrawline = new GestureDrawline(context, list, isVerify, passWord, callBack ,color,errColor);
    }

    private void addChild(){
        for (int i = 0; i < 9; i++) {
            ImageView image = new ImageView(context);
            image.setScaleType(ImageView.ScaleType.CENTER);
            image.setImageResource(R.drawable.lock_nomarl);
            this.addView(image);
            invalidate();
            // 第几行
            int row = i / 3;
            // 第几列
            int col = i % 3;
            // 定义点的每个属性
            int leftX = (int) (col*blockWidth+blockWidth/baseNum +blockWidth * 0.25f);
            int topY = row*blockWidth+blockWidth/baseNum;
            int rightX = (int) (col*blockWidth+blockWidth-blockWidth/baseNum+blockWidth * 0.25f);
            int bottomY = row*blockWidth+blockWidth-blockWidth/baseNum;
            GesturePoint p = new GesturePoint(leftX, rightX, topY, bottomY, image,i+1 , prDrawable);
            this.list.add(p);
        }
    }

    public void setParentView(ViewGroup parent){
        // 得到屏幕的宽度
        int width = screenDispaly[0];
        LayoutParams layoutParams = new LayoutParams(width, width);
        this.setLayoutParams(layoutParams);
        gestureDrawline.setLayoutParams(layoutParams);
        gestureDrawline.setBackgroundColor(Color.TRANSPARENT);
        parent.addView(gestureDrawline);
        parent.addView(this);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            //第几行
            int row = i/3;
            //第几列
            int col = i%3;
            View v = getChildAt(i);
            v.layout((int) (col*blockWidth+blockWidth/baseNum + blockWidth *0.25f), row*blockWidth+blockWidth/baseNum ,
                    (int) (col*blockWidth+blockWidth-blockWidth/baseNum + blockWidth * 0.25f), row*blockWidth+blockWidth-blockWidth/baseNum );
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 遍历设置每个子view的大小
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            v.measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * 保留路径delayTime时间长
     * @param delayTime
     */
    public void clearDrawlineState(long delayTime) {
        gestureDrawline.clearDrawlineState(delayTime);
    }

    public void setVerify(boolean falg) {
        gestureDrawline.isDrawEnable = falg;
    }

}
