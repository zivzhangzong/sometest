package pulldata.exception;

/**
 * 汉字匹配异常
 *
 * @author zhuzhe
 * @date 2019/1/28 11:02
 * @email zhuzhe_mail@163.com
 */
public class ChinesePatternException extends RuntimeException {

    public ChinesePatternException() {
        super();
    }

    public ChinesePatternException(String message) {
        super(message);
    }
}
