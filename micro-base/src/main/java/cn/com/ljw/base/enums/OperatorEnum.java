package cn.com.ljw.base.enums;

/**
 * 操作符枚举
 *
 * @author Steph_Lin
 * @date 2021/4/10
 */
public enum OperatorEnum {

    EQUAL(1, "="),
    GREATE_THAN(2, ">"),
    NOT_LESS_THAN(3, ">="),
    LESS_THAN(4, "<"),
    NOT_GREATE_THAN(5, "<="),
    LIKE(6, "%");

    private Integer code;

    private String value;

    OperatorEnum(Integer code, String value){
        this.code = code;
        this.value = value;
    }

}
