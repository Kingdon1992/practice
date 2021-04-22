package string.sort;

/**
 * 低位优先
 *
 * @author : wangqingsong
 * @since : 2020-10-12 10:38:38
 */
public class LSD {
    public static void main(String[] args) {
        String[] list = {
                "4PGC938",
                "2IYE230",
                "3CIO720",
                "1ICK750",
                "1OHV845",
                "4JZY524",
                "1ICK750",
                "3CIO720",
                "1OHV845",
                "1OHV845",
                "2RLA629",
                "2RLA629",
                "3ATW723"
        };
        String[] sort = sort(list);
        for (int i = 0; i < sort.length; i++) {
            System.out.println(sort[i]);
        }
    }

    private static String[] sort(String[] list) {
        if (list.length == 0) {
            return list;
        }

        int strLen = list[0].length();

        int[] count;
        String[] aux;

        for (int i = strLen - 1; i >= 0; i--) {
            count = new int[256 + 1];
            for (int j = 0; j < list.length; j++) {
                count[list[j].charAt(i) + 1]++;
            }
            for (int j = 1; j < count.length; j++) {
                count[j] += count[j - 1];
            }
            aux = new String[list.length];
            for (int j = 0; j < list.length; j++) {
                aux[count[list[j].charAt(i)]++] = list[j];
            }
            list = aux;
        }
        return list;
    }

    private static String[] sort(String[] list, int w) {
        int[] count;
        for (int j = w - 1; j >= 0; j--) {
            count = new int[256 + 1];
            for (int i = 0; i < list.length; i++) {
                count[list[i].charAt(j) + 1]++;
            }
            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }
            String[] aux = new String[list.length];
            for (int i = 0; i < list.length; i++) {
                aux[count[list[i].charAt(j)]++] = list[i];
            }
            list = aux;
        }
        return list;
    }
}
