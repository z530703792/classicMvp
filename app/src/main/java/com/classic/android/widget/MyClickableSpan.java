package com.classic.android.widget;

import android.app.Activity;
import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.classic.base_library.webview.HtmlAddress;
import com.classic.android.R;
import com.classic.android.webview.OpenWebViewUtils;

/**
 * @author: zcq 2019-08-27 17:31
 */

public class MyClickableSpan extends ClickableSpan {
    public final static String content_1 = "我已阅读并签署";
    public final static String content_2 = "《借款协议》";
    public final static String content_3 = "《CA证书授权书》";
    public final static String content_4 = "《风险提示书》";
    public final static String content_5 = "《Z智投授权委托书》";
    public final static String content_6 = "《债权转让及受让协议》";

    private String             content;
    private Context            context;

    public MyClickableSpan(Context context, String content) {
        this.context = context;
        this.content = content;
    }


    @Override
    public void updateDrawState(TextPaint ds) {
        if (content.equals(content_1))
            ds.setColor(context.getResources().getColor(R.color.color_FF636A71));
        else if (content.equals(content_2))
            ds.setColor(context.getResources().getColor(R.color.main_color));
        else if (content.equals(content_3))
            ds.setColor(context.getResources().getColor(R.color.main_color));
        else if (content.equals(content_4))
            ds.setColor(context.getResources().getColor(R.color.main_color));
        else if (content.equals(content_5))
            ds.setColor(context.getResources().getColor(R.color.main_color));
        else if (content.equals(content_6))
            ds.setColor(context.getResources().getColor(R.color.main_color));
    }

    @Override
    public void onClick(View widget) {
       if (content.equals(content_2)) {
           OpenWebViewUtils.toWebView("协议", HtmlAddress.xieyi, (Activity) context);
        } else if (content.equals(content_3)) {
           OpenWebViewUtils.toWebView("CA证书授权书", HtmlAddress.CAzssq, (Activity) context);
        } else if (content.equals(content_4)) {
           OpenWebViewUtils.toWebView("风险提示书", HtmlAddress.fxtsBook, (Activity) context);
        } else if (content.equals(content_5)) {
           OpenWebViewUtils.toWebView("授权委托书", HtmlAddress.bestPlanBook, (Activity) context);
        } else if (content.equals(content_6)) {
           OpenWebViewUtils.toWebView("转让及受让协议", HtmlAddress.rightsBook, (Activity) context);
        }
    }


    public static void setContent(Context context, TextView tv, String[] strings){
        for (String s : strings){
            SpannableString content = new SpannableString(s);
            ClickableSpan my_clickableSpan1 = new MyClickableSpan(context, s);
            content.setSpan(my_clickableSpan1, 0, content.length(),
                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            tv.append(content);
        }
        SpannableString content_ = new SpannableString(" ");
        ClickableSpan my_clickableSpan = new MyClickableSpan(context, "");
        content_.setSpan(my_clickableSpan, 0, content_.length(),
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv.append(content_);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
