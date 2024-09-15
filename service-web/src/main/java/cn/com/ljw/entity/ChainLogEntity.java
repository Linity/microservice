package cn.com.ljw.entity;

import cn.com.ljw.base.entity.AutoIdEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@TableName(value = "bc_chain_log")
public class ChainLogEntity extends AutoIdEntity {

    @ApiModelProperty("ip地址")
    private String ip;

    @ApiModelProperty("模块")
    private String moduleName;

}