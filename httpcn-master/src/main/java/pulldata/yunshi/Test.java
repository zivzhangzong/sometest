package pulldata.yunshi;

import common.Ignore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zhuzhe
 * @date 2019/3/12 11:29
 * @email zhuzhe_mail@163.com
 */
@Ignore
public class Test {


    public static List<YunShiResult3> yunShiResult3s(int number) {

        List<String> yiji = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add("祈福");
        list.add("安葬");
        list.add("纳采");
        list.add("祭祀");
        list.add("分居");
        list.add("移徙");
        list.add("嫁娶");
        list.add("解除");
        list.add("修造");
        list.add("捕捉");
        list.add("开市");
        list.add("开仓");
        list.add("安床");
        list.add("求医");
        list.add("穿井");
        list.add("立券");
        list.add("作灶");
        list.add("词讼");
        list.add("栽种");
        list.add("纳财");
        list.add("破土");
        list.add("和讼");
        list.add("盖屋");
        list.add("出货");
        list.add("启鑽");
        list.add("赴任");
        list.add("上梁");
        list.add("入宅");
        list.add("修坟");
        list.add("求财");
        list.add("竖柱");
        list.add("出行");

        int size = list.size();

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                for (int k = j + 1; k < size; k++) {
                    for (int l = k + 1; l < size; l++) {
                        for (int m = l + 1; m < size; m++) {
                            for (int n = m + 1; n < size; n++) {
                                String s = list.get(i) + "、" + list.get(j)
                                        + "、" + list.get(k)
                                        + ":" + list.get(l)
                                        + "、" + list.get(m)
                                        + "、" + list.get(n);
//                                System.out.println(s);
                                yiji.add(s);
                            }
                        }
                    }
                }
            }
        }

        Collections.shuffle(yiji);

        List<YunShiResult3> newList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            String s = yiji.get(i);
            String[] split = s.split(":");
            YunShiResult3 yunShiResult3_1 = new YunShiResult3();
            yunShiResult3_1.setYi(split[0]);
            yunShiResult3_1.setJi(split[1]);
            newList.add(yunShiResult3_1);
        }
        return newList;
    }
}
