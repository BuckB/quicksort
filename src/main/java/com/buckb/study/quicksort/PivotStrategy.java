package com.buckb.study.quicksort;

public interface PivotStrategy {
    int choosePivot(int[] array, int low, int high);
}
