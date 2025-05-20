package com.buckb.study.quicksort;

public interface PivotStrategy {
    /**
     * Chooses and returns the index of the pivot element within the specified sub-array.
     * This method is responsible for determining *where* the pivot is, not for its value
     * or for physically moving it within the array.
     *
     * @param array The array being sorted.
     * @param low The starting index of the sub-array.
     * @param high The ending index of the sub-array.
     * @return The index of the chosen pivot element.
     */
    int choosePivot(int[] array, int low, int high);
}
