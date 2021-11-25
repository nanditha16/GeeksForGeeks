package com.test.practice;

import java.util.Stack;

public class MyQueue {

	private Stack<Integer> one;
    private Stack<Integer> two;
    
    public MyQueue() {
        this.one = new Stack<>();
        this.two = new Stack<>();
    }
    
    public void push(int x) {
        this.one.push(x);
    }
    
    public int pop() {
        if(this.two.isEmpty()) {
            int size = this.one.size();
            for(int i = 0; i < size; i++) {
                this.two.push(this.one.pop());
            }
            
            return this.two.pop();
        }
        else {
           return this.two.pop(); 
        }
    }
    
    public int peek() {
        if(this.two.isEmpty()) {
            if(one.isEmpty()) {
                return -1;
            }
            int size = this.one.size();
            for(int i = 0; i < size; i++) {
                this.two.push(this.one.pop());
            }
            
            return this.two.peek();
        }
        else {
            return this.two.peek();
        }
    }
    
    public boolean empty() {
        return this.two.isEmpty() && this.one.isEmpty();
    }
    
	public static void main(String[] args) {
		MyQueue obj = new MyQueue();
		obj.push(1);
		obj.push(2);

		System.out.println(obj.pop());
		System.out.println(obj.peek());
		System.out.println(obj.empty());

	}

}
