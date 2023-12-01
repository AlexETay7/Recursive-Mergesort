import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Doubly-linked node-based implementation of IndexedUnsortedList.
 * @author Alex Taylor CS221-Fa23
 */
public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;
    private int modCount;

    /**
     * Initialize a new empty double-linked list.
     */
    public IUDoubleLinkedList() {
        head = tail = null;
        size = 0;
        modCount = 0;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (T element : this) {
            str.append(element.toString());
            str.append(", ");
        }
        if (!isEmpty()) {
            str.delete(str.length() - 2, str.length());
        }
        str.append("]");
        return str.toString();
    }

    @Override
    public void addToFront(T element) {      
        ListIterator<T> list = listIterator();
        list.add(element);
    }

    @Override
    public void addToRear(T element) {
        Node<T> newNode = new Node<>(element);
        if (!isEmpty()) {
            tail.setNextNode(newNode);
            newNode.setPreviousNode(tail);
        } else {
            head = newNode;
        }
        tail = newNode;
        size++;
        modCount++;
    }

    @Override
    public void add(T element) {
        addToRear(element);
    }

    @Override
    public void addAfter(T element, T target) {         // START BY ATTACHING NEWNODE WITH ALL ADD METHODS!
        Node<T> newNode = new Node<>(element);
        Node<T> currentNode = head;
        while (currentNode != null) {
            if (currentNode.getElement().equals(target)) {
                Node<T> nextNode = currentNode.getNextNode();
                newNode.setNextNode(nextNode);
                currentNode.setNextNode(newNode);
                newNode.setPreviousNode(currentNode);
                if (nextNode != null) {                 // Next node was throwing null pointers because for empty lists it was being set to null
                    nextNode.setPreviousNode(newNode);
                } else {          // If the target is the current tail
                    tail = newNode;         // Update the tail
                }
                size++;
                modCount++;
                return;
            }
            currentNode = currentNode.getNextNode();
        }
        throw new NoSuchElementException();
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> newNode = new Node<>(element);

        if (index == 0) {
            newNode.setNextNode(head);
            if (isEmpty()) {
                tail = newNode;
            } else {
                head.setPreviousNode(newNode);
            }
            head = newNode;
        } else {
            Node<T> previousNode = head;
            for (int i = 0; i < index - 1; i++) {
                previousNode = previousNode.getNextNode();
            }
            
            if (previousNode == tail) {
                tail = newNode;
            } else {
                previousNode.getNextNode().setPreviousNode(newNode);
            }
            newNode.setPreviousNode(previousNode);
            newNode.setNextNode(previousNode.getNextNode());
            previousNode.setNextNode(newNode);
        }
        size++;
        modCount++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T retVal;
        if (size == 1) {
            retVal = head.getElement();
            head = tail = null;
        } else {
        retVal = head.getElement();
        head = head.getNextNode();            // Set new head to element in front of current head  
        }   
        size--;
        modCount++;
        return retVal;
    }

    @Override
    public T removeLast() {
        T retVal;
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (size == 1) {
            retVal = head.getElement();
            head = tail = null;
        } else {
            retVal = tail.getElement();
            tail.getPreviousNode().setNextNode(null);
            tail = tail.getPreviousNode();
        }
        size--;
        modCount++;
        return retVal;
    }

    @Override
    public T remove(T element) {
        Node<T> currentNode = head;
        while (currentNode != null && !currentNode.getElement().equals(element)) {
            currentNode = currentNode.getNextNode();
        }
        if (currentNode == null) {
            throw new NoSuchElementException();
        }
        
        if (currentNode == tail) {
            tail = currentNode.getPreviousNode();
        } else {
            currentNode.getNextNode().setPreviousNode(currentNode.getPreviousNode());
        }
        if (currentNode == head) {
            head = currentNode.getNextNode();
        } else {
            currentNode.getPreviousNode().setNextNode(currentNode.getNextNode());
        }
        size--;
        modCount++;
        return currentNode.getElement(); 
    }

    @Override
    public T remove(int index) {
        T retVal;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (index == 0) {
            retVal = head.getElement();
            if (size != 1) {
            head = head.getNextNode();
            } else {
                head = tail = null;
            }

        } else {

        Node<T> previousNode = head;
        for (int i = 0; i < index - 1; i++) {
            previousNode = previousNode.getNextNode();
        }
        if (previousNode.getNextNode() == tail) { // Removing the tail
            retVal = tail.getElement();
            tail = previousNode;
        } else {
            retVal = previousNode.getNextNode().getElement();
            previousNode.setNextNode(previousNode.getNextNode().getNextNode());
            previousNode.getNextNode().setPreviousNode(previousNode);
        }
    }
    
        size--;
        modCount++;
        return retVal;
    }

    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == 1) {
            head.setElement(element);
        }
        if (index == 0) {
            head.setElement(element);
        } else {
            Node<T> currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNextNode();
            }
            currentNode.setElement(element);
        }
        modCount++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextNode();
        }
        return currentNode.getElement();
    }

    @Override
    public int indexOf(T element) {
        int currentIndex = 0;
        Node<T> currentNode = head;
        while (currentNode != null && !currentNode.getElement().equals(element)) {
            currentNode = currentNode.getNextNode();
            currentIndex++;
        }
        if (currentNode == null) {
            currentIndex = -1;
        }
        return currentIndex;
    }

    @Override
    public T first() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.getElement();
    }

    @Override
    public T last() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.getElement();
    }

    @Override
    public boolean contains(T target) {
        return indexOf(target) > -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new DLLIterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new DLLIterator();
    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        return new DLLIterator(startingIndex);
    }

    private class DLLIterator implements ListIterator<T> {

        private Node<T> nextNode;
        private Node<T> lastReturnedNode;
        private int iterModCount;
        private int nextIndex;
        

        /**
         * Initialize iterator before first element.
         */
        public DLLIterator() {
            this(0);
        }

        /**
         * Initialize iterator before startingIndex element.
         * @param startingIndex
         */
        public DLLIterator(int startingIndex) {                     
            if (startingIndex > size || startingIndex < 0) {   // need to condition check startingIndex
                throw new IndexOutOfBoundsException();
            }
            if (startingIndex > size / 2) {         // start at the tail end if in second half, allows for O(n/2) instead of just O(n) efficiency
                nextNode = tail;
                if (startingIndex == size) {
                    nextNode = null;
                }
                for (int i = size - 1; i > startingIndex; i--) {
                    nextNode = nextNode.getPreviousNode();          //nextNode is NOT nullable here, because my other methods are written correctly
                }
            } else {
                nextNode = head;
                for (int i = 0; i < startingIndex; i++) {       
                    nextNode = nextNode.getNextNode();
                }
            }
            

            nextIndex = startingIndex;
            iterModCount = modCount;
            lastReturnedNode = null;
        }
        
        @Override
        public boolean hasNext() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return nextNode != null;
        }

        @Override
        public T next() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T retVal = nextNode.getElement();
            lastReturnedNode = nextNode;
            nextNode = nextNode.getNextNode();
            nextIndex++;
            return retVal;
        }

        @Override
        public boolean hasPrevious() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return nextNode != head;
        }

        @Override
        public T previous() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (nextNode == null) {
                nextNode = tail;
            } else {
                nextNode = nextNode.getPreviousNode();
            }
            lastReturnedNode = nextNode;
            nextIndex--;
            return nextNode.getElement();
        }

        @Override
        public int nextIndex() {      
            
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            int toRet;
            if (nextIndex == size) {
                toRet = size;
            } else {
                toRet = nextIndex;
            }
            return toRet;
        }

        @Override
        public int previousIndex() {        
            
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            int toRet;
            if (nextIndex == 0) {
                toRet = -1;
            } else {
                toRet = nextIndex - 1;
            }
            return toRet;
        }

        @Override
        public void remove() {          
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            } 
            if (lastReturnedNode == null) {
                throw new IllegalStateException();
            }
            if (lastReturnedNode == head) {
                head = lastReturnedNode.getNextNode();
            } else {
                lastReturnedNode.getPreviousNode().setNextNode(lastReturnedNode.getNextNode());
            }
            if (lastReturnedNode == tail) {
                tail = lastReturnedNode.getPreviousNode();
            } else {
                lastReturnedNode.getNextNode().setPreviousNode(lastReturnedNode.getPreviousNode());
            }
            if (lastReturnedNode == nextNode) {
                nextNode = nextNode.getNextNode();
            } else {
                nextIndex--;
            }
            lastReturnedNode = null;
            size--;
            modCount++;
            iterModCount++;
        }

        @Override
        public void set(T e) {                      
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            } 
            if (lastReturnedNode == null) {
                throw new IllegalStateException();
            }
            lastReturnedNode.setElement(e);
            modCount++;
            iterModCount++;
        }

        @Override
        public void add(T e) { 
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }                 
            Node<T> newNode = new Node<T>(e);
            newNode.setNextNode(nextNode);
            if (isEmpty()) {
                head = tail = newNode;
            } else if (nextNode == null) {
                tail.setNextNode(newNode);
                newNode.setPreviousNode(tail);
                tail = newNode;
            } else if (nextNode == head) {
                head.setPreviousNode(newNode);
                head = newNode;
            } else {
                nextNode.getPreviousNode().setNextNode(newNode);
                newNode.setPreviousNode(nextNode.getPreviousNode());
                nextNode.setPreviousNode(newNode);
            }
            size++;
            modCount++;
            iterModCount++;
            nextIndex++;
            lastReturnedNode = null;
        }
        
    }

    
}