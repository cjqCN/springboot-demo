package com.example.demo.util;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class CollectionUtil {



	/**
	 * 判断两个集合是否相同（元素相同、与顺序无关）
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean isEqualCollection(final Collection<?> a, final Collection<?> b) {
		return CollectionUtils.isEqualCollection(a, b);
	}

	/**
	 * 两个集合取并集
	 *
	 * @param a
	 * @param b
	 * @param <O>
	 * @return
	 */
	public static <O> Collection<O> union(final Iterable<? extends O> a, final Iterable<? extends O> b) {
		return CollectionUtils.union(a, b);
	}

	/**
	 * 两个集合取交集
	 *
	 * @param a
	 * @param b
	 * @param <O>
	 * @return
	 */
	public static <O> Collection<O> intersection(final Iterable<? extends O> a, final Iterable<? extends O> b) {
		return CollectionUtils.intersection(a, b);
	}

	/**
	 * 交集的补集
	 *
	 * @param a
	 * @param b
	 * @param <O>
	 * @return
	 */
	public static <O> Collection<O> disjunction(final Iterable<? extends O> a, final Iterable<? extends O> b) {
		return CollectionUtils.disjunction(a, b);
	}

	/**
	 * 两个集合相减
	 *
	 * @param a
	 * @param b
	 * @param <O>
	 * @return
	 */
	public static <O> Collection<O> subtract(final Iterable<? extends O> a, final Iterable<? extends O> b) {
		return CollectionUtils.subtract(a, b);
	}

}

/**
 * Created by jn on 2014/6/15.
 */
class CollectionsDemo {

	public void Test112() {
		//快速 new 一个 list
		List<String> theseElements = Lists.newArrayList("alpha", "beta", "gamma");
	}

	public void Test1() {
		//把某几个元素指定位置排序
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");

		List<String> listNew = new ArrayList<String>();
		for (String stringTemp : list) {
			System.out.println("--------------------------------#" + stringTemp);
			if (stringTemp.equals("3")) {
				listNew.add(0, stringTemp);
			} else if (stringTemp.equals("5")) {
				listNew.add(2, stringTemp);
			} else {
				listNew.add(stringTemp);
			}
		}

		for (String stringTemp2 : listNew) {
			System.out.println("--------------------------------#" + stringTemp2);
		}

	}

	public void Test2() {
		String tempString = "abcdef";
		List<String> list = new ArrayList<String>();
		list.add("abcdef");
		list.add("abcdef113");
		boolean contains = list.contains(tempString);
		System.out.println("--------------------------------" + contains);

	}

	public static void main(String[] args) {

		String[] arrayA = new String[]{"5", "2", "3", "3", "4", "1"};
		String[] arrayB = new String[]{"7", "4", "4", "4", "5", "6", "3"};
		String[] arrayC = new String[]{"4", "7", "4", "4", "6", "5", "3"};

		//把数组转换成List（不过Arrays.asList()方法返回的List不能add对象，因为该方法的实现是使用参数引用的数组的大小来new的一个ArrayList，会报异常：java.lang
		// .UnsupportedOperationException）
		List<String> aList = Arrays.asList(arrayA);
		List<String> bList = Arrays.asList(arrayB);
		List<String> cList = Arrays.asList(arrayC);

		//把一个迭代转换成 list
		Iterator<String> iter = bList.iterator();
		List myList = Lists.newArrayList(iter);//使用 Guava
		List myList2 = IteratorUtils.toList(iter);//使用 Apache common

		//把字符串转换成List
		String input = "apple - banana - orange";
		List<String> result = Splitter.on("-").trimResults().splitToList(input);

		//把字符串转换成Map
		String input33 = "John=first,Adam=second";
		Map<String, String> result33 = Splitter.on(",").withKeyValueSeparator("=").split(input33);


		//把 list 通过逗号隔开（也可以对数组起作用）
		String joinResult = StringUtils.join(aList, ",");//结果是：5,2,3,3,4,1
		//通过guava的方式来：
		String result44 = Joiner.on(",").join(aList);
		String result2 = Joiner.on(",").skipNulls().join(aList);

		//把list转换成数组
		String[] array = aList.toArray(new String[aList.size()]);

		Collections.sort(aList);//对list进行升序排序(底层用的是arrays的sort方法)
		Collections.sort(bList);//对list进行升序排序(底层用的是arrays的sort方法)

		System.out.println("数组A,排序后:" + ArrayUtils.toString(aList.toArray()));
		System.out.println("数组B,排序后:" + ArrayUtils.toString(bList.toArray()));

		System.out.println("--------------------------------");

		boolean equalCollection = CollectionUtils.isEqualCollection(cList, bList);//判断是否相同（顺序不同没关系）
		Collection<String> union = CollectionUtils.union(aList, bList); //并集
		Collection<String> intersection = CollectionUtils.intersection(aList, bList); //交集
		Collection<String> disjunction = CollectionUtils.disjunction(aList, bList); //交集的补集
		Collection<String> subtract = CollectionUtils.subtract(aList, bList); //集合相减
		Collection<String> subtract2 = CollectionUtils.subtract(bList, aList); //集合相减


		boolean containsAny = CollectionUtils.containsAny(aList, bList);//判断两个 list 中只要有一个元素两者共有，则返回 true


		Predicate EQUALS_TWO = new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				return (object.equals("5"));
			}
		};

		//判断是否符合指定条件
		boolean exists = CollectionUtils.exists(aList, EQUALS_TWO);

		Collections.sort((List<String>) union);//对list进行升序排序(底层用的是arrays的sort方法)
		Collections.sort((List<String>) intersection);//对list进行升序排序(底层用的是arrays的sort方法)
		Collections.sort((List<String>) disjunction);//对list进行升序排序(底层用的是arrays的sort方法)
		Collections.sort((List<String>) subtract);//对list进行升序排序(底层用的是arrays的sort方法)
		Collections.sort((List<String>) subtract2);//对list进行升序排序(底层用的是arrays的sort方法)

		System.out.println("并集(A, B): " + ArrayUtils.toString(union.toArray()));
		System.out.println("交集(A, B): " + ArrayUtils.toString(intersection.toArray()));
		System.out.println("交集的补集(A, B)(就是并集去掉交集部分): " + ArrayUtils.toString(disjunction.toArray()));
		System.out.println("集合相减(A, B)前面减后面,留下前面特有的内容: " + ArrayUtils.toString(subtract.toArray()));
		System.out.println("集合相减(B, A)前面减后面,留下前面特有的内容: " + ArrayUtils.toString(subtract2.toArray()));

		System.out.println("--------------------------------");

		System.out.println("判断是否为空:" + CollectionUtils.isEmpty(aList));//包含了size=0
		System.out.println("判断是否非空:" + CollectionUtils.isNotEmpty(bList));
		System.out.println("比较两个集合:" + CollectionUtils.isEqualCollection(aList, bList));//没有顺序差别,只要内容一致就返回true

		System.out.println("--------------------------------");

		String[] arrayD = new String[]{"5", "2", "9"};
		List<String> dList = new ArrayList<String>();
		dList.add("3");
		dList.add("1");
		CollectionUtils.addAll(dList, arrayD);
		Collections.sort(dList);
		System.out.println("集合数据,增加数组中数据: " + ArrayUtils.toString(dList.toArray()));

	}
}
