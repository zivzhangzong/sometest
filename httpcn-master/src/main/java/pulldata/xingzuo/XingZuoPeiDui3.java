package pulldata.xingzuo;

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
 * 星座匹配
 *
 * @author zhuzhe
 * @date 2019/1/21 11:23
 * @email zhuzhe_mail@163.com
 * @since 2.0
 */
public class XingZuoPeiDui3 {

    public static void main(String[] args) throws Exception {
//        for (int i = 1; i <= 12; i++) {
//            for (int j = 1; j <= 12; j++) {
//                XingZuoPeiDui3Result pull = pull(i, j);
//                System.out.println(pull);
//                System.out.println("########################### " + i + " : " + j);
//            }
//        }
    }

    /**
     * @param girl 女星座
     * @param boy  男星座
     *             <p>
     *             1——>白羊
     *             2——>金牛
     *             3——>双子
     *             4——>巨蟹
     *             5——>狮子
     *             6——>处女
     *             7——>天秤
     *             8——>天蝎
     *             9——>射手
     *             10——>魔羯
     *             11——>水瓶
     *             12——>双鱼
     *             </p>
     * @throws Exception
     */
    public static XingZuoPeiDui3Result pull(int girl, int boy) throws Exception {

        String host = "https://www.meiguoshenpo.com/tool/match.html?MATCH_SIGN_2=" +
                girl +
                "&MATCH_SIGN_1=" +
                boy;
        String path = null;
        Map<String, String> querys = new HashMap<>();
        Map<String, String> bodys = new HashMap<>();
        HttpResponse httpResponse = HttpUtils.doGet(host, path, bodys, querys);

        HttpEntity entity = httpResponse.getEntity();
        String string = EntityUtils.toString(entity, "utf-8");

        Document parse = Jsoup.parse(string);

        Element show_cnt = parse.getElementById("PAGE_LEFT").getElementsByClass("show_cnt").get(0);

        XingZuoPeiDui3Result xingZuoPeiDui3Result = new XingZuoPeiDui3Result();
        String html = show_cnt.html();

        int index = html.indexOf("两情相悦指数：");
        String substring = html.substring(index + 7, index + 8);
        xingZuoPeiDui3Result.setMutualLove(Integer.valueOf(substring));
        int index1 = html.indexOf("天长地久指数：");
        String substring1 = html.substring(index1 + 7, index1 + 8);
        xingZuoPeiDui3Result.setForever(Integer.valueOf(substring1));

        String friendship = html.substring(html.indexOf("友情："), html.indexOf("爱情："))
                .replaceAll("</p>", "")
                .replaceAll("<p>", "")
                .replaceAll("<br>", "").trim();
        xingZuoPeiDui3Result.setFriendship(friendship.replaceAll("友情：", "").length());

        String love = html.substring(html.indexOf("爱情："), html.indexOf("婚姻："))
                .replaceAll("</p>", "")
                .replaceAll("<p>", "")
                .replaceAll("<br>", "").trim();
        xingZuoPeiDui3Result.setLove(love.replaceAll("爱情：", "").length());

        String marriage = html.substring(html.indexOf("婚姻："), html.indexOf("亲情："))
                .replaceAll("</p>", "")
                .replaceAll("<p>", "")
                .replaceAll("<br>", "").trim();
        xingZuoPeiDui3Result.setMarriage(marriage.replaceAll("婚姻：", "").length());

        String family = html.substring(html.indexOf("亲情："), html.indexOf("亲情：") + 8)
                .replaceAll("</p>", "")
                .replaceAll("</p", "")
                .replaceAll("</", "")
                .replaceAll("<", "")
                .replaceAll("&nbsp;", "")
                .replaceAll("&nbsp", "")
                .replaceAll("&nbs", "")
                .replaceAll("&nb", "")
                .replaceAll("&n", "")
                .replaceAll("&", "")
                .replaceAll("<p>", "")
                .replaceAll("<br>", "").trim();
        xingZuoPeiDui3Result.setFamily(family.replaceAll("亲情：", "").length());

        String substring2 = html.substring(html.indexOf("亲情：") + family.length())
                .replaceAll("&nbsp;", "").trim()
                .replace("<a href=\"https://www.meiguoshenpo.com/\" target=\"_blank\">", "")
                .replace("</a>", "")
                .substring(4);
        xingZuoPeiDui3Result.setResult(substring2);
        return xingZuoPeiDui3Result;
    }
}
