Assignment 6:
Shape.java has been used to implement all the queries.

Used 2 graphs, 1st of points and edges and 2nd of triangles and edges.
Both have been developed using adjacency list representation.

1.ADD TRIANGLE:
-> new objects have been created for points, edges and triangles. 
-> their pre existence has been checked (using hash function only for points and iterating over the list for the rest).
	If existing already, the new references have been changed to previously existing objects else nothing.
-> New triangle has then been created using all the points and edges.
-> The point Nodes and edges are then added to graph1, similarly traingle Nodes and edges to graph2.
Average case Time complexity is O(1) + O(no. of edges in graph1) + O(1) <=> O(no. of edges)	

2.TYPE MESH:
-> count1 maintains no. of edges which is part of 1 triangle.
-> count2 maintains no. of edges which is part of 2 triangles.
-> count3 maintains no. of edges which is part of >=3 triangles.
-> The three variables are updated in ADD_TRIANGLE itself and have been used to find mesh type.

3.BOUNDARY_EDGES:
-> If mesh type isn't 2, null has been returned.
-> Else, edges have been iterated over and no. of triangles have been calculated for each.
-> The ones with 1 triangle have then been sorted and returned.
Time Complexity: O(n*log n), n-> no. of edges

4.COUNT_CONNECTED_COMPONENTS:
-> Dfs was done on graph2, starting from any random node.
-> An unmarked node (if any) was then found and the process was repeated until all were marked.
-> The no. of dfs done was counted and returned.
Time complexity: O(triangles + Graphedges in graph2)

5.NEIGHBORS_OF_TRIANGLE:
-> Triangle object is found by iterating over the list. If not found, null is returned.
-> Traingle lists stored in all edges are then merged and returned.

6. EDGE NEIGHBOR TRIANGLE:
-> The triangle object is checked for in triangle lists.
-> If not found, null is returned else its edges are returned.
Time complexity: O(No. of triangles)

7. VERTEX NEIGHBOR TRIANGLE:
-> The triangle object is checked for in triangle lists.
-> If not found, null is returned else its points are returned.
Time complexity: O(No. of triangles)

8. EXTENDED NEIGHBOR TRIANGLE:
-> Similar to NEIGHBORS OF TRIANGLE, just that triangle lists of the triangle's points are iterated.

9. INCIDENT TRIANGLES:
-> The point is searched for in entire list of points.
-> if not found, null is returned else its triangles are returned (in order of insertion)
Time Complexity: O(no. of points)

10. NEIGHBORS OF POINT:
-> The given point is searched for in all points' list.
-> Its incident edges' other ends are then returned in an array.
O(no. of points)

11.EDGE NEIGHBOR OF POINTS:
-> The given point is searched for in all points' list.
-> Its incident edges are then returned in an array.

12. FACE NEIGHBORS OF POINT:
-> Same as INCIDENT TRIANGLES

13. IS_CONNECTED:
-> DFS is done with respect to one node. If the other point is also visited in the process, return true else false (also in case of inability to find either point.)

14.MAXIMUM DIAMETER:
-> BFS done with respect to each node and max distance in the process noted.
-> maxima of all these maximas returned.

15.CENTROID OF COMPONENT:
-> DFS done with respect to given triangle and marked the triangles' points visited in the process.
-> AVERAGE of x,y and z co-ordiantes returned.

16. CENTROID:
-> Similar logic as previous case used, just that the dfs was extended to all components in this case.

17. CLOSEST COMPONENTS:
-> The points were stored in separate arraylists based on the component they were a part of.
-> The distance of all points of one component was calculated from every other point of every other component and minima was returned.

18.TRIANGLE NEIGHBOR OF EDGE:
-> Edge was found in all edges list and its triangles were retirned in the order of insertion.

THE END.  
