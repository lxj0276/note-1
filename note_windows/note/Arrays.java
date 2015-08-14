Arrays：
	用于操作数组对象的工具类，里面都是静态方法。
	asList方法：将数组转换成list集合。
		String[] arr = {"abc","kk","qq"}
		List<String> list = Arrays.asList(arr);//将arr数组转成list集合。
	将数组转换成集合，有什么好处呢？用aslist方法，将数组变成集合；
		可以通过list集合中的方法来操作数组中的元素：isEmpty()、contains、indexOf、set；
	注意（数组的局限性）：数组是固定长度，不可以使用集合对象增加或者删除等，会改变数组长度的功能方法。 
		比如add、remove、clear。（会报不支持操作异常UnsupportedOperationException）；
	如果数组中存储的引用数据类型，直接作为集合的元素可以直接用集合方法操作。
	如果数组中存储的是基本数据类型，asList会将数组实体作为集合元素存在。

集合变数组：用的是Collection接口中的方法：toArray();
	如果给toArray传递的指定类型的数据长度小于了集合的size，那么toArray方法，会自定再创建一个该类型的数据，长度为集合的size。
	如果传递的指定的类型的数组的长度大于了集合的size，那么toArray方法，就不会创建新数组，直接使用该数组即可，并将集合中的元素存储到数组中，
		其他为存储元素的位置默认值null。
	所以，在传递指定类型数组时，最好的方式就是指定的长度和size相等的数组。
	将集合变成数组后有什么好处？限定了对集合中的元素进行增删操作，只要获取这些元素即可。