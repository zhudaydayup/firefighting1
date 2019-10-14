package com.anjian.jiuxiao.dao.FireFighting;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: 随心的小新
 * @Date: 2019/4/25 17:17
 */
@Mapper
public interface TimeLimitDao {
    /**
     * 获取被执法单位名称
     */
    @Select("SELECT * FROM FIRE_CHECK_ITEM WHERE CREATE_TIME= #{recordId}")
    List<Map> getEnforcerById(@Param("recordId") String recordId);

    /**
     * 获取存在的问题列表
     */
    @Select("SELECT * FROM FIRE_CHECK_ITEM WHERE CREATE_TIME= #{recordId}" )
    List<Map> getProblemsById(@Param("recordId") String recordId);

    /**
     * 获取PDF预览信息
     */
    @Select ( "select count(*) from  FIRE_TIME_LIMIT_RECORD where RECORD_ID=#{RECORD_ID}" )
    int getCreateTimeLimitTableCount(@Param("RECORD_ID") String RECORD_ID);
    @Update ( "update FIRE_TIME_LIMIT_RECORD set TIME_IDS = #{TIME_IDS},EXECUTE_PEOPLE2=#{EXECUTE_PEOPLE2},CARD_NUMBER2=#{CARD_NUMBER2},CHECKE_UNIT = #{CHECKE_UNIT},EXECUTE_PEOPLE = #{EXECUTE_PEOPLE},CARD_NUMBER = #{CARD_NUMBER},CHECKE_DETAIL=#{CHECKE_DETAIL},GREATE_TIME=#{createTime},CHECK_UNIT=#{CHECK_UNIT} where RECORD_ID=#{RECORD_ID}" )
    int updateCreateTimeLimitTable(@Param("TIME_IDS") String TIME_IDS,
                                   @Param("EXECUTE_PEOPLE2") String EXECUTE_PEOPLE2, @Param("CARD_NUMBER2") String CARD_NUMBER2,
                                   @Param("CHECKE_UNIT") String CHECKE_UNIT, @Param("EXECUTE_PEOPLE") String EXECUTE_PEOPLE,
                                   @Param("CARD_NUMBER") String CARD_NUMBER, @Param("CHECKE_DETAIL") String CHECKE_DETAIL,
                                   @Param("createTime") String createTime, @Param("CHECK_UNIT") String CHECK_UNIT, @Param("RECORD_ID") String RECORD_ID);
    @Insert( "insert into FIRE_TIME_LIMIT_RECORD(TIME_IDS,EXECUTE_PEOPLE2,CARD_NUMBER2,CHECKE_UNIT, EXECUTE_PEOPLE,CARD_NUMBER,CHECKE_DETAIL,GREATE_TIME,CHECK_UNIT,RECORD_ID) values (#{TIME_IDS},#{EXECUTE_PEOPLE2}, #{CARD_NUMBER2},#{CHECKE_UNIT}, #{EXECUTE_PEOPLE}, #{CARD_NUMBER}, #{CHECKE_DETAIL},#{createTime},#{CHECK_UNIT},#{RECORD_ID})")
    int createTimeLimitTable(@Param("TIME_IDS") String TIME_IDS,
                             @Param("EXECUTE_PEOPLE2") String EXECUTE_PEOPLE2, @Param("CARD_NUMBER2") String CARD_NUMBER2,
                             @Param("CHECKE_UNIT") String CHECKE_UNIT, @Param("EXECUTE_PEOPLE") String EXECUTE_PEOPLE,
                             @Param("CARD_NUMBER") String CARD_NUMBER, @Param("CHECKE_DETAIL") String CHECKE_DETAIL,
                             @Param("createTime") String createTime, @Param("CHECK_UNIT") String CHECK_UNIT, @Param("RECORD_ID") String RECORD_ID);

    /**
     * 上传执法者的名称以及ID
     */
    @Update( "update FIRE_TIME_LIMIT_RECORD set CHECKE_SIGH = #{CHECKE_SIGH} where RECORD_ID = #{RecordId}")
    int UpdateEnforceSigh(@Param("CHECKE_SIGH") String CHECKE_SIGH, @Param("RecordId") String RecordId);

    /**
     * 上传被执法单位的名称以及ID
     */
    @Update("update FIRE_TIME_LIMIT_RECORD set REPRESENR_SIGN = #{REPRESENR_SIGN} where RECORD_ID = #{RecordId}")
    int UpdateInspectorSigh(@Param("REPRESENR_SIGN") String REPRESENR_SIGN, @Param("RecordId") String RecordId);
    /**
     * 最后生成PDF页面以及二维码图片
     */
    @Select("select * from FIRE_TIME_LIMIT_RECORD where RECORD_ID = #{recordId} ")
    List<Map> getTimeLimitRecord(@Param("recordId") String recordId);

    /**
     * 获取PDF模板
     * @param
     * @return
     */
    @Select("SELECT * FROM FIRE_TIME_LIMIT_RECORD WHERE RECORD_ID= #{ID}")
    List<Map> getThreeById(@Param("ID") String ID);

    /**
     * 从最初的表格中获取信息
     * @param
     * @param
     * @param
     */
    @Select("select * from FIRE_CHECK_ITEM where CREATE_TIME = #{ID}  ")
    List<Map> searchCareId(@Param("ID") String ID);

    @Update("update FIRE_TIME_LIMIT_RECORD set ENFORCE_PAPER= #{pdfName},QR_CODE_URL=#{qrCodeName} where RECORD_ID= #{id}")
    void updatePdfAndQrcode(@Param("pdfName") String pdfName, @Param("qrCodeName") String qrCodeName, @Param("id") String id);

    /**
     * 生成文书编号
     */
    @Select ("select id1 from(select row_number() over (order by RECORD_ID) as id1,RECORD_ID from (select distinct RECORD_ID from FIRE_TIME_LIMIT_RECORD where CHECK_UNIT=#{CHECK_UNIT})) where RECORD_ID=#{RECORD_ID}")
    String getCheckUnitTable(@Param("CHECK_UNIT") String CHECK_UNIT, @Param("RECORD_ID") String RECORD_ID);
}


