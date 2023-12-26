# Recursive-Mergesort
****************
* Recursive Mergesort
* University Assignment
* Alex Taylor
**************** 

### ***OVERVIEW:***

I implemented a recursive mergesort sorting algorithm in Java. The mergesort is used to sort objects in a list that implements the IndexedUnsortedList interface. I implemented the mergesort algorithm twice, once using the compareTo() method (extends Comparable), and once using the compare() method (which extends Comparator).


### ***INCLUDED FILES:***

 * IUDoubleLinkedList.java - A node-based doubly-linked implementation of the IndexedUnsortedList interface.
 * IndexedUnsortedList.java - Interface for an Iterable, Indexed, Unsorted List ADT. Implemented in IUDoubleLinkedList.java.
 * Node.java - A doubly-linked node class for linear data structures.
 * Sort.java - A class for sorting lists that implements the IndexedUnsortedList interface, using ordering defined by a class of objects in a list or a Comparator. As written uses a Mergesort 
 algorithm.
 * README - A brief overview of the program, how to run and compile it, related concepts, and testing information.


### ***COMPILING AND RUNNING:***

 In a terminal window, navigate to the directory containing all source files, compile all
 java files with the command:
 <pre>
 $ javac *.java
 </pre>

 Ignore the warnings produced in this process.

 Run the compiled SortTester class file with the command:
 <pre>
 $ java SortTester.java
 </pre>

 The console output will give the results after the program finishes.

### ***PROGRAM DESIGN AND IMPORTANT CONCEPTS*** ###
The recursive mergesort algorithm is a divide-and-conquer sorting technique that operates by dividing an array into two halves, sorting each half independently, and then merging the sorted halves to produce a fully sorted array. Mergesort is known for its stable sorting behavior and has a time complexity of O(n log n) in the average and worst cases, making it efficient for large datasets.


### ***TESTING:***

A test class was provided to me by my professor, however, I modified the tester to run with a double-linked list I created instead of the single-linked list provided. The tester still works as intended.
