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
}
