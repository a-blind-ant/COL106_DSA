package RedBlack;
import java.util.ArrayList;
import java.util.List;


public class RBTree<T extends Comparable, E> implements RBTreeInterface<T, E>  {

	RedBlackNode<T, E> root = null;
    @Override
    public void insert(T key, E value) {
    	//ROOT IS NULL
    	if(root==null)
    	{
    		root = new RedBlackNode<T, E>();
    		root.key = key;
    		root.value = value;
    		root.ch = 'b';
    		root.list.add(value);
    		return;
    	}
    	//Assert: ROOT IS NOT NULL
    	RedBlackNode<T ,E> temp = root;
    	RedBlackNode<T ,E> prev = root;
    	while(temp != null)
		{
			prev = temp;
			if(temp.key.compareTo(key) == 0)
				break;
			else if(temp.key.compareTo(key) < 0)
			{
				temp = temp.right;
			}
			else
				temp = temp.left;
		}
		if(temp != null)
		{
			temp.list.add(value);			
		}
		else
		{
			//Creating the new node
			RedBlackNode<T, E> naya = new RedBlackNode<T, E>();
			naya.key = key;
			naya.value = value;
			naya.list.add(value);
			naya.parent = prev;
			//Adding the node to the tree
			if(prev.key.compareTo(key) < 0)
			{
				prev.right = naya;
			}
			else
			{
				prev.left = naya;
			}
			//
			//boolean flag = true;
			while(naya.parent.ch == 'r')
			{	
				//BLACK UNCLE
				//System.out.println("hope");
				if(naya.parent.parent.right == null || naya.parent.parent.left == null || naya.parent.parent.right.ch == 'b' || naya.parent.parent.left.ch == 'b')
				{
					RedBlackNode<T, E> gf = naya.parent.parent;
					RedBlackNode<T, E> f = naya.parent;
					RedBlackNode<T, E> s = naya;
	//				System.out.println("Black uncle");
					restruct(s, f, gf, gf.parent);
					break;
				}
				//RED UNCLE
				else
				{
					//System.out.println("jetlag");
					if(naya.parent.parent == root)
					{
						root.left.ch = 'b';
						root.right.ch = 'b';
						break;
					}
					else{
					recolor(naya);
					naya = naya.parent.parent;}
					//break;
				}
			}
		}
    }

    public void recolor(RedBlackNode<T, E> x)
    {
    	//x.parent.ch = 'b';
    	if(x.parent.parent != root)
    		x.parent.parent.ch = 'r';
    	if(x.parent.parent.left != null)
    		x.parent.parent.left.ch = 'b';
    	if(x.parent.parent.right != null)
    		x.parent.parent.right.ch = 'b';
    }

    public void restruct(RedBlackNode<T, E> s, RedBlackNode<T, E> f, RedBlackNode<T, E> gf, RedBlackNode<T, E> mega_parent)
    {
    	//System.out.println("In rotation");
    	RedBlackNode<T, E> high = gf;
    	RedBlackNode<T, E> mid = f;
    	RedBlackNode<T, E> lo = null;
    	//t1, t2, t3, t4 -> redblacknode roots of the subtree
    	RedBlackNode<T, E> t1 = null; 
    	RedBlackNode<T, E> t2 = null; 
    	RedBlackNode<T, E> t3 = null; 
    	RedBlackNode<T, E> t4 = null;
    	//
    	if(gf.left == f)
    	{
    		if(f.left == s)
    		{
    			t1 = s.left;
    			t2 = s.right;
    			t3 = f.right;
    			t4 = gf.right;
    		}
    		else
    		{
    			t1 = f.left;
    			t2 = s.left;
    			t3 = s.right;
    			t4 = gf.right;
    		}
    	}
    	else
    	{
    		if(f.left == s)
    		{
    			t1 = gf.left;
    			t2 = s.left;
    			t3 = s.right;
    			t4 = f.right;
    		}
    		else
    		{
    			t1 = gf.left;
    			t2 = f.left;
    			t3 = s.left;
    			t4 = s.right;
    		}	
    	} 
    	//
    	if(mid.key.compareTo(high.key)>0)
    	{
    		mid = gf;
    		high = f;
    	}
    	if(s.key.compareTo(mid.key)>0)
    	{
    		lo = mid;
    		mid = s;
    	}
    	else
    	{
    		lo = s;
    	}
    	if(mid.key.compareTo(high.key)>0)
    	{
    		RedBlackNode<T, E> faaltu = mid;
    		mid = high;
    		high = faaltu;
    	}
    	/*mid.ch = 'b';
    	mid.parent = mega_parent;
    	mid.left = lo;
    	mid.right = high;*/
    	    	//
    	lo.ch = 'r';
    	lo.parent = mid;
    	lo.left = t1;
    	if(t1!=null)
    		t1.parent = lo;
    	lo.right = t2;
    	if(t2!=null)
    		t2.parent = lo;
    	//
    	high.ch = 'r';
    	high.parent = mid;
    	high.left = t3;
    	if(t3!=null)
    		t3.parent = high;
    	if(t4!=null)
    		t4.parent = high;
    	high.right = t4;
    	//
    	mid.ch = 'b';
    	mid.parent = mega_parent;
    	mid.left = lo;
    	mid.right = high;
    	//
    	if(mega_parent!=null)
    	{
    		if(mega_parent.left == gf)
    			mega_parent.left = mid;
    		else
    			mega_parent.right = mid;
    	}
    	else
    		{root = mid; root.parent = null;}

    }

    @Override
    public RedBlackNode<T, E> search(T key) {
		/*if(root!=null)
		{
			System.out.print("hi"+root.key);
			if(root.left!=null)
				System.out.print(" " + root.left.key);
			if(root.right!=null)
				System.out.print(" " + root.right.key);
		}*/
		RedBlackNode<T, E> temp = root;
		while(temp != null)
		{
		//	System.out.println("sleep");
			if(temp.key.compareTo(key) == 0)
				return temp;
			else if(temp.key.compareTo(key) < 0)
			{
				 //System.out.println("1");
				 temp = temp.right;
			}
			else
				{ temp = temp.left;}
		}
		return new RedBlackNode<T, E>();
    }

    /*public void bfs()
    {
    Queue<RedBlackNode> qu=new LinkedList<RedBlackNode>();
    System.out.println("_________________________________________________");
    qu.add(this.root);
    int c=1;
    while(c>0)
    {
    RedBlackNode q=qu.remove();
    c--;
    if(q.left!=null)
    {c++;
    qu.add(q.left);}
    if(q.right!=null)
    {qu.add(q.right);
    c++;}
    if(q==root)
    System.out.println(q.key+"  "+q.ch);
    if(q.parent!=null)
    System.out.println(q.parent.key+" "+q.parent.ch+"  "+q.key+"  "+q.ch);
    }
    System.out.println("_________________________________________________");
    }*/


}