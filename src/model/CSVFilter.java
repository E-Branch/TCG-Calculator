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
		//String extension = Utils.getExtention(file);
		
		if (!Utils.extensionIsBlank(file)) {
			if (Utils.isCSV(file)) {
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
