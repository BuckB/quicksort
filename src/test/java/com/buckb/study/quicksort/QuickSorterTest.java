package com.buckb.study.quicksort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
