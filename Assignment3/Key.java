public class Key<K> implements Comparable<Key<K>>
{
	K first;
	K last;
	public Key(K f, K l)
	{
		this.first = f;
		this.last = l;
	}

	public String toString()
	{
		return first.toString()+last.toString();		
	}

	public int compareTo(Key<K> key_temp)
	{
		return first.toString().compareTo(key_temp.first.toString());
	}
}