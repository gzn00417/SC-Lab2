package P2;

import static org.junit.Assert.*;
import org.junit.Test;

public abstract class FriendshipGraphTest {

    public abstract FriendshipGraph emptyInstance();

    /**
     * Basic Network Test
     */
    @Test
    public void GraphTest1() {
	FriendshipGraph graph = emptyInstance();

	Person rachel = new Person("Rachel");
	Person ross = new Person("Ross");
	Person ben = new Person("Ben");
	Person kramer = new Person("Kramer");

	assertEquals(true, graph.addVertex(rachel));
	assertEquals(true, graph.addVertex(ross));
	assertEquals(true, graph.addVertex(ben));
	assertEquals(true, graph.addVertex(kramer));

	assertEquals(0, graph.addEdge(rachel, ross));
	assertEquals(0, graph.addEdge(ross, rachel));
	assertEquals(0, graph.addEdge(ross, ben));
	assertEquals(0, graph.addEdge(ben, ross));

	assertEquals(1, graph.getDistance(rachel, ross));
	assertEquals(2, graph.getDistance(rachel, ben));
	assertEquals(0, graph.getDistance(rachel, rachel));
	assertEquals(-1, graph.getDistance(rachel, kramer));
    }

    /**
     * Further Test
     */
    @Test
    public void GrpahTest2() {
	FriendshipGraph graph = emptyInstance();

	Person a = new Person("A");
	Person b = new Person("B");
	Person c = new Person("C");
	Person d = new Person("D");
	Person e = new Person("E");
	Person f = new Person("F");
	Person g = new Person("G");
	Person h = new Person("H");
	Person i = new Person("I");
	Person j = new Person("J");

	assertEquals(true, graph.addVertex(a));
	assertEquals(true, graph.addVertex(b));
	assertEquals(true, graph.addVertex(c));
	assertEquals(true, graph.addVertex(d));
	assertEquals(true, graph.addVertex(e));
	assertEquals(true, graph.addVertex(f));
	assertEquals(true, graph.addVertex(g));
	assertEquals(true, graph.addVertex(h));
	assertEquals(true, graph.addVertex(i));
	assertEquals(true, graph.addVertex(j));

	assertEquals(0, graph.addEdge(a, b));
	assertEquals(0, graph.addEdge(a, d));
	assertEquals(0, graph.addEdge(b, d));
	assertEquals(0, graph.addEdge(c, d));
	assertEquals(0, graph.addEdge(d, e));
	assertEquals(0, graph.addEdge(c, f));
	assertEquals(0, graph.addEdge(e, g));
	assertEquals(0, graph.addEdge(f, g));
	assertEquals(0, graph.addEdge(h, i));
	assertEquals(0, graph.addEdge(i, j));

	assertEquals(2, graph.getDistance(a, e));
	assertEquals(1, graph.getDistance(a, d));
	assertEquals(3, graph.getDistance(a, g));
	assertEquals(3, graph.getDistance(b, f));
	assertEquals(2, graph.getDistance(d, f));
	assertEquals(2, graph.getDistance(h, j));
	assertEquals(0, graph.getDistance(i, i));
	assertEquals(-1, graph.getDistance(d, j));
	assertEquals(-1, graph.getDistance(c, i));
	assertEquals(-1, graph.getDistance(f, h));
    }

}
