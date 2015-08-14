代理： 
	要为已存在的多个具有相同接口的目标类的各个方法增加一些系统功能，例如：异常处理、日志、计算方法的运行时间、事务管理等等，如何做呢？
	编写一个与目标类具有相同接口的代理类，代理类的每个方法调用目标类的相同方法，并在调用方法时加上系统功能的代码。
	如果采用工厂模式和配置文件的方式进行管理，则不需要修改客户端程序，在配置文件中配置是如何使用目标类、还是代理类，这样以后很容易切换。
	
	AOP(Aspect oriented program)面向方面的编程。AOP的目标是要使交叉业务模块化。
	
	动态代理技术：
		JVM可以再运行期动态生成出类的字节码，这种动态生成的类往往被用做代理类，即动态代理类。
		JVM生成的动态类必须实现一个或多个接口，所以，JVM生成的动态类只能用作具有相同接口的目标类的代理。
		CGLIB库可以动态生成一个类的子类，一个类的子类也可以用作该类的代理，所以，如果要为一个没有实现接口的类生成动态代理类，那么可以使用CGLIB库。
		代理类的各个方法中通常除了要调用目标的相应方法和对外返回目标返回结果外，还可以再代理方法中的如下四个位置加上系统功能代码：
			·在调用目标方法之前
			·在调用目标方法之后
			·在调用目标方法前后
			·在处理目标方法异常的catch块中
		
		
	Class clazzProxy = Proxy.getProxy
	Collection proxy3 = (Collection)getProxy(target,new MyAdvice()); 	
	private static Object getProxy(final Object target,final Advice advice) {
		Object proxy3 = Proxy.newProxyInstance(
				target.getClass().getClassLoader(),
				/*new Class[]{Collection.class},*/
				target.getClass().getInterfaces(),
				new InvocationHandler(){
				
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {

						/*long beginTime = System.currentTimeMillis();
						Object retVal = method.invoke(target, args);
						long endTime = System.currentTimeMillis();
						System.out.println(method.getName() + " running time of " + (endTime - beginTime));
						return retVal;*/
						

						advice.beforeMethod(method);
						Object retVal = method.invoke(target, args);
						advice.afterMethod(method);
						return retVal;						
						
					}
				}
				);
		return proxy3;
	}
	