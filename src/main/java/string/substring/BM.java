package string.substring;

import java.util.Arrays;

/**
 * @author : wangqingsong
 * @since : 2020-10-20 11:30:08
 */
public class BM {
    private int BM(String text, String pattern) {
        int m = text.length();
        int n = pattern.length();
        int[] bm = bm(pattern);
        int skip;
        for (int i = 0; i <= m - n; i += skip) {
            skip = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    skip = j - bm[text.charAt(i + j)];
                    skip = skip < 0 ? 1 : skip;
                    break;
                }
            }
            if (skip == 0) {
                return i;
            }
        }
        return -1;
    }

    private int[] bm(String pattern) {
        int r = 256;
        int[] bm = new int[r];
        Arrays.fill(bm, -1);
        for (int i = 0; i < pattern.length(); i++) {
            bm[pattern.charAt(i)] = i;
        }
        return bm;
    }
}
