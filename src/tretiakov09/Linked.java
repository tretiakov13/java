package ua.khpi.oop.tretiakov09;

public interface Linked<E> {
	void addLast(E e);
	void addFirst(E e);
	int size();
	E getElementByIndex(int index);
	void removeByIndex(int index);
	void clean();
}
