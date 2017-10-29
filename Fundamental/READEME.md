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
