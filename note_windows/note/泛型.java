�����ܽ�ʡ��ĳЩJava����ת��(casting)�ϵĲ�����

ʲôʱ���巺���ࣿ ������Ҫ�����ġ����á��������Ͳ�ȷ��ʱ�����Զ��巺���������չ��

����Ҫʹ�÷��ͣ���һ����ȫ���ơ�
	�ô���
		1��������ʱ�ڳ��ֵ�����ClassCastException��ת�Ƶ��˱���ʱ�ڡ����������Ա������⣬������ʱ��������٣���߰�ȫ��
		2��������ǿ��ת���鷳��
		���͵���Ҫ�ô������ñ���������������������Ϣ��ִ�����ͼ�飬ִ������ת����������������֤����Щ����ת���ľ�������
	���������Ƚ����������ж�����ʹ�÷��͡�	
	�����ڱȽ����е�Ӧ�ã�
	class LenComparator implements Comparator<String>{
		public int compara(String s1,String s2){
			int num = new Integer(s1.length()).comparaTo(new Integer(s2.length()));
			if(num == 0)
				return s2.compareTo(s1);
			return num;
		}
	}

���͵Ĺ��ɣ�
    ����������
    ���ͽӿ�����
		����ӿڵ����ӣ�	
			1 	public interface List<T> extends Collection<T> {
			2 	...
			3 	}
    ���ͷ�������
    ���͹�����(constructor)����
		���ͷ����͹����������ӣ� 
			public static <t> T getFirst(List<T> list)
			
���巺�����ͣ�
	������ʵ�������еĶദ��ʹ��ͬһ�����ͱ�������ʱ��Ҫ���÷������͵ķ�ʽ���ж��壬��Ϊ�༶��ķ��ͣ��﷨���£�
		public class GenericDao<T>{
			private T field;
			public void save(T obj){}
			public T getById(int id){}
		}
	�༶��ķ����Ǹ������ø�����ʱָ����������Ϣ�����������ͱ����ģ��磺
		��GenericDao<String> dao = null;
		��new genericDao<String>();
	ע�⣺
		���ڶԷ������ͽ��в�����ʱ�����Ͳ�����ʵ���������������ͣ������ǻ������͡�
		����һ������������Ϊ����ʱ��ֻ�ܱ�ʵ�������ͷ�������(������Ƕ����),�����ܱ���̬�����;�̬�������á�
			��Ϊ��̬��Աʱ������������������ģ����Ծ�̬��Ա��Ӧ�����༶������Ͳ�����
			�����̬��ҲҪ�÷��͵Ļ���Ҫ�Լ��������壺public static <E> void update2(E obj){}
	
���ͣ�ʹ������ֻ�ܴ洢ͬһ�����͵Ķ�����������ȫ����javacʹ�ã���������ɺ�ȡ��������Ϣ������ͨ������õ�����࣬Ȼ���������д��벻��ָ�����͵����ݡ�
	ArrayList<E>�ඨ���ArrayList<Integer>���������漰�������
		��������ΪArrayList<E>�������͡�
		��ArrayList<E>�е�E��Ϊ���ͱ��������Ͳ�����
		������ArrayList<Integer>�е�Integer��Ϊ������������
		��ArrayList<Integer>�е�Integer��Ϊ���Ͳ�����ʵ����ʵ�����Ͳ�����
		��ArrayList<Integer>�е�<>��typeof��
		��ArrayList��Ϊԭʼ���͡�
	�ڴ�������ʵ��ʱ�������Ԫ�ز���ʹ�ò����������ͣ������������д���
		Vector<Integer> vectorList[] = new Vector<Integer>[10];
	ͨ���?:
		ʹ�ã�ͨ������������������ֲ����������ͣ���ͨ�������ı�����Ҫ�������ã����Ե�����������޹صķ��������ܵ�����������йصķ�����
		���޶�ͨ�����
			Vector<? extends Number> num = new Vector<Integer>();
		���޶�ͨ�����
			Vector<? super Integer> num = new Vector<Number>();
		�޶�ͨ������ǰ����Լ���
	
	���ͷ�����ǰ�棬�ڷ���ֵ֮ǰ����<T>��˵�����͡�
	ֻ���������Ͳ�����ΪT�Ĳ������͡�
	private static <T> void swap(T[] a,int i,int j){ 
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	���쳣ʹ�÷��ͣ�
	private static <T extends Exception> sayHello() throws T{
		try{
		
		}catch(Exception e){
			throw (T)e; 
		}
	}
	
	���Ͳ����������ƶϣ�
		���ݵ��÷��ͷ���ʱʵ�ʴ��ݵ����ͱ����򷵻�ֵ�����ͱ������ƶϣ�����������£�
			��ĳ�����ͱ���ֵ�����������б��е����в����ͷ���ֵ�е�һ����Ӧ��ʱ����ôֱ�Ӹ��ݵ��÷���ʱ���ݵ����ͱ����򷵻�ֵ���������ͱ��������͡�
				�磺swap(new String[6],4,5);---->static <E> void swap(E[] a,int i,intj)
			��ĳ�����ͱ��������������б��е����в����ͷ���ֵ�жദ��Ӧ��ʱ��
				�������ͱ���Ϊͬһ������ʱ�������ͱ������Ǵ����ͣ�
					�磺add(3,5);---> statci <T> T add(T a, T b)
				�����������Ͷ�Ӧ��ͬ����ʱ����û��ʹ�÷���ֵ������ȡ��������е���󽻼����ͱ�����
					�磺fill(new Integer[3],3.5f)--->static <T> void fill(T[] a,T v) >>>�˶�Ӧ�����ͱ���ΪNumber��
				�������ͱ�����Ӧ��ͬ���ͱ�������ʹ���˷���ֵ����ʱ���ȿ��Ƿ���ֵ�����ͱ�����
					�磺int x=(3,3.5f)---> static <T> T add(T a,T b)  >>>����������Ӧ��x�����͸�ΪNumber(��Number x=(3,3.5))��
			���ͱ����������ƶϾ��д����ԣ�
				��copy(new Integer[5],new String[5])--->static <T> void copy(T[] a,T[] b); >>>����û���⣬�ƶ�ʵ�����ͱ���ΪObject.
				��copy(new Vector<String>(),new Integer[5])--->static <T> void copy(Collection<T> a,T[] b); >>>��������⣬
						����Vector�ཫ���ͱ���ȷ��ΪString���ͣ����Գ����⡣

���͵�ϸ�ڣ�
	1�������͵��״���ʲô����ȡ���ڵ����ߴ�������ͣ����û����Ĭ����Object���ͣ�
	2����ʹ�ô����͵��ഴ������ʱ����ʽ����ָ���ķ��ͱ���һ�£� ԭ�򣺱�������������÷���ʱֻ��������
		Ȼ�����������ڼ���÷���ʱ��Ҫ���Ƕ�����������ˣ�
	3�� 3������ʽ���߿���������һ��ʹ�÷��ͣ�����һ�߲�ʹ��(����������)��
		ArrayList<String> al = new ArrayList<Object>(); //��
		//Ҫ��֤�������ߵķ��;�������һ�¾Ϳ����ˣ����������׳���
		ArrayList<? extends Object> al = new ArrayList<String>(); al.add("aa"); //��
		//��Ϊ���Ͼ�������мȿɴ洢String��Ҳ���Դ洢Object���������࣬������Ӿ�������Ͷ��󲻺��ʣ����ͼ�����ְ�ȫ���⡣
			//��extends Object ����Object�������Ͳ�ȷ������ô����Ӿ������͵Ķ����أ�
		public static void method(ArrayList<? extends Object> al) {
		al.add("abc"); //��
		//ֻ�ܶ�al�����е�Ԫ�ص���Object���еķ��������������͵ķ����������ã���Ϊ�����Ͳ�ȷ���� }
						
�������͸����Ƿ�������û���κι�ϵ��
���������飬���Ǻ�����������صģ��������A������B�������ͣ���ôA[]��B[]�������͡�
	Ϊʲô�������ϻ����������ͺ������͵Ĺ�ϵ��������ǲ���أ����û�а취��һ��δ֪���͵Ķ������鴫��һ��������(����ÿ�ζ���װ��Object[])��
	
	���͵�ʵ����
		HashMap<String,Integer> maps = new HashMap<String,Integer>();
		maps.put("zhangsan",22);
		maps.put("lisi",32);
		maps.put("wangwu",25);
		Set<Map.Entry<String,Integer>> entrySet = maps.entrySet();
		for(Map.Entry<String,Integer> entry : entrySet){
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		
		��jspҳ���о���Ҫ��Set��Map���Ͻ��е�����
		<c:forEach items = "$(map)" var = "entry">
			${entry.key} : ${entry.value}
		</c:forEach>
	
	������Ķ���С����
			class Utils<T>{
				private T t;
				public void setObject(T t){
					this.t = t;
				}
				public T getObject(){
					return t;
				}
				//���ͷ����Ķ���
				public <T> void show(T t){
					System.out.println("show:" + t);
				}
			}
			
		���ͷ����Ķ���С����
		��̬���������Է������϶���ķ��͡�
		�����̬����������Ӧ���������Ͳ�ȷ�������Խ����Ͷ����ڷ����ϡ�
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
			
		���Ͷ����ڽӿ��ϣ�
			interface Inter<T>{
				void show(T t);
			}
			class InterImpl<T> implements Inter<T>{
				public void show(T t){
					System.out.println("show:" + t);
				}
			}
			
	
	
