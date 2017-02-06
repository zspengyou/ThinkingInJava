package io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
//: io/DirList.java
// Display a directory listing using regular expressions.
// {Args: "D.*\.java"}
import java.util.regex.Pattern;

public class DirList {
	public static void main(String[] args) {
		File path = new File(".");
		args = new String []{".+txt"};
		String[] list;
		if (args.length == 0)
			list = path.list();
		else
			list = path.list(new DirFilter(args[0]));
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		for (String dirItem : list)
			System.out.println(dirItem);
	}
}

class DirFilter implements FilenameFilter {
	private Pattern pattern;

	public DirFilter(String regex) {
		pattern = Pattern.compile(regex);
	}
	@Override
	public boolean accept(File dir, String name) {
		System.out.println(dir.toString());
		System.out.println(name);
		return pattern.matcher(name).matches();
	}
} /*
	 * Output: DirectoryDemo.java DirList.java DirList2.java DirList3.java
	 */// :~
