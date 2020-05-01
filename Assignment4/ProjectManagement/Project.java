package ProjectManagement;


public class Project {
	String name = null;
	int priority = 0;
	int budget = 0;


	public Project(String name, String priority, String budget)
	{
		this.name = name;
		this.priority = Integer.parseInt(priority);
		this.budget = Integer.parseInt(budget);
	}

	public int compareTo(Project x)
	{
		return this.priority - x.priority;
	}

	public String toString()
	{
		return this.name;
	}
}
