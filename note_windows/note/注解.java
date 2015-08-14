注解(Annotation)：
	注解相当于一种标记，在程序中加了注解就等于为程序打上了某种标记；没加，则等于没有某种标记，以后，javac编译器、开发工具和其他程序可以用反射来了解
		你的类及各种元素上有无何种标记，看有什么标记，就去干相应的事。
		
	了解注解及java提供的几个基本注解：
		@SuppressWarnings("deprecation")
		@Deprecated
		@override
		
		标记可以加在包、类、字段、方法、方法的参数以及局部变量上。
		
		自定义注解及其应用：
					注解类			--->				应用了“注解类”的类	--->	对“应用了注解类的类”进行反射操作的类。
				如：@Retention(RetentionPolicy.RUNTIME)	@A							Class C{
					@interface A{                       Class B{ 					if(B.class.isAnnotionPresent(A.class))
																						A a = B.class.getAnnotion(A.class);
					}									}							}					
			@Retention为元注解，，有三种取值：RetetionPolicy.SOURCE、RetentionPolicy.CLASS、RetentionPolicy.RUNTIME；
					分别对应注解的三种生存状态：java源文件--->class文件--->内存中的字节码。
			@Target(ElementType.METHOD,elementType.TYPE):默认设置为任何元素。
			
	为注解增加基本属性：
		定义基本类型的属性和应用属性：
			·在注解类中增加String color();
			·@MyAnnotation(color = "red");
		用反射方式获得注解对应的实例对象后，再通过该对象调用属性对应的方法。
			·MyAnnotation annotation = (MyAnnotation)AnnotationTest.class.getAnnotation(MyAnnotation.class)
			·System.out.println(a.color());
			·可以认为上边这个@MyAnnotation是MyAnnotation类的一个实例对象。
		为属性指定缺省值：
			String color() default "yellow";
		value属性：
			·String value()default "zx";
			·如果注解中有名称为value的属性，而你只想设置value属性（其他属性采用默认值，不需要显示指定其属性值），那么可以省略value=部分。
				例如：@MyAnnotation("zxx").
		数组类型的属性：
			·int[] arrayAttr() default {3,2,1}; 
			·@MyAnnotation(ArrayAttr = {2,3,4})
			如果数组属性中只有一个元素，这个时候属性值部分可以省略大括号。
		枚举类型的属性：
			·EnumTest.TrafficLamp lamp()
			·@MyAnnotation(lamp=EnumTest.TrafficLamp.GREEN)
		注解类型的属性：
			·MetaAnnotation annotationAttr() default @MetaAnnotation(value = "123");
			·@MyAnnotation(annotationAttr = @MetaAnnotation("yyy"))
			·可以认为上边这个@MyAnnotation是MyAnnotation类的一个实例对象；同样的道理，可以认为上面这个@MetaAnnotation是MetaAnnotation类的一个
				实例对象，调用的代码如下：
				MetaAnnotation ma = myAnnotation.annotationAttr();
				System.out.println(ma.value());
	注解的详细语法可以通过看java语言规范了解，即看java的language specification.
			
		例：
			定义一个注解：
				public @interface ItAnnotation {
				String color() default "blue";
				String value();//如果只有value属性需要设置，再添加注解的时候就可以不写"value="了。
				int[] arrayAttr() default {3,2,1}; 
				EnumTest.TrafficLamp lamp() default EnumTest.TrafficLamp.RED;
				MetaAnnotation annotationAttr() default @MetaAnnotation(value = "123");
				}
			将此注解应用在程序中：
				public void main() throws Exception{
					if(Class.isAnnotationPresent(MyAnnotation.class))
					MyAnnotation annotation = (MyAnnotation)MyAnnotation.class.getAnnotation(MyAnnotation.class);
					annotation.color();
					annotation.value();
					annotation.arrayAttr();
					annotation.lamp();
					annotation.annotationAttr().value();
				}