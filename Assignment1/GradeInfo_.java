public interface GradeInfo_ {
   static enum LetterGrade {
	A, Aminus, B, Bminus, C, Cminus, D, E, F, I; 
   } 
   public static int gradepoint(GradeInfo_.LetterGrade grade)
	{

		if (grade == GradeInfo_.LetterGrade.A) return 10;

		else if (grade == GradeInfo_.LetterGrade.Aminus) return 9;

		else if (grade == GradeInfo_.LetterGrade.B) return 8;

		else if (grade == GradeInfo_.LetterGrade.Bminus) return 7;

		else if (grade == GradeInfo_.LetterGrade.C) return 6;

		else if (grade == GradeInfo_.LetterGrade.Cminus) return 5;

		else if (grade == GradeInfo_.LetterGrade.D) return 4;

		else return 0;
		
	}

	    public default LetterGrade grade() 
	{ return LetterGrade.I;
	}

	
  
}