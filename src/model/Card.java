/**
 * Used to store card information. currently only stores name, description, and number of copies in 
 * the deck
 */

package model;

/**
 * 
 */
public class Card {

	/** name of the card */
	private String name;
	
	/** description of the card or card effect */
	private String description;
	
	/** the number of copies of the card in the deck */
	private int copies;
	
	/**
	 * constructs a card from the provided parameters. whitespace are removed from the beginning of 
	 * name and description 
	 *  
	 * @param name the name to set, cannot be empty or only whitespace
	 * @param description the description to set, cannot be empty or only whitespace
	 * @param copies the copies to set, cannot be zero or negative
	 * 
	 * @throws Exception if any of the value validations fail
	 */
	public Card(String name, String description, int copies) throws Exception {
		this.setName(name);
		this.setDescription(description);
		this.setCopies(copies);
	}
	
	/**
	 * constructs a card from the provided parameters. whitespace are removed from the beginning of 
	 * name and description. copies is set to 1
	 * 
	 * @param name the name to set, cannot be empty or only whitespace
	 * @param description the description to set, cannot be empty or only whitespace
	 * 
	 * @throws Exception if any of the value validations fail
	 */
	public Card(String name, String description) throws Exception {
		this(name,description,1);
	}
	
	/**
	 * constructs a card from the provided parameters. whitespace are removed from the beginning of 
	 * name. Description is set to an empty string
	 * 
	 * @param name the name to set, cannot be empty or only whitespace
	 * @param copies the copies to set, cannot be zero or negative
	 * 
	 * @throws Exception if any of the value validations fail
	 */
	public Card(String name, int copies) throws Exception{
		this(name,"", copies);
	}
	
	
	/**
	 * constructs a card from the provided parameters. whitespace are removed from the beginning of 
	 * name. Description is set to an empty string and copies is set to 1
	 * 
	 * @param name the name to set, cannot be empty or only whitespace
	 * 
	 * @throws Exception if any of the value validations fail
	 */
	public Card(String name) throws Exception {
		this(name,"",1);
	}
	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the description 
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @return the number of copies
	 */
	public int getCopies() {
		return copies;
	}

	/**
	 * sets the value of class name to name, removing whitespace from beginning and end
	 * 
	 * @param name the name to set
	 * @throws Exception if name is empty or only whitespace
	 */
	public void setName(String name) throws Exception {
		
		String trimmedName = name.trim();
		
		if (trimmedName.isEmpty()) {
			throw new Exception("Name cannot be empty or only whitespace");
		} else {
			this.name = name;
		}		
	}
	
	/**
	 * sets the value of description, removing whitespace from beginning and end
	 * unlike name, description can be empty
	 * 
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description.trim();
	}
	
	/**
	 * @param count the number of copies to set
	 * @throws Exception if integer is zero or negative
	 */
	public void setCopies(int copies) throws Exception {
		if (copies > 0) {
			this.copies = copies;
		}
		else {
			throw new Exception("Copies must be an integer over 0");
		}
	}

}
