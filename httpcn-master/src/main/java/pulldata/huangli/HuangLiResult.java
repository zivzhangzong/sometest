package pulldata.huangli;

import lombok.Data;

/**
 * @author zhuzhe
 * @date 2019/2/28 13:12
 * @email zhuzhe_mail@163.com
 */
@Data
public class HuangLiResult {

    //    公历日期：公元 2019年02月28日 星期四
    private String date;

    //    农历日期：农历 二零一九年 正月(大)廿四
    private String chineseDate;

    //    宜：日值月破 大事不宜
    private String yi;

    //    忌：日值月破 大事不宜
    private String ji;

    //    干支： 己亥年 丙寅月 丙申日
    private String ganzhi;

    //    纳音： [年]平地木 [月]炉中火 [日]山下火
    private String nayin;

    //    生肖： 属
    private String shengxiao;

    //    星座： 双鱼座
    private String xingzuo;

    //    十二神： 破执位
    private String shen;

    //    值日： 天牢(黑道日)
    private String zhiRi;

    //    冲煞： 冲虎[正冲庚寅]煞南
    private String chongSha;

    //    胎神占方： 厨灶炉房内北
    private String taiShen;

    //    彭祖百忌： 丙不修灶必见灾殃 申不安床鬼祟入房
    private String pengZu;

    //    今日合害： 今日与属鼠、龙半合，与属蛇六合，较为吉利；与属虎相冲，与属猪相害，与属虎、蛇相刑，不太有利。
    private String heHai;

    //    吉神宜趋： 天德合 四相 月德合
    private String jiShen;

    //    凶煞宜忌： 死神 月煞 五虚 八专 朱雀
    private String xiongSha;

    //    24节气： 立春：2月4日 雨水：2月19日
    private String jieQi;

    //    儒略日： 2458548.5
    private String ruLueRi;

    //    节日： 农历: 女娲补天日 黄道婆祭
    private String jieRi;

    //    六曜：赤口
    private String liuYao;

    //    星宿：斗宿[斗木獬]
    private String xingXiu;

    //    岁煞：岁煞南
    private String suiSha;

    //    月煞：月煞北
    private String yueSha;

    //    月令：丙寅
    private String yueLing;

    //    月名：孟春
    private String yueMing;

    //    物候：候雁北
    private String wuHou;

    //    月相：有明月
    private String yueXiang;

    //    日禄：巳命互禄 庚命进禄
    private String riLu;

    //    年三煞：在西　占申山、酉山、戌山
    private String yearSanS;

    //    月三煞：在北　占亥山、子山、丑山
    private String monthSanS;

    //    日三煞：在南　占巳山、午山、未山
    private String daySanS;

    //    年七煞：巳山
    private String yearQiS;

    //    月七煞：申山
    private String monthQiS;

    //    日七煞：寅山
    private String dayQiS;

    //    耕田：五牛耕田
    private String gengTian;

    //    得金：九日得金
    private String deJin;

    //    九星：三碧-轩辕星(木)-安神
    private String jiuXing;

    //    二十八星宿：西方奎木狼-凶 奎星造作得祯祥，家内荣和大吉昌，若是埋葬阴卒死，当年定主两叁伤，看看军令刑伤到，重重官事主瘟惶，开门放水遭灾祸，叁年两次损儿郎。 释义：奎星造作算得了祯祥，可使家内繁荣而和睦。但是不可用来埋葬，否则一年内必有伤亡，而且有官事及怪病发生。要是开门放水则有灾祸，对儿子不利。
    private String xingXiuAll;

    //    河图洛书九星吉凶：三碧-轩辕星(木)-安神 出入会轩辕，凡事必缠牵，相生全不美，相克更忧煎。 远行多不利，博彩尽输钱，九天玄女法，句句不虚言。
    private String heTu;

    //    年五行：平地木
    private String yearWuX;

    //    月五行：炉中火
    private String monthWuX;

    //    日五行：山下火
    private String dayWuX;

    //    喜神：西南
    private String xiShen;

    //    福神：正东
    private String fuShen;

    //    财神：西南
    private String caiShen;

    //    阳贵神：正西
    private String yangGuiS;

    //    阴贵神：西北
    private String yinGuiS;

    //    年空亡：辰巳
    private String yearKongW;

    //    月空亡：戌亥
    private String monthKongW;

    //    日空亡：辰巳
    private String dayKongW;

    //    治水：八龙治水
    private String zhiShui;

    //    分饼：四人分饼
    private String fenBing;
}
