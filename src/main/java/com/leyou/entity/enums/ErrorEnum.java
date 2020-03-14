package com.leyou.entity.enums;


public enum ErrorEnum {
    // general
    SYS_ERR(600, "系统错误"),
    APP_ERR(601, "应用错误"),
    VALID_ERR(602, "参数校验错误"),

    // user
    USER_NOT_EXIST(700, "用户不存在"),
    PASSWORD_ERROR(701, "密码错误"),
    INVALID_TOKEN(702, "非法登录"),
    OPERATION_FAILED(704, "操作失败！"),

    // activity
    ACTIVITY_NOT_EXIST(800, "活动不存在"),

    // goods
    GOODS_NOT_EXIST(900, "商品不存在"),

    // store
    STORE_NOT_EXIST(1100, "门店不存在"),

    // store server
    STORE_SERVER_NOT_EXIST(1200, "美容师不存在"),

    //pay
    PACKAGE_NOT_EXIST(1010, "套餐为空"),
    COUPON_NOT_EXIST(1001, "优惠劵不存在"),
    COUPON_EXPIRE(1002, "优惠劵已过期"),
    COUPON_HAVE_RECEIVE(1003, "已领取过该优惠劵"),
    COUPON_UN_RECEIVE(1004, "未领取该优惠劵"),
    PAY_TOTAL_PRICE_ERROR(1005, "总价错误"),
    PAY_COUPON_PRICE_ERROR(1006, "优惠价格错误"),
    PAY_PAY_PRICE_ERROR(1007, "支付金额错误"),
    PAY_TYPE_ERROR(1008, "暂不支持该支付方式"),
    ORDER_NOT_EXIST(1009, "订单不存在"),
    ORDER_STATUS_ERROR(1010, "请选择已支付的订单");


    private Integer code;

    private String msg;

    ErrorEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
