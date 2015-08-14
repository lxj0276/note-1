单例设计模式（必须会写）？懒汉式特点，是否有问题，如何解决问题？
答：
	思想：
		1，不让其他程序创建该类对象。
		2，在本类中创建一个本类对象。
		3，对外提供方法，让其他程序获取这个对象。 
	两种模式：

	饿汉式：先初始化对象（在方法被调用前）
	class Single
	{
		   private Single(){}    //构造函数私有化
		   private static Single s=new Single();   //在类中创建一个本类对象   
		   public static Single getInstance()    //提供一个方法获取该对象的方法
		   {
				  return s;
		   }
	}

	懒汉式（延时加载）：方法被调用时才初始化对象。该法不安全。
	class Single
	{
		   private Single(){}
		   private static Single s=null;
		   public static Single getInstance()
		   {
				 if{s == null){
					synchronized(Single.class)
					{
						if(s == null)
							s = new Single();
					}
				}
					return s;
		   }
	}
	懒汉式的特点在于实例的延迟加载，有问题，如果多线程访问时会出现安全问题，可用同步方式解决，同步函数和同步代码块都行但是效率稍微有些低效。
		可用双重判断解决这个问题，加的锁：是该类所属的字节码为对象Single.class。
		
	
	设计单例模式时，该对事物怎么描述，还怎么描述，当需要保证该事物的对象在内存中唯一时，就将以上三步加上即可。