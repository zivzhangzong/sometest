package pulldata.xingming;

import lombok.Data;

/**
 * 姓名信息
 *
 * @author zhuzhe
 * @date 2019/1/25 17:00
 * @email zhuzhe_mail@163.com
 * @since 1.9
 */
@Data
public class NameInfo {

    /**
     * 简体姓名
     */
    private Name simpleName = new Name();

    /**
     * 繁体姓名
     */
    private Name oldName = new Name();

    /**
     * 姓名拼音
     */
    private Name englishName = new Name();

    /**
     * 姓名笔画
     */
    private Name numberName = new Name();

    /**
     * 姓名五行
     */
    private Name wuXingName = new Name();

    /**
     * 说明
     */
    private String description;

    @Data
    public class Name {
        // 姓
        private String firstName;
        // 名
        private String lastName;

        public Name firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Name lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
    }
}
