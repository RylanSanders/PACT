package gui;

import java.util.ArrayList;

public class LimitedStack<T> {
	private int size;
	private ArrayList<T> elements;

	public LimitedStack(int size) {
		this.size = size;
		elements = new ArrayList<>();
	}

	public void put(T element) {
		if (elements.size() >= size)
			elements.remove(0);
		elements.add(element);
	}
	
	public T pop() throws ArrayIndexOutOfBoundsException{
		if(elements.size()==0)
			throw new ArrayIndexOutOfBoundsException();
		T e = elements.get(elements.size()-1);
		elements.remove(elements.size()-1);
		return e;
	}
	
	public T peek() {
		return elements.get(elements.size()-1);
	}
	
	@Override
	public String toString() {
		return elements.toString();
	}
}
