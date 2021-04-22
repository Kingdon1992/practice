package string.substring;

/**
 * @author : wangqingsong
 * @since : 2020-10-13 15:11:19
 */
public class Normal {
    public static void main(String[] args) {
        String text = "hi you are my sun,yyo check now";
        String patten = "yoyo";
        int index = find(text, patten);
        System.out.println(index);
    }

    private static int find(String text, String patten) {
        int n = text.length();
        int m = patten.length();
        int i = 0;
        int j = 0;
        for (; i < n && j < m; i++) {
            if (text.charAt(i) == patten.charAt(j)) {
                j++;
            } else {
                i -= j;
                j = 0;
            }
        }
        if (j == m) {
            return i - j;
        } else {
            return j;
        }
    }
}
