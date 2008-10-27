package xfmigration.domain;

/**
 * An interface of a forge.
 * 
 */
public interface IForge {
    /**
     * Getter of a forge ontology uri.
     * 
     * @return ontology uri.
     */
    String getOntologyUri();

    /**
     * Getter of a forge ontology url.
     * 
     * @return ontology url.
     */
    String getOntologyUrl();

    /**
     * Getter of a forge wsdl url.
     * 
     * @return a forge wsdl url.
     */
    String getWsdlUrl();

    /**
     * Getter of a forge owls url.
     * 
     * @return a forge owls url.
     */
    String getOwlsUrl();

    /**
     * Getter for a home directory name.
     * 
     * @return Home directory name.
     */
    String getHomeDir();
}
