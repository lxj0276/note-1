
java反射:

	反射技术：其实就是动态加载一个指定的类，并获取该类中的所有的内容。而且将字节码文件封装成对象，并将字节码文件中的内容都封装成对象，
		这样便于操作这些成员。
	
	Class类描述的信息：类的名字，类的访问属性，类所属于的包名，字段名称的列表，实现的接口、方法名称的列表等等。

	反射的基本步骤：
		1、获得Class对象，就是获取到指定的名称的字节码文件对象。
		2、实例化对象，获得类的属性、方法或构造函数。
		3、访问属性、调用方法、调用构造函数创建对象。
	
	得到字节码对应的实例对象（Class类型）的三种方法：
		1、类名.class 如：System.class;
		2、对象.getClass() 如：new Date().getClass();
		3、Class.forName("类名") 如：Class.forName("java.util.Date"); //做反射的时候一般用这种。
			String classname = "cn.itcast.reflect.Person";// 来自配置文件
			Class clazz = Class.forName(classname);// 此对象代表Person.class
	反射的用法：
		1）、需要获得java类的各个组成部分，首先需要获得类的Class对象，获得Class对象的三种方式：
		Class.forName(classname) 用于做类加载
		obj.getClass() 用于获得对象的类型
		类名.class 用于获得指定的类型，传参用
		2)、反射类的成员方法：
		Class clazz = Person.class;
		Method method = clazz.getMethod(methodName, new Class[]{paramClazz1, paramClazz2});
		method.invoke();
		3)、反射类的构造函数：
		Constructor con = clazz.getConstructor(new Class[]{paramClazz1, paramClazz2,...}) con.newInstance(params...)
		4)、反射类的属性：
		Field field = clazz.getField(fieldName);
		field.setAccessible(true);
		field.setObject(value);
		
	创建对象的两种方式(其实就是对象在进行实例化时的初始化方式)：
		1，调用空参数的构造函数：使用了Class类中的newInstance()方法。
		2，调用带参数的构造函数：先要获取指定参数列表的构造函数对象，然后通过该构造函数的对象的newInstance(实际参数) 进行对象的初始化。
		
	如何生成获取到字节码文件对象的实例对象。
		Class clazz = Class.forName("cn.itcast.bean.Person");//类加载
		// 直接获得指定的类型
		clazz = Person.class;
		// 根据对象获得类型
		Object obj = new Person("zhangsan", 19);
		clazz = obj.getClass();
		Object obj = clazz.new Instance();//该实例化对象的方法调用就是指定类中的空参数构造函数，给创建对象进行初始化。当指定类中没有空参数构造函数时，该如何创建该类对象呢？请看method_2();
		public static void method_2() throws Exception {
		Class clazz = Class.forName("cn.itcast.bean.Person");
		//既然类中没有空参数的构造函数,那么只有获取指定参数的构造函数,用该函数来进行实例化。
		//获取一个带参数的构造器。
		Constructor constructor = clazz.getConstructor(String.class,int.class);
		//想要对对象进行初始化，使用构造器的方法newInstance();
		Object obj = constructor.newInstance("zhagnsan",30);
		//获取所有构造器。
		Constructor[] constructors = clazz.getConstructors();//只包含公共的
		constructors = clazz.getDeclaredConstructors();//包含私有的
		for(Constructor con : constructors) { System.out.println(con);
		}
		}
		反射指定类中的方法：
		//获取类中所有的方法。
		public static void method_1() throws Exception {
		Class clazz = Class.forName("cn.itcast.bean.Person");
		Method[] methods = clazz.getMethods();//获取的是该类中的公有方法和父类中的公有方法。
		methods = clazz.getDeclaredMethods();//获取本类中的方法，包含私有方法。
		for(Method method : methods) { System.out.println(method);
		}
		}
		//获取指定方法；
		public static void method_2() throws Exception {
		Class clazz = Class.forName("cn.itcast.bean.Person");
		//获取指定名称的方法。
		Method method = clazz.getMethod("show", int.class,String.class);
		//想要运行指定方法，当然是方法对象最清楚，为了让方法运行，调用方法对象的invoke方法即可，但是方法运行必须要明确所属的对象和具体的实际参数。
		Object obj = clazz.newInstance();
		method.invoke(obj, 39,"hehehe");//执行一个方法 }
		//想要运行私有方法。
		public static void method_3() throws Exception {
		Class clazz = Class.forName("cn.itcast.bean.Person");
		//想要获取私有方法。必须用getDeclearMethod();
		Method method = clazz.getDeclaredMethod("method", null);
		// 私有方法不能直接访问，因为权限不够。非要访问，可以通过暴力的方式。
		method.setAccessible(true);//一般很少用，因为私有就是隐藏起来，所以尽量不要访问。 }
		//反射静态方法。
		public static void method_4() throws Exception {
		Class clazz = Class.forName("cn.itcast.bean.Person");
		Method method = clazz.getMethod("function",null);
		method.invoke(null,null);
		}

	九个预定义的Class实例对象：
		八个基本类型，加上void。即：Boolean, Character,Byte,Short,Integer,Long,Float,Double,Void.
		int.class == Integer.TYPE。
		查看是不是数组类型的Class实例对象用的是：Class.isArray().
	总之，只要是在源程序中出现的类型，都有各自的Class实例对象。
	
	反射就是把java类中的各种成分映射成相应的java类。
	
	构造方法的反射：Constructor类代表某个类中的一个构造方法：
		得到某个类所有的构造方法----如：Constructor [] constructor = Class.forName("java.lang.String").getConstructors();
		得到某一个构造方法----如：Constructor [] constructor = Class.forName("java.lang.String").getConstructor(StringBuffer.class);
		用反射实现 new String(new StringBuffer("abc"));
			如下：  Constructor con = String.class.getConstructor(StringBuffer.class);    
					String str = (String)constructor.newInstance(new StringBuffer("abc"));
					调用newInstance时要用到跟getConstructor中相同类型的实例对象。也就是说上边第一句用了StringBuffer，第二句也必须是StringBuffer。
		Class.newInstance()方法：
			该方法内部先得到默认的构造方法，然后用该构造方法创建实例对象。
			例子：String obj = (String)Class.forName("java.lang.String").newInstance();
		
	成员变量的反射：Field类代表某个类中的一个成员变量。
		ReflectPoint pt1 = new ReflectPoint(3,5);
		Field fieldY = pt1.getClass().getField("y");//fieldY不是对象身上的变量，而是类上的，要用它去取某个对象上对应的值。
		System.out.Println(fieldY.get(pt1));  //fieldY.get(pt1)取出pt1对象上变量的值。
		
		fieldX.setAccessble(true);		//暴力反射，不管是不是私有的，都可以取到。
		Field fieldX = pt1.getClass().getDeclaredField("x");
		System.out.Println(fieldX.get(pt1));
		
	成员方法的反射：Method类代表某个类中的一个成员方法。
		得到类中的某一个方法：
			例子:Method charAt = Class.forName("java.lang.String").getMethod("charAt", int.class);
		调用方法：
			通常方式：System.out.println(str.charAt(1));
			反射方式：System.out.println(charAt.invoke(str,1));
				如果传递给Method对象的invoke()方法的第一个参数为null，说明该Method对象对应的是一个静态方法！
				
	数组的反射：
		具有相同维数和元素类型的数组属于同一个类型，即具有相同的Class实例对象。
		代表数组的Class实例对象的getSuperClsss()方法返回的父类为Object类对象的Class。
		基本类型的一维数组可以被当做Object类型使用，不能当做Object[]类型使用；非基本类型的一维数组，即可以当做Object类型使用，
			又可以当做Object[]类型使用。
		Arrays.asList(Object[] obj);
		Arrays.asList()方法处理int[]和String[]时的差异。
		Array工具类用于完成对数组的反射操作。 
			private statice void printObject(Object obj){
				Class clazz = obj.getClass();
				if(clazz.isArray){
					int len = Array.getLength(obj);
					for(int i = 0; i<length; i++){
						System.out.println(Array.get(obj,i));
					}
				}else{
					System.out.println(obj,i);
				}
			}