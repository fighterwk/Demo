package com.model_algorithm.sort;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/11/22
 */
public class Sort {


    /**
     * 快速排序
     *
     * @param a 数组的引用
     * @param l 排序的左下标
     * @param r 排序的右下标
     */
    static void quickSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }

        int i, j, x;
        i = l;
        j = r;
        x = a[i];

        // 1. 先从右到左进行查找比x小的值
        // 2. 找到后替换数据
        // 3. 替换数据后，从左到右进行查找比x小的值然后交换

        while (i < j) {
            // 从右到左
            while (i < j && a[j] > x) {
                j--;
            }

            // 找到替换数据
            if (i < j) {
//                a[i++] = a[j];
                a[i] = a[j];
                i++;
            }

            // 从左到右查找比x小的
            while (i < j && a[i] > x) {
                i++;
            }

            if (i < j) {
                a[j] = a[i];
            }
        }

        a[i] = x;

        quickSort(a, l, i - 1);
        quickSort(a, i + 1, r);

    }

    // 冒泡
    static void bubbleSort(int[] a, int l, int r) {
        for (int i = r; i > 0; i--) {
            for (int j = l; j < i; j++) {
                if (a[i] < a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }

    }

    // 选择排序
    static void selectSort(int[] a, int l, int r) {
        int i, j, min;


        for (i = l; i < r; i++) {
            min = i;
            // 查找最小的数据
            for (j = i + 1; j <= r; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }

            int temp = a[i];
            a[i] = a[min];
            a[min] = temp;
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{30, 40, 60, 10, 20, 50};
        System.out.println("原数据:");
        ArrayPrint.print(a);

        selectSort(a, 0, a.length - 1);
        System.out.println("排序后:");
        ArrayPrint.print(a);

//        bubbleSort(a, 0, a.length - 1);
//        System.out.println("排序后:");
//        ArrayPrint.print(a);

//        quickSort(a, 0, a.length - 1);
//        System.out.println("排序后:");
//        ArrayPrint.print(a);

    }

}
