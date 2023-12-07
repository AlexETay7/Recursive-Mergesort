# Recursive Mergesort
*University Assignment*
I implemented a recursive mergesort sorting algorithm in Java. The mergesort is used to sort objects in a list that implements the IndexedUnsortedList interface. I implemented the mergesort algorithm twice, once using the compareTo() method (extends Comparable), and once using the compare() method (which extends Comparator).

### ***Overview***
The recursive mergesort algorithm is a divide-and-conquer sorting technique that operates by dividing an array into two halves, sorting each half independently, and then merging the sorted halves to produce a fully sorted array. Mergesort is known for its stable sorting behavior and has a time complexity of O(n log n) in the average and worst cases, making it efficient for large datasets.

A test class was provided to me by my professor, however, I modified the tester to run with a double-linked list I created instead of the single-linked list provided. The tester still works as intended.


