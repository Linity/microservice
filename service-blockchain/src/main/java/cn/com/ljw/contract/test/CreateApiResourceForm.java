package cn.com.ljw.contract.test;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CreateApiResourceForm {

    /**
     * 描述
     */
    @Size(max = 200)
    private String description;

    /**
     * 应用code
     */
    private String appCode;

    /**
     * 排序序号
     */
    private Integer sort;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * API资源路径
     */
    @Size(max = 200)
    private String apiResourceUrl;

    /**
     * API资源编码（生成规则：requestMethod + : + apiResourceUrl）
     */
    private String apiResourceCode;

    /**
     * API资源名称
     */
    @Size(max = 80)
    private String apiResourceName;

    /**
     * 控制类型（data：数据权限类型API接口，system：系统类型API接口，其他待定）
     */
    private String ctrlType;

}

