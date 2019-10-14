package com.anjian.jiuxiao.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Totle:DataDao
 * @ProjectName:yjbawechat
 * @author:社会码农
 * @data:2019/4/118:09
 */
@Mapper
public interface DataDao{
    /**
     *传送九小场所责令限期整改数据
     * @param
     * @return
     */
    @Select("SELECT * FROM FIRE_CHECK_ITEM WHERE CREATE_TIME = #{recordId}")
    List<Map> putFireFightingTimeLimitDate(String recordId);

    @Select("SELECT * FROM FIRE_TIME_LIMIT_RECORD WHERE RECORD_ID = #{recordId}")
    List<Map> putFireFightingTimeLimitDate1(String recordId);

    @Select("SELECT * FROM FIRE_CHECK_ITEM WHERE CREATE_TIME = #{recordId}")
    List<Map> putFireFightingTimeLimitDate3(String recordId);


    /**
     *传送九小场所现场改正数据
     */
    @Select("SELECT * FROM FIRE_RECTIFICATION_RECORD WHERE RECORD_ID = #{recordId}")
    List<Map> putTimeRectificationDate(String recordId);

    /**
     *传送九小场所重大隐患数据
     */
    @Select("SELECT * FROM FIRE_DANGER_RECORD WHERE RECORD_ID = #{recordId}")
    List<Map> putFireHiddenDangerRecordDate(String recordId);

}
