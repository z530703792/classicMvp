package com.classic.base_library.model.bean.base;

/**
 * 异常处理类，将异常包装成一个 Fault ,抛给上层统一处理
 * Created by zcq on 2019/12/7.
 */

public class BaseFault extends RuntimeException {
    private String errorCode;

    public BaseFault(String errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
