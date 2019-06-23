package pulldata.xingming;

import lombok.Data;

/**
 * 姓名五格数理剖析
 *
 * @author zhuzhe
 * @date 2019/2/25 14:53
 * @email zhuzhe_mail@163.com
 */
@Data
public class WuGeAnalyse {

    // 总格数理
    private WuGeAnalyseResult zong;

    // 天格数理
    private WuGeAnalyseResult tian;

    // 人格数理
    private WuGeAnalyseResult ren;

    // 地格数理
    private WuGeAnalyseResult di;

    // 外格数理
    private WuGeAnalyseResult wai;

    @Data
    public class WuGeAnalyseResult {

        // 数理
        private String shuLi;

        // 数理吉凶
        private String score;

        // 数理简评
        private String evaluate;

        // 影响
        private String effect;

        // 数理解释
        private String explain;

        // 数理暗示
        private String imply;

        public WuGeAnalyseResult shuLi(String shuLi) {
            this.shuLi = shuLi;
            return this;
        }

        public WuGeAnalyseResult score(String score) {
            this.score = score;
            return this;
        }

        public WuGeAnalyseResult evaluate(String evaluate) {
            this.evaluate = evaluate;
            return this;
        }

        public WuGeAnalyseResult effect(String effect) {
            this.effect = effect;
            return this;
        }

        public WuGeAnalyseResult explain(String explain) {
            this.explain = explain;
            return this;
        }

        public WuGeAnalyseResult imply(String imply) {
            this.imply = imply;
            return this;
        }
    }
}
