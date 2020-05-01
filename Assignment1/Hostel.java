import java.util.Iterator;
//Hotsel class
public class Hostel implements Entity_{
	String hname;
	LL<Student> studentList;
	//Constructor
	public Hostel(String name)
	{
		hname = name;
		studentList = new LL<Student>();		
	}
	
	//Studiter class
	class Studiter implements Iterator<Student_>{
		Position_<Student> obj;
		public Studiter(LL<Student> list)
		{
			obj = list.getHead();
		}
		public boolean hasNext()
		{
			return !(obj==null);
		}
		public Student next()
		{
			Position_<Student> temp = obj;
			obj = obj.after();
			return temp.value();
		}
	}

	//Inherited functions
	public String name()
	{
		return hname;
	}
	public Iterator<Student_> studentList()
	{
		return new Studiter(studentList);
	}

	//Method to add student to studentList
	public void addStudent(Student x)
	{
		studentList.add(x);
	}  
}

