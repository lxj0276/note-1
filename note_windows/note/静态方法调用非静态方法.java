 什么时候可以在静态方法中调用非静态方法呢？
	在静态方法中一般不可以直接调用非静态方法，但是可以通过在静态方法中创建一个对象，
	然后在静态方法中通过调用对象的静态方法从而间接调用非静态方法。
	eg:    

	question：输出3~100之间的所有素数

	1.在静态方法中调用静态方法

	public class findPrime{

	public static boolean primeJudge(int k){      //判断素数          
	   for(int i=2;i<=Math.sqrt(k);i++){
		if(k%i==0) return false;
		}
	   return true;
	   }
	  
	public static void main(String[] args){  
	   for(int i=3;i<=100;i++){
		if(primeJudge(i))                     //静态的main方法体中调用静态的primejudge方法
		 System.out.print(i+"   ");
		}
	   }
	}

	若将primejudge方法前的static去掉，即定义为非静态方法，则会出现下面的错误：

	findPrime.java:10: 无法从静态上下文中引用非静态 方法 primeJudge(int)
							if(primeJudge(i)){
							   ^
	1 错误

	原因就是在静态的方法中调用了非静态的primejudge方法。若改为如下的code，则不会出现这样的错误：

	public class findPrime{

	public boolean primeJudge(int k){              //primejudge定义 为非静态的方法   

	    for(int i=2;i<=Math.sqrt(k);i++){
		if(k%i==0) return false;
		}
	   return true;
	   }

	public static void main(String[] args){   
	   for(int i=3;i<=100;i++){
		findPrime p=new findPrime();       //定义一个对象

	//通过调用对象的primejudge方法在静态的main方法中间接调用非静态primejudge方法

	if(p.primeJudge(i))                    

	System.out.print(i+"   ");
		}
	   }
	}