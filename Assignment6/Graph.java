public class Graph<A,B>
{
	AL<Graph<A,B>.Node<A>> all_nodes = new AL(1);
	AL<Graph<A,B>.Edge<B>> all_edges = new AL(1);


	public class Node<A>
	{
		A obj = null;
		int pos = 0;
		AL<Edge<B>> inc_edges = new AL(1);
		int level = 0;

		public Node(A obj)
		{
			this.obj = obj;
		}
		int visited = 0;

		public int hashCode()
		{
			return this.obj.hashCode();
		}

		public boolean equals(Object temp)
		{
			Node<A> another = (Node)temp;
			return this.obj.equals(another.obj);
		}
	}

	public class Edge<B>
	{
		B obj = null;
		Node n1 = null;
		Node n2 = null;
		int pos = 0;
		/*int pos_n1 = 0; 
		int pos_n2 = 0;*/

		public Edge(B obj)
		{
			this.obj = obj;
		}

		public boolean equals(Object temp)
		{
			Edge<B> another = (Edge)temp;
			return this.obj.equals(another.obj);
		}

		public int hashCode()
		{
			return this.obj.hashCode();
		}
	}

	public Node add_node(A obj)
	{
		Node<A> node = new Node(obj);
		all_nodes.add(node);
		node.pos = all_nodes.size - 1;
		return node;	
	}

	public Edge add_edge(B obj)
	{
		Edge<B> edge = new Edge(obj);
		all_edges.add(edge);
		edge.pos = all_edges.size - 1;
		return edge;
	}

	public void dfs(Node<A> x)
	{
		int i = 0;
		x.visited = 1;
		while(x.inc_edges.get(i)!=null)
		{
			Edge<B> temp = x.inc_edges.get(i);
			if(temp.n2.visited == 0)
				this.dfs(temp.n2);
			if(temp.n1.visited == 0)
				this.dfs(temp.n1);
			i++;
		}
	}

	public int count_dfs()
	{
		int count = 0;
		int i = 0;
		while(all_nodes.get(i)!=null)
		{
			if(all_nodes.get(i).visited == 0)
				{ dfs(all_nodes.get(i)); count++;}
			i+=1;
		}
		i = 0;
		while(all_nodes.get(i)!=null)
		{
			all_nodes.get(i).visited = 0;
			i+=1;
		}
		return count;
	}

	public boolean is_connected(Node<A> n1, Node<A> n2)
	{
		this.dfs(n1);
		boolean ans = false;
		if(n2.visited==1)
			ans = true;
		else
			ans = false;
		int i = 0;
		int n = all_nodes.i + 1;
		while(i<n && all_nodes.get(i)!=null)
		{
			all_nodes.get(i).visited = 0;
			i+=1;
		}
		return ans;
	}

	public void comp_of(Node<A> x, AL<Node<A>> ans)
	{
		int i = 0;
		x.visited = 1;
		ans.add(x);
		while(x.inc_edges.get(i)!=null)
		{
			Edge<B> temp = x.inc_edges.get(i);
			if(temp.n2.visited == 0)
				this.comp_of(temp.n2, ans);
			if(temp.n1.visited == 0)
				this.comp_of(temp.n1, ans);
			i++;
		}
	}
	public void max_comp(AL<Node<A>> ans)
	{
		int i = 0;
		while(all_nodes.get(i)!=null)
		{
			if(all_nodes.get(i).visited == 0)
			{
				AL<Node<A>> temp = new AL(1);
				this.comp_of(all_nodes.get(i), temp);
				if(ans.i < temp.i)
				{
					ans.a = temp.a;
					ans.size = temp.size;
					ans.i = temp.i;
				}
			}
			i+=1;
		}
		i = 0;
		while(all_nodes.get(i)!=null)
		{
			all_nodes.get(i).visited = 0;
			i+=1;
		}
	}

	public int bfs(Node<A> x)
	{
		int ans = 0;
		x.level = 0;
		Queue<Node<A>> qu = new Queue();
		qu.enqueue(x);
		x.visited = 1;
		int i = 0;
		while(qu.size()>0)
		{
			Node<A> temp = qu.dequeue();
			i = 0;
			while(temp.inc_edges.get(i)!=null)
			{
				if(temp.inc_edges.get(i).n2.visited==0)
					{qu.enqueue(temp.inc_edges.get(i).n2); temp.inc_edges.get(i).n2.visited=1; temp.inc_edges.get(i).n2.level = temp.inc_edges.get(i).n1.level+1; ans = temp.inc_edges.get(i).n2.level;} 
				if(temp.inc_edges.get(i).n1.visited==0)
					{qu.enqueue(temp.inc_edges.get(i).n1); temp.inc_edges.get(i).n1.visited=1; temp.inc_edges.get(i).n1.level = temp.inc_edges.get(i).n2.level+1; ans = temp.inc_edges.get(i).n1.level;}
				i++;
			}
		}
		return ans;
	}

	public int max_dia()
	{
		AL<Node<A>> nodes = new AL(1);
		this.max_comp(nodes);
		int i = 0;
		int ans = 0;
		while(nodes.get(i)!=null)
		{
			int temp = this.bfs(nodes.get(i));
			int  k =0;
			while(nodes.get(k)!=null)
			{
				nodes.get(k).visited = 0;
				k+=1;
			}
			if(temp > ans)
				ans = temp;
			i++;
		}
		return ans;
	}


}