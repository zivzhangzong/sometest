package pulldata.bazi;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.HttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 八字财运
 *
 * @author zhuzhe
 * @date 2019/2/26 15:27
 * @email zhuzhe_mail@163.com
 * @since 2.1
 */
public class BaZiCaiYun {

    public static void main(String[] args) throws Exception {

        BaZiCeShiParam baZiCeShiParam = new BaZiCeShiParam();
        baZiCeShiParam.setName("hello");
        baZiCeShiParam.setSex("1");
        baZiCeShiParam.setYear("2018");
        baZiCeShiParam.setMonth("1");
        baZiCeShiParam.setDay("1");
        baZiCeShiParam.setHour("12");
        baZiCeShiParam.setMinute("12");

        BaZiCaiYunResult pull = pull(baZiCeShiParam);
        System.out.println(pull);
    }

    public static BaZiCaiYunResult pull(BaZiCeShiParam param) throws Exception {

        String host = "http://m.life.httpcn.com/m/bzcaiyun/";
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

        Element offCanvasContentScroll = parse.getElementById("offCanvasContentScroll");
        Element hcChaUl = offCanvasContentScroll.getElementsByClass("hc-cha-ul").get(0);
        Elements lis = hcChaUl.getElementsByClass("hc-cha-li");

        BaZiCaiYunResult baZiCaiYunResult = new BaZiCaiYunResult();

        BaZiCeShiResult.UserInfo userInfo = new BaZiCeShiResult().new UserInfo();

        Element element = lis.get(0);
        Elements elementsByClass = element.getElementsByTag("span");

        userInfo = userInfo
                .name(param.getName())
                .sex(param.getSex().equals("1") ? "男" : "女")
                .birthday(elementsByClass.get(2).text())
                .birthdayChina(elementsByClass.get(3).text())
                .nianMing(elementsByClass.get(4).text())
                .wuXing(elementsByClass.get(5).text())
                .chineseZodiac(elementsByClass.get(6).text().substring(0, 2))
                .lifeBuddha(elementsByClass.get(7).text())
                .constellation(elementsByClass.get(8).text())
                .life(elementsByClass.get(9).text());
        baZiCaiYunResult.setUserInfo(userInfo);

        Element element2 = lis.get(2);
        Element hcChaLb = element2.getElementsByClass("hc-cha-lb").get(0);
        List<BaZiCaiYunResult.CaiYunResult> caiYunResultList = new ArrayList<>();
        Elements li2 = hcChaLb.getElementsByTag("li");

        for (int i = 0; i < li2.size(); i++) {

            BaZiCaiYunResult.CaiYunResult caiYunResult = baZiCaiYunResult.new CaiYunResult();
            Element element1 = li2.get(i);
            String nian = element1.getElementsByClass("nian").get(0).text();
            caiYunResult.setAge(nian);

            String tiangdiz = element1.getElementsByClass("tiangdiz").get(0).text().replaceAll("得分：", "");
            caiYunResult.setScore(tiangdiz);

            String text = element1.getElementsByClass("info").get(0).getElementsByTag("font").get(0).text();
            caiYunResult.setSummary(text);

            String p = element1.getElementsByClass("info").get(0).getElementsByTag("p").get(0).text().replaceAll("简评：", "").replaceAll(text, "").trim();
            caiYunResult.setEvaluate(p);
            caiYunResultList.add(caiYunResult);
        }
        baZiCaiYunResult.setCaiYunResultList(caiYunResultList);

        return baZiCaiYunResult;
    }
}
