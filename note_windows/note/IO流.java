字符流：
Reader：用于读取字符流的抽象类。子类必须实现的方法只有 read(char[], int, int) 和close()。
	|---BufferedReader：从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。 
			可以指定缓冲区的大小，或者可使用默认的大小。大多数情况下，默认值就足够大了。
	|---LineNumberReader：跟踪行号的缓冲字符输入流。此类定义了方法 setLineNumber(int) 和 getLineNumber()，它们可分别用于设置和获取当前行号。
	|---InputStreamReader：是字节流通向字符流的桥梁：它使用指定的 charset 读取字节并将其解码为字符。它使用的字符集可以由名称指定或显式给定，
			或者可以接受平台默认的字符集。
	|---FileReader：用来读取字符文件的便捷类。此类的构造方法假定默认字符编码和默认字节缓冲区大小都是适当的。
			要自己指定这些值，可以先在 FileInputStream 上构造一个 InputStreamReader。
	|---CharArrayReader：
	|---StringReader：
Writer：写入字符流的抽象类。子类必须实现的方法仅有 write(char[], int, int)、flush() 和close()。
	|---BufferedWriter：将文本写入字符输出流，缓冲各个字符，从而提供单个字符、数组和字符串的高效写入。
	|---OutputStreamWriter：是字符流通向字节流的桥梁：可使用指定的 charset 将要写入流中的字符编码成字节。
			它使用的字符集可以由名称指定或显式给定，否则将接受平台默认的字符集。
	|---FileWriter：用来写入字符文件的便捷类。此类的构造方法假定默认字符编码和默认字节缓冲区大小都是可接受的。
			要自己指定这些值，可以先在 FileOutputStream 上构造一个 OutputStreamWriter。
	|---PrintWriter：
	|---CharArrayWriter：
	|---StringWriter：
---------------------------------
字节流：
InputStream：是表示字节输入流的所有类的超类。
	|--- FileInputStream：从文件系统中的某个文件中获得输入字节。哪些文件可用取决于主机环境。FileInputStream 用于读取诸如图像数据之类的原始字节流。
			要读取字符流，请考虑使用 FileReader。
	|--- FilterInputStream：包含其他一些输入流，它将这些流用作其基本数据源，它可以直接传输数据或提供一些额外的功能。
	|--- BufferedInputStream：该类实现缓冲的输入流。
	|--- Stream：
	|--- ObjectInputStream：
	|--- PipedInputStream：
-----------------------------------------------
OutputStream：此抽象类是表示输出字节流的所有类的超类。
	|--- FileOutputStream：文件输出流是用于将数据写入 File 或 FileDescriptor 的输出流。
	|--- FilterOutputStream：此类是过滤输出流的所有类的超类。
	|--- BufferedOutputStream：该类实现缓冲的输出流。
	|--- PrintStream：
	|--- DataOutputStream：
	|--- ObjectOutputStream：
	|--- PipedOutputStream：
--------------------------------

缓冲区是提高效率用的，给谁提高呢？
	BufferedWriter：是给字符输出流提高效率用的，那就意味着，缓冲区对象建立时，必须要先有流对象。明确要提高具体的流对象的效率。
	
	FileWriter fw = new FileWriter("bufdemo.txt");
	BufferedWriter bufw = new BufferedWriter(fw);//让缓冲区和指定流相关联。
	for(int x=0; x<4; x++){
		bufw.write(x+"abc");
		bufw.newLine(); //写入一个换行符，这个换行符可以依据平台的不同写入不同的换行符。
		bufw.flush();//对缓冲区进行刷新，可以让数据到目的地中。
	}
	bufw.close();//关闭缓冲区，其实就是在关闭具体的流。
	-----------------------------
	BufferedReader：
	FileReader fr = new FileReader("bufdemo.txt");
	BufferedReader bufr = new BufferedReader(fr);
	String line = null;
	while((line=bufr.readLine())!=null){ //readLine方法返回的时候是不带换行符的。
		System.out.println(line);
	}
	bufr.close();
	-----------------------------
	//记住，只要一读取键盘录入，就用这句话。
	BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
	//输出到控制台
	BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	String line = null;
	while((line=bufr.readLine())!=null){
		if("over".equals(line))
		break;
		bufw.write(line.toUpperCase());//将输入的字符转成大写字符输出
		bufw.newLine();
		bufw.flush();
	}
	bufw.close();
	bufr.close();
	------------------------------
	------------------------------
流对象：其实很简单，就是读取和写入。但是因为功能的不同，流的体系中提供N多的对象。那么开始时，到底该用哪个对象更为合适呢？这就需要明确流的操作规律。

流的操作规律：
	1，明确源和目的。
	数据源：就是需要读取，可以使用两个体系：InputStream、Reader； 数据汇：就是需要写入，可以使用两个体系：OutputStream、Writer；
	2，操作的数据是否是纯文本数据？
	如果是：数据源：Reader
	数据汇：Writer
	如果不是：数据源：InputStream
	数据汇：OutputStream
	3，虽然确定了一个体系，但是该体系中有太多的对象，到底用哪个呢？
	明确操作的数据设备。
	数据源对应的设备：硬盘(File)，内存(数组)，键盘(System.in) s
	数据汇对应的设备：硬盘(File)，内存(数组)，控制台(System.out)。
	4，需要在基本操作上附加其他功能吗？比如缓冲。
	如果需要就进行装饰。
	
转换流特有功能：转换流可以将字节转成字符，原因在于，将获取到的字节通过查编码表获取到指定对应字符。
转换流的最强功能就是基于字节流 + 编码表 。没有转换，没有字符流。
发现转换流有一个子类就是操作文件的字符流对象：
	InputStreamReader
		|--FileReader
	OutputStreamWriter
		|--FileWrier
想要操作文本文件，必须要进行编码转换，而编码转换动作转换流都完成了。所以操作文件的流对象只要继承自转换流就可以读取一个字符了。

但是子类有一个局限性，就是子类中使用的编码是固定的，是本机默认的编码表，对于简体中文版的系统默认码表是GBK。 FileReader fr = new FileReader("a.txt");
InputStreamReader isr = new InputStreamReader(new FileInputStream("a.txt"),"gbk"); 以上两句代码功能一致，
如果仅仅使用平台默认码表，就使用FileReader fr = new FileReader("a.txt"); //因为简化。 如果需要制定码表，必须用转换流。

转换流 = 字节流+编码表。
	转换流的子类File = 字节流 + 默认编码表。
	凡是操作设备上的文本数据，涉及编码转换，必须使用转换流。