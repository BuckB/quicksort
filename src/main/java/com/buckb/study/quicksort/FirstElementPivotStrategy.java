package com.buckb.study.quicksort;

public class FirstElementPivotStrategy implements PivotStrategy {

    @Override
    public int choosePivot(int[] array, int lowIndex, int highIndex) {
        return lowIndex;
    }
}
