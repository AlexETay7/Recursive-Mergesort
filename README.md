# Recursive Mergesort
I implemented a recursive mergesort sorting algorithm in java. The mergesort is used to sort objects in a list that implements the IndexedUnsortedList interface. I implemented the mergesort algorithm twice, once using the compareTo() method, and once using the compare() method of a comparator.

A test class was provided to me by my professor, however, I modified the tester to run with a double-linked list I created instead of the single-linked list provided. The tester still works as intended.

The recursive mergesort algorithm is a divide-and-conquer sorting technique that operates by dividing an array into two halves, sorting each half independently, and then merging the sorted halves to produce a fully sorted array. The process begins by recursively dividing the array into smaller subarrays until each subarray contains only one element, which is inherently sorted. Afterward, the algorithm merges adjacent pairs of these sorted subarrays, combining them into larger sorted subarrays until the entire array is sorted. The merging step involves comparing elements from the two subarrays and placing them in order. 

Mergesort is known for its stable sorting behavior and has a time complexity of O(n log n) in the average and worst cases, making it efficient for large datasets.
