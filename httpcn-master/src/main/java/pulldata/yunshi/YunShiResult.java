package pulldata.yunshi;

import lombok.Data;
import pulldata.UserInfo;

/**
 * @author zhuzhe
 * @date 2019/1/19 11:53
 * @email zhuzhe_mail@163.com
 */
@Data
public class YunShiResult {

    private UserInfo userInfo;

    //公历2019年1月19日
    private String today;

    //阳丙火红龙日
    private String todayMsg;

    // 分别是一段文本
//    eg：流日的红龙和你的命盘有一种非常特殊的关系，凶吉交织复杂，好坏参半。是故，处事需要十分谨慎小心，命运可能会走出不同的结果。
    private String yunShi1;
    private String yunShi2;
    private String yunShi3;
    // 运势总结   eg:声名不错，荣誉加身
    private String yunShiReport;
}
