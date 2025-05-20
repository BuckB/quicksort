package com.buckb.study.quicksort;

public class QuickSorter {

    private PivotStrategy pivotStrategy;

    public QuickSorter() {
        this(new LastElementPivotStrategy());
    }

    public QuickSorter(PivotStrategy pivotStrategy) {
        this.pivotStrategy = pivotStrategy;
    }

    public int[] sort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
        if (array.length <= 1) {
            return array;
        }
        this.quickSort(array, 0, array.length - 1);
        return array;
    }

    private void swap(int[] array,
            int greaterValueIndex, int lowerValueIndex) {
        int temp = array[greaterValueIndex];
        array[greaterValueIndex] = array[lowerValueIndex];
        array[lowerValueIndex] = temp;
    }

    private int partition(int[] array,
            int lowIndex, int highIndex) {
        int pivot = this.pivotStrategy.choosePivot(array, lowIndex, highIndex);
        int baseIndex = lowIndex - 1;
        for (int step = lowIndex; step < highIndex; step++) {
            if (array[step] <= pivot) {
                baseIndex++;
                if (baseIndex != step) {
                    this.swap(array, baseIndex, step);
                }
            }
        }
        this.swap(array, baseIndex + 1, highIndex);
        return baseIndex + 1;
    }

    private void quickSort(int[] array,
            int lowIndex, int highIndex) {
        if (lowIndex < highIndex) {
            int pivotIndex = this.partition(array, lowIndex, highIndex);
            this.quickSort(array, lowIndex, pivotIndex - 1);
            this.quickSort(array, pivotIndex + 1, highIndex);
        }
    }
}
