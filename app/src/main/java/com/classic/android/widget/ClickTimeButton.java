package com.classic.android.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;

import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.classic.base_library.App.App;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 鉴于经常用到获取验证码倒计时按钮 在网上也没有找到理想的 自己写一个
 *
 * @author sj
 *         <p/>
 *         2015年1月14日[佛祖保佑 永无BUG]
 *         <p/>
 *         PS: 由于发现timer每次cancle()之后不能重新schedule方法,所以计时完毕只恐timer.
 *         每次开始计时的时候重新设置timer, 没想到好办法初次下策
 *         注意把该类的onCreate()onDestroy()和activity的onCreate()onDestroy()同步处理
 */
public class ClickTimeButton extends Button implements OnClickListener {
    private long lenght = 60 * 1000;// 倒计时长度,这里给了默认60秒
    private String textafter = "获取(";
    private CharSequence textbefore = "获取";
    private String TIME;
    private String CTIME;
    private OnClickListener mOnclickListener;
    private Timer t;
    private TimerTask tt;
    private long time;
    Map<String, Long> map = new HashMap<String, Long>();

    public ClickTimeButton(Context context) {
        super(context);
        setOnClickListener(this);
        setPadding(0,0,0,0);

    }

    public ClickTimeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
        setPadding(0,0,0,0);
    }

    @SuppressLint("HandlerLeak")
    Handler han = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public void handleMessage(android.os.Message msg) {
            ClickTimeButton.this.setText( time/1000 +"s后重发" );
            time -= 1000;
            if (time < 0) {
                ClickTimeButton.this.setEnabled(true);
                ClickTimeButton.this.setText(textbefore);
                clearTimer();
            }
        }
    };

    public void initTimer() {
        time = lenght;
        t = new Timer();
        tt = new TimerTask() {

            @Override
            public void run() {
                han.sendEmptyMessage(0x01);
            }
        };
    }

    private void clearTimer() {
        if (tt != null) {
            tt.cancel();
            tt = null;
        }
        if (t != null)
            t.cancel();
        t = null;
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        if (l instanceof ClickTimeButton) {
            super.setOnClickListener(l);
        } else
            this.mOnclickListener = l;
    }

    @Override
    public void onClick(View v) {
        if (mOnclickListener != null)
            mOnclickListener.onClick(v);

        // t.scheduleAtFixedRate(task, delay, period);
    }


    public void startCountDoun() {
        initTimer();
        this.setText(time/1000 + "s后重发");
        this.setEnabled(false);
        t.schedule(tt, 0, 1000);
    }

    /**
     * 和activity的onDestroy()方法同步
     */
    public void onDestroy() {
        if (App.map == null)
            App.map = new HashMap<String, Long>();
        App.map.put(TIME, time);
        App.map.put(CTIME, System.currentTimeMillis());
        clearTimer();
    }

    /**
     * 和activity的onCreate()方法同步
     */

    public void onCreate(String type) {
        this.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG);
        TIME = type;
        CTIME = "C" + type;
        //Log.e("timec", "onCreate: " + CTIME);
        if (startAndAContinue()) {
            long time = System.currentTimeMillis() - App.map.get(CTIME)
                    - App.map.get(TIME);
            //MyAppliction.map.clear();
            initTimer();
            this.time = Math.abs(time);
            t.schedule(tt, 0, 1000);
            this.setText(time/1000 + "s后重发");
            this.setEnabled(false);
        } else {

        }
    }

    /**
     * 设置计时时候显示的文本
     */
    public ClickTimeButton setTextAfter(String text1) {
        this.textafter = text1;
        return this;
    }

    /**
     * 设置点击之前的文本
     */
    public ClickTimeButton setTextBefore(CharSequence text0) {
        this.textbefore = text0;
        this.setText(textbefore);
        return this;
    }

    /**
     * 设置到计时长度
     *
     * @param lenght 时间 默认毫秒
     * @return
     */
    public ClickTimeButton setLenght(long lenght) {
        this.lenght = lenght;
        return this;
    }

    /**
     * 判断是否从新开始
     */
    public boolean startAndAContinue() {
        if (App.map == null) {
            return false;
        }
        if (App.map.size() <= 0) {// 这里表示没有上次未完成的计时
            return false;
        }
        Long aLong = App.map.get(CTIME);
        if (aLong == null) {
            return false;
        }
        Long aLong1 = App.map.get(TIME);
        if (aLong1 == null) {
            return false;
        }
        long time = System.currentTimeMillis() - aLong
                - aLong1;
        if (time > 0) {
            return false;
        }
        return true;
    }
}