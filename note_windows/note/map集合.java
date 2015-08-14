Map：
		|--Hashtable：底层是哈希表数据结构，不可以存入null键和null值。该集合是线程同步的。效率低。用作键的对象必须实现hashCode方法和equals方法。
		|--HashMap：底层是哈希表数据结构，允许使用null键和null值。该集合是线程不同步。效率高。
			（可以通过get方法返回值来判断一个键是否存在，可以通过返回null来判断）
		|--TreeMap：底层是二叉树数据结构。可以用于给Map集合中的键进行排序。线程不同步。

Map集合存储和Collection有着很大不同：
	Collection一次存一个元素；Map一次存一对元素。 Collection是单列集合；Map是双列集合。
	Map中的存储的一对元素：一个是键，一个是值，键与值之间有对应(映射)关系。
	特点：要保证map集合中键的唯一性。
		
Map	:该集合只能存储键值对，一对对往里边存，而且每个键最多只能映射到一个值。

如何想要获取map中的所有元素：
	原理：map中是没有迭代器的，collection具备迭代器，只要将map集合转成Set集合，可以使用迭代器了。之所以转成set，是因为map集合具备着键的唯一性，
		其实set集合就来自于map，set集合底层其实用的就是map的方法。
		
取出map集合中所有元素:
	方式一:
		Set keySet = map.keySet();
		for(Iterator it = keySet.iterator();it.hasNext()) {
		Object key = it.next();
		Object value = map.get(key);
		System.out.println(key+":"+value);
		}
	方式二：entrySet()方法。
		Set entrySet = map.entrySet();
		for(Iterator it = entrySet.iterator();it.hasNext()) {
		Map.Entry me = (Map.Entry)it.next();
		System.out.println(me.getKey()+"::::"+me.getValue());
		}
	1、添加：
		put(K key,V value)：返回这个键对应的原来的值。如果第一次存，返回来null，如果第二次在相同的键存，则覆盖原先的值，并且返回原先的值。
		putAll(Map<? extends K, ?extends V> m)
	2、删除：
		clear();
		remove(Object key)
	3、判断：
		containsValue(Object value)
		containsKey(Object key)
		isEmpty()
	4、获取：
		get(Object key)
		size()
		values()
		entrySet()
		keySet()

	Map和Set很像。Set底层就是用Map实现的。 
	
	当数据之间存在映射关系时，就先想使用Map集合。
	
	Map集合的两种取出方式：
		1、keySet:返回类型：Set<k>。将map中所有的键存入到Set集合。因为set具备迭代器。所以可以用迭代方式取出所有的键，再根据get方法，获取每一个键对应的值。
			
			//keySet取出Map中的键值。
			Set<String> keySet = map.keySet();
			Iterator<String> it = keySet.iterator();
			/*
			for(Iterator<String> it = keySet.iterator();;it.hasNext();)
			{
				String key = it.next();
				String value = map.get(key);
				System.out.println(key:" + key + ",value:" + str);
			}
			*/
			while(it.hasNext()){
				String key = it.next();
				String value = map.get(key);
				System.out.println(key:" + key + ",value:" + str);
			}
		2、entrySet：返回类型：Set<Map.Entry<k,v>>。返回映射中包含映射关系的Set试图，这个关系就是:Map.Entry.
			Map.Entry: 其实Entry也是一个接口，它是Map接口中的一个内部接口。因为先有Map集合才有的Map关系，所以把Entry设计成Map接口的一个内部接口。
				interface Map{
					public static interface Entry{
						public abstract Object getKey();
						public abstract Object getValue();
					}
				}
		
			//entrySet取出Map中的键值
			Set<Map.Entry<String, String>> entryMap = map.entrySet();
			Iterator<Map.Entry<String, String>> it2 = entryMap.iterator();
			while(it2.hasNext()){
				Map.Entry<String, String> me = it2.next();
				System.out.println("key:" + me.getKey() + ",value:" + me.getValue());
				
			}
	
	Map扩展知识。
		Map集合被使用是因为具备映射关系。