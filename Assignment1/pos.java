//Pos CLASS
public class pos<T> implements Position_<T>{
	private T data;
	pos<T> nextnode;

	public pos(){data = null;
		nextnode = null;}
	public pos(T data, pos<T> x){
		nextnode = x;
		this.data = data;	
	}
	
	public T value(){
		return data;
	}

	public Position_<T> after(){
		Position_<T> answer= nextnode;
		return answer;
	}
}
