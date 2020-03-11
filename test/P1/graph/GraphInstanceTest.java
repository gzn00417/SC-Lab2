/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST NOT add constructors, fields, or non-@Test methods
 * to this class, or change the spec of {@link #emptyInstance()}. Your tests
 * MUST only obtain Graph instances by calling emptyInstance(). Your tests MUST
 * NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {

    // Testing strategy
    // TODO

    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
	assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testInitialVerticesEmpty() {
	// TODO you may use, change, or remove this test
	assertEquals("expected new graph to have no vertices", Collections.emptySet(), emptyInstance().vertices());
    }

    // TODO other tests for instance methods of Graph

    @Test
    public void testAdd() {
	Graph<String> emptyInstance = emptyInstance();
	assertEquals(true, emptyInstance.add("1"));
	assertEquals(false, emptyInstance.add("1"));
	assertEquals(true, emptyInstance.add("2"));
	assertEquals(true, emptyInstance.add("3"));
	assertEquals(true, emptyInstance.add("4"));
    }

    @Test
    public void testRemove() {
	Graph<String> emptyInstance = emptyInstance();

	// add
	assertEquals(true, emptyInstance.add("1"));
	assertEquals(false, emptyInstance.add("1"));
	assertEquals(true, emptyInstance.add("2"));
	assertEquals(true, emptyInstance.add("3"));
	assertEquals(true, emptyInstance.add("4"));

	// remove
	assertEquals(true, emptyInstance.remove("4"));
	assertEquals(false, emptyInstance.remove("0"));
    }

    @Test(expected = RuntimeException.class)
    public void testSet1() {
	Graph<String> emptyInstance = emptyInstance();

	// add
	assertEquals(true, emptyInstance.add("1"));
	assertEquals(false, emptyInstance.add("1"));
	assertEquals(true, emptyInstance.add("2"));
	assertEquals(true, emptyInstance.add("3"));
	assertEquals(true, emptyInstance.add("4"));

	// remove
	assertEquals(true, emptyInstance.remove("4"));
	assertEquals(false, emptyInstance.remove("0"));

	// set
	assertEquals(0, emptyInstance.set("1", "2", 1));
	assertEquals(1, emptyInstance.set("1", "2", 2));
	assertEquals(2, emptyInstance.set("1", "2", 1));
	emptyInstance.set("1", "3", -1);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testSet2() {
	Graph<String> emptyInstance = emptyInstance();
	assertEquals(true, emptyInstance.add("1"));
	assertEquals(true, emptyInstance.add("2"));
	assertEquals(true, emptyInstance.add("3"));
	assertEquals(true, emptyInstance.add("4"));
	assertEquals(true, emptyInstance.remove("4"));

	// set
	assertEquals(0, emptyInstance.set("1", "2", 1));
	assertEquals(1, emptyInstance.set("1", "2", 2));
	assertEquals(2, emptyInstance.set("1", "2", 1));
	
	thrown.expect(RuntimeException.class);
	thrown.expectMessage("Negative weight");
	emptyInstance.set("1", "3", -1);
    }

    @Test
    public void test1() {
	Graph<String> emptyInstance = emptyInstance();

	// add
	assertEquals(true, emptyInstance.add("1"));
	assertEquals(false, emptyInstance.add("1"));
	assertEquals(true, emptyInstance.add("2"));
	assertEquals(true, emptyInstance.add("3"));
	assertEquals(true, emptyInstance.add("4"));

	// remove
	assertEquals(true, emptyInstance.remove("4"));
	assertEquals(false, emptyInstance.remove("0"));

	// set
	assertEquals(0, emptyInstance.set("1", "2", 1));
	assertEquals(1, emptyInstance.set("1", "2", 2));
	assertEquals(2, emptyInstance.set("1", "2", 1));
	// assertEquals(0, emptyInstance.set("1", "3", -1));
	// assertEquals(0, emptyInstance.set("2", "3", 0));

	// source
	Map<String, Integer> sources = new HashMap<String, Integer>();
	sources.put("1", 1);
	assertEquals(sources, emptyInstance.sources("2"));
	assertEquals(Collections.emptySet(), emptyInstance.sources("3"));
	assertEquals(Collections.emptySet(), emptyInstance.sources("4"));

	// targets
	Map<String, Integer> targets = new HashMap<String, Integer>();
	targets.put("2", 1);
	assertEquals(targets, emptyInstance.targets("1"));
	assertEquals(Collections.emptySet(), emptyInstance.targets("3"));
	assertEquals(Collections.emptySet(), emptyInstance.targets("5"));

	// vertices
	Set<String> vertices = new HashSet<String>();
	vertices.add("1");
	vertices.add("2");
	vertices.add("3");
	assertEquals(vertices, emptyInstance.vertices());
    }

}
