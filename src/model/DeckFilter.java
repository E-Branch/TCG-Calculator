/**
 * 
 */
package model;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * 
 */
public class DeckFilter extends FileFilter {

	/**
	 * 
	 */
	public DeckFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean accept(File file) {
		if (file.isDirectory()) {
			return true;	// allows users to navigate file system
		}
		String extention = Utils.getExtention(file);
		
		if (!extention.isBlank()) {
			if (extention.equals(Utils.csv) || extention.equals(Utils.json)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getDescription() {
		return "CSV or JSON file";
	}

}

