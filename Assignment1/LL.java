//LL class
import java.util.Iterator;
public class LL<T> implements LinkedList_<T>{
	
	private pos<T> head;
	private int size;
	pos<T> tail;
	
	public LL(){
		head = null;
		tail = null;
		size = 0;
	}

	public pos<T> getHead(){
		return head;
	}
	
	public Position_<T> add(T e){
		if(head == null)
		{
			Position_<T> x = new pos<T>(e, null);
			head = (pos<T>)x;
			tail = head;
			size++;
			return x;
		}
		else
		{
			Position_<T> next = new pos<T>(e, null);
			tail.nextnode = (pos<T>)next;
			tail = (pos<T>)next;
			size++;
			return next;
		}
	}

	public int count(){
		return size;
	}

	public Iterator<Position_<T>> positions()
	{
		Iterator<Position_<T>> x = new iter<T>(this) ;	
		return x;	
	}


	//--------

	/**public static void main(String[] args)
	{
		LL list = new LL();
		Integer i;
		i = 10;
		Position_<Integer> f = list.add(i);
		i =12;
		list.add(12);
		System.out.println(list.count() + "   " + f.value());
		Iterator<Position_<Integer>> amokh = list.positions();
		System.out.println(amokh.next().value());

	}*/
}


