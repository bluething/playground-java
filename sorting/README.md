### Bubble Sort

* How it works: Repeatedly steps through the list, compares adjacent elements, and swaps them if they're in the wrong order
* Time Complexity: O(n²) average and worst case, O(n) best case
* Space Complexity: O(1)
* Stability: Stable
* Best for: Small datasets or nearly sorted data

### Selection Sort

* How it works: Divides the array into sorted and unsorted regions, repeatedly finds the minimum element from the unsorted region and moves it to the sorted region
* Time Complexity: O(n²) for all cases
* Space Complexity: O(1)
* Stability: Not stable
* Best for: Small datasets where memory is limited

### Insertion Sort

* How it works: Builds the sorted array one item at a time by inserting each element into its proper position
* Time Complexity: O(n²) average and worst case, O(n) best case
* Space Complexity: O(1)
* Stability: Stable
* Best for: Small datasets or nearly sorted data

### Merge Sort

* How it works: Divides the array into halves recursively, sorts them, then merges the sorted halves
* Time Complexity: O(n log n) for all cases
* Space Complexity: O(n)
* Stability: Stable
* Best for: Large datasets where stability is important

```java
    private static void mergeSortHelper(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSortHelper(arr, left, mid);
            mergeSortHelper(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
```   
How it works:

* The array keeps getting split in half until each subarray has 1 element.  
* Then the recursive calls start returning, merging the sorted halves together.

```text
[5, 3, 8, 4, 2]
  /          \
[5, 3, 8]   [4, 2]
 /    \       /  \
[5,3] [8]   [4] [2]

```