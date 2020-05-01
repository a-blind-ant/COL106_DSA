import java.io.*;
public class assignment3
{
	public static void main(String[] args) throws IOException, NotFoundException
	{
		int x = Integer.parseInt(args[0]);

		//Separate chaining using BSTs
		if(args[1].equals("SCBST"))
		{
			File file = new File(args[2]);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String str;
			HashBST<Key<String>, Student> list = new HashBST<Key<String>, Student>(x);
			while((str = br.readLine())!=null)
			{
				String[] arr = str.split(" ");
				if(arr[0].equals("insert"))
				{
					Student kid = new Student(arr[1],arr[2],arr[3],arr[4],arr[5]);
					Key<String> key = new Key<String>(arr[1], arr[2]); 
					int ans = list.insert(key, kid);
					if(ans == -1)
						System.out.println("E");
					else
						System.out.println(ans);
				}
				else if(arr[0].equals("update"))
				{
					Student kid = new Student(arr[1],arr[2],arr[3],arr[4],arr[5]);
					Key<String> key = new Key<String>(arr[1], arr[2]);
					int ans = list.update(key ,kid);
					if(ans == -1)
					{
						System.out.println("E");
					}
					else
					{
						System.out.println(ans);
					}
				}	
				else if(arr[0].equals("delete"))
				{
					int ans = list.delete(new Key<String>(arr[1], arr[2]));
					if(ans == -1)
						System.out.println("E");
					else
						System.out.println(ans);
				}
				else if(arr[0].equals("contains"))
				{
					//Pair<String, String> p = new Pair<String, String>(arr[1], arr[2]);
					boolean ans = list.contains(new Key<String>(arr[1],arr[2]));
					if(ans)
						System.out.println("T");
					else
						System.out.println("F");
				}
				else if(arr[0].equals("get"))
				{
   					try
   					{
   						Key<String> key = new Key<String>(arr[1], arr[2]);
   						Student ans = list.get(key);
   						System.out.println(ans.fname()+" "+ans.lname()+" "+ans.hostel()+" "+ans.department()+" "+ans.cgpa());
					}
					catch(Exception pyl)
					{
						System.out.println("E");
					}
				}	
				else if(arr[0].equals("address"))
				{
					try
					{
						Key<String> key = new Key<String>(arr[1], arr[2]); 
						String ans = list.address(key);
						System.out.println(ans);
					}
					catch (Exception ell)
					{
						System.out.println("E");
					}
				}
			}
		}

		//Double Hashing / Open addressing	
		else if(args[1].equals("DH"))
		{
			//System.out.println(args[0]+args[1]+args[2]);
			File file = new File(args[2]);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String str;
			DoubleHash<Key<String>,Student> list = new DoubleHash<Key<String>,Student>(x);
			while((str = br.readLine())!=null)
			{
				String[] arr = str.split(" ");
				if(arr[0].equals("insert"))
				{
					Student kid = new Student(arr[1],arr[2],arr[3],arr[4],arr[5]);
					int ans = list.insert(new Key<String>(arr[1], arr[2]), kid);
					if(ans == -1)
						System.out.println("E");
					else
						System.out.println(ans);
				}
				else if(arr[0].equals("update"))
				{
					Student kid = new Student(arr[1],arr[2],arr[3],arr[4],arr[5]);
					int ans = list.update(new Key<String>(arr[1], arr[2]),kid);
					if(ans==-1)
					{
						System.out.println("E");
					}
					else
					{
						System.out.println(ans);
					}
				}
				else if(arr[0].equals("contains"))
				{
					boolean ans = list.contains(new Key<String>(arr[1], arr[2]));
					if(ans)
						System.out.println("T");
					else
						System.out.println("F");
				}
				else if(arr[0].equals("delete"))
				{
					int ans = list.delete(new Key<String>(arr[1], arr[2]));
					if(ans==-1)
					{
						System.out.println("E");
					}
					else
					{
						System.out.println(ans);			
					}
				}
				else if(arr[0].equals("get"))
				{
					try
					{
						Student ans = list.get(new Key<String>(arr[1], arr[2]));
   						System.out.println(ans.fname()+" "+ans.lname()+" "+ans.hostel()+" "+ans.department()+" "+ans.cgpa());					
					}
					catch(Exception mcp)
					{
						System.out.println("E");
					}	
				}
				else if(arr[0].equals("address"))
				{
					try
					{
						String ans = list.address(new Key<String>(arr[1], arr[2]));
						System.out.println(ans);
					}
					catch(Exception mtl)
					{
						System.out.println("E");
					}
				}
			}
		}		
	}
}