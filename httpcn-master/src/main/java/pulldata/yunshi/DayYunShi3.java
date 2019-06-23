package pulldata.yunshi;

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
 * 每日运势
 *
 * @author zhuzhe
 * @date 2019/1/19 11:50
 * @email zhuzhe_mail@163.com
 * @since 1.4
 */
@Deprecated
public class DayYunShi3 {

    public static YunShiResult3 pull(YunShiParam3 yunShiParam3) throws Exception {

//        http://bztest.yiqibazi.com/Daily_1968_6_19_4_%E5%90%A6_%E6%9D%A5%E4%BA%86_%E7%94%B7_%E5%85%AC%E5%8E%86_.html
        String host = "http://bztest.yiqibazi.com/Daily_" +
                yunShiParam3.getYear() +
                "_" +
                yunShiParam3.getMonth() +
                "_" +
                yunShiParam3.getDay() +
                "_" +
                yunShiParam3.getHour() +
                "_" +
                "%E5%90%A6" +
                "_" +
                URLEncoder.encode(yunShiParam3.getName(), "utf-8") +
                "_" +
                URLEncoder.encode(yunShiParam3.getSex().equals("1") ? "男" : "女", "utf-8") +
                "_" +
                "%E5%85%AC%E5%8E%86" +
                "_.html";

        String path = null;
        Map<String, String> querys = new HashMap<>();
        Map<String, String> bodys = new HashMap<>();
        HttpResponse httpResponse = HttpUtils.doGet(host, path, bodys, querys);

        HttpEntity entity = httpResponse.getEntity();
        String string = EntityUtils.toString(entity, "GBK");

        Document parse = Jsoup.parse(string);
        Element left = parse.getElementById("yq_body").getElementById("main").getElementById("left");

        YunShiResult3 yunShiResult3 = new YunShiResult3();
        String number = left.getElementsByClass("score").get(0).getElementsByClass("number").get(0).text();
        number = number.replaceAll("运势：", "").replaceAll("分", "").trim();
        int score = Integer.valueOf(number);

        yunShiResult3.setScore(score > 50 ? score : score + 50);

        Element info = left.getElementsByClass("infos").get(0);
        Elements div = info.getElementsByTag("div");

        yunShiResult3.setFinancialPosition(div.get(1).text().replaceAll("财位：", ""));
        yunShiResult3.setGoodLuckPosition(div.get(2).text().replaceAll("桃花位：", ""));
        yunShiResult3.setGoodLuckNumber(div.get(3).text().replaceAll("幸运数字：", ""));
        yunShiResult3.setGoodLuckColor(div.get(4).text().replaceAll("吉祥色彩：", ""));
        yunShiResult3.setGoodLuckFood(div.get(5).text().replaceAll("开运食物：", ""));
        yunShiResult3.setYi(div.get(6).text());
        yunShiResult3.setJi(div.get(7).text());
        yunShiResult3.setMaxim(div.get(8).text().replaceAll("每日箴言：", ""));

        return yunShiResult3;
    }
}


