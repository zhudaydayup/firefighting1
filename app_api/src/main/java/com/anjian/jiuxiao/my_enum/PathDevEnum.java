package com.anjian.jiuxiao.my_enum;

/**
 * @Author: 随心的小新
 * @Date: 2019/7/29 21:56
 */
public enum PathDevEnum {

    FIRE_QIN_T("擎天九小场所测试","http://zfxc.njyjgl.cn/yjbd"),
    FIRE_QIN_R("擎天九小场所运行","http://zfxc.njyjgl.cn/yjbd");

    private String msg;
    private String url;

    @Override
    public String toString() {
        return "PathDevEnum{" +
                "msg='" + msg + '\'' +
                ", url='" + url + '\'' +
                '}';
    }



    PathDevEnum() {
    }

    PathDevEnum(String msg, String url) {
        this.msg = msg;
        this.url = url;
    }

    PathDevEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }



    public String getUrl() {
        return url;
    }
}
