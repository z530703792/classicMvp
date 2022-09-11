package com.classic.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.classic.android.R;
public class MyItemView1 extends RelativeLayout {
    private static final String TAG = "MyItemView1";
    private ImageView itemIcon;
    private TextView itemTitle,itemInfo;
    private int iconRes;
    private String itemTitles;
    private String itemInfos;

    public MyItemView1(Context context) {
        this(context, (AttributeSet) null);
    }

    public MyItemView1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyItemView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setClickable(true);
        this.init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_my_item, this, false);
        itemIcon = view.findViewById(R.id.img_left);
        itemTitle = view.findViewById(R.id.text_middle);
        itemInfo = view.findViewById(R.id.text_right);
        addView(view);
        //1. 获取配置的属性值
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyItemView1, defStyleAttr, 0);

        try {
            int indexCount = typedArray.getIndexCount();
            //2.开始遍历，并用变量存储用户配置的数据，包括菜单布局的id等
            for (int i = 0; i < indexCount; ++i) {
                int attr = typedArray.getIndex(i);
                if (attr == R.styleable.MyItemView1_itemIcon) {
                    iconRes = typedArray.getResourceId(R.styleable.MyItemView1_itemIcon, -1);
                } else if (attr == R.styleable.MyItemView1_itemTitle) {
                    itemTitles = typedArray.getString(R.styleable.MyItemView1_itemTitle);
                } else if (attr == R.styleable.MyItemView1_itemInfo) {
                    itemInfos = typedArray.getString(R.styleable.MyItemView1_itemInfo);
                }
            }
            itemIcon.setImageResource(iconRes);
            itemTitle.setText(itemTitles);
            itemInfo.setText(itemInfos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            typedArray.recycle();
        }
    }

    public void setItemIcon(int res){
        itemIcon.setImageResource(res);
    }

    public void setItemTitle(String title){
        itemTitle.setText(title);
    }

    public void setItemIcon(String info){
        itemInfo.setText(info);
    }

}
