package pulldata.xingming;

import lombok.Data;

/**
 * @author zhuzhe
 * @date 2019/1/26 11:08
 * @email zhuzhe_mail@163.com
 * @since 1.9
 */
@Data
public class XingMingPeiDuiResult {

    // 姓名配对指数及分析
    private String title;

    // 缘份指数  eg: 90
    private String score;

    // 配对分析  eg: 李啊对李思的姓名五格数理有6％的补益作用力；李思对李啊的姓名五格数理有96％的补益作用力。
    private String result;

    // 简评  eg: 从五格数理看，你们的姓名相合程度相当棒！请好好珍惜！
    private String evaluate;

    // 姓名命盘(第一个人)
    private MingPan mingPan1;

    // 姓名命盘(第二个人)
    private MingPan mingPan2;
}
