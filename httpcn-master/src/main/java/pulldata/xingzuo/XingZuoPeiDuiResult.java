package pulldata.xingzuo;

import lombok.Data;

/**
 * @author zhuzhe
 * @date 2019/1/21 11:33
 * @email zhuzhe_mail@163.com
 */
@Data
public class XingZuoPeiDuiResult {

    private PeiDuiResult peiDuiResult;

    private XingZuoResult xingZuoResult1;

    private XingZuoResult xingZuoResult2;

    @Data
    public class PeiDuiResult{

        //   eg: 白羊座配狮子座
        private String title;

        // 速配指数  eg: 100分
        private String score;

        // 速配比重  eg: 48:52
        private String proportion;

        // 速配分析  eg: 同属火象星座的白羊及狮...
        private String result;

        // 注意事项  eg: 虽然是天生一对的组...
        private String attention;
    }

    @Data
    public class XingZuoResult{

        //   eg: 白羊座和什么座最配
        private String title;

        // 白羊座介绍  eg: 白羊座有刚强、倔强、固执而不服...
        private String introduce;

        // 最来电的星座  eg: 射手座
        private String appropriate;

        // 最来电的星座配对指数   eg: 100分[白羊居上风]
        private String appropriateScore;

        // 最来电的星座配对结果   eg: 二人同属火象星座，两人都是热情...
        private String appropriateResult;

        // 最不协调的星座
        private String inappropriate;

        // 最不协调的星座配对指数
        private String inappropriateScore;

        // 最不协调的星座配对结果
        private String inappropriateResult;
    }
}

