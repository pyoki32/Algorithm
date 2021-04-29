import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class Sol_여행경로 {

	public static void main(String[] args) {
		
		String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
		String []ans =solution(tickets);
		for(String str : ans) {
			System.out.println(str);
		}
		
		
	}
	static HashMap<String,ArrayList<String>> hm;
	static HashMap<String,Integer> hmTicket;
	static int total;
	static String [] answer;
	static ArrayList<String> res;
	public static String[] solution(String[][] tickets) {
        hm = new HashMap<String,ArrayList<String>>();
        hmTicket= new HashMap<String,Integer>();
        total = tickets.length;
        for(int i=0;i<tickets.length;i++) {
        		if(!hm.containsKey(tickets[i][0])) {
        			ArrayList<String> toList = new ArrayList<>(); 
        			toList.add(tickets[i][1]);
        			hm.put(tickets[i][0], toList);
        		}else {
        			ArrayList<String> temp = hm.get(tickets[i][0]); 
        			temp.add(tickets[i][1]); 					
        			Collections.sort(temp);
        			hm.put(tickets[i][0], temp);
        		}
        		
        		String route = tickets[i][0]+tickets[i][1];
        		if(!hmTicket.containsKey(route)) {
        			hmTicket.put(route, 1);
        		}else {
        			int ticketCnt = hmTicket.get(route);
        			hmTicket.put(route, ticketCnt+1);
        		}
        }
        String [] ans = new String[total+1];
        answer  = new String[total+1];
        res = new ArrayList<String>();
        solve("ICN",ans,1);
        Collections.sort(res);
        answer = res.get(0).split("#");
        return answer;
    }
	static void solve(String start,String [] ans,int depth) {
			if(depth==total+1) {
					String tempStr="";
					ans[0]="ICN";
					for(int i=0;i<total+1;i++) {
						 tempStr +=ans[i]+"#";
				        }					
					res.add(tempStr);
					return;
				}
				ArrayList<String> toList = hm.get(start);
				if(toList!=null){
				for(String to : toList) {
					String route =start+to;
					int ticketCnt = hmTicket.get(route);
					if(ticketCnt>0) {
						hmTicket.put(route, ticketCnt-1);
						ans[depth]=to;
						solve(to,ans,depth+1);
						ans[depth]="";
						hmTicket.put(route, ticketCnt);
					}
					}
				}
		}

}
