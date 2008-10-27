package xfmigration.exporter;

import xfmigration.domain.Forge;

/**
 * A factory class to provide implementations of IProjectExporter interface.
 */
public final class ExporterFactory {

    /**
     * A default constructor.
     */
    private ExporterFactory() {
    }

    /**
     * A method to provide an implementation of {@link IProjectExporter} as
     * {@link DataExporterImpl}.
     * 
     * @param destForge
     *            A mapping destination forge.
     * @return An implementation of {@link IProjectExporter}.
     */
    public static IProjectExporter createDataExporter(Forge destForge) {
        return new DataExporterImpl(destForge);
    }
}
