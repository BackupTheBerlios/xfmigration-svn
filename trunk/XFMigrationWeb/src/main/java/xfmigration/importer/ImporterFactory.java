package xfmigration.importer;

import xfmigration.domain.Forge;

/**
 * A Factory class to obtain an implementation of {@link IProjectImporter}.
 */
public final class ImporterFactory {

	/**
	 * A private default constructor of an utility class.
	 */
	private ImporterFactory() {
	}
	
    /**
     * A method to obtain an implementation of {@link IProjectImporter}.
     * 
     * @param srcForge
     *            A mapping source forge reference.
     * @param repositoryPath
     *            a repository path.
     * @return An implementation of {@link IProjectImporter}.
     */
    public static IProjectImporter createProjectImporter(Forge srcForge, String repositoryPath) {
        return new ProjectImporterImpl(srcForge, repositoryPath);
    }
}
