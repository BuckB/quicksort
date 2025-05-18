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
            this.swap(array, 0, 1);
        }
        return array;
    }

    private void swap(int[] array, int greaterValueIndex, int lowerValueIndex) {
        int temp = array[greaterValueIndex];
        array[greaterValueIndex] = array[lowerValueIndex];
        array[lowerValueIndex] = temp;
    }

}
