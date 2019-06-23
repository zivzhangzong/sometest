package pulldata.xingzuo;

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
 * 星座匹配
 *
 * @author zhuzhe
 * @date 2019/1/21 11:23
 * @email zhuzhe_mail@163.com
 */
public class XingZuoPeiDui {

    public static void main(String[] args) throws Exception {

//        for (int i = 0; i < 500; i++) {
//            System.out.println(i);
//            XingZuoPeiDuiResult pull = pull("baiyang", "baiyang");
//        }

        XingZuoPeiDuiResult pull = pull("baiyang", "baiyang");
        System.out.println(pull);
    }

    /**
     * @param xz1 星座1,星座的汉语拼音  eg: sheshou
     * @param xz2
     * @return
     * @throws Exception
     */
    public static XingZuoPeiDuiResult pull(String xz1, String xz2) throws Exception {

        String host = "http://life.httpcn.com/xzpei.asp?xz1=" + xz1 + "&xz2=" + xz2
                + "&act=submit&B=%C5%E4%B6%D4%B7%D6%CE%F6";
        String path = null;
        Map<String, String> querys = new HashMap<>();
        Map<String, String> bodys = new HashMap<>();
        HttpResponse httpResponse = HttpUtils.doGet(host, path, bodys, querys);

        HttpEntity entity = httpResponse.getEntity();
        String string = EntityUtils.toString(entity, "GBK");

        Document parse = Jsoup.parse(string);

        Elements l_form_ok = parse.getElementsByClass("l_form_ok");
        Elements chaxun_b = Jsoup.parse(l_form_ok.get(0).html()).getElementsByClass("chaxun_b");
        Elements chaxun_t = Jsoup.parse(l_form_ok.get(0).html()).getElementsByClass("chaxun_t");
        XingZuoPeiDuiResult xingZuoPeiDuiResult = new XingZuoPeiDuiResult();
        XingZuoPeiDuiResult.PeiDuiResult peiDuiResult = xingZuoPeiDuiResult.new PeiDuiResult();
        XingZuoPeiDuiResult.XingZuoResult xingZuoResult1 = xingZuoPeiDuiResult.new XingZuoResult();
        XingZuoPeiDuiResult.XingZuoResult xingZuoResult2 = xingZuoPeiDuiResult.new XingZuoResult();

        Element element = chaxun_b.get(0);
        peiDuiResult.setTitle(chaxun_t.get(0).childNode(1).childNode(0).toString());
        peiDuiResult.setScore(element.childNode(13).childNode(0).toString());
        peiDuiResult.setProportion(element.childNode(19).childNode(0).toString());
        peiDuiResult.setResult(element.childNode(26).toString());
        peiDuiResult.setAttention(element.childNode(32).toString());

        Document parse1 = Jsoup.parse(chaxun_b.get(1).html().replace("<br>", ""));
        Node element1 = parse1.childNode(0).childNode(1);

        xingZuoResult1.setTitle(chaxun_t.get(1).childNode(1).childNode(0).toString());
        xingZuoResult1.setIntroduce(element1.childNode(0).toString());
        xingZuoResult1.setAppropriate(element1.childNode(3).childNode(0).toString());
        xingZuoResult1.setAppropriateScore(element1.childNode(7).childNode(0).toString());
        xingZuoResult1.setAppropriateResult(element1.childNode(8).toString());
        xingZuoResult1.setInappropriate(element1.childNode(11).childNode(0).toString());
        xingZuoResult1.setInappropriateScore(element1.childNode(15).childNode(0).toString());
        xingZuoResult1.setInappropriateResult(element1.childNode(16).toString());

        xingZuoPeiDuiResult.setPeiDuiResult(peiDuiResult);
        xingZuoPeiDuiResult.setXingZuoResult1(xingZuoResult1);
        if (chaxun_b.size() > 2) {
            Document parse2 = Jsoup.parse(chaxun_b.get(2).html().replace("<br>", ""));
            Node element2 = parse2.childNode(0).childNode(1);
            xingZuoResult2.setTitle(chaxun_t.get(2).childNode(1).childNode(0).toString());
            xingZuoResult2.setIntroduce(element2.childNode(0).toString());
            xingZuoResult2.setAppropriate(element2.childNode(3).childNode(0).toString());
            xingZuoResult2.setAppropriateScore(element2.childNode(7).childNode(0).toString());
            xingZuoResult2.setAppropriateResult(element2.childNode(8).toString());
            xingZuoResult2.setInappropriate(element2.childNode(11).childNode(0).toString());
            xingZuoResult2.setInappropriateScore(element2.childNode(15).childNode(0).toString());
            xingZuoResult2.setInappropriateResult(element2.childNode(16).toString());
            xingZuoPeiDuiResult.setXingZuoResult2(xingZuoResult2);
        }
        return xingZuoPeiDuiResult;
    }
}
