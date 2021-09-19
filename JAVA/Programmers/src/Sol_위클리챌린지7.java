class Solution {
  public static int[] solution(int[] enter, int[] leave) {
        int[] answer = new int[enter.length];
        int[] enterIdx = new int[enter.length+1];
        int[] leaveIdx = new int[enter.length+1];
        for (int i = 0; i < enter.length; i++) {
            enterIdx[enter[i]] = i+1;
            leaveIdx[leave[i]] = i+1;
           
        }
        for (int i = 1; i <= enter.length; i++) {
            int cnt=0;
            
               for(int j=1;j<enterIdx[i];j++){
                    if(leaveIdx[enter[j-1]]>leaveIdx[i])cnt++;
                    else{
                        for(int k= enterIdx[i]+1;k<=enter.length;k++){
                            if(leaveIdx[enter[k-1]]<leaveIdx[enter[j-1]]){
                                cnt++;
                                break;
                            }
                        }
                    }
                }
                
                for(int j= enterIdx[i]+1;j<=enter.length;j++){
                    if(leaveIdx[enter[j-1]]<leaveIdx[i])cnt++;
                    else{
                        for(int k= 1;k<leaveIdx[i];k++){
                            if(enterIdx[leave[k-1]]>enterIdx[enter[j-1]]){
                               cnt++;
                                break;
                            }
                        }
                    } 
                }
            answer[i-1]=cnt;
        }
        return answer;
    }
}