package pulldata.xingming;

import lombok.Data;

/**
 * 五格数理
 *
 * @author zhuzhe
 * @date 2019/1/26 10:23
 * @email zhuzhe_mail@163.com
 * @since 1.9
 */
@Data
public class WuGeShuLi {

    // 天格
    private ShuLi tian = new ShuLi();
    // 人格
    private ShuLi ren = new ShuLi();
    // 地格
    private ShuLi di = new ShuLi();
    // 外格
    private ShuLi wai = new ShuLi();
    // 总格
    private ShuLi zong = new ShuLi();
    // 三才
    private String sanCai;

    @Data
    public class ShuLi {

        // 数理
        private String shuLi;
        // 五行
        private String wuXing;
        // 得分
        private String score;
        // 吉凶
        private String status;

        public ShuLi shuLi(String shuLi) {
            this.shuLi = shuLi;
            return this;
        }

        public ShuLi wuXing(String wuXing) {
            this.wuXing = wuXing;
            return this;
        }

        public ShuLi score(String score) {
            this.score = score;
            return this;
        }

        public ShuLi status(String status) {
            this.status = status;
            return this;
        }
    }
}
