public class Queue<T>
{
	AL<T> q = new AL(1);
	int s = -1;
	int e = -1;
	public T enqueue(T element)
	{
		if(s==-1 && e==-1)
		{
			s = 0;
			e = 0;
		}
		else
		{
			e++;
		}
		return q.add(element);		
	}

	public T dequeue()
	{
		if(this.size()==0)
			return null;
		else if(e==s)
		{
			s++;
			return q.get(s-1);
		}
		else
		{
			s++;
			return q.get(s-1);
		}
	}

	public int size()
	{
		if(e==-1 && s==-1)
			return 0;
		return e-s+1;
	}
}