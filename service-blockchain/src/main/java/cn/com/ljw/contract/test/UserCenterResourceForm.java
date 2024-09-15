package cn.com.ljw.contract.test;


public class UserCenterResourceForm {
    private int id;

    /**
     * 上级资源ID(根级为0)
     */
    private int parentId;

    /**
     * 资源类型:1.菜单资源;2.操作资源(按钮资源);3.数据行资源;4.接口资源
     */
    private String resourceType;

    private String resourceCode;
    private String resourceConnectPath;

    private String resourceShowName;

    private String resourceName;

    private String resourcePath;

    /**
     * 资源模块
     */
    private String resourceModule;

    private int resourceSort;

    private String remark;

    private String resourceIcon;

    private int resourceIconIsShow;

    private String createTime;

    /**
     * 	资源导航路径,格式：|0|9848|9849|10529| ,|{id}|
     */
    private String resourceNav;

    /**
     * 接口请求方式,多个逗号分隔(GET, POST, PUT, DELETE)
     */
    private String methods;

    /**
     * 	接口协议,多个逗号分隔(http, https)
     */
    private String protocols;

    public String getResourceConnectPath() {
        return resourceConnectPath;
    }

    public void setResourceConnectPath(String resourceConnectPath) {
        this.resourceConnectPath = resourceConnectPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceShowName() {
        return resourceShowName;
    }

    public void setResourceShowName(String resourceShowName) {
        this.resourceShowName = resourceShowName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getResourceModule() {
        return resourceModule;
    }

    public void setResourceModule(String resourceModule) {
        this.resourceModule = resourceModule;
    }

    public int getResourceSort() {
        return resourceSort;
    }

    public void setResourceSort(int resourceSort) {
        this.resourceSort = resourceSort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getResourceIcon() {
        return resourceIcon;
    }

    public void setResourceIcon(String resourceIcon) {
        this.resourceIcon = resourceIcon;
    }

    public int getResourceIconIsShow() {
        return resourceIconIsShow;
    }

    public void setResourceIconIsShow(int resourceIconIsShow) {
        this.resourceIconIsShow = resourceIconIsShow;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getResourceNav() {
        return resourceNav;
    }

    public void setResourceNav(String resourceNav) {
        this.resourceNav = resourceNav;
    }

    public String getMethods() {
        return methods;
    }

    public void setMethods(String methods) {
        this.methods = methods;
    }

    public String getProtocols() {
        return protocols;
    }

    public void setProtocols(String protocols) {
        this.protocols = protocols;
    }
}

