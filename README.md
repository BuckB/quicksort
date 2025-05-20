# Quicksort Algorithm in Java using TDD

This project implements the Quicksort algorithm in Java, adhering to Test-Driven Development (TDD) principles, clean code practices and SOLID guidelines.<br>
This README provides a roadmap for our implementation.

**Stack:**

* Java 21
* Logback (for logging, if necessary)
* JUnit 5 (for testing)
* AssertJ (for fluent assertions)

## 1. Understanding Quicksort

### How it Works

Quicksort is a highly efficient, comparison-based, divide-and-conquer sorting algorithm. Its basic steps are:

1.  **Choose a Pivot:** Select an element from the array, called the pivot. The choice of pivot can significantly impact performance. Common strategies include:
    * Picking the first element.
    * Picking the last element (our choice for simplicity in the initial implementation).
    * Picking a random element.
    * Picking the median of three elements.
2.  **Partitioning:** Rearrange the array so that all elements smaller than the pivot come before it, and all elements greater than the pivot come after it. Elements equal to the pivot can go on either side. After partitioning, the pivot is in its final sorted position.
3.  **Recursion:** Recursively apply the above steps to the sub-array of elements with smaller values and separately to the sub-array of elements with greater values.

### Performance

* **Best Case and Average Case Time Complexity:** $O(n \log n)$, where $n$ is the number of elements in the array. This occurs when the pivot selection consistently divides the array into roughly equal halves.
* **Worst Case Time Complexity:** $O(n^2)$. This occurs when the pivot selection consistently results in highly unbalanced partitions (e.g., picking the smallest or largest element as the pivot in an already sorted or reverse-sorted array).
* **Space Complexity:** $O(\log n)$ for the average case (due to the recursion stack). $O(n)$ in the worst case.

## 2. Breaking Down the Problem

To implement Quicksort using TDD, we'll break the problem into smaller, testable units:

1.  **`QuickSorter` Class:** The main class that will house the `sort` method.
2.  **`sort(int[] arr)` method:** The public-facing method that users will call to sort an array. This method will handle initial calls and edge cases (e.g., null or empty arrays).
3.  **`quickSortRecursive(int[] arr, int low, int high)` method:** A private helper method that implements the recursive logic of the Quicksort algorithm. It will take the array and the indices `low` and `high` to define the current sub-array being processed.
4.  **`partition(int[] arr, int low, int high)` method:** A private helper method responsible for selecting a pivot and rearranging the elements in the sub-array (from `low` to `high`) around this pivot. It will return the final index of the pivot element.

## 3. Clean Code and SOLID Principles

Throughout the development, we will strive to:

* **Clean Code:**
    * Meaningful names for classes, methods, and variables.
    * Small, focused methods.
    * Clear and concise comments where necessary (but self-documenting code is preferred).
    * Consistent formatting.
* **SOLID Principles:**
    * **Single Responsibility Principle (SRP):** Each class and method will have a single, well-defined responsibility. The `QuickSorter` class sorts, the `partition` method partitions.
    * **Open/Closed Principle (OCP):** While our initial implementation will be concrete, we'll keep in mind that extensions (like different pivot strategies) should ideally be possible without modifying existing, working code (e.g., through strategy pattern, though perhaps overkill for this specific exercise unless explicitly requested).
    * **Liskov Substitution Principle (LSP):** Not directly applicable here as we are not dealing with a deep inheritance hierarchy for the sorting logic itself.
    * **Interface Segregation Principle (ISP):** If we were to define interfaces (e.g., `Sorter`), they would be lean and focused.
    * **Dependency Inversion Principle (DIP):** We'll depend on abstractions if we introduce them. For this core algorithm, it's less prominent unless we abstract out, for example, the comparison logic or pivot strategy.

## 4. TDD Approach and Use Cases

We will follow the Red-Green-Refactor cycle of TDD:

1.  **Red:** Write a failing test for a small piece of functionality (a specific use case).
2.  **Green:** Write the minimum amount of code necessary to make the test pass.
3.  **Refactor:** Improve the code (e.g., for clarity, efficiency, adherence to clean code principles) while ensuring all tests still pass.

### Use Cases (in order of implementation):

We'll start with the core `sort` method and build up the functionality.

**Use Case 1: Sorting a Null Array**
* **Given:** A null array.
* **When:** The `sort` method is called.
* **Then:** The method should not throw a `NullPointerException` but handle it gracefully, perhaps by doing nothing or logging a warning. For this exercise, we'll define "graceful" as not modifying the input and throwing an  `IllegalArgumentException` as it's a clear signal of invalid input.

**Use Case 2: Sorting an Empty Array**
* **Given:** An empty array.
* **When:** The `sort` method is called.
* **Then:** The array remains empty, and no exception is thrown.

**Use Case 3: Sorting an Array with a Single Element**
* **Given:** An array with one element.
* **When:** The `sort` method is called.
* **Then:** The array remains unchanged, and no exception is thrown.

**Use Case 4: Sorting an Already Sorted Array (Two Elements)**
* **Given:** An already sorted array with two elements (e.g., `{10, 20}`).
* **When:** The `sort` method is called.
* **Then:** The array remains unchanged. This will start forcing the implementation of the `partition` and recursive calls.

**Use Case 5: Sorting a Reverse Sorted Array (Two Elements)**
* **Given:** A reverse sorted array with two elements (e.g., `{20, 10}`).
* **When:** The `sort` method is called.
* **Then:** The array is sorted (e.g., `{10, 20}`).

**Use Case 6: Sorting an Array with Three Elements (Simple Case)**
* **Given:** An array like `{3, 1, 2}`.
* **When:** The `sort` method is called.
* **Then:** The array is sorted `{1, 2, 3}`.

**Use Case 7: Sorting an Array with Duplicate Elements**
* **Given:** An array with duplicate elements (e.g., `{5, 1, 4, 1, 5, 9, 2, 6}`).
* **When:** The `sort` method is called.
* **Then:** The array is sorted correctly, handling duplicates.

**Use Case 8: Sorting a Larger, Unsorted Array**
* **Given:** A larger array with elements in random order.
* **When:** The `sort` method is called.
* **Then:** The array is fully sorted.

**Use Case 9 (Optional/Refinement): Pivot Selection Strategy**
* Initially, we'll use the last element as the pivot. We might explore other strategies (like median-of-three) later and refactor if necessary, ensuring all tests still pass. This is more of a performance optimization and refinement step.

## TDD Approach for Pivot Selection Strategy (Design Pattern Integration):

To introduce flexibility for different pivot selection strategies, we will employ the **Strategy Design Pattern**. This allows us to define an interface for pivot selection and then implement various strategies as concrete classes, which can be injected into the `QuickSorter`. This adheres to the Open/Closed Principle (OCP) by making the `QuickSorter` open for extension (new pivot strategies) but closed for modification (no need to change its core sorting logic when a new strategy is added).

Our TDD steps for this will be:
1.  **Red (Test for Design Flexibility):** Write a test that attempts to construct the `QuickSorter` with a `PivotStrategy` interface (e.g., `LastElementPivotStrategy`). This test will initially fail because the `QuickSorter` constructor doesn't support this injection, and the `PivotStrategy` interface/class doesn't exist in `main` yet.

2.  **Green (Implement Interface and Constructor):** Create the `PivotStrategy` interface and a concrete `LastElementPivotStrategy` class. Modify the `QuickSorter` to accept a `PivotStrategy` in its constructor and use it in the `partition` method. This will make the test compile and pass, bringing us to a "Green" state.

3.  **Refactor (Integrate and Clean Up):** Ensure all existing tests still pass. Refactor the `QuickSorter` and related classes for clarity and adherence to SOLID principles. We'll specifically ensure the `partition` method delegates pivot selection to the injected strategy.
