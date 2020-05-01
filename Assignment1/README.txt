1. 	public interface Position_<T> // Here T is a class, meant to make the implementation generic 
		Methods:
			1.public T value();
			2.public Position_<T> after();
	Class pos<T> implements Position_<T> //implements the functions declared in the above interface
		Attributes:
			1.T data; //Meant to store data held by the class instance
			2.pos<T> nextnode; //Meant to store a reference to the node this instance holds the refernece to
		Methods:
			1.public pos<T>(); //Constructor1 to create a new object with all atributes as null
			2.public pos<T>(T data, pos<T> x); //Constructor2 to intialize both data and reference to the next node
			3.public T value(); // Function which can be called by an object to get the data of class "T" stored in it
			4.public Position_<T> after(); // Function defined to allow an object to get the reference to the next node


2.	public class iter<T> implements Iterator<Position_<T>> //Custom iterator to iterate over the to be defined linked lists
		Attributes:
			1.Position_<T> obj; //Reference to next node in the list
		Methods:
			1.public boolean hasNext(); //Tests the presence of next node 
			2.public Position_<T> next(); //Returns the next node moving the iterator forward
			

3.	public interface LinkedList_<T>
		Methods:
			1.public Position_<T> add(T e);
			2.public Iterator<Position_<T>> positions(); 
			3.public int count();
	Class LL<T> implements LinkedList_<T>
		Attributes:
			1.pos<T> head; //Reference to the first object of pos<T>
			2.int size; //number of pos<T> type objects in the linked list
			3.pos<T> tail; //Reference to the latest added element of linked List
		Methods:
			1.public LL(); //Constructor with head and tail as null and size as zero
			2.public pos<T> getHead(); //Returns the head of a given linked list
			3.public Position_<T> add(T e); //Adds a node with value T 
			4.public int count(); //Returns the number of nodes in the list
			5.public Iterator<Position_<T>> positions(); //Returns an iterator to iterate over the nodes of the linked list


4.	public interface GradeInfo_ 
		Methods:
			1.static enum LetterGrade(); //Declares an enumeration with possible grades
			2.public static int gradepoint(GradeInfo_.LetterGrade grade) //Returns numeric equivalent of each grade
			3.public default LetterGrade grade() //gives 'I' for all courses initially 
	

	public class GradeInfo implements GradeInfo_
		Attributes
			1.GradeInfo_.LetterGrade g; //Instantiates the enum defined above
		Methods:
			1.public GradeInfo(GradeInfo_.LetterGrade grade) //Constructor
			
			
5.	public interface CourseGrade_ 		// Tuple of course and grade
		Methods:
			1.public String coursetitle();   	 // Returns course title 
			2.public String coursenum();   	 // Returns course code, e.g., COL106 
			3.public GradeInfo_ grade();   	 // Returns student's letter grade
	public class CourseGrade implements CourseGrade_{
		Attributes:
			1.String coursetitle; //Course name (Eg.Data Structures and algorithms)
			2.String coursenum; //Course Code (Eg,COL106)
			3.GradeInfo_ grade; //Student's grade in the course
			4.String g; //String equivalent of grade
			5.int gradepoint; //Mathematical equivalent of the grade
		Methods:
			1.public CourseGrade(String a, String b, String c); //Constructor
			2.public String coursetitle(); //Returns the course title
			3.public String coursenum(); //Returns the course code
			4.public GradeInfo_ grade(); //Returns the course grade
			5.public String g(); //Returns the String equivalent of grade


6.	public interface Student_ 
	Methods:
		1.public String name(); //returns Student's name               	
		2.public String entryNo(); //Returns student's Entry number           	
		3.public String hostel(); //Returns student's hostel             	
		4.public String department(); //Returns student's department         	
		5.public String completedCredits(); //Returns the credits completed by the student until previous semmester   	
		6.public String cgpa(); //Returns the student's cgpa until the previous sem   		
		7.public Iterator<CourseGrade> courseList(); //Returns iterator to the list of past/present courses
	public lass Student
	Attributes:
		1.String name;
		2.String entryNo;
		3.String hostel;
		4.String department;	//All the attributes are self-explanatory
		5.String completedCredits;
		6.String cgpa;
		7.LL<CourseGrade> courseList;
	Methods:
		1.public Student(String a, String b, String c, String d); // Constructor
		2.public void addCourse(CourseGrade ob); //Adds a course to the student's course list
		(Besides, we have the methods implemented from the above interface)
	Nested class:
		1.class Courseiter implements Iterator<CourseGrade> //Class to declare iterator for the list of courses done by the student
		
		
7.	public interface Entity_ 
		Methods:
			1.public String name(); //Stores the name of the entity (Course/Department/Hostel)                 
			2.public Iterator<Student_> studentList(); (Returns iterator to the student list of the entity)
	public class Hostel implements Entity_
		Attributes:
			1.String hname; //Hostel's name
			2.LL<Student> studentList; //Students' linked list
		Methods:
			1.public Hostel(String name); //Constructor
			2.public void addStudent(Student x); //Adds Student to the student list
			(Besides methods have been implemented from the above interface)
		Nested class:
			1.class Studiter implements Iterator<Student_> //Declares iterator to iterate over the corresponding student list
	public class Course implements Entity_ //Similar to class Hostel
	public class Department implements Entity_ //Similar to class Hostel
	
	
8.	public class Assignment1 //The class which runs the entire code
		Variables:
			1.static LL<Hostel> allHostels = new LL<Hostel>(); //List of all hostels, initialized to be empty
			2.static LL<Course> allCourses = new LL<Course>(); //List of all courses, initialized to be empty
			3.static LL<Department> allDepartments = new LL<Department>(); //List of all departments, initialized to be empty
			4.static LL<Student> Allstudents = new LL<Student>(); //List of all students, initialized to be empty
		Methods:
			1.private static  void getData(String file1, String file2); //Method to read data from the student record file and course file
			2.private static void answerQueries(String file3); //Method to read the student query file and output the corresponding responses
			3.public static void main(String[] args); //calls getData and answerQueries, Execution begins here
			
HONOR CODE: Referred internet (Google/GeeksForGeeks etc.) for inbuilt file I/O statements
			The code has been written by me and I haven't copied any code or piece of code for this assignment. 
