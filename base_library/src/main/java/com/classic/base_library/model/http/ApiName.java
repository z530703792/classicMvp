package com.classic.base_library.model.http;

/**
 * @author: zcq 2019-08-06 16:25
 */
public class ApiName {
    //案列
    public final static String zcq                    = "zcq";

    /**
     * ---------------------------登录--------------------------------
     */
    public final static String LOGIN                  = "login/";
    //登录
    public final static String ACCOUNT_LOGIN          = LOGIN + "accountLogin";
    //获取登陆用手机验证码
    public static final String GET_LOGIN_TEST_CODE    = LOGIN + "sendNoteCheckCode";
    //手机验证码登陆
    public static final String ACCOUNT_PHONE_LOGIN    = LOGIN + "mobileLogin";

    /**
     * ----------------------------注册 --------------------------------------
     */
    //注册
    public static final String REGISTER               = "regist/";
    //获取注册手机验证码
    public static final String GET_REGISTER_TEST_CODE = REGISTER + "sendNoteCheckCode";
    //账号注册
    public static final String ACCOUNT_REGISTER       = REGISTER + "registAccount";

    /**
     * ---------------------------用户信息--------------------------------
     */

    public final static String USER                   = "user/";
    //修改用户信息
    public static final String UPDATE_USER            = USER + "updateUser";
    //获取所有申请
    public final static String GET_ALL_INVITE_RECORD  = USER + "getAllInviteRecord";
    //更换用户头像
    public static final String UPDATE_USER_HEADER     = USER + "updateUserHead";

    /**
     * ---------------------------用户账号信息变更--------------------------------
     */
    public static final String UPDATE_ACCOUNT_INFO    = "messageAlter/";
    //获取更改账户信息手机验证码
    public static final String SEND_NOTE_CHECK_CODE   = UPDATE_ACCOUNT_INFO + "sendNoteCheckCode";
    //修改手机号
    public static final String UPDATE_PHONE           = UPDATE_ACCOUNT_INFO + "updateMobile";
    //修改支付密码
    public static final String UPDATE_PAY_PWD         = UPDATE_ACCOUNT_INFO + "updatePaymentPwd";

}
