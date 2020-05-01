package PriorityQueue;
import java.util.ArrayList;

public class MaxHeap<T extends Comparable> implements PriorityQueueInterface<T> {
	//Nested class
	class Node<T extends Comparable>{
		T child;
		float priority;
		
		public Node(T baccha, float x){
			child = baccha;
			priority = x;
		}

		public float compareTo(Node<T> another){
			int munjal = this.child.compareTo(another.child);
			if(munjal != 0)
			{
				return (float)munjal;
			}
			else
			{
				return (another.priority-this.priority);
			}
		}
	}	


	ArrayList<Node<T>> heap = new ArrayList<Node<T>>();
	float count = 0; 

    @Override
    public void insert(T element) {
    	Node<T> ele = new Node(element, count);
    	//
    	heap.add(ele);
    	int i = heap.size() - 1;
    	while(i > 0)
    	{
    		int k = (i-1)/2;
    		if(heap.get(k).compareTo(heap.get(i)) < 0.00)
    		{
    			heap.set(k, heap.set(i, heap.get(k)));
    			i = k;
    		}
    		else
    		{
    			break;
    		}
    	}
    	//
    	count++;
    }

    @Override
    public T extractMax() {
    	if(heap.size() == 0)
    	{
    		return null;
    	}
    	Node<T> temp = heap.get(0);
    	heap.set(0, heap.get(heap.size()-1));
    	heap.remove(heap.size()-1);
    	int i = 0;
    	while(2*i + 2 < heap.size())
    	{
    		int c1 = 2*i + 1;
    		int c2 = 2*i + 2;
    		if(heap.get(c1).compareTo(heap.get(c2)) > 0)
    		{
    			if(heap.get(c1).compareTo(heap.get(i)) > 0)
    			{
    				heap.set(i, heap.set(c1, heap.get(i)));
    				i = c1;
    			}
    			else
    			{
    				break;
    			}
    		}
    		else
    		{
    			if(heap.get(c2).compareTo(heap.get(i)) > 0)
    			{
    				heap.set(i, heap.set(c2, heap.get(i)));
    				i = c2;
    			}
    			else
    			{
    				break;
    			}
    		}	    		
    	}
    	if(2*i + 1 == heap.size()-1)
    	{
			if(heap.get(2*i + 1).compareTo(heap.get(i)) > 0)
			{
				heap.set(i, heap.set(2*i + 1, heap.get(i)));
			}
    	}
        //
        return temp.child;
    }

    public int size()
    {
        return this.heap.size();
    }

}