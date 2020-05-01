package ProjectManagement;

public class User implements Comparable<User> {
	String name = null;
	

	public User(String name)
	{
		this.name = name;
	}

    @Override
    public int compareTo(User user) {
        return 0;
    }

    public String toString()
    {
    	return this.name;
    }
}
