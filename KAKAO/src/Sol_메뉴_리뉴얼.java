import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Sol_메뉴_리뉴얼 {

	static char[] ordersArr;
	static String pickStr;
	static HashMap<String, Integer> hm;
	static int[] maxOrder;

	public static void main(String[] args) {
		String[] orders = { "XYZ", "XWY", "WXA" };
		int[] course = { 2, 3, 4 };
		String[] answer = solution(orders, course);

		for (String str : answer) {
			System.out.println(str);
		}

	}

	public static String[] solution(String[] orders, int[] course) {

		maxOrder = new int[11];
		hm = new HashMap<>();
		for (int i = 0; i < orders.length; i++) {
			ordersArr = new char[orders[i].length()];
			for (int j = 0; j < orders[i].length(); j++) {
				ordersArr[j] = orders[i].charAt(j);
			}
			Arrays.sort(ordersArr);
			for (int k : course) {
				pickStr = "";
				pickMenu(0, 0, k);
			}
		}

		ArrayList<String> res = new ArrayList<>();
		Set<Map.Entry<String, Integer>> entries = hm.entrySet();
		for (Map.Entry<String, Integer> entry : entries) {
			String menu = entry.getKey();
			int cnt = entry.getValue();
			for (int k : course) {
				if (menu.length() == k && maxOrder[menu.length()] == cnt) {
					res.add(menu);
					break;
				}
			}
		}
		Collections.sort(res);
		String[] answer = new String[res.size()];
		for (int i = 0; i < res.size(); i++) {
			answer[i] = res.get(i);
		}

		return answer;
	}

	public static void pickMenu(int start, int depth, int cnt) {

		if (depth == cnt) {
			if (hm.containsKey(pickStr)) {
				int num = hm.get(pickStr);
				hm.put(pickStr, num + 1);
				int orderCnt = pickStr.length();
				maxOrder[orderCnt] = Math.max(maxOrder[orderCnt], num + 1);
			} else {
				hm.put(pickStr, 1);
			}
			return;
		}
		for (int i = start; i < ordersArr.length; i++) {
			pickStr += ordersArr[i];
			pickMenu(i + 1, depth + 1, cnt);
			pickStr = pickStr.substring(0, pickStr.length() - 1);
		}
	}
}
