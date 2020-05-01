package ProjectManagement;

public class Job implements Comparable<Job> {

	String name;
	Project project;
	String user;
	int runtime;
	String status = "NOT FINISHED";
	int endtime = -1;
	int stamp = 0;
    
    public Job(String name, Project project, String user, String runtime)
    {
    	this.name = name;
    	this.project = project;
    	this.user = user;
    	this.runtime = Integer.parseInt(runtime);
    }

    @Override
    public int compareTo(Job job) {
    	if(project.compareTo(job.project)!=0)
        	return project.compareTo(job.project);
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
}