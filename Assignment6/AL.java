public class AL<T>
{
	LHMap<T> hashmap = new LHMap();
	class Element<T>
	{
		T value;
		public Element(T ele)
		{
			this.value = ele;
		}			
	}
	Element<T>[] a;
	int size = 0;
	int i = 0;

	//CONSTRUCTOR
	public AL(int n)
	{
		a = new Element[10];
		this.size = 10;
	}

	//ADD ELEMENT
	public T add(T temp)
	{	
		hashmap.add(temp);
		//Element to be inseretd at i
		if(i<=size-1)
		{
			a[i] = new Element(temp);
			i++;
			return temp;
		}
		else
		{
			Element<T>[] b = new Element[2*size];
			for(int k = 0; k<size; k++)
			{
				b[k] = a[k];
			}
			a = b;
			size = 2*size;
			a[i] = new Element(temp);
			i++;
			return temp;
		}
	}

	//GET ELEMENT
	public T get(int index)
	{
		if(index>size-1)
			return null;
		if(a[index] == null)
			return null;
		return a[index].value;
	}

	public T search(T ele)
	{
		return hashmap.search(ele);
	}

	
}