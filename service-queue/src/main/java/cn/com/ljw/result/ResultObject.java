package cn.com.ljw.result;

import java.io.Serializable;

/**
 * @author Steph_Lin
 * @date 2020/10/22
 */
public class ResultObject<T> implements Serializable {

    private static long serialVersionUID = -2792556188993845048L;

    private String code;

    private String message;

    private T data;

    /**
     * 让构造函数为 private，这样该类就不会被实例化
     */
    private ResultObject() {
    }

    /**
     * 只有成功信息
     */
    public static <T> ResultObject<T> success() {
        ResultObject<T> resultObject = new ResultObject<>();
        resultObject.setCode(ResultCodeEnum.SUCCESSFUL.getCode());
        resultObject.setMessage(ResultCodeEnum.SUCCESSFUL.getMessage());
        return resultObject;
    }

    /**
     * 只关注message
     */
    public static <T> ResultObject<T> successMsg(String message) {
        ResultObject<T> resultObject = new ResultObject<>();
        resultObject.setCode(ResultCodeEnum.SUCCESSFUL.getCode());
        resultObject.setMessage(message);
        return resultObject;
    }

    /**
     * 只关注data
     */
    public static <T> ResultObject<T> successDate(T data) {
        ResultObject<T> resultObject = new ResultObject<>();
        resultObject.setCode(ResultCodeEnum.SUCCESSFUL.getCode());
        resultObject.setMessage(ResultCodeEnum.SUCCESSFUL.getMessage());
        resultObject.setData(data);
        return resultObject;
    }

    public static <T> ResultObject<T> success(String message, T data) {
        ResultObject<T> resultObject = new ResultObject<>();
        resultObject.setCode(ResultCodeEnum.SUCCESSFUL.getCode());
        resultObject.setMessage(message);
        resultObject.setData(data);
        return resultObject;
    }

    /**
     * 可以选择返回状态码
     */
    public static <T> ResultObject<T> success(ResultCodeEnum resultCodeEnum, String message, T data) {
        ResultObject<T> resultObject = new ResultObject<>();
        resultObject.setCode(resultCodeEnum.getCode());
        resultObject.setMessage(message);
        resultObject.setData(data);
        return resultObject;
    }


    public static <T> ResultObject<T> error(String message) {
        ResultObject<T> resultObject = new ResultObject<>();
        resultObject.setCode(ResultCodeEnum.ERROR.getCode());
        resultObject.setMessage(message);
        return resultObject;
    }


    public static <T> ResultObject<T> error(ResultCodeEnum resultCodeEnum, String message) {
        ResultObject<T> resultObject = new ResultObject<>();
        resultObject.setCode(resultCodeEnum.getCode());
        resultObject.setMessage(message);
        return resultObject;
    }


    public static <T> ResultObject<T> error(Exception e) {
        ResultObject<T> resultObject = new ResultObject<>();
        resultObject.setCode(ResultCodeEnum.ERROR.getCode());
        if (e.getMessage() == null) {
            resultObject.setMessage(ResultCodeEnum.ERROR.getMessage());
        } else {
            resultObject.setMessage(e.getMessage());
        }
        return resultObject;
    }


    public ResultObject timeout(String message) {
        ResultObject<T> resultObject = new ResultObject<>();
        resultObject.setCode(ResultCodeEnum.REQUEST_TIMEOUT.getCode());
        resultObject.setMessage(message);
        return resultObject;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
