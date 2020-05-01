//This class implements the Queue
public class Queue<V> implements QueueInterface<V>{

 //TODO Complete the Queue implementation
 private NodeBase<V>[] queue;
 private int capacity, currentSize, front, rear;
	
 public Queue(int capacity) {    
     this.capacity = capacity;
     queue = new  Node[capacity];
     currentSize = 0;
     front = -1;
     rear = -1;
 }

 public int size() {
     return this.currentSize;
 }

 public boolean isEmpty() {
     return (currentSize==0);
 }
	
 public boolean isFull() {
     return (currentSize==capacity);
 }

 public void enqueue(Node<V> node) {
	if(!isFull())
	{
		queue[currentSize] = node;
		currentSize++;
	}    	 
}

 public NodeBase<V> dequeue() {
    if(!isEmpty())
    {
    	NodeBase<V> run = queue[0];
    	for(int i=0;i<currentSize-1;i++)
    	{
    		queue[i] = queue[i+1];
    	}
    	currentSize--;
    	return run;
    }
    return null;
 }

}

