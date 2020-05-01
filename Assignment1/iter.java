import java.util.Iterator;

public class iter<T> implements Iterator<Position_<T>>{
	Position_<T> obj;

	public iter(LL<T> list)
	{
		obj = list.getHead();
	}

	public boolean hasNext()
	{
		if(obj == null)
		{
			return false;
		}
		return true;
	}

	public Position_<T> next()
	{
		if(this.hasNext())
		{
			Position_<T> temp = obj;
			obj = obj.after();
			return temp;
		}
		return null;
	}
}
