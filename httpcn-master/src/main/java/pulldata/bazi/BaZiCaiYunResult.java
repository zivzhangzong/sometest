package pulldata.bazi;

import lombok.Data;

import java.util.List;

/**
 * @author zhuzhe
 * @date 2019/2/26 16:19
 * @email zhuzhe_mail@163.com
 */
@Data
public class BaZiCaiYunResult {

    private BaZiCeShiResult.UserInfo userInfo;

    private List<CaiYunResult> caiYunResultList;

    @Data
    public class CaiYunResult {

        // 年龄段
        private String age;

        // 得分
        private String score;

        // 总结
        private String summary;

        // 简评
        private String evaluate;
    }
}
