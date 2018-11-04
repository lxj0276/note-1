// "static void main" must be defined in a public class.
import java.util.*;

class Solution{

	public int[] solution(int[] T) {
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		int cap=0;
		int[] ans = new int[T.length-1];
		for(int i=0; i<T.length; i++){
			if(T[i]==i) cap=i;
			else{
				if(map.containsKey(T[i])) {
				ArrayList<Integer> temp =map.get(T[i]);
				temp.add(i);
				map.put(T[i],temp);
				}
				else{
					ArrayList<Integer> list =new ArrayList<>();
					list.add(i);
					map.put(T[i],list);
				}
			}
		}
		int count=0;
		int index=0;
		ArrayList<Integer> l = new ArrayList<>();
		for(int i : map.get(cap)){
			//ans[index]++;
			l.add(i);
		}
		//index++;
		boolean flag=true;
		ArrayList<Integer> ll = new ArrayList<>();
		while(flag){
			flag=false;
			for(int i : l) if(map.containsKey(i)) flag=true;
			ll = new ArrayList<>();
			for(int i : l){
				if(map.containsKey(i)) {
                    for(int j : map.get(i)) ll.add(j);
                }
				ans[index]++;
			}
			l=new ArrayList<Integer>(ll);
			index++;
		}
		return ans;

	}

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lim = Integer.parseInt(scanner.nextLine());
        int[] arr = new int[lim];
        String val= scanner.nextLine();
        String[] vals = val.split(" ");
        for (int i = 0; i <lim ; i++) {
            arr[i] = Integer.parseInt(vals[i]);
        }
        scanner.close();
        int[] r = new Solution().solution(arr);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <r.length ; i++) {
            sb.append(r[i]);
            if(i!= r.length-1)
                sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}
