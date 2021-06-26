import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Sol_순위_검색 {
	static HashMap<String, Integer> hm;
	static ArrayList<ArrayList<Integer>> keyIdxArr;
	static String[] infoKey;
	static int keyIdx;

	public static void main(String[] args) {

		String[] info = { "java backend junior pizza 150", "python frontend senior chicken 210",
				"python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
				"python backend senior chicken 50" };
		String[] query = { "java and backend and junior and pizza 100",
				"python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
				"- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 1" };
		int[] answer = solution(info, query);
		for (int k : answer)
			System.out.println(k);

	}

	public static int[] solution(String[] info, String[] query) {
		keyIdx = 0;
		keyIdxArr = new ArrayList<>();
		int keyIdxSize = (info.length * 15) + 1;
		for (int i = 0; i <= keyIdxSize; i++) {
			keyIdxArr.add(new ArrayList<>());
		}
		hm = new HashMap<>();
		for (int i = 0; i < info.length; i++) {
			infoKey = info[i].split(" ");
			for (int j = 0; j <= 4; j++) {
				int[] pick = new int[j + 1];
				combination(pick, 0, 4, j, 0);
			}
		}

		for (int i = 0; i <= keyIdxSize; i++) {
			if (keyIdxArr.get(i).size() != 0)
				Collections.sort(keyIdxArr.get(i));

		}

		int[] answer = new int[query.length];
		for (int i = 0; i < query.length; i++) {

			String[] queryRes = makeQueryKey(query[i]);
			String queryKey = queryRes[0];
			int score = Integer.parseInt(queryRes[1]);
			int findKeyIdx = -1;
			if (hm.containsKey(queryKey)) {
				findKeyIdx = hm.get(queryKey);
				ArrayList<Integer> tempAl = new ArrayList<>();
				tempAl = keyIdxArr.get(findKeyIdx);
				int idx = binarySearch(tempAl, score);
				if (idx == tempAl.size() - 1) {
					if (score > tempAl.get(idx)) {
						answer[i] = 0;
					} else {
						answer[i] = tempAl.size() - idx;
					}
				} else
					answer[i] = tempAl.size() - idx;
			}
		}

		return answer;
	}

	public static void combination(int[] pick, int index, int n, int r, int target) {
		if (r == 0) {
			makeKey(pick, index);
		} else if (target == n)
			return;
		else {
			pick[index] = target;
			combination(pick, index + 1, n, r - 1, target + 1);
			combination(pick, index, n, r, target + 1);
		}
	}

	public static void makeKey(int[] pick, int index) {
		String[] key = new String[infoKey.length];
		for (int i = 0; i < infoKey.length; i++) {
			key[i] = infoKey[i];

		}
		for (int i = 0; i < index; i++) {
			key[pick[i]] = "-";

		}

		String hmKey = "";
		for (int i = 0; i < key.length - 1; i++) {
			hmKey += key[i];
		}

		int score = Integer.parseInt(key[key.length - 1]);
		if (hm.containsKey(hmKey)) {
			int idx = hm.get(hmKey);
			keyIdxArr.get(idx).add(score);
		} else {

			keyIdxArr.get(keyIdx).add(score);
			hm.put(hmKey, keyIdx);
			keyIdx++;
		}

	}

	public static String[] makeQueryKey(String query) {

		String[] queryStr = query.split(" and | ");

		String queryKey = "";
		for (int i = 0; i < queryStr.length - 1; i++) {
			queryKey += queryStr[i];
		}

		String[] res = new String[2];
		res[0] = queryKey;
		res[1] = queryStr[queryStr.length - 1];
		return res;
	}

	public static int binarySearch(ArrayList<Integer> scores, int value) {
		int low = 0;
		int high = scores.size() - 1;
		int mid = 0;

		while (low < high) {
			mid = (low + high) / 2;
			if (value <= scores.get(mid)) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}
}
