package cn.com.ljw.entity;

import java.util.Date;

public class ChainLog {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private String ip;

    private Date optTime;

    private String moduleName;

    private String content;

    private Boolean state;

    private String appId;

    private String optType;

    private String userId;

    private String userName;

    private String department;

    private String code;

    public ChainLog(Integer id, Date createTime, Date updateTime, String ip, Date optTime, String moduleName, String content, Boolean state, String appId, String optType, String userId, String userName, String department, String code) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.ip = ip;
        this.optTime = optTime;
        this.moduleName = moduleName;
        this.content = content;
        this.state = state;
        this.appId = appId;
        this.optType = optType;
        this.userId = userId;
        this.userName = userName;
        this.department = department;
        this.code = code;
    }

    public ChainLog() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getOptTime() {
        return optTime;
    }

    public void setOptTime(Date optTime) {
        this.optTime = optTime;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType == null ? null : optType.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}