/**
 * Copyright 2013 Marin Solutions
 */
package com.captaindebug.store.monitoring;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * A queue implementation where items are only in the queue for a certain amount of time...
 * 
 * @author Roger
 * 
 */
public class TimedList<T extends Delayed> implements List<T> {

	private final List<T> list = new CopyOnWriteArrayList<T>();

	private final DelayQueue<T> currentItems;

	public TimedList() {

		currentItems = new DelayQueue<T>();

	}

	/**
	 * @see java.util.List#size()
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * @see java.util.List#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * @see java.util.List#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	/**
	 * @see java.util.List#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

	/**
	 * @see java.util.List#toArray()
	 */
	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	/**
	 * @see java.util.List#toArray(T[])
	 */
	@Override
	public <P> P[] toArray(P[] a) {
		return list.toArray(a);
	}

	/**
	 * @see java.util.List#add(java.lang.Object)
	 */
	@Override
	public boolean add(T e) {
		currentItems.put(e);
		return list.add(e);
	}

	/**
	 * @see java.util.List#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		currentItems.remove(o);
		return list.remove(o);
	}

	/**
	 * @see java.util.List#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	/**
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends T> c) {
		currentItems.addAll(c);
		return list.addAll(c);
	}

	/**
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		currentItems.addAll(c);
		return list.addAll(index, c);
	}

	/**
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		currentItems.removeAll(c);
		return list.removeAll(c);
	}

	/**
	 * @see java.util.List#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		currentItems.retainAll(c);
		return list.retainAll(c);
	}

	/**
	 * @see java.util.List#clear()
	 */
	@Override
	public void clear() {
		currentItems.clear();
		list.clear();
	}

	/**
	 * @see java.util.List#get(int)
	 */
	@Override
	public T get(int index) {
		return list.get(index);
	}

	/**
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	@Override
	public T set(int index, T element) {
		currentItems.put(element);
		return list.set(index, element);
	}

	/**
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, T element) {
		currentItems.put(element);
		list.add(index, element);
	}

	/**
	 * @see java.util.List#remove(int)
	 */
	@Override
	public T remove(int index) {
		T element = list.get(index);
		currentItems.remove(element);
		return list.remove(index);
	}

	/**
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	/**
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	/**
	 * @see java.util.List#listIterator()
	 */
	@Override
	public ListIterator<T> listIterator() {
		return list.listIterator();
	}

	/**
	 * @see java.util.List#listIterator(int)
	 */
	@Override
	public ListIterator<T> listIterator(int index) {
		return list.listIterator(index);
	}

	/**
	 * @see java.util.List#subList(int, int)
	 */
	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}

}
