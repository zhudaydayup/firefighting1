package com.anjian.jiuxiao.util;

import com.anjian.jiuxiao.my_enum.PathEnum;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


public class CreatePdfUtil {
    static final Logger logger = LoggerFactory.getLogger(CreatePdfUtil.class);

    public static String createPdf(String folderName,String ID,String CHECKED_UNIT,String CHECKE_UNIT,String CHECK_PEOPLE,String CHECK_START_TIME,String CHECK_ITEM,String CHECK_DESC,String CHECKMAN_SIGN,String CHECKEDMAN_SIGN,String CHECK_IMG,String unit){
        String qu = "";
        String street = "";
        if(unit.contains("区")){
            street = unit.replaceAll("[\\S]+政府", "").substring(0,2);
            if(unit.substring(0,1).contains("江")){
                qu = unit.substring(0,2);
            } else {
                qu = unit.substring(0,1);
            }
            unit = "南京市"+ unit;
        }else {
            unit = "南京市应急管理局";
        }
        Document document = new Document( PageSize.A4);
        //创建书写器将文档写入指定磁盘
        String path = PathEnum.PDF_PATH.getPath();
        //根据时间戳创建新的文件名
        String fileName = System.currentTimeMillis() + new Random().nextInt(1000) + ".pdf";
        //创建文件路径
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String createTime = sdf.format(new Date());
        String filePath = path + "/" + folderName+ "/" +createTime;
        String url = filePath+ "/" +fileName;
        String pdfAbsolutePath="http://zfxc.njyjgl.cn/yjbd/pdf/getPdf?url="+folderName+"/"+createTime+"/"+fileName;
        try {
            File folder = new File(filePath);
            //判断文件夹是否存在，如不存在，创建
            if(!folder.exists()){
                folder.mkdirs();
            }
            BaseFont bfChinese= BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font titleFont = new Font(bfChinese, 20, Font.BOLD, BaseColor.BLACK);//加入document：
            Font subTitleFont = new Font(bfChinese, 16, Font.NORMAL, BaseColor.BLACK);
            Font para = new Font(bfChinese, 12, Font.NORMAL, BaseColor.BLACK);
            Font lineFont=new Font(bfChinese,2, Font.NORMAL, BaseColor.BLACK);
            PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(url));
            document.open();
            String Temp = "";
            if (CHECKE_UNIT.contains("江北")) {
                Temp = "江北";
            }
            if (CHECKE_UNIT.contains("玄武")) {
                Temp = "玄武";
            }
            if (CHECKE_UNIT.contains("秦淮")) {
                Temp = "秦淮";
            }
            if (CHECKE_UNIT.contains("建邺")) {
                Temp = "建邺";
            }
            if (CHECKE_UNIT.contains("鼓楼")) {
                Temp = "鼓楼";
            }
            if (CHECKE_UNIT.contains("栖霞")) {
                Temp = "栖霞";
            }
            if (CHECKE_UNIT.contains("经济开发")) {
                Temp = "经济开发";
            }
            if (CHECKE_UNIT.contains("雨花台")) {
                Temp = "雨花台";
            }
            if (CHECKE_UNIT.contains("江宁")) {
                Temp = "江宁";
            }
            if (CHECKE_UNIT.contains("浦口")) {
                Temp = "浦口";
            }
            if (CHECKE_UNIT.contains("六合")) {
                Temp = "六合";
            }
            if (CHECKE_UNIT.contains("溧水")) {
                Temp = "溧水";
            }
            if (CHECKE_UNIT.contains("高淳")) {
                Temp = "高淳";
            }
            Paragraph title = new Paragraph("",titleFont);
            title.setAlignment( Element.ALIGN_CENTER);
            Chapter chapter1 = new Chapter(title,1);
            chapter1.setNumberDepth(0);
            Paragraph subTitle = new Paragraph("安全生产监督管理检查通知书",subTitleFont);
            subTitle.setLeading(25f);
            subTitle.setAlignment( Element.ALIGN_CENTER);
            chapter1.add(subTitle);

            Paragraph number = new Paragraph("宁"+qu+"应急（"+qu+street+"）检通字〔2019〕"+ID+"号  ",para);
            number.setLeading(25f);
            number.setAlignment( Element.ALIGN_CENTER);
            chapter1.add(number);
            document.add(chapter1);

            Paragraph checkedUnit = new Paragraph( );
            Chunk chunk = new Chunk(""+CHECKED_UNIT+":",para);
            chunk.setUnderline(0.1f, -1f);
            checkedUnit.add (  chunk );
            checkedUnit.setLeading(25f);
            document.add(checkedUnit);


            Paragraph content = new Paragraph();
            content.setLeading(25f);
            String check = "";
            if(CHECK_PEOPLE.contains("__")){
                String[] arr=CHECK_PEOPLE.split("__");
                for (int i=0;i<arr.length-1;i++){
                    check += arr[i]+"，";
                }
                check += arr[arr.length-1];
            }
            else {
                check = CHECK_PEOPLE;
            }
            Chunk text = new Chunk("        "+"根据国务院《消防安全责任制实施办法》第9条第1款第6项、《南京市安全生产条例》第7条第2款、第43条的规定，我单位工作人员",para);
            content.add(text);
            text = new Chunk(check,para);
            text.setUnderline(0.1f, -1f);
            content.add(text);
            String[] time = CHECK_START_TIME.split("-");
            String day = time[2].split(" ")[0];
            text = new Chunk("于",para);
            content.add(text);
            text = new Chunk(time[0],para);
            text.setUnderline(0.1f, -1f);
            content.add(text);
            text = new Chunk("年",para);
            content.add(text);
            text = new Chunk(time[1],para);
            text.setUnderline(0.1f, -1f);
            content.add(text);
            text = new Chunk("月",para);
            content.add(text);
            text = new Chunk(day,para);
            text.setUnderline(0.1f, -1f);
            content.add(text);
            text = new Chunk("日对你单位进行安全检查。",para);
            content.add(text);
            document.add(content);
            if(CHECK_ITEM!=null&&CHECK_ITEM!=""){
                Paragraph content0 = new Paragraph("        "+"一、此次检查，发现你单位存在以下安全生产违法违规问题：",para);
                content0.setLeading(25f);
                document.add(content0);
                List<String> checkItem = new ArrayList<String>();
                if(CHECK_ITEM.contains("__")){
                    String[] arr=CHECK_ITEM.split("__");
                    for(int i = 0;i<arr.length-1;i++){
                        checkItem.add(arr[i]+"；");
                    }
                    checkItem.add(arr[arr.length-1]+"。");
                }
                else {
                    checkItem.add(CHECK_ITEM);
                }
                for(int i=0;i<checkItem.size();i++){
                    Paragraph text1 = new Paragraph("        "+(i+1)+"是"+checkItem.get(i),para);
                    text1.setLeading(25f);
                    document.add(text1);
                }
            }else {
                Paragraph content0 = new Paragraph("        "+"此次检查，没有发现你单位存在安全生产违法违规问题。",para);
                content0.setLeading(25f);
                document.add(content0);
            }
            //具体问题
            if(CHECK_DESC!=null&&CHECK_DESC!=""){
                Paragraph content1 = new Paragraph("        "+"二、其它安全生产违法情况：",para);
                content1.setLeading(25f);
                document.add(content1);
                Paragraph content2 = new Paragraph("        "+CHECK_DESC,para);
                content2.setLeading(25f);
                document.add(content2);
                Paragraph content3 = new Paragraph(" ",para);
                content3.setLeading(25f);
                document.add(content3);
                Paragraph content4 = new Paragraph(" ",para);
                content4.setLeading(25f);
                document.add(content4);
            }

            //检查人签名
            PdfContentByte sign = writer.getDirectContent();
            BaseFont bf= BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            sign.beginText();
            sign.setFontAndSize(bf, 12);
            sign.showTextAligned( PdfContentByte.ALIGN_LEFT,  "   检查人:", 25f, 320f, 0);
            sign.endText();
            //安全生产监管行政执法人员(签名)图片
            Image image = Image.getInstance ( PathEnum.IMAGES_PATH.getPath()+"/sign"+CHECKMAN_SIGN) ;
            image.setAlignment( Image.TEXTWRAP);
            image.setAbsolutePosition ( 230f, 300f );
            image.scaleAbsolute ( 100, 50 );
            document.add(image);
            //被检查单位负责人
            PdfContentByte sign2 = writer.getDirectContent();
            BaseFont bf6= BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            sign2.beginText();
            sign2.setFontAndSize(bf6, 12);
            sign2.showTextAligned( PdfContentByte.ALIGN_LEFT,  "   被检查单位：", 25f, 220f, 0);
            sign2.endText();
            Image image1 = Image.getInstance ( PathEnum.IMAGES_PATH.getPath()+"/sign"+CHECKEDMAN_SIGN) ;
            image1.setAlignment( Image.TEXTWRAP);
            image1.setAbsolutePosition ( 230f, 200f);
            image1.scaleAbsolute ( 100, 50 );
            document.add(image1);

            //印章
            PdfContentByte cb = writer.getDirectContent();

            //检查部门
            cb = writer.getDirectContent();
            bf= BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            cb.beginText();
            cb.setFontAndSize(bf, 12);
            cb.showTextAligned( PdfContentByte.ALIGN_RIGHT,   unit, 550f, 85f, 0);
            cb.endText();

            //创建时间
            cb = writer.getDirectContent();
            bf= BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            cb.beginText();
            cb.setFontAndSize(bf, 12);
            Date date=new Date();
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
            String now=df.format(date);
            String[] split = now.split("-");
            cb.showTextAligned( PdfContentByte.ALIGN_RIGHT,  split[0]+"年"+split[1]+"月"+split[2]+"日", 550f, 60f, 0);
            cb.endText();

            //附件

            if(!"".equals(CHECK_IMG)){
                document.newPage();
                Paragraph annex1 = new Paragraph("附件：",para);
                document.add(annex1);
                annex1.setLeading(25f);
                Paragraph annex = new Paragraph(" ",para);
                annex.setLeading(25f);
                if(CHECK_IMG.contains("|")){
                    String[] arr=CHECK_IMG.split("\\|");
                    for(int i = 0;i<arr.length;i++){
                        Image annexImg = Image.getInstance( PathEnum.IMAGES_PATH.getPath()+"/"+arr[i]);
                        annexImg.setIndentationLeft(100f);
                        annexImg.scaleAbsolute(150, 150);
                        annex.add(annexImg);
                    }
                    document.add(annex);
                }
                else {
                    Image annexImg = Image.getInstance( PathEnum.IMAGES_PATH.getPath()+"/"+CHECK_IMG);
                    annexImg.setIndentationLeft(100f);
                    annexImg.scaleAbsolute(150, 150);
                    annex.add(annexImg);
                    document.add(annex);
                }
            }
            //预览二维码
            String qrCodeName=System.currentTimeMillis() + new Random().nextInt(1000)+".png";
            Map<EncodeHintType, Object> hints = new HashMap<>();
            //编码
            hints.put( EncodeHintType.CHARACTER_SET, "UTF-8");
            //边框距
            hints.put( EncodeHintType.MARGIN, 0);
            QRCodeWriter qrCodeWriter = new QRCodeWriter ();
            BitMatrix bm = qrCodeWriter.encode(pdfAbsolutePath, BarcodeFormat.QR_CODE, 200, 200, hints);
            File folder1 = new File( PathEnum.QRCORD_PATH.getPath()+"/fireFighting/"+createTime);
            //判断文件夹是否存在，如不存在，创建
            if(!folder1.exists()){
                folder1.mkdirs();
            }
            Path filePath1=new File( PathEnum.QRCORD_PATH.getPath()+"/fireFighting/"+createTime+qrCodeName).toPath();
            MatrixToImageWriter.writeToPath(bm, "png", filePath1);
            document.close();
            return folderName+ "/" +createTime+ "/" +fileName+"_"+"fireFighting/"+createTime+qrCodeName;
        } catch (Exception e){
            e.printStackTrace();
            logger.info ( e.toString () );
            document.close();
            return "";
        }
    }


}
