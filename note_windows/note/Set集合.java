Set : 无绪，不可重复。该集合中无索引。
	|--hashSet：
		底层数据结构是哈希表，线程非同步。
		HashSet是如何保证元素唯一性的呢？是通过元素的两个方法：hashCode和equals来完成。
		判断元素的hashcode值是否相同。如果相同，继续判断元素的equals方法，看其是否相同，如果都相同，则是同一个元素。
		当自定义对象往集合中存的时候，一般都要复写对象的hashCode和equals方法。
		HashSet对于判断元素是否存在，以及删除等操作，依赖的方法是元素的hashCode和equals方法。
		|--LinkedHashSet：有序，hashset的子类。
	|--TreeSet：
		对Set集合中的元素的进行指定顺序的排序。不同步。TreeSet底层的数据结构就是二叉树。
		
		TreeSet方法保证元素唯一性的方式：就是参考比较方法的结果是否为0，如果return 0，视为两个对象重复，不存。
		
		用于对Set集合进行元素的指定顺序排序，排序需要依据元素自身具备的比较性。
			如果元素不具备比较性，在运行时会发生ClassCastException异常。
		所以需要元素实现Comparable接口，强制让元素具备比较性，复写compareTo方法。 依据compareTo方法的返回值，确定元素在TreeSet数据结构中的位置。
		
		TreeSet集合排序有两种方式，Comparable和Comparator区别：
			1：让元素自身具备比较性，需要元素对象实现Comparable接口，覆盖compareTo方法。
			2：让集合自身具备比较性，需要定义一个实现了Comparator接口的比较器，并覆盖compare方法，并将该类对象作为实际参数传递给TreeSet集合的构造函数。

			Set接口取出方式只有一种，迭代器。			
			
Set:元素是无序的（存入和取出的顺序不一定一致），元素不可以重复。
	Set集合的功能和Collection是一致的。