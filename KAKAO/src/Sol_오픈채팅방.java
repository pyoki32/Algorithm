import java.util.ArrayList;
import java.util.HashMap;

public class Sol_오픈채팅방 {

	public static void main(String[] args) {
		String[] record = { "Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo",
				"Change uid4567 Ryan" };
		solution(record);
	}

	public static String[] solution(String[] record) {
		
		HashMap<String, String> nicknameByUserID = new HashMap<>();
		
		for (int i = 0; i < record.length; i++) {
			String[] str = record[i].split(" ");
			String action = str[0];
			String id = str[1];
			if (action.equals("Enter")) {
				nicknameByUserID.put(id,str[2]);			
			} else if (action.equals("Change")) {
				nicknameByUserID.put(id,str[2]);
			}
		}
		
		ArrayList<String> res = new ArrayList<>();
		
		for (int i = 0; i < record.length; i++) {
			String[] str = record[i].split(" ");
			String action = str[0];
			String id = str[1];
			if (action.equals("Enter")) {
				res.add(nicknameByUserID.get(id)+"님이 들어왔습니다.");
			} else if (action.equals("Leave")) {
				res.add(nicknameByUserID.get(id)+"님이 나갔습니다.");
			}
		}

		String[] answer = new String[res.size()];
		for(int i=0;i<answer.length;i++) {
			answer[i]=res.get(i);	
		}
		return answer;
	}
}
