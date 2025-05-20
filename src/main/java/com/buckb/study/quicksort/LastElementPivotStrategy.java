package com.buckb.study.quicksort;

public class LastElementPivotStrategy implements PivotStrategy {

    @Override
    public int choosePivot(int[] array, int low, int high) {
        return array[high];
    }

}
