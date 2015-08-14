枚举：
	解决的问题：对象的某个属性的值不能是任意的，必须为固定的一组取值其中的某一个；
	
	枚举就相当于一个类，其中实现接口、继承类；也可以定义、构造函数、成员变量、普通方法和抽象方法。
	
	枚举元素必须位于枚举体的最开始部分，枚举元素列表最后要有分号和其他成员分隔。若把枚举中的成员方法或变量等放在枚举元素的最前面，编译器会报错。
	
	带构造方法的枚举：
		构造方法必须定义成私有的。
		如果有多个构造方法，默认选用无参的。
		
	如果枚举只有一个成员时，就可以作为一种单例的实现方式。
	
	public enum WeekDay {
		SUN(1),MON(5),TUE,WED,THI,FRI,SAT;
	
		private WeekDay(){	//必须是private，默认调用的构造方法是无参的。
			System.out.println("first");
		}
		private WeekDay(int day){
			System.out.println("second");
		}
	}