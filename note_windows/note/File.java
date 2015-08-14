File类：将文件系统中的文件和文件夹封装成了对象。提供了更多的属性和行为可以对这些文件和文件夹进行操作。这些是流对象办不到的，因为流只操作数据。

File类常见方法：
	1：创建。
		boolean createNewFile()：在指定目录下创建文件，如果该文件已存在，则不创建。而对操作文件的输出流而言，输出流对象已建立，就会创建文件，
				如果文件已存在，会覆盖。除非续写。
		boolean mkdir()：创建此抽象路径名指定的目录。 boolean mkdirs()：创建多级目录。
	2：删除。
		boolean delete()：删除此抽象路径名表示的文件或目录。
		void deleteOnExit()：在虚拟机退出时删除。
		注意：在删除文件夹时，必须保证这个文件夹中没有任何内容，才可以将该文件夹用delete删除。
		window的删除动作，是从里往外删。注意：java删除文件不走回收站。要慎用。
	3：获取.
		long length()：获取文件大小。
		String getName()：返回由此抽象路径名表示的文件或目录的名称。
		String getPath()：将此抽象路径名转换为一个路径名字符串。
		String getAbsolutePath()：返回此抽象路径名的绝对路径名字符串。
		String getParent()：返回此抽象路径名父目录的抽象路径名，如果此路径名没有指定父目录，则返回 null。
		long lastModified()：返回此抽象路径名表示的文件最后一次被修改的时间。
		File.pathSeparator：返回当前系统默认的路径分隔符，windows默认为 “；”。
		File.Separator：返回当前系统默认的目录分隔符，windows默认为 “\”。
	4：判断：
		boolean exists()：判断文件或者文件夹是否存在。
		boolean isDirectory()：测试此抽象路径名表示的文件是否是一个目录。
		boolean isFile()：测试此抽象路径名表示的文件是否是一个标准文件。
		boolean isHidden()：测试此抽象路径名指定的文件是否是一个隐藏文件。
		boolean isAbsolute()：测试此抽象路径名是否为绝对路径名。
	5：重命名。
		boolean renameTo(File dest)：可以实现移动的效果。剪切+重命名。
	String[] list()：列出指定目录下的当前的文件和文件夹的名称。包含隐藏文件。
		如果调用list方法的File 对象中封装的是一个文件，那么list方法返回数组为null。如果封装的对象不存在也会返回null。
			只有封装的对象存在并且是文件夹时，这个方法才有效。