public class Point implements PointInterface {
	float x = 0;
	float y = 0;
	float z = 0;
	float [] all = new float[3];
	AL<Triangle> my_triangles = new AL(1);
	Graph<Point, Edge>.Node<Point> spare_node = null;

	int marked = 0;

	public Point(float a, float b, float c)
	{
		this.x = a;
		this.y = b;
		this.z = c;
		all[0] = this.x;
		all[1] = this.y;
		all[2] = this.z;
	}

	//OVERRIDEN METHODS
	public float getX()
	{
		return this.x;
	}
	public float getY()
	{
		return this.y;
	}
	public float getZ()
	{
		return this.z;
	}
	public float [] getXYZcoordinate()
	{
		return this.all;
	}

	//MY METHODS
	public boolean equals(Object another)
	{
		Point p = (Point)another;
		if(p.x != this.x)
			return false;
		if(p.y != this.y)
			return false;
		if(p.z != this.z)
			return false;
		return true;
	}

	public String toString()
	{
		String ans = "";
		ans = ans + " " + (this.x);
		ans = ans + " " + (this.y);
		ans = ans + " " + (this.z);
		return ans;
	}

	public float dist(Point another)
	{
		return (this.x-another.x)*(this.x-another.x) + (this.y-another.y)*(this.y-another.y) + (this.z-another.z)*(this.z-another.z);
	}

	public int compareTo(Point another)
	{
		if(this.getX()<another.getX())
			return -1;
		else if(this.getX()>another.getX())
			return 1;
		else
		{
			if(this.getY()<another.getY())
				return -1;
			else if(this.getY()>another.getY())
				return 1;
			else
			{
				if(this.getZ()<another.getZ())
					return -1;
				else if(this.getZ()>another.getZ())
					return 1;
				return 0;
			}
		}	 
	}

	public int hashCode()
	{
		int a = (int)this.getX();
		int b = (int)this.getY();
		int c = (int)this.getZ();
		int ans =  (101*a)+(103*b)+(107*c);
		if(ans<0)
			ans = 0-ans;
		return ans;
	}	
}