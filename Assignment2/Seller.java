//package ass2_COL106;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Seller<V> extends SellerBase<V> {
	
    public Seller (int sleepTime, int catalogSize, Lock lock, Condition full, Condition empty, PriorityQueue<V> catalog, Queue<V> inventory) {
        //TODO Complete the constructor method by initializing the attributes
        // ...
    	this.catalog = catalog;
    	this.lock = lock;
    	this.full = full;
    	this.empty = empty;
    	this.setSleepTime(sleepTime);
    	this.inventory = inventory;
    }
    
    public void sell() throws InterruptedException {
            lock.lock();
	
	
    	try {
            //TODO Complete the try block for produce method
            // ...
            //System.out.println(lock);

    		while(catalog.isFull())
    		{
    			full.await();
    		}
            //full.signalAll();
    		//catalog.enqueue(x);
            //System.out.println("MUNJAL"+this.catalog.size());

            if(inventory.isEmpty()!=true)
              catalog.enqueue((Node<V>)inventory.dequeue());

    		empty.signal();    		
	} catch(Exception e) {
            e.printStackTrace();
	} finally {
		lock.unlock();
            //TODO Complete this block
	}		
    }
}

