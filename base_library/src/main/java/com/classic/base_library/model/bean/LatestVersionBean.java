package com.classic.base_library.model.bean;

/**
 * @author: zcq  2019-06-08 11:49
 */
public class LatestVersionBean {

    /**
     * id : 1
     * applicationStore : 豌豆荚
     * appKey : B
     * versionCode : 1.0.1
     * buildVersion : 1
     * address : www.baidu.com
     * publishTime : 1528424731000
     * updateDescription : 测试接口
     * force : true
     */

    private long id;
    private String applicationStore;
    private String appKey;
    private String versionCode;
    private String buildVersion;
    private String address;
    private long publishTime;
    private String updateDescription;
    private boolean force;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApplicationStore() {
        return applicationStore;
    }

    public void setApplicationStore(String applicationStore) {
        this.applicationStore = applicationStore;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getBuildVersion() {
        return buildVersion;
    }

    public void setBuildVersion(String buildVersion) {
        this.buildVersion = buildVersion;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public String getUpdateDescription() {
        return updateDescription;
    }

    public void setUpdateDescription(String updateDescription) {
        this.updateDescription = updateDescription;
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }
}
