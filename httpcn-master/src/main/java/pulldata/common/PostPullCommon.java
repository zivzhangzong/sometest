package pulldata.common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import pulldata.yunshi.YunShiParam;
import utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuzhe
 * @date 2019/1/21 10:18
 * @email zhuzhe_mail@163.com
 */
public class PostPullCommon {

    public static String pull(String url, YunShiParam param) throws Exception {

        Map<String, String> headers = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        querys.put("data_type", param.getDataType());
        querys.put("year", param.getYear());
        querys.put("month", param.getMonth());
        querys.put("day", param.getDay());
        querys.put("hour", param.getHour());
        querys.put("minute", param.getMinute());
        querys.put("pid", param.getPid());
        querys.put("cid", param.getCid());
        querys.put("name", param.getName());
        querys.put("sex", param.getSex());

        querys.put("act", "submit");
        Map<String, String> bodys = new HashMap<>();
        HttpResponse httpResponse = HttpUtils.doPost(url, null, headers, bodys, querys);

        HttpEntity entity = httpResponse.getEntity();
        String string = EntityUtils.toString(entity, "GBK");
        return string;
    }
}
