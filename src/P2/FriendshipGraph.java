package P2;

import P1.graph.*;

import java.util.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class FriendshipGraph {
    private Graph<Person> graph = new Graph();

    /**
     * @method addVertex add new vertex if the person's name is unduplicated
     * @param newPerson adding person
     * @param head      head of Linked List of persons
     * @param NameSet   set of persons' names, used for removing duplication
     */

    private Node head = null;
    private HashSet<String> NameSet = new HashSet<>();

    public void addVertex(Person newPerson) {
	if (NameSet.contains(newPerson.Name)) {
	    System.out.println("Person " + newPerson.Name + " already existed.");
	    System.exit(0);
	}
	NameSet.add(newPerson.Name);
	Node NewVertex = new Node();
	newPerson.node = NewVertex;
	NewVertex.LoadData(newPerson);
	if (head == null)
	    head = NewVertex;
	else
	    head.addNode(NewVertex);
	return;
    }

    /**
     * @method addEdge add edges of double directions
     * @param a,b 2 persons being linking with an edge
     * @param A,B 2 nodes of the 2 persons
     */

    public void addEdge(Person a, Person b) {
	if (a == b) {
	    System.out.println("They are the same one.");
	    System.exit(0);
	}
	Node A = a.node, B = b.node;
	A.addNodeEdge(B);
	B.addNodeEdge(A);
	return;
    }

    /**
     * @method getDistance
     * @param sta path starting person
     * @param end path ending person
     * @return distance between 2 persons or -1 when unlinked
     */

    public int getDistance(Person sta, Person end) {
	if (sta == end)
	    return 0;
	Queue<Person> qu = new LinkedList<Person>();
	for (Node p = head; p != null; p = p.next) {
	    p.vis = false;
	    p.dis = 0;
	}
	sta.node.vis = true;
	for (qu.offer(sta); !qu.isEmpty();) {
	    Person p = qu.poll();
	    for (Node.Edge e = p.node.lastEdge; e != null; e = e.nextEdge) {
		if (!e.terminal.vis) {
		    qu.offer(e.terminal.person);
		    e.terminal.vis = true;
		    e.terminal.dis = p.node.dis + 1;
		    if (e.terminal.person == end)
			return end.node.dis;
		}
	    }
	}
	return -1;
    }
}