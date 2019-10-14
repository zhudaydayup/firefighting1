package com.anjian.jiuxiao.dao.FireFighting;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: 随心的小新
 * @Date: 2019/4/25 20:36
 */
@Mapper
public interface TimelyRectificationDao {
    /**
     * 根据ID来查询数据
     * @param
     * @return
     */

    @Select("SELECT * FROM FIRE_CHECK_ITEM WHERE CREATE_TIME= #{RECORD_ID}")
    List<Map> getMessageById(@Param("RECORD_ID") String RECORD_ID);
    /**
     * 建立现场检查记录表
     */
    @Select( "select count(*) from FIRE_RECTIFICATION_RECORD where RECORD_ID=#{RECORD_ID}" )
    int getInspectionRecordCount(@Param("RECORD_ID") String RECORD_ID);
    @Update( "update FIRE_RECTIFICATION_RECORD set PROCESS_DECISION=#{PROCESS_DECISION},CHECKE_DETAIL=#{CHECKE_DETAIL},CHECKE_UNIT=#{CHECKE_UNIT},CHECKED_START_TIME=#{CHECKED_START_TIME},RECORD_TIME=#{RECORD_TIME},CHECK_UNIT=#{CHECK_UNIT} where RECORD_ID=#{RECORD_ID}" )
    void updateInspectionRecord(@Param("PROCESS_DECISION") String PROCESS_DECISION, @Param("CHECKE_DETAIL") String CHECKE_DETAIL, @Param("CHECKE_UNIT") String CHECKE_UNIT, @Param("CHECKED_START_TIME") String CHECKED_START_TIME, @Param("RECORD_TIME") String RECORD_TIME, @Param("CHECK_UNIT") String CHECK_UNIT, @Param("RECORD_ID") String RECORD_ID);
    @Insert("insert into FIRE_RECTIFICATION_RECORD (PROCESS_DECISION,CHECKE_DETAIL,CHECKE_UNIT,CHECKED_START_TIME,RECORD_TIME,CHECK_UNIT,RECORD_ID) values (#{PROCESS_DECISION},#{CHECKE_DETAIL},#{CHECKE_UNIT},#{CHECKED_START_TIME},#{RECORD_TIME},#{CHECK_UNIT},#{RECORD_ID})" )
    void setInspectionRecord(@Param("PROCESS_DECISION") String PROCESS_DECISION, @Param("CHECKE_DETAIL") String CHECKE_DETAIL, @Param("CHECKE_UNIT") String CHECKE_UNIT, @Param("CHECKED_START_TIME") String CHECKED_START_TIME, @Param("RECORD_TIME") String RECORD_TIME, @Param("CHECK_UNIT") String CHECK_UNIT, @Param("RECORD_ID") String RECORD_ID);

    /**
     * 上传执法者的名称以及ID
     */
    @Update( "update FIRE_RECTIFICATION_RECORD set CHECKE_SIGH = #{CHECKE_SIGNATURE} where RECORD_ID = #{RecordId}")
    int UpdateCheckSignature(@Param("CHECKE_SIGNATURE") String CHECKE_SIGNATURE, @Param("RecordId") String RecordId);

    /**
     * 上传被执法单位的名称以及ID
     */
    @Update("update FIRE_RECTIFICATION_RECORD set REPRESENR_SIGN = #{REPRESENR_SIGNATURE} where RECORD_ID = #{RecordId}")
    int UpdateRepresentSignature(@Param("REPRESENR_SIGNATURE") String REPRESENR_SIGNATURE, @Param("RecordId") String RecordId);

    /**
     * 从最初的表格中获取信息
     * @param
     * @param
     * @param
     */
    @Select("select * from FIRE_RECTIFICATION_RECORD where RECORD_ID = #{RECORD_ID}  ")
    List<Map> getPdfInfo(@Param("RECORD_ID") String RECORD_ID);

    @Select("select * from FIRE_CHECK_ITEM where CREATE_TIME = #{RECORD_ID}  ")
    List<Map> searchCareId(@Param("RECORD_ID") String RECORD_ID);

    @Update("update FIRE_RECTIFICATION_RECORD set PDF_PATH= #{PDF_PATH},QRCODE_PATH=#{QRCODE_PATH} where RECORD_ID= #{RECORD_ID}")
    void updatePdfAndQrcode(@Param("PDF_PATH") String PDF_PATH, @Param("QRCODE_PATH") String QRCODE_PATH, @Param("RECORD_ID") String RECORD_ID);

    /**
     * 最后生成PDF页面
     */
    @Select("select * from FIRE_RECTIFICATION_RECORD where RECORD_ID = #{recordId} ")
    List<Map> LookUpPdfById(@Param("recordId") String recordId);
    /**
     * 九小场所重大隐患上报记录
     */
    @Insert("insert into FIRE_DANGER_RECORD (RECORD_ID,PROCESS_DECISION,RISK_LEVEL,CHECKE_UNIT,CHECKED_START_TIME,RECORD_TIME) values (#{RECORD_ID},#{PROCESS_DECISION},#{Risk_Level},#{CHECKE_UNIT},#{CHECKED_START_TIME},#{RECORD_TIME})" )
    void setFireHiddenDangerRecord(@Param("RECORD_ID") String RECORD_ID, @Param("PROCESS_DECISION") String PROCESS_DECISION, @Param("Risk_Level") String Risk_Level, @Param("CHECKE_UNIT") String CHECKE_UNIT, @Param("CHECKED_START_TIME") String CHECKED_START_TIME, @Param("RECORD_TIME") String RECORD_TIME);


    @Select("select id1 from(select row_number() over (order by RECORD_ID) as id1,RECORD_ID from(select distinct RECORD_ID from FIRE_RECTIFICATION_RECORD where  CHECK_UNIT=#{CHECK_UNIT})) where RECORD_ID=#{RECORD_ID}")
    String getCheckUnitTable(@Param("CHECK_UNIT") String CHECK_UNIT, @Param("RECORD_ID") String RECORD_ID);
}
