## Stack

```java
public class Stack {
	private int maxSize;
	private char[] stackArray;
	private int top;

	public Stack(int size) {
		this.maxSize = size;
		this.stackArray = new char[maxSize];
		this.top = -1;
	}

	public void push(char j) {
		if(isFull()) {
			System.out.println("This stack is already full");
		} else {
			top++;
			stackArray[top] = j;
		}
	}

	public char pop() {
		if(isEmpty()) {
			System.out.println("This stack is already empty");
			return '0';
		}else {
			int old_top = top;
			top--;
			return stackArray[old_top];
		}
	}

	public char peek() {
		return stackArray[top];
	}

	public boolean isEmpty() {
		return (top == -1);
	}

	public boolean isFull() {
		return (maxSize - 1 == top);
	}
}

public class App {

	public static void main(String[] args) {
		Stack theStack = new Stack(3);
		theStack.push(20);
		theStack.push(40);
		theStack.push(60);
		theStack.push(80);

		while(!theStack.isEmpty()) {
			long value = theStack.pop();
			System.out.println(value);
		}
		/*
		This stack is already full
		60
		40
		20
		*/

		System.out.println(reverseString("HELLO"));
		// OLLEH
	}

	public static String reverseString(String str) {
		int stackSize = str.length(); // get the max stack size
		Stack theStack = new Stack(stackSize);
		for(int j = 0; j < str.length(); j++) {
			char ch = str.charAt(j);
			theStack.push(ch);
		}

		String result = "";
		while(!theStack.isEmpty()) {
			char ch = theStack.pop();
			result = result + ch; // appending to the output
		}

		return result;
	}
}
```
----

## Queue

```java
public class Queue {
	private int maxSize; // Initializes the set number of slots
	private long[] queArray; // slots to maintain the data
	private int front; // this will be the index position for the element in the front
	private int rear; // going to be the index position for the lement at the back of the line
	private int nItems; // counter to maintain the nunber of items in our queue

	public Queue(int size) {
		this.maxSize = size;
		this.queArray = new long[size];
		front = 0; // index position of the first slot of the array
		rear = -1; // There is no item in the array yet to be considered as the last item.
		nItems = 0; // we don't have elements in the array yet
	}

	public void insert(long j) {
		if(rear == maxSize - 1) {
			rear = -1;
		}

		rear++;
		queArray[rear] = j;
		nItems++;
	}

	public long remove() { // remove item from the front of the queu
		long temp = queArray[front];
		front++;
		if(front == maxSize) {
			front = 0; // we can set front back to the 0th index so that we can utilize the entire array again.
		}

		nItems--;
		return temp;
	}

	public long peekFront() {
		return queArray[front];
	}

	public boolean isFull() {
		return (nItems == maxSize);
	}

	public void view() {
		System.out.print("[");
		for(int i = 0; i < queArray.length; i++) {
			System.out.print(queArray[i] + " " );
		}
		System.out.print("]");
	}
}

public class App {

	public static void main(String[] args) {
		Queue myQueue = new Queue(5);
		myQueue.insert(100);
		myQueue.insert(1000);
		myQueue.insert(14);
		myQueue.insert(12);
		myQueue.insert(44);
		myQueue.insert(33);
		myQueue.view();
	}
}

// [33 1000 14 12 44 ]
```

------


## Singly LinkedList

```java
public class Node {
	public int data;
	public Node next;

	public void displayNode() {
		System.out.println("{ " + data + " } ");
	}
}

public class SinglyLinkedList {
	private Node first;

	public SinglyLinkedList() {

	}

	public boolean isEmpty() {
		return (first == null);
	}

	// used to insert at the beginning of the list
	public void insertFirst(int data) {
		Node newNode = new Node();
		newNode.data = data;
		newNode.next = first;
		first = newNode;
	}

	public void insertLast(int data) {
		Node current = first;

		while(current.next != null) {
			current = current.next;
		}

		Node newNode = new Node();
		newNode.data = data;
		current.next = newNode;
	}

	public Node deleteFirst() {
		Node temp = first;
		first = first.next;
		return temp;
	}

	public void displayList() {
		System.out.println("List: (first --> last ");

		Node current = first;
		while(current != null) {
			current.displayNode();
			current = current.next;
		}
		System.out.println();
	}
}

public class App {

	public static void main(String[] args) {
		SinglyLinkedList myList = new SinglyLinkedList();
		myList.insertFirst(100);
		myList.insertFirst(52);
		myList.insertFirst(99);
		myList.insertFirst(88);
		myList.insertLast(99999);

		myList.displayList();
	}
}
/*
List: (first --> last
{ 88 }
{ 99 }
{ 52 }
{ 100 }
{ 99999 }
*/
```


## Circular LinkedList

```java
public class Node {
	public int data;
	public Node next;

	public void displayNode() {
		System.out.println("{ " + data + " } ");
	}
}

public class CircularLinkedList {
	private Node first;
	private Node last;

	public CircularLinkedList() {
		first = null;
		last = null;
	}

	private boolean isEmpty() {
		return first == null;
	}

	public void insertFirst(int data) {
		Node newNode = new Node();
		newNode.data = data;

		if(isEmpty()) {
			last = newNode;
		}

		newNode.next = first; // newNode --> old first
		first = newNode;
	}

	public void insertLast(int data) {
		Node newNode = new Node();
		newNode.data = data;

		if(isEmpty()) {
			first = newNode;
		} else {
			last.next = newNode; // the next value of the last node will point to the new node
			last = newNode; // we make the new node we create be the last one in the list
		}
	}

	public int deleteFirst() {
		int temp = first.data;

		if(first.next == null) {
			last = null;
		}

		first = first.next; // first will point to old's next value
		return temp;
	}

	public void displayList() {
		System.out.println("List (first --> last)");
		Node current = first;
		while(current != null) {
			current.displayNode();
			current = current.next;
		}
		System.out.println();
	}
}



	public class App {
		public static void main(String[] args) {
			CircularLinkedList myList = new CircularLinkedList();
			myList.insertFirst(100);
			myList.insertFirst(50);
			myList.insertFirst(99);
			myList.insertFirst(88);
			myList.insertLast(999999);

			myList.displayList();
		}
	}
/*
List (first --> last)
{ 88 }
{ 99 }
{ 50 }
{ 100 }
{ 999999 }
*/
```

-------

## Doubly LinkedList

```java
public class Node {
	public int data;
	public Node next;
	public Node previous;

	public void displayNode() {
		System.out.print("{ " + data + " }");
	}
}

public class DoublyLinkedList {
	private Node first;
	private Node last;

	public DoublyLinkedList() {
		this.first = null;
		this.last = null;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void insertFirst(int data) {
		Node newNode = new Node();
		newNode.data = data;

		if(isEmpty()) {
			last = newNode; // if empty, last will refer to the new Node being created
		} else {
			first.previous = newNode;
		}

		newNode.next = first; // the new node's next field will point to the old first
		this.first = newNode;
	}

	public void insertLast(int data) {
		Node newNode = new Node();
		newNode.data = data;

		if(isEmpty()) {
			first = newNode;
		} else {
			last.next = newNode; // assigning old last to newNode
			newNode.previous = last;
		}

		this.last = newNode; // the linkedList's last field should point to the new node
	}

	// Assume non-empty list
	public Node deleteFirst() {
		Node temp = first;
		if(first.next == null) { // there is only one item in the list
			last = null;
		} else {
			first.next.previous = null;
		}

		first = first.next; // we are assigning the reference of the node following the old first node to the first field in the linked list object
		return temp; // return the deleted old first node;
	}

	// assume non-empty list
	public Node deleteLast() {
		Node temp = last;
		if(first.next == null) { // we only have one node in this list
			first = null;
		} else {
			last.previous.next = null; // the last node's previous node's next field will point to null
		}

		last = last.previous;
		return temp;
	}

	// assume non-empty list
	public boolean insertAfter(int key, int data) { // key is the node's data we looking for to insert a new data
		Node current = first; // we start from the beginning of the list
		while(current.data != key) {
			current = current.next;
			if(current == null) {
				return false;
			}
		}

		Node newNode = new Node();
		newNode.data = data;

		if(current == last) {
			current.next = null;
			last = newNode;
		} else {
			newNode.next = current.next; // new node's next field should point to the node ahead of the current node
			current.next.previous = newNode; // the node ahead of current, it's previous field should point to the new node
		}

		newNode.previous = current;
		current.next = newNode;

		return true;
	}

	// assume non-empty list
	public Node deleteKey(int key) {
		Node current = first;
		while(current.data != key) {
			current = current.next;
			if(current == null) {
				return null;
			}
		}

		if(current == first) {
			first = current.next; // make the first field point to the node following current since current
		} else {
			current.previous.next = current.next;
		}

		if (current == last) {
			last = current.previous;
		} else {
			current.next.previous = current.previous;
		}

		return current;
	}

	public void displayForward() {
		System.out.print("List (first --> last): ");
		Node current = first; // start from the beginning
		while(current != null) {
			current.displayNode(); // call the display method of the node
			current = current.next; // move to the next node
		}
		System.out.println();
	}

	public void displayBackwrard() {
		System.out.print("List (last --> first): ");
		Node current = last; // start from the beginning
		while(current != null) {
			current.displayNode(); // call the display method of the node
			current = current.previous; // move to the previous node
		}
		System.out.println();
	}
}


public class App {

	public static void main(String[] args) {
		DoublyLinkedList theList = new DoublyLinkedList();
		theList.insertFirst(22);
		theList.insertFirst(44);
		theList.insertFirst(66);
		theList.insertLast(11);
		theList.insertLast(33);
		theList.insertLast(55);
		theList.displayForward();
		theList.displayBackwrard();
		theList.deleteFirst();
		theList.deleteLast();
		theList.deleteKey(11);
		theList.displayForward();
		theList.insertAfter(22, 77);
		theList.insertAfter(33, 88);
		theList.displayForward();
	}
}


/*
List (first --> last): { 66 }{ 44 }{ 22 }{ 11 }{ 33 }{ 55 }
List (last --> first): { 55 }{ 33 }{ 11 }{ 22 }{ 44 }{ 66 }
List (first --> last): { 44 }{ 22 }{ 33 }
List (first --> last): { 44 }{ 22 }{ 77 }{ 33 }{ 88 }
*/
```


-----

##  Linear Search and Binary Search

```java
package algo.linearsearch;

public class App {

	public static void main(String[] args) {
		System.out.println(recursiveLinearSearch(new int[] {4, 48, 4, 28, 34, 76, 9, 3}, 0, 28));
		/*
		 index at: 0
		 index at: 1
		 index at: 2
		 3
		*/

		System.out.println(binarySearch(new int[] {1,2,3,4,7,9,12,18}, 12)); // 6
		System.out.println(recursiveBinarySearch(new int[] {1,2,3,4,7,9,12,18}, 0, 7, 9)); // 5
	}

	public static int linearSearch(int [] a, int x) {
		for(int i = 0; i < a.length; i++) {
			if(a[i] == x) {
				return i;
			}
		}

		return -1;
	}

	public static int recursiveLinearSearch(int [] a, int i, int x) {
		if( i > a.length - 1) { // if evaluates to true, x was not found in the array
			return -1;
		} else if (a[i] == x) {
			return i;
		} else {
			System.out.println("index at: " + i);
			return recursiveLinearSearch(a, i + 1, x);
		}
	}

	public static int binarySearch(int [] a, int x) {
		int p = 0;
		int r = a.length - 1;

		while(p <= r) {
			int q = (p + r)/ 2;
			if(x < a[q]) r = q - 1;
			else if (x > a[q]) p = q + 1;
			else return q;
		}

		return -1;
	}

	public static int recursiveBinarySearch(int [] a, int p, int r, int x) {
		int q = (p + r)/2;
		if (p > r) return -1;
		else if (x < a[q]) return recursiveBinarySearch(a, p, q - 1, x);
		else if(x > a[q]) return recursiveBinarySearch(a, q + 1, r, x);
		else return q;
	}
}

```
