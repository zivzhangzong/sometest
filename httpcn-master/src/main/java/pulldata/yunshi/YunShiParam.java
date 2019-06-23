package pulldata.yunshi;

import lombok.Data;

/**
 * @author zhuzhe
 * @date 2019/1/19 11:53
 * @email zhuzhe_mail@163.com
 */
@Data
public class YunShiParam {

    // 生日类型，1 农历     0 公历
    private String dataType;

    // 1950-2011
    private String year;
    private String month;
    private String day;

    private String hour;
    private String minute;
    private String pid = "";
    private String cid = "";

    private String name;
    // 1 男     0 女
    private String sex;

    // 真太阳时  1 是  (该参数可以直接忽略)
    private String zty;
}
