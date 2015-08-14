增强for循环：
	foreach语句，foreach简化了迭代器。
	格式：// 增强for循环括号里写两个参数，第一个是声明一个变量，第二个就是需要迭代的容器
	for( 元素类型 变量名 : Collection集合 & 数组 ) {
	
	}
	高级for循环和传统for循环的区别：
		高级for循环在使用时，必须要明确被遍历的目标。这个目标，可以是Collection集合或者数组，如果遍历Collection集合，在遍历过程中还需要对元素进行操作，
			比如删除.
		需要使用迭代器。
	如果遍历数组，还需要对数组元素进行操作，建议用传统for循环因为可以定义角标通过角标操作元素。
	如果只为遍历获取，可以简化成高级for循环，它的出现为了简化书写。
	高级for循环可以遍历map集合吗？不可以。但是可以将map转成set后再使用foreach语句。

	增强for循环:
		迭代变量必须在()中定义！
		迭代变量可以是数组或实现了Iterator接口的集合类。
		for(type 变量名：集合变量名){...}
		for(int arg : args)
			sum += arg;
	
增强for循环：	
	1、作用：对存储对象的容器进行迭代： 数组 collection map.
	2)、增强for循环迭代数组：
		String [] arr = {"a", "b", "c"};//数组的静态定义方式，只试用于数组首次定义的时候
		for(String s : arr) {
		System.out.println(s);
		}
	3)、单列集合 Collection：
		List list = new ArrayList();
		list.add("aaa");
		// 增强for循环, 没有使用泛型的集合能不能使用增强for循环迭代？能
		for(Object obj : list) {
		String s = (String) obj;
		System.out.println(s);
		}
	4)、双列集合 Map：
		Map map = new HashMap();
		map.put("a", "aaa");
		// 传统方式：必须掌握这种方式
		Set entrys = map.entrySet(); // 1.获得所有的键值对Entry对象
		iter = entrys.iterator(); // 2.迭代出所有的
		entry while(iter.hasNext()) {
		Map.Entry entry = (Entry) iter.next();
		String key = (String) entry.getKey(); // 分别获得key和value
		String value = (String) entry.getValue();
		System.out.println(key + "=" + value);
		}
		// 增强for循环迭代：原则上map集合是无法使用增强for循环来迭代的，因为增强for循环只能针对实现了Iterable接口的集合进行迭代；Iterable是jdk5中新定义的接口，就一个方法iterator方法，只有实现了Iterable接口的类，才能保证一定有iterator方法，java有这样的限定是因为增强for循环内部还是用迭代器实现的，而实际上，我们可以通过某种方式来使用增强for循环。
		for(Object obj : map.entrySet()) {
		Map.Entry entry = (Entry) obj; // obj 依次表示Entry
		System.out.println(entry.getKey() + "=" + entry.getValue());
		}
	5、集合迭代注意问题：在迭代集合的过程中，不能对集合进行增删操作（会报并发访问异常）；可以用迭代器的方法进行操作（子类listIterator：有增删的方法）。
	6)、增强for循环注意问题：在使用增强for循环时，不能对元素进行赋值；
		int[] arr = {1,2,3};
		for(int num : arr) {
		num = 0; //不能改变数组的值 }
		System.out.println(arr[1]); //2