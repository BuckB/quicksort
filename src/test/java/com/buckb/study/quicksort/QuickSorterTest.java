package com.buckb.study.quicksort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuickSorterTest {

    private QuickSorter quickSorter;

    @BeforeEach
    public void setup() {
        this.quickSorter = new QuickSorter();
    }

    @Test
    @DisplayName("Given a null array, when sort is called, then an IllegalArgumentException should be thrown")
    void givenNullArray_whenSort_thenThrowIllegalArgumentException() {
        int[] array = null;

        assertThatThrownBy(() -> this.quickSorter.sort(array))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Input array cannot be null");
    }

    @Test
    @DisplayName("Given an empty array, when sort is called, then return an empty array")
    void givenEmptyArray_whenSort_thenReturnEmptyArray() {
        int[] array = {};
        int[] result = this.quickSorter.sort(array);

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Given an array with one element, when sort is called, then return the same array")
    void givenArrayWithOneElement_whenSort_thenReturnSameArray() {
        int[] array = { 5 };
        int[] result = this.quickSorter.sort(array);

        assertThat(result).containsExactly(5);
    }

    @Test
    @DisplayName("Given an already sorted array with two elements, when sort is called, then return the sorted array")
    void givenAlreadySortedArrayWithTwoElements_whenSort_thenReturnSortedArray() {
        int[] array = { 1, 2 };
        int[] result = this.quickSorter.sort(array);

        assertThat(result).containsExactly(1, 2);
    }

    @Test
    @DisplayName("Given an unsorted array with two elements, when sort is called, then return the sorted array")
    void givenUnsortedArrayWithTwoElements_whenSort_thenReturnSortedArray() {
        int[] array = { 28, 13 };
        int[] result = this.quickSorter.sort(array);

        assertThat(result).containsExactly(13, 28);
    }

    @Test
    @DisplayName("Given an unsorted array with more than two elements, when sort is called, then return the sorted array")
    void givenUnsortedArrayWithMoreThanTwoElements_whenSort_thenReturnSortedArray() {
        int[] array = { 5, 3, 8, 1, 2 };
        int[] result = this.quickSorter.sort(array);

        assertThat(result).containsExactly(1, 2, 3, 5, 8);
    }

    @Test
    @DisplayName("Given an array with duplicate elements, when sort is called, then return the sorted array")
    void givenArrayWithDuplicateElements_whenSort_thenReturnSortedArray() {
        int[] array = { 5, 1, 4, 1, 5, 9, 2, 6 };
        int[] result = this.quickSorter.sort(array);

        assertThat(result).containsExactly(1, 1, 2, 4, 5, 5, 6, 9);
    }

    @Test
    @DisplayName("Given a QuickSorter with a LastElementPivotStrategy, when sorting, it should sort correctly")
    void givenLastElementPivotStrategy_whenSorting_thenSortsCorrectly() {
        // This is the key change: we're injecting a strategy
        this.quickSorter = new QuickSorter(new LastElementPivotStrategy());

        int[] array = { 3, 1, 4, 1, 5, 9, 2, 6 }; // A reasonably sized unsorted array with duplicates
        int[] expected = { 1, 1, 2, 3, 4, 5, 6, 9 };
        int[] result = this.quickSorter.sort(array);
        assertThat(result).isEqualTo(expected);
    }
}
