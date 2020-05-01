1. Problem Statement: http://www.cse.iitd.ac.in/~csz188011/col106-a3.html

Given Interfaces and classes(written as given):
	public interface Student_
	public interface MyHashTable_<K, T>
	public class NotFoundException extends Exception //Thrown when a method isn't able to find an element for the given input(key)

//----------------------------------------------//

2.public class Student implements Student_:
	//Variables:
	String fname
	String lname
	String hostel
	String department
	String cgpa 
	String key
	//Methods:
	public Student(String fname,String lname,String hostel,String department,String cgpa) //Constructor
	public toString()	//returns the concatenation of fname and lname of the student which acts as key for the particular student when needed				

//------------------------------------------//

3.public class Node<K,T>:	//Used in SCBST
	//Variables:
	Pair<K,T> value	//Stores the pair of Student and the corresponding key
	Node left = null
	Node right = null
	//Methods:
	public Node(Pair<K,T> obj)	//Constructor 

4.public class Node2<T>:	//Used in DH
	//Variables:
	T value
	boolean flag	//To determine the state of the node
	//Methods:
	public Node2(T obj)	//Constructor

IMPLEMENTAION:
	The state of node2_obj can be interpreted as 1.never held a value (if the node2 object is null), 2.held a value but is now empty (if node2!=null and node2_obj.value == null) 
	and 3.Still holds a value (if if node2!=null and node2_obj.value != null)

//------------------------------------------//

5.public class Pair<A,B>:	
	//Variables:
	A a
	B b
	//Methods:
	public Pair(A a,B b)	//Constructor

//--------------------------------------------//

6.public class Key<K> implements Comparable<Key<K>>		//Class to implement keys for a given value
	//Variables:
	K first
	K last
	//Methods:
	public Key(K f, K l)	//Constructor
	public String toString()	//Converts the key to its string equivalent using overriding of methods (fname + lname in this case)
	public int compareTo(Key<K> key_temp)	//Overrides the method to compare on the basis of only first names

//--------------------------------------------//

7.public class DoubleHash<K,T> implements MyHashTable_<K,T>
	//Variables:	
	int size	//Stores the table capacity
	Node2<T>[] hashtable 	//Array implementation of the table
	//Methods:
	public static long djb2(String str, int hashtableSize)	//Given hash function 1	
	public static long sdbm(String str, int hashtableSize) 	//Given hash function 2
	public DoubleHash(int size)		//Constructor

IMPLEMENTATION: K->Key and T->Student
				Node2 has a Student field and thus each node contains a Student.

APPROACH AND ANALYSIS:
	1.insert() -> We maintain a counter (say i), which helps us calculate the hashvalue for a given key (for the object to be inserted) repeatedly.
				  the value h1 + i*h2 is calculated in each iteration, in case the array[i] is empty, value is inserted else iteration goes on.
				  This is repeated until we find an empty slot or we have visited all possible array indices and they begin to repeat. 
				  Returns -1 if unable to insert object upon which main prints "E".
				  TIME COMPLEXITY:Best case = O(1) [no collision]
				  				  Average case = O(1) [under the assumption that hash function gives random outputs and T>N (T ~ 1.5N or 2N)]
				  				  Worst case = O(n) [empty slot is reached after each slot has been visited] 												 	
	
	2.contains() ->Similar logic has been used for finding the element. A slot is visited in each iteration until a node in state 1 
				   (refer Node2 for explanation of states) is found or array indices begin to repeat.
				   TIME COMPLEXITY:Best case = O(1)
				   				   Average case = O(1)
				   				   Worst case = O(n)

	3.delete() ->Similar procedure is followed. If corresponding slot is found state is changed from 3 to 2 else throws NotFoundException to print
				 "E" in the command line.
				 TIME COMPLEXITY:Best case = O(1)
				   				  Average case = O(1)
				   				  Worst case = O(n)

	4.get() -> Similar procedure. Returns the Student object if found else throws NotFoundException caught by main to print "E".
			   TIME COMPLEXITY:Best case = O(1)
				   			   Average case = O(1)
				   			   Worst case = O(n)

	5.update() ->Similar procedure. Searches for the element corresponding to the given key. If found element is updated else -1 is returned.
				 TIME COMPLEXITY:Best case = O(1)
				   				 Average case = O(1)
				   				 Worst case = O(n)			   				   

	6.address() ->Similar procedure. Searches for the element corresponding to the given key. If found index is returned else NotFoundException is thrown.
				  TIME COMPLEXITY:Best case = O(1)
				  				  Average case = O(1)
				  				  Worst case = O(n)

//---------------------------------------------//

8.public class HashBST<K,T> implements MyHashTable<K,T>
	//Variables:
	int size 	//Stores the table capacity
	Node2<T>[] hashtable 	//Array implementation of the table
	//Methods:
	public static long djb2(String str, int hashtablesize)	//Given hash function
	public HashBST(int size)

IMPLEMENTATION:	K->Key and T->Student 
				Node has a pair<K,T> field and thus each node contains a pair of Student and the corresponding key.

APPROACH AND ANALYSIS:
	1.insert() ->Hashvalue is first calculated using the given hash function. Each slot acts as a root of BST full of elements mapped to the slot.
				 The tree is traversed by comparing each node to the first name of the Student to be inserted to find the correct insertion slot.
				 The insertion stops if an element with same fname+lname is found (returning -1) or correct node position for insertion is found 
				 (returning the number of nodes touched). 												 	
				 TIME COMPLEXITY:Best case = O(1)	//If empty BST root is found at index[hashvalue]
				 				 Average case = O(Log(lambda))~O(1) 	//Where lambda is the load factor	
				 				 Worst case = O(n) 	//In case all elements are mapped to the same BST and all the inserted 
				 				 elements are in ascending/ descending order (n is the total no. of elements)

	2.contains() ->Hashvalue is calculated.The tree is then traversed using comparisons based on fname. If a node with same fname+lname is found, 
				   true	is returned else we return false.
				   TIME COMPLEXITY:Best case = O(1)
				   				   Average case = O(log(lambda))~O(1)
				   				   Worst case = O(n)

	3.delete() ->Similar procedure is followed.If the required node is not found, -1 is returned else we return the number of nodes touched.
				 TIME COMPLEXITY:Best case = O(1)
				 				 Average case = O(log(lambda))~O(1)
				 				 Worst case = O(n)			   				   			 				

	4.get() ->Similar procedure.If the required node is not found, NotFoundException is thrown, caught by the main method to print "E" 
	else the stored student is returned.
			  TIME COMPLEXITY:Best case = O(1)
			  				  Average case = O(log(lambda))~O(1)
			  				  Worst case = O(n)

	5.update() ->Similar procedure. Expected position of the node is found using comparisons based on fname. Node is then compared by fname+lname and
				 updated in case a match is found. -1 is returned when the object is not found else the number of nodes touched is returned.
				 TIME COMPLEXITY:Best case = O(1)
				 				 Average case = O(log(lambda))~O(1)
				 				 Worst case = O(n)		  				  			 				  

	6.address() ->Similar Procedure. Each time we go right "R" is concatenated to the answer "L" when we go left. NotFoundException is thrown in
				  case the node is not found else we return the "ans" String.
				  TIME COMPLEXITY:Best case = O(1)
				  				  Average case = O(log(lambda))~O(1)
				  				  Worst case = O(n)

//-----------------------------------------------//

9.public class assignment3
	//Methods:
	public static void main(String[] args) 	//main driver method

IMPLEMENTATION: We first accept the tablesize, Method for conflict resolution and input file name as command line arguments from the user.
The input file is then read line by line and answer to each query is printed in the command line itself.

//------------------------------------------------//

NOTES AND FINDINGS:

1.While resolving the case of same first names, the Students added later are added to the right of the node with the same fname. This has been done in 
accordance with my delete operation which finds the replacement of a node to be deleted as the left-most on the RHS.
ie.. I found that in case of equality, the latter nodes have to be added to the side from which we find this node's replacement while deleting.

2.I ran several of my own cases (and those made by others too) to debug the code. 

3.Hash functions have been assumed to be O(1) and random while doing time complexity analysis for all methods. In other cases, time complexity may change accordingly.				
