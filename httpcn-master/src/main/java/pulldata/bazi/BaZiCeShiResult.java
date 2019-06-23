package pulldata.bazi;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuzhe
 * @date 2019/1/19 10:11
 * @email zhuzhe_mail@163.com
 */
@Data
public class BaZiCeShiResult {

    private UserInfo userInfo = new UserInfo();

    private Character character = new Character();

    private Marriage marriage = new Marriage();

    private Career career = new Career();

    @Data
    public class UserInfo {
        private String name; //姓名
        private String sex; //性别
        private String birthday; // 出生公历
        private String birthdayChina; // 出生阴历
        private String nianMing; //年命纳音
        private String wuXing; //天运五行
        private String chineseZodiac; //生 肖
        private String lifeBuddha; //本命佛
        private String constellation; //星座
        private String life; //命卦

        public UserInfo name(String name) {
            this.name = name;
            return this;
        }

        public UserInfo sex(String sex) {
            this.sex = sex;
            return this;
        }

        public UserInfo birthday(String birthday) {
            this.birthday = birthday;
            return this;
        }

        public UserInfo birthdayChina(String birthdayChina) {
            this.birthdayChina = birthdayChina;
            return this;
        }

        public UserInfo nianMing(String nianMing) {
            this.nianMing = nianMing;
            return this;
        }

        public UserInfo wuXing(String wuXing) {
            this.wuXing = wuXing;
            return this;
        }

        public UserInfo chineseZodiac(String chineseZodiac) {
            this.chineseZodiac = chineseZodiac;
            return this;
        }

        public UserInfo lifeBuddha(String lifeBuddha) {
            this.lifeBuddha = lifeBuddha;
            return this;
        }

        public UserInfo constellation(String constellation) {
            this.constellation = constellation;
            return this;
        }

        public UserInfo life(String life) {
            this.life = life;
            return this;
        }
    }

    // 性格特质
    @Data
    public class Character {
        private String lifeAdvantage;//从命局看 优点
        private String lifeDisadvantage;//从命局看 缺点
        private String birthday;//从生日看

        public Character lifeAdvantage(String lifeAdvantage) {
            this.lifeAdvantage = lifeAdvantage;
            return this;
        }

        public Character lifeDisadvantage(String lifeDisadvantage) {
            this.lifeDisadvantage = lifeDisadvantage;
            return this;
        }

        public Character birthday(String birthday) {
            this.birthday = birthday;
            return this;
        }
    }

    // 婚姻感情
    @Data
    public class Marriage {
        private String spouse;//配偶的个性
        private String oppositeSex;//异性缘及桃花

        public Marriage spouse(String spouse) {
            this.spouse = spouse;
            return this;
        }

        public Marriage oppositeSex(String oppositeSex) {
            this.oppositeSex = oppositeSex;
            return this;
        }
    }

    // 事业助力与阻力
    @Data
    public class Career {
        private List<String> help = new ArrayList<>();//助力
        private List<String> resistance = new ArrayList<>();//阻力

        public Career addHelp(String help) {
            this.help.add(help);
            return this;
        }

        public Career addResistance(String resistance) {
            this.resistance.add(resistance);
            return this;
        }
    }
}


