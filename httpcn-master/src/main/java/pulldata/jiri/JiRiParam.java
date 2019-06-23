package pulldata.jiri;

import lombok.Data;

/**
 * @author zhuzhe
 * @date 2019/1/22 11:25
 * @email zhuzhe_mail@163.com
 */
@Data
public class JiRiParam {

    /**
     * 生肖
     * "" : 不限
     * 鼠牛虎兔龙蛇马羊猴鸡狗猪
     */
    private String sx;

    // 年，支持2010 - 2020
    private String year;

    // 月份
    private String month;

    /**
     * {@link XM}
     */
    private String xm;

    // "1" : 仅黄道日
    // "" : 所有吉日
    private String hd = "";

    /**
     * 吉日类别
     */
    public enum XM {
        xm01("1", "结婚"),
        xm02("2", "订婚"),
        xm03("3", "搬家"),
        xm04("4", "入宅"),
        xm05("5", "装修"),
        xm06("6", "开业"),
        xm07("7", "建筑"),
        xm08("8", "签约"),
        xm09("9", "交易"),
        xm10("10", "祈福"),
        xm11("11", "求嗣"),
        xm12("12", "开光"),
        xm13("13", "安葬"),;

        private String value;

        // 描述
        private String desc;

        XM(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public String getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }
}

