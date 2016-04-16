package com.xiaogua.better.apache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.junit.Test;

import com.xiaogua.better.bean.Sub_Normal_Bean;
import com.xiaogua.better.datetime.DateTimeCode;

public class TestApacheComparatorUtils {

	@SuppressWarnings("unchecked")
	@Test
	public void testApacheComparatorUtils() throws Exception {
		List<Sub_Normal_Bean> dataList = getSubNormalBeanList();
		ArrayList<Object> sortFields = new ArrayList<Object>();

		Comparator<Object> nullFirstCmp = ComparableComparator.getInstance();
		nullFirstCmp = ComparatorUtils.nullLowComparator(nullFirstCmp);
		sortFields.add(new BeanComparator<String>("subName", nullFirstCmp));
		sortFields.add(new BeanComparator<Integer>("subAge"));
		sortFields.add(new BeanComparator<Date>("subDate", nullFirstCmp));

		ComparatorChain multiSort = new ComparatorChain(sortFields);
		Collections.sort(dataList, multiSort);
		System.out.println(dataList);

	}

	private List<Sub_Normal_Bean> getSubNormalBeanList() throws Exception {
		List<Sub_Normal_Bean> dataList = new ArrayList<Sub_Normal_Bean>();
		dataList.add(new Sub_Normal_Bean());
		dataList.add(new Sub_Normal_Bean("subName2", 1, null));
		dataList.add(new Sub_Normal_Bean("subName2", 1,
				DateTimeCode.getDateFromStr("2015-1-2 3:4:6", DateTimeCode.FULL_DATETIME)));
		dataList.add(new Sub_Normal_Bean("subName1", 3,
				DateTimeCode.getDateFromStr("2015-1-2 3:4:6", DateTimeCode.FULL_DATETIME)));
		dataList.add(new Sub_Normal_Bean("subName1", 3,
				DateTimeCode.getDateFromStr("2015-1-2 3:4:5", DateTimeCode.FULL_DATETIME)));
		dataList.add(new Sub_Normal_Bean("subName2", 1,
				DateTimeCode.getDateFromStr("2015-1-2 3:4:5", DateTimeCode.FULL_DATETIME)));
		return dataList;
	}
}
