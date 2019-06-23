package pulldata.huangli;

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
 * 黄历查询
 *
 * @author zhuzhe
 * @date 2019/2/28 10:29
 * @email zhuzhe_mail@163.com
 * @since 2.1
 */
public class HuangLi {

    public static void main(String[] args) throws Exception {
        HuangLiResult pull = pull("2019-2-28");
        System.out.println(pull);
    }

    public static HuangLiResult pull(String date) throws Exception {

        String host = "http://m.life.httpcn.com/huangli_date/" + date;

        String path = null;
        Map<String, String> querys = new HashMap<>();
        Map<String, String> bodys = new HashMap<>();
        HttpResponse httpResponse = HttpUtils.doGet(host, path, bodys, querys);

        HttpEntity entity = httpResponse.getEntity();
        String string = EntityUtils.toString(entity, "GBK");

        Document parse = Jsoup.parse(string);

        Element offCanvasContentScroll = parse.getElementById("offCanvasContentScroll");
        Element huangliDateBj = offCanvasContentScroll.getElementsByClass("huangli-date-bj").get(0);
        Elements txtc = huangliDateBj.getElementsByClass("txtc");

        HuangLiResult huangLiResult = new HuangLiResult();

        huangLiResult.setDate(txtc.get(0).text());
        huangLiResult.setChineseDate(txtc.get(1).text());

        Elements huangliDateTable = huangliDateBj.getElementsByClass("huangli-date-table");

        Element element0 = huangliDateTable.get(0);
        Elements tds = element0.getElementsByClass("two-table").get(0).getElementsByTag("td");
        huangLiResult.setYi(tds.get(0).text());
        huangLiResult.setJi(tds.get(1).text());

        Elements trs = element0.getElementsByClass("lr-table").get(0).getElementsByTag("tr");
        huangLiResult.setGanzhi(trs.get(0).text().replaceAll("干支", ""));
        huangLiResult.setNayin(trs.get(1).text().replaceAll("纳音", ""));
        huangLiResult.setShengxiao(trs.get(2).text().replaceAll("生肖", ""));
        huangLiResult.setXingzuo(trs.get(3).text().replaceAll("星座", ""));
        huangLiResult.setShen(trs.get(4).text().replaceAll("十二神", ""));
        huangLiResult.setZhiRi(trs.get(5).text().replaceAll("值日", ""));
        huangLiResult.setChongSha(trs.get(6).text().replaceAll("冲煞", ""));
        huangLiResult.setTaiShen(trs.get(7).text().replaceAll("胎神占方", ""));
        huangLiResult.setPengZu(trs.get(8).text().replaceAll("彭祖百忌", ""));
        huangLiResult.setHeHai(trs.get(9).text().replaceAll("今日合害", ""));

        Elements tds1 = element0.getElementsByClass("two-table").get(1).getElementsByTag("td");
        huangLiResult.setJiShen(tds1.get(0).text().replaceAll("吉神宜趋", ""));
        huangLiResult.setXiongSha(tds1.get(1).text().replaceAll("凶煞宜忌", ""));

        Elements trs1 = element0.getElementsByClass("lr-table").get(1).getElementsByTag("tr");
        huangLiResult.setJieQi(trs1.get(0).text().replaceAll("24节气", ""));
        huangLiResult.setRuLueRi(trs1.get(1).text().replaceAll("儒略日", ""));
        huangLiResult.setJieRi(trs1.get(2).text().replaceAll("节日", ""));


        Element element1 = huangliDateTable.get(1);
        Element fourTable = element1.getElementsByClass("four-table").get(0);
        Elements trs2 = fourTable.getElementsByTag("tr");

        huangLiResult.setLiuYao(trs2.get(0).childNode(1).childNode(0).toString());
        huangLiResult.setXingXiu(trs2.get(1).childNode(1).childNode(0).toString());
        huangLiResult.setSuiSha(trs2.get(2).childNode(1).childNode(0).toString());

        huangLiResult.setYueSha(trs2.get(3).childNode(1).childNode(0).toString());
        huangLiResult.setYueLing(trs2.get(4).childNode(1).childNode(0).toString());
        huangLiResult.setYueMing(trs2.get(5).childNode(1).childNode(0).toString());

        huangLiResult.setWuHou(trs2.get(6).childNode(1).childNode(0).toString());
        huangLiResult.setYueXiang(trs2.get(7).childNode(1).childNode(0).toString());
        huangLiResult.setRiLu(trs2.get(8).childNode(1).childNode(0).toString());

        huangLiResult.setYearSanS(trs2.get(9).childNode(1).childNode(0).toString());
        huangLiResult.setMonthSanS(trs2.get(10).childNode(1).childNode(0).toString());
        huangLiResult.setDaySanS(trs2.get(11).childNode(1).childNode(0).toString());

        huangLiResult.setYearQiS(trs2.get(12).childNode(1).childNode(0).toString());
        huangLiResult.setMonthQiS(trs2.get(13).childNode(1).childNode(0).toString());
        huangLiResult.setDayQiS(trs2.get(14).childNode(1).childNode(0).toString());

        huangLiResult.setGengTian(trs2.get(15).childNode(1).childNode(0).toString());
        huangLiResult.setDeJin(trs2.get(16).childNode(1).childNode(0).toString());
        huangLiResult.setJiuXing(trs2.get(17).childNode(1).childNode(0).toString());

        huangLiResult.setXingXiuAll(trs2.get(18).getElementsByTag("td").get(1).text());
        huangLiResult.setHeTu(trs2.get(19).getElementsByTag("td").get(1).text());

        huangLiResult.setYearWuX(trs2.get(0).childNode(3).childNode(0).toString());
        huangLiResult.setMonthWuX(trs2.get(1).childNode(3).childNode(0).toString());
        huangLiResult.setDayWuX(trs2.get(2).childNode(3).childNode(0).toString());

        huangLiResult.setXiShen(trs2.get(3).childNode(3).childNode(0).toString());
        huangLiResult.setFuShen(trs2.get(4).childNode(3).childNode(0).toString());
        huangLiResult.setCaiShen(trs2.get(5).childNode(3).childNode(0).toString());

        huangLiResult.setYangGuiS(trs2.get(6).childNode(3).childNode(0).toString());
        huangLiResult.setYinGuiS(trs2.get(7).childNode(3).childNode(0).toString());

        huangLiResult.setYearKongW(trs2.get(12).childNode(3).childNode(0).toString());
        huangLiResult.setMonthKongW(trs2.get(13).childNode(3).childNode(0).toString());
        huangLiResult.setDayKongW(trs2.get(14).childNode(3).childNode(0).toString().replaceAll("&gt;", ""));

        huangLiResult.setZhiShui(trs2.get(15).childNode(3).childNode(0).toString());
        huangLiResult.setFenBing(trs2.get(16).childNode(3).childNode(0).toString());

        return huangLiResult;
    }
}
