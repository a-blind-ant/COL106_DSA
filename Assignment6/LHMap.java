public class LHMap<T>{
	int size,count;
	T arr[];
	public LHMap()
	{
		count=0;
		size=113;
		arr=(T[])new Object[size];
	}

	public void add(T element)
	{
		int limit=(4*count)/3;
		if(size>limit)
		{ 
			int index=(element.hashCode())%size;
			for(int i=0;i<size;i++)
			{
				if(arr[index]==null)
					{
						arr[index]=element;
						break;
					}

				else
					index=(index+1)%size;

			}
			count++;
		}
		else
		{
			rehash();
			this.add(element);
		}
	}

	void rehash()
	{
		T[] arr1=(T[])new Object[2*size];
		int size1=2*size;
		for(int i=0;i<size;i++)
		{
			if(arr[i]!=null)
			{
				int index=(arr[i].hashCode())%size1;
				for(int j=0;j<size1;j++)
				{
					if(arr1[index]==null)
						{arr1[index]=arr[i];
							break;}
					else
						{index=(index+1)%size1;}
				}
			}
		}
		size=2*size;
		arr=(T[])new Object[size]; 
		for (int i=0;i<size;i++) 
		{
			if(arr1[i]!=null)
			arr[i]=arr1[i];
		}
	}

	public T search(T element)
	{
		T ans = null;
		int index=(element.hashCode())%size;
		int i = 0;
		for(i=0;i<size;i++)
		{
			if(arr[index] == null)
				continue;
			if(arr[index].equals(element))
			{
				ans=arr[index];
				break;
			}
			else
				index=(index+1)%size;
		}
		return ans;
	}
	
}