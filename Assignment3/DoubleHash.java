import java.lang.Math; 

public class DoubleHash<K, T> implements MyHashTable_<K,T>
{
	int size;
	Node2<T>[] hashtable;
	public DoubleHash(int size)
	{
		hashtable = new Node2[size];
		this.size = size; 
	}

	//HASH FUNCTIONS
	public static long djb2(String str, int hashtableSize)
	{ 
    	long hash = 5381; 
    	for (int i = 0; i < str.length(); i++) 
    	{ 
        	hash = ((hash << 5) + hash) + str.charAt(i); 
    	} 
    	return Math.abs(hash) % hashtableSize; 
	}

	public static long sdbm(String str, int hashtableSize) 
	{ 
   		long hash = 0; 
    	for (int i = 0; i < str.length(); i++) 
    	{ 
        	hash = str.charAt(i) + (hash << 6) + (hash << 16) - hash; 
    	} 
    	return Math.abs(hash) % (hashtableSize - 1) + 1; 
	} 

	//INSERTION
	public int insert(K key, T obj)
	{
		/*if(this.contains(key))
			return -1;*/
		int i = 0;
		int index;
		while(i<=this.size)
		{
			index = ((int)djb2(key.toString(), size) + i*(int)sdbm(key.toString(), size)) %size;
			if (hashtable[index]==null)
			{
				hashtable[index] = new Node2<T>(obj);
				break;
			}
			else if(hashtable[index].value == null)
			{
				hashtable[index].value = obj;
				hashtable[index].flag = true;
				break;
			}
			else
			{
				i++;
			}
		}
		if(i>this.size)
			return -1;
	//	System.out.println(index + "thisis krishan");
		return (i+1);
	}

	//CONTAINS
	public boolean contains(K key)
	{
		int i = 0;
		boolean jawab = false;
		while(i<=this.size)
		{
			int index = ((int)djb2(key.toString(), size) + i*(int)sdbm(key.toString(), size)) %size;
			if(hashtable[index] == null)
			{
				jawab = false;
				break;
			}
			else if(hashtable[index].value==null)
			{
				i++;
			}
			else
			{
				if(hashtable[index].value.toString().equals(key.toString()))
				{
					jawab = true;
					break;
				}
				else
				{
					i++;
				}
			}
		}
		return jawab;
	}

	//UPDATION
	public int update(K key,T obj)
	{
		int i = 0;
		Node2<T> temp = null;
		while(i<=this.size)
		{
			int index = ((int)djb2(key.toString(), size) + i*(int)sdbm(key.toString(), size)) %size;
			if(hashtable[index] == null)
			{
				temp = null;
				break;
			}
			else if(hashtable[index].value == null)
			{
				i++;
			}
			else
			{
				if(hashtable[index].value.toString().equals(key.toString()))
				{
					temp = hashtable[index];
					break;
				}
				else
				{
					i++;
				}
			}
		}
		if(temp == null || i>this.size)
		{			
			return -1;
		}
		else
		{
			temp.value = obj;
			return (i+1);
		}
	}

	//DELETION
	public int delete(K key)
	{
		if(!this.contains(key))
			return -1;
		int i = 0;
		boolean bool = false;
		while(i<=this.size)
		{
			int index = ((int)djb2(key.toString(), size) + i*(int)sdbm(key.toString(), size)) %size;
			if(hashtable[index] == null)
			{
				bool = false;
				break;
			}
			else if(hashtable[index].value == null)
			{
				i++;
			}
			else
			{
				if(hashtable[index].value.toString().equals(key.toString()))
				{
					bool = true;
					hashtable[index].value = null;
					hashtable[index].flag = true;
					break;
				}
				else
				{
					i++;
				}
			}
		}
		if(!bool)
		{
			return -1;
		}
		else
		{
			return (i+1);
		}
	}

	//GET
	public T get(K key) throws NotFoundException
	{
		int i = 0;
		T temp = null;
		while(i<=this.size)
		{
			int index = ((int)djb2(key.toString(), size) + i*(int)sdbm(key.toString(), size)) %size;
			if(hashtable[index] == null)
			{
				temp = null;
				break;
			}
			else if(hashtable[index].value == null)
			{
				i++;
			}
			else
			{
				if(hashtable[index].value.toString().equals(key.toString()))
				{
					temp = hashtable[index].value;
					break;
				}
				else
				{
					i++;
				}
			}
		}
		if(temp == null)
		{
			throw new NotFoundException();
		}
		else
		{
			return temp;
		}
	}

	//ADDRESS
	public String address(K key) throws NotFoundException
	{
		int i = 0;
		String ans = "E";
		while(i<=this.size)
		{
			int index = ((int)djb2(key.toString(), size) + i*(int)sdbm(key.toString(), size)) %size;
			if(hashtable[index] == null)
			{
				ans = "E";
				break;
			}
			else if(hashtable[index].value == null)
			{
				i++;
			}
			else
			{
				if(hashtable[index].value.toString().equals(key.toString()))
				{
					ans = Integer.toString(index);
					break;
				}
				else
				{
					i++;
				}
			}
		}
		if(ans.equals("E"))
		{
			throw new NotFoundException();
		}
		else
		{
			return ans;
		}
	}

}