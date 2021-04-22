package string.sort;

/**
 * 低位优先
 *
 * @author : wangqingsong
 * @since : 2020-10-12 10:38:38
 */
public class MSD {
    public static void main(String[] args) {
        String[] list = {
                "she",
                "sells",
                "seashells",
                "by",
                "the",
                "seashore",
                "the",
                "shells",
                "she",
                "sells",
                "are",
                "surely",
                "seashells"
        };
        sort(list, 0, list.length, 0);
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }
    }

    private static void sort(String[] list, int begin, int end, int index) {
        int[] count;
        int range = 256;
        count = new int[range + 2];
        for (int i = begin; i < end; i++) {
            int charIndex = charAt(list[i], index);
            count[charIndex + 2]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        String[] aux = new String[list.length];
        for (int i = begin; i < end; i++) {
            aux[count[charAt(list[i], index) + 1]++] = list[i];
        }
        for (int i = begin; i < end; i++) {
            list[i] = aux[i - begin];
        }
        for (int i = 2 ; i < range; i++) {
            if (count[i]>=count[i+1]-1){
                continue;
            }
            sort(list, begin + count[i], begin + count[i + 1], index + 1);
        }
    }

    private static int charAt(String str, int index) {
        if (index < str.length()) {
            return str.charAt(index);
        }
        return -1;
    }
}
