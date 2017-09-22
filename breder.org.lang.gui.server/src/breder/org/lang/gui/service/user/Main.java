package breder.org.lang.gui.service.user;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static List<List<Integer>> execute(int pages) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		int p = pages / 4;
		if (pages % 4 != 0) {
			p++;
		}
		for (int n = 0; n < p; n++) {
			ArrayList<Integer> arrayList = new ArrayList<Integer>(4);
			list.add(arrayList);
			for (int m = 0; m < 4; m++) {
				arrayList.add(0);
			}
		}
		int page = 1;
		{
			int index = 0;
			for (int n = 0; n < p; n++) {
				if (page <= pages) {
					list.get(index++).set(1, page++);
				}
			}
		}
		{
			int index = 0;
			for (int n = 0; n < p; n++) {
				if (page <= pages) {
					list.get(index++).set(3, page++);
				}
			}
		}
		{
			int index = p - 1;
			for (int n = 0; n < p; n++) {
				if (page <= pages) {
					list.get(index--).set(2, page++);
				}
			}
		}
		{
			int index = p - 1;
			for (int n = 0; n < p; n++) {
				if (page <= pages) {
					list.get(index--).set(0, page++);
				}
			}
		}
		for (int n = 0; n < p / 2; n++) {
			{
				Integer aux = list.get(n * 2 + 1).get(0);
				list.get(n * 2 + 1).set(0, list.get(n * 2 + 1).get(1));
				list.get(n * 2 + 1).set(1, aux);
			}
			{
				Integer aux = list.get(n * 2 + 1).get(2);
				list.get(n * 2 + 1).set(2, list.get(n * 2 + 1).get(3));
				list.get(n * 2 + 1).set(3, aux);
			}
		}
		return list;
	}

	public static void main(String[] args) {
		List<List<Integer>> list = execute(94);
		for (int n = 0; n < list.size() / 2; n++) {
			System.out.println(list.get(n * 2).toString()
					+ list.get(n * 2 + 1).toString());
		}
	}

}
