//package pulldata.bazi;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.util.EntityUtils;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import utils.HttpUtils;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 八字合婚
// *
// * @author zhuzhe
// * @date 2019/1/18 14:07
// * @email zhuzhe_mail@163.com
// */
////@Slf4j
//public class BaZiHeHun2 {
//
//    public static void main(String[] args) throws Exception {
//        BaZiHeHunParam param = new BaZiHeHunParam();
//        param.setDataType("1");
//        param.setDataType2("1");
//        param.setCid("");
//        param.setCid2("");
//        param.setPid("");
//        param.setPid2("");
//        param.setSex("1");
//        param.setSex2("0");
//        param.setName("222");
//        param.setName2("333");
//        param.setYear("2012");
//        param.setYear2("2012");
//        param.setMonth("1");
//        param.setMonth2("1");
//        param.setDay("24");
//        param.setDay2("24");
//        param.setHour("0");
//        param.setHour2("0");
//        param.setMinute("0");
//        param.setMinute2("0");
//
//
//        for (int i = 0; i < 1; i++) {
//            BaZiHeHunResult pull = pull(param);
//            System.out.println("#############" + i);
//        }
//
////        System.out.println(param);
//    }
//
//    public static BaZiHeHunResult pull(BaZiHeHunParam baZiHeHunParam) throws Exception {
//
//        String host = "http://life.httpcn.com/hehun.asp";
//        String path = null;
//        Map<String, String> headers = new HashMap<>();
//        Map<String, String> querys = new HashMap<>();
//        querys.put("data_type", baZiHeHunParam.getDataType());
//        querys.put("year", baZiHeHunParam.getYear());
//        querys.put("month", baZiHeHunParam.getMonth());
//        querys.put("day", baZiHeHunParam.getDay());
//        querys.put("hour", baZiHeHunParam.getHour());
//        querys.put("minute", baZiHeHunParam.getMinute());
//        querys.put("pid", baZiHeHunParam.getPid());
//        querys.put("cid", baZiHeHunParam.getCid());
//        querys.put("name", baZiHeHunParam.getName());
//        querys.put("sex", baZiHeHunParam.getSex());
//        querys.put("data_type2", baZiHeHunParam.getDataType2());
//        querys.put("year2", baZiHeHunParam.getYear2());
//        querys.put("month2", baZiHeHunParam.getMonth2());
//        querys.put("day2", baZiHeHunParam.getDay2());
//        querys.put("hour2", baZiHeHunParam.getHour2());
//        querys.put("minute2", baZiHeHunParam.getMinute2());
//        querys.put("pid2", baZiHeHunParam.getPid2());
//        querys.put("cid2", baZiHeHunParam.getCid2());
//        querys.put("name2", baZiHeHunParam.getName2());
//        querys.put("sex2", baZiHeHunParam.getSex2());
//
//        querys.put("act", "submit");
//        Map<String, String> bodys = new HashMap<>();
//        HttpResponse httpResponse = HttpUtils.doPost(host, path, headers, bodys, querys);
//
//        HttpEntity entity = httpResponse.getEntity();
//        String string = EntityUtils.toString(entity, "GBK");
////        log.info("八字合婚：" + string);
////        System.out.println("八字合婚：" + string);
//
//        Document parse = Jsoup.parse(string);
//
//        Elements l_form_ok = parse.getElementsByClass("l_form_ok");
//
//        Elements chaxun_b = Jsoup.parse(l_form_ok.html()).getElementsByClass("chaxun_b");
//
//        Element element = chaxun_b.get(1);
////        System.out.println("合婚结果：" + element.html());
//
//        BaZiHeHunResult baZiHeHunResult = new BaZiHeHunResult();
//        StringBuffer one = new StringBuffer();
//        one.append(element.childNode(10).toString());
//        one.append(element.childNode(11).childNode(0).childNode(0).toString());
//        one.append(element.childNode(12).toString());
//        one.append(element.childNode(13).childNode(0).childNode(0).toString());
//        one.append(element.childNode(14).toString());
//        baZiHeHunResult.setOne(one.toString());
//
//        StringBuffer two = new StringBuffer();
//        two.append(element.childNode(16).toString());
//        two.append(element.childNode(17).childNode(0).toString());
//        two.append(element.childNode(18).toString());
//        baZiHeHunResult.setTwo(two.toString());
//
//        StringBuffer three = new StringBuffer();
//        three.append(element.childNode(20).toString());
//        three.append(element.childNode(21).childNode(0).childNode(0).toString());
//        three.append(element.childNode(22).toString());
//        baZiHeHunResult.setThree(three.toString());
//
//        StringBuffer four = new StringBuffer();
//        four.append(element.childNode(24).toString());
//        four.append(element.childNode(25).childNode(0).childNode(0).toString());
//        four.append(element.childNode(26).toString());
//        baZiHeHunResult.setFour(four.toString());
//
//        StringBuffer five = new StringBuffer();
//        five.append(element.childNode(28).toString());
//        five.append(element.childNode(29).childNode(0).toString());
//        five.append(element.childNode(30).toString());
//        baZiHeHunResult.setFive(five.toString());
//
//        StringBuffer six = new StringBuffer();
//        six.append(element.childNode(32).toString());
//        six.append(element.childNode(33).childNode(0).toString());
//        six.append(element.childNode(34).toString());
//        baZiHeHunResult.setSix(six.toString());
//
//        StringBuffer seven = new StringBuffer();
//        seven.append(element.childNode(36).toString());
//        seven.append(element.childNode(37).childNode(0).toString());
//        baZiHeHunResult.setSeven(seven.toString());
//
//        StringBuffer eight = new StringBuffer();
//        eight.append(element.childNode(40).toString());
//        eight.append(element.childNode(41).childNode(0).toString());
//        baZiHeHunResult.setEight(eight.toString());
//
//        StringBuffer nine = new StringBuffer();
//        nine.append(element.childNode(44).toString());
//        nine.append(element.childNode(45).childNode(0).toString());
//        baZiHeHunResult.setNine(nine.toString());
//
//        StringBuffer ten = new StringBuffer();
//        ten.append(element.childNode(48).toString());
//        baZiHeHunResult.setTen(ten.toString());
//
//        StringBuffer eleven = new StringBuffer();
//        eleven.append(element.childNode(50).toString());
//        baZiHeHunResult.setEleven(eleven.toString());
//
//        return baZiHeHunResult;
//    }
//}
