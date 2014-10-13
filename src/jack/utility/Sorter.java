package jack.utility;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Sorter {

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(
			Map<K, V> map, boolean ascend) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(
				map.entrySet());
		if (ascend) {
			Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
				public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
					// ascend order
					return (o1.getValue()).compareTo(o2.getValue());
				}
			});
		} else {
			Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
				public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
					// ascend order
					return (o2.getValue()).compareTo(o1.getValue());
				}
			});
		}

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	/**
	 * sort entries in map
	 * 
	 * @param map
	 * @param byKey
	 *            true:sort by key; false:sort by value;
	 * @param ascend
	 *            true:sort by ascend order; false: sort by descend order;
	 * @return
	 */
	public static <K extends Comparable<? super K>, V extends Comparable<? super V>> Map<K, V> sort(
			Map<K, V> map, boolean byKey, boolean ascend) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(
				map.entrySet());
		if (byKey && ascend) {
			Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
				public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
					// ascend order
					return (o1.getKey()).compareTo(o2.getKey());
				}
			});
		} else if (byKey && !ascend) {
			Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
				public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
					// descend order
					return (o2.getKey()).compareTo(o1.getKey());
				}
			});
		} else if (!byKey && ascend) {
			Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
				public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
					// ascend order
					return (o1.getValue()).compareTo(o2.getValue());
				}
			});
		} else if (!byKey && !ascend) {
			Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
				public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
					// descend order
					return (o2.getValue()).compareTo(o1.getValue());
				}
			});
		}

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public static <K, V> void print(Map<K, V> map) {
		for (Map.Entry<K, V> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "\t" + entry.getValue());
		}
	}

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("a", 10);
		map.put("b", 8);
		map.put("d", 8);
		map.put("f", 55);

		map = Sorter.sort(map, true, true);
		Sorter.print(map);
		System.out.println("==========================");

		map = Sorter.sort(map, true, false);
		Sorter.print(map);
		System.out.println("==========================");

		map = Sorter.sort(map, false, true);
		Sorter.print(map);
		System.out.println("==========================");

		map = Sorter.sort(map, false, false);
		Sorter.print(map);
	}

}
