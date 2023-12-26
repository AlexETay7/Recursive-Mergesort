# Recursive Mergesort

I implemented a recursive mergesort sorting algorithm in Java. The mergesort is used to sort objects in a list that implements the IndexedUnsortedList interface. I implemented the mergesort algorithm twice, once using the compareTo() method (extends Comparable), and once using the compare() method (which extends Comparator).


# Recursive-Mergesort
****************
* Recursive Mergesort
* University Assignment
* Alex Taylor
**************** 

### ***OVERVIEW:***

The recursive mergesort algorithm is a divide-and-conquer sorting technique that operates by dividing an array into two halves, sorting each half independently, and then merging the sorted halves to produce a fully sorted array. Mergesort is known for its stable sorting behavior and has a time complexity of O(n log n) in the average and worst cases, making it efficient for large datasets.


### ***INCLUDED FILES:***

 * README - A brief overview of the program, how to run and compile it, related concepts, and testing information.


### ***COMPILING AND RUNNING:***

 In a terminal window, navigate to the directory containing all source files, compile all
 java files with the command:
 <pre>
 $ javac *.java
 </pre>

 Run the compiled ListTester class file with the command:
 <pre>
 $ java ListTester.java
 </pre>

 The console output will give the results after the program finishes.


### ***TESTING:***

A test class was provided to me by my professor, however, I modified the tester to run with a double-linked list I created instead of the single-linked list provided. The tester still works as intended.
