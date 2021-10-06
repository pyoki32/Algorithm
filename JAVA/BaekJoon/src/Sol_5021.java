import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Sol_5021 {
    static class Family {
        String[] parents;
        double lineage;
        ArrayList<String> children;

        public Family(String[] parents, double lineage, ArrayList<String> children) {
            this.parents = parents;
            this.lineage = lineage;
            this.children = children;
        }

        public void inputChild(String child) {
            this.children.add(child);
        }

        public void inputParents(String[] parents) {
            this.parents = parents;
        }

        public void inputLineage(double blood) {
            this.lineage += blood;
        }
    }

    static HashMap<String, Family> tree = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String king = br.readLine();
        for (int i = 0; i < N; i++) {
            String[] parents = new String[2];
            st = new StringTokenizer(br.readLine());
            String child = st.nextToken();
            parents[0] = st.nextToken();
            parents[1] = st.nextToken();

            if (!tree.containsKey(child)) {
                tree.put(child, new Family(parents, 0, new ArrayList<String>()));

            } else {
                tree.get(child).inputParents(parents);
            }
            if (!tree.containsKey(parents[0])) {
                ArrayList<String> p1Child = new ArrayList<>();
                p1Child.add(child);
                tree.put(parents[0], new Family(new String[2], 0, p1Child));
            } else {
                tree.get(parents[0]).inputChild(child);
            }
            if (!tree.containsKey(parents[1])) {
                ArrayList<String> p2Child = new ArrayList<>();
                p2Child.add(child);
                tree.put(parents[1], new Family(new String[2], 0, p2Child));
            } else {
                tree.get(parents[1]).inputChild(child);
            }
        }
        tree.get(king).inputLineage(1);
        spreadBlood(king, 1);
        String answerName = "";
        double maxLineage = -1;
        for (int i = 0; i < M; i++) {
            String name = br.readLine();
            if (tree.containsKey(name)) {
                if (maxLineage < tree.get(name).lineage) {
                    answerName = name;
                    maxLineage = tree.get(name).lineage;
                }
            }
        }
        System.out.println(answerName);
    }

    static void spreadBlood(String name, double blood) {

        ArrayList<String> children = tree.get(name).children;
        if (!children.isEmpty()) {
            for (String child : children) {
                tree.get(child).inputLineage(blood / 2);
                spreadBlood(child, blood / 2);
            }
        }
        return;
    }
}
