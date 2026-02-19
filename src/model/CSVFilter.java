/**
 * 
 */
package model;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * 
 */
public class CSVFilter extends FileFilter {

	@Override
	public boolean accept(File file) {
		if (file.isDirectory()) {
			return true;	// allows users to navigate file system
		}
		String extention = Utils.getExtention(file);
		
		if (!extention.isBlank()) {
			if (extention.equals(Utils.csv)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getDescription() {
		return "CSV file";
	}

}
