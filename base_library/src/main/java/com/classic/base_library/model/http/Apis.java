package com.classic.base_library.model.http;

import com.classic.base_library.BuildConfig;
import com.classic.base_library.model.bean.AccountBean;
import com.classic.base_library.model.bean.InviteRecordBean;
import com.classic.base_library.model.bean.zcqBean;
import com.classic.base_library.model.bean.base.BaseBean;
import com.classic.base_library.model.bean.base.ListResponse;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zcq
 */
public interface Apis {
    String HOST      = BuildConfig.BASE_API;
    String HOST_HTML = BuildConfig.BASE_API_HTML;

    @FormUrlEncoded
    @POST(ApiName.zcq)
    Flowable<BaseBean> getzcq(@FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @POST(ApiName.zcq)
    Flowable<ListResponse<zcqBean>> getListzcq(@FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @POST(ApiName.zcq)
    Flowable<BaseBean<zcqBean>> getDatazcq(@FieldMap Map<String, Object> params);

    /**
     * 登录
     * 
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(ApiName.ACCOUNT_LOGIN)
    Flowable<BaseBean<AccountBean>> accountLogin(@FieldMap Map<String, Object> params);

    /**
     * 获取所有申请
     * 
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(ApiName.GET_ALL_INVITE_RECORD)
    Flowable<BaseBean<InviteRecordBean>> getAllInviteRecord(@FieldMap Map<String, Object> params);

    /**
     * 修改用户信息
     * 
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(ApiName.UPDATE_USER)
    Flowable<BaseBean> updateUser(@FieldMap Map<String, Object> params);

    /**
     * 获取注册手机验证码
     * 
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(ApiName.GET_REGISTER_TEST_CODE)
    Flowable<BaseBean> getRegisterPhoneTestCode(@FieldMap Map<String, Object> params);

    /**
     * 注册请求
     * 
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(ApiName.ACCOUNT_REGISTER)
    Flowable<BaseBean<AccountBean>> accountRegister(@FieldMap Map<String, Object> params);

    /**
     * 获取登陆手机验证码
     * 
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(ApiName.GET_LOGIN_TEST_CODE)
    Flowable<BaseBean> getLoginPhoneTestCode(@FieldMap Map<String, Object> params);

    /**
     * 登陆请求
     * 
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(ApiName.ACCOUNT_PHONE_LOGIN)
    Flowable<BaseBean<AccountBean>> accountPhoneLogin(@FieldMap Map<String, Object> params);

    /**
     * 获取更改账户信息手机验证码
     * 
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(ApiName.SEND_NOTE_CHECK_CODE)
    Flowable<BaseBean> getUpdateAccountPhoneCode(@FieldMap Map<String, Object> params);

    /**
     * 修改手机号
     * 
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(ApiName.UPDATE_PHONE)
    Flowable<BaseBean> updatePhone(@FieldMap Map<String, Object> params);

    /**
     * 修改支付密码
     * 
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(ApiName.UPDATE_PAY_PWD)
    Flowable<BaseBean> updatePayPwd(@FieldMap Map<String, Object> params);

    /**
     * 更换用户头像
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(ApiName.UPDATE_USER_HEADER)
    Flowable<BaseBean> updateUserHeader(@FieldMap Map<String, Object> params);
}
