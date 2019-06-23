package pulldata.xingming;

import lombok.Data;

/**
 * @author zhuzhe
 * @date 2019/2/25 14:27
 * @email zhuzhe_mail@163.com
 */
@Data
public class XingMingWuGe {

    // 五格互动看基础运
    private XingMingWuGeResult basic;

    // 五格互动看成功运
    private XingMingWuGeResult success;

    // 五格互动看社交运
    private XingMingWuGeResult social;

    // 五格互动看家庭运
    private XingMingWuGeResult family;

    // 五格互动看家庭外部关系
    private XingMingWuGeResult familyRelation;

    // 五格互动看个人特性
    private XingMingWuGeResult person;

    @Data
    public class XingMingWuGeResult {

        // 评分
        private String score;

        // 评价结果
        private String contentResult;

        public XingMingWuGeResult score(String score) {
            this.score = score;
            return this;
        }

        public XingMingWuGeResult contentResult(String contentResult) {
            this.contentResult = contentResult;
            return this;
        }
    }
}
