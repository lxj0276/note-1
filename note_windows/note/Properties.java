Java.util.Properties：一个可以将键值进行持久化存储的对象。Map--Hashtable
的子类。
Map
	|--Hashtable
		|--Properties：用于属性配置文件，键和值都是字符串类型。
特点：1：可以持久化存储数据。2：键值都是字符串。3：一般用于配置文件。
	load()：将流中的数据加载进集合。
		void load(InputStream inStream)  //Reads a property list (key and element pairs) from the input byte stream.
		void load(Reader reader)  
		原理：其实就是将读取流和指定文件相关联，并读取一行数据，因为数据是规则的key=value，所以获取一行后，通过 = 对该行数据进行切割，左边就是键，
			右边就是值，将键、值存储到properties集合中。
	store()：写入各个项后，刷新输出流。
		void store(OutputStream out, String comments)  
		void store(Writer writer, String comments)  
	list()：将集合的键值数据列出到指定的目的地。
		void list(PrintStream out)  //Prints this property list out to the specified output stream.
		void list(PrintWriter out)  
	Enumeration<?> propertyNames() 
	Object setProperty(String key, String value)  
	Set<String> stringPropertyNames()  
		
	