package pulldata.bazi;

import lombok.Data;

/**
 * @author zhuzhe
 * @date 2019/1/18 11:38
 * @email zhuzhe_mail@163.com
 */
@Data
public class BaZiHeHunResult {

    // 合婚指数，匹配度
    private int score;

    private String one;
    private String two;
    private String three;
    private String four;
    private String five;
    private String six;
    private String seven;
    private String eight;
    private String nine;
    private String ten;
    private String eleven;
}
// 示例：  由上到下对应 one -> eleven
//        女命对男命有25％的补益作用力，男命对女命有25％的补益作用力。
//        双方喜用神互相生助，有共同点，容易相处。
//        双方八字交会后，五行平衡性为71％。
//        双方八字交会后，阴阳平衡性为50％。
//        男女命日元搭配不和谐，相处不容易。
//        男女命生年无害，可以交往！
//        九星合婚得：归魂，可以较量轻重，月家少忌，可以为婚。
//        男命摩羯座，女命摩羯座，较为和谐的一对。
//        男命生日密码：1，女命生日密码：1，两人不太适合在一起，生日没有缘份。
//        男命对照女命：天干无合。地支有丑未男月女年相冲，丑未男月女时相冲。
//        女命对照男命：天干无合。地支有丑未女月男年相冲，丑未女月男时相冲。
