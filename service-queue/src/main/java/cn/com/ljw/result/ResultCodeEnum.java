package cn.com.ljw.result;

/**
 * @author Steph_Lin
 * @date 2020/10/22
 */
public enum ResultCodeEnum {

    /**
     * token相关的状态码
     * -1 用户未登陆
     * -2 token已经失效
     */
    TOKEN_UNAUTHORIZED("-1", "用户未登陆"),
    TOKEN_INVALID("-2", "无效的Token"),


    /**
     * 请求成功，data中包含有前端需要的数据。
     * 例如查询信息
     */
    SUCCESSFUL("200", "请求成功"),

    /**
     * 请求成功，data中不含前端的数据，只需要message的情况下。
     * 例如更新或者保存
     */
    SUCCESSFUL_MSG("204", "请求成功"),

    /**
     * 请求成功，但是前端传递的参数不符合后端格式校验，此时message要包含错误提示信息。
     * 列如新增数据时，名称重名时返回406
     */
    BAD_PARAMS("406", "参数不符合要求"),

    /**
     * Request Timeout
     */
    REQUEST_TIMEOUT("408", "请求超时"),

    /**
     * ERROR.
     */
    ERROR("500", "系统异常");


    private String code;
    private String message;

    ResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
