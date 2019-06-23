package pulldata.xingming;

import lombok.Data;

/**
 * @author zhuzhe
 * @date 2019/1/26 11:10
 * @email zhuzhe_mail@163.com
 * @since 1.9
 */
@Data
public class MingPan {

    // 姓名
    private String userName;

    // 姓名信息
    private NameInfo nameInfo;

    // 五格数理
    private WuGeShuLi wuGeShuLi;

    // 从五格数理姓名学看蕴含的个性
    private String wuGe;

    // 从形家四运姓名学看蕴含的个性
    private String xingJia;
}
