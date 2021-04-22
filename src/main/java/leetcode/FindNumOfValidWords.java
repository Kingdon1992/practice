package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。
 * <p>
 * 字谜的迷面 puzzle 按字符串形式给出，如果一个单词 word 符合下面两个条件，那么它就可以算作谜底：
 * <p>
 * 单词 word 中包含谜面 puzzle 的第一个字母。
 * 单词 word 中的每一个字母都可以在谜面 puzzle 中找到。
 * 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而 "beefed"（不含字母 "a"）以及 "based"（其中的 "s" 没有出现在谜面中）。
 * 返回一个答案数组 answer，数组中的每个元素 answer[i] 是在给出的单词列表 words 中可以作为字谜迷面 puzzles[i] 所对应的谜底的单词数目。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：
 * words = ["aaaa","asas","able","ability","actt","actor","access"],
 * puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
 * 输出：[1,1,3,2,4,0]
 * 解释：
 * 1 个单词可以作为 "aboveyz" 的谜底 : "aaaa"
 * 1 个单词可以作为 "abrodyz" 的谜底 : "aaaa"
 * 3 个单词可以作为 "abslute" 的谜底 : "aaaa", "asas", "able"
 * 2 个单词可以作为 "absoryz" 的谜底 : "aaaa", "asas"
 * 4 个单词可以作为 "actresz" 的谜底 : "aaaa", "asas", "actt", "access"
 * 没有单词可以作为 "gaswxyz" 的谜底，因为列表中的单词都不含字母 'g'。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= words.length <= 10^5
 * 4 <= words[i].length <= 50
 * 1 <= puzzles.length <= 10^4
 * puzzles[i].length == 7
 * words[i][j], puzzles[i][j] 都是小写英文字母。
 * 每个 puzzles[i] 所包含的字符都不重复。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-valid-words-for-each-puzzle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-02-26 10:27:57
 */
public class FindNumOfValidWords {
    public static void main(String[] args) {
        String[] words = {"aaaa", "asas", "able", "ability", "actt", "actor", "access"};
        String[] puzzles = {"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"};

        FindNumOfValidWords f = new FindNumOfValidWords();
        List<Integer> numOfValidWords = f.findNumOfValidWordsB(words, puzzles);
        System.out.println(numOfValidWords);
    }

    public List<Integer> findNumOfValidWordsB(String[] words, String[] puzzles) {
        List<Integer> result=new ArrayList<>();
        //key是识别码，value是该识别码出现的次数
        Map<Integer, Integer> map = getMap(words);
        for(int i=0;i<puzzles.length;i++){
            String puzzle = puzzles[i];
            int count=0;
            for(int j=0;j<(1<<6);j++){
                int value=0;
                for(int k=0;k<6;k++){
                    if((1<<k&j)!=0){
                        value|=1<<(puzzle.charAt(k+1)-'a');
                    }
                }
                value|=1<<(puzzle.charAt(0)-'a');
                count+=map.getOrDefault(value,0);
            }
            result.add(count);
        }
        return result;
    }

    Map<Integer, Integer> getMap(String[] words){
        Map<Integer, Integer> map = new HashMap<>();
        for(String word:words){
            int value=0;
            for (int j = 0; j < word.length(); j++) {
                value|=1<<(word.charAt(j)-'a');
            }
            if(Integer.bitCount(value)<=7){
                map.put(value,map.getOrDefault(value,0)+1);
            }
        }
        return map;
    }



    //-------------解法一（超时）
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        int[][] wordHaves = new int[words.length][26];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                wordHaves[i][words[i].charAt(j) - 'a'] = 1;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < puzzles.length; i++) {
            String puzzle = puzzles[i];
            int count = 0;
            for (int j = 0; j < wordHaves.length; j++) {
                if (process(wordHaves[j], puzzle)) {
                    count++;
                }
            }
            result.add(count);
        }
        return result;
    }

    boolean process(int[] wordHave, String puzzle) {
        int[] have = new int[26];
        for (int i = 0; i < puzzle.length(); i++) {
            have[puzzle.charAt(i) - 'a'] = 1;
        }
        for (int i = 0; i < wordHave.length; i++) {
            if (wordHave[i] == 1 && have[i] == 0) {
                return false;
            }
        }
        if (wordHave[puzzle.charAt(0) - 'a'] == 0) {
            return false;
        }
        return true;
    }
}
