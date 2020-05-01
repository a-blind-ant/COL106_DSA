public class CourseGrade implements CourseGrade_{
	String coursetitle;
	String coursenum;
	GradeInfo_ grade;
	String g;
	int gradepoint;

	public CourseGrade(String a, String b, String c)
	{
		coursenum = a;
		g = b;
		grade = new GradeInfo(GradeInfo_.LetterGrade.valueOf(b));
		coursetitle = c;
		gradepoint = GradeInfo_.gradepoint(GradeInfo_.LetterGrade.valueOf(b));
	}

	public String coursetitle()
	{
		return coursetitle;
	}

	public String coursenum()
	{
		return coursenum;
	}

	public GradeInfo_ grade()
	{
		return grade;
	}

	public String g()
	{
		return g;
	}
}