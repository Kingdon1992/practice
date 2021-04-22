package sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wangqingsong
 * @since : 2020-09-04 16:20:25
 */
public class SortTestMain {
    public static void main(String[] args) throws Exception {
        List<BaseSort> sorts = getSorts();
        sorts.forEach(sort -> {
            try {
                sort.batchSort(100, 50000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static List<BaseSort> getSorts() {
        List<BaseSort> sorts = new ArrayList<>();
        sorts.add(new QuickSort());
        sorts.add(new QuickSort2());

        return sorts;
    }
}
