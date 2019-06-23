package pulldata.xingzuo;

import lombok.Data;

/**
 * @author zhuzhe
 * @date 2019/2/20 10:04
 * @email zhuzhe_mail@163.com
 */
@Data
public class XingZuoPeiDui3Result {

    // 两情相悦指数
    private int mutualLove;

    // 天长地久指数
    private int forever;

    // 友情指数
    private int friendship;
    
    // 爱情指数
    private int love;
    
    // 婚姻指数
    private int marriage;
    
    // 亲情指数
    private int family;

    // 配对结果描述
    private String result;
}
