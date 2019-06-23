package pulldata.jiri;

import lombok.Data;

import java.util.List;

/**
 * @author zhuzhe
 * @date 2019/1/21 14:55
 * @email zhuzhe_mail@163.com
 */
@Data
public class JiRiResult {

    // 说明   eg：主人属鼠，结婚吉日不能冲鼠，以下推...
    private String introduce;

    // 吉日列表
    private List<DayResult> dayResultList;

    @Data
    public class DayResult {

        // 公历时间
        private String date;

        // 农历时间
        private String chineseDate;

        // 冲
        private String chong;

        // 宜
        private String yi;

        // 忌
        private String ji;
    }
}
