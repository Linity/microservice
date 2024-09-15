package cn.com.ljw.contract.test;

import lombok.Data;

import java.util.List;

@Data
public class BatchCreateApiResourceForm {
    private Long syncType;

    private List<CreateApiResourceForm> apiResourceFormList;

    private String appCode;
}

