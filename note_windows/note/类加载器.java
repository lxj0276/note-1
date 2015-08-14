类加载器：
	java虚拟机中可以安装多个类加载器，系统默认三个主要类加载器，每个类负责加载特定位置的类：BootStrap，ExtClassLoader，AppClassLoader.
	类加载器也是java类，因为其他是java类的加载器本身也要被类加载器加载，显然必须有第一个类加载器不是java类，这个是BootStrap（用C++写的一段二进制代码）。
	java虚拟机中的所有类装载器采用具有父子关系的树形结构进行组织，在实例化每个类装载器对象时，需要为其指定一个父级类装载器对象，
		或者默认采用系统类装载器为其父级类加载。
		类加载器之间的父子关系和管辖范围图：
		BootStrap(加载JRE/lib/rt.jar)<---ExtClassLoader(加载JRE/lib/ext/*.jar)<---AppClassLoader(加载CLASSPATH指定的所有jar或目录).
		
	类加载器的委托机制：
	
		每个类加载器加载类时，又先委托给其上级类加载器。
			·当所有祖亲类加载器没有加载到类，回到发起者类加载器，还加载不到，则抛ClassNotFoundException，而不是再去找发起者类加载器的儿子，
				因为没有getChild方法。
				
		当java虚拟机要加载一个类时，到底派哪个类加载器去加载呢 ？
			·首先当前线程的类加载器去加载线程中的第一个类。
			·如果类A中引用了类B，java虚拟机将使用加载类A的类装载器来加载类B。
			·还可以直接调用ClassLoader.loadClass()方法来指定某个类加载器去加载某个类。

		自定义类加载器：
			·自定义的类加载器必须继承ClassLoader
			·loadClass方法与findClass方法(需要复写findClass方法)
			·defineClass方法。