package pulldata.jiri;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.HttpUtils;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 吉日查询
 *
 * @since 1.3
 *
 * @author zhuzhe
 * @date 2019/1/21 14:07
 * @email zhuzhe_mail@163.com
 */
public class JiRi {

    public static void main(String[] args) throws Exception {
//        JiRiParam jiRiParam = new JiRiParam();
//        jiRiParam.setSx("狗");
//        jiRiParam.setYear("2019");
//        jiRiParam.setMonth("1");
//        jiRiParam.setHd("");
//        jiRiParam.setXm(JiRiParam.XM.xm02.getValue());
    }

    public static JiRiResult pull(JiRiParam jiRiParam) throws Exception {

        String host = "http://life.httpcn.com/jiri.asp?sx=" +
                URLEncoder.encode(jiRiParam.getSx(), "GBK") +
                "&y=" +
                jiRiParam.getYear() +
                "&m=" +
                jiRiParam.getMonth() +
                "&tp=0&xm=" +
                jiRiParam.getXm() +
                "&hd=" + jiRiParam.getHd() +
                "&act=1&Submit=%B2%E9%D1%AF";

//        http://life.httpcn.com/jiri.asp?sx=%C5%A3&y=2019&m=8&tp=0&xm=4&hd=&act=1&Submit=%B2%E9%D1%AF

        String path = null;
        Map<String, String> querys = new HashMap<>();
        Map<String, String> bodys = new HashMap<>();
        HttpResponse httpResponse = HttpUtils.doGet(host, path, bodys, querys);

        HttpEntity entity = httpResponse.getEntity();
        String string = EntityUtils.toString(entity, "GBK");

        Document parse = Jsoup.parse(string);

        Elements l_form_ok = parse.getElementsByClass("l_form_ok");
        String html = l_form_ok.get(1).html();

        html = html.replaceAll("<span class=\"red\">", "");
        html = html.replaceAll("<span class=\"gray\">", "");
        html = html.replaceAll("<span class=\"green\">", "");
        html = html.replaceAll("<span>", "");
        html = html.replaceAll("</span>", "");

        Elements l_jmtxt = Jsoup.parse(html).getElementsByClass("l_jmtxt");

        JiRiResult jiRiResult = new JiRiResult();
        jiRiResult.setIntroduce(l_jmtxt.get(0).childNode(2).toString());
        List<JiRiResult.DayResult> list = new ArrayList<>();

        Element element = l_jmtxt.get(0);
        Elements p1 = element.getElementsByTag("p");

        for (int i = 0; i < p1.size(); i++) {
            int index = p1.get(i).siblingIndex();
            JiRiResult.DayResult dayResult = jiRiResult.new DayResult();
            dayResult.setDate(element.childNode(index).childNode(2).childNode(0).toString());
            dayResult.setChineseDate(element.childNode(index + 1).toString());
            dayResult.setChong(element.childNode(index + 5).toString());
            dayResult.setYi(element.childNode(index + 9).toString());
            dayResult.setJi(element.childNode(index + 13).toString());
            list.add(dayResult);
        }
        jiRiResult.setDayResultList(list);

        return jiRiResult;
    }
}
