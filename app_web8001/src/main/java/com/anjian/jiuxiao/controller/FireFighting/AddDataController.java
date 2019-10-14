package com.anjian.jiuxiao.controller.FireFighting;

import com.anjian.jiuxiao.service.FireFighting.FireIndexService;
import com.anjian.jiuxiao.util.CreatePdfUtil;
import com.anjian.jiuxiao.util.FirePdfUtil;
import com.anjian.jiuxiao.util.ResultVO;
import com.anjian.jiuxiao.util.StreetMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/addData")
public class AddDataController {
    @Autowired
    private FireIndexService fireIndexService;
    @RequestMapping("/insertCheckItem")
    @ResponseBody
    public Map<String,Object> insetCheckItem(HttpServletRequest request) throws IOException{
        String CHECKED_UNIT = request.getParameter("CHECKED_UNIT");
        String UNIT_NUMBER=request.getParameter("UNIT_NUMBER");
        String ADDRESS=request.getParameter("ADDRESS");
        String REPRESENT_PEOPLE=request.getParameter("REPRESENT_PEOPLE");
        String REPRESENT_MOBILE=request.getParameter("REPRESENT_MOBILE");
        String RISK_POINT=request.getParameter("RISK_POINT");
        String CHECK_START_TIME=request.getParameter("CHECK_START_TIME");
        String CHECK_PEOPLE=request.getParameter("CHECK_PEOPLE");
        String CARD_NUMBER=request.getParameter("CARD_NUMBER");
        String CHECK_UNIT=request.getParameter("CHECK_UNIT");
        String CHECK_ITEM=request.getParameter("CHECK_ITEM");
        String CHECK_DESC=request.getParameter("CHECK_DESC");
        String CHECK_IMG=request.getParameter("CHECK_IMG");
        String CHECK_VIDEO=request.getParameter("CHECK_VIDEO");
        String INSPECT_TYPE=request.getParameter("inspect_type");
        String REGION_ID=request.getParameter("department_id");
        Date date=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String CREATE_TIME=df.format(date)+new Random().nextInt(1000);
        fireIndexService.insetCheckItem(CHECKED_UNIT,UNIT_NUMBER,ADDRESS,REPRESENT_PEOPLE,REPRESENT_MOBILE,RISK_POINT,CHECK_START_TIME,CHECK_PEOPLE,CARD_NUMBER,CHECK_UNIT,CHECK_ITEM,CHECK_DESC,CHECK_IMG,CHECK_VIDEO,CREATE_TIME,INSPECT_TYPE,REGION_ID);
        String id = fireIndexService.getCheckUnitTable(CHECK_UNIT,CREATE_TIME);
        String fightingPdf = FirePdfUtil.createPdfNoSign("fightingPdf", CHECKED_UNIT, CHECK_UNIT, CHECK_PEOPLE, CHECK_START_TIME, CHECK_ITEM, CHECK_DESC, CHECK_IMG,CHECK_UNIT,id);
        fireIndexService.updateCheckPdf(fightingPdf,"暂无",CREATE_TIME);
        Map<String,Object> map = new HashMap<>(16);
        map.put("date",CREATE_TIME);
        map.put("pdfUrl",fightingPdf);
        return map;
    }

    //生成pdf
    @RequestMapping("/createPdf")
    @ResponseBody
    public Map<String,Object> createPdf(HttpServletRequest request) throws IOException{
        String CREATE_TIME=request.getParameter("RecordId");
        List<Map> list=fireIndexService.getCheckInfo(CREATE_TIME);
        String ID = "";
        if(list.get(0).get("ID")!=null){
            ID=list.get(0).get("ID").toString();
        }
        String CHECKED_UNIT = "";
        if(list.get(0).get("CHECKED_UNIT")!=null){
            CHECKED_UNIT=list.get(0).get("CHECKED_UNIT").toString();
        }
        String CHECK_UNIT = "";
        if(list.get(0).get("CHECK_UNIT")!=null){
            CHECK_UNIT=list.get(0).get("CHECK_UNIT").toString();
        }
        String CHECK_PEOPLE = "";
        if(list.get(0).get("CHECK_PEOPLE")!=null){
            CHECK_PEOPLE=list.get(0).get("CHECK_PEOPLE").toString();
        }
        String CHECK_START_TIME = "";
        if(list.get(0).get("CHECK_START_TIME")!=null){
            CHECK_START_TIME=list.get(0).get("CHECK_START_TIME").toString();
        }
        String CHECK_ITEM = "";
        if(list.get(0).get("CHECK_ITEM")!=null){
            CHECK_ITEM=list.get(0).get("CHECK_ITEM").toString();
        }
        String CHECK_DESC = "";
        if(list.get(0).get("CHECK_DESC")!=null){
            CHECK_DESC=list.get(0).get("CHECK_DESC").toString();
        }
        String CHECKMAN_SIGN = "";
        if(list.get(0).get("CHECKMAN_SIGN")!=null){
            CHECKMAN_SIGN=list.get(0).get("CHECKMAN_SIGN").toString();
        }
        String CHECKEDMAN_SIGN = "";
        if(list.get(0).get("CHECKEDMAN_SIGN")!=null){
            CHECKEDMAN_SIGN=list.get(0).get("CHECKEDMAN_SIGN").toString();
        }
        String CHECK_IMG = "";
        if(list.get(0).get("CHECK_IMG")!=null){
            CHECK_IMG=list.get(0).get("CHECK_IMG").toString();
        }
        Map<String,Object> map = new HashMap<>(16);

        String ID1 = fireIndexService.getCheckUnitTable(CHECK_UNIT,CREATE_TIME);
        String pathAB = CreatePdfUtil.createPdf("fightingPdf",ID1,CHECKED_UNIT,CHECK_UNIT,CHECK_PEOPLE,CHECK_START_TIME,CHECK_ITEM,CHECK_DESC,CHECKMAN_SIGN,CHECKEDMAN_SIGN,CHECK_IMG,CHECK_UNIT);
        String[] arr = pathAB.split("_");
        ResultVO resultVO = fireIndexService.updateCheckPdf(arr[0],arr[1],CREATE_TIME);
        map.put("msg",resultVO.getMsg());
        map.put("ID",ID);
        map.put("url",pathAB);
        return map;
    }
    @RequestMapping("/getStreetMsg")
    @ResponseBody
    public Map<String,Object> getStreetMsg(HttpServletRequest request){
        String region_code = request.getParameter("region_code");
        String streetMsg = StreetMsg.getStreetMsg(region_code);
        Map<String,Object> map2 = new HashMap<>(5);
        map2.put("streetMsg",streetMsg);
        return map2;
    }
}
