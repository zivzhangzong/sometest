package pulldata.yunshi;

import lombok.Data;

import java.util.Calendar;

/**
 * @author zhuzhe
 * @date 2019/3/11 13:27
 * @email zhuzhe_mail@163.com
 */
@Data
public class YunShiParam3 {

    // 注：公历日期
    // 1950-2011
    private int year;
    private int month;
    private int day;

    private String hour;

    private String name;
    // 1 男     0 女
    private String sex;

    public int hashCode() {
        Calendar instance = Calendar.getInstance();
        int code = this.getName().hashCode();
        String s = String.valueOf(code + Integer.valueOf(this.getSex())).substring(1, 2);
        int year = this.getYear() + instance.get(Calendar.YEAR);
        year = year % 10;
        int month = this.getMonth() + instance.get(Calendar.MONTH);
        month = month % 10;
        int day = this.getDay() + instance.get(Calendar.DATE);
        day = day % 10;
        String hour = this.getHour().substring(1);
        return Integer.valueOf(s + year + month + day + hour) + 1;
    }
}
