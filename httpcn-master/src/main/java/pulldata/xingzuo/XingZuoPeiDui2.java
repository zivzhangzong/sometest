package pulldata.xingzuo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import utils.HttpUtils;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 星座匹配
 *
 * @author zhuzhe
 * @date 2019/1/21 11:23
 * @email zhuzhe_mail@163.com
 * @since 2.0
 */
public class XingZuoPeiDui2 {

    public static void main(String[] args) throws Exception {
        XingZuoPeiDui2Result pull = pull("白羊座", "处女座");
//        System.out.println(pull);
//        for (int i = 0; i < pull.getResult().size(); i++) {
//            System.out.println(pull.getResult().get(i));
//        }
    }

    /**
     * @param xz1 女 星座,星座的名称  eg:白羊座
     * @param xz2 男
     * @return
     * @throws Exception
     */
    public static XingZuoPeiDui2Result pull(String xz1, String xz2) throws Exception {

        String host = "https://www.ankangwang.com/peidui/?flag=2&astro_m=" +
                URLEncoder.encode(xz1, "utf-8") +
                "&astro_f=" +
                URLEncoder.encode(xz2, "utf-8") +
                "&Input=12%E6%98%9F%E5%BA%A7%E9%85%8D%E5%AF%B9%E6%B5%8B%E8%AF%95";
        String path = null;
        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();

        Map<String, String> bodys = new HashMap<>();
        HttpResponse httpResponse = HttpUtils.doPost(host, path, headers, bodys, querys);

        HttpEntity entity = httpResponse.getEntity();
        String string = EntityUtils.toString(entity, "utf-8");

        Document parse = Jsoup.parse(string);

        Element neirong = parse.getElementById("Neirong");
        Element table = neirong.getElementsByClass("Nh122").get(0).getElementsByTag("table").get(0);
        Element tbody = table.getElementsByTag("tbody").get(0);

        Element td = tbody.getElementsByTag("tr").get(2).getElementsByTag("td").get(0);

        String html = td.html();
        int index = html.indexOf("<br>");
        html = html.substring(index + 4);

        String[] split = html.split("<p></p>");

        XingZuoPeiDui2Result result = new XingZuoPeiDui2Result();
        List<String> list = new ArrayList<>();

        for (int i = 0; i < split.length; i++) {
            list.add(split[i]);
        }
        result.setResult(list);
        return result;
    }
}
