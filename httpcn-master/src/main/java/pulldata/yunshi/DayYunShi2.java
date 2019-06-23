package pulldata.yunshi;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
@Deprecated
public class DayYunShi2 {

    public static YunShiResult pull(YunShiParam param) throws Exception {

//        https://www.yiqibazi.com/daily_1996_6_19_6_0_%E5%93%88%E5%93%88_1_1_0.html

        String host = "https://www.yiqibazi.com/daily_" +
                "1996" +
                "_6_19_6_0_" +
                "%E5%93%88%E5%93%88" +
                "_1_1_0.html";

        String path = null;
        Map<String, String> querys = new HashMap<>();
        Map<String, String> bodys = new HashMap<>();
        HttpResponse httpResponse = HttpUtils.doGet(host, path, bodys, querys);

        HttpEntity entity = httpResponse.getEntity();
        String string = EntityUtils.toString(entity, "GBK");

        Document parse = Jsoup.parse(string);

        Element softRight = parse.getElementsByClass("softRight").get(0);
        Element element = softRight.getElementsByClass("daily-con").get(0);
        Element score = element.getElementsByClass("score").get(0).getElementsByClass("number").get(0);
        return null;
    }
}


