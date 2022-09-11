package com.classic.base_library.webview;

import com.classic.base_library.model.http.Apis;

/**
 * Created by zcq on 2019/1/4.
 */

public interface HtmlAddress {




    /**
     * 红包规则
     */
    String bonusRule      = Apis.HOST_HTML + "bonusRule";



    /**
     * 投资有礼
     */
    String investGift     = Apis.HOST_HTML + "investment";

    /**
     * 新客指引
     */
    String guidance       = Apis.HOST_HTML + "guidance";

    /**
     * 风险提示
     */
    String riskHints      = Apis.HOST_HTML + "fxts";

    /**
     * 积分规则
     */
    String integralRule   = Apis.HOST_HTML + "integralRule";

    /**
     * 约标
     */
    String aboutInvest    = Apis.HOST_HTML + "investAbout";

    /**
     * 风险测评
     */
    String risk           = "http://xx.xx.224.90:xxxx/riskEvaluation";

    /**
     * 信息披露
     */

    String basic          = Apis.HOST_HTML + "xxpl_index";

    /**
     * 邀请活动
     */
    String inviteAct      = Apis.HOST_HTML + "inviteAct";

    /**
     * 绑卡规则
     */
    String bindBankRule   = Apis.HOST_HTML + "bindBankRule";

    /**
     * 限额表
     */
    String bankList       = Apis.HOST_HTML + "bankList";

    /**
     * 协议模板
     */
    String loanProtocol   = Apis.HOST_HTML + "loanProtocol";

    /**
     * 关于我们
     */

    String about          = Apis.HOST_HTML + "h5AboutUs";

    /**
     * 行业资讯
     */
    String newsInform     = Apis.HOST_HTML + "form/";

    /**
     * 出借协议模板
     */
    String xieyi          = Apis.HOST_HTML + "xieyi";

    /**
     * CA证书授权书
     */
    String CAzssq         = Apis.HOST_HTML + "CAzssq";

    /**
     * 风险提示书
     */
    String fxtsBook       = Apis.HOST_HTML + "fxtsBook";

    /**
     * 风险测评
     */
    String riskQuiz       = Apis.HOST_HTML + "";

    /**
     * 风险测评结果
     */
    String riskEnd        = Apis.HOST_HTML + "";

    /**
     * 活动
     */
    String inviteLdy      = Apis.HOST_HTML + "";

    /**
     * 项目详情
     */
    String itemDetails    = Apis.HOST_HTML + "";

    /**
     * 授权委托书
     */
    String bestPlanBook   = Apis.HOST_HTML + "";

    /**
     * 转让及受让协议
     */
    String rightsBook     = Apis.HOST_HTML + "";

    /**
     * 转让项目详情
     */
    String planDetails    = Apis.HOST_HTML + "";

    /**
     * 转让说明
     */
    String transferDe     = Apis.HOST_HTML + "";

}
