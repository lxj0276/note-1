Collection 最常用的：ArrayList、LinkedList、HashSet、HashMap。
	|--List 有序，可重复。因为该集合体系有索引。当设计到很多增删时用LinkedList，其他的用ArrayList。
		|--ArrayList:
			底层的数据结构使用的是数组结构。线程不同步。特点：查询、修改速度很快，但是增删稍慢。
			ArrayList集合判断元素是否相同，依据的是元素的equals方法。list.contains(obj)比较是否包含的时候用的是equals方法。
			remove也都是调用的equals方法。其他集合（例如hashSet）于此不同。判断元素是否相同，数据结构不同，依赖的方法也不同。
		|--LinkedList：
			底层使用的是链表数据结构。特点：增删速度很快，查询慢。
		|--Vector：
			底层是数组数据结构。线程同步。增删查改都很慢。（不用）。
			有用的一个方法：Enumeration en = vector.elements();枚举就是Vector特有的取出方式。
			Vector支持枚举，别的集合方法只有迭代器。
	|--Set  无绪，不可重复。该集合中无索引。
		|--hashSet：
			底层数据结构是哈希表，线程非同步。
			HashSet是如何保证元素唯一性的呢？是通过元素的两个方法：hashCode和equals来完成。
			判断元素的hashcode值是否相同。如果相同，继续判断元素的equals方法，看其是否相同，如果都相同，则是同一个元素。
			当自定义对象往集合中存的时候，一般都要复写对象的hashCode和equals方法。
			HashSet对于判断元素是否存在，以及删除等操作，依赖的方法是元素的hashCode和equals方法。
		|--TreeSet：
			保证元素唯一性的依据：compareTo方法的return 0.如果return的是0则认为相同。当主要元素比较完后一定要比较次要元素，要不容易丢失元素。
			
			可以对Set集合中的元素进行排序。
			TreeSet排序的第一种方式：让元素自身具备比较性。元素需要实现Comparable接口，覆盖CompareTo方法。这种方式也成为元素的自然顺序（默认顺序）。
						 第二种方式：当元素自身不具备比较性，或者具备的比较性不是所需要的，这时就需要让集合自身具备比较性。
								在集合初始化时，就有了比较方式。定义一个比较器，将比较器对象作为参数传递给TreeSet集合的构造函数。
								定义一个比较器，需要实现Comparator接口，覆盖compare方法。
			当两种排序都存在时，以比较器为主。
			
	1，添加：
	add(object)：添加一个元素
	addAll(Collection) ：添加一个集合中的所有元素。
	2，删除：
	clear()：将集合中的元素全删除，清空集合。
	remove(obj) ：删除集合中指定的对象。注意：删除成功，集合的长度会改变。 removeAll(collection) ：删除部分元素。部分元素和传入Collection一致。
	3，判断：
	boolean contains(obj) ：集合中是否包含指定元素 。
	boolean containsAll(Collection) ：集合中是否包含指定的多个元素。 boolean isEmpty()：集合中是否有元素。
	4，获取：
	int size()：集合中有几个元素。
	5，取交集：
	boolean retainAll(Collection) ：对当前集合中保留和指定集合中的相同的元素。如果两个集合元素相同，返回flase；如果retainAll修改了当前集合，返回true。
	6，获取集合中所有元素：
	Iterator iterator()：迭代器
	7，将集合变成数组：
	toArray();
	
集合什么时候用？
	当存储的是一个元素时，就用Collection。当存储对象之间存在着映射关系时，就使用Map集合。
	保证唯一，就用Set。不保证唯一，就用List。
	
使用集合的技巧：
	看到Array就是数组结构，有角标，查询速度很快。
	看到link就是链表结构：
	增删速度快，而且有特有方法。
		offerFirst();
		offerLast();
		
		peekFirst();
		peedLast();
		获取元素，但不删除元素，如果集合中没有元素，会返回null.
		
		pollFirst();
		pollLast();
		获取元素，但元素被删除，如果集合中没有元素，会返回null.
	看到hash就是哈希表，就要想要哈希值，就要想到唯一性，就要想到存入到该结构的中的元素必须覆盖hashCode，equals方法。
	看到tree就是二叉树，就要想到排序，就想要用到比较。
