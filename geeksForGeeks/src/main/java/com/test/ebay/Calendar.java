package com.test.ebay;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Calendar {
	TreeMap<Integer, Integer> delta;

    Calendar() {
    	delta = new TreeMap();
    }

	/* 
	 * Implement a Calendar class to store your events.
	 * Input :
	 * ["MyCalendarTwo","book","book","book","book","book","book"]
	 * [[],[10,20],[50,60],[10,40],[5,15],[5,10],[25,55]]
	 * 
	 * 
	 * Output:
	 * [null,true,true,true,false,true,true]
	 * 
	 */
    public boolean book(int start, int end) {
    	delta.put(start, delta.getOrDefault(start, 0) + 1);
        delta.put(end, delta.getOrDefault(end, 0) - 1);

        int active = 0;
        for (int d: delta.values()) {
            active += d;
            if (active >= 3) {
                delta.put(start, delta.get(start) - 1);
                delta.put(end, delta.get(end) + 1);
                if (delta.get(start) == 0)
                    delta.remove(start);
                return false;
            }
        }
        return true;
    }
}
