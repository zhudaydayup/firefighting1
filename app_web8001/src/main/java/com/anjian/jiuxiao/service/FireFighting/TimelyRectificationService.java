package com.anjian.jiuxiao.service.FireFighting;

import com.alibaba.fastjson.JSON;
import com.anjian.jiuxiao.dao.FireFighting.FireIndexDao;
import com.anjian.jiuxiao.dao.FireFighting.TimelyRectificationDao;
import com.anjian.jiuxiao.util.MsgUtil;
import com.anjian.jiuxiao.util.MsgUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


/**
 * @Author: 随心的小新
 * @Date: 2019/4/25 20:31
 */
@Service
public class TimelyRectificationService {

    @Autowired
    TimelyRectificationDao timelyRectificationDao;
    @Autowired
    FireIndexDao fireIndexDao;
    static final org.slf4j.Logger logger = LoggerFactory.getLogger(TimelyRectificationService.class);

    /**
     * 根据ID来查询数据
     * @param
     * @return
     */
//    public List<PunishMeasureEntity> getMessageById(String RECORD_ID){
//        return timelyRectificationDao.getMessageById(RECORD_ID);
//    }
    public String getMessageById(String RECORD_ID){
        String responseText = "";
        try {
            List<Map> mapList = timelyRectificationDao.getMessageById(RECORD_ID);
            responseText = JSON.toJSONString ( mapList);
            responseText =  "[" + responseText + "]";
        }catch (Exception e){
            e.printStackTrace ();
            logger.info ( e.toString () );
            e.printStackTrace ();

        }

        return responseText;
    }
    /**
     * 建立现场检查记录表
     */
    public void setInspectionRecord(String PROCESS_DECISION,String CHECKE_DETAIL,String CHECKE_UNIT,String CHECKED_START_TIME,String CHECK_UNIT,String RECORD_ID){
        int count = 0;
        Calendar now = Calendar.getInstance();
        int month = now.get(Calendar.MONTH)+1;
        String RECORD_TIME = now.get(Calendar.YEAR)+"年"+month+"月"+now.get(Calendar.DAY_OF_MONTH)+"日";
        try {
            count = timelyRectificationDao.getInspectionRecordCount(RECORD_ID);
            if (count>0) {
                timelyRectificationDao.updateInspectionRecord(PROCESS_DECISION,CHECKE_DETAIL,CHECKE_UNIT,CHECKED_START_TIME,RECORD_TIME,CHECK_UNIT,RECORD_ID);
            }else {
                timelyRectificationDao.setInspectionRecord(PROCESS_DECISION,CHECKE_DETAIL,CHECKE_UNIT,CHECKED_START_TIME,RECORD_TIME,CHECK_UNIT,RECORD_ID);
            }
        } catch (Exception e) {
            e.printStackTrace ();
            logger.info ( e.toString () );
        }

    }
    /**
     * 九小场所重大隐患上报记录
     */
    public void setFireHiddenDangerRecord(String RECORD_ID,String PROCESS_DECISION,String Risk_Level,String CHECKE_UNIT,String CHECKED_START_TIME){
        Calendar now = Calendar.getInstance();
        int month = now.get(Calendar.MONTH)+1;
        String RECORD_TIME = now.get(Calendar.YEAR)+"年"+month+"月"+now.get(Calendar.DAY_OF_MONTH)+"日";
        timelyRectificationDao.setFireHiddenDangerRecord(RECORD_ID,PROCESS_DECISION,Risk_Level,CHECKE_UNIT,CHECKED_START_TIME,RECORD_TIME);
    }
    /**
     * 上传执法者签名
     */
    public String UpdateCheckSignature(String CHECKE_SIGNATURE, String RecordId) {
        String responseText="";
        int flag = timelyRectificationDao.UpdateCheckSignature(CHECKE_SIGNATURE, RecordId);
        if (flag > 0) {
            responseText = MsgUtils.successMsg();
        }
        else {
            responseText = MsgUtils.errorMsg("网络繁忙，请稍后再试...");
        }
        return responseText;
    }
    /**
     * 上传被执法单位签名
     */
    public String UpdateRepresentSignature(String REPRESENR_SIGNATURE, String RecordId) {
        String responseText="";
        int flag = timelyRectificationDao.UpdateRepresentSignature(REPRESENR_SIGNATURE, RecordId);
        if (flag > 0) {
            responseText = MsgUtils.successMsg();
        }
        else {
            responseText = MsgUtils.errorMsg("网络繁忙，请稍后再试...");
        }
        return responseText;
    }
    /**
     * 最后生成PDF页面以及二维码图片
     */
    public String LookUpPdfById(String recordId) {
        String responseText="";
        List<Map> list=timelyRectificationDao.LookUpPdfById(recordId);
        responseText = MsgUtils.successMsg("RecordInfo", list);
        return responseText;

    }
    /**
     * 获取PDF页面
     * @param
     * @return
     * @throws IOException
     */
    public String createDocumentPdf(String RECORD_ID,String ID) throws IOException {
        Document document = new Document( PageSize.A4);
        String pdfPath="D:/zfbdzl/pdf/xianchangchuli/";
        String qrCodePath="D:/zfbdzl/qrcode/xianchangchuli/";
        String url="D:/zfbdzl/images/";
        String pdfAbsolutePath="http://zfxc.njyjgl.cn/yjbd/pdf/getPdf?url=xianchangchuli/";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date1=sdf.format(new Date());
        int random=(int)(Math.random()*10000);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        // new Date()为获取当前系统时间
        String nowTime=df.format(new Date());
        String pdfName = date1+"/"+nowTime+ random+".pdf";//包含时间的文件夹
        //设置日期格式
        List<Map> List=timelyRectificationDao.getPdfInfo(RECORD_ID);
        //从表格中拉取证件号信息
        List<Map>mapList=timelyRectificationDao.searchCareId(RECORD_ID);
        String UPID="";//自增id
        String AREA="";//检查单位所属辖区
        String CARD_NUMBER1="";//证件号1
        String CARD_NUMBER2 ="";//证件号2
        String CHECKE_UNIT="";
        String CHECK_START_TIME="";
        String CHECKE_DETAIL="";
        String PROCESS_DECISION="";
        String CHECKE_SIGH="";
        String REPRESENR_SIGN="";
        String SIGNATURE2_PATH="";
        String SIGNATURE3_PATH="";
        String RECORD_TIME="";
        String CHECK_UNIT = "";
        String CHECK_PEOPLE = "";

        if(mapList.get(0).get("CHECK_UNIT")!=null){
            CHECK_UNIT = mapList.get(0).get("CHECK_UNIT").toString ();
            if(CHECK_UNIT.contains("__")){
                CHECK_UNIT = CHECK_UNIT.split("__")[0];
            }
        }

        String qu = "";
        String street = "";
        String id=timelyRectificationDao.getCheckUnitTable(CHECK_UNIT,RECORD_ID);
//        List<Map> listFire = fireIndexDao.getCheckUnitPro1(RECORD_ID);
//        id = listFire.get ( 0 ).get ( "ID" ).toString ();
        if(CHECK_UNIT.contains("区")){
            street = CHECK_UNIT.replaceAll("[\\S]+政府", "").substring(0,2);
            if(CHECK_UNIT.substring(0,1).contains("江")){
                qu = CHECK_UNIT.substring(0,2);
            } else {
                qu = CHECK_UNIT.substring(0,1);
            }
            CHECK_UNIT = "南京市"+ CHECK_UNIT;
        }else {
            CHECK_UNIT = "南京市应急管理局";
        }
        if(mapList.get(0).get("CHECK_PEOPLE")!=null)  CHECK_PEOPLE = mapList.get(0).get("CHECK_PEOPLE").toString ();
        if(List.get(0).get("RECORD_ID")!=null)  UPID = ID;
//        if(mapList.get(0).get("AREA")!=null)  AREA = mapList.get(0).get("AREA").toString ();
        if(mapList.get(0).get("CARD_NUMBER")!=null)  CARD_NUMBER1 = mapList.get(0).get("CARD_NUMBER").toString ();
        if(mapList.get(0).get("CARD_NUMBER2")!=null)  CARD_NUMBER2 = mapList.get(0).get("CARD_NUMBER2").toString ();
        if(mapList.get(0).get("CHECK_START_TIME")!=null)  CHECK_START_TIME = mapList.get(0).get("CHECK_START_TIME").toString ();
        if(List.get(0).get("CHECKE_UNIT")!=null) CHECKE_UNIT = List.get(0).get("CHECKE_UNIT").toString ();
        if(List.get(0).get("CHECKE_DETAIL")!=null)  CHECKE_DETAIL = List.get(0).get("CHECKE_DETAIL").toString ();
        if(List.get(0).get("PROCESS_DECISION")!=null)  PROCESS_DECISION = List.get(0).get("PROCESS_DECISION").toString ();
        if(List.get(0).get("CHECKE_SIGH")!=null)  CHECKE_SIGH = List.get(0).get("CHECKE_SIGH").toString ();
        if(List.get(0).get("REPRESENR_SIGN")!=null)  REPRESENR_SIGN = List.get(0).get("REPRESENR_SIGN").toString ();
//        if(List.get(0).get("SIGNATURE2_PATH")!=null)  SIGNATURE2_PATH = List.get(0).get("SIGNATURE2_PATH").toString ();
//        if(List.get(0).get("SIGNATURE3_PATH")!=null)  SIGNATURE3_PATH = List.get(0).get("SIGNATURE3_PATH").toString ();
        if(List.get(0).get("RECORD_TIME")!=null)  RECORD_TIME = List.get(0).get("RECORD_TIME").toString ();
        String[] SIGNATURE_PATH_ARRAY= CHECKE_SIGH.split("\\|");
        String[] CARD_NUMBER1_ARRAY= CARD_NUMBER1.split("\\__");
        String[] SIGNATURE2_PATH_ARRAY= REPRESENR_SIGN.split("\\|");
//        String[] SIGNATURE3_PATH_ARRAY= SIGNATURE3_PATH.split("\\|");
        String[] CHECKE_DETAIL_ARRAY1=CHECKE_DETAIL.split("\\|");

        try {
            File file1 =new File(pdfPath+date1);
            if(!file1 .exists()  && !file1 .isDirectory()){
                file1 .mkdir();
            }
            File file2 =new File(qrCodePath+date1);
            if(!file2 .exists()  && !file2 .isDirectory()){
                file2 .mkdir();
            }
            PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream(pdfPath+pdfName));
            BaseFont bfChinese= BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            //加入document：
            Font titleFont = new Font(bfChinese, 20, Font.BOLD, BaseColor.BLACK);
            Font para = new Font(bfChinese, 12, Font.NORMAL, BaseColor.BLACK);
            Font lineFont=new Font(bfChinese,2, Font.NORMAL, BaseColor.BLACK);
            Paragraph paragraph;
            float leading=20f;
            //补全下划线至行尾
            LineSeparator lineSeparator=new LineSeparator();
            lineSeparator.setOffset(-1f);
            lineSeparator.setLineWidth(0.1F);
            Chunk chunk;
            document.addTitle("example of PDF");
            document.open();
            //主标题：安全生产行政执法文书现场检查记录
            String Temp = "";
            if (CHECK_UNIT.contains("江北")) {
                Temp = "江北";
            }
            if (CHECK_UNIT.contains("玄武")) {
                Temp = "玄武";
            }
            if (CHECK_UNIT.contains("秦淮")) {
                Temp = "秦淮";
            }
            if (CHECK_UNIT.contains("建邺")) {
                Temp = "建邺";
            }
            if (CHECK_UNIT.contains("鼓楼")) {
                Temp = "鼓楼";
            }
            if (CHECK_UNIT.contains("栖霞")) {
                Temp = "栖霞";
            }
            if (CHECK_UNIT.contains("经济开发")) {
                Temp = "经济开发";
            }
            if (CHECK_UNIT.contains("雨花台")) {
                Temp = "雨花台";
            }
            if (CHECK_UNIT.contains("江宁")) {
                Temp = "江宁";
            }
            if (CHECK_UNIT.contains("浦口")) {
                Temp = "浦口";
            }
            if (CHECK_UNIT.contains("六合")) {
                Temp = "六合";
            }
            if (CHECK_UNIT.contains("溧水")) {
                Temp = "溧水";
            }
            if (CHECK_UNIT.contains("高淳")) {
                Temp = "高淳";
            }
            Paragraph title = new Paragraph("安全生产监督管理立即整改通知书",titleFont);
            title.setAlignment( Element.ALIGN_CENTER);
            document.add(title);





            //应急检字
            Paragraph subTitle2=new Paragraph("宁"+qu+"应急（"+qu+street+"）即字〔2019〕"+id+"号",para);
            subTitle2.setAlignment( Element.ALIGN_CENTER);
            document.add(subTitle2);
            //设置行距，该处会影响后面所有的chunk的行距
            paragraph=new Paragraph("",para);
            paragraph.setLeading(25f);
            document.add(paragraph);

            //被检查单位
            chunk = new Chunk("    "+CHECKE_UNIT+"："+"",para);
            chunk.setUnderline(0.1f, -1f);
            document.add(chunk);
//            document.add(new Chunk(lineSeparator));
            document.add( Chunk.NEWLINE);


            String []time=CHECK_START_TIME.split("\\-");
            //时间
            chunk = new Chunk("         根据国务院《消防安全责任制实施办法》第9条第1款第6项、《南京市安全生产条例》第7条第2款、第43条的规定，我单位工作人员",para);
            document.add(chunk);
            if(CHECK_PEOPLE.contains("__")){
                String[] str1 = CHECK_PEOPLE.split("__");
                chunk = new Chunk(str1[0],para);
                document.add(chunk);
                for (int i = 1; i < str1.length; i++) {
                    chunk = new Chunk("、"+str1[i],para);
                    document.add(chunk);
                }

            }else {
                chunk = new Chunk(CHECK_PEOPLE,para);
                document.add(chunk);
            }

            chunk.setUnderline(0.1f, -1f);
            chunk = new Chunk("于",para);
            document.add(chunk);
            chunk = new Chunk(time[0],para);
            chunk.setUnderline(0.1f, -1f);
            document.add(chunk);

            chunk = new Chunk("年",para);
            document.add(chunk);

            chunk = new Chunk(time[1],para);
            chunk.setUnderline(0.1f, -1f);

            document.add(chunk);
            chunk = new Chunk("月",para);
            document.add(chunk);
            String []time1=time[2].split ( "\\ " );
            chunk = new Chunk(time1[0],para);
            chunk.setUnderline(0.1f, -1f);
            document.add(chunk);
            chunk = new Chunk("日",para);
            document.add(chunk);
            chunk = new Chunk("对你单位进行安全检查。",para);
            document.add(chunk);
            document.add( Chunk.NEWLINE);

            chunk = new Chunk("        一、经查，你单位存在下列问题：",para);
            document.add(chunk);
            document.add( Chunk.NEWLINE);


            //违法违规行为
//            for(int i=1;i<2;i++){
//                Chunk checkUnit3 = new Chunk(""+i+""+CHECKE_DETAIL,para);
//                checkUnit3.setUnderline(0.1f, -1f);
//                document.add(checkUnit3);
//                document.add(Chunk.NEWLINE);
//            }

            for(int i=0;i<CHECKE_DETAIL_ARRAY1.length;i++){
                Chunk problem = new Chunk( "         "+(i+1)+"、" ,para);
                document.add(problem);
                chunk = new Chunk( CHECKE_DETAIL_ARRAY1[i],para );
                document.add(chunk);
//                document.add(new Chunk(lineSeparator));
                document.add( Chunk.NEWLINE);
            }
//            Chunk fy = new Chunk("         （此栏不够，可另附页）",para);
////            document.add(fy);
////            document.add(Chunk.NEWLINE);


            //处理决定
            if(PROCESS_DECISION!=null && !PROCESS_DECISION.equals ( "" )){
                chunk = new Chunk("        二、整改期限及整改要求：",para);
                document.add(chunk);
                document.add( Chunk.NEWLINE);
                Chunk chunk5 = new Chunk("         以上问题请你单位立即整改完毕，并达到有关法律法规和标准规定的要求。",para);
                document.add(chunk5);
                document.add( Chunk.NEWLINE);
            }





            chunk = new Chunk("        三、法律后果：",para);
            document.add(chunk);
            document.add( Chunk.NEWLINE);
            chunk = new Chunk("        整改达不到要求的，依法上报或已送相关部门给予行政处罚；由此造成事故的，依法追究有关人员的责任。",para);
            document.add(chunk);
            document.add( Chunk.NEWLINE);


            PdfContentByte sign = writer.getDirectContent();
            BaseFont bf= BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            if(CHECKE_SIGH!=null && !CHECKE_SIGH.equals ( "" )){
                sign.beginText();
                sign.setFontAndSize(bf, 12);
                sign.showTextAligned( PdfContentByte.ALIGN_LEFT,  "   检查人:", 25f, 320f, 0);
                sign.endText();
                //安全生产监管行政执法人员(签名)图片
                for(int i=0;i<SIGNATURE_PATH_ARRAY.length;i++){
                    Image image = Image.getInstance(url+SIGNATURE_PATH_ARRAY[i]);
                    image.setAbsolutePosition(230f, 300f-40*i);
                    image.scaleAbsolute(100, 50);
                    document.add(image);
                }
                //被检查单位负责人
                PdfContentByte sign2 = writer.getDirectContent();
                BaseFont bf6= BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
                sign2.beginText();
                sign2.setFontAndSize(bf6, 12);
                sign2.showTextAligned( PdfContentByte.ALIGN_LEFT,  "   被检查单位：", 25f, 220f, 0);
                sign2.endText();

                for(int i=0;i<SIGNATURE2_PATH_ARRAY.length;i++) {
                    Image image = Image.getInstance ( url + SIGNATURE2_PATH_ARRAY[i] );
                    image.setAbsolutePosition ( 230f, 200f - 40 * i );
                    image.scaleAbsolute ( 100, 50 );
                    document.add ( image );
                }
            }




            //印章
            PdfContentByte cb = writer.getDirectContent();
            cb = writer.getDirectContent();
            bf= BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
//            cb.beginText();
//            cb.setFontAndSize(bf, 12);
//            cb.showTextAligned(PdfContentByte.ALIGN_LEFT,  "", 320f, 90f, 0);
//            cb.endText();
//
//            if(AREA.equals("宁")){
//                Image image = Image.getInstance ( "C:/zfbdmoban/images/zz.png" );
//                image.setAbsolutePosition ( 400f, 40f );
//                image.scaleAbsolute ( 140, 140 );
//                document.add ( image );
//            }



            //检查部门
            cb = writer.getDirectContent();
            bf= BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            cb.beginText();
            cb.setFontAndSize(bf, 12);
            cb.showTextAligned( PdfContentByte.ALIGN_RIGHT,   CHECK_UNIT, 550f, 85f, 0);
            cb.endText();

            //创建时间
            cb = writer.getDirectContent();
            bf= BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            cb.beginText();
            cb.setFontAndSize(bf, 12);
            Date date=new Date();
            SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
            String now=df1.format(date);
            String[] split = now.split("-");
            cb.showTextAligned( PdfContentByte.ALIGN_RIGHT,  split[0]+"年"+split[1]+"月"+split[2]+"日", 550f, 60f, 0);
            cb.endText();


            //预览二维码

            String content=pdfAbsolutePath+pdfName;
            String qrCodeName=date1+"/"+nowTime+ random+".png";
            try{
                Map<EncodeHintType, Object> hints = new HashMap<>();
                //编码
                hints.put( EncodeHintType.CHARACTER_SET, "UTF-8");
                //边框距
                hints.put( EncodeHintType.MARGIN, 0);
                QRCodeWriter qrCodeWriter = new QRCodeWriter ();
                BitMatrix bm = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 197, 197, hints);
                Path file=new File(qrCodePath+qrCodeName).toPath();
                MatrixToImageWriter.writeToPath(bm, "png", file);

            }catch (Exception e){
                System.out.print(e);
                logger.info ( e.toString () );
                return MsgUtil.errorMsg(e.toString());
            }
            timelyRectificationDao.updatePdfAndQrcode(pdfName,qrCodeName,RECORD_ID);


        } catch (DocumentException e) {
            e.printStackTrace();
            logger.info ( e.toString () );
            return MsgUtil.errorMsg(e.toString());
        } finally {
            document.close();
        }
        return MsgUtil.successMsg();
    }
    public static PdfPCell getPDFCell(String string, Font font, int HorizontalAlignment, int VerticalAlignment)
    {
        //创建单元格对象，将内容与字体放入段落中作为单元格内容
        PdfPCell cell=new PdfPCell(new Paragraph(string,font));

        cell.setHorizontalAlignment(HorizontalAlignment);
        cell.setVerticalAlignment(VerticalAlignment);

        //设置最小单元格高度
        //cell.setMinimumHeight(25);
        return cell;
    }
}
