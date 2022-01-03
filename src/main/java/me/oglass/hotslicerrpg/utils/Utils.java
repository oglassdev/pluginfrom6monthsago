package me.oglass.hotslicerrpg.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Utils {

	private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();
	
	public static String chat (String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	public static String[] chat (String[] s) {
		List<String> arr = new ArrayList<String>();
		for (String str : s) {
			arr.add(ChatColor.translateAlternateColorCodes('&', str));
		}
		return arr.toArray(new String[0]);
	}

	static {

		map.put(1000, "M");
		map.put(900, "CM");
		map.put(500, "D");
		map.put(400, "CD");
		map.put(100, "C");
		map.put(90, "XC");
		map.put(50, "L");
		map.put(40, "XL");
		map.put(10, "X");
		map.put(9, "IX");
		map.put(5, "V");
		map.put(4, "IV");
		map.put(1, "I");

	}

	public final static String toRoman(int number) {
		int l =  map.floorKey(number);
		if ( number == l ) {
			return map.get(number);
		}
		return map.get(l) + toRoman(number-l);
	}
}
