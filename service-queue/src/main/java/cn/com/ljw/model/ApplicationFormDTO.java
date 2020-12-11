package cn.com.ljw.model;


import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Steph_Lin
 * @date 2020/10/22
 */
public class ApplicationFormDTO implements Serializable {

    private static final long serialVersionUID = 3237813512727366142L;

    /**
     * 流程编码
     * （参见 cn.com.citycloud.datac.developer.process.enums.ApplicationProcessEnum）
     *
     * PROCESS_SHARE_API_USE_APPLICATION", "用户申请使用共享接口流程"
     * PROCESS_LIMIT_API_USE_APPLICATION", "用户申请使用受限接口流程"
     * PROCESS_SHARE_API_PUBLISH_APPLICATION", "用户申请共享接口发布流程"
     * PROCESS_LIMIT_API_PUBLISH_APPLICATION", "用户申请受限接口发布流程"
     * PROCESS_SHARE_API_PUT_AWAY_APPLICATION", "用户申请共享接口上架流程"
     * PROCESS_LIMIT_API_PUT_AWAY_APPLICATION", "用户申请受限接口上架流程"
     * PROCESS_SHARE_API_SOLD_OUT_APPLICATION", "用户申请共享接口下架流程"
     * PROCESS_LIMIT_API_SOLD_OUT_APPLICATION", "用户申请受限接口下架流程"
     * PROCESS_ADMIN_SOLD_OUT_API_FORCE", "管理员强制下架接口流程"
     * PROCESS_API_COMMENT_APPLICATION", "接口评论审批流程"
     * PROCESS_APPLICATION_DEVELOPER", "用户申请成为开发者流程"
     */
    @NotBlank(message = "流程编码不能为空")
    private String processCode;

    /**
     * 应用申请编号（随机生成）
     */
    @NotBlank(message = "应用申请编号不能为空")
    private String applicationId;

    /**
     * 申请记录编号
     */
    private String applyRecordId;

    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * 接口提供方
     */
    private String interfaceAttribution;

    /**
     * 共享类型（共享策略）1: 公开共享,2：受限共享,3：内部共享
     */
    private String shareType;

    /**
     * API接口管理部门编号
     */
    private Long interfaceManageDeptId;

    /**
     * API接口管理部门名称
     */
    private String interfaceManageDeptName;

    /**
     * 资源编号
     */
    private String sourceId;

    public ApplicationFormDTO() {
        super();
    }

    public ApplicationFormDTO(
            @NotBlank(message = "流程编码不能为空") String processCode,
            @NotBlank(message = "申请编号不能为空") String applicationId,
            String applyRecordId,
            String sourceId,
            String interfaceName,
            String interfaceAttribution,
            String shareType,
            Long interfaceManageDeptId,
            String interfaceManageDeptName) {
        this.processCode = processCode;
        this.applicationId = applicationId;
        this.applyRecordId = applyRecordId;
        this.sourceId = sourceId;
        this.interfaceName = interfaceName;
        this.interfaceAttribution = interfaceAttribution;
        this.shareType = shareType;
        this.interfaceManageDeptId = interfaceManageDeptId;
        this.interfaceManageDeptName = interfaceManageDeptName;
    }

    @Override
    public String toString() {
        return "ApplicationFormDTO{" +
                "processCode='" + processCode + '\'' +
                ", applicationId='" + applicationId + '\'' +
                ", applyRecordId='" + applyRecordId + '\'' +
                ", interfaceName='" + interfaceName + '\'' +
                ", interfaceAttribution='" + interfaceAttribution + '\'' +
                ", shareType='" + shareType + '\'' +
                ", interfaceManageDeptId=" + interfaceManageDeptId +
                ", interfaceManageDeptName='" + interfaceManageDeptName + '\'' +
                ", sourceId='" + sourceId + '\'' +
                '}';
    }
}
