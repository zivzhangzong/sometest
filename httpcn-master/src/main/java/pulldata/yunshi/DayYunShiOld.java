package pulldata.yunshi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pulldata.UserInfo;
import pulldata.bazi.BaZiCeShiParam;
import pulldata.common.UserInfoCommon;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 每日运势
 *
 * @author zhuzhe
 * @date 2019/1/19 11:50
 * @email zhuzhe_mail@163.com
 * @since 1.2
 */
@Deprecated
public class DayYunShiOld {

    // eg:
    public static void main(String[] args) throws Exception {
//        YunShiParam yunShiParam = new YunShiParam();
//        yunShiParam.setDataType("1");
//        yunShiParam.setYear("1994");
//        yunShiParam.setMonth("12");
//        yunShiParam.setDay("4");
//        yunShiParam.setHour("22");
//        yunShiParam.setMinute("47");
//        yunShiParam.setName("LLLLL");
//        yunShiParam.setSex("1");
//        yunShiParam.setPid("陕西");
//        yunShiParam.setCid("宝鸡");
//        YunShiResult pull = pull(yunShiParam);
//        System.out.println(pull);
    }


    public static YunShiResult pull(YunShiParam param) throws Exception {

        UserInfo userInfo = getUserInfo(param);

        String param2 = "UM_distinctid=1685ab96a152ec-09ac8f59ba95c6-323b5b03-1fa400-1685ab96a1e145; " +
                "Hm_lvt_668a2b1ccec82d575177212da2570e5d=1547710322,1547774418,1547863449; " +
                "ASPSESSIONIDSATTAQTT=EONPIOABONGKPGGGIMNMJKAP; " +
                "input%5Fsex=" + param.getSex() + "; " +
                "input%5Fhour=" + param.getHour() + "; " +
                "input%5Fming=; " +
                "input%5Fxing=; " +
                "input%5Fminute=" + param.getMinute() + "; " +
                "iscookies=false; " +
                "CNZZDATA1267010321=555813250-1547706281-http%253A%252F%252Flife.httpcn.com%252F%7C1547879220; " +
                "input%5Fday=" + param.getDay() + "; " +
                "input%5Fname=" + encode(param.getName()) + "; " +
                "input%5Fmonth=" + param.getMonth() + "; " +
                "input%5Fyear=" + param.getYear() + "; " +
                "input%5FRenYue=0; " +
                "input%5Fzty=0; " +
                "Hm_lpvt_668a2b1ccec82d575177212da2570e5d=1547883490; " +
                "bztext=TestOK%7C" +
                encode(param.getName()) +
                "%7Ctrue%7Ctrue%7C" +
                "%BA%D3%B1%B1" +
                "%7C" +
                "%CA%AF%BC%D2%D7%AF" +
                "%7C" +
                "%D0%C1%CE%B4" +
                "%7C" +
                "%B0%CB%7C%B3%F5%CA%AE%7C%CB%C8%7C%C2%B7%C5%D4%CD%C1%7C%C9%BD%CF%C2%BB%F0%7C%CB%C9%B0%D8%C4%BE%7C%B0%D7%C0%AF%BD%F0%7C%D0%C1%7C%CE%B4%7C%B6%A1%7C%D3%CF%7C%B8%FD%7C%D2%FA%7C%D0%C1%7C%CB%C8%7C%B4%A6%C5%AE%7C%D1%F2%7C%A1%F9%B4%CB%C3%FC%BE%D6%C8%EB%D1%F2%C8%D0%B8%F1%A1%A3%7C%B6%AB%CB%C4%C3%FC%7C%C7%EF%7C4%7C0%7C1%7C2%7C1%7C5%7C0%7C2%7C4%7C3%7C326%7C0%7C19%7C60%7C28%7C%BD%F0%7C%CB%AE%7C%BD%F0%A1%A2%CD%C1%7C%CB%AE%A1%A2%C4%BE%A1%A2%BB%F0%7C354%7C79%7C%B9%FD%CD%FA%7C%C4%BE%7C%BB%F0%7C%CB%AE%7C%BD%F0" +
                "%7C%CD%C1%7C" +
                param.getYear() +
                "%7C" +
                param.getMonth() +
                "%7C" +
                param.getDay() +
                "%7C" +
                param.getHour() +
                "%7C" +
                param.getMinute() +
                "%7C%B6%FE%7C%B5%DB%CD%FA%7C%BD%F0%7C%CB%AE%7C%CD%C1%7C%BB%F0%7C%C4%BE%7C%C0%EB%7C%7C%7C; " +
                "input%5Fdata%5Ftype=" + param.getDataType() + "; " +
                "input%5Fcid=" + encode(param.getCid()) + "; " +
                "input%5Fpid=" + encode(param.getPid());

//            TestOK|朱哲|true|false|||丁酉|三|十八|巳|山下火|佛灯火|天上火|大林木|丁|酉|甲|辰|己|未|己|巳|白羊|鸡|※此命局未入正格。|西四命|春|1|0|1|2|4|2|1|3|3|5|94|1|34|72|203|土|水|土、火|金、水、木|275|129|过旺|水|木|金|土|火|
//            1957|4|17|10|10|三|衰|土|金|火|木|水|兑|||; path=/

        String url2 = "http://life.httpcn.com/yuncheng.asp?act=submit";
        URL url = new URL(url2);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        con.setRequestProperty("Cookie", param2);
        con.connect();
        InputStream inputStream = con.getInputStream();
        //
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len = 0;
        while (true) {
            len = inputStream.read(b);
            if (len == -1) {
                break;
            }
            byteArrayOutputStream.write(b, 0, len);
        }
        String string = byteArrayOutputStream.toString("GBK");

        Document parse = Jsoup.parse(string);

        Elements l_form_ok = parse.getElementsByClass("l_form_ok");

        Elements chaxun_b = Jsoup.parse(l_form_ok.get(0).html()).getElementsByClass("chaxun_b");

        YunShiResult yunShiResult = new YunShiResult();
//        Element element1 = chaxun_b.get(1);
//        UserInfo userInfo = userInfo(element1);

        yunShiResult.setUserInfo(userInfo);

        try {
            Element element2 = chaxun_b.get(2);
            yunShiResult.setToday(element2.childNode(5).childNode(0).toString());
            yunShiResult.setTodayMsg(element2.childNode(14).toString());
            yunShiResult.setYunShi1(element2.childNode(20).toString());
            yunShiResult.setYunShi2(element2.childNode(24).toString());
            yunShiResult.setYunShi3(element2.childNode(29).childNode(1).toString());
            StringBuffer yunShiReport = new StringBuffer();
//            yunShiReport.append(element2.childNode(29).childNode(6).toString());
//            yunShiReport.append(element2.childNode(29).childNode(7).childNode(0).toString());
//            yunShiReport.append(element2.childNode(29).childNode(8).toString());
            yunShiReport.append(element2.childNode(29).childNode(9).childNode(0).toString());
//            yunShiReport.append(element2.childNode(29).childNode(10).toString());
            yunShiResult.setYunShiReport(yunShiReport.toString());

//            yunShiResult.setYunShi4(element2.childNode(31).childNode(4).toString());
//            yunShiResult.setYunShi5(element2.childNode(33).childNode(1).toString());
        } catch (Exception e) {
        }

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


    public static String encode(String string) throws UnsupportedEncodingException {
        return URLEncoder.encode(string, "GBK");
    }

    public static String decode(String string) throws UnsupportedEncodingException {
        return URLDecoder.decode(string, "GBK");
    }
}


