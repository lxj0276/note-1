泛型能节省你某些Java类型转换(casting)上的操作。

什么时候定义泛型类？ 当类中要操作的“引用”数据类型不确定时，可以定义泛型来完成扩展。

集合要使用泛型：是一个安全机制。
	好处：
		1、将运行时期出现的问题ClassCastException，转移到了编译时期。方便与程序员解决问题，让运行时的问题减少，提高安全。
		2、避免了强制转换麻烦。
		泛型的主要好处就是让编译器保留参数的类型信息，执行类型检查，执行类型转换操作：编译器保证了这些类型转换的绝对无误。
	迭代器、比较器、集合中都可以使用泛型。	
	泛型在比较器中的应用：
	class LenComparator implements Comparator<String>{
		public int compara(String s1,String s2){
			int num = new Integer(s1.length()).comparaTo(new Integer(s2.length()));
			if(num == 0)
				return s2.compareTo(s1);
			return num;
		}
	}

泛型的构成：
    泛型类声明
    泛型接口声明
		类与接口的例子：	
			1 	public interface List<T> extends Collection<T> {
			2 	...
			3 	}
    泛型方法声明
    泛型构造器(constructor)声明
		泛型方法和构造器的例子： 
			public static <t> T getFirst(List<T> list)
			
定义泛型类型：
	如果类的实例对象中的多处都使用同一个类型变量，这时就要采用泛型类型的方式进行定义，即为类级别的泛型，语法如下：
		public class GenericDao<T>{
			private T field;
			public void save(T obj){}
			public T getById(int id){}
		}
	类级别的泛型是根据引用该类名时指定的类型信息来参数化类型变量的，如：
		·GenericDao<String> dao = null;
		·new genericDao<String>();
	注意：
		·在对泛型类型进行参数化时，类型参数的实例必须是引用类型，不能是基本类型。
		·当一个变量被声明为泛型时，只能被实例变量和方法调用(还有内嵌类型),而不能被静态变量和静态方法调用。
			因为静态成员时被参数化的类所共享的，所以静态成员不应该有类级别的类型参数。
			如果静态的也要用泛型的话，要自己独立定义：public static <E> void update2(E obj){}
	
泛型：使集合中只能存储同一个类型的对象，这样更安全。给javac使用，当编译完成后将取消泛型信息。可以通过反射得到这个类，然后往集合中存入不是指定泛型的数据。
	ArrayList<E>类定义和ArrayList<Integer>类引用中涉及如下术语：
		·整个称为ArrayList<E>泛型类型。
		·ArrayList<E>中的E称为类型变量或类型参数。
		·整个ArrayList<Integer>中的Integer称为参数化的类型
		·ArrayList<Integer>中的Integer称为类型参数的实例或实例类型参数。
		·ArrayList<Integer>中的<>念typeof。
		·ArrayList称为原始类型。
	在创建数组实例时，数组的元素不能使用参数化的类型，如下面的语句有错误：
		Vector<Integer> vectorList[] = new Vector<Integer>[10];
	通配符?:
		使用？通配符可以引用其他各种参数化的类型，？通配符定义的变量主要用作引用，可以调用与参数化无关的方法，不能调用与参数化有关的方法。
		上限定通配符：
			Vector<? extends Number> num = new Vector<Integer>();
		下限定通配符：
			Vector<? super Integer> num = new Vector<Number>();
		限定通配符总是包括自己。
	
	泛型方法的前面，在返回值之前，用<T>来说明类型。
	只有引用类型才能作为T的参数类型。
	private static <T> void swap(T[] a,int i,int j){ 
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	对异常使用泛型：
	private static <T extends Exception> sayHello() throws T{
		try{
		
		}catch(Exception e){
			throw (T)e; 
		}
	}
	
	类型参数的类型推断：
		根据调用泛型方法时实际传递的类型变量或返回值的类型变量来推断，具体规则如下：
			当某个类型变量值在整个参数列表中的所有参数和返回值中的一处被应用时，那么直接根据调用方法时传递的类型变量或返回值来决定类型变量的类型。
				如：swap(new String[6],4,5);---->static <E> void swap(E[] a,int i,intj)
			当某个类型变量在整个参数列表中的所有参数和返回值中多处被应用时：
				·若类型变量为同一种类型时，则类型变量就是此类型，
					如：add(3,5);---> statci <T> T add(T a, T b)
				·若参数类型对应不同类型时，且没有使用返回值，这是取多个参数中的最大交集类型变量，
					如：fill(new Integer[3],3.5f)--->static <T> void fill(T[] a,T v) >>>此对应的类型变量为Number。
				·若类型变量对应不同类型变量，且使用了返回值，这时优先考虑返回值的类型变量，
					如：int x=(3,3.5f)---> static <T> T add(T a,T b)  >>>编译器报错，应把x的类型改为Number(即Number x=(3,3.5))。
			类型变量的类型推断具有传递性，
				·copy(new Integer[5],new String[5])--->static <T> void copy(T[] a,T[] b); >>>编译没问题，推断实际类型变量为Object.
				·copy(new Vector<String>(),new Integer[5])--->static <T> void copy(Collection<T> a,T[] b); >>>编译出问题，
						根据Vector类将类型变量确定为String类型，所以出问题。

泛型的细节：
	1）、泛型到底代表什么类型取决于调用者传入的类型，如果没传，默认是Object类型；
	2）、使用带泛型的类创建对象时，等式两边指定的泛型必须一致； 原因：编译器检查对象调用方法时只看变量，
		然而程序运行期间调用方法时就要考虑对象具体类型了；
	3） 3）、等式两边可以在任意一边使用泛型，在另一边不使用(考虑向后兼容)；
		ArrayList<String> al = new ArrayList<Object>(); //错
		//要保证左右两边的泛型具体类型一致就可以了，这样不容易出错。
		ArrayList<? extends Object> al = new ArrayList<String>(); al.add("aa"); //错
		//因为集合具体对象中既可存储String，也可以存储Object的其他子类，所以添加具体的类型对象不合适，类型检查会出现安全问题。
			//？extends Object 代表Object的子类型不确定，怎么能添加具体类型的对象呢？
		public static void method(ArrayList<? extends Object> al) {
		al.add("abc"); //错
		//只能对al集合中的元素调用Object类中的方法，具体子类型的方法都不能用，因为子类型不确定。 }
						
泛型类型跟其是否子类型没有任何关系。
而对于数组，它们和子类型是相关的：如果类型A是类型B的子类型，那么A[]是B[]的子类型。
	为什么在数组上会有这种类型和子类型的关系？如果它们不相关，你就没有办法把一个未知类型的对象数组传入一个方法里(不是每次都封装成Object[])。
	
	泛型的实例：
		HashMap<String,Integer> maps = new HashMap<String,Integer>();
		maps.put("zhangsan",22);
		maps.put("lisi",32);
		maps.put("wangwu",25);
		Set<Map.Entry<String,Integer>> entrySet = maps.entrySet();
		for(Map.Entry<String,Integer> entry : entrySet){
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		
		在jsp页面中经常要对Set或Map集合进行迭代：
		<c:forEach items = "$(map)" var = "entry">
			${entry.key} : ${entry.value}
		</c:forEach>
	
	泛型类的定义小例：
			class Utils<T>{
				private T t;
				public void setObject(T t){
					this.t = t;
				}
				public T getObject(){
					return t;
				}
				//泛型方法的定义
				public <T> void show(T t){
					System.out.println("show:" + t);
				}
			}
			
		泛型方法的定义小例：
		静态方法不可以访问类上定义的泛型。
		如果静态方法操作的应用数据类型不确定，可以将泛型定义在方法上。
			class Demo{
				public <T> void show(T t){
					System.out.println("show:" + t);
				}
				public <Q> void print(Q q){
					System.out.println("print:" +q);
				}
				public static <T> method(T t){
					System.out.println("show:" + t);
				}
			}
			
		泛型定义在接口上：
			interface Inter<T>{
				void show(T t);
			}
			class InterImpl<T> implements Inter<T>{
				public void show(T t){
					System.out.println("show:" + t);
				}
			}
			
	
	
