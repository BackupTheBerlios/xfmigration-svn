package xfmigration.web.util;

/**
 * A class to represent a backing bean of a controller forregistering a local project metadata.
 */
public class RegisterLocalProjectForm {

    /**
     * Forge id.
     */
	private int forgeId;
	
	/**
	 * project name.
	 */
	private String projectName;
	
	/**
	 * A byte array representation of uploaded file.
	 */
	private byte[] projectFileData;
	
	/**
	 * A constructor.
	 * @param fId Forge id.
	 */
	public RegisterLocalProjectForm(int fId) {
		forgeId = fId;
	}
	
	/**
	 * Setter method for forge id.
	 * @param forgeEntityId value of forge id.
	 */
	public void setForgeId(int forgeEntityId) {
		this.forgeId = forgeEntityId;
	}
	
	/**
	 * Getter method for forge id.
	 * @return value of forge id.
	 */
	public int getForgeId() {
		return forgeId;
	}
	
	/**
	 * Setter method for project name.
	 * @param name value of project name.
	 */
	public void setProjectName(String name) {
		this.projectName = name;
	}
	
	/**
	 * Getter method for project name.
	 * @return String value of project name.
	 */
	public String getProjectName() {
		return projectName;
	}
	
	/**
	 * Setter method for project file data byte array representation.
	 * @param file byte array file representation.
	 */
	public void setProjectFileData(byte[] file) {
		this.projectFileData = file;
	}
	
	/**
	 * Getter method for project file data byte array representation.
	 * @return A byte array of project metadata file.
	 */
	public byte[] getProjectFileData() {
		return projectFileData;
	}
}
