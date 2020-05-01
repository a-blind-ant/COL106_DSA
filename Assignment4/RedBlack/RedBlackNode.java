package RedBlack;

import Util.RBNodeInterface;

import java.util.ArrayList;
import java.util.List;

public class RedBlackNode<T extends Comparable, E> implements RBNodeInterface<E> {

	T key = null;
	E value = null;
	List<E> list = new ArrayList<E>();
	char ch = 'r';
	RedBlackNode<T, E> parent = null;
    RedBlackNode<T, E> left = null;
    RedBlackNode<T, E> right = null;

    @Override
    public E getValue() {
        if(this.key == null)
            return null;
        return this.value;
    }

    @Override
    public List<E> getValues() {
        if(this.key == null)
        {
            return null;
        }
        return this.list;     
    }    
}
