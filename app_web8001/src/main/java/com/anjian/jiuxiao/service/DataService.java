package com.anjian.jiuxiao.service;


import com.alibaba.fastjson.JSONObject;
import com.anjian.jiuxiao.dao.DataDao;
import com.anjian.jiuxiao.util.HttpUtil;
import com.anjian.jiuxiao.util.MsgUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Totle:DataService
 * @ProjectName:yjbawechat
 * @author:社会码农
 * @data:2019/4/118:05
 */
@Service
public class DataService {

    @Resource
    private DataDao dataDao;
    static final Logger logger = LoggerFactory.getLogger(DataService.class);



    /**
     * 传送九小场所限期改正数据
     */
    public String putFireFightingTimeLimitDate(String recordId,String userId,String sCode) {
        String risk_level ="责令整改";
        String id ="";
        String social_credit_code ="";
        String checked_start_time ="";
        String ent_name ="";
        String checker_id ="";
        String checker ="";
        String checked_detail ="";
        String risk_point ="";
        String risk_content ="";
        String rectify_law_document ="";
        String rectify_law_document_qr ="";
        String unit_address ="";
        String inspect_type ="";
        String region_id ="";
        String fire_safety_behavior = "";
        String rectification_deadline = "";

        try {
            HashMap<String, String> map = new HashMap<>(31);
            List<Map> data = dataDao.putFireFightingTimeLimitDate(recordId);
            Date date=new Date();
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String today=df.format(date);
            if (data.get ( 0 ).get ("CREATE_TIME")!=null){
                id =  data.get ( 0 ).get ("CREATE_TIME").toString();
            }
            if (data.get ( 0 ).get ("CHECKED_UNIT")!=null){
                ent_name =  data.get ( 0 ).get ("CHECKED_UNIT").toString();
            }
            if (data.get ( 0 ).get ("UNIT_NUMBER")!=null){
                social_credit_code =  data.get ( 0 ).get ("UNIT_NUMBER").toString();
            }
            if (data.get ( 0 ).get ("CARD_NUMBER")!=null){
                checker_id =  data.get ( 0 ).get ("CARD_NUMBER").toString();
            }
            if (data.get ( 0 ).get ("CHECK_PEOPLE")!=null){
                checker =  data.get ( 0 ).get ("CHECK_PEOPLE").toString();
            }
            if (data.get ( 0 ).get ("CHECK_START_TIME")!=null){
                checked_start_time =  data.get ( 0 ).get ("CHECK_START_TIME").toString();
            }
            if (data.get ( 0 ).get ("CHECK_DESC")!=null){
                checked_detail =  data.get ( 0 ).get ("CHECK_DESC").toString();
            }
            if (data.get ( 0 ).get ("RISK_POINT")!=null){
                risk_point =  data.get ( 0 ).get ("RISK_POINT").toString();
            }
            if (data.get ( 0 ).get ("CHECK_ITEM")!=null){
                risk_content =  data.get ( 0 ).get ("CHECK_ITEM").toString();
            }
            if (data.get ( 0 ).get ("ADDRESS")!=null){
                unit_address =  data.get ( 0 ).get ("ADDRESS").toString();
            }
            List<Map> data1 = dataDao.putFireFightingTimeLimitDate1(recordId);
            if (data1.get ( 0 ).get ("ENFORCE_PAPER")!=null){
                rectify_law_document =  data1.get ( 0 ).get ("ENFORCE_PAPER").toString();
            }
            if (data1.get ( 0 ).get ("QR_CODE_URL")!=null){
                rectify_law_document_qr =  data1.get ( 0 ).get ("QR_CODE_URL").toString();
            }
            if (data1.get ( 0 ).get ("TIME_IDS")!=null){
                rectification_deadline =  data1.get ( 0 ).get ("TIME_IDS").toString();
            }
            List<Map> data3 = dataDao.putFireFightingTimeLimitDate3(recordId);
            if (data3.get ( 0 ).get ("INSPECT_TYPE")!=null){
                inspect_type =  data3.get ( 0 ).get ("INSPECT_TYPE").toString();
            }
            if (data3.get ( 0 ).get ("REGION_ID")!=null){
                region_id =  data3.get ( 0 ).get ("REGION_ID").toString();
            }
            String CHECK_ITEM = "";
            if (data3.get ( 0 ).get ("CHECK_ITEM")!=null){
                CHECK_ITEM = data3.get ( 0 ).get ("CHECK_ITEM").toString();
            }
            String[] arr = CHECK_ITEM.split("__");
            for (int i=0;i<arr.length;i++)
            {
                Map<String,Object> map1 = new HashMap<>(10);
                map1.put("value",i+1);
                map1.put("text",arr[i]);
                JSONObject jsonObject = new JSONObject(map1);
                fire_safety_behavior = fire_safety_behavior +jsonObject.toString()+",";
            }
            fire_safety_behavior ="["+ fire_safety_behavior.substring(0, fire_safety_behavior.length() - 1)+"]";

            map.put("id", UUID.randomUUID().toString());   //记得修改
            map.put("ent_name", ent_name);
            map.put("social_credit_code", sCode);
            map.put("checker_id", userId);
            map.put("checker", checker);
            map.put("checked_start_time", checked_start_time);
            map.put("checked_end_time", today);
            map.put("checked_detail", checked_detail);
            map.put("checked_status", "");
            map.put("checked_experts", "");
            map.put("checked_deps","");
            map.put ( "is_correction", "1");
            map.put ( "record_id",recordId );
            map.put("correction_detail","");
            map.put ( "notice_law_document", "");
            map.put ( "notice_law_document_qr","" );
            String pdf="http://zfxc.njyjgl.cn/yjbd/";
            String qrcode="http://zfxc.njyjgl.cn/yjbd/";
//            map.put ( "record_law_document", pdf+"expert_record/"+pdf_path);
//            map.put ( "record_law_document_qr",qrcode+"expert_record/"+qrcode_path );

            map.put ( "record_law_document", "http://zfxc.njyjgl.cn/yjbd/"+data.get(0).get("CHECK_PDF").toString());
            map.put ( "record_law_document_qr","http://zfxc.njyjgl.cn/yjbd/"+data.get(0).get("QR_CODE").toString() );
            map.put ( "punish_law_document", "");
            map.put ( "punish_law_document_qr", "");
            map.put ( "rectify_law_document", pdf+"zeling/"+rectify_law_document);
            map.put ( "rectify_law_document_qr", qrcode+"zeling/"+rectify_law_document_qr);
            map.put ( "review_law_document", "");
            map.put ( "review_law_document_qr", "");
            map.put ( "risk_level",risk_level );
            map.put ( "risk_content" ,risk_content);
            map.put ( "checked_type", "");
            map.put ( "supervisor_id", "");
            map.put ( "risk_point" ,risk_point);
            map.put ( "unit_address", unit_address);
            map.put ( "ent_id", social_credit_code);
            map.put ( "inspect_type", inspect_type);
            map.put ( "department_id", region_id);
            map.put ( "fire_safety_behavior", fire_safety_behavior);
            map.put("rectification_deadline",rectification_deadline);
            String url="http://api.njyjgl.cn/spp_grid_dev/resource/place_inspecting/enforcement_nust";
            String res= HttpUtil.httpPost(url,map);
            return res;
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.info ( e.toString () );
            return  MsgUtils.errorMsg("网络繁忙，请稍后再试...");
        }
    }

    /**
     * 传送九小场所现场改正数据
     */
    public String putTimeRectificationDate(String recordId,String userId,String sCode) {
        String risk_level ="现场整改";
        String id ="";
        String social_credit_code ="";
        String checked_start_time ="";
        String ent_name ="";
        String checker_id ="";
        String checker ="";
        String checked_detail ="";
        String risk_point ="";
        String risk_content ="";
        String punish_law_document ="";
        String punish_law_document_qr ="";
        String unit_address ="";
        String correction_detail ="";
        String inspect_type ="";
        String region_id ="";
        try {
            HashMap<String, String> map = new HashMap<>(31);
            List<Map> data = dataDao.putFireFightingTimeLimitDate(recordId);
            Date date=new Date();
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String today=df.format(date);
            if (data.get ( 0 ).get ("CREATE_TIME")!=null){
                id =  data.get ( 0 ).get ("CREATE_TIME").toString();
            }
            if (data.get ( 0 ).get ("CHECKED_UNIT")!=null){
                ent_name =  data.get ( 0 ).get ("CHECKED_UNIT").toString();
            }
            if (data.get ( 0 ).get ("UNIT_NUMBER")!=null){
                social_credit_code =  data.get ( 0 ).get ("UNIT_NUMBER").toString();
            }

            if (data.get ( 0 ).get ("CHECK_PEOPLE")!=null){
                checker =  data.get ( 0 ).get ("CHECK_PEOPLE").toString();
            }
            if (data.get ( 0 ).get ("CHECK_START_TIME")!=null){
                checked_start_time =  data.get ( 0 ).get ("CHECK_START_TIME").toString();
            }
            if (data.get ( 0 ).get ("CHECK_DESC")!=null){
                checked_detail =  data.get ( 0 ).get ("CHECK_DESC").toString();
            }
            if (data.get ( 0 ).get ("RISK_POINT")!=null){
                risk_point =  data.get ( 0 ).get ("RISK_POINT").toString();
            }
            if (data.get ( 0 ).get ("CHECK_ITEM")!=null){
                risk_content =  data.get ( 0 ).get ("CHECK_ITEM").toString();
            }
            if (data.get ( 0 ).get ("ADDRESS")!=null){
                unit_address =  data.get ( 0 ).get ("ADDRESS").toString();
            }
            List<Map> data1 = dataDao.putTimeRectificationDate(recordId);
            if (data1.get ( 0 ).get ("PDF_PATH")!=null){
                punish_law_document =  data1.get ( 0 ).get ("PDF_PATH").toString();
            }
            if (data1.get ( 0 ).get ("QRCODE_PATH")!=null){
                punish_law_document_qr =  data1.get ( 0 ).get ("QRCODE_PATH").toString();
            }
            if (data1.get ( 0 ).get ("PROCESS_DECISION")!=null){
                correction_detail =  data1.get ( 0 ).get ("PROCESS_DECISION").toString();
            }
            List<Map> data3 = dataDao.putFireFightingTimeLimitDate3(recordId);
            if (data3.get ( 0 ).get ("INSPECT_TYPE")!=null){
                inspect_type =  data3.get ( 0 ).get ("INSPECT_TYPE").toString();
            }
            if (data3.get ( 0 ).get ("REGION_ID")!=null){
                region_id =  data3.get ( 0 ).get ("REGION_ID").toString();
            }
            map.put("id", UUID.randomUUID().toString());   //记得修改
            map.put("ent_name", ent_name);
            map.put("social_credit_code", sCode);
            map.put("checker_id", userId);
            map.put("checker", checker);
            map.put("checked_start_time", checked_start_time);
            map.put("checked_end_time", today);
            map.put("checked_detail", checked_detail);
            map.put("checked_status", "");
            map.put("checked_experts", "");
            map.put("checked_deps","");
            map.put ( "is_correction", "否");
            map.put ( "record_id",recordId );
            map.put("correction_detail",correction_detail);
            map.put ( "notice_law_document", "");
            map.put ( "notice_law_document_qr","" );

            String pdf="http://zfxc.njyjgl.cn/yjbd/";
            String qrcode="http://zfxc.njyjgl.cn/yjbd/";
            map.put ( "record_law_document", "http://zfxc.njyjgl.cn/yjbd/"+data.get(0).get("CHECK_PDF").toString());
            map.put ( "record_law_document_qr","http://zfxc.njyjgl.cn/yjbd/"+data.get(0).get("QR_CODE").toString() );
            map.put ( "punish_law_document", pdf+"xianchangchuli/"+punish_law_document);
            map.put ( "punish_law_document_qr", qrcode+"xianchangchuli/"+punish_law_document_qr);
            map.put ( "rectify_law_document","" );
            map.put ( "rectify_law_document_qr","" );
            map.put ( "review_law_document", "");
            map.put ( "review_law_document_qr", "");
            map.put ( "risk_level",risk_level );
            map.put ( "risk_content" ,risk_content);
            map.put ( "checked_type", "");
            map.put ( "supervisor_id", "");
            map.put ( "risk_point" ,risk_point);
            map.put ( "unit_address", unit_address);
            map.put ( "ent_id", social_credit_code);
            map.put ( "inspect_type", inspect_type);
            map.put ( "department_id", region_id);
            String url="http://api.njyjgl.cn/spp_grid_dev/resource/place_inspecting/enforcement_nust";
            String res= HttpUtil.httpPost(url,map);
            return res;
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.info ( e.toString () );
            return  MsgUtils.errorMsg("网络繁忙，请稍后再试...");
        }
    }

    /**
     * 传送九小场所合格数据
     */
    public String putFireFightingDate(String recordId,String userId,String sCode) {
        String checked_end_time = "";
        String risk_level ="无隐患";
        String id ="";
        String social_credit_code ="";
        String checked_start_time ="";
        String ent_name ="";
        String checker_id ="";
        String checker ="";
        String checked_detail ="";
        String risk_point ="";
        String risk_content ="";
        String unit_address ="";
        String correction_detail ="";
        String inspect_type ="";
        String region_id ="";
        try {
            HashMap<String, String> map = new HashMap<>(31);
            List<Map> data = dataDao.putFireFightingTimeLimitDate(recordId);
            Date date=new Date();
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String today=df.format(date);
            if (data.get ( 0 ).get ("CREATE_TIME")!=null){
                id =  data.get ( 0 ).get ("CREATE_TIME").toString();
            }
            if (data.get ( 0 ).get ("CHECKED_UNIT")!=null){
                ent_name =  data.get ( 0 ).get ("CHECKED_UNIT").toString();
            }
            if (data.get ( 0 ).get ("UNIT_NUMBER")!=null){
                social_credit_code =  data.get ( 0 ).get ("UNIT_NUMBER").toString();
            }

            if (data.get ( 0 ).get ("CHECK_PEOPLE")!=null){
                checker =  data.get ( 0 ).get ("CHECK_PEOPLE").toString();
            }
            if (data.get ( 0 ).get ("CHECK_START_TIME")!=null){
                checked_start_time =  data.get ( 0 ).get ("CHECK_START_TIME").toString();
            }

            if (data.get ( 0 ).get ("CHECK_DESC")!=null){
                checked_detail =  data.get ( 0 ).get ("CHECK_DESC").toString();
            }
            if (data.get ( 0 ).get ("RISK_POINT")!=null){
                risk_point =  data.get ( 0 ).get ("RISK_POINT").toString();
            }
            if (data.get ( 0 ).get ("CHECK_ITEM")!=null){
                risk_content =  data.get ( 0 ).get ("CHECK_ITEM").toString();
            }
            if (data.get ( 0 ).get ("ADDRESS")!=null){
                unit_address =  data.get ( 0 ).get ("ADDRESS").toString();
            }
            List<Map> data3 = dataDao.putFireFightingTimeLimitDate3(recordId);
            if (data3.get ( 0 ).get ("INSPECT_TYPE")!=null){
                inspect_type =  data3.get ( 0 ).get ("INSPECT_TYPE").toString();
            }
            if (data3.get ( 0 ).get ("REGION_ID")!=null){
                region_id =  data3.get ( 0 ).get ("REGION_ID").toString();
            }
            map.put("id", UUID.randomUUID().toString());   //记得修改
            map.put("ent_name", ent_name);
            map.put("social_credit_code", sCode);
            map.put("checker_id", userId);
            map.put("checker", checker);
            map.put("checked_start_time", checked_start_time);
            map.put("checked_end_time", today);
            map.put("checked_detail", checked_detail);
            map.put("checked_status", "");
            map.put("checked_experts", "");
            map.put("checked_deps","");
            map.put ( "is_correction", "否");
            map.put ( "record_id",recordId );
            map.put("correction_detail",correction_detail);
            map.put ( "notice_law_document", "");
            map.put ( "notice_law_document_qr","" );
            map.put ( "record_law_document", "http://zfxc.njyjgl.cn/yjbd/"+data.get(0).get("CHECK_PDF").toString());
            map.put ( "record_law_document_qr","http://zfxc.njyjgl.cn/yjbd/"+data.get(0).get("QR_CODE").toString() );
            map.put ( "punish_law_document", "");
            map.put ( "punish_law_document_qr", "");
            map.put ( "rectify_law_document","" );
            map.put ( "rectify_law_document_qr","" );
            map.put ( "review_law_document", "");
            map.put ( "review_law_document_qr", "");
            map.put ( "risk_level",risk_level );
            map.put ( "risk_content" ,risk_content);
            map.put ( "checked_type", "");
            map.put ( "supervisor_id", "");
            map.put ( "risk_point" ,risk_point);
            map.put ( "unit_address", unit_address);
            map.put ( "ent_id", social_credit_code);
            map.put ( "inspect_type", inspect_type);
            map.put ( "department_id", region_id);
            String url="http://api.njyjgl.cn/spp_grid_dev/resource/place_inspecting/enforcement_nust";
            String res= HttpUtil.httpPost(url,map);
            return res;
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.info ( e.toString () );
            return  MsgUtils.errorMsg("网络繁忙，请稍后再试...");
        }
    }






    /**
     * 传送九小场所重大隐患数据
     */
    /*public String putFireHiddenDangerRecordDate(String recordId) {
        String checked_end_time = "";
        String risk_level ="重大隐患上报";
        String id ="";
        String social_credit_code ="";
        String checked_start_time ="";
        String ent_name ="";
        String checker_id ="";
        String checker ="";
        String checked_detail ="";
        String risk_point ="";
        String risk_content ="";
        String unit_address ="";
        String correction_detail ="";
        String inspect_type ="";
        String region_id ="";
        try {
            HashMap<String, String> map = new HashMap<>(31);
            List<Map> data = dataDao.putFireFightingTimeLimitDate(recordId);
            if (data.get ( 0 ).get ("ID")!=null){
                id =  data.get ( 0 ).get ("ID").toString();
            }
            if (data.get ( 0 ).get ("CHECKED_UNIT")!=null){
                ent_name =  data.get ( 0 ).get ("CHECKED_UNIT").toString();
            }
            if (data.get ( 0 ).get ("UNIT_NUMBER")!=null){
                social_credit_code =  data.get ( 0 ).get ("UNIT_NUMBER").toString();
            }
            if (data.get ( 0 ).get ("CARD_NUMBER")!=null){
                checker_id =  data.get ( 0 ).get ("CARD_NUMBER").toString();
            }
            if (data.get ( 0 ).get ("CHECK_PEOPLE")!=null){
                checker =  data.get ( 0 ).get ("CHECK_PEOPLE").toString();
            }
            if (data.get ( 0 ).get ("CHECK_START_TIME")!=null){
                checked_start_time =  data.get ( 0 ).get ("CHECK_START_TIME").toString();
            }
            if (data.get ( 0 ).get ("CREATE_TIME")!=null){
                checked_end_time =  data.get ( 0 ).get ("CREATE_TIME").toString();
            }
            if (data.get ( 0 ).get ("CHECK_DESC")!=null){
                checked_detail =  data.get ( 0 ).get ("CHECK_DESC").toString();
            }
            if (data.get ( 0 ).get ("RISK_POINT")!=null){
                risk_point =  data.get ( 0 ).get ("RISK_POINT").toString();
            }
//            if (data.get ( 0 ).get ("CHECK_ITEM")!=null){
//                risk_content =  data.get ( 0 ).get ("CHECK_ITEM").toString();
//            }
            if (data.get ( 0 ).get ("ADDRESS")!=null){
                unit_address =  data.get ( 0 ).get ("ADDRESS").toString();
            }
            List<Map> data1 = dataDao.putFireHiddenDangerRecordDate(recordId);
            if (data1.get ( 0 ).get ("PROCESS_DECISION")!=null){
                risk_content =  data1.get ( 0 ).get ("PROCESS_DECISION").toString();
            }
            List<Map> data3 = dataDao.putFireFightingTimeLimitDate3(recordId);
            if (data3.get ( 0 ).get ("INSPECT_TYPE")!=null){
                inspect_type =  data3.get ( 0 ).get ("INSPECT_TYPE").toString();
            }
            if (data3.get ( 0 ).get ("REGION_ID")!=null){
                region_id =  data3.get ( 0 ).get ("REGION_ID").toString();
            }
//            if (data1.get ( 0 ).get ("QRCODE_PATH")!=null){
//                punish_law_document_qr =  data1.get ( 0 ).get ("QRCODE_PATH").toString();
//            }
//            if (data1.get ( 0 ).get ("PROCESS_DECISION")!=null){
//                correction_detail =  data1.get ( 0 ).get ("PROCESS_DECISION").toString();
//            }
            map.put("id", id);   //记得修改
            map.put("ent_name", ent_name);
            map.put("social_credit_code", "");
            map.put("checker_id", checker_id);
            map.put("checker", checker);
            map.put("checked_start_time", checked_start_time);
            map.put("checked_end_time", checked_end_time);
            map.put("checked_detail", checked_detail);
            map.put("checked_status", "");
            map.put("checked_experts", "");
            map.put("checked_deps","");
            map.put ( "is_correction", "0");
            map.put ( "record_id",recordId );
            map.put("correction_detail",correction_detail);
            map.put ( "notice_law_document", "");
            map.put ( "notice_law_document_qr","" );
//            String pdf="http://zfxc.njyjgl.cn/yjbd/";
//            String qrcode="http://zfxc.njyjgl.cn/yjbd/";
            map.put ( "record_law_document", "http://zfxc.njyjgl.cn/yjbd/"+data.get(0).get("CHECK_PDF").toString());
            map.put ( "record_law_document_qr","http://zfxc.njyjgl.cn/yjbd/"+data.get(0).get("QR_CODE").toString() );
            map.put ( "punish_law_document", "");
            map.put ( "punish_law_document_qr", "");
            map.put ( "rectify_law_document","" );
            map.put ( "rectify_law_document_qr","" );
            map.put ( "review_law_document", "");
            map.put ( "review_law_document_qr", "");
            map.put ( "risk_level",risk_level );
            map.put ( "risk_content" ,risk_content);
            map.put ( "checked_type", "专业检查");
            map.put ( "supervisor_id", "网格巡查");
            map.put ( "risk_point" ,risk_point);
            map.put ( "unit_address", unit_address);
            map.put ( "ent_id", social_credit_code);
            map.put ( "inspect_type", inspect_type);
            map.put ( "department_id", region_id);
            String url="http://api.njyjgl.cn/spp_grid_dev/resource/place_inspecting/enforcement_nust";
            String res= HttpUtil.httpPost(url,map);
            return res;
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.info ( e.toString () );
            return  MsgUtils.errorMsg("网络繁忙，请稍后再试...");
        }
    }*/
}
