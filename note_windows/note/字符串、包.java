package ：
	对类文件进行分类管理。
	给类提供多层命名空间。
	写在程序文件的第一行。
	类名的全称是：包名.类名。
	包也是一种封装形式。

java的升级有三方面：提高效率，提高安全，简化书写。
	
java但凡取从头到尾的时候，一般都是包含头不包含尾。

字符串：
	String s1 = "abc"; //s1是一个类类型变量，“abc”是一个对象。
	字符串的最大特点：一旦被初始化就不可以被改变。
	
	String s1 = "abc"; 与 String s2 = new String("abc");的区别：
		s1在内存中有一个对象。
		s2在内存中有两个对象。
		
	常见的操作方法有哪些：
		1、获取：(当访问到字符串中不存在的角标时，会发生StringIndexOutOfBoundsException)
				 (如果没有找到，返回-1)
			1 · int length();字符串串的长度。
			2 · char charAt(int index);根据位置获取位置上某个字符.
			3 · int indexOf(int ch);Returns the index within this string of the first occurrence of the specified character.
			  · int indexOf(int ch,int fromIndex);Returns the index within this string of the first occurrence of the specified character, starting the search at the specified index.
			  · int indexOf(String str);Returns the index within this string of the first occurrence of the specified substring.
			  · int indexOf(String str,int fromIndex);Returns the index within this string of the first occurrence of the specified substring, starting at the specified index.
			  
			  · int lastIndexOf(int ch);Returns the index within this string of the last occurrence of the specified character.
			  · int lastIndexOf(int ch,int fromIndex);Returns the index within this string of the last occurrence of the specified character, searching backward starting at the specified index.
			  · int lastIndexOf(String str);Returns the index within this string of the last occurrence of the specified substring.	
			  · int lastIndexOf(String str,int fromIndex);Returns the index within this string of the last occurrence of the specified substring, searching backward starting at the specified index.
		2、判断：
			1  ·boolean contains(str)//与indexOf的不同是：indexOf即判断有没有，有的话还返回位置，美欧返回O。
			2  ·boolean isEmpty()
			3  ·boolean startsWith(str)
			4  ·boolean endsWith(str)
			5  ·boolean equals(obj)
			6  ·boolean equalsIgnoreCase(str)
		3、转换：
			1  ·将字符数组转成字符串：
					构造函数：
						String(char[])
						String(char[] offset,count)
					静态方法
						static String copyValueof(char[] ch) 
						static String copyValueof(char[] ch,int offset,int count) 
						static String valueOf(char[])
			2  ·将字符串转成字符数组：
					char[] toCharArray();
			3  ·将字节数组转成字符串：
					String(byte[])
					String(byte[] offset,count)
			4  ·将字符串转成字节数组：
					byte[] getBytes();
			5  ·将基本数据类型转成字符串：
					static String valueOf(int)
					static String valueOf(double)
		4、替换：
			String replace(oldchar,newchar)//如果替换的字符不存在，返回的还是原字符串。
			String replace(CharSequence target,CharSequence replacement)
		5、切割：
			String[] split(regex)
		6、子串，获取字符串中的一部分：
			String substring(begin);从指定位置到结尾。如果角标不存在在 ，会出现字符串角标越界异常。
			String substring(begin,end);包含头，不包含尾
		7、转换，去除空格，比较：
			String toUpperCase();
			String toLowerCase();
			
			String trim();//去除字符串两边的空格
			
			int compareTo(str);//俺自然顺序比较

StringBuffer：是字符串缓冲区，是一个容器。容器的功能：c(creat)r(read)u(update)d(delete)
	特点：
		·长度可以变化
		·并且可以操作多个数据类型。
		·最终会通过toString方法变成字符串。
	
	易错讲解：（只要不重新new 一个StringBuffer，则StringBuffer还是同一个）
		StringBuffer ab = new StringBuffer();
		ab.append("abc").append(true).append(31);
		StringBuffer sb1 = ab,append("edf");
		System.out.println(sb == ab1);//打印为true.
		
	
	1、存储：
		StringBuffer append(数据  ) ： 将制定数据作为参数添加到已有数据结尾处。
		StringBuffer insert(int index, 数据)  可以将数据插入到index位置，有可能出现角标越界。
	2、删除：
		StringBuffer delete(int start, int end)，包含start，不包含end.
		StringBuffer deleteCharAt(int index) 
		StringBuffer delete(0, sb.length());清空缓冲区。
	3、获取：
		char charAt(int index)；
		int indexOf(String str) 
		int lastIndexOf(String str)
		int length() 
		String substring(int start, int end)  //返回的是String，不再是StringBuffer。
	4、修改：
		StringBuffer replace(int start, int end, String str)
		void setCharAt(int index, char ch) //没有返回值。
	5、反转：
		StringBuffer reverse() 
	6、将缓冲区中的指定数据存储到指定数组中
		void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)  
	
StringBuilder：是StringBuffer的一个简易替换，线程不同步。
		
StringBuilder（线程非安全）和StringBuffer（线程安全）的区别：
	由此可见，如果我们的程序是在单线程下运行，或者是不必考虑到线程同步问题，我们应该优先使用StringBuilder类；当然，如果要保证线程安全，自然非StringBuffer莫属了。
		除了对多线程的支持不一样外，这两个类的使用几乎没有任何差别。

	
基本数据类型对象包装类。
	byte		Byte
	short 		Short 
	int			Integer
	long		Long
	boolean		Boolean
	float		Float
	double		Double
	char		Character
	
基本数据类型对象包装类的最常见作用：
	用于基本数据类型和字符串类型之间做转换。
	基本数据类型转成字符串。
		基本数据类型.toString(基本数据类型值);--->static String toString(int i)  >>> 如：Integer.toString(34);//将34整数变为字符串"34"
		基本数据类型 + "";
	字符串转成基本数据类型。
		static int parseInt(String s)  
		static double parseDouble(String s)  
		static boolean parseBoolean(String s) 
	
	十进制转成其他进制：
		static String toBinaryString(int i)  
		static String toHexString(int i)  
		static String toOctalString(int i)  
	其他进制转为十进制：
		static int parseInt(String s, int radix)
			如：Integer.parseInt("3c",16);//结果为60
				Integer.parseInt("110",2);//结果为6
				Integer.parseInt("110",10);//结果为110
				
	练习：
		Integer x = new Integer("123");
		Integer y = new Integer(123);
		System.out.println(x==y); //fause
		System.out.println(x.equals(y)); //true
		
	自动装箱拆箱：
		Integer x = 4; //自动装箱，有可能为null
		x += 2; //自动拆箱，x变成int类型，然后和2进行加法运算，然后将结果再装箱赋给X。
		
	练习：(当数值在byte范围内容，对于新特性，如果该数值已经存在，则不会再开辟新空间)
		Integer x = 128;
		Integer y = 128;
		System.out.println(x==y); //fause
		
		Integer a = 127;
		Integer b = 127;
		System.out.println(a==b); //true 因为a,b指向了同一个Integer对象。