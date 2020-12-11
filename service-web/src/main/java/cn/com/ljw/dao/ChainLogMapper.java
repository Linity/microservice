package cn.com.ljw.dao;

import cn.com.ljw.entity.ChainLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChainLogMapper {
    int deleteByPrimaryKey(Integer id);

    int save(ChainLog record);

    int insertSelective(ChainLog record);

    ChainLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChainLog record);

    int updateByPrimaryKey(ChainLog record);
}