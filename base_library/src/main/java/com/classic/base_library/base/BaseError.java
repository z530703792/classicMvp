package com.classic.base_library.base;


import com.classic.base_library.model.bean.base.BaseFault;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

/**
 * @author: zcq  2019-05-25 10:58
 */
public abstract class BaseError {

    public static final int TOLOGIN = -2;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int REQUEST_TIMEOUT = 408;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int BAD_GATEWAY = 502;
    public static final int SERVICE_UNAVAILABLE = 503;
    public static final int GATEWAY_TIMEOUT = 504;
    public static final int ERR_CODE_CONNECT = 271;
    public static final int ERR_CODE_NET = 272;
    public static final int ERR_CODE_UNKNOWN = 273;

    public  void onError(Throwable es) {
        Throwable e = es;
        for (Throwable throwable = e; throwable.getCause() != null && !(e instanceof HttpException); ) {
            e = throwable;
            throwable = throwable.getCause();
        }
        if (e instanceof BaseFault) {
            BaseFault baseFault = (BaseFault) e;
            this.onErr(0 , baseFault.getMessage());
        }else if (e instanceof HttpException) {
            int var4 = ((HttpException) e).code();

            switch (var4) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    this.onErr(ERR_CODE_CONNECT, "网络错误");
                    break;
            }

        } else if (e instanceof SocketTimeoutException) {
            this.onErr(ERR_CODE_NET, "响应超时!");
        } else if (e instanceof UnknownHostException) {
            this.onErr(ERR_CODE_NET, "请求错误!");
        }else if (e instanceof ConnectException) {
            this.onErr(ERR_CODE_CONNECT, "请求超时!");
        } else if (e instanceof SocketException) {
            this.onErr(ERR_CODE_CONNECT, "连接失败!");
        } else if (e instanceof IllegalArgumentException) {
            this.onErr(ERR_CODE_NET, "服务错误!");
        } else {
            this.onErr(ERR_CODE_UNKNOWN, "未知错误!");
        }

    }
    protected abstract void onErr(int code, String error_msg);

}