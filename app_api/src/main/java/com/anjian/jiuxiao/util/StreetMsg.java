package com.anjian.jiuxiao.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author ：努力吧小朱.
 * @version : Java1.8
 * @date ：Created in 23:37 2019/7/30
 * @ Description：${description}
 * @ Modified By：
 */
public class StreetMsg {
    public static String getStreetMsg(String region_code){
        String json = "{\n" +
                "    \"code\": \"3201\",\n" +
                "    \"subs\": [\n" +
                "      {\n" +
                "        \"code\": \"320116\",\n" +
                "        \"subs\": [\n" +
                "          {\n" +
                "            \"code\": \"320116011\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"龙袍街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320116110\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"竹镇镇\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320116007\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"龙池街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320116401\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"新材料产业园\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320116006\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"雄州街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320116010\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"横梁街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320116450\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"化工园区\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320116012\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"马鞍街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320116009\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"金牛湖街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320116008\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"程桥街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320116013\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"冶山街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320116400\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"六合开发区\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"name\": \"六合区\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"code\": \"320102\",\n" +
                "        \"subs\": [\n" +
                "          {\n" +
                "            \"code\": \"320102010\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"玄武湖街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320102005\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"玄武门街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320102009\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"孝陵卫街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320102007\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"锁金村街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320102002\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"梅园新村街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320102003\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"新街口街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320102008\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"红山街道\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"name\": \"玄武区\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"code\": \"320105\",\n" +
                "        \"subs\": [\n" +
                "          {\n" +
                "            \"code\": \"320105007\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"南苑街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320105401\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"建邺区河西中央商务区\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320105004\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"江心洲街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320105002\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"沙洲街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320105403\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"中新南京生态科技岛\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320105003\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"双闸街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320105400\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"建邺区新城科技园\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320105001\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"莫愁湖街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320105006\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"兴隆街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320105402\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"建邺区江东商业文化旅游中心区\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"name\": \"建邺区\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"code\": \"320106\",\n" +
                "        \"subs\": [\n" +
                "          {\n" +
                "            \"code\": \"320106009\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"热河南路街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320106006\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"江东街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320106011\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"建宁路街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320106007\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"凤凰街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320106005\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"挹江门街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320106013\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"小市街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320106004\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"中央门街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320106010\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"幕府山街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320106001\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"宁海路街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320106008\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"下关街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320106002\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"华侨路街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320106012\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"宝塔桥街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320106003\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"湖南路街道\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"name\": \"鼓楼区\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"code\": \"320113\",\n" +
                "        \"subs\": [\n" +
                "          {\n" +
                "            \"code\": \"320113002\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"马群街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320113405\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"南京市马群科技园\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320113408\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"江苏金箔文化产业园\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320113410\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"南京紫东国际创意园\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320113401\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"栖霞经济开发区\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320113007\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"仙林街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320113009\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"八卦洲街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320113413\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"紫金（仙林）科技创业特别社区\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320113403\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"南京市靖安三江口工业园\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320113010\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"西岗街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320113005\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"栖霞街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320113400\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"龙潭监狱\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320113402\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"南京龙潭物流园\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320113406\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"南京仙林大学城\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320113407\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"江苏生命科技创新园\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320113409\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"南京大学科学园\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320113411\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"南京金港科技创业中心\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320113450\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"南京经济技术开发区\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320113003\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"迈皋桥街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320113006\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"龙潭街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320113001\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"尧化街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320113412\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"新港环保科技园\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320113004\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"燕子矶街道\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"name\": \"栖霞区\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"code\": \"320118\",\n" +
                "        \"subs\": [\n" +
                "          {\n" +
                "            \"code\": \"320118104\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"漆桥镇\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320118106\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"东坝镇\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320118401\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"高淳县茶叶实验场\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320118108\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"桠溪镇\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320118101\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"阳江镇\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320118001\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"淳溪街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320118002\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"古柏街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320118105\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"固城镇\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320118102\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"砖墙镇\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320118400\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"高淳县团结圩良种繁育场\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320118403\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"高淳县傅家坛林场\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320118402\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"高淳监狱\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"name\": \"高淳区\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"code\": \"320111\",\n" +
                "        \"subs\": [\n" +
                "          {\n" +
                "            \"code\": \"320111009\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"永宁街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320111401\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"汤泉农场\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320111402\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"珍珠泉管委会\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320111004\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"江浦街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320111008\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"星甸街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320111450\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"高新技术开发区\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320111005\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"桥林街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320111400\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"老山林场\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320111006\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"汤泉街道\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"name\": \"浦口区\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"code\": \"320112\",\n" +
                "        \"subs\": [\n" +
                "          {\n" +
                "            \"code\": \"320112001\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"顶山街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320112004\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"盘城街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320112006\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"长芦街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320112007\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"葛塘街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320112003\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"沿江街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320112002\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"泰山街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320112005\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"大厂街道\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"name\": \"江北新区\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"code\": \"320114\",\n" +
                "        \"subs\": [\n" +
                "          {\n" +
                "            \"code\": \"320114002\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"赛虹桥街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320114004\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"西善桥街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320114006\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"铁心桥街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320114400\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"雨花经济开发区\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320114401\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"板桥新城\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320114402\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"中国（南京）软件谷\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320114005\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"板桥街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320114007\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"梅山街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320114001\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"雨花街道\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"name\": \"雨花台区\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"code\": \"320104\",\n" +
                "        \"subs\": [\n" +
                "          {\n" +
                "            \"code\": \"320104008\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"大光路街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320104401\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"高新园区\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320104004\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"双塘街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320104400\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"夫管办\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320104002\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"夫子庙街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320104003\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"红花街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320104005\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"中华门街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320104007\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"洪武路街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320104006\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"五老村街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320104009\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"瑞金路街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320104011\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"光华路街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320104010\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"月牙湖街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320104001\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"秦虹街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320104012\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"朝天宫街道\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"name\": \"秦淮区\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"code\": \"320117\",\n" +
                "        \"subs\": [\n" +
                "          {\n" +
                "            \"code\": \"320117001\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"永阳街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320117107\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"和凤镇\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320117002\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"柘塘街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320117106\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"晶桥镇\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320117101\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"白马镇\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320117105\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"洪蓝街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320117102\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"东屏街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320117104\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"石湫街道\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"code\": \"320117400\",\n" +
                "            \"subs\": [],\n" +
                "            \"name\": \"溧水开发区\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"name\": \"溧水区\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"name\": \"南京市\"\n" +
                "  }";
        JSONObject jsonObject = JSONObject.parseObject(json);
        String code = jsonObject.get("subs") + "";
        JSONArray jsonArray = JSONArray.parseArray(code);
        String msg = "";
        if(region_code.length() == 3){
            msg =  "南京市应急管理局";
        }else if(region_code.length() == 6){
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject1 = JSONObject.parseObject(jsonArray.get(i)+"");
                if(region_code.equals(jsonObject1.get("code"))){
                    msg = jsonObject1.get("name") + "应急管理局";
                    break;
                }
            }
        }else {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject1 = JSONObject.parseObject(jsonArray.get(i)+"");
                String code1 = jsonObject1.get("subs") + "";
                JSONArray jsonArray1 = JSONArray.parseArray(code1);
                for (int i1 = 0; i1 < jsonArray1.size(); i1++) {
                    JSONObject jsonObject2 = JSONObject.parseObject(jsonArray1.get(i1)+"");
                    if( region_code.equals(jsonObject2.get("code"))){
                        msg = jsonObject1.get("name") + "人民政府" + jsonObject2.get("name") + "办事处";
                        break;
                    }
                }

            }
        }
        return msg;
    }
}
