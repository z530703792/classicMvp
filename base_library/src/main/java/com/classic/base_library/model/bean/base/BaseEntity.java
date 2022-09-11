package com.classic.base_library.model.bean.base;

import java.io.Serializable;
import java.util.Map;

import io.reactivex.Flowable;

/**
 * Created by zcq
 */
public interface BaseEntity {
    class BaseBean implements Serializable {
        public long beid;
        public String objectId;
        public Map<String, Object> param;
    }

    interface IListBean {
        Flowable getPageAt(String type);

        void setParam(Map<String, Object> param);
    }

    abstract class ListBean extends BaseBean implements IListBean {
        @Override
        public void setParam(Map<String, Object> param) {
            this.param=param;
        }
    }
}
