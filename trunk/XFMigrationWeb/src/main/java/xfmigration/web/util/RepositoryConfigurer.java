package xfmigration.web.util;

/**
 * Class to hold data regarding configuration details.
 */
public class RepositoryConfigurer {

    /**
     * Ontology repository path.
     */
	private String repositoryPath;
	
	/**
	 * test data directory name.
	 */
	private String testdataDirName;
	
	/**
	 * directory name to store semantic web services definition files.
	 */
	private String swsDirName;
	
	/**
	 * Getter method for repository path.
	 * @return String value of repository path.
	 */
	public String getRepositoryPath() {
		return repositoryPath;
	}
	
	/**
	 * Setter method for repository path.
	 * @param path A String value of a path.
	 */
	public void setRepositoryPath(String path) {
		repositoryPath = path.trim();
	}

	/**
	 * Setter method for test data directory name.
	 * @param testdataDirectoryName {@link String} value of test data directory name.
	 */
	public void setTestdataDirName(String testdataDirectoryName) {
		this.testdataDirName = testdataDirectoryName;
	}

	/**
	 * Getter method for test data directory name.
	 * @return String value of test data direcotry name.
	 */
	public String getTestdataDirName() {
		return testdataDirName;
	}

	/**
	 * Setter method for SWS directory name.
	 * @param swsDirectoryName String value of SWS directory name.
	 */
	public void setSwsDirName(String swsDirectoryName) {
		this.swsDirName = swsDirectoryName;
	}

	/**
	 * Getter method for SWS directory name.
	 * @return String value of SWS directory name.
	 */
	public String getSwsDirName() {
		return swsDirName;
	}
	
}
