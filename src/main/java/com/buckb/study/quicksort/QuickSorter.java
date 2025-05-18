package com.buckb.study.quicksort;

public class QuickSorter {

    public int[] sort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
        if (array.length <= 1) {
            return array;
        }
        if (array[0] > array[1]) {
            int temp = array[0];
            array[0] = array[1];
            array[1] = temp;
        }
        return array;
    }

}
