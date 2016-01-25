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
}
