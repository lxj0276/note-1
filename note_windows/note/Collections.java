Collections：它的出现给集合操作提供了更多的功能。这个类不需要创建对象，内部提供的都是静态方法。
静态方法：
	Collections.sort(list);//list集合进行元素的自然顺序排序。
	Collections.sort(list,new ComparatorByLen());///按指定的比较器方法排序。
	class ComparatorByLen implements Comparator<String>{
	public int compare(String s1,String s2){
	int temp = s1.length()-s2.length();
	return temp==0?s1.compareTo(s2):temp;
	}
	}
	Collections.max(list); //返回list中字典顺序最大的元素。
	int index = Collections.binarySearch(list,"zz");//二分查找，返回角标。
	static void reverse(List<?> list)  
	static <T> Comparator<T> reverseOrder();//逆向反转排序。
		Arrays.sort(a, Collections.reverseOrder());
	Collections.shuffle(list);//随机对list中的元素进行位置的置换。
将非同步集合转成同步集合的方法：
	Collections中的 XXX synchronizedXXX(XXX);
	static <T> Collection<T> synchronizedCollection(Collection<T> c)  
		List synchronizedList(list);
		Map synchronizedMap(map);
	原理：定义一个类，将集合所有的方法加同一把锁后返回。
Collection 和 Collections的区别：
	Collections是个java.util下的类，是针对集合类的一个工具类,提供一系列静态方法,
		实现对集合的查找、排序、替换、线程安全化（将非同步的集合转换成同步的）等操作。
	Collection是个java.util下的接口，它是各种集合结构的父接口，继承于它的接口主要有Set和List,提供了关于集合的一些操作,
		如插入、删除、判断一个元素是否其成员、遍历等。