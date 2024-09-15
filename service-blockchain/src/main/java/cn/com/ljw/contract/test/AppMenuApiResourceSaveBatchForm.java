package cn.com.ljw.contract.test;

import lombok.Data;

import java.io.Serializable;

@Data
public class AppMenuApiResourceSaveBatchForm implements Serializable {

    /** default serialVersionUID */
    private static final long serialVersionUID = 1L;

    private String menuCode;

    private String apiResourceCode;
}
