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

	graph.addVertex(rachel);
	graph.addVertex(ross);
	graph.addVertex(ben);
	graph.addVertex(kramer);

	graph.addEdge(rachel, ross);
	graph.addEdge(ross, rachel);
	graph.addEdge(ross, ben);
	graph.addEdge(ben, ross);

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

	graph.addVertex(a);
	graph.addVertex(b);
	graph.addVertex(c);
	graph.addVertex(d);
	graph.addVertex(e);
	graph.addVertex(f);
	graph.addVertex(g);
	graph.addVertex(h);
	graph.addVertex(i);
	graph.addVertex(j);

	graph.addEdge(a, b);
	graph.addEdge(a, d);
	graph.addEdge(b, d);
	graph.addEdge(c, d);
	graph.addEdge(d, e);
	graph.addEdge(c, f);
	graph.addEdge(e, g);
	graph.addEdge(f, g);
	graph.addEdge(h, i);
	graph.addEdge(i, j);

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
