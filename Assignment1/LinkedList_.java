//LinkedList_ Interface
import java.util.Iterator;
public interface LinkedList_<T> {
	public Position_<T> add(T e);
	public Iterator<Position_<T>> positions();
	public int count();
}