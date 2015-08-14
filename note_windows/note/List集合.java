List 有序，可重复。因为该集合体系有索引。当设计到很多增删时用LinkedList，其他的用ArrayList。
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


List集合特有的方法：凡是可以操作角标的方法都是该体系特有的方法。
	增：add(index,element);
		addAll(index,Collection);
		
	删:remove(index);
	
	改:set(index,element);
	
	查:get(index)；
		subList(from,to);包含from不包含to.
		listIterator();
	List集合特有的迭代器：ListIterator是Iterator的子接口。
	
	在迭代时，不可以通过集合对象的方法操作集合中的元素。因为会发生ConcurrentModificationException异常。
	所以，在迭代时，只能用迭代器方法操作元素，可是Iterator的方法是有限的，只能对元素进行判断，取出，删除操作；如果想要其他的操作如添加，删除等，
		需要使用其子接口：ListIterator。该接口只能通过List集合的ListIterator方法获取。
		
	在进行list列表元素迭代的时候，如果想要在迭代过程中，想要对元素进行操作的时候，比如满足条件添加新元素。会发生.ConcurrentModificationException并发修改异常。
	导致的原因是：
		集合引用和迭代器引用在同时操作元素，通过集合获取到对应的迭代器后，在迭代中，进行集合引用的元素添加，迭代器并不知道，所以会出现异常情况。
	如何解决呢？
		既然是在迭代中对元素进行操作,找迭代器的方法最为合适.可是Iterator中只有hasNext,next,remove方法.通过查阅的它的子接口,ListIterator,发现该列表迭代器接口具备了对元素的增、删、改、查的动作。
		ListIterator是List集合特有的迭代器。ListIterator it = list.listIterator;


	LinkedList的特有方法: 
	/*addFirst();
		addLast();
		
		getFirst();
		getLast();
		获取元素，但不删除元素，如果集合中没有元素，会出现NoSuchElementException
		
		removeFirst();
		removeLast();
		获取元素，但元素被删除，如果集合中没有元素，会出现NoSuchElementException
	*/
	LinkedList的特有方法: (在JDK1.6之后出现了替代方法)：
	offerFirst();
	offerLast();
	
	peekFirst();
	peedLast();
	获取元素，但不删除元素，如果集合中没有元素，会返回null.
	
	pollFirst();
	pollLast();
	获取元素，但元素被删除，如果集合中没有元素，会返回null.
	
	使用LinkedList模拟一个堆栈或者队列的数据结构。
	
	堆栈：先进后出 
	队列：先进先出
	class DuiLie{
		private LinkedList link;
		
		DuiLie(){
			link = new LinkedList();
		}
		
		public void myAdd(Object obj){
			link.addFirst(obj);
		}
		
		public Object myGet(){
			return link.removeLast();
		}
		
		public boolean isNull(){
			return link.isEmpty();
		}
	}
	