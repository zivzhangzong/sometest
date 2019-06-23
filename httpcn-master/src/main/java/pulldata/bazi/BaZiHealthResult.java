package pulldata.bazi;

import lombok.Data;

import java.util.List;

/**
 * @author zhuzhe
 * @date 2019/2/26 14:06
 * @email zhuzhe_mail@163.com
 */
@Data
public class BaZiHealthResult {

    private BaZiCeShiResult.UserInfo userInfo;

    private List<String> result;
}
