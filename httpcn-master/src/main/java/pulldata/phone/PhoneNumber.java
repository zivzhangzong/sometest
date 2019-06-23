package pulldata.phone;

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
 * 手机号码测试
 *
 * @author zhuzhe
 * @date 2019/3/1 10:56
 * @email zhuzhe_mail@163.com
 * @since 2.1
 */
public class PhoneNumber {

    public static void main(String[] args) throws Exception {
        PhoneNumberResult pull = pull("13333333333");
        System.out.println(pull);
    }

    public static PhoneNumberResult pull(String phoneNumber) throws Exception {

        String host = "http://m.life.httpcn.com/m/sjhm/";
        String path = null;
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        querys.put("isbz", "0");
        querys.put("xiyong", "0");
        querys.put("act", "submit");
        querys.put("word", phoneNumber);
        Map<String, String> bodys = new HashMap<>();
        HttpResponse httpResponse = HttpUtils.doPost(host, path, headers, bodys, querys);

        HttpEntity entity = httpResponse.getEntity();
        String string = EntityUtils.toString(entity, "UTF-8");

        Document parse = Jsoup.parse(string);
        Element offCanvasContentScroll = parse.getElementById("offCanvasContentScroll");
        Element elementsByClass = offCanvasContentScroll.getElementsByClass("hc-cha-ul").get(0);

        PhoneNumberResult phoneNumberResult = new PhoneNumberResult();

        String score = elementsByClass.getElementsByClass("hc-cha-fenshuN").get(0).getElementsByClass("colred").get(0).text();
        phoneNumberResult.setScore(Integer.valueOf(score));
        Elements hcChaHb = elementsByClass.getElementsByClass("hc-cha-hb");

        Elements lis = hcChaHb.get(0).getElementsByTag("li");

        phoneNumberResult.getCoreResult()
                .star(lis.get(0).getElementsByTag("p").text().replaceAll("权重：", "").replaceAll("☆", "").trim().length())
                .phoneNum(phoneNumber)
                .score(lis.get(2).text().replaceAll("评分：", "").trim())
                .content(lis.get(3).text().replaceAll("简析：", "").trim());

        phoneNumberResult.getAllResult()
                .star(lis.get(6).getElementsByTag("p").text().replaceAll("权重：", "").replaceAll("☆", "").trim().length())
                .phoneNum(phoneNumber)
                .score(lis.get(8).text().replaceAll("评分：", "").trim())
                .content(lis.get(9).text().replaceAll("简析：", "").trim());

        return phoneNumberResult;
    }
}
