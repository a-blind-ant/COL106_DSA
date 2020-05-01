import java.lang.Math;

public class HashBST<K extends Comparable<K>, T> implements MyHashTable_<K,T>
{
	int size;
	Node<K,T>[] hashtable;
	
	public HashBST(int size)
	{
		this.size = size;
		hashtable = new Node[this.size];
	}

	//HASH FUNCTION
	public static long djb2(String str, int hashtableSize) 
	{ 
    	long hash = 5381; 
    	for (int i = 0; i < str.length(); i++) 
    	{ 
        	hash = ((hash << 5) + hash) + str.charAt(i); 
   		} 
    	return Math.abs(hash) % hashtableSize; 
	}
	
	//INSERTION
	public int insert(K key, T obj)
	{
		/*if(this.contains(key))
			return -1;*/
		long index = djb2(key.toString(), this.size);
		//System.out.println(index);
		int count = 1;
		Node<K,T> root = hashtable[(int)index];
		Node<K,T> prev = hashtable[(int)index];
		if(root == null)
		{
			hashtable[(int)index] = new Node<K,T>(new Pair<K, T>(key,obj));
			return 1;
		}
		while(root!=null)
		{
			prev = root;
			if(key.toString().compareTo(root.value.a.toString())==0)
			{
				return -1;
			}
			if(key.compareTo(root.value.a)<0)
			{
				root = root.left;
				//System.out.print("L");				
				count++;
			}
			else
			{
				root = root.right;
				//System.out.print("R");
				count++;
			}
		}
		//System.out.println(prev.value.toString());		
		if(key.compareTo(prev.value.a)<0 && prev.left == null)
		{
			prev.left = new Node<K,T>(new Pair<K, T>(key, obj));
		}
		else
		{
			prev.right = new Node<K,T>(new Pair<K, T>(key, obj));
			//prev.right = new Node<K,T>(obj);
		}
		return count;
	}

	// CONTAINS
	public boolean contains(K key) 
	{
		long index = djb2(key.toString(), this.size);
		Node<K,T> root = hashtable[(int)index];
		boolean answer = false;
		while(root != null)
		{
			if(key.toString().compareTo(root.value.a.toString())==0)
			{
				answer = true;
				break;
			}
			else if(key.compareTo(root.value.a)<0)
			{
				root = root.left;
			}
			else
			{
				root = root.right;
			}	
		}
		return answer;
	}

	//UPDATION
	public int update(K key, T obj)
	{
		long index = djb2(key.toString(), this.size);
		Node<K,T> root = hashtable[(int)index];
		Integer count = 1;
		while(root != null)
		{
			if(key.toString().compareTo(root.value.a.toString()) == 0)
			{
				root.value.b = obj;
				return count;
			}
			else if(key.compareTo(root.value.a)<0)
			{
				root = root.left;
				count++;
			}
			else
			{
				root = root.right;
				count++;
			}	
		}
		return -1;
	}

	//DELETION INCOMPLETE
	public int delete(K key)
	{
		/*if(!this.contains(key))
			return -1;*/
		long index = djb2(key.toString(), this.size);
		Node<K,T> root = hashtable[(int)index];
		Node<K,T> prev = hashtable[(int)index];
		int ans = 1;
		while(root != null)
		{
			if(key.toString().compareTo(root.value.a.toString())==0)
			{
			//	temp = root;
				break;
			}
			else if(key.compareTo(root.value.a)<0)
			{
				prev = root;
				root = root.left;
				ans++;
			}
			else
			{
				prev = root;
				root = root.right;
				ans++;
			}	
		}

		if(root == null)
			return -1;

		if(root == hashtable[(int)index])
		{
			if(root.left == null && root.right == null)
			{
				hashtable[(int)index] = null;
				return 1;
			}
			else if(root.left == null && root.right != null)
			{
				hashtable[(int)index] = root.right;
				return 2;
			}
			else if(root.left != null && root.right == null)
			{
				hashtable[(int)index] = root.left;
				return 2;
			}
			else
			{
				Node<K,T> temp = root.right;
				ans++;
				Node<K,T> temp_prev = root;
				while(temp.left!=null)
				{
					temp_prev = temp;
					temp = temp.left;
					ans++;
				}
				if (temp_prev.left == temp) 
					{if(temp.right!=null) ans++; temp_prev.left = temp.right;}		
				else 
					{if(temp.right!=null) ans++; temp_prev.right = temp.right;}
				hashtable[(int)index].value = temp.value;			
				return ans;
			}	
		}

		else
		{	
			if(root.left == null && root.right == null)
			{
				if(prev.right == root)
					prev.right = null;
				else
					prev.left = null;
				return ans;
			}
			else if(root.left == null && root.right != null)
			{
				if(prev.right == root)
				{
					prev.right = root.right;
				}
				else
				{
					prev.left = root.right;
				}
				ans++;
				return ans;
			}
			else if(root.left != null && root.right == null)
			{
				if(prev.right == root)
				{
					prev.right = root.left;
				}
				else
				{
					prev.left = root.left;
				}
				ans++;	
				return ans;
			}
			else
			{
				Node<K,T> temp = root.right;
				ans++;
				Node<K,T> temp_prev = root;
				while(temp.left!=null)
				{
					temp_prev = temp;
					temp = temp.left;
					ans++;
				}
				if(temp_prev.left == temp)
					{if(temp.right!=null) ans++; temp_prev.left = temp.right;}
				else
					{if(temp.right!=null) ans++; temp_prev.right = temp.right;}
				if(prev.left == root)				
					prev.left.value = temp.value;
				else
					prev.right.value = temp.value;			
				return ans;
			}
		}
	}

	//GET
   public T get(K key) throws NotFoundException
   {
		long index = djb2(key.toString(), this.size);
		int ans = 1;
		Node<K,T> root = hashtable[(int)index];
		while(root != null)
		{
			if(key.toString().compareTo(root.value.a.toString())==0)
			{
				break;
			}
			else if(key.compareTo(root.value.a)<0)
			{
				root = root.left;
				ans++;
			}
			else
			{
				root = root.right;
				ans++;
			}	
		}
		if(root==null)
		{
			throw new NotFoundException();
		}
		return root.value.b;			
   }

   //ADDRESS
   public String address(K key) throws NotFoundException
   {
   		long index = djb2(key.toString(), this.size);
   		Node<K,T> root = hashtable[(int)index];	
   		String ans = index + "-";
   		while(root != null)
		{
			if(key.toString().compareTo(root.value.a.toString())==0)
			{
				break;
			}
			else if(key.compareTo(root.value.a)<0)
			{
				root = root.left;
				ans = ans + "L";
			}
			else if(key.compareTo(root.value.a)>=0)
			{
				root = root.right;
				ans = ans + "R";
			}	
		}
		if(root==null)
		{
			throw new NotFoundException();
		}
		return ans;
   } 

}