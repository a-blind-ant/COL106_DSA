public class tester
{
	public static void main(String args[])
	{
		Queue<Integer> q1=new Queue<Integer>();
		q1.enqueue(21);
		q1.enqueue(1);
		q1.enqueue(121);
		q1.enqueue(441);
		q1.enqueue(222);
		q1.enqueue(256);
		q1.enqueue(8987);
		q1.enqueue(68);
		q1.enqueue(67);
		System.out.println(q1.size());
		System.out.println(q1.dequeue());
		System.out.println(q1.dequeue());
		System.out.println(q1.dequeue());
		System.out.println(q1.dequeue());
		System.out.println(q1.dequeue());
		System.out.println(q1.dequeue());
		System.out.println(q1.dequeue());
		System.out.println(q1.dequeue());
		System.out.println(q1.dequeue());
		System.out.println(q1.dequeue());
		System.out.println(q1.dequeue());
		System.out.println(q1.size());
		q1.enqueue(783);
		q1.enqueue(8348);
		System.out.println(q1.dequeue());
		System.out.println(q1.dequeue());
		System.out.println(q1.dequeue());
		System.out.println(q1.size());
		
	}
}