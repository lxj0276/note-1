自动拆装箱：java中数据类型分为两种 ： 基本数据类型 引用数据类型(对象)
	在 java程序中所有的数据都需要当做对象来处理，针对8种基本数据类型提供了包装类，如下：
		int --> Integer
		byte --> Byte
		short --> Short
		long --> Long
		char --> Character
		double --> Double
		float --> Float
		boolean --> Boolean
		
1)、Integer x = 1; x = x + 1; 经历了什么过程？装箱 拆箱 装箱；
2)、为了优化，虚拟机为包装类提供了缓冲池，Integer池的大小 -128~127 一个字节的大小；
3)、String池：Java为了优化字符串操作 提供了一个缓冲池；