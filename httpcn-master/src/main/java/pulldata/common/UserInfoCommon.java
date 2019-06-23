package pulldata.common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pulldata.UserInfo;
import pulldata.bazi.BaZiCeShiParam;
import utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuzhe
 * @date 2019/1/21 16:40
 * @email zhuzhe_mail@163.com
 * @since 1.2
 */
public class UserInfoCommon {

    public static UserInfo getUserInfo(BaZiCeShiParam param) throws Exception {
        String host = "http://life.httpcn.com/bazi.asp";
        String path = null;
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        querys.put("data_type", param.getDataType());
        querys.put("year", param.getYear());
        querys.put("month", param.getMonth());
        querys.put("day", param.getDay());
        querys.put("hour", param.getHour());
        querys.put("minute", param.getMinute());
        querys.put("pid", param.getPid());
        querys.put("cid", param.getCid());
        querys.put("name", param.getName());
        querys.put("sex", param.getSex());

        querys.put("act", "submit");
        Map<String, String> bodys = new HashMap<>();
        HttpResponse httpResponse = HttpUtils.doPost(host, path, headers, bodys, querys);

        HttpEntity entity = httpResponse.getEntity();
        String string = EntityUtils.toString(entity, "GBK");

        Document parse = Jsoup.parse(string);

        Elements l_form_ok = parse.getElementsByClass("l_form_ok");

        Elements chaxun_b = Jsoup.parse(l_form_ok.get(0).html()).getElementsByClass("chaxun_b");
        Element element = chaxun_b.get(1);
        return userInfo(element);
    }

    private static UserInfo userInfo(Element element) {
        UserInfo userInfo = new UserInfo();
        userInfo.setBasicInfo(element.childNode(2).toString());
        userInfo.setBirthday(element.childNode(6).toString());
        userInfo.setBirthdayOfChina(element.childNode(10).toString().replace("&nbsp;", ""));
        userInfo.setChineseZodiac(element.childNode(15).childNode(0).toString());
        userInfo.setLifeBuddha(element.childNode(23).childNode(0).toString());
        userInfo.setConstellation(element.childNode(29).childNode(0).toString());
        userInfo.setLife(element.childNode(34).toString());
        return userInfo;
    }
}
