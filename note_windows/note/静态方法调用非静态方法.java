 ʲôʱ������ھ�̬�����е��÷Ǿ�̬�����أ�
	�ھ�̬������һ�㲻����ֱ�ӵ��÷Ǿ�̬���������ǿ���ͨ���ھ�̬�����д���һ������
	Ȼ���ھ�̬������ͨ�����ö���ľ�̬�����Ӷ���ӵ��÷Ǿ�̬������
	eg:    

	question�����3~100֮�����������

	1.�ھ�̬�����е��þ�̬����

	public class findPrime{

	public static boolean primeJudge(int k){      //�ж�����          
	   for(int i=2;i<=Math.sqrt(k);i++){
		if(k%i==0) return false;
		}
	   return true;
	   }
	  
	public static void main(String[] args){  
	   for(int i=3;i<=100;i++){
		if(primeJudge(i))                     //��̬��main�������е��þ�̬��primejudge����
		 System.out.print(i+"   ");
		}
	   }
	}

	����primejudge����ǰ��staticȥ����������Ϊ�Ǿ�̬����������������Ĵ���

	findPrime.java:10: �޷��Ӿ�̬�����������÷Ǿ�̬ ���� primeJudge(int)
							if(primeJudge(i)){
							   ^
	1 ����

	ԭ������ھ�̬�ķ����е����˷Ǿ�̬��primejudge����������Ϊ���µ�code���򲻻���������Ĵ���

	public class findPrime{

	public boolean primeJudge(int k){              //primejudge���� Ϊ�Ǿ�̬�ķ���   

	    for(int i=2;i<=Math.sqrt(k);i++){
		if(k%i==0) return false;
		}
	   return true;
	   }

	public static void main(String[] args){   
	   for(int i=3;i<=100;i++){
		findPrime p=new findPrime();       //����һ������

	//ͨ�����ö����primejudge�����ھ�̬��main�����м�ӵ��÷Ǿ�̬primejudge����

	if(p.primeJudge(i))                    

	System.out.print(i+"   ");
		}
	   }
	}