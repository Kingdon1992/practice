package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 在LeetCode商店中， 有许多在售的物品。
 * <p>
 * 然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。
 * <p>
 * 现给定每个物品的价格，每个大礼包包含物品的清单，以及待购物品清单。请输出确切完成待购清单的最低花费。
 * <p>
 * 每个大礼包的由一个数组中的一组数据描述，最后一个数字代表大礼包的价格，其他数字分别表示内含的其他种类物品的数量。
 * <p>
 * 任意大礼包可无限次购买。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,5], [[3,0,5],[1,2,10]], [3,2]
 * 输出: 14
 * 解释:
 * 有A和B两种物品，价格分别为¥2和¥5。
 * 大礼包1，你可以以¥5的价格购买3A和0B。
 * 大礼包2， 你可以以¥10的价格购买1A和2B。
 * 你需要购买3个A和2个B， 所以你付了¥10购买了1A和2B（大礼包2），以及¥4购买2A。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shopping-offers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-03-04 15:40:57
 */
public class ShoppingOffers {
    public static void main(String[] args) {
        ShoppingOffers s = new ShoppingOffers();
        List<Integer> price = Arrays.asList(2, 3, 4);
        List<Integer> s1 = Arrays.asList(1, 1, 0, 4);
        List<Integer> s2 = Arrays.asList(2, 2, 1, 9);
        List<List<Integer>> special = Arrays.asList(s1, s2);
        List<Integer> needs = Arrays.asList(1, 2, 1);
        int i = s.shoppingOffers(price, special, needs);
        System.out.println(i);
    }

    /**
     * 非最优解，但是有效，能解决问题
     */
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int[] profit = new int[special.size()];
        //首先计算每个礼包都省下多少钱
        for (int i = 0; i < special.size(); i++) {
            List<Integer> s = special.get(i);
            //优惠价
            int newPrice = s.get(s.size() - 1);
            //原价
            int originPrice = 0;
            for (int j = 0; j < s.size() - 1; j++) {
                //循环加上每种商品的原价
                originPrice = originPrice + s.get(j) * price.get(j);
            }
            //计算节约了多少钱
            profit[i] = originPrice - newPrice;
        }

        //计算礼包组合，尝试使用回溯完成

        //用于存储当前组合情况,n号位存储挑选的n号礼包数量，默认全部是0
        List<Integer> now = new ArrayList<>();
        for (int i = 0; i < special.size(); i++) {
            now.add(0);
        }
        //用户存储所有组合
        List<List<Integer>> compose = new ArrayList<>();
        //开始回溯
        dfs(0, special, needs, now, compose);

        //记录目前省下最多的钱
        int max = 0;
        //遍历所有组合，取出省钱最多的组合
        for (int i = 0; i < compose.size(); i++) {
            int partProfit = 0;
            List<Integer> c = compose.get(i);
            //计算节约了多少钱
            for (int j = 0; j < c.size(); j++) {
                partProfit += c.get(j) * profit[j];
            }
            //看看这个组合和原先省下最多的钱进行比较
            max = Math.max(partProfit, max);
        }

        //计算原价
        int originPrice = 0;
        for (int i = 0; i < needs.size(); i++) {
            originPrice += needs.get(i) * price.get(i);
        }

        //返回
        return originPrice - max;
    }

    /*
     * index 当前挑选到礼包几号，可重复挑选
     */
    void dfs(int index, List<List<Integer>> special, List<Integer> needs, List<Integer> now, List<List<Integer>> compose) {
        //将选择加入组合
        compose.add(new ArrayList(now));

        for (int i = index; i < special.size(); i++) {
            //当前选择的礼包
            List<Integer> s = special.get(i);
            //计算礼包是不是能选，默认能
            boolean flag = true;
            for (int j = 0; j < needs.size(); j++) {
                if (s.get(j) > needs.get(j)) {
                    //发现有一个商品数量，礼包含有数，超过了所需要的数量，不能选
                    flag = false;
                    break;
                }
            }
            //不能选的时候，直接跳过该礼包
            if (!flag) {
                continue;
            }
            //能选，做相关处理
            //该礼包数量+1
            now.set(i, now.get(i) + 1);
            //需要的商品数进行处理
            for (int j = 0; j < needs.size(); j++) {
                needs.set(j, needs.get(j) - s.get(j));
            }
            //递归
            dfs(i, special, needs, now, compose);
            //回溯，该礼包数量-1
            now.set(i, now.get(i) - 1);
            //回溯，需要的商品数
            for (int j = 0; j < needs.size(); j++) {
                needs.set(j, needs.get(j) + s.get(j));
            }
        }
    }
}
