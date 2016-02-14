package com.xiaogua.better.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListCode {
	public static <T> List<T> convertListListWithFlatMap(List<List<T>> collection) {
		return collection.stream().flatMap(value -> value.stream()).collect(Collectors.<T>toList());
	}

	public static <T> List<T> convertListValueWithReduce(List<List<T>> collection) {
		return collection.stream().reduce(new ArrayList<T>(), (list, x) -> {
			list.addAll(x);
			return list;
		}, (list1, list2) -> {
			list1.addAll(list2);
			return list1;
		});
	}
}
