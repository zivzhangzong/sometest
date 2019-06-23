package pulldata;

import lombok.Data;

/**
 * @author zhuzhe
 * @date 2019/1/19 16:14
 * @email zhuzhe_mail@163.com
 */
@Data
public class UserInfo {

    private String basicInfo;//基本信息   eg: 张三，性别：男，出生地：河北石家庄
    private String birthday;// 公历      eg: 2002年12月26日22时47分 星期二
    private String birthdayOfChina;// 农历  eg: 辛未年八月初十巳时
    private String chineseZodiac;//生肖     eg: 属羊
    private String lifeBuddha;//本命佛      eg: 大日如来
    private String constellation;//星座     eg: 处女座
    private String life;//命卦             eg: 离命（在您居住的房屋中，您的本命财位在 东方。）
}
