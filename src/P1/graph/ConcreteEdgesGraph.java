/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph implements Graph<String> {

    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();

    // Abstraction function:
    // TODO
    /**
     * use a universe L to represent vertices; use edge class to represent edges
     */

    // Representation invariant:
    // TODO
    /*
     * the vertex must be not null; the weight must be positive
     */

    // Safety from rep exposure:
    // TODO
    // make the rep be private and final ,don't provide public function to modify
    // the value of these fields
    // never return mutable rep, only return the clone data

    // TODO constructor
    ConcreteEdgesGraph() {

    }

    // TODO checkRep
    private void checkRep() {
        for (String vertex : vertices)
            assert (vertex != null);
        for (Edge edge : edges)
            assert (edge != null);
    }

    @Override
    public boolean add(String vertex) {
        if (vertices.contains(vertex))
            return false;
        vertices.add(vertex);
        return true;
    }

    @Override
    public int set(String source, String target, int weight) {
        if (weight < 0)
            throw new Exception("Negative weight");
        // Find the same edge
        for (Edge edge : edges) {
            if (edge.sameEdge(source, target)) {
                int lastEdgeWeight = edge.weight();
                edges.remove(edge);
                Edge instance = new Edge(source, target, weight);
                edges.add(instance);
                checkRep();
                return lastEdgeWeight;
            }
        }
        // weight=0 means delete an edge, so it can't be before FINDING
        if (weight == 0)
            return 0;
        // new positive edge
        Edge instance = new Edge(source, target, weight);
        edges.add(instance);
        checkRep();
        return 0;
    }

    @Override
    public boolean remove(String vertex) {
        if (!vertices.contains(vertex))
            return false;
        edges.removeIf(edge -> edge.source().equals(vertex) || edge.target().equals(vertex));
        vertices.remove(vertex);
        checkRep();
        return true;
    }

    @Override
    public Set<String> vertices() {
        return new HashSet<>(vertices);
    }

    @Override
    public Map<String, Integer> sources(String target) {
        Map<String, Integer> sources = new HashMap<>();
        for (Edge edge : edges) {
            if (target.equals(edge.target())) {
                sources.put(edge.source(), edge.weight());
            }
        }
        checkRep();
        return sources;
    }

    @Override
    public Map<String, Integer> targets(String source) {
        Map<String, Integer> targets = new HashMap<>();
        for (Edge edge : edges) {
            if (source.equals(edge.source())) {
                targets.put(edge.target(), edge.weight());
            }
        }
        checkRep();
        return targets;
    }

    // TODO toString()
    @Override
    public String toString() {
        return "the number of vertices is  " + vertices.size() + " ,the number of edges is " + edges.size();
    }

}

/**
 * TODO specification Immutable. This class is internal to the rep of
 * ConcreteEdgesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Edge<L> {

    // TODO fields
    private L source, target;
    private int weight;

    // Abstraction function:
    // TODO
    /*
     * source: the start vertex of the edge target: the end vertex of the edge
     * weight: the value of the edge
     */

    // Representation invariant:
    // TODO
    /*
     * the weight must be positive the source and target must be different
     */

    // Safety from rep exposure:
    // TODO
    /*
     * make source, target and weight unchangable
     */

    // TODO constructor
    Edge(L source, L target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        checkRep();
    }

    // TODO checkRep
    private void checkRep() {
        assert (weight > 0 || source == target);
    }

    // TODO methods
    public L source() {
        return this.source;
    }

    public L target() {
        return this.target;
    }

    public int weight() {
        return this.weight;
    }

    public boolean sameEdge(L source, L target) {
        return source == this.source && target == this.target;
    }

    // TODO toString()
    @Override
    public String toString() {
        return "Source: " + this.source + "; Target: " + this.target + "; Weight: " + this.weight;
    }
}
