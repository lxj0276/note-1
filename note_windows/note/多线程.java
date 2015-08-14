	
实现多线程的两种方法:
	1、创建线程的第一种方式：继承Thread，由子类复写run方法。
		步骤：
			1，定义类继承Thread类；
			2，复写run方法，将要让线程运行的代码都存储到run方法中； 
			3，通过创建Thread类的子类对象，创建线程对象；
			4，调用线程的start方法，开启线程，并执行run方法。
	2、：实现一个接口Runnable。
		步骤：
			1，定义类实现Runnable接口。
			2，覆盖接口中的run方法（用于封装线程要运行的代码）。
			3，通过Thread类创建线程对象；
			4，将实现了Runnable接口的子类对象作为实际参数传递给Thread类中的构造函数。为什么要传递呢？因为要让线程对象明确要运行的run方法所属的对象。
			5，调用Thread对象的start方法。开启线程，并运行Runnable接口子类中的run方法。
			Ticket t = new Ticket();
			Thread t1 = new Thread(t);
			t1.start();
	多线程安全问题的原因：
		一个线程在执行多条语句，并运算同一个数据时，在在执行过程中的某一时刻，其他线程参与进来，并操作了这个数据。导致到了错误数据的产生。
	多线程涉及到两个因素：
		1，多个线程在操作共享数据。
		2，有多条语句对共享数据进行运算。
	解决安全问题的原理：
		只要将操作共享数据的语句在某一时段让一个线程执行完，在执行过程中，其他线程不能进来执行就可以解决这个问题。		
	wait() 和sleep()的区别（：从执行权和锁上来分析） ：
		1、wait：可以指定时间也可以不指定时间。不指定时间，只能由对应的notify或者notifyAll来唤醒。
			sleep：必须指定时间，时间到自动从冻结状态转成运行状态(临时阻塞状态)。
		2、wait释放资源，释放锁；sleep释放资源，不释放锁。
	同步的弊端：死锁。  容易产生死锁的情况：同步中嵌套同步，而锁却不同。
		

Java中的线程的生命周期大体可分为5种状态。

	①NEW：这种情况指的是，通过New关键字创建了Thread类（或其子类）的对象

	②RUNNABLE：这种情况指的是Thread类的对象调用了start()方法，这时的线程就等待时间片轮转到自己这，以便获得CPU；第二种情况是线程在处于RUNNABLE状态时并没有运行完自己的run方法，时间片用完之后回到RUNNABLE状态；还有种情况就是处于BLOCKED状态的线程结束了当前的BLOCKED状态之后重新回到RUNNABLE状态。

	③RUNNING：这时的线程指的是获得CPU的RUNNABLE线程，RUNNING状态是所有线程都希望获得的状态。
	
	④BLOCKED：这种状态指的是处于RUNNING状态的线程，出于某种原因，比如调用了sleep方法、等待用户输入等而让出当前的CPU给其他的线程。

	⑤DEAD：处于RUNNING状态的线程，在执行完run方法之后，就变成了DEAD状态了。
	
线程中start（）开启线程，并且调用run方法;而run()方法不能开启一个新的线程。



实现方式（Runnable)和继承方式(extends Thead)有什么区别 ？
	这两种方式最大的区别是在资源共享的问题，继承Thread类的多个线程之间不能实现资源的共享，而实现Runnable接口后可以实现多个线程之间的资源共享。
	因为java的单继承特性，如果用继承Thread类的方式来实现多线程的话，此线程类就不能继承其他的类了。所以在程序中往往要求用实现Runnable的方式来开发会更好。
	
synchronized处理线程同步问题。需要同步就看那些语句在操作共享数据。
	同步代码块的格式：synchronized(obj){}

看需不需要同步的三个原则：
	明确哪些代码是多线程代码，明确共享数据，明确多线程运行中哪些语句是操作共享数据的。

同步函数使用的锁是this。

同步函数被静态修饰后，使用的锁不再是this，因为静态方法中不可以并列this，使用的是该方法所在类的字节码文件对象，即：类名.class
	
同步的弊端：死锁。  容易产生死锁的情况：同步中嵌套同步，而锁却不同。


setDaemon(true);Marks this thread as either a daemon thread or a user thread. The Java Virtual Machine exits when the only threads running are 	all 	daemon threads. 设置守护线程，当没有前台线程在运行时，后台线程也随之结束。
	public class TheadDemo1 {

		public static void main(String[] args) {
			Thread th = new TestThread();
			th.setDaemon(true);//设置此线程为守护线程，
			th.start();
			System.out.println("main(): " + Thread.currentThread().getName());
			
		}

	}

	class TestThread extends Thread{
		public void run(){
			while(true){
				System.out.println("run(): " + Thread.currentThread().getName());
				
			}
		}
	}
	
同步Synchronized可以替换为现实中的Lock操作。
	将Object中的wait,notify,notifyAll，替换为了Condition的await,signal,signalAll。
	该Condition对象可以通过Lock锁获取。
	用Condition可以实现本方只唤醒对方的操作。
	
ReentrantLock
	class X {
		private final ReentrantLock lock = new ReentrantLock();
	   // ...

		public void m() {
			lock.lock();  // block until condition holds
			try {
			// ... method body
			} finally {
			lock.unlock()
			}
		}
	}

	class BoundedBuffer {
	   final Lock lock = new ReentrantLock();
	   final Condition notFull  = lock.newCondition(); 
	   final Condition notEmpty = lock.newCondition(); 

	   final Object[] items = new Object[100];
	   int putptr, takeptr, count;

	   public void put(Object x) throws InterruptedException {
			lock.lock();
			try {
			   while (count == items.length)
				 notFull.await();
			   items[putptr] = x;
			   if (++putptr == items.length) putptr = 0;
			   ++count;
			   notEmpty.signal();
			 } finally {
			   lock.unlock();
			}
		}

		public Object take() throws InterruptedException {
			 lock.lock();
			 try {
			   while (count == 0)
				 notEmpty.await();
			   Object x = items[takeptr];
			   if (++takeptr == items.length) takeptr = 0;
			   --count;
			   notFull.signal();
			   return x;
			 } finally {
			   lock.unlock();
			}
		}
	}

