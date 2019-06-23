package pulldata.bazi;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 八字合婚
 *
 * @author zhuzhe
 * @date 2019/1/18 14:07
 * @email zhuzhe_mail@163.com
 * @update 1.6
 * @since 1.0
 */
public class BaZiHeHun {

    public static void main(String[] args) throws Exception {
        BaZiHeHunParam param = new BaZiHeHunParam();
        param.setDataType("1");
        param.setDataType2("1");
        param.setSex("1");
        param.setSex2("0");
        param.setName(" dasddddddddda");
        param.setName2("sadddddddddddsadasdasdasd ");
        param.setYear("2005");
        param.setYear2("2005");
        param.setMonth("1");
        param.setMonth2("1");
        param.setDay("24");
        param.setDay2("24");
        param.setHour("0");
        param.setHour2("0");
        param.setMinute("0");
        param.setMinute2("0");

        for (int i = 0; i < 1; i++) {
            BaZiHeHunResult pull = pull(param);
            System.out.println("#############" + i);
        }
//        System.out.println(param);
    }

    public static BaZiHeHunResult pull(BaZiHeHunParam baZiHeHunParam) throws Exception {

        checkName(baZiHeHunParam);

        String host = "http://m.life.httpcn.com/m/hehun/";
        String path = null;
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        querys.put("data_type", baZiHeHunParam.getDataType());
        querys.put("year", baZiHeHunParam.getYear());
        querys.put("month", baZiHeHunParam.getMonth());
        querys.put("day", baZiHeHunParam.getDay());
        querys.put("hour", baZiHeHunParam.getHour());
        querys.put("minute", baZiHeHunParam.getMinute());
        querys.put("name", baZiHeHunParam.getName());
        querys.put("zty", "0");
        querys.put("data_type2", baZiHeHunParam.getDataType2());
        querys.put("year2", baZiHeHunParam.getYear2());
        querys.put("month2", baZiHeHunParam.getMonth2());
        querys.put("day2", baZiHeHunParam.getDay2());
        querys.put("hour2", baZiHeHunParam.getHour2());
        querys.put("minute2", baZiHeHunParam.getMinute2());
        querys.put("name2", baZiHeHunParam.getName2());
        querys.put("zty2", "0");

        querys.put("act", "submit");
        Map<String, String> bodys = new HashMap<>();
        HttpResponse httpResponse = HttpUtils.doPost(host, path, headers, bodys, querys);

        HttpEntity entity = httpResponse.getEntity();
        String string = EntityUtils.toString(entity, "UTF-8");
        Document parse = Jsoup.parse(string);
        BaZiHeHunResult baZiHeHunResult = new BaZiHeHunResult();
        Elements hcChaLi = parse.getElementsByClass("hc-cha-li");

        Element element0 = hcChaLi.get(0);
//        Element num = element0.getElementsByClass("num").get(0);
//        baZiHeHunResult.setScore(Integer.valueOf(num.childNode(0).childNode(0).toString()));
        Elements ul = element0.getElementsByClass("hc-cha-hb");

        Node liLists = ul.get(0).childNode(0);

        String replace = liLists.toString().replace("<font class=\"spannianbgnkc\">", "")
                .replace("<span class=\"iconfont icon-tishi\">", "")
                .replace("</span>", "")
                .replace("<font class=\"txt-col-red\">", "")
                .replace("<font class=\"txt-col-green\">", "")
                .replace("<font class=\"txt-col-blue\">", "")
                .replace("</font>", "")
                .replace("<label class=\"label-col-zs\">", "")
                .replace("<i></i></label>", "")
                .replace("<p>", "")
                .replace("</p>", "")
                .replace("男命对照女命", "男命对照女命：")
                .replace("女命对照男命", "女命对照男命：");
        Elements ul1 = Jsoup.parse(replace).getElementsByTag("li");
        try {
            baZiHeHunResult.setOne(ul1.get(0).text());
            baZiHeHunResult.setTwo(ul1.get(1).text());
            baZiHeHunResult.setThree(ul1.get(2).text());
            baZiHeHunResult.setFour(ul1.get(3).text());
            baZiHeHunResult.setFive(ul1.get(4).text());
            baZiHeHunResult.setSix(ul1.get(5).text());
            baZiHeHunResult.setSeven(ul1.get(6).text());
            baZiHeHunResult.setEight(ul1.get(7).text());
            baZiHeHunResult.setNine(ul1.get(8).text());
            baZiHeHunResult.setTen(ul1.get(9).text());
            baZiHeHunResult.setEleven(ul1.get(10).text());
        } catch (Exception e) {
        }
        return baZiHeHunResult;
    }

    private static void checkName(BaZiHeHunParam baZiHeHunParam) {
        int length = baZiHeHunParam.getName().trim().length();
        if (length < 1 || length > 7) {
            baZiHeHunParam.setName("name");
        }
        int length1 = baZiHeHunParam.getName2().trim().length();
        if (length1 < 1 || length1 > 7) {
            baZiHeHunParam.setName2("name2");
        }
    }
}
