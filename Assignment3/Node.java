public class Node<K,T>
{
	Pair<K,T> value;
	Node left = null;
	Node right = null;
	
	public Node(Pair<K,T> obj)
	{
		value = obj;
	}
}