package com.classic.base_library.model.bean;

import com.classic.base_library.model.bean.base.DataBean;

/**
 * @author: zcq 2019-12-11 14:16
 */
public class AccountBean extends DataBean<UserBean> {

    /**
     * user :
     * {"id":14,"nickName":"用户y9loyz","sex":0,"headPortraitUrl":"http://test-lot.decennium-tech.com/qq-img/default_head.png","qrCodeUrl":"","historyMobile":null,"mobile":"13018042871","signature":null,"accountId":16,"userToken":"{\"code\":200,\"info\":{\"token\":\"dbfec9a8587d2751aff0e6d94a2c4744\",\"accid\":\"13018042871\",\"name\":\"\"}}","createAt":"2019-12-11
     * 16:36:44","updateAt":"2019-12-11 16:36:44","deleted":1}
     */

    private UserBean user;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    @Override
    public UserBean getDate() {
        return user;
    }
}
