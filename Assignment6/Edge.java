public class Edge implements EdgeInterface, Comparable<Edge>
{
	Point p1 = null;
	Point p2 = null;
	PointInterface[] end_points = new Point[2];
	AL<Triangle> my_triangles = new AL(1);
	Graph<Point, Edge>.Edge<Edge> spare_edge = null;
	Graph<Triangle, Edge>.Edge<Edge> shape_edge = null;

	public Edge(Point p1, Point p2)
	{
		this.p1 = p1;
		this.p2 = p2;
		end_points[0] = p1;
		end_points[1] = p2;
	}

	public PointInterface[] edgeEndPoints()
	{
		return end_points;
	}

	public boolean equals(Object obj)
	{
		Edge another = (Edge)obj;
		if((another.p1.equals(this.p1) || another.p1.equals(this.p2)) == false)
		{
			return false;
		}
		if((another.p2.equals(this.p1) || another.p2.equals(this.p2)) == false)
		{
			return false;
		}
		return true;
	}

	public float sq_length()
	{
		float ans = 0;
		ans = ans + (p1.getX() - p2.getX())*(p1.getX() - p2.getX());
		ans = ans + (p1.getY() - p2.getY())*(p1.getY() - p2.getY());
		ans = ans + (p1.getZ() - p2.getZ())*(p1.getZ() - p2.getZ());
		return ans;
	}

	public int compareTo(Edge another)
	{
		if(this.sq_length()<another.sq_length())
			return -1;
		else if(this.sq_length()>another.sq_length())
			return 1;
		return 0;
	}

	public String toString()
	{
		return this.p1.toString() + " " + this.p2.toString();
	}

	public int hashCode()
	{
		return p1.hashCode() + p2.hashCode();
	}
}