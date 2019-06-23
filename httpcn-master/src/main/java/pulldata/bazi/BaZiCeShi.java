package pulldata.bazi;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 八字测试
 *
 * @author zhuzhe
 * @date 2019/1/18 14:07
 * @email zhuzhe_mail@163.com
 * @since 1.9
 */
public class BaZiCeShi {

    public static void main(String[] args) throws Exception {
//        BaZiCeShiParam baZiCeShiParam = new BaZiCeShiParam();
//        baZiCeShiParam.setName("朱哲");
//        baZiCeShiParam.setSex("1");
//        baZiCeShiParam.setYear("1988");
//        baZiCeShiParam.setMonth("12");
//        baZiCeShiParam.setDay("23");
//        baZiCeShiParam.setHour("15");
//        baZiCeShiParam.setMinute("0");
//
//        BaZiCeShiResult pull = pull(baZiCeShiParam);
//        System.out.println(pull);
    }

    public static BaZiCeShiResult pull(BaZiCeShiParam param) throws Exception {

        String host = "http://m.life.httpcn.com/m/bazi/";
        String path = null;
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        querys.put("RenYue", "0");
        querys.put("data_type", "0");
        // 公历日期
        querys.put("year", param.getYear());
        querys.put("month", param.getMonth());
        querys.put("day", param.getDay());
        querys.put("hour", param.getHour());
        querys.put("minute", param.getMinute());
        querys.put("sex", param.getSex());
        querys.put("name", param.getName());
        querys.put("zty", "0");

        querys.put("act", "submit");
        Map<String, String> bodys = new HashMap<>();
        HttpResponse httpResponse = HttpUtils.doPost(host, path, headers, bodys, querys);

        HttpEntity entity = httpResponse.getEntity();
        String string = EntityUtils.toString(entity, "UTF-8");

        Document parse = Jsoup.parse(string);

        BaZiCeShiResult baZiCeShiResult = new BaZiCeShiResult();

        Element offCanvasContentScroll = parse.getElementById("offCanvasContentScroll");
        Element hcCha = offCanvasContentScroll.getElementsByClass("hc-cha").get(0);
        Elements ul = hcCha.getElementsByClass("hc-cha-ul");
        Elements li = ul.get(0).getElementsByClass("hc-cha-li");

        // 个人信息
        Element element0 = li.get(0);
        Element element0_1 = element0.getElementsByClass("hc-cha-content").get(0);
        Elements elementsByClass = element0_1.getElementsByClass("hc-cha-info");
        baZiCeShiResult.getUserInfo().name(param.getName())
                .sex(getSpan(elementsByClass, 1).substring(0, 1))
                .birthday(getSpan(elementsByClass, 2))
                .birthdayChina(getSpan(elementsByClass, 3))
                .nianMing(getSpan(elementsByClass, 4))
                .wuXing(getSpan(elementsByClass, 5))
                .chineseZodiac(getSpan(elementsByClass, 6).substring(0, getSpan(elementsByClass, 6).indexOf("(")))
                .lifeBuddha(getSpan(elementsByClass, 7))
                .constellation(getSpan(elementsByClass, 8))
                .life(getSpan(elementsByClass, 9));

        Element element4 = li.get(4);
        Element element4_1 = element4.getElementsByClass("hc-cha-content").get(0);
        Elements element4_1_hcChaHb = element4_1.getElementsByClass("hc-cha-hb");

        Elements li1 = element4_1_hcChaHb.get(0).getElementsByTag("li");

        baZiCeShiResult.getCharacter()
                .lifeAdvantage(li1.get(0).getElementsByTag("p").get(0).text())
                .lifeDisadvantage(li1.get(1).getElementsByTag("p").get(0).text())
                .birthday(element4_1_hcChaHb.get(1).getElementsByTag("p").get(0).text());

        Element element8 = li.get(8);
        Element element8_1 = element8.getElementsByClass("hc-cha-content").get(0);
        Element element8_1_hcChaHb_0 = element8_1.getElementsByClass("hc-cha-hb").get(0);
        Elements li4 = element8_1_hcChaHb_0.getElementsByTag("li");

        baZiCeShiResult.getMarriage()
                .spouse(li4.get(0).getElementsByTag("p").text())
                .oppositeSex(li4.get(1).getElementsByTag("p").text());

        Element element9 = li.get(9);
        Element element9_1 = element9.getElementsByClass("hc-cha-content").get(0);
        Elements element9_1_hcChaHb = element9_1.getElementsByClass("hc-cha-hb");
        Elements li2 = element9_1_hcChaHb.get(0).getElementsByTag("li");

        for (int i = 0; i < li2.size(); i++) {
            if (!li2.get(i).text().startsWith("从八字命盘来看")) {
                baZiCeShiResult.getCareer().addHelp(li2.get(i).text().replaceFirst(i + 1 + "", ""));
            }
        }

        Elements li3 = element9_1_hcChaHb.get(1).getElementsByTag("li");
        for (int i = 0; i < li3.size(); i++) {
            baZiCeShiResult.getCareer().addResistance(li3.get(i).text().replaceFirst(i + 1 + "", ""));
        }

        return baZiCeShiResult;
    }

    private static String getSpan(Elements elementsByClass, int index) {
        return elementsByClass.get(index).getElementsByTag("span").get(0).text();
    }
}
