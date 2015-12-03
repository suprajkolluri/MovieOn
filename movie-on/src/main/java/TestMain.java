import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TestMain {

	public static void main(String[] args) {
		String str = "dir1\n dir11\n dir12\n  picture.jpeg\n  dir121\n  file1.txt\ndir2\n file2.gif";
		solution(str);
	}

	public static void solution(String S) {
		Map<String, List<Integer>> map = new LinkedHashMap<String, List<Integer>>();
		String lineArr[] = S.split("\\r?\\n");
		int i = 0;
		for (String s : lineArr) {
			int spaceCount = spaceCount(s);
			List<Integer> list = new ArrayList<Integer>();
			list.add(++i);
			list.add(spaceCount);
			map.put(s, list);
		}

		for(Entry<String, List<Integer>> e: map.entrySet()){
			System.out.println(e.getKey());
			System.out.println(e.getValue());
		}
	}

	public static int spaceCount(String str) {
		int spaceCount = 0;
		for (char c : str.toCharArray()) {
			if (c == ' ') {
				spaceCount++;
			}
		}
		return spaceCount;
	}
}
