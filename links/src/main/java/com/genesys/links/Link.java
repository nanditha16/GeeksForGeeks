package com.genesys.links;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Link {

	private HashSet<Link> links = new HashSet<Link>();

	public void linkTo(Link link) {
		links.add(link);
	}

	public Boolean isLinkedTo(Link to) {
		return isLinkedBFS(to);
	}

	private Boolean isLinkedBFS(Link to) {
		Link temp = new Link();

		// To keep track of visited nodes
		HashMap<Link, Boolean> visited = new HashMap<Link, Boolean>();
		
		// Queue for BFS
		LinkedList<Link> queue = new LinkedList<Link>();
		
		Link n;

		// mark the initial node as visited and queue
		visited.put(this, true);
		queue.add(this);

		Iterator<Link> i;

		while (queue.size() != 0) {
			//dequeue the marked node and do BFS iteratively
			temp = queue.poll();

			i = temp.links.iterator();

			while (i.hasNext()) {
				n = i.next();
				//return if path is found
				if (n == to) {
					return true;
				}
				//mark as visited and add to queue to iterate
				if (!visited.containsKey(n)) {
					visited.putIfAbsent(n, true);
					queue.add(n);
				}
			}
		}
		return false;
	}
}
