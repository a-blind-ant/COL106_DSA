package ProjectManagement;
import java.util.ArrayList;

public class Project {
	int entry_time = 0;
	String name = null;
	int priority = 0;
	int budget = 0;
	ArrayList<JobReport_> kaam = new ArrayList<JobReport_>(); 

	public Project(String name, String priority, String budget)
	{
		this.name = name;
		this.priority = Integer.parseInt(priority);
		this.budget = Integer.parseInt(budget);
	}

	public int compareTo(Project x)
	{
		int ans = x.priority - this.priority;
		if(ans==0)
			ans = this.entry_time - x.entry_time;
		return ans;
	}

	public String toString()
	{
		return this.name;
	}
}
