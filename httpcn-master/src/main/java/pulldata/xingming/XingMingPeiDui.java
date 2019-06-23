package pulldata.xingming;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import pulldata.exception.ChinesePatternException;
import utils.HttpUtils;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 姓名配对
 *
 * @author zhuzhe
 * @date 2019/1/23 14:44
 * @email zhuzhe_mail@163.com
 * @since 2.1
 */
public class XingMingPeiDui {

    public static void main(String[] args) throws Exception {
//        XingMingPeiDuiParam peiDuiParam = new XingMingPeiDuiParam();
//        peiDuiParam.setXingMan("李达");
//        peiDuiParam.setMingMan("看");
//        peiDuiParam.setXingWomen("李");
//        peiDuiParam.setMingWomen("思");
//        XingMingPeiDuiResult pull = pull(peiDuiParam);
//        System.out.println(pull);
    }

    public static XingMingPeiDuiResult pull(XingMingPeiDuiParam peiDuiParam) throws Exception {

        Pattern compile = Pattern.compile("[\\u4E00-\\u9FA5]{1,2}");
        if (!compile.matcher(peiDuiParam.getXingMan()).matches()
                || !compile.matcher(peiDuiParam.getMingMan()).matches()
                || !compile.matcher(peiDuiParam.getXingWomen()).matches()
                || !compile.matcher(peiDuiParam.getMingWomen()).matches()
                ) {
            throw new ChinesePatternException("姓和名只能包含一到两个中文汉字");
        }

        String host = "http://m.life.httpcn.com/m/xmpd/?act=submit&xing1=" +
                URLEncoder.encode(peiDuiParam.getXingMan(), "UTF-8") +
                "&ming1=" +
                URLEncoder.encode(peiDuiParam.getMingMan(), "UTF-8") +
                "&sex1=1&xing2=" +
                URLEncoder.encode(peiDuiParam.getXingWomen(), "UTF-8") +
                "&ming2=" +
                URLEncoder.encode(peiDuiParam.getMingWomen(), "UTF-8") +
                "&sex2=0";

        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        Map<String, String> bodys = new HashMap<>();
        HttpResponse httpResponse = HttpUtils.doPost(host, null, headers, bodys, querys);

        HttpEntity entity = httpResponse.getEntity();
        String string = EntityUtils.toString(entity, "UTF-8");

        Document parse = Jsoup.parse(string);

        XingMingPeiDuiResult xingMingPeiDuiResult = new XingMingPeiDuiResult();
        Element offCanvasContentScroll = parse.getElementById("offCanvasContentScroll");
        Elements elementsByClass = offCanvasContentScroll.getElementsByClass("hc-cha-ul");
        Elements muiTableViewCell = elementsByClass.get(0).getElementsByClass("mui-table-view-cell");

        Element muiTableViewCell0 = muiTableViewCell.get(0);
        Elements num = muiTableViewCell0.getElementsByClass("num");
        Elements hcChaHb = muiTableViewCell0.getElementsByClass("hc-cha-hb");
        Elements p = hcChaHb.get(0).getElementsByTag("p");
        xingMingPeiDuiResult.setTitle(muiTableViewCell0.childNode(0).childNode(0).toString());
        xingMingPeiDuiResult.setScore(num.get(0).childNode(0).childNode(0).toString());
        xingMingPeiDuiResult.setResult(p.get(0).text());
        xingMingPeiDuiResult.setEvaluate(p.get(1).text().replace("简评：", ""));

        Element muiTableViewCell1 = muiTableViewCell.get(1);
        MingPan mingPan1 = mingPan(muiTableViewCell1, "item5a", "item6a");
        xingMingPeiDuiResult.setMingPan1(mingPan1);

        Element muiTableViewCell2 = muiTableViewCell.get(2);
        MingPan mingPan2 = mingPan(muiTableViewCell2, "item5b", "item6b");
        xingMingPeiDuiResult.setMingPan2(mingPan2);

        return xingMingPeiDuiResult;
    }

    private static MingPan mingPan(Element muiTableViewCell, String item5, String item6) {
        Element item5a = muiTableViewCell.getElementById(item5);
        Elements ulList = item5a.getElementsByTag("ul");
        NameInfo nameInfo = new NameInfo();
        nameInfo.getSimpleName().firstName(getLi(ulList, 1, 0)).lastName(getLi(ulList, 1, 1));
        nameInfo.getOldName().firstName(getLi(ulList, 2, 0)).lastName(getLi(ulList, 2, 1));
        nameInfo.getEnglishName().firstName(getLi(ulList, 3, 0)).lastName(getLi(ulList, 3, 1));
        nameInfo.getNumberName().firstName(getLi(ulList, 4, 0)).lastName(getLi(ulList, 4, 1));
        nameInfo.getWuXingName().firstName(getLi(ulList, 5, 0)).lastName(getLi(ulList, 5, 1));
        nameInfo.setDescription(item5a.getElementsByTag("p").text());
        Element item6a = muiTableViewCell.getElementById(item6);
        Elements ulList2 = item6a.getElementsByTag("ul");
        WuGeShuLi wuGeShuLi = new WuGeShuLi();
        wuGeShuLi.getTian().shuLi(getLi(ulList2, 1, 0)).wuXing(getLi(ulList2, 1, 1)).score(getLi(ulList2, 1, 2)).status(getLi(ulList2, 1, 3));
        wuGeShuLi.getRen().shuLi(getLi(ulList2, 2, 0)).wuXing(getLi(ulList2, 2, 1)).score(getLi(ulList2, 2, 2)).status(getLi(ulList2, 2, 3));
        wuGeShuLi.getDi().shuLi(getLi(ulList2, 3, 0)).wuXing(getLi(ulList2, 3, 1)).score(getLi(ulList2, 3, 2)).status(getLi(ulList2, 3, 3));
        wuGeShuLi.getWai().shuLi(getLi(ulList2, 4, 0)).wuXing(getLi(ulList2, 4, 1)).score(getLi(ulList2, 4, 2)).status(getLi(ulList2, 4, 3));
        wuGeShuLi.getZong().shuLi(getLi(ulList2, 5, 0)).wuXing(getLi(ulList2, 5, 1)).score(getLi(ulList2, 5, 2)).status(getLi(ulList2, 5, 3));
        wuGeShuLi.setSanCai(item6a.getElementsByTag("p").text());

        Node nodeUl = muiTableViewCell.getElementsByClass("hc-cha-hb").get(0).childNode(0);

        MingPan mingPan = new MingPan();
        mingPan.setUserName(muiTableViewCell.childNode(0).childNode(0).toString().replace("姓名命盘", ""));
        mingPan.setNameInfo(nameInfo);
        mingPan.setWuGeShuLi(wuGeShuLi);
        mingPan.setWuGe(Jsoup.parse(nodeUl.childNode(0).toString()).getElementsByTag("p").get(0).childNode(0).toString());
        mingPan.setXingJia(Jsoup.parse(nodeUl.childNode(1).toString()).getElementsByTag("p").get(0).childNode(0).toString());

        return mingPan;
    }

    private static String getLi(Elements ulList, int num1, int num2) {
        return ulList.get(num1).getElementsByTag("li").get(num2).text();
    }
}
