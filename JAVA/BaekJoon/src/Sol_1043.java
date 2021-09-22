import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Sol_1043 {
    static ArrayList<ArrayList<Integer>> party;
    static ArrayList<HashSet<Integer>> person;
    static HashSet<Integer> knowsTruthPerson;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int knowsTruthCnt = Integer.parseInt(st.nextToken());
        knowsTruthPerson = new HashSet<>();
        ArrayList<Integer> firstKnowsTruthPerson = new ArrayList<>();
        if (knowsTruthCnt != 0) {
            for (int kt = 0; kt < knowsTruthCnt; kt++) {
                int personNum = Integer.parseInt(st.nextToken());
                firstKnowsTruthPerson.add(personNum);
                knowsTruthPerson.add(personNum);
            }
        }
        person = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            person.add(new HashSet<>());
        }
        party = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            party.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            int personCnt = Integer.parseInt(st.nextToken());
            int[] partyPeople = new int[personCnt];
            for (int pc = 0; pc < personCnt; pc++) {
                partyPeople[pc] = Integer.parseInt(st.nextToken());
                party.get(i).add(partyPeople[pc]);
            }
            for (int j = 0; j < personCnt; j++) {
                for (int k = 0; k < personCnt; k++) {
                    if (j != k) {
                        if (!person.get(partyPeople[j]).contains(partyPeople[k]))
                            person.get(partyPeople[j]).add(partyPeople[k]);
                        if (!person.get(partyPeople[k]).contains(partyPeople[j]))
                            person.get(partyPeople[k]).add(partyPeople[j]);
                    }
                }
            }
        }
        visited = new boolean[N + 1];
        for (int fkt : firstKnowsTruthPerson) {
            if (!visited[fkt]) spreadTruth(fkt);
        }
        int cnt = 0;
        for (int i = 0; i < M; i++) {
            boolean overCheck = false;
            ArrayList<Integer> partyPeople = party.get(i);
            for (int k : partyPeople) {
                if (knowsTruthPerson.contains(k)) {
                    overCheck = true;
                    break;
                }
            }
            if (!overCheck) cnt++;
        }
        System.out.println(cnt);
    }

    static void spreadTruth(int start) {
        Iterator iter = person.get(start).iterator();
        while (iter.hasNext()) {
            int nextPerson = (int) iter.next();
            if (!visited[nextPerson]) {
                visited[nextPerson] = true;
                if (!knowsTruthPerson.contains(nextPerson)) knowsTruthPerson.add(nextPerson);
                spreadTruth(nextPerson);
            }
        }
    }
}
