package com.test.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javafx.util.Pair;

public class LadderLength {

	
	/*
	 * BFS Example
	 * Time Complexity: O(M×N)
	 * Space Complexity: O(M×N)
	 */
	/*
	 * Intuition - Start from beginWord and search the endWord using BFS.
	 * 	Algorithm
	 * 
	 * 1. Do the pre-processing on the given wordList and find all the possible 
	 * generic/intermediate states. Save these intermediate states in a dictionary
	 *  with key as the intermediate word and value as the list of words
	 *   which have the same intermediate word.
	 * 
	 * 2. Push a tuple containing the beginWord and 1 in a queue. The 1 represents 
	 * the level number of a node. We have to return the level of the endNode 
	 * as that would represent the shortest sequence/distance from the beginWord.
	 * 
	 * 3. To prevent cycles, use a visited dictionary.
	 * 
	 * 4. While the queue has elements, get the front element of the queue. 
	 * Let's call this word as current_word.
	 * 
	 * 5. Find all the generic transformations of the current_word and 
	 * find out if any of these transformations is also a transformation of
	 *  other words in the word list. This is achieved by checking the all_combo_dict.
	 * 
	 * 6. The list of words we get from all_combo_dict are all the words which 
	 * have a common intermediate state with the current_word. 
	 * These new set of words will be the adjacent nodes/words
	 *  to current_word and hence added to the queue.
	 * 
	 * 7. Hence, for each word in this list of intermediate words, 
	 * append (word, level + 1) into the queue where level is the level for the current_word.
	 * 
	 * 8. Eventually if you reach the desired word, its level would represent the 
	 * shortest transformation sequence length.
	 * 
	 */
	public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
		
		// Since all words are of same length.
	    int L = beginWord.length();

	    // Dictionary to hold combination of words that can be formed,
	    // from any given word. By changing one letter at a time.
	    HashMap<String, ArrayList<String>> allComboDict = new HashMap<String, ArrayList<String>>();
		
	 	//builds the adjacency list first before beginning the breadth first search algorithm.
        wordList.forEach(
	            word -> {
	              for (int i = 0; i < L; i++) {
	                // Key is the generic word
	                // Value is a list of words which have the same intermediate generic word.
	                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
	                allComboDict.computeIfAbsent(newWord, k -> new ArrayList<>()).add(word);
	              }
	            });
	    
	    
	    // Queue for BFS - word and it's length
	    Queue<Pair<String, Integer>> Q = new LinkedList<Pair<String, Integer>>();
	    Q.add(new Pair<String, Integer>(beginWord, 1));

	    // Visited to make sure we don't repeat processing same word.
	    HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
	    visited.put(beginWord, true);
	    
	    while (!Q.isEmpty()) {
	    	Pair<String, Integer> node = Q.remove();
	        String word = node.getKey();
	        int level = node.getValue();
	        
	        for (int i = 0; i < L; i++) {

	        	// Intermediate words for current word
	            String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

	         
	         // Next states are all the words which share the same intermediate state.
	            for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<String>())) {
	            	// If at any point if we find what we are looking for
	                // i.e. the end word - we can return with the answer.
	                if (adjacentWord.equals(endWord)) {
	                  return level + 1;
	                }
	                
	                // Otherwise, add it to the BFS Queue. Also mark it visited
	                if (!visited.containsKey(adjacentWord)) {
	                  visited.put(adjacentWord, true);
	                  Q.add(new Pair<String, Integer>(adjacentWord, level + 1));
	                }
	            } 
	        }
	    }
		return 0;
   
	}
	
	public static void main(String[] args) {
		String beginWord = "hit";
		String	endWord = "cog";
		List<String> wordList = new ArrayList<String>();
		wordList.add("hot");
		wordList.add("dot");
		wordList.add("dog");
		wordList.add("lot");
		wordList.add("log");
		wordList.add("cog");
		
		/*
		 * finding the shortest path from a start node to a destination node, if there exists one. 
		 * Breadth First Search approach.
		 */
		System.out.println(ladderLength(beginWord,endWord, wordList));

	}

}
