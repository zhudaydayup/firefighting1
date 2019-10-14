package com.anjian.jiuxiao.controller;

import com.alibaba.fastjson.JSONObject;
import com.anjian.jiuxiao.service.DataService;
import com.anjian.jiuxiao.service.FireFighting.FireIndexService;
import com.anjian.jiuxiao.util.HttpUtil;
import com.anjian.jiuxiao.util.MsgUtils;
import com.anjian.jiuxiao.util.PostUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Totle:DataController
 * @ProjectName:yjbawechat
 * @author:社会码农
 * @data:2019/4/117:58
 */
@RestController
@RequestMapping(value = "/data/")
public class DataController {

    @Autowired
    private DataService dataService;
    @Autowired
    FireIndexService fireIndexService;

    /**
     * 获取企业信息
     * @param request
     * @return
     */
    @RequestMapping(value = "getCompanyMsg")
    public String getCompanyMsg(HttpServletRequest request , HashMap<String, String> map ) {
        try {
            String ent_id = request.getParameter("social_credit_code");
            HashMap<String,String> map1=new HashMap<> (  );

            map1.put ( "query",ent_id );
            map1.put ( "field","pkid" );

            List<Map<String,String> > list = new ArrayList<> (  );
            list.add ( map1 );
            map.put ( "must" , JSONObject.toJSONString(list) );
            String data="{\"must\":"+"[{\"field\":\"pkid\",\"query\":"+"\""+ent_id+"\""+"}]}";

            String url = "http://222.190.243.160/api/resource/query/unit_places_temp/1";
            String res= PostUtils.httpPost(url,data);
            return res;
        }catch (Exception e){
            e.printStackTrace ();
            return  MsgUtils.errorMsg("网络繁忙，请稍后再试...");
        }

    }

    /**
     * 获取执法通知书检查中的安检员信息
     */
    @RequestMapping(value = "getCheckerByUserId")
    public String getCheckerByUserId(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        return HttpUtil.sendGet(" http://api.njyjgl.cn/spp_grid_dev/security/staff/person/" + userId );

    }

    /**
     * 传送九小场所限期改正数据
     */
    @RequestMapping(value = "putFireFightingTimeLimitDate")
    public String putFireFightingTimeLimitDate(HttpServletRequest request) {
        String recordId = request.getParameter("RecordId");
        String userId = request.getParameter("userId");
        String sCode = request.getParameter("sCode");
        return dataService.putFireFightingTimeLimitDate(recordId,userId,sCode);
    }
    /**
     * 传送九小场所立即改正数据
     */
    @RequestMapping(value = "putTimeRectificationDate")
    public String putTimeRectificationDate(HttpServletRequest request) {
        String recordId = request.getParameter("RecordId");
        String userId = request.getParameter("userId");
        String sCode = request.getParameter("sCode");
        return dataService.putTimeRectificationDate(recordId,userId,sCode);
    }
    /**
     * 传送九小场所合格数据
     */
    @RequestMapping(value = "putFireFightingDate")
    @ResponseBody
    public Map<String, Object> putFireFightingDate(HttpServletRequest request) {
        String recordId = request.getParameter("RecordId");
        String userId = request.getParameter("userId");
        String sCode = request.getParameter("sCode");
        String checkItem = fireIndexService.getCheckItem(recordId);
        Map<String,Object> map = new HashMap<>(5);
        map.put("checkItem",checkItem);
        map.put("msg",dataService.putFireFightingDate(recordId,userId,sCode));
        return map;
    }
    /**
     * 传送九小场所重大隐患数据
     */
   /* @RequestMapping(value = "putFireHiddenDangerRecordDate")
    public String putFireHiddenDangerRecordDate(HttpServletRequest request) {
        String recordId = request.getParameter("RecordId");
        return dataService.putFireHiddenDangerRecordDate(recordId);
    }*/
}
