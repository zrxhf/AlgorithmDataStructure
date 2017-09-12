package rabinkarp;

public class RK {
	
	//
	// Be sure to look at the write up for this assignment
	//  so that you get full credit by satisfying all
	//  of its requirements
	//
	public char[] query;
	public int key;
	public int window;
	public int index;
	public int h;
	public int past;
	
	

	/**
	 * Rabin-Karp string matching for a window of the specified size
	 * @param m size of the window
	 */
	public RK(int m) {
		
		query=new char[m];
	   	window=m;
	}
	

	/**
	 * Compute the rolling hash for the previous m-1 characters with d appended.
	 * @param d the next character in the target string
	 * @return
	 */
	public int nextCh(char d) {
	    
		past=1;
		for (int i=0;i<window;i++){
			past=past*31%511;
		    if (past<0) {past+=511;}
		}
		
		index = key % window;
		h = (h*31-past*Character.hashCode(query[index])+d)%511;
		if(h<0){h+=511;}
		
		query[index]=d;
		key++;
		return h;
		}
		
	
}

