import java.util.Iterator;
import java.io.*;

public class Student implements Student_{
	String name;
	String entryNo;
	String hostel;
	String department;
	String completedCredits;
	String cgpa;
	LL<CourseGrade> courseList;

	//Constructor
	public Student(String a, String b, String c, String d)
	{
		name = a;
		entryNo = b;
		hostel = c;
		department = d;
		this.courseList = new LL<CourseGrade>();
		//write for completedCredits and CGPA calculation		
	}

	//Courseiter class
	class Courseiter implements Iterator<CourseGrade>{		
		Position_<CourseGrade> obj;
		public Courseiter(LL<CourseGrade> courseList)
		{
			obj = courseList.getHead();
		}
		public boolean hasNext()
		{
			return (obj != null);
		}
		public CourseGrade next()
		{
			if(obj!=null)
			{				
				Position_<CourseGrade> temp = obj;
				obj = obj.after();
				return temp.value();				
			}
			return null;
		}
	}

	//Method to add a course
	public void addCourse(CourseGrade ob)
	{
		courseList.add(ob);
	}	

	//Inherited methods
	public Iterator<CourseGrade> courseList()
	{
		return new Courseiter(this.courseList);
	}
	public String name()
	{
		return name;
	}
	public String entryNo()
	{
		return entryNo;
	}
	public String hostel()
	{
		return hostel;
	}
	public String department()
	{
		return department;
	}
	public String completedCredits()
	{
		Iterator<CourseGrade> obj = this.courseList();
		Integer count = 0;
		while(obj.hasNext() && !obj.next().g.equals("I") && !obj.next().g.equals("E") && !obj.next().g.equals("F"))
		{
			count+=1;
		}
		count *= 3;
		completedCredits = count.toString();
		return completedCredits;
	}
	public String cgpa()
	{
		Iterator<CourseGrade> obj = this.courseList();
		Integer nmr = 0;
		Integer dmr = 0;
		while(obj.hasNext())
		{
			CourseGrade temp = obj.next();
			if(!temp.g.equals("I"))
			{nmr += temp.gradepoint;
			dmr++;}
		}
		if(dmr == 0)
			return "0.00";	
		Double cg = (double)nmr/(double)dmr;
		cgpa =cg.toString();
		cgpa = cgpa.format("%.2f", cg); 		
		return cgpa;
	} 
}