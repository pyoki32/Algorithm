
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BaekJoon_9935 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String boom = br.readLine();

		Stack<Character> stack = new Stack<>();
		Stack<Character> tempStack = new Stack<>();
		char boom_end = boom.charAt(boom.length() - 1);
		for (int i = 0; i < str.length(); i++) {
			stack.push(str.charAt(i));
			if (stack.peek() == boom_end && stack.size() >= boom.length()) {
				boolean findBoom = true;
				for (int j = boom.length() - 1; j >= 0; j--) {
					char temp = stack.pop();
					tempStack.push(temp);
					if (boom.charAt(j) != temp) {
						findBoom = false;
						break;
					}
				}
				
				if (!findBoom) {
					while (!tempStack.isEmpty()) {
						stack.push(tempStack.pop());
					}
				} else {
					tempStack.clear();
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (Character ch : stack) {
			sb.append(ch);
		}
		if(stack.size() ==0) {
			System.out.println("FRULA");
		}else
		System.out.println(sb);
	}

}
