public class Student implements Student_
{
	String fname,lname,hostel,department,cgpa, key;
	
	public Student(String fname,String lname,String hostel,String department,String cgpa)
	{
		this.fname = fname;
		this.lname = lname;
		this.hostel = hostel;
		this.department = department;
		this.cgpa = cgpa;
		key = fname+lname;
	}
	public String fname()
	{
		return fname;
	}
	public String lname()
	{
		return lname;
	}
	public String hostel()
	{
		return hostel;
	}
	public String department()
	{
		return department;
	}
	public String cgpa()
	{
		return cgpa;
	}
	public String toString()
	{
		return fname()+lname();
	}
}