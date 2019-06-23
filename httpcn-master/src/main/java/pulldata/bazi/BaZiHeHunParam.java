package pulldata.bazi;

import lombok.Data;

/**
 * @author zhuzhe
 * @date 2019/1/18 14:27
 * @email zhuzhe_mail@163.com
 */
@Data
public class BaZiHeHunParam {

    // 男：
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
    // 是否考虑真太阳时  1 是
    private String zty;


    // 女：
    // 生日类型，1 农历     0 公历
    private String dataType2;

    private String year2;
    private String month2;
    private String day2;

    private String hour2;
    private String minute2;
    private String pid2;
    private String cid2;

    private String name2;
    // 1 男     0 女
    private String sex2;
    private String zty2;
}
//        赋值示例：
//        ("data_type", "1");
//        ("year", "1994");
//        ("month", "12");
//        ("day", "12");
//        ("hour", "12");
//        ("minute", "12");
//        ("pid", "陕西");
//        ("cid", "西安");
//        ("name", "朱哲");
//        ("sex", "1");
