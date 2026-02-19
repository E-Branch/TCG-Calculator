package model;

import java.io.File;

public class Utils {
	public static final String csv = "csv";
	public static final String json = "json";
	
	public static String getExtention(File f) {
		String filename = f.getName();
		int i = filename.lastIndexOf(".");
		
		if (i > 0 && i < filename.length()-1) {
			return filename.substring(i+1).toLowerCase();
		}
		return "";
	}
}
