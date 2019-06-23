package pulldata.yunshi;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pulldata.UserInfo;
import pulldata.bazi.BaZiCeShiParam;
import pulldata.common.UserInfoCommon;
import utils.HttpUtils;

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
public class DayYunShi {

    // eg:
    public static void main(String[] args) throws Exception {
//        YunShiParam yunShiParam = new YunShiParam();
//        yunShiParam.setDataType("1");
//        yunShiParam.setYear("1990");
//        yunShiParam.setMonth("1");
//        yunShiParam.setDay("1");
//        yunShiParam.setHour("0");
//        yunShiParam.setMinute("0");
//        yunShiParam.setName("杨盼盼");
//        yunShiParam.setSex("0");
//        YunShiResult pull = pull(yunShiParam);
//        System.out.println(pull);
    }

    public static YunShiResult pull(YunShiParam param) throws Exception {

        UserInfo userInfo = getUserInfo(param);

        String host = "http://m.life.httpcn.com/m/bzriyun/";
        String path = null;
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        querys.put("data_type", param.getDataType());
        querys.put("year", param.getYear());
        querys.put("month", param.getMonth());
        querys.put("day", param.getDay());
        querys.put("hour", param.getHour());
        querys.put("minute", param.getMinute());
        querys.put("zty", "0");
        querys.put("cid", param.getCid());
        int length = param.getName().length();
        if (length < 2 || length > 9) {
            querys.put("name", "name");
        } else {
            querys.put("name", param.getName());
        }
        querys.put("sex", param.getSex());

        querys.put("act", "submit");
        Map<String, String> bodys = new HashMap<>();
        HttpResponse httpResponse = HttpUtils.doPost(host, path, headers, bodys, querys);

        HttpEntity entity = httpResponse.getEntity();
        String string = EntityUtils.toString(entity, "UTF-8");

        Document parse = Jsoup.parse(string);
        Elements hcChaContent = parse.getElementsByClass("hc-cha-content");
        Element element1 = hcChaContent.get(1);
        Elements liList = element1.getElementsByTag("li");
        Element dayElement = liList.get(0);

        YunShiResult yunShiResult = new YunShiResult();
        yunShiResult.setUserInfo(userInfo);
        yunShiResult.setToday(dayElement.childNode(1).childNode(0).toString().replace("\n", ""));
        yunShiResult.setTodayMsg(dayElement.childNode(2).childNode(0).toString());
        yunShiResult.setYunShi1(dayElement.childNode(3).childNode(1).childNode(1).toString());
        yunShiResult.setYunShi2(dayElement.childNode(3).childNode(1).childNode(3).toString());
        yunShiResult.setYunShi3(dayElement.childNode(3).childNode(1).childNode(6).toString());
        yunShiResult.setYunShiReport(dayElement.childNode(3).childNode(1).childNode(13).childNode(0).toString());

        return yunShiResult;
    }

    private static UserInfo getUserInfo(YunShiParam param) throws Exception {
        BaZiCeShiParam baZiCeShiParam = new BaZiCeShiParam();
        baZiCeShiParam.setName(param.getName());
        baZiCeShiParam.setSex(param.getSex());
        baZiCeShiParam.setPid(param.getPid());
        baZiCeShiParam.setCid(param.getCid());
        baZiCeShiParam.setDataType(param.getDataType());
        baZiCeShiParam.setYear(param.getYear());
        baZiCeShiParam.setMonth(param.getMonth());
        baZiCeShiParam.setDay(param.getDay());
        baZiCeShiParam.setHour(param.getHour());
        baZiCeShiParam.setMinute(param.getMinute());
        return UserInfoCommon.getUserInfo(baZiCeShiParam);
    }
}


