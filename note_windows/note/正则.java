正则表达式：其实是用来操作字符串的一些规则。
	好处：正则的出现，对字符串的复杂操作变得更为简单。
	特点：将对字符串操作的代码用一些符号来表示。只要使用了指定符号，就可以调用底层的代码对字符串进行操作。符号的出现，简化了代码的书写。
	弊端：符号的出现虽然简化了书写，但是却降低了阅读性。 其实更多是用正则解决字符串操作的问题。
		组：用小括号标示，每定义一个小括号，就是一个组，而且有自动编号，从1开始。 只要使用组，对应的数字就是使用该组的内容。别忘了，数组要加\\。 
			(aaa(wwww(ccc))(eee))技巧，从左括号开始数即可。有几个左括号就是几组。
常见操作：
	1，匹配：其实用的就是String类中的matches方法。
		String reg = "[1-9][0-9]{4,14}";
		boolean b = qq.matches(reg);//将正则和字符串关联对字符串进行匹配。
		
		Pattern p = Pattern.compile("a*b");
		Matcher m = p.matcher("aaaaab");
		boolean b = m.matches();
		 
		上边三行就等于下边一行：
		boolean b = Pattern.matches("a*b", "aaaaab");
		
	2，切割：
		其实用的就是String类中的split方法。
	3，替换：
		其实用的就是String类中的replaceAll();
	4，获取：
		1），先要将正则表达式编译成正则对象。使用的是Pattern中静态方法 compile(regex);
		2），通过Pattern对象获取Matcher对象。 Pattern用于描述正则表达式，可以对正则表达式进行解析。 而将规则操作字符串，需要从新封装到匹配器对象Matcher中。 然后使用Matcher对象的方法来操作字符串。 如何获取匹配器对象呢？
		通过Pattern对象中的matcher方法。该方法可以正则规则和字符串想关联。并返回匹配器对象。
		3），使用Matcher对象中的方法即可对字符串进行各种正则操作。
		
Greedy quantifiers 
	X? X, once or not at all 
	X* X, zero or more times 
	X+ X, one or more times 
	X{n} X, exactly n times 
	X{n,} X, at least n times 
	X{n,m} X, at least n but not more than m times 
	
Character classes 
	[abc] a, b, or c (simple class) 	
	[^abc] Any character except a, b, or c (negation) 
	[a-zA-Z] a through z or A through Z, inclusive (range) 
	[a-d[m-p]] a through d, or m through p: [a-dm-p] (union) 
	[a-z&&[def]] d, e, or f (intersection) 
	[a-z&&[^bc]] a through z, except for b and c: [ad-z] (subtraction) 
	[a-z&&[^m-p]] a through z, and not m through p: [a-lq-z](subtraction) 
	
Characters 
	x The character x 
	\\ The backslash character 
	\0n The character with octal value 0n (0 <= n <= 7) 
	\0nn The character with octal value 0nn (0 <= n <= 7) 
	\0mnn The character with octal value 0mnn (0 <= m <= 3, 0 <= n <= 7) 
	\xhh The character with hexadecimal value 0xhh 
	\uhhhh The character with hexadecimal value 0xhhhh 
	\x{h...h} The character with hexadecimal value 0xh...h (Character.MIN_CODE_POINT  <= 0xh...h <=  Character.MAX_CODE_POINT) 
	\t The tab character ('\u0009') 
	\n The newline (line feed) character ('\u000A') 
	\r The carriage-return character ('\u000D') 
	\f The form-feed character ('\u000C') 
	\a The alert (bell) character ('\u0007') 
	\e The escape character ('\u001B') 
	\cx The control character corresponding to x 
	
Predefined character classes 
	. Any character (may or may not match line terminators) 
	\d A digit: [0-9] 
	\D A non-digit: [^0-9] 
	\h A horizontal whitespace character: [ \t\xA0\u1680\u180e\u2000-\u200a\u202f\u205f\u3000] 
	\H A non-horizontal whitespace character: [^\h] 
	\s A whitespace character: [ \t\n\x0B\f\r] 
	\S A non-whitespace character: [^\s] 
	\v A vertical whitespace character: [\n\x0B\f\r\x85\u2028\u2029]  
	\V A non-vertical whitespace character: [^\v] 
	\w A word character: [a-zA-Z_0-9] 
	\W A non-word character: [^\w] 



