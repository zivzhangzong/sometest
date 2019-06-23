package pulldata.yunshi;

import lombok.Data;

/**
 * @author zhuzhe
 * @date 2019/3/11 11:53
 * @email zhuzhe_mail@163.com
 */
@Data
public class YunShiResult3 {

    //运势评分
    private int score;

    //财位
    private String financialPosition;

    //桃花位
    private String goodLuckPosition;

    //幸运数字
    private String goodLuckNumber;

    //吉祥色彩
    private String goodLuckColor;

    //开运食物
    private String goodLuckFood;

    //宜
    private String yi;

    //忌
    private String ji;

    //每日箴言
    private String maxim;
}
