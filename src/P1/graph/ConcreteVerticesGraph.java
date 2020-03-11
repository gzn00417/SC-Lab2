/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph implements Graph<String> {

    private final List<Vertex> vertices = new ArrayList<>();

    // Abstraction function:
    // TODO
    // the List vertices represents the nodes in the graph
    // the class Vertex contains edges to or from ThisVertex

    // Representation invariant:
    // TODO
    // the weight must be non-negative
    // the total number of TO edges must be the same with the FROM edges
    // ThisVertex of the vertex can't be null

    // Safety from rep exposure:
    // TODO
    // the vertex class can't be gotten by outside
    // make the vertices be private and final and immutable

    // TODO constructor
    ConcreteVerticesGraph() {

    }

    // TODO checkRep
    private void checkRep() {
        for (Vertex vertex : vertices) {
            assert (vertex.ThisVertex() != null);
            for (Map.Entry<String, Integer> entry : vertex.sources().entrySet()) {
                assert (entry.getKey() != null);
                assert (entry.getValue() > 0);
            }
            for (Map.Entry<String, Integer> entry : vertex.targets().entrySet()) {
                assert (entry.getKey() != null);
                assert (entry.getValue() > 0);
            }
        }
    }

    @Override
    public boolean add(String vertex) {
        for (Vertex V : vertices) {
            if (vertex.equals(V.ThisVertex()))
                return false;
        }
        Vertex newVertex = new Vertex(vertex);
        vertices.add(newVertex);
        checkRep();
        return true;
    }

    @Override
    public int set(String source, String target, int weight) {
        if (weight < 0)
            throw new Exception("Negative weight");
        Vertex from, to;
        for (Vertex vertex : vertices) {
            if (vertex.equals(source))
                from = vertex;
            if (vertex.equals(target))
                to = vertex;
        }
        int lastEdgeWeight;
        if (weight > 0) {
            lastEdgeWeight = from.setOutEdge(target, weight);
            lastEdgeWeight = to.setInEdge(source, weight);
        } else {
            lastEdgeWeight = from.removeOutEdge(target);
            lastEdgeWeight = to.removeInEdge(source);
        }
        checkRep();
        return lastEdgeWeight;
    }

    @Override
    public boolean remove(String vertex) {
        for (Vertex THIS : vertices) {
            if (THIS.ThisVertex().equals(vertex)) {
                for (Vertex v : vertices) {
                    if (THIS.sources().containsKey(v)) {
                        // THIS.removeInEdge(v);
                        v.removeOutEdge(THIS);
                    }
                    if (THIS.targets().containsKey(v)) {
                        // THIS.removeOutEdge(v);
                        v.removeInEdge(THIS);
                    }
                }
                checkRep();
                return true;
            }
        }
        checkRep();
        return false;
    }

    @Override
    public Set<String> vertices() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public Map<String, Integer> sources(String target) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public Map<String, Integer> targets(String source) {
        throw new RuntimeException("not implemented");
    }

    // TODO toString()

}

/**
 * TODO specification Mutable. This class is internal to the rep of
 * ConcreteVerticesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Vertex<String> {

    // TODO fields
    private String ThisVertex;
    private Map<String, Integer> inEdges = new HashMap<>();
    private Map<String, Integer> outEdges = new HashMap<>();

    // Abstraction function:
    // TODO
    // ThisVertex represents itself;
    // inEdges contains Edges that directs it;
    // outEdges contains Edges that it directs others;

    // Representation invariant:
    // TODO
    // ThisVertex MUST be not null;
    // Weights of inEdges and outEdges MUST be positive;

    // Safety from rep exposure:
    // TODO
    // Edges CAN'T be modified from the outside

    // TODO constructor
    Vertex(String label) {
        this.ThisVertex = label;
    }

    // TODO checkRep
    private void checkRep() {
        for (String key : inEdges.keySet())
            assert (inEdges.get(key) > 0);
        for (String key : outEdges.keySet())
            assert (outEdges.get(key) > 0);
    }

    // TODO methods
    public String ThisVertex() {
        return ThisVertex;
    }

    public Map<String, Integer> sources() {
        Map<String, Integer> sources = new HashMap<>();
        sources.putAll(inEdges); // 深拷贝
        return sources;
    }

    public Map<String, Integer> targets() {
        Map<String, Integer> targets = new HashMap<>();
        targets.putAll(outEdges); // 深拷贝
        return targets;
    }

    public int setInEdge(String source, int weight) {
        for (String key : inEdges.keySet()) {
            if (key.equals(source)) {
                int lastEdgeWeight = inEdges.get(key);
                inEdges.remove(key);
                inEdges.put(source, weight);
                return lastEdgeWeight;
            }
        }
        inEdges.put(source, weight);
        checkRep();
        return 0;
    }

    public int setOutEdge(String target, int weight) {
        for (String key : outEdges.keySet()) {
            if (key.equals(target)) {
                int lastEdgeWeight = outEdges.get(key);
                outEdges.remove(key);
                outEdges.put(target, weight);
                return lastEdgeWeight;
            }
        }
        outEdges.put(target, weight);
        checkRep();
        return 0;
    }

    public int removeInEdge(String source) {
        if (!inEdges.containsKey(source)) {
            return 0;
        }
        int lastEdgeWeight = inEdges.get(source);
        inEdges.remove(source);
        checkRep();
        return lastEdgeWeight;
    }

    public int removeOutEdge(String target) {
        if (!outEdges.containsKey(target)) {
            return 0;
        }
        int lastEdgeWeight = outEdges.get(target);
        outEdges.remove(target);
        checkRep();
        return lastEdgeWeight;
    }

    // TODO toString()
    @Override
    public String toString() {
        return this.ThisVertex;
    }

}
