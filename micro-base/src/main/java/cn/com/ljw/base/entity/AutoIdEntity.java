package cn.com.ljw.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Steph_Lin
 * @date 2024/9/11
 */
@Data
@NoArgsConstructor
public class AutoIdEntity extends BaseEntity {

    @ApiModelProperty("主键")
    @TableId(type = IdType.AUTO)
    protected Long id;

}
