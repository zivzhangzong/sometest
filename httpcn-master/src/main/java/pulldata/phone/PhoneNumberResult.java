package pulldata.phone;

import lombok.Data;

/**
 * @author zhuzhe
 * @date 2019/3/1 13:16
 * @email zhuzhe_mail@163.com
 */
@Data
public class PhoneNumberResult {

    // 综合评分
    private int score;

    // 核心数理解析
    private Result coreResult = new Result();

    // 全体数理解析
    private Result allResult = new Result();

    @Data
    public class Result {

        // 权重  1-5
        private int star;

        // 号码
        private String phoneNum;

        // 评分
        private String score;

        // 简析
        private String content;

        public Result star(int star){
            this.star = star;
            return this;
        }

        public Result phoneNum(String phoneNum){
            this.phoneNum = phoneNum;
            return this;
        }

        public Result score(String score){
            this.score = score;
            return this;
        }

        public Result content(String content){
            this.content = content;
            return this;
        }
    }
}
