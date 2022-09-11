package com.classic.base_library.msg;

/**
 * @author: zcq 2019-06-13 15:57
 */
public class RxEvent {

    /**
     * RecyclerView
     */
    public static final String EVENT_DEL_ITEM    = "delete_item";     //删除单条数据
    public static final String EVENT_UPDATE_ITEM = "update_item";     //更新单条数据

    /**
     * 主界面切换显示
     */
    public final static String EVENT_MAIN_PAGE   = "main_page_switch";
    /**
     * 切换显示
     */
    public final static String EVENT_PAGE        = "page_switch";

    /**
     * 聊天消息
     */
    public final static String EVENT_CHAT_NUM    = "chat_num";

    /**
     * 新的好友
     */
    public final static String EVENT_ADD_FRIEND  = "add_friend";
}
