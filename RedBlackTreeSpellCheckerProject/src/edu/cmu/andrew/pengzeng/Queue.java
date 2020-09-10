/*
 * @Author: Peng Zeng
 * @AndrewID: pengzeng
 */
package edu.cmu.andrew.pengzeng;

import java.util.NoSuchElementException;

public class Queue {
    private Object[] queue;
    private int front;
    private int rear;
    private int count;
	
	public Queue(){
		queue = new Object[5];
		front = 0;
		rear = 0;
		count = 0;
	}
	
	/*
	 * Check if the queue is empty or not
	 * @return true if queue is empty.
	 * 
	 * Big Theta(1)
	 */
	public boolean isEmpty() {
		return count == 0;
	}
	
	/*
	 * Check if the queue is full or not
	 * @return true if queue is at current capacity.
	 * 
	 * Big Theta(1)
	 */
	public boolean isFull() {
		return count == queue.length;
	}
	
	/*
	 * @Precondition: Queue is not empty
	 * @Postcondition: returns and removes the front element of queue
	 * 
	 * Big Theta(1)
	 */
	public Object deQueue(){
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		// Store the front element
		Object rmItem = queue[front];
		// Remove the queue front 
		queue[front] = null;
		// Decrease the size of queue
		// If the front reach the end, loop around array
		front = (front + 1) % queue.length;
		count--;
		return rmItem;
	}
	
	/*
	 * Add an object reference to the rear of the queue.
	 * Precondition: Memory is available for doubling queue capacity when full.
	 * Post-condition: queue now contains x in the rear.
	 * @return None
	 * 
	 * Big Theta(n)
	 */
	public void enQueue(Object x) {
		// If x is null, do nothing
		if (x == null) {
			System.out.println("Can not add null!");
			return;
		}
		
		// If the queue is empty, we need to add new one
		if (isEmpty()) {
			front = 0;
			rear = 0;
			queue[rear] = x;
			count++;
		} else if (isFull()) { 
			// If the queue overflows, we need to double the size of queue. 
			// Create newQueue, two times larger than old
			Object[] newQueue = new Object[queue.length * 2];
			// Copy the old element into new element
			for (int i = 0; i < queue.length; i++) {
				newQueue[i] = queue[(front + i) % queue.length];
			}
			// Add new element and make queue point to newQueue
			newQueue[count] = x;
			queue = newQueue;
			rear = count;
			count++;
			front = 0;
		} else {
			// Add the element to the rear
			rear = (rear + 1) % queue.length;
			queue[rear] = x;
			count++;
		}
	}
	
	/*
	 * Method getFront returns the front of the queue without removing it.
	 * Pre-condition: queue not empty
	 * 
	 * Big Theta(1)
	 */
	public Object getFront() {
		return queue[front % queue.length];
	}
	
	/*
	 * The toString method returns a String representation of the current queue contents.
	 * @return Shows the front element, and then shows second, third and so on.
	 * Postcondition: convert the array to strings
	 * Big Theta(n)
	 */
	public String toString(){
		StringBuilder item = new StringBuilder("");
		for (int i = 0; i < count; i++) {
			item.append(queue[i % queue.length].toString() + ',');
		}
		return item.toString();
	}
	
	/*
	 * main is for testing the queue routines
	 */
	public static void main(String[] args) {
		Queue q = new Queue();
		System.out.println("Is the Queue Empty right now?  " + q.isEmpty());
		System.out.println("Input 0 to 9 into Queue");
		for (int i = 0; i < 10; i++) {
			q.enQueue(i);
		}
		System.out.println(q.queue.length);
		System.out.println("Is the Queue Full right now?  " + q.isFull());
		System.out.println("The number in Queue is: " + q.count);
		System.out.println("The Queue is: " + q.toString());
		System.out.println("The front of the Queue is: " + q.getFront());
		System.out.println();
		System.out.println("Then we dequeue the Queue!");
		q.toString();
		for (int i = 0; i < 10; i++) {
			q.deQueue();
		}
		System.out.println("Is the Queue Empty right now?  " + q.isEmpty());

	}

}
