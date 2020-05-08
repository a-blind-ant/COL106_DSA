public class Triangle implements TriangleInterface, Comparable<Triangle>
{
	Point p1 = null;
	Point p2 = null;
	Point p3 = null;
	Edge e1 = null;
	Edge e2 = null;
	Edge e3 = null;
	int time_stamp = 0;
	PointInterface[] p = new Point[3];
	Graph<Triangle, Edge>.Node<Triangle> shape_node = null;

	public Triangle(Point p1, Point p2, Point p3, Edge e1, Edge e2, Edge e3, int x)
	{
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		p[0] = p1;
		p[1] = p2;
		p[2] = p3;
		this.e1 = e1;
		this.e2 = e2;
		this.e3 = e3;
		this.time_stamp = x;
	}

	public PointInterface[] triangle_coord()
	{
		return p;
	}

	public Edge contains_Edge(Edge edge)
	{
		if(e1.equals(edge))
			return e1;
		if(e2.equals(edge))
			return e2;
		if(e3.equals(edge))
			return e3;
		return null;
	}

	public Point contains_Point(Point point)
	{
		if(p1.equals(point))
			return p1;
		if(p2.equals(point))
			return p2;
		if(p3.equals(point))
			return p3;
		return null;
	}

	public int compareTo(Triangle obj)
	{
		return this.time_stamp - obj.time_stamp;
	}

	public String toString()
	{
		return p1.toString() + " " + p2.toString() + " " + p3.toString();
	}

	public int hashCode()
	{
		return p1.hashCode() + p2.hashCode() + p3.hashCode();
	}
}