package pulldata.bazi;

import lombok.Data;

/**
 * @author zhuzhe
 * @date 2019/1/19 10:08
 * @email zhuzhe_mail@163.com
 */
@Data
public class BaZiCeShiParam {

    // 生日类型，1 农历     0 公历
    private String dataType;

    private String year;
    private String month;
    private String day;

    private String hour;
    private String minute;
    private String pid;
    private String cid;

    private String name;
    // 1 男     0 女
    private String sex;
}
