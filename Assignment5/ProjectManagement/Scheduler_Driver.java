package ProjectManagement;


import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import Trie.*;
import RedBlack.*;
import PriorityQueue.*;
import java.util.List;

public class Scheduler_Driver extends Thread implements SchedulerInterface {

    int globalTime = 0;
    int job_count = 0;
    int pro_count = 0;
    int unfinishedJobs_count = 0;

    Trie<UserReport_> allUsers = new Trie<UserReport_>();   
    Trie<Project> allProjects = new Trie<Project>();    
    MaxHeap<Job> jobs = new MaxHeap<Job>();
    RBTree<String, JobReport_> allJobs = new RBTree<String, JobReport_>();    
    RBTree<String, JobReport_> unfinishedJobs = new RBTree<String, JobReport_>();    
    ArrayList<Job> finishedJobs = new ArrayList<Job>();

    public static void main(String[] args) throws IOException {
//

        Scheduler_Driver scheduler_driver = new Scheduler_Driver();
        File file;
        if (args.length == 0) {
            URL url = Scheduler_Driver.class.getResource("INP");
            file = new File(url.getPath());
        } else {
            file = new File(args[0]);
        }

        scheduler_driver.execute(file);
    }

    public void execute(File commandFile) throws IOException {


        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(commandFile));

            String st;
            while ((st = br.readLine()) != null) {
                String[] cmd = st.split(" ");
                if (cmd.length == 0) {
                    System.err.println("Error parsing: " + st);
                    return;
                }
                String project_name, user_name;
                Integer start_time, end_time;

                long qstart_time, qend_time;

                switch (cmd[0]) {
                    case "PROJECT":
                        handle_project(cmd);
                        break;
                    case "JOB":
                        handle_job(cmd);
                        break;
                    case "USER":
                        handle_user(cmd[1]);
                        break;
                    case "QUERY":
                        handle_query(cmd[1]);
                        break;
                    case "": // HANDLE EMPTY LINE
                        handle_empty_line();
                        break;
                    case "ADD":
                        handle_add(cmd);
                        break;
                    //--------- New Queries
                    case "NEW_PROJECT":
                    case "NEW_USER":
                    case "NEW_PROJECTUSER":
                    case "NEW_PRIORITY":
                        timed_report(cmd);
                        break;
                    case "NEW_TOP":
                        qstart_time = System.nanoTime();
                        timed_top_consumer(Integer.parseInt(cmd[1]));
                        qend_time = System.nanoTime();
                        System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                        break;
                    case "NEW_FLUSH":
                        qstart_time = System.nanoTime();
                        timed_flush( Integer.parseInt(cmd[1]));
                        qend_time = System.nanoTime();
                        System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                        break;
                    default:
                        System.err.println("Unknown command: " + cmd[0]);
                }

            }


            run_to_completion();
            print_stats();

        } catch (FileNotFoundException e) {
            System.err.println("Input file Not found. " + commandFile.getAbsolutePath());
        } catch (NullPointerException ne) {
            ne.printStackTrace();

        }
    }

    @Override
    public ArrayList<JobReport_> timed_report(String[] cmd) {
        long qstart_time, qend_time;
        ArrayList<JobReport_> res = null;
        switch (cmd[0]) {
            case "NEW_PROJECT":
                System.out.println("Project query");
                qstart_time = System.nanoTime();
                res = handle_new_project(cmd);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                break;
            case "NEW_USER":
                System.out.println("User query");
                qstart_time = System.nanoTime();
                res = handle_new_user(cmd);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));

                break;
            case "NEW_PROJECTUSER":
                System.out.println("Project User query");
                qstart_time = System.nanoTime();
                res = handle_new_projectuser(cmd);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                break;
            case "NEW_PRIORITY":
                System.out.println("Priority query");
                qstart_time = System.nanoTime();
                res = handle_new_priority(cmd[1]);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                break;
        }

        return res;
    }

    @Override
    public ArrayList<UserReport_> timed_top_consumer(int top) {
        ArrayList<UserReport_> ans = allUsers.all_incoming_members;
        ans.sort(null);
        int n = top;
        if(n>ans.size())
        	n = ans.size();
        if(top>=ans.size())
        {
            return ans;
        }
        ArrayList<UserReport_> answer = new ArrayList<UserReport_>();
        int i = 0;
        while(i<top)
        {
        	answer.add(ans.get(i));
        	i++;
        }
        return answer;
    }


    @Override
    public void timed_flush(int waittime) {
        MaxHeap<Job> temp = new MaxHeap<Job>();
        int gt = this.globalTime;
        Job joob = jobs.extractMax();
        while(joob != null)
        {
        	if(gt - joob.arrival_time < waittime)
        	{
        		temp.insert(joob);
        		joob = jobs.extractMax();		
        	}
        	else
        	{
            	if(joob.runtime > joob.project.budget)
            	{
                	temp.insert(joob);
                	joob = jobs.extractMax();
            	}
            	else
            	{
                	finishedJobs.add(joob);
		            joob.status = "COMPLETED";
		            globalTime+=joob.runtime;
		            joob.completion_time = globalTime;
		            joob.endtime = globalTime;
		            String use = joob.user();
		            User user = (User)allUsers.search(use).getValue();
		            user.consumed_time += joob.runtime;
		            user.last_accessed = joob.endtime;
		            joob.project.budget-=joob.runtime;
		            joob = jobs.extractMax();
            	}
        	}
        }

        jobs = temp;
        //
        return;
    }
    
    private ArrayList<JobReport_> handle_new_priority(String s) {
        int p = Integer.parseInt(s);
        ArrayList<JobReport_> ans = allJobs.wallace;
        ArrayList<JobReport_> answer = new ArrayList<JobReport_>();
        int n = ans.size();
        int i = 0;
        while(i<n)
        {
        	if(((Job)ans.get(i)).status.equals("NOT FINISHED") && ((Job)ans.get(i)).project.priority>=p)
				answer.add(ans.get(i));        		
        	i++;
        }
        return answer;    
    }

    private ArrayList<JobReport_> handle_new_projectuser(String[] cmd) {
        TrieNode<User> u = allUsers.search(cmd[2]);
        ArrayList<JobReport_> ju = new ArrayList<JobReport_>();
        if(u == null)
        	return ju;
        else
        	ju = ((User)u.getValue()).kaam;
		ArrayList<JobReport_> ans = new ArrayList<JobReport_>();
		int i = 0;
		int n = ju.size();
		int t1 = Integer.parseInt(cmd[3]);
        int t2 = Integer.parseInt(cmd[4]);
		while(i<n)
		{
			if(ju.get(i).project_name().equals(cmd[1]) && ju.get(i).arrival_time() >= t1 && ju.get(i).arrival_time() <= t2)
				ans.add(ju.get(i));
			i++;
		}
		this.sort1(ans, 0, ans.size()-1);
		return ans;        
    }

    public void merge1(ArrayList<JobReport_> ls, int l, int m, int r) 
    { 
            int n1 = m-l+1; 
            int n2 = r-m; 
            ArrayList<JobReport_> x1 = new ArrayList<JobReport_>(n1); 
            ArrayList<JobReport_> x2 = new ArrayList<JobReport_>(n2); 
            //System.out.println(x1.size());
            for (int i=0; i<n1; ++i) 
                x1.add(i, ls.get(l+i)); 
            for (int j=0; j<n2; ++j) 
                x2.add(j, ls.get(m+1+j)); 
            int i = 0, j = 0; 
            int k = l; 
            while (i < n1 && j < n2) 
            { 
                if (extra(x1.get(i),x2.get(j))<=0) 
                { 
                    ls.set(k,x1.get(i)); 
                    i++; 
                } 
                else
                { 
                    ls.set(k,x2.get(j)); 
                    j++; 
                } 
                k++; 
            } 
            while (i < n1) 
            { 
                ls.set(k, x1.get(i)); 
                i++; 
                k++; 
            } 
            while(j < n2) 
            { 
                ls.set(k, x2.get(j)); 
                j++; 
                k++; 
            } 
    }
    public int extra(JobReport_ pehla, JobReport_ doosra)
    {
        if(((Job)pehla).status.equals("COMPLETED") && ((Job)doosra).status.equals("NOT FINISHED"))
        	return -1;
        if(((Job)doosra).status.equals("COMPLETED") && ((Job)pehla).status.equals("NOT FINISHED"))
        	return 1;	 
        int ans = pehla.completion_time() - doosra.completion_time();
        if(ans == 0)
        {
            ans = pehla.arrival_time() - doosra.arrival_time();
        }
        return ans;
    } 
    public void sort1(ArrayList<JobReport_> ls, int l, int r) 
    { 
        //System.out.println("Identity theft");
        if (l < r) 
        { 
            int m = (l+r)/2; 
            sort1(ls, l, m); 
            sort1(ls , m+1, r); 
            merge1(ls, l, m, r); 
        } 
    }

    private ArrayList<JobReport_> handle_new_user(String[] cmd) {
        TrieNode<User> u = allUsers.search(cmd[1]);
        if(u == null)
            return new ArrayList<JobReport_>();
        User user = (User)u.getValue();
        int t1 = Integer.parseInt(cmd[2]);
        int t2 = Integer.parseInt(cmd[3]);
        ArrayList<JobReport_> helper = user.kaam;
        ArrayList<JobReport_> ans = new ArrayList<JobReport_>();
        int i = 0;
        int n = helper.size();
        while(i<n)
        {
            if(helper.get(i).arrival_time()>=t1 && helper.get(i).arrival_time()<=t2)
            {
                ans.add(helper.get(i));
            }
            i++;
        }
        return ans;
    }

    private ArrayList<JobReport_> handle_new_project(String[] cmd) {
        TrieNode<Project> u = allProjects.search(cmd[1]);
        if(u == null)
            return new ArrayList<JobReport_>();
        Project user = (Project)u.getValue();
        int t1 = Integer.parseInt(cmd[2]);
        int t2 = Integer.parseInt(cmd[3]);
        ArrayList<JobReport_> helper = user.kaam;
        ArrayList<JobReport_> ans = new ArrayList<JobReport_>();
        int i = 0;
        int n = helper.size();
        while(i<n)
        {
            if(helper.get(i).arrival_time()>=t1 && helper.get(i).arrival_time()<=t2)
            {
                ans.add(helper.get(i));
            }
            i++;
        }
        return ans;                
    }

    //------------------------------------------
    //OLD METHODS 
    //------------------------------------------

    public void schedule() {
        Job joob = jobs.extractMax();
        //System.out.print("Executing: " + joob.name + " from: " +joob.project);
        while(joob!=null)
        {
            if(joob.runtime > joob.project.budget)
            {
                unfinishedJobs.insert(joob.project.name, joob);
                System.out.println("Executing: " + joob.name + " from: " + joob.project.name);    
                System.out.println("Un-sufficient budget.");
                joob = jobs.extractMax();
                this.unfinishedJobs_count++;
            }
            else
            {
                break;
            }    
        }
        if(joob!=null)
        {
            finishedJobs.add(joob);
            joob.status = "COMPLETED";
            System.out.println("Executing: " + joob.name + " from: " + joob.project.name);
            globalTime+=joob.runtime;
            joob.completion_time = globalTime;
            joob.endtime = globalTime;
            String use = joob.user();
            User user = (User)allUsers.search(use).getValue();
            user.consumed_time += joob.runtime;
            user.last_accessed = joob.endtime;
            joob.project.budget-=joob.runtime;
            System.out.println("Project: " + joob.project.name + " budget remaining: " + joob.project.budget);     
        }
    }

    public void run_to_completion() {
        if(jobs.size() == 0)
        {
            System.out.println("Running code");   
            System.out.println("Remaining jobs: " + jobs.size());
            System.out.println("System execution completed");
            return;
        }   
        while(jobs.size()>0)
        {
            System.out.println("Running code");   
            System.out.println("Remaining jobs: " + jobs.size());
            schedule();
            System.out.println("System execution completed");
        }
    }

    public void print_stats() {
        System.out.println("--------------STATS---------------");
        System.out.println("Total jobs done: " + finishedJobs.size());
        while(finishedJobs.size()>0)
        {
            System.out.println(finishedJobs.remove(0));
        }
        System.out.println("------------------------");
        System.out.println("Unfinished jobs: ");
        int ans = this.unfinishedJobs_count;
        ArrayList<JobReport_> jim = (ArrayList<JobReport_>)unfinishedJobs.dfs();
        //MERGE SORT
        this.sort2(jim, 0, this.unfinishedJobs_count - 1);
        //
        int n = jim.size();
        int i = 0;
        while(i<n)
        {
            System.out.println(jim.get(i));
            i++;
        }
        System.out.println("Total unfinished jobs: " + ans);
        System.out.println("--------------STATS DONE---------------");
    }
	//****************************************************************
	public void merge2(ArrayList<JobReport_> ls, int l, int m, int r) 
    { 
            int n1 = m-l+1; 
            int n2 = r-m; 
            ArrayList<JobReport_> x1 = new ArrayList<JobReport_>(n1); 
            ArrayList<JobReport_> x2 = new ArrayList<JobReport_>(n2); 
            //System.out.println(x1.size());
            for (int i=0; i<n1; ++i) 
                x1.add(i, ls.get(l+i)); 
            for (int j=0; j<n2; ++j) 
                x2.add(j, ls.get(m+1+j)); 
            int i = 0, j = 0; 
            int k = l; 
            while (i < n1 && j < n2) 
            { 
                if (extra2(x1.get(i),x2.get(j))<=0) 
                { 
                    ls.set(k,x1.get(i)); 
                    i++; 
                } 
                else
                { 
                    ls.set(k,x2.get(j)); 
                    j++; 
                } 
                k++; 
            } 
            while (i < n1) 
            { 
                ls.set(k, x1.get(i)); 
                i++; 
                k++; 
            } 
            while(j < n2) 
            { 
                ls.set(k, x2.get(j)); 
                j++; 
                k++; 
            } 
    }
    public int extra2(JobReport_ pehla, JobReport_ doosra)
    {
        int ans = ((Job)pehla).project.compareTo(((Job)doosra).project);
        if(ans == 0)
        	ans = ((Job)pehla).stamp - ((Job)doosra).stamp;
        return ans;	
    } 
    public void sort2(ArrayList<JobReport_> ls, int l, int r) 
    { 
        //System.out.println("Identity theft");
        if (l < r) 
        { 
            int m = (l+r)/2; 
            sort2(ls, l, m); 
            sort2(ls , m+1, r); 
            merge2(ls, l, m, r); 
        } 
    }
	//****************************************************************


    public void handle_add(String[] cmd) {
        String project = cmd[1];
        int amount = Integer.parseInt(cmd[2]);        
        System.out.println("ADDING Budget");
        TrieNode<Project> x = allProjects.search(project);
        if(x == null)
        {
            return;
        }
        else
        {
            x.getValue().budget+=amount;
            RedBlackNode<String, JobReport_> pam = unfinishedJobs.search(project);
            List<JobReport_> dwight = pam.getValues();
            if(dwight == null)
            {
                return;
            }
            else
            {
                int i = 0;
                while(dwight.size()>0)
                {
                    jobs.insert((Job)dwight.remove(0));
                    i++;
                }
                this.unfinishedJobs_count -= i;        
            }
        }
    }

    public void handle_empty_line() {
        System.out.println("Running code");
        System.out.println("Remaining jobs: " + jobs.size());
        schedule();
        System.out.println("Execution cycle completed");
    }


    public void handle_query(String key) {
        System.out.println("Querying");
        List<JobReport_> l = allJobs.search(key).getValues();
        if(l == null)
        {
            System.out.println(key + ": NO SUCH JOB");
        }
        else
        {
            System.out.println(((Job)l.get(0)).name + ": " + ((Job)l.get(0)).status);
        }
    }

    public void handle_user(String name) {
        UserReport_ user = new User(name);
        System.out.println("Creating user");
        allUsers.insert(name, user);
    }

    public void handle_job(String[] cmd) {
        System.out.println("Creating job");
        TrieNode<User> useme = allUsers.search(cmd[3]);
        String project = cmd[2];
        //String user = cmd[3];

        if(allProjects.search(project) == null)
        {
            System.out.println("No such project exists. " + project);
            return;
        }
        else if(useme == null)
        {
            System.out.println("No such user exists: " + cmd[3]);
            return;
        }
        else
        {
            Job job = new Job(cmd[1], (Project)(allProjects.search(project).getValue()), cmd[3], cmd[4], globalTime);
            job.stamp = job_count;
            job.project.kaam.add(job);
            ((User)useme.getValue()).kaam.add(job);
            allJobs.insert(cmd[1], job);   
            job_count++; 
            jobs.insert(job);
        }
    }

    public void handle_project(String[] cmd) {
        System.out.println("Creating project");
        Project project = new Project(cmd[1], cmd[2], cmd[3]);
        this.pro_count++;
        project.entry_time = this.pro_count;
        allProjects.insert(cmd[1], project);
    }

    public void execute_a_job() {
        return;
    }

    //------------------------------------------------------
    //TIMED OLD METHODS
    //------------------------------------------------------


	public void timed_schedule() {
        Job joob = jobs.extractMax();
        while(joob!=null)
        {
            if(joob.runtime > joob.project.budget)
            {
                unfinishedJobs.insert(joob.project.name, joob);
                joob = jobs.extractMax();
                this.unfinishedJobs_count++;
            }
            else
            {
                break;
            }    
        }
        if(joob!=null)
        {
            finishedJobs.add(joob);
            joob.status = "COMPLETED";
            globalTime+=joob.runtime;
            joob.completion_time = globalTime;
            joob.endtime = globalTime;
            String use = joob.user();
            User user = (User)allUsers.search(use).getValue();
            user.consumed_time += joob.runtime;
            user.last_accessed = joob.endtime;
            joob.project.budget-=joob.runtime;
        }
    }


    public void timed_run_to_completion() {
        if(jobs.size() == 0)
        {
            return;
        }   
        while(jobs.size()>0)
        {
            timed_schedule();
        }
    }


	public void timed_handle_add(String[] cmd) {
        String project = cmd[1];
        int amount = Integer.parseInt(cmd[2]);        
        TrieNode<Project> x = allProjects.search(project);
        if(x == null)
        {
            return;
        }
        else
        {
            x.getValue().budget+=amount;
            RedBlackNode<String, JobReport_> pam = unfinishedJobs.search(project);
            List<JobReport_> dwight = pam.getValues();
            if(dwight == null)
            {
                return;
            }
            else
            {
                int i = 0;
                while(dwight.size()>0)
                {
                    jobs.insert((Job)dwight.remove(0));
                    i++;
                }
                this.unfinishedJobs_count -= i;        
            }
        }
    }


    public void timed_handle_query(String key) {
        List<JobReport_> l = allJobs.search(key).getValues();
    }


    public void timed_handle_user(String name) {
        UserReport_ user = new User(name);
        allUsers.insert(name, user);
    }


	public void timed_handle_job(String[] cmd) {
        TrieNode<User> useme = allUsers.search(cmd[3]);
        String project = cmd[2];
        if(allProjects.search(project) == null)
        {
            return;
        }
        else if(useme == null)
        {
            return;
        }
        else
        {
            Job job = new Job(cmd[1], (Project)(allProjects.search(project).getValue()), cmd[3], cmd[4], globalTime);
            job.stamp = job_count;
            job.project.kaam.add(job);
            ((User)useme.getValue()).kaam.add(job);
            allJobs.insert(cmd[1], job);   
            job_count++; 
            jobs.insert(job);
        }
    }    


	public void timed_handle_project(String[] cmd) {
        Project project = new Project(cmd[1], cmd[2], cmd[3]);
        this.pro_count++;
        project.entry_time = this.pro_count;
        allProjects.insert(cmd[1], project);
    }    
}
