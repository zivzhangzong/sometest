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
 * 八字健康测试
 *
 * @author zhuzhe
 * @date 2019/2/26 13:31
 * @email zhuzhe_mail@163.com
 * @since 2.1
 */
public class BaZiHealth {

    public static void main(String[] args) throws Exception {

        BaZiCeShiParam baZiCeShiParam = new BaZiCeShiParam();
        baZiCeShiParam.setName("方法");
        baZiCeShiParam.setSex("1");
        baZiCeShiParam.setYear("1998");
        baZiCeShiParam.setMonth("12");
        baZiCeShiParam.setDay("23");
        baZiCeShiParam.setHour("15");
        baZiCeShiParam.setMinute("0");

        BaZiHealthResult pull = pull(baZiCeShiParam);
        System.out.println(pull);
    }

    public static BaZiHealthResult pull(BaZiCeShiParam param) throws Exception {

        String host = "http://m.life.httpcn.com/m/bzjiankang/";
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
        Element muiScroll = offCanvasContentScroll.getElementsByClass("mui-scroll").get(0);
        Element hcCha = muiScroll.getElementsByClass("hc-cha").get(0);
        Element hcChaUl = hcCha.getElementsByClass("hc-cha-ul").get(0);

        Elements lis = hcChaUl.getElementsByClass("hc-cha-li");

        BaZiHealthResult baZiHealthResult = new BaZiHealthResult();
        BaZiCeShiResult.UserInfo userInfo = new BaZiCeShiResult().new UserInfo();

        Element element = lis.get(0);
        Elements elementsByClass = element.getElementsByTag("span");

        baZiHealthResult.setUserInfo(userInfo
                .name(param.getName())
                .sex(param.getSex().equals("1") ? "男" : "女")
                .birthday(elementsByClass.get(2).text())
                .birthdayChina(elementsByClass.get(3).text())
                .nianMing(elementsByClass.get(4).text())
                .wuXing(elementsByClass.get(5).text())
                .chineseZodiac(elementsByClass.get(6).text().substring(0, 2))
                .lifeBuddha(elementsByClass.get(7).text())
                .constellation(elementsByClass.get(8).text())
                .life(elementsByClass.get(9).text())
        );

        List<String> result = new ArrayList<>();
        Element element2 = lis.get(2);
        Elements li2 = element2.getElementsByClass("hc-cha-hb").get(0).getElementsByTag("li");

        for (int i = 0; i < li2.size(); i++) {
            result.add(li2.get(i).text());
        }
        baZiHealthResult.setResult(result);
        return baZiHealthResult;
    }
}
