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
public class ConcreteVerticesGraph<L> implements Graph<L> {

	private final List<Vertex<L>> vertices = new ArrayList<>();

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
		for (Vertex<L> vertex : vertices) {
			assert (vertex.ThisVertex() != null);
			Map<L, Integer> sources = vertex.sources();
			for (Map.Entry<L, Integer> entry : sources.entrySet()) {
				assert (entry.getKey() != null);
				assert (entry.getValue() > 0);
			}
			Map<L, Integer> targets = vertex.targets();
			for (Map.Entry<L, Integer> entry : targets.entrySet()) {
				assert (entry.getKey() != null);
				assert (entry.getValue() > 0);
			}
		}
	}

	@Override
	public boolean add(L vertex) {
		for (Vertex<L> V : vertices) {
			if (vertex.equals(V.ThisVertex()))
				return false;
		}
		Vertex<L> newVertex = new Vertex<L>(vertex);
		vertices.add(newVertex);
		checkRep();
		return true;
	}

	@Override
	public int set(L source, L target, int weight) {
		if (weight < 0)
			throw new RuntimeException("Negative weight");
		if (source.equals(target))
			return 0;
		Vertex<L> from = null, to = null;
		for (Vertex<L> vertex : vertices) {
			if (vertex.ThisVertex().equals(source))
				from = vertex;
			if (vertex.ThisVertex().equals(target))
				to = vertex;
		}
		if (from == null || to == null)
			throw new NullPointerException("Inexistent vertex");
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
	public boolean remove(L vertex) {
		for (Vertex<L> THIS : vertices) {
			if (THIS.ThisVertex().equals(vertex)) {
				for (Vertex<L> v : vertices) {
					if (THIS.sources().containsKey(v)) {
						// THIS.removeInEdge(v);
						v.removeOutEdge(THIS.ThisVertex());
					}
					if (THIS.targets().containsKey(v)) {
						// THIS.removeOutEdge(v);
						v.removeInEdge(THIS.ThisVertex());
					}
				}
				vertices.remove(THIS);
				checkRep();
				return true;
			}
		}
		checkRep();
		return false;
	}

	@Override
	public Set<L> vertices() {
		Set<L> VERTICES = new HashSet<>();
		for (Vertex<L> vertex : vertices)
			VERTICES.add((L) vertex.ThisVertex());
		checkRep();
		return VERTICES;
	}

	@Override
	public Map<L, Integer> sources(L target) {
		Map<L, Integer> sources = new HashMap<>();
		for (Vertex<L> vertex : vertices) {
			if (vertex.ThisVertex().equals(target)) {
				sources.putAll(vertex.sources());
				break;
			}
		}
		checkRep();
		return sources;
	}

	@Override
	public Map<L, Integer> targets(L source) {
		Map<L, Integer> targets = new HashMap<>();
		for (Vertex<L> vertex : vertices) {
			if (vertex.ThisVertex().equals(source)) {
				targets.putAll(vertex.targets());
				break;
			}
		}
		checkRep();
		return targets;
	}

	// TODO toString()
	public String toString() {
		return "This graph has " + vertices.size() + " vertices";
	}
}

/**
 * TODO specification Mutable. This class is internal to the rep of
 * ConcreteVerticesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Vertex<L> {

	// TODO fields
	private L ThisVertex;
	private Map<L, Integer> inEdges = new HashMap<>();
	private Map<L, Integer> outEdges = new HashMap<>();

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
	Vertex(L label) {
		ThisVertex = label;
	}

	// TODO checkRep
	private void checkRep() {
		for (L key : inEdges.keySet())
			assert (inEdges.get(key) > 0);
		for (L key : outEdges.keySet())
			assert (outEdges.get(key) > 0);
	}

	// TODO methods
	public L ThisVertex() {
		return ThisVertex;
	}

	public Map<L, Integer> sources() {
		Map<L, Integer> sources = new HashMap<>();
		sources.putAll(inEdges); // 深拷贝
		return sources;
	}

	public Map<L, Integer> targets() {
		Map<L, Integer> targets = new HashMap<>();
		targets.putAll(outEdges); // 深拷贝
		return targets;
	}

	public int setInEdge(L source, int weight) {
		for (L key : inEdges.keySet()) {
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

	public int setOutEdge(L target, int weight) {
		for (L key : outEdges.keySet()) {
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

	public int removeInEdge(L source) {
		if (!inEdges.containsKey(source)) {
			return 0;
		}
		int lastEdgeWeight = inEdges.get(source);
		inEdges.remove(source);
		checkRep();
		return lastEdgeWeight;
	}

	public int removeOutEdge(L target) {
		if (!outEdges.containsKey(target)) {
			return 0;
		}
		int lastEdgeWeight = outEdges.get(target);
		outEdges.remove(target);
		checkRep();
		return lastEdgeWeight;
	}

	// TODO toL()
	@Override
	public String toString() {
		return ThisVertex.toString();
	}

}
