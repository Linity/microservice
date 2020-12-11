package cn.com.ljw.contract.java.logic.entity;

/**
 * Created by Steph_Lin on 2020/3/13.
 */
public class ContractChainLog {

    private String id;
    private String content;
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ContractChainLog{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        ContractChainLog contractChainLog = (ContractChainLog) obj;
        return this.id.equals(contractChainLog.getId());
    }

}
