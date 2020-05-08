public class Shape implements ShapeInterface
{
	Graph<Triangle, Edge> shape = new Graph();
	int count1 = 0;
	int count2 = 0;
	int count3 = 0;
	int tri_count = 0;
	Graph<Point, Edge> spare = new Graph();
	//1.ADD_TRIANGLE
	public boolean ADD_TRIANGLE(float[] a)
	{
		float[] x = new float[3];
		x[0] = a[0];
		x[1] = a[3];
		x[2] = a[6];
		float[] y = new float[3];
		y[0] = a[1];
		y[1] = a[4];
		y[2] = a[7];
		float[] z = new float[3];
		z[0] = a[2];
		z[1] = a[5];
		z[2] = a[8];
		boolean b1 = false;
		boolean b2 = false;
		boolean b3 = false;
		float z31 = z[2] - z[0];
		float y31 = y[2] - y[0];
		float x31 = x[2] - x[0];
		float z21 = z[1] - z[0];
		float y21 = y[1] - y[0];
		float x21 = x[1] - x[0];
		if(z31*y21 == z21*y31)
			b1 = true;
		if(x31*y21 == y31*x21)
			b2 = true;
		if(z31*x21 == x31*z21)
			b3 = true;
		if(b1 && b2 && b3)
			{return false;}
		else
		{
			Point p1 = new Point(x[0],y[0],z[0]);
			Point p2 = new Point(x[1],y[1],z[1]);
			Point p3 = new Point(x[2],y[2],z[2]);
			Edge e1 = new Edge(p1,p2);
			Edge e2 = new Edge(p2,p3);
			Edge e3 = new Edge(p3,p1);

			//POINTS CHECK
			int i = 0;
			boolean flag1 = true;
			boolean flag2 = true;
			boolean flag3 = true;
			boolean fla1 = true;
			boolean fla2 = true;
			boolean fla3 = true;
			/*while(spare.all_nodes.get(i)!=null && (flag1 || flag2 || flag3))
			{				
				if(spare.all_nodes.get(i).obj.equals(p1))
				{
					flag1 = false;
					p1 = spare.all_nodes.get(i).obj;
				}
				if(spare.all_nodes.get(i).obj.equals(p2))
				{
					flag2 = false;
					p2 = spare.all_nodes.get(i).obj;
				}
				if(spare.all_nodes.get(i).obj.equals(p3))
				{
					flag3 = false;
					p3 = spare.all_nodes.get(i).obj;
				}
				i+=1;
			}

			Graph<Point, Edge>.Node<Point> np1 = null;
			if(flag1)
				{np1 = spare.add_node(p1); p1.spare_node = np1;}
			else
				np1 = p1.spare_node;
			Graph<Point, Edge>.Node<Point> np2 = null;
			if(flag2)
				{np2 = spare.add_node(p2); p2.spare_node = np2;}
			else
				np2 = p2.spare_node;
			Graph<Point, Edge>.Node<Point> np3 = null;
			if(flag3)
				{np3 = spare.add_node(p3); p3.spare_node = np3;}
			else
				np3 = p3.spare_node;*/

			Graph<Point, Edge>.Node<Point> np1 = spare.new Node<Point>(p1);	
			Graph<Point, Edge>.Node<Point> np2 = spare.new Node<Point>(p2);	
			Graph<Point, Edge>.Node<Point> np3 = spare.new Node<Point>(p3);
			Graph<Point, Edge>.Node<Point> np_temp = null;
			np_temp = spare.all_nodes.search(np1);
			if(np_temp!=null)
			{
				np1 = np_temp;
				np_temp = null;
			}
			else
			{
				spare.all_nodes.add(np1);
			}
			np_temp = spare.all_nodes.search(np2);
			if(np_temp!=null)
			{
				np2 = np_temp;
				np_temp = null;
			}
			else
			{
				spare.all_nodes.add(np2);
			}
			np_temp = spare.all_nodes.search(np3);
			if(np_temp!=null)
			{
				np3 = np_temp;
				np_temp = null;
			}
			else
			{
				spare.all_nodes.add(np3);
			}
			p1 = np1.obj; 
			p2 = np2.obj; 
			p3 = np3.obj;
			p1.spare_node = np1; 
			p2.spare_node = np2; 
			p3.spare_node = np3; 

			//Assert: np1, np2, np3 correspond  to the correct nodes of p1,p2 and p3 in spare.

			//e1 <-> p1 and p2
			i = 0;
			while(np1.inc_edges.get(i)!=null)
			{
				Graph<Point, Edge>.Edge<Edge> kk = np1.inc_edges.get(i);
				if(kk.obj.equals(e1))
				{
					e1 = kk.obj;
					fla1 = false;
				}
				i++;
			}
			if(fla1)
			{
				Graph<Point, Edge>.Edge<Edge> ee1 = spare.add_edge(e1);
				e1.spare_edge = ee1;
				np1.inc_edges.add(ee1);
				np2.inc_edges.add(ee1);
				ee1.n1 = np1;
				ee1.n2 = np2;
			}
			/*Graph<Point, Edge>.Edge<Edge> ee_temp = null;
			Graph<Point, Edge>.Edge<Edge> ee1 = spare.new Edge<Edge>(e1);	
			Graph<Point, Edge>.Edge<Edge> ee2 = spare.new Edge<Edge>(e2);	
			Graph<Point, Edge>.Edge<Edge> ee3 = spare.new Edge<Edge>(e3);	
			ee_temp = spare.all_edges.search(ee1);	
			if(ee_temp!=null)
			{
				ee1 = ee_temp;
				ee_temp = null;
			}
			else
			{
				spare.all_edges.add(ee1);
			}
			ee_temp = spare.all_edges.search(ee2);	
			if(ee_temp!=null)
			{
				ee2 = ee_temp;
				ee_temp = null;
			}
			else
			{
				spare.all_edges.add(ee2);
			}
			ee_temp = spare.all_edges.search(ee3);	
			if(ee_temp!=null)
			{
				ee3 = ee_temp;
				ee_temp = null;
			}
			else
			{
				spare.all_edges.add(ee3);
			}
			e1 = ee1.obj;
			e2 = ee2.obj;
			e3 = ee3.obj;
			e1.spare_edge = ee1;
			e2.spare_edge = ee2;
			e3.spare_edge = ee3;*/


			//e2 <-> p2 and p3
			i = 0;
			while(p2.spare_node.inc_edges.get(i)!=null)
			{
				Graph<Point, Edge>.Edge<Edge> kk = p2.spare_node.inc_edges.get(i);
				if(kk.obj.equals(e2))
				{
					e2 = kk.obj;
					fla2 = false;
				}
				i++;
			}
			if(fla2)
			{
				Graph<Point, Edge>.Edge<Edge> ee2 = spare.add_edge(e2);
				e2.spare_edge = ee2;
				np2.inc_edges.add(ee2);
				np3.inc_edges.add(ee2);
				ee2.n1 = np2;
				ee2.n2 = np3;
			}
			//e3 <-> p3 and p1
			i = 0;
			while(p3.spare_node.inc_edges.get(i)!=null)
			{
				Graph<Point, Edge>.Edge<Edge> kk = p3.spare_node.inc_edges.get(i);
				if(kk.obj.equals(e3))
				{
					e3 = kk.obj;
					fla3 = false;
				}
				i++;
			}
			if(fla3)
			{
				Graph<Point, Edge>.Edge<Edge> ee3 = spare.add_edge(e3);
				e3.spare_edge = ee3;
				np3.inc_edges.add(ee3);
				np1.inc_edges.add(ee3);
				ee3.n1 = np3;
				ee3.n2 = np1;
			}


			//Assert: e1, e2 and e3 have been built properly and added to spare.

			//SHAPE GRAPH ADDITIONS
			tri_count++;
			Triangle triangle = new Triangle(p1, p2, p3, e1, e2, e3, tri_count);
			p1.my_triangles.add(triangle);
			p2.my_triangles.add(triangle);
			p3.my_triangles.add(triangle);
			Graph<Triangle, Edge>.Node<Triangle> node = shape.add_node(triangle);
			triangle.shape_node = node;
			int j = 0;
			while(e1.my_triangles.get(j)!=null)	
			{
				Graph<Triangle, Edge>.Node<Triangle> t = e1.my_triangles.get(j).shape_node;
				Graph<Triangle, Edge>.Edge<Edge> e = shape.add_edge(e1);
				node.inc_edges.add(e);
				t.inc_edges.add(e);
				e.n1 = t;
				e.n2 = node;
				node.inc_edges.add(e);
				j++;
			}
			e1.my_triangles.add(triangle);
			int n1 = e1.my_triangles.i;
			if(n1==1)
			{
				count1++;
			}
			else if(n1==2)
			{
				count2++;
				count1--;
			}
			else if(n1==3)
			{
				count3++;
				count2--;
			}
			else
			{
				count3++;
			}
			j = 0;
			while(e2.my_triangles.get(j)!=null)
			{
				Graph<Triangle, Edge>.Node<Triangle> t = e2.my_triangles.get(j).shape_node;
				Graph<Triangle, Edge>.Edge<Edge> e = shape.add_edge(e2);
				node.inc_edges.add(e);
				t.inc_edges.add(e);
				e.n1 = t;
				e.n2 = node;
				node.inc_edges.add(e);
				j++;
			}
			e2.my_triangles.add(triangle);
			int n2 = e2.my_triangles.i;
			if(n2==1)
			{
				count1++;
			}
			else if(n2==2)
			{
				count2++;
				count1--;
			}
			else if(n2==3)
			{
				count3++;
				count2--;
			}
			else
			{
				count3++;
			}
			j = 0;
			while(e3.my_triangles.get(j)!=null)
			{
				Graph<Triangle, Edge>.Node<Triangle> t = e3.my_triangles.get(j).shape_node;
				Graph<Triangle, Edge>.Edge<Edge> e = shape.add_edge(e3);
				node.inc_edges.add(e);
				t.inc_edges.add(e);
				e.n1 = t;
				e.n2 = node;
				node.inc_edges.add(e);
				j++;
			}
			e3.my_triangles.add(triangle);
			int n3 = e3.my_triangles.i;
			if(n3==1)
			{
				count1++;
			}
			else if(n3==2)
			{
				count2++;
				count1--;
			}
			else if(n3==3)
			{
				count3++;
				count2--;
			}
			else
			{
				count3++;
			}
			return true;
		}
	}

	//2.MESH TYPE
	public int TYPE_MESH()
	{
		if(count1==0 && count3==0)
			return 1;
		else if(count1>0 && count3==0)
			return 2;
		else
			return 3;			
	}

	//3.BOUNDARY_EDGES
	//*********************************************
	//**SORTING TBD *******************************
	//*********************************************
	public EdgeInterface[] BOUNDARY_EDGES()
	{
		if(this.TYPE_MESH()!=2)
			return null;
		else
		{
			AL<Graph<Point, Edge>.Edge<Edge>> edges = spare.all_edges;
			EdgeInterface[] ans = new Edge[count1];
			int i = 0;
			int j = 0;
			while(edges.get(i)!=null)
			{
				if(edges.get(i).obj.my_triangles.i == 1)
				{
					ans[j] = edges.get(i).obj;
					j+=1;
				}
				i+=1;
			}
			this.edge_sort((Edge[])ans, 0 ,ans.length-1);
			return ans;
		}
	}

	//4.COUNT_CONNECTED_COMPONENTS
	public int COUNT_CONNECTED_COMPONENTS()
	{
		return shape.count_dfs();
	}

	//5.NEIGHBORS OF TRIANGLE
	//*********************************************
	//**SORTING TBD ******************************* HAVE DONE SOMETHING NOW.
	//*********************************************
	public TriangleInterface[] NEIGHBORS_OF_TRIANGLE(float[] a)
	{
		AL<Graph<Triangle, Edge>.Node<Triangle>> nodes = shape.all_nodes;
		int i = 0;
		Triangle temp = null;
		Point p1 = new Point(a[0], a[1], a[2]);
		Point p2 = new Point(a[3], a[4], a[5]);
		Point p3 = new Point(a[6], a[7], a[8]);
		while(nodes.get(i)!=null)
		{
			Triangle local = nodes.get(i).obj;
			if(local.contains_Point(p1)!=null && local.contains_Point(p2)!=null && local.contains_Point(p3)!=null)
			{
				temp = local;
				break;
			}
			i+=1;
		}
		if(temp==null)
			return null;
		else
		{
			Triangle og = temp;
			int count = temp.e1.my_triangles.i + temp.e2.my_triangles.i + temp.e3.my_triangles.i -3;
			if(count==0)
				return null;
			else
			{
				AL<Triangle> ans = new AL(1);
				AL<Triangle> l1 = (temp.e1.my_triangles);
				AL<Triangle> l2 = (temp.e2.my_triangles);
				AL<Triangle> l3 = (temp.e3.my_triangles);
				int i1 = 0;
				int i2 = 0;
				int i3 = 0;
				while(l1.get(i1)!=null && l2.get(i2)!=null)
				{
					temp = null;
					if(l1.get(i1).compareTo(l2.get(i2)) == 0)
					{
						temp = l1.get(i1);
						i1++;
						i2++;
					}
					else if(l1.get(i1).compareTo(l2.get(i2)) < 0)
					{
						temp = l1.get(i1);
						i1++;
					}
					else
					{
						temp = l2.get(i2);
						i2++;
					}
					if(!temp.equals(og))
					ans.add(temp);
				}
				while(l1.get(i1)!=null)
				{
					if(!og.equals(l1.get(i1)))
					ans.add(l1.get(i1));
					i1++;
				}
				while(l2.get(i2)!=null)
				{
					if(!og.equals(l2.get(i2)))
					ans.add(l2.get(i2));
					i2++;
				}
				AL<Triangle> answer = new AL(1);
				l1 = ans;
				l2 = l3;
				i1 = 0;
				i2 = 0;
				while(l1.get(i1)!=null && l2.get(i2)!=null)
				{
					temp = null;
					if(l1.get(i1).compareTo(l2.get(i2)) == 0)
					{
						temp = l1.get(i1);
						i1++;
						i2++;
					}
					else if(l1.get(i1).compareTo(l2.get(i2)) < 0)
					{
						temp = l1.get(i1);
						i1++;
					}
					else
					{
						temp = l2.get(i2);
						i2++;
					}
					if(!temp.equals(og))
					answer.add(temp);
				}
				while(l1.get(i1)!=null)
				{
					if(!og.equals(l1.get(i1)))
					answer.add(l1.get(i1));
					i1++;
				}
				while(l2.get(i2)!=null)
				{
					if(!og.equals(l2.get(i2)))
					answer.add(l2.get(i2));
					i2++;
				}
				TriangleInterface[] ls = new Triangle[answer.i];
				i = 0;
				while(answer.get(i)!=null)
				{	
					ls[i] = answer.get(i);
					i++;
				}
				return ls;
			}
		}
	}

	//6.EDGE NEIGHBOR OF TRIANGLE
	public EdgeInterface[] EDGE_NEIGHBOR_TRIANGLE(float[] a)
	{
		AL<Graph<Triangle, Edge>.Node<Triangle>> nodes = shape.all_nodes;
		int i = 0;
		Triangle temp = null;
		Point p1 = new Point(a[0], a[1], a[2]);
		Point p2 = new Point(a[3], a[4], a[5]);
		Point p3 = new Point(a[6], a[7], a[8]);
		while(nodes.get(i)!=null)
		{
			Triangle local = nodes.get(i).obj;
			if(local.contains_Point(p1)!=null && local.contains_Point(p2)!=null && local.contains_Point(p3)!=null)
			{
				temp = local;
				break;
			}
			i+=1;
		}
		if(temp==null)
			return null;
		else
		{
			EdgeInterface[] ans = new Edge[3];
			ans[0] = temp.e1;
			ans[1] = temp.e2;
			ans[2] = temp.e3;
			return ans;
		}
	}

	//7.VERTEX NEIGHBOR OF TRIANGLE
	public PointInterface[] VERTEX_NEIGHBOR_TRIANGLE(float[] a)
	{
		AL<Graph<Triangle, Edge>.Node<Triangle>> nodes = shape.all_nodes;
		int i = 0;
		Triangle temp = null;
		Point p1 = new Point(a[0], a[1], a[2]);
		Point p2 = new Point(a[3], a[4], a[5]);
		Point p3 = new Point(a[6], a[7], a[8]);
		while(nodes.get(i)!=null)
		{
			Triangle local = nodes.get(i).obj;
			if(local.contains_Point(p1)!=null && local.contains_Point(p2)!=null && local.contains_Point(p3)!=null)
			{
				temp = local;
				break;
			}
			i+=1;
		}
		if(temp==null)
			return null;
		else
		{
			PointInterface[] ans = new Point[3];
			ans[0] = temp.p1;
			ans[1] = temp.p2;
			ans[2] = temp.p3;
			return ans;
		}
	}

	//8.EXTENDED_NEIGHBOR_TRIANGLE
	//*********************************************
	//**SORTING TBD *******************************
	//*********************************************
	public TriangleInterface [] EXTENDED_NEIGHBOR_TRIANGLE(float [] a)
	{
		AL<Graph<Triangle, Edge>.Node<Triangle>> nodes = shape.all_nodes;
		int i = 0;
		Triangle temp = null;
		Point p1 = new Point(a[0], a[1], a[2]);
		Point p2 = new Point(a[3], a[4], a[5]);
		Point p3 = new Point(a[6], a[7], a[8]);
		while(nodes.get(i)!=null)
		{
			Triangle local = nodes.get(i).obj;
			if(local.contains_Point(p1)!=null && local.contains_Point(p2)!=null && local.contains_Point(p3)!=null)
			{
				temp = local;
				break;
			}
			i+=1;
		}
		if(temp==null)
			return null;
		else
		{
			Triangle og = temp;
			int count = temp.p1.my_triangles.i + temp.p2.my_triangles.i + temp.p3.my_triangles.i -3;
			if(count==0)
				return null;
			else
			{
				AL<Triangle> ans = new AL(1);
				AL<Triangle> l1 = (temp.p1.my_triangles);
				AL<Triangle> l2 = (temp.p2.my_triangles);
				AL<Triangle> l3 = (temp.p3.my_triangles);
				int i1 = 0;
				int i2 = 0;
				int i3 = 0;
				while(l1.get(i1)!=null && l2.get(i2)!=null)
				{
					temp = null;
					if(l1.get(i1).compareTo(l2.get(i2)) == 0)
					{
						temp = l1.get(i1);
						i1++;
						i2++;
					}
					else if(l1.get(i1).compareTo(l2.get(i2)) < 0)
					{
						temp = l1.get(i1);
						i1++;
					}
					else
					{
						temp = l2.get(i2);
						i2++;
					}
					if(!temp.equals(og))
					ans.add(temp);
				}
				while(l1.get(i1)!=null)
				{
					if(!og.equals(l1.get(i1)))
					ans.add(l1.get(i1));
					i1++;
				}
				while(l2.get(i2)!=null)
				{
					if(!og.equals(l2.get(i2)))
					ans.add(l2.get(i2));
					i2++;
				}
				AL<Triangle> answer = new AL(1);
				l1 = ans;
				l2 = l3;
				i1 = 0;
				i2 = 0;
				while(l1.get(i1)!=null && l2.get(i2)!=null)
				{
					temp = null;
					if(l1.get(i1).compareTo(l2.get(i2)) == 0)
					{
						temp = l1.get(i1);
						i1++;
						i2++;
					}
					else if(l1.get(i1).compareTo(l2.get(i2)) < 0)
					{
						temp = l1.get(i1);
						i1++;
					}
					else
					{
						temp = l2.get(i2);
						i2++;
					}
					if(!temp.equals(og))
					answer.add(temp);
				}
				while(l1.get(i1)!=null)
				{
					if(!og.equals(l1.get(i1)))
					answer.add(l1.get(i1));
					i1++;
				}
				while(l2.get(i2)!=null)
				{
					if(!og.equals(l2.get(i2)))
					answer.add(l2.get(i2));
					i2++;
				}
				TriangleInterface[] ls = new Triangle[answer.i];
				i = 0;
				while(answer.get(i)!=null)
				{	
					ls[i] = answer.get(i);
					i++;
				}
				return ls;
			}
		}	
	}

	//9.INCIDENT_TRIANGLES
	public TriangleInterface [] INCIDENT_TRIANGLES(float [] a)
	{
		AL<Graph<Point, Edge>.Node<Point>> points = spare.all_nodes;
		Point p1 = new Point(a[0], a[1], a[2]);
		Point temp = null;
		int i = 0;
		while(points.get(i)!=null)
		{
			if(p1.equals(points.get(i).obj))
			{
				temp = points.get(i).obj;
				break;
			}
			i++;
		}
		if(temp==null)
			return null;
		else
		{
			TriangleInterface[] ans = new Triangle[temp.my_triangles.i];
			i = 0;
			while(temp.my_triangles.get(i)!=null)
			{
				ans[i] = temp.my_triangles.get(i);
				i++;
			}
			return ans;
		}	
	}

	//10.NEIGHBORS_OF_POINT
	public PointInterface [] NEIGHBORS_OF_POINT(float [] a)
	{
		AL<Graph<Point, Edge>.Node<Point>> points = spare.all_nodes;
		Point p1 = new Point(a[0], a[1], a[2]);
		Point temp = null;
		int i = 0;
		while(points.get(i)!=null)
		{
			if(p1.equals(points.get(i).obj))
			{
				temp = points.get(i).obj;
				break;
			}
			i++;
		}
		if(temp==null)
			return null;
		Graph<Point, Edge>.Node<Point> node = temp.spare_node;
		AL<Graph<Point, Edge>.Edge<Edge>> lol = node.inc_edges;
		PointInterface[] ans = new Point[lol.i];
		i = 0;
		while(lol.get(i)!=null)
		{
			if(lol.get(i).n1==node)
				ans[i] = (Point)lol.get(i).n2.obj;
			if(lol.get(i).n2==node)
				ans[i] = (Point)lol.get(i).n1.obj;
			i+=1;
		}
		return ans;
	}

	//11.EDGE NEIGHBORS OF POINT
	public EdgeInterface [] EDGE_NEIGHBORS_OF_POINT(float [] a)
	{
		AL<Graph<Point, Edge>.Node<Point>> points = spare.all_nodes;
		Point p1 = new Point(a[0], a[1], a[2]);
		Point temp = null;
		int i = 0;
		while(points.get(i)!=null)
		{
			if(p1.equals(points.get(i).obj))
			{
				temp = points.get(i).obj;
				break;
			}
			i++;
		}
		if(temp==null)
			return null;
		Graph<Point, Edge>.Node<Point> node = temp.spare_node;
		AL<Graph<Point, Edge>.Edge<Edge>> lol = node.inc_edges;
		EdgeInterface[] ans = new Edge[lol.i];
		i = 0;
		while(lol.get(i)!=null)
		{
			ans[i] = lol.get(i).obj;
			i+=1;
		}
		return ans;
	}

	//12.FACE NEIGHBORS OF POINT
	public TriangleInterface [] FACE_NEIGHBORS_OF_POINT(float [] a)	
	{
		AL<Graph<Point, Edge>.Node<Point>> points = spare.all_nodes;
		Point p1 = new Point(a[0], a[1], a[2]);
		Point temp = null;
		int i = 0;
		while(points.get(i)!=null)
		{
			if(p1.equals(points.get(i).obj))
			{
				temp = points.get(i).obj;
				break;
			}
			i++;
		}
		if(temp==null)
			return null;
		i = 0;
		TriangleInterface[] ans = new Triangle[temp.my_triangles.i];
		while(temp.my_triangles.get(i)!=null)
		{
			ans[i] = temp.my_triangles.get(i);
			i++;
		}
		return ans;
	}

	//13. IS_CONNECTED(Traingle1 and Triangle2)
	public boolean IS_CONNECTED(float [] a1, float [] a2)
	{
		AL<Graph<Triangle, Edge>.Node<Triangle>> nodes = shape.all_nodes;
		int i = 0;
		Triangle temp1 = null;
		Point p1 = new Point(a1[0], a1[1], a1[2]);
		Point p2 = new Point(a1[3], a1[4], a1[5]);
		Point p3 = new Point(a1[6], a1[7], a1[8]);
		while(nodes.get(i)!=null)
		{
			Triangle local = nodes.get(i).obj;
			if(local.contains_Point(p1)!=null && local.contains_Point(p2)!=null && local.contains_Point(p3)!=null)
			{
				temp1 = local;
				break;
			}
			i+=1;
		}
		if(temp1==null)
			return false;

		i = 0;
		Triangle temp2 = null;
		p1 = new Point(a2[0], a2[1], a2[2]);
		p2 = new Point(a2[3], a2[4], a2[5]);
		p3 = new Point(a2[6], a2[7], a2[8]);
		while(nodes.get(i)!=null)
		{
			Triangle local = nodes.get(i).obj;
			if(local.contains_Point(p1)!=null && local.contains_Point(p2)!=null && local.contains_Point(p3)!=null)
			{
				temp2 = local;
				break;
			}
			i+=1;
		}
		if(temp2==null)
			return false;

		Graph<Triangle, Edge>.Node<Triangle> n1 = temp1.shape_node;			
		Graph<Triangle, Edge>.Node<Triangle> n2 = temp2.shape_node;

		return shape.is_connected(n1, n2);			
	}

	//14.TRIANGLE_NEIGHBOR_OF_EDGE
	public TriangleInterface [] TRIANGLE_NEIGHBOR_OF_EDGE(float [] a)
	{
		Edge temp = null;
		Point p1 = new Point(a[0], a[1], a[2]);
		Point p2 = new Point(a[3], a[4], a[5]);
		Edge tester = new Edge(p1, p2);
		AL<Graph<Point, Edge>.Edge<Edge>> edges = spare.all_edges;
		int i = 0;
		while(edges.get(i)!=null)
		{
			Edge local = edges.get(i).obj;
			if(local.equals(tester))
			{
				temp = local;
				break;
			}
			i+=1;
		}
		if(temp==null)
			return null;
		TriangleInterface[] ans = new Triangle[temp.my_triangles.i];
		i = 0;
		while(temp.my_triangles.get(i)!=null)
		{
			ans[i] = temp.my_triangles.get(i);
			i++;
		}
		return ans;
	}

	//15.MAXIMUM DIAMETER
	public int MAXIMUM_DIAMETER()
	{
		return shape.max_dia();
	}

	//16.CENTROID OF COMPONENT
	public PointInterface CENTROID_OF_COMPONENT(float[] a)
	{
		Point point = new Point(a[0], a[1], a[2]);
		boolean flag = true;
		AL<Graph<Point, Edge>.Node<Point>> points = spare.all_nodes;
		int i = 0;
		while(points.get(i)!=null)
		{
			if(points.get(i).obj.equals(point))
			{
				flag = false;
				point = points.get(i).obj;
				break;
			}
			i++;
		}
		if(flag)
		{
			return null;
		}
		else
		{
			Triangle t = point.my_triangles.get(0);
			AL<Point> ps = new AL<Point>(1);
			this.shape_dfs(t.shape_node, ps);
			i = 0;
			float n = (float)ps.i;
			float accx = 0;
			float accy = 0;
			float accz = 0;
			while(ps.get(i)!=null)
			{
				accx+=ps.get(i).getX();
				accy+=ps.get(i).getY();
				accz+=ps.get(i).getZ();
				ps.get(i).marked = 0;
				i++;
			}
			Point c = new Point(accx/n, accy/n, accz/n);
			i = 0;
			while(shape.all_nodes.get(i)!=null)
			{
				shape.all_nodes.get(i).visited = 0;
				i++;
			}
			return c;
		}			
	}
	public void shape_dfs(Graph<Triangle,Edge>.Node<Triangle> x, AL<Point> ps)
	{
		int i = 0;
		x.visited = 1;
		if(x.obj.p1.marked==0)
		{
			ps.add(x.obj.p1);
			x.obj.p1.marked = 1;
		}
		if(x.obj.p2.marked==0)
		{
			ps.add(x.obj.p2);
			x.obj.p2.marked = 1;
		}
		if(x.obj.p3.marked==0)
		{
			ps.add(x.obj.p3);
			x.obj.p3.marked = 1;
		}
		while(x.inc_edges.get(i)!=null)
		{
			Graph<Triangle, Edge>.Edge<Edge> temp = x.inc_edges.get(i);
			if(temp.n2.visited == 0)
				this.shape_dfs(temp.n2, ps);
			if(temp.n1.visited == 0)
				this.shape_dfs(temp.n1, ps);
			i++;
		}
	}

	//17.CENTROID
	public PointInterface[] CENTROID()
	{
		AL<Graph<Triangle, Edge>.Node<Triangle>> triangles = shape.all_nodes;
		int i = 0;
		AL<Point> ans = new AL(1);
		while(triangles.get(i)!=null)
		{
			if(triangles.get(i).visited==0)
			{
				AL<Point> poc = new AL<Point>(1);
				this.shape_dfs(triangles.get(i), poc);
				int j = 0;
				float n = (float)poc.i;
				float accx = 0;
				float accy = 0;
				float accz = 0;
				while(poc.get(j)!=null)
				{
					accx+=poc.get(j).getX();
					accy+=poc.get(j).getY();
					accz+=poc.get(j).getZ();
					poc.get(j).marked = 0;
					j++;
				}
				Point c = new Point(accx/n, accy/n, accz/n);
				ans.add(c);
			}
			i++;
		}
		PointInterface[] answer = new Point[ans.i];
		i = 0;
		while(ans.get(i)!=null)
		{
			answer[i] = ans.get(i);
			i++;
		}
		i = 0;
		while(shape.all_nodes.get(i)!=null)
		{
			shape.all_nodes.get(i).visited = 0;
			i++;
		}
		this.point_sort((Point[])answer,0 , answer.length-1);
		return answer;
	}

	//18.CLOSEST COMPONENTS
	public PointInterface[] CLOSEST_COMPONENTS()
	{
		AL<Graph<Triangle, Edge>.Node<Triangle>> triangles = shape.all_nodes;
		int i = 0;
		int counter = 0;
		AL<AL<Point>> comp_points = new AL(1);
		while(triangles.get(i)!=null)
		{
			if(triangles.get(i).visited==0)
			{
				comp_points.add(new AL(1));
				this.shape_dfs(triangles.get(i), comp_points.get(counter));
				int count = 0;
				while(comp_points.get(counter).get(count)!=null)
				{
					comp_points.get(counter).get(count).marked = 0;
					count++;
				}
				counter++;
			}
			i++;
		}
		if(comp_points.i==1)
			return null;
		Point p1 = comp_points.get(0).get(0);
		Point p2 = comp_points.get(1).get(0);
		float ans = p1.dist(p2);
		i = 0;
		int j = 0;
		//System.out.println(ans);
		while(comp_points.get(i+1)!=null)
		{
			j = i+1;
			while(comp_points.get(j)!=null)
			{
				int a1 = 0;
				int a2 = 0;
				while(comp_points.get(i).get(a1)!=null)
				{
					a2 = 0;
					while(comp_points.get(j).get(a2)!=null)
					{
						if(comp_points.get(i).get(a1).dist(comp_points.get(j).get(a2))<ans)
						{
							p1 = comp_points.get(i).get(a1);
							p2 = comp_points.get(j).get(a2);
							ans = comp_points.get(i).get(a1).dist(comp_points.get(j).get(a2));
						}
						if(ans==0)
							{break;}
						a2++;
					}
					a1++;
				}
				j++;
			}
			i++;
		}
		i = 0;
		while(shape.all_nodes.get(i)!=null)
		{
			shape.all_nodes.get(i).visited = 0;
			i++;
		}
		PointInterface[] answer = new Point[2];
		answer[0] = p1;
		answer[1] = p2;
		return answer;
	}


//CHECK FALTU WAALI GATLIA IN ALL FUNCTIONS

	//EDGE SORTING FUNCTIONS
	public void edge_sort(Edge[] a, int s, int e)
	{
		if(s<e)
		{
			int m = (s+e)/2;
			edge_sort(a, s ,m);
			edge_sort(a, m+1, e);
			edge_merge(a, s, m, e);
		}
	}
	public void edge_merge(Edge[] a, int l, int m, int r)
	{
		int n1 = m-l+1;
		int n2 = r-m;
		Edge[] a1 = new Edge[n1];
		Edge[] a2 = new Edge[n2];
		for(int i = 0; i<n1; i++)
		{
			a1[i] = a[l+i];
		}
		for(int i = 0;i<n2; i++)
		{
			a2[i] = a[i+m+1];
		}
		int i = 0;
		int j = 0;
		int k = l;
		while(i<n1 && j<n2)
		{
			if(a1[i].compareTo(a2[j])<=0)
			{
				a[k] = a1[i];
				i++;
			}
			else
			{
				a[k] = a2[j];
				j++;
			}
			k++;
		}
		while(i<n1)
		{
			a[k] = a1[i];
			i++;
			k++;
		}
		while(j<n2)
		{
			a[k] = a2[j];
			j++;
			k++;
		}
	}

	public void point_sort(Point[] a, int s, int e)
	{
		if(s<e)
		{
			int m = (s+e)/2;
			point_sort(a, s ,m);
			point_sort(a, m+1, e);
			point_merge(a, s, m, e);
		}
	}
	public void point_merge(Point[] a, int l, int m, int r)
	{
		int n1 = m-l+1;
		int n2 = r-m;
		Point[] a1 = new Point[n1];
		Point[] a2 = new Point[n2];
		for(int i = 0; i<n1; i++)
		{
			a1[i] = a[l+i];
		}
		for(int i = 0;i<n2; i++)
		{
			a2[i] = a[i+m+1];
		}
		int i = 0;
		int j = 0;
		int k = l;
		while(i<n1 && j<n2)
		{
			if(a1[i].compareTo(a2[j])<=0)
			{
				a[k] = a1[i];
				i++;
			}
			else
			{
				a[k] = a2[j];
				j++;
			}
			k++;
		}
		while(i<n1)
		{
			a[k] = a1[i];
			i++;
			k++;
		}
		while(j<n2)
		{
			a[k] = a2[j];
			j++;
			k++;
		}
	}

}