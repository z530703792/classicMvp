package com.classic.android.widget;

/**
 * @author: zcq  2019-08-28 16:34
 */

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.classic.android.R;


public class ShareDialog {
    private Dialog mDialog;
    //取消分享
    private TextView txtCancle;
    private OnClickListener mOnCancleListener;
    //微信分享
    private TextView mWeChatShare;
    private OnClickListener mOnWeChatShareListener;
    //微信朋友圈分享
    private TextView mWeChatFriendShare;
    private OnClickListener mOnWeChatFriendShareListener;
    //QQ分享
    private TextView mQQShare;
    private OnClickListener mOnQQShareListener;
    //QQ空间分享
    private TextView mQQkjShare;
    private OnClickListener mOnQQkjShareListener;
    //二维码
    private TextView mQRcodeShare;
    private OnClickListener mOnQRcodeListener;
    //复制链接
    private TextView mCopylinkShare;
    private OnClickListener mCopylinkShareListener;

    private TextView tvMyInvitationCode;


    private Context mContext;
    private Display display;

    public ShareDialog(Context context){
        mContext=context;
        //获取屏幕对象
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public ShareDialog setMyInvitationCode(String code){
        tvMyInvitationCode.setText(code);
        return this;
    }

    //设置微信分享
    public ShareDialog setOnWeChatShare(OnClickListener weChatShareListener){
        mOnWeChatShareListener=weChatShareListener;
        return this;
    }

    //设置分享朋友圈
    public ShareDialog setOnWeChatFriendShare(OnClickListener weChatFriendShareListener){
        mOnWeChatFriendShareListener=weChatFriendShareListener;
        return this;
    }

    //QQ分享
    public ShareDialog setOnQQShare(OnClickListener qqShareListener){
        mOnQQShareListener=qqShareListener;
        return this;
    }

    //QQ空间分享
    public ShareDialog setOnQQkjShare(OnClickListener qqkjShareListener){
        mOnQQkjShareListener=qqkjShareListener;
        return this;
    }

    //二维码
    public ShareDialog setOnQRcodeShare(OnClickListener qrcodeShareListener){
        mOnQRcodeListener=qrcodeShareListener;
        return this;
    }

    //复制链接
    public ShareDialog setOnCopylinkShare(OnClickListener copylinkShareListener){
        mCopylinkShareListener=copylinkShareListener;
        return this;
    }

    public ShareDialog setOnCancleListener(OnClickListener cancleListener){
        mOnCancleListener=cancleListener;
        return this;
    }

    public void show(){
        mDialog.show();
    }

    public void dismiss(){
        mDialog.dismiss();
    }

    /**
     * 创建BaseDialog实例
     * @return
     */
    public ShareDialog builder(){
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_share, null);
        //设置弹出框横向铺满整个屏幕
        view.setMinimumWidth(display.getWidth());
        mDialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
        //设置dialog弹出后会点击屏幕，dialog不消失；点击物理返回键dialog消失
        mDialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);
        tvMyInvitationCode = view.findViewById(R.id.tv_my_invitation_code);
        //设置点击隐藏
        txtCancle= view.findViewById(R.id.tv_share_cancel);
        txtCancle.setOnClickListener(v -> {
            if (mOnCancleListener != null) {
                mOnCancleListener.onClick(mDialog, Dialog.BUTTON_NEGATIVE);
            }
            dismiss();
        });
        //微信分享
        mWeChatShare= view.findViewById(R.id.tv_share_wx);
        mWeChatShare.setOnClickListener(v -> {
            if (mOnWeChatShareListener != null) {
                mOnWeChatShareListener.onClick(mDialog, Dialog.BUTTON_POSITIVE);
            }
            dismiss();
        });
        //朋友圈分享
        mWeChatFriendShare= view.findViewById(R.id.tv_share_wxpyq);
        mWeChatFriendShare.setOnClickListener(v -> {
            if(mOnWeChatFriendShareListener!=null) {
                mOnWeChatFriendShareListener.onClick(mDialog, Dialog.BUTTON_POSITIVE);
            }
            dismiss();
        });
        //QQ分享
        mQQShare= view.findViewById(R.id.tv_share_qq);
        mQQShare.setOnClickListener(v -> {
            if(mOnQQShareListener!=null){
                mOnQQShareListener.onClick(mDialog,Dialog.BUTTON_POSITIVE);
            }
        });
         //QQ空间分享
        mQQkjShare= view.findViewById(R.id.tv_share_qqkj);
        mQQkjShare.setOnClickListener(v -> {
            if(mOnQQkjShareListener!=null){
                mOnQQkjShareListener.onClick(mDialog,Dialog.BUTTON_POSITIVE);
            }
        });
         //二维码
        mQRcodeShare= view.findViewById(R.id.tv_share_qrcode);
        mQRcodeShare.setOnClickListener(v -> {
            if(mOnQRcodeListener!=null){
                mOnQRcodeListener.onClick(mDialog,Dialog.BUTTON_POSITIVE);
            }
        });
         //复制链接
        mCopylinkShare= view.findViewById(R.id.tv_share_copylink);
        mCopylinkShare.setOnClickListener(v -> {
            if(mCopylinkShareListener!=null){
                mCopylinkShareListener.onClick(mDialog,Dialog.BUTTON_POSITIVE);
            }
        });

        mDialog.setContentView(view);
        return this;
    }
}