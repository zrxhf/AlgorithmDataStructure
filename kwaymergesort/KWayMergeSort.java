package kwaymergesort;

import timing.Ticker;
public class KWayMergeSort {

	
/**
* 
* @param K some positive power of 2.
* @param input an array of unsorted integers.  Its size is either 1, or some other power of 2 that is at least K
* @param ticker call .tick() on this to account for the work you do
* @return
*/
public static Integer[] kwaymergesort(int K, Integer[] input, Ticker ticker) {
//
// FIXME
// Following just copies the input as the answer
//
// You must replace the loop below with code that performs
// a K-way merge sort, placing the result in ans
//
// The web page for this assignment provides more detail.
//
// Use the ticker as you normally would, to account for
// the operations taken to perform the K-way merge sort.
//
	int n = input.length;
	Integer[] answer = new Integer[n];
	Integer[][] tmp = new Integer[K][n/K];
    if (n<2) return input;
    int id = 0;
    for(int i=0;i<=K-1;i++)
       for(int j=0;j<=n/K-1;j++){
          tmp[i][j]=input[id++];
          ticker.tick();
          }
   
    //Recursively call kwaymergesort() on each array
    for(int i=0;i<=K-1;i++){
    input = tmp[i];
    tmp[i] = kwaymergesort(K,tmp[i],ticker);
    ticker.tick();
    }
    //Sort each array
    answer = mergesort(tmp,ticker)[0];
    ticker.tick();
    return answer;
}

public static Integer[][] mergesort(Integer[][] input,Ticker ticker){
  int m = input.length/2;
  int n = input[0].length*2;
  Integer[][] subpb = new Integer[m][n];
  
  for(int i=0;i < m*2; i+=2){
      subpb[i/2] = merge(input[i],input[i+1],ticker);
      ticker.tick();
      }

  if (subpb.length != 1){
      ticker.tick();
      return mergesort(subpb,ticker);
      }
      else{
      ticker.tick();
      return subpb;
      }
}

public static Integer[] merge(Integer[] inputa,Integer[] inputb,Ticker ticker){

//sorted arrays
int n = inputa.length;
int m = inputb.length;
Integer[] ans = new Integer[n+m];
int idxA=0, idxB = 0;
ticker.tick(4);
//merge into one
for (int i = 0; i < n+m; i++) {
	if (idxA < n && idxB < n) {
		if (inputa[idxA] < inputb[idxB]) {
            ans[i] = inputa[idxA++];
            ticker.tick();
            } 
		else {
            ans[i] = inputb[idxB++];
            ticker.tick();
            }
        } 
	else if (idxA < n) {
            ans[i] = inputa[idxA++];
            ticker.tick();
        } 
	else {
            ans[i] = inputb[idxB++];
            ticker.tick();
        } 
    }
    return ans;     
}
}
