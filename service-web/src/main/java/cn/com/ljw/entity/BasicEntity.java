package cn.com.ljw.entity;


import java.util.Date;

/**
 * @author Steph_Lin
 * @date 2022/11/25
 */
public abstract class BasicEntity {

    private Integer id;

    private Date addTime;

    private Date updateTime;

    public abstract boolean mappingToDb();

}
