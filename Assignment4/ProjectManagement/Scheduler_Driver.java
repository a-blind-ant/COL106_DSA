package ProjectManagement;

import PriorityQueue.PriorityQueueDriverCode;

import java.io.*;
import java.net.URL;
import Trie.*;
import RedBlack.*;
import PriorityQueue.*;
import java.util.ArrayList;
import java.util.List;

public class Scheduler_Driver extends Thread implements SchedulerInterface {

    int globalTime = 0;
    int job_count = 0;

    Trie<User> allUsers = new Trie<User>();   
    Trie<Project> allProjects = new Trie<Project>();    
    MaxHeap<Job> jobs = new MaxHeap<Job>();
    ArrayList<Job> unfinishedJobs = new ArrayList<Job>();    
    RBTree<String, Job> allJobs = new RBTree<String, Job>();    
    ArrayList<Job> finishedJobs = new ArrayList<Job>();

    //MaxHeap<Job> traversed_jobs = new MaxHeap<Job>();
    //int job_count = 0; 

    public static void main(String[] args) throws IOException {
        Scheduler_Driver scheduler_driver = new Scheduler_Driver();

        File file;
        if (args.length == 0) {
            URL url = PriorityQueueDriverCode.class.getResource("INP");
            file = new File(url.getPath());
        } else {
            file = new File(args[0]);
        }

        scheduler_driver.execute(file);
    }

    public void execute(File file) throws IOException {

        URL url = Scheduler_Driver.class.getResource("INP");
        file = new File(url.getPath());

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.err.println("Input file Not found. "+file.getAbsolutePath());
        }
        String st;
        while ((st = br.readLine()) != null) {
            String[] cmd = st.split(" ");
            if (cmd.length == 0) {
                System.err.println("Error parsing: " + st);
                return;
            }

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
                case "":
                    handle_empty_line();
                    break;
                case "ADD":
                    handle_add(cmd);
                    break;
                default:
                    System.err.println("Unknown command: " + cmd[0]);
            }
        }


        run_to_completion();

        print_stats();

    }




    @Override
    public void run() {
        // till there are JOBS
        schedule();
    }


    @Override
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

    @Override
    public void handle_project(String[] cmd) {
        System.out.println("Creating project");
        Project project = new Project(cmd[1], cmd[2], cmd[3]);
        allProjects.insert(cmd[1], project);
    }

    @Override
    public void handle_job(String[] cmd) {
        System.out.println("Creating job");

        String project = cmd[2];
        String user = cmd[3];

        if(allProjects.search(project) == null)
        {
            System.out.println("No such project exists. " + project);
            return;
        }
        else if(allUsers.search(user) == null)
        {
            System.out.println("No such user exists: " + user);
            return;
        }
        else
        {
            Job job = new Job(cmd[1], (Project)(allProjects.search(project).getValue()), cmd[3], cmd[4]);
           // Job job2 = new Job(cmd[1], (Project)(allProjects.search(Project).getValue()), cmd[3], cmd[4]);
            job.stamp = job_count;
            //job2.stamp = job_count;     
            allJobs.insert(cmd[1], job);   
            job_count++; 
            jobs.insert(job);
        }
    }

    @Override
    public void handle_user(String name) {
        User user = new User(name);
        System.out.println("Creating user");
        allUsers.insert(name, user);
    }

    @Override
    public void handle_query(String key) {
        //Job job = jobs.search;
        System.out.println("Querying");
        List<Job> l = allJobs.search(key).getValues();
        if(l == null)
        {
            System.out.println(key + ": NO SUCH JOB");
        }
        else
        {
            System.out.println(l.get(0).name + ": " + l.get(0).status);
        }
    }

    @Override
    public void handle_empty_line() {
        System.out.println("Running code");
        System.out.println("Remaining jobs: " + jobs.size());
        schedule();
        System.out.println("Execution cycle completed");
    }

    @Override
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
            //int i = 0;
            int i = 0;
            while(i<unfinishedJobs.size())
            {
                if(unfinishedJobs.get(i).project.name.compareTo(project) == 0)
                {
                    Job tired = unfinishedJobs.remove(i);
                    jobs.insert(tired);
                }
                else
                {
                    i++;
                }
            }
        }
    }

    @Override
    public void print_stats() {
        System.out.println("--------------STATS---------------");
        System.out.println("Total jobs done: " + finishedJobs.size());
        while(finishedJobs.size()>0)
        {
            System.out.println(finishedJobs.remove(0));
        }
        System.out.println("------------------------");
        System.out.println("Unfinished jobs: ");
        int ans = unfinishedJobs.size();
        while(unfinishedJobs.size()>0)
        {
            jobs.insert(unfinishedJobs.remove(0));
        }
        while(jobs.size()>0)
        {
            System.out.println(jobs.extractMax());
        }
        System.out.println("Total unfinished jobs: " + ans);
        System.out.println("--------------STATS DONE---------------");
    }

    @Override
    public void schedule() {
        //System.out.println("Running code");
        //System.out.println("Remaining jobs: " + jobs.size());
        Job joob = jobs.extractMax();
        //System.out.print("Executing: " + joob.name + " from: " +joob.project);
        while(joob!=null)
        {
            if(joob.runtime > joob.project.budget)
            {
                unfinishedJobs.add(joob);
                System.out.println("Executing: " + joob.name + " from: " + joob.project.name);    
                System.out.println("Un-sufficient budget.");
                joob = jobs.extractMax();
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
            joob.endtime = globalTime;
            joob.project.budget-=joob.runtime;
            System.out.println("Project: " + joob.project.name + " budget remaining: " + joob.project.budget);     
        }
        //System.out.println("Execution cycle completed");    
    }
}
