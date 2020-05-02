import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class StoneAndJwell {
	
	
	    public int numJewelsInStones(String J, String S) {
	        int countJ=0;
	        Set<Character> s=new HashSet<Character>();
	        
	        for(int i =0;i<J.length();i++){
	            s.add(J.charAt(i));
	        }
	        
	        for(int i=0;i<S.length();i++){
	            if(s.contains(S.charAt(i))){
	                countJ++;
	            }
	        }
	        return countJ;
	        
	    }
	

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter String S: ");
		String S = scanner.nextLine();
		
		System.out.println("Enter String J: ");
		String J = scanner.nextLine();
		
		StoneAndJwell andJwell = new StoneAndJwell();
		int countJ=andJwell.numJewelsInStones(J, S);
		System.out.println(countJ);
		
		

	}

}
