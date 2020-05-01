//package ass2_COL106;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Buyer<V> extends BuyerBase<V> {
    public Buyer (int sleepTime, int catalogSize, Lock lock, Condition full, Condition empty, PriorityQueue<V> catalog, int iteration) {
        //TODO Complete the Buyer Constructor method
        // ...
    	this.catalog = catalog;
    	this.lock = lock;
    	this.full = full;
    	this.empty = empty;
    	this.setSleepTime(sleepTime);
    	this.setIteration(iteration);    	
    }
    public void buy() throws InterruptedException {
        lock.lock();
    	
    	NodeBase<V> n = null;
    	try {
            //TODO Complete the try block for consume method
            // ...
        
		while(catalog.isEmpty())
		{
			empty.await();
		}
        //empty.signalAll();
		n = catalog.dequeue();
		
		
		
	    System.out.print("Consumed "); // DO NOT REMOVE (For Automated Testing)
            n.show(); // DO NOT REMOVE (For Automated Testing)
            // ...
          //  System.out.println(this.catalog.size());
        full.signalAll();

	} catch (Exception e) {
            e.printStackTrace();
	} finally {
            //TODO Complete this block
		lock.unlock();
	}
    }
}
