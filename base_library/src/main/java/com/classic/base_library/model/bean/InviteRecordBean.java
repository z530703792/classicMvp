package com.classic.base_library.model.bean;

import com.classic.base_library.model.bean.base.ListBean;

import java.util.List;

/**
 * @author: zcq 2019-12-19 21:19
 */
public class InviteRecordBean extends ListBean<InviteRecordBean.InviteRecordListBean> {

    private List<InviteRecordListBean> inviteRecordList;

    public List<InviteRecordListBean> getInviteRecordList() {
        return inviteRecordList;
    }

    public void setInviteRecordList(List<InviteRecordListBean> inviteRecordList) {
        this.inviteRecordList = inviteRecordList;
    }

    @Override
    public List<InviteRecordListBean> getDate() {
        return inviteRecordList;
    }

    public static class InviteRecordListBean {
        /**
         * id : 8 userId : 19 userNickName : 用户a verificationMessage : 但是但是多 inviteeId :
         * 14 inviteeMobile : 13018042871 status : 2 createAt : 2019-12-19 21:19:01
         * updateAt : 2019-12-19 21:19:01 deleted : 1
         */

        private long   id;
        private long   userId;
        private String userNickName;
        private String verificationMessage;
        private long   inviteeId;
        private String inviteeMobile;
        private String userMobile;
        private int    status;
        private String createAt;
        private String updateAt;
        private int    deleted;
        private String headPortraitUrl;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getUserNickName() {
            return userNickName;
        }

        public void setUserNickName(String userNickName) {
            this.userNickName = userNickName;
        }

        public String getVerificationMessage() {
            return verificationMessage;
        }

        public void setVerificationMessage(String verificationMessage) {
            this.verificationMessage = verificationMessage;
        }

        public long getInviteeId() {
            return inviteeId;
        }

        public void setInviteeId(long inviteeId) {
            this.inviteeId = inviteeId;
        }

        public String getInviteeMobile() {
            return inviteeMobile;
        }

        public void setInviteeMobile(String inviteeMobile) {
            this.inviteeMobile = inviteeMobile;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreateAt() {
            return createAt;
        }

        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }

        public String getUpdateAt() {
            return updateAt;
        }

        public void setUpdateAt(String updateAt) {
            this.updateAt = updateAt;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        public String getHeadPortraitUrl() {
            return headPortraitUrl;
        }

        public void setHeadPortraitUrl(String headPortraitUrl) {
            this.headPortraitUrl = headPortraitUrl;
        }

        public String getUserMobile() {
            return userMobile;
        }

        public void setUserMobile(String userMobile) {
            this.userMobile = userMobile;
        }
    }

}
