package pulldata.xingming;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.HttpUtils;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 姓名测试
 *
 * @author zhuzhe
 * @date 2019/1/29 14:40
 * @email zhuzhe_mail@163.com
 * @since 2.1
 */
public class XingMingCeShi {

    public static XingMingCeShiResult pull(XingMingCeShiParam ceShiParam) throws Exception {

        String host = "http://m.life.httpcn.com/m/xingming/?act=submit&xing=" +
                URLEncoder.encode(ceShiParam.getXing(), "UTF-8") +
                "&ming=" +
                URLEncoder.encode(ceShiParam.getMing(), "UTF-8") +
                "&sex=" +
                ceShiParam.getSex() +
                "&isbz=0";

        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        Map<String, String> bodys = new HashMap<>();
        HttpResponse httpResponse = HttpUtils.doPost(host, null, headers, bodys, querys);

        HttpEntity entity = httpResponse.getEntity();
        String string = EntityUtils.toString(entity, "UTF-8");

        Document parse = Jsoup.parse(string);

        Element offCanvasContentScroll = parse.getElementById("offCanvasContentScroll");

        // mui-scroll
        Element muiScroll = offCanvasContentScroll.getElementsByClass("mui-scroll").get(0);

        // hc-cha
        Element hcCha = muiScroll.getElementsByClass("hc-cha").get(0);

        Elements elementsByClass = hcCha.getElementsByClass("hc-cha-li");

        XingMingCeShiResult xingMingCeShiResult = new XingMingCeShiResult();

        // 朱哲姓名测试打分
        Element element1 = elementsByClass.get(1);
        Elements elementsByClass1 = element1.getElementsByClass("progress-bar");
        xingMingCeShiResult.setScore(elementsByClass1.get(0).text());
        Elements elementsByClass2 = element1.getElementsByClass("hc-cha-hb");
        xingMingCeShiResult.setContent(elementsByClass2.get(0).text());

        // 姓名数理和八字命盘
        Element element2 = elementsByClass.get(2);
        MingPan mingPan = mingPan(element2, "item5", "item6");
        xingMingCeShiResult.setMingPan(mingPan);

        //  【以下需登录】
//      #################
        // 姓名五格互动关系
//        Element element3 = elementsByClass.get(3);
//        Element hcChaLb = element3.getElementsByClass("hc-cha-lb").get(0);
//        Elements li = hcChaLb.getElementsByTag("li");
//
//        XingMingWuGe xingMingWuGe = new XingMingWuGe();
//        for (int i = 0; i < li.size(); i++) {
//            Element element = li.get(i);
//            String nian = element.getElementsByClass("nian").get(0).text();
//            String tiangdiz = element.getElementsByClass("tiangdiz").get(0).text();
//            String p = element.getElementsByTag("p").get(0).text();
//            switch (nian) {
//                case "五格互动看基础运":
//                    xingMingWuGe.setBasic(xingMingWuGe.new XingMingWuGeResult().score(tiangdiz).contentResult(p));
//                    break;
//                case "五格互动看成功运":
//                    xingMingWuGe.setSuccess(xingMingWuGe.new XingMingWuGeResult().score(tiangdiz).contentResult(p));
//                    break;
//                case "五格互动看社交运":
//                    xingMingWuGe.setSocial(xingMingWuGe.new XingMingWuGeResult().score(tiangdiz).contentResult(p));
//                    break;
//                case "五格互动看家庭运":
//                    xingMingWuGe.setFamily(xingMingWuGe.new XingMingWuGeResult().score(tiangdiz).contentResult(p));
//                    break;
//                case "五格互动看家庭外部关系":
//                    xingMingWuGe.setFamilyRelation(xingMingWuGe.new XingMingWuGeResult().score(tiangdiz).contentResult(p));
//                    break;
//                case "五格互动看个人特性":
//                    xingMingWuGe.setPerson(xingMingWuGe.new XingMingWuGeResult().score(tiangdiz).contentResult(p));
//                    break;
//            }
//        }
//        xingMingCeShiResult.setXingMingWuGe(xingMingWuGe);
//
//      #################
        WuGeAnalyse wuGeAnalyse = new WuGeAnalyse();
        //
        Element element4 = elementsByClass.get(4);
        Elements elementsByClass3 = element4.getElementsByClass("hc-cha-hb");

        // 总格
        for (int i = 0; i < elementsByClass3.size(); i++) {
            Element element_1 = elementsByClass3.get(i);
            Elements li1 = element_1.getElementsByTag("li");

            if (li1.get(0).text().startsWith("总格数理")) {
                wuGeAnalyse.setZong(wuGeAnalyse.new WuGeAnalyseResult().shuLi(li1.get(0).text()).score(li1.get(1).text())
                        .evaluate(li1.get(2).text()).effect(li1.get(3).text())
                        .explain(li1.get(4).text().replaceAll("【汉程网解析】", ""))
                        .imply(li1.get(5).text()));
            }
            if (li1.get(0).text().startsWith("天格数理")) {
                wuGeAnalyse.setTian(wuGeAnalyse.new WuGeAnalyseResult().shuLi(li1.get(0).text()).score(li1.get(1).text())
                        .evaluate(li1.get(2).text()).effect(li1.get(3).text())
                        .explain(li1.get(4).text().replaceAll("【汉程网解析】", ""))
                        .imply(li1.get(5).text()));
            }
            if (li1.get(0).text().startsWith("人格数理")) {
                wuGeAnalyse.setRen(wuGeAnalyse.new WuGeAnalyseResult().shuLi(li1.get(0).text()).score(li1.get(1).text())
                        .evaluate(li1.get(2).text()).effect(li1.get(3).text())
                        .explain(li1.get(4).text().replaceAll("【汉程网解析】", ""))
                        .imply(li1.get(5).text()));
            }
            if (li1.get(0).text().startsWith("地格数理")) {
                wuGeAnalyse.setDi(wuGeAnalyse.new WuGeAnalyseResult().shuLi(li1.get(0).text()).score(li1.get(1).text())
                        .evaluate(li1.get(2).text()).effect(li1.get(3).text())
                        .explain(li1.get(4).text().replaceAll("【汉程网解析】", ""))
                        .imply(li1.get(5).text()));
            }
            if (li1.get(0).text().startsWith("外格数理")) {
                wuGeAnalyse.setWai(wuGeAnalyse.new WuGeAnalyseResult().shuLi(li1.get(0).text()).score(li1.get(1).text())
                        .evaluate(li1.get(2).text()).effect(li1.get(3).text())
                        .explain(li1.get(4).text().replaceAll("【汉程网解析】", ""))
                        .imply(li1.get(5).text()));
            }
        }

        xingMingCeShiResult.setWuGeAnalyse(wuGeAnalyse);

        return xingMingCeShiResult;
    }

    private static MingPan mingPan(Element muiTableViewCell, String item5, String item6) {
        Element item5a = muiTableViewCell.getElementById(item5);
        Elements ulList = item5a.getElementsByTag("ul");
        NameInfo nameInfo = new NameInfo();
        nameInfo.getSimpleName().firstName(getLi(ulList, 1, 0)).lastName(getLi(ulList, 1, 1));
        nameInfo.getOldName().firstName(getLi(ulList, 2, 0)).lastName(getLi(ulList, 2, 1));
        nameInfo.getEnglishName().firstName(getLi(ulList, 3, 0)).lastName(getLi(ulList, 3, 1));
        nameInfo.getNumberName().firstName(getLi(ulList, 4, 0)).lastName(getLi(ulList, 4, 1));
        nameInfo.getWuXingName().firstName(getLi(ulList, 5, 0)).lastName(getLi(ulList, 5, 1));
        nameInfo.setDescription(item5a.getElementsByTag("p").text());
        Element item6a = muiTableViewCell.getElementById(item6);
        Elements ulList2 = item6a.getElementsByTag("ul");
        WuGeShuLi wuGeShuLi = new WuGeShuLi();
        wuGeShuLi.getTian().shuLi(getLi(ulList2, 1, 0)).wuXing(getLi(ulList2, 1, 1)).score(getLi(ulList2, 1, 2)).status(getLi(ulList2, 1, 3));
        wuGeShuLi.getRen().shuLi(getLi(ulList2, 2, 0)).wuXing(getLi(ulList2, 2, 1)).score(getLi(ulList2, 2, 2)).status(getLi(ulList2, 2, 3));
        wuGeShuLi.getDi().shuLi(getLi(ulList2, 3, 0)).wuXing(getLi(ulList2, 3, 1)).score(getLi(ulList2, 3, 2)).status(getLi(ulList2, 3, 3));
        wuGeShuLi.getWai().shuLi(getLi(ulList2, 4, 0)).wuXing(getLi(ulList2, 4, 1)).score(getLi(ulList2, 4, 2)).status(getLi(ulList2, 4, 3));
        wuGeShuLi.getZong().shuLi(getLi(ulList2, 5, 0)).wuXing(getLi(ulList2, 5, 1)).score(getLi(ulList2, 5, 2)).status(getLi(ulList2, 5, 3));
        wuGeShuLi.setSanCai(item6a.getElementsByTag("p").text());

        MingPan mingPan = new MingPan();
        mingPan.setNameInfo(nameInfo);
        mingPan.setWuGeShuLi(wuGeShuLi);

        return mingPan;
    }

    private static String getLi(Elements ulList, int num1, int num2) {
        return ulList.get(num1).getElementsByTag("li").get(num2).text();
    }
}
