package pulldata.xingming;

import lombok.Data;

/**
 * @author zhuzhe
 * @date 2019/2/25 14:21
 * @email zhuzhe_mail@163.com
 */
@Data
public class XingMingCeShiResult {

    // 评分
    private String score;

    // 评价内容
    private String content;

    // 姓名数理和八字命盘
    private MingPan mingPan;

    // 姓名五格互动关系
    private XingMingWuGe xingMingWuGe;

    // 姓名五格数理剖析
    private WuGeAnalyse wuGeAnalyse;
}
