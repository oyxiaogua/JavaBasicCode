package com.xiaogua.better.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class CollectionCode {
	public static <E> Enumeration<E> asEnumeration(final Iterator<E> iter) {
		return new Enumeration<E>() {
			public boolean hasMoreElements() {
				return iter.hasNext();
			}

			public E nextElement() {
				return iter.next();
			}
		};
	}

	public static <E> Iterator<E> asIterator(final Enumeration<E> e) {
		return new Iterator<E>() {
			public boolean hasNext() {
				return e.hasMoreElements();
			}

			public E next() {
				return e.nextElement();
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	public static <T> Collection<T> asCollection(final Iterator<? extends T> iterator) {
		List<T> list = new ArrayList<>();
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}
		return list;
	}

	public static void sortCnList(List<String> list) {
		Collections.sort(list, new ChineseCompator());
	}

	public static void sortCnArray(String[] arrs) {
		Arrays.sort(arrs, new ChineseCompator());
	}

	public static <E> Enumeration<E> getCollectionEnumeration(Collection<E> coll) {
		return Collections.enumeration(coll);
	}

	public static <T> ArrayList<T> newArrayList(Iterable<T> items) {
		ArrayList<T> list = new ArrayList<>();
		items.forEach(list::add);
		return list;
	}

	public static <T> ArrayList<T> newArrayList(Iterator<T> iterator) {
		ArrayList<T> list = new ArrayList<>();
		iterator.forEachRemaining(list::add);
		return list;
	}

}
