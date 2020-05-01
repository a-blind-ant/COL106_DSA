import java.io.*;
import java.util.Iterator;

public class Assignment1{
	
	static LL<Hostel> allHostels = new LL<Hostel>();
	static LL<Course> allCourses = new LL<Course>();
	static LL<Department> allDepartments = new LL<Department>();
	static LL<Student> Allstudents = new LL<Student>();

	//Method to get data from files
	private static  void getData(String file1, String file2) throws Exception
	{
		//file1
		FileReader obj = new FileReader(file1);
		int i;		
		String line;
		while((i=obj.read())!=-1)
		{
			line = new String();
			line += (char)i;
			while(((i=obj.read())!=-1) && (char)i!='\n')
			{
				line += (char)i;				
			}
			String[] words = line.split(" ");
			//Storing the data in a new student object
			Student baccha = new Student(words[1],words[0],words[3],words[2]);
			Allstudents.add(baccha);
		}
		obj.close();
		/*Iterator<Position_<Student>> temp = Allstudents.positions();
		while(temp.hasNext())
		{
			System.out.println(temp.next().value().name());
		}*/
		obj = new FileReader(file2);
		while((i=obj.read())!=-1)
		{
			line = new String();
			line += (char)i;
			while(((i=obj.read())!=-1) && (char)i!='\n')
			{
				line += (char)i;				
			}
			String[] words = line.split(" ");
			Iterator<Position_<Student>> temp = Allstudents.positions();
			while(temp.hasNext())
			{
				Student x = temp.next().value();
				if(x.entryNo().equals(words[0]))
				{
					String ct = new String();
					for(int index = 3; index<words.length; index++)
					{
						ct = ct + " " + words[index];
					}
					CourseGrade y = new CourseGrade(words[1],words[2],ct.substring(1));
					x.addCourse(y);
				}
			}
		}
		/*Iterator<Position_<Student>> temp = Allstudents.positions();
		while(temp.hasNext())
		{
			System.out.println(temp.next().value().courseList().next().coursenum);
		}*/
		/**allHostels.add(new Hostel(Allstudents.getHead().value().hostel()));
		allDepartments.add(new Department(Allstudents.getHead().value().department()));
		allCourses.add(new Course(Allstudents.getHead().value().courseList().next().coursetitle(),Allstudents.getHead().value().courseList().next().coursenum()));
		
		allHostels.getHead().value().addStudent(Allstudents.getHead().value());
		allCourses.getHead().value().addStudent(Allstudents.getHead().value());
		allDepartments.getHead().value().addStudent(Allstudents.getHead().value());**/

		Iterator<Position_<Student>> temp = Allstudents.positions();
		while(temp.hasNext())
		{
			Student kid = temp.next().value();
			boolean flag = false;

			Iterator<Position_<Hostel>> h = allHostels.positions();			
			while(h.hasNext())
			{
				Hostel hostel = h.next().value();
				if(hostel.name().equals(kid.hostel()))
				{
					flag = true;
					hostel.addStudent(kid);
					break;
				}
			}
			if(!flag)
			{
				Hostel extra = new Hostel(kid.hostel());
				allHostels.add(extra);
				extra.addStudent(kid);
			}

			flag = false;
			Iterator<Position_<Department>> d = allDepartments.positions();			
			while(d.hasNext())
			{
				Department department = d.next().value();
				if(department.name().equals(kid.department()))
				{
					flag = true;
					department.addStudent(kid);
					break;
				}
			}
			if(!flag)
			{
				Department extra = new Department(kid.department());
				allDepartments.add(extra);
				extra.addStudent(kid);
			}

			Iterator<CourseGrade> crs = kid.courseList();
			while(crs.hasNext())
			{
				CourseGrade ob = crs.next();
				flag = false;
				Iterator<Position_<Course>> c = allCourses.positions();
				while(c.hasNext())
				{
					Course course = c.next().value();
					if(course.name().equals(ob.coursetitle()))
					{
						flag = true;
						course.addStudent(kid);
						break;
					}
				}
				if(!flag)
				{
					Course extra = new Course(ob.coursetitle(),ob.coursenum);
					allCourses.add(extra);
					extra.addStudent(kid);
				}
			}

		}

	}

	private static void answerQueries(String file3) throws Exception
	{
		//Reading Query file(REMEMBER PRINT IN REVERSE ORDER AND LEXICOGRAPHICAL ORDER AS WELL)
		FileReader obj = new FileReader(file3);
		int i;		
		LL<String> queries = new LL<String>();
		int n = 0;
		while((i=obj.read())!=-1)
		{
			n++;	
			while(((i=obj.read())!=-1) && (char)i!='\n')
			{
			}
		}
		//System.out.println(n);

		String[] line = new String[n];

		obj = new FileReader(file3);
		int j = 0;
		while((i=obj.read())!=-1)
		{
			line[j] = new String();
			line[j] += (char)i;
			while(((i=obj.read())!=-1) && (char)i!='\n')
			{
				line[j] += (char)i;				
			}
			j++;
		}
		//System.out.println(line[3]);
		for(j=n-1;j>=0;j--)
		{
			String[] words = line[j].split(" ");
			if(words[0].equals("COURSETITLE"))
			{
				Iterator<Position_<Course>> cult = allCourses.positions();
				while(cult.hasNext())
				{
					Position_<Course> l = cult.next();
					Course crs = l.value();
					if(crs.cnum.substring(0,6).equals(words[1].substring(0,6)))
					{
						System.out.println(crs.name());
						break;
					}
				}
			}

			else if (words[0].equals("INFO"))
			{
				Iterator<Position_<Student>> cult = Allstudents.positions();
				while(cult.hasNext())
				{
					Student stu = cult.next().value();
					if(stu.name.equals(words[1]) || stu.entryNo.equals(words[1]))
					{
						String output = stu.entryNo() + " " + stu.name() + " " + stu.department() + " " + stu.hostel() + " " + stu.cgpa()+ " ";
						Iterator<CourseGrade> citer = stu.courseList();
						int count = 0;
						while(citer.hasNext())
						{
							CourseGrade lol = citer.next();
							count++;
						}
						String[] empty = new String[count];
						citer = stu.courseList();
						count = 0;
						while(citer.hasNext())
						{
							CourseGrade lol = citer.next();
							empty[count] = lol.coursenum() + " " + lol.g() + " ";
							count++;
						}
						//Sorting the array empty
						for(int q=0;q<empty.length;q++)
						{
    						for(int w=0;w<empty.length-1;w++)
    						{
        						if(empty[w].compareTo(empty[w+1])>0)
        						{
            						String temp = empty[w];
            						empty[w] = empty[w+1];
            						empty[w+1] = temp; 
        						}
    						}
						}	
						for(int q =0; q<empty.length;q++)
						{
							output = output + empty[q];
						}
						System.out.println(output);
						break;
					} 
				}
			}

			
			else if(words[0].equals("SHARE"))
			{
				Iterator<Position_<Student>> it = Allstudents.positions();
				Student child = Allstudents.getHead().value();
				while(it.hasNext())
				{
					child = it.next().value();
					if(child.entryNo().equals(words[1]))
					{
						break;
					}
				}
				if(words[2].equals(child.hostel()))
				{
					
					Iterator<Position_<Hostel>> h = allHostels.positions();
					Hostel hostel = allHostels.getHead().value();
					while(h.hasNext())
					{
						hostel = h.next().value();
						if(hostel.name().equals(child.hostel()))
						{
							break;
						}
					}
					Iterator<Student_> another = hostel.studentList();
					int total = 0;
					while(another.hasNext())
					{
						another.next();
						total++;
					}
					String[] empty = new String[total];
					another = hostel.studentList();
					int index=0;
					while(another.hasNext())
					{
						empty[index++]=another.next().entryNo();
					}
					for(int q=0;q<empty.length;q++)
						{
    						for(int w=0;w<empty.length-1;w++)
    						{
        						if(empty[w].compareTo(empty[w+1])>0)
        						{
            						String temp = empty[w];
            						empty[w] = empty[w+1];
            						empty[w+1] = temp; 
        						}
    						}
						}
					String answer = new String();	
					for(index =0;index<empty.length;index++)
					{
						if(!empty[index].equals(words[1]))	
							answer = answer + " " + empty[index];
					}
					System.out.println(answer.substring(1));
				}

			

				else if(words[2].equals(child.department()))
				{
					
					Iterator<Position_<Department>> h = allDepartments.positions();
					Department department = allDepartments.getHead().value();
					while(h.hasNext())
					{
						department = h.next().value();
						if(department.name().equals(child.department()))
						{
							break;
						}
					}
					Iterator<Student_> another = department.studentList();
					int total = 0;
					while(another.hasNext())
					{
						another.next();
						total++;
					}
					String[] empty = new String[total];
					another = department.studentList();
					int index=0;
					while(another.hasNext())
					{
						empty[index++]=another.next().entryNo();
					}
					for(int q=0;q<empty.length;q++)
						{
    						for(int w=0;w<empty.length-1;w++)
    						{
        						if(empty[w].compareTo(empty[w+1])>0)
        						{
            						String temp = empty[w];
            						empty[w] = empty[w+1];
            						empty[w+1] = temp; 
        						}
    						}
						}
					String answer = new String();	
					for(index =0;index<empty.length;index++)
					{
						if(!empty[index].equals(words[1]))	
							answer = answer + " " + empty[index];
					}
					System.out.println(answer.substring(1));
				}

				else
				{
					Iterator<Position_<Course>> h = allCourses.positions();
					Course course = allCourses.getHead().value();
					while(h.hasNext())
					{
						course = h.next().value();
						//System.out.println(words[2].length());

						if(course.cnum.equals(words[2]))
						{
							break;
						}
					}
					Iterator<Student_> another = course.studentList();
					int total = 0;
					while(another.hasNext())
					{
						another.next();
						total++;
					}
					String[] empty = new String[total];
					another = course.studentList();
					int index=0;
					while(another.hasNext())
					{
						empty[index++]=another.next().entryNo();
					}
					for(int q=0;q<empty.length;q++)
						{
    						for(int w=0;w<empty.length-1;w++)
    						{
        						if(empty[w].compareTo(empty[w+1])>0)
        						{
            						String temp = empty[w];
            						empty[w] = empty[w+1];
            						empty[w+1] = temp; 
        						}
    						}
						}
					String answer = new String();	
					for(index =0;index<empty.length;index++)
					{
						if(!empty[index].equals(words[1]))	
							answer = answer + " " + empty[index];
					}
					if(answer.length()>=1)
						System.out.println(answer.substring(1));
					else
						System.out.println(answer);
													
				}	

				

			}	}
	}	

	//MAIN fucntion

	public static void main(String[] args) throws Exception
	{
		getData(args[0],args[1]);
		answerQueries(args[2]);

	}
}
