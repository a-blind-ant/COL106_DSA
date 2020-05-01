package ProjectManagement;

public class Job implements Comparable<Job>, JobReport_ {

	String name;
	Project project;
	String user;
	int runtime;
	String status = "NOT FINISHED";
	int endtime = -1;
    int arrival_time = -1;
    int completion_time = -1;
	int stamp = 0;
    
    public Job(String name, Project project, String user, String runtime,int arrival_time)
    {
    	this.name = name;
    	this.project = project;
    	this.user = user;
    	this.runtime = Integer.parseInt(runtime);
        this.arrival_time = arrival_time;
    }

    @Override
    public int compareTo(Job job) {
    	if(this.project.priority - job.project.priority != 0)
        	return this.project.priority - job.project.priority;
        else
        {
        	return job.stamp - this.stamp;
        }		
        //	return this.stamp - job.stamp;
    }

    public String toString()
    {
        String stts = this.status;
        if(this.status.equals("NOT FINISHED"))
        {
            stts = "REQUESTED";
        }
        String tilt = ((Integer)endtime).toString();
        if(endtime == -1)
        {
            tilt = null;
        }
        return "Job{user=\'"+this.user+"\'"+", project="+"\'"+this.project.name+"\'"+", jobstatus="+stts+", execution_time="+this.runtime+", end_time="+tilt+", name="+"\'"+this.name+"\'"+"}";
    }

    //NEW METHODS
    public String user()
    {
        return this.user;
    }
    public String project_name()
    {
        return this.project.name;
    }
    public int budget()
    {
        return this.project.budget;
    } 
    public int arrival_time()
    {
        return this.arrival_time;
    }
    public int completion_time()
    {
        return this.completion_time;
    }

    public int extra(Job doosra)
    {
        int ans = this.completion_time - doosra.completion_time;
        if(ans ==0)
        {
            ans = this.arrival_time - doosra.completion_time;
        }
        return ans;
    }
}