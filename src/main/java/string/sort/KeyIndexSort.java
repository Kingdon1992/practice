package string.sort;

import lombok.AllArgsConstructor;

/**
 * 键索引计数法
 *
 * @author : wangqingsong
 * @since : 2020-10-10 17:48:42
 */
public class KeyIndexSort {
    public static void main(String[] args) {
        Person[] list = new Person[20];
        list[0] = new Person("Anderson", 2);
        list[1] = new Person("Brown", 3);
        list[2] = new Person("Davis", 3);
        list[3] = new Person("Garcia", 4);
        list[4] = new Person("Harris", 1);
        list[5] = new Person("Jackson", 3);
        list[6] = new Person("Johnson", 4);
        list[7] = new Person("Jones", 3);
        list[8] = new Person("Martin", 1);
        list[9] = new Person("Martinez", 2);
        list[10] = new Person("Miller", 2);
        list[11] = new Person("Moore", 1);
        list[12] = new Person("Robinson", 2);
        list[13] = new Person("Smith", 4);
        list[14] = new Person("Taylor", 3);
        list[15] = new Person("Thomas", 4);
        list[16] = new Person("Thompson", 4);
        list[17] = new Person("White", 2);
        list[18] = new Person("Williams", 3);
        list[19] = new Person("Wilson", 4);
        Person[] sort = sort(list);
        for (int i = 0; i < sort.length; i++) {
            System.out.println(sort[i]);
        }
    }

    private static Person[] sort(Person[] list) {
        int[] count = {0, 0, 0, 0, 0};
        for (int i = 0; i < list.length; i++) {
            count[list[i].number]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        Person[] aux = new Person[list.length];
        for (int i = 0; i < list.length; i++) {
            Person person = list[i];
            aux[count[person.number - 1]++] = person;
        }
        return aux;
    }

    @AllArgsConstructor
    static class Person {
        private String name;
        private int number;
    }
}
