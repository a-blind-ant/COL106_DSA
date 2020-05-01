1.Bases.java:
abstract class NodeBase<V> //Generic Base class for nodes of Priority Queue and Queue
    Variables:
    	protected int priority; // Stores Priority of the node
    	protected V value;	// Stores value of the node
    Methods:	
    	public abstract int getPriority(); // return the priority of the node
    	public abstract V getValue();	// returns the value stored by the node
    	public void show()	// outputs the priority of the node

interface QueueInterface<V> 	//Generic Interface for Queue
	Methods:
	    public int size(); // Returns the size, i.e. the number of elements in the queue at present
	    public boolean isEmpty(); // Returns true if the queue is empty, else returns false
	    public boolean isFull();  // Returns true if the queue is full, else returns false
	    public void enqueue(Node<V> item); // Adds an item to the rear of the queue
	    public NodeBase<V> dequeue(); // Removes an item from the front of the queue

abstract class BuyerBase<V> implements Runnable //Generic Abstract base class for Buyers which can implement threads
	Variables:
		protected PriorityQueue<V> catalog; // The shared priority queue
   		protected Lock lock; // Shared lock
    	protected Condition full, empty; // Shared condition variables
   		private int sleepTime; // Sleep duration (in ms) for current thread
    	private int iteration; // No. of iterations for buyer threads
	Methods:
    	public abstract void buy() throws InterruptedException; // Implements the buy method from shared queue
    	public void run()	//Implements the run method for thread
    	public void setSleepTime(int sleepTime)	//Sets sleep time for Buyer threads
    	public void setIteration(int iteration) //Sets total no. of iterations

abstract class SellerBase<V> implements Runnable //Generic Abstract base class for Sellers which can implement threads
	Variables:    
	    protected PriorityQueue<V> catalog; // Shared priority queue
	    protected Lock lock; //Shared lock
	    protected Condition full, empty; // Shared condition variables
	    private int sleepTime; // Sleep duration (in ms) for current thread
	    protected Queue<V> inventory; // List of items (shared between sellers)
	Methods:
    	public abstract void sell() throws InterruptedException; // Implements the sell (produce) method to shared queue
    	public void run()	//Implements the run() method for threads
    	public void setSleepTime(int sleepTime)	//Sets the sleep time for seller threads


2.Node.java
public class Node<V> extends NodeBase<V> //Node class inherits from the base class
	Methods:
		public Node(int priority, V value)	//Constructor
		public int getPriority()	//Returns Node priority
		public V getValue()		//Returns node value
	(Besides the class has inherited varidables and methods)


3.Queue.java
public class Queue<V> implements QueueInterface<V>	//Generic class for queue implementation
	Variables:
		private NodeBase<V>[] queue;	//Array to be implemented as the queue 
 		private int capacity, currentSize, front, rear;	//Self-explanatory variable names
	Methods:
		public Queue(int capacity) 	//Constructor
		(Besides, it contains methods implemented from the base interface)


4.PriorityQueue.java
public class PriorityQueue<V> implements QueueInterface<V>	//Generic class fro Priority queue implementation
    Variables:
    	private NodeBase<V>[] queue; //Array to implement PriorityQueue
    	private int capacity, currentSize; //Variables to store maximum and current sizes
    Methods:	
    	public PriorityQueue(int capacity) //Contructor
    	public void display() //Prints the priority of each node in the PriorityQueue
		(Besides, it contains methods implemented from the base interface)


5.Buyer.java		
public class Buyer<V> extends BuyerBase<V>	//Generic class for implementaion of buyers
    Methods:
    	public Buyer (int sleepTime, int catalogSize, Lock lock, Condition full, Condition empty, PriorityQueue<V> catalog, int iteration) //Constructor
    (Besides, it contains variables and methods inherited from the base class)
    [Rough definition of buy() -> The method has been made synchronized with the help of locks and conditions, making the thread wait in case of empty queue.
     Buyer thread then removes an element from the catalog and signals the threads via full condition, notifying availability of free space in the catalog.]


6.Seller.java
public class Seller<V> extends SellerBase<V> //Generic class for implementation of sellers
	Methods:
	    public Seller (int sleepTime, int catalogSize, Lock lock, Condition full, Condition empty, PriorityQueue<V> catalog, Queue<V> inventory) //Constructor
    (Besides, it contains variables and methods inherited from the base class)
    [Rough definition of sell() -> The method has been made synchronized with the help of locks and conditions, making the thread wait in case of a full 
    queue. Seller thread then adds an element from the inventory to the catalog and signals the threads via empty condition, notifying availability of items\
     in the catalog.]


7.Assignment2Driver.java
public class Assignment2Driver //Driver class to execute the entire code
	Variables:    
	    public int catalogSize; //Maintains the capacity of catalog
	    public int numBuyers;	//Number of buyers
	    public int numSellers;	//Number of sellers
	    public int sellerSleepTime, buyerSleepTime;	//sleep times for seller and buyer threads 
	    public Queue<Item> inventory;	//Queue to be shared by sellers for adding elements to the catalog
	Methods:
    	public static void main(String[] args) throws InterruptedException //Main method (Execution begins here)
    	[Main method takes the inputs from the user and initialises the corresponding variables/ objects. It then declares and envokes threads for different
    	sellers and buyers to begin the execution.]


    	
		    


