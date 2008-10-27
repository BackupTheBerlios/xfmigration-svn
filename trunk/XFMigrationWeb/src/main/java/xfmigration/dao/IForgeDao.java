package xfmigration.dao;

import java.util.List;
import xfmigration.domain.Forge;

/**
 * An interface to define method for accessing/persisting Forge data.
 */
public interface IForgeDao extends IGenericDao<Forge, Integer> {

    /**
     * query for retrieving a forge object of a given ontology URI.
     */
    String QUERY_FORGE_BY_ONT_URI = "from Forge f where f.ontologyUri=?";

    /**
     * query for retrieving a forge object of a given name.
     */
    String QUERY_FORGE_BY_NAME = "from Forge f where f.forgeName=?";

    /**
     * query for retrieving mapping destination forges from a given source
     * forge.
     */
    String QUERY_FORGES_BY_SRC_FORGE_ID = "from Forge f where f.id in"
            + " (select m.destForge.id from Mapping m where m.srcForge.id=?)";

    /**
     * A method to find a forge by uri.
     * 
     * @param ontologyURI
     *            forge ontology uri.
     * @return an instance of Forge with a given uri.
     */
    Forge findByUri(String ontologyURI);

    /**
     * A method to find a forge by name.
     * 
     * @param forgeName
     *            forge name
     * @return and instance of Forge with a given name.
     */
    Forge findByName(String forgeName);

    /**
     * A method to find destination forges for a given source forge according to
     * registered mappings.
     * 
     * @param srcForgeId
     *            Id of Forge.
     * @return a List of Destination Forge objects which based on defined
     *         mappings.
     */
    List<Forge> findDestinationForges(Integer srcForgeId);
}
