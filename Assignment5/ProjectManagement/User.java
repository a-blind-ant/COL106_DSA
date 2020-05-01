package ProjectManagement;
import java.util.ArrayList;

public class User implements Comparable<User>, UserReport_ {
	String name = null;
	int consumed_time = 0;
    int last_accessed = 0;
    ArrayList<JobReport_> kaam = new ArrayList<JobReport_>();
	public User(String name)
	{
		this.name = name;
	}

    @Override
    public int compareTo(User user) {
        int ans = user.consumed_time - this.consumed_time;
        if(ans == 0)
            ans = this.last_accessed - user.last_accessed;
        return ans;
    }

    public String toString()
    {
    	return "User{name=" + '\'' + this.name + '\'' + ", usage=" + this.consumed_time + "}";
    }

    //NEW METHODS
    public String user()
    {
        return this.name;
    }
    public int consumed()
    {
        return this.consumed_time;
    }
}
