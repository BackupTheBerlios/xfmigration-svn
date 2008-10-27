package xfmigration.dao;

import java.util.List;
import xfmigration.domain.Mapping;

/**
 * An interface to define method for accessing/persisting mapping related data.
 */
public interface IMappingDao extends IGenericDao<Mapping, Integer> {

	/**
	 * query for retrieving mapping objects by source forge id.
	 */
	String QUERY_MAPPINGS_BY_SRC_FORGE_ID = "from Mapping m where m.srcForge.id=?";

	/**
	 * query for retrieving mapping objects by source forge uri.
	 */
	String QUERY_MAPPING_BY_SRC_FORGE_URI = "";

	/**
	 * query for retrieving mapping objects by mapping url.
	 */
	String QUERY_MAPPING_BY_URL = "from Mapping m where m.mappingURL=?";

	/**
	 * query for retrieving mapping objects by source and destination forges'
	 * IDs.
	 */
	String QUERY_MAPPING_BY_SRC_AND_DEST_FORGE_ID = "from Mapping m "
			+ " where m.srcForge.id=? and m.destForge.id=?";

	/**
	 * Retrieves a List of Mapping objects of a given source forge id.
	 * 
	 * @param srcForgeId
	 *            source forge id.
	 * @return a List of Mapping objects of a given source forge id.
	 */
	List<Mapping> findMappings(Integer srcForgeId);

	/**
	 * Retrieves a Mapping object by a mapping url.
	 * 
	 * @param mappingUrl
	 *            Mapping url.
	 * @return A Mapping object with a given url.
	 */
	Mapping findByUrl(String mappingUrl);

	/**
	 * Retrieves a List of Mapping objects for a given source and destination
	 * forge IDs.
	 * 
	 * @param srcForgeId
	 *            source forge id.
	 * @param destForgeId
	 *            destination forge id.
	 * @return A List of {@link Mapping} objects.
	 */
	List<Mapping> findMappings(Integer srcForgeId, Integer destForgeId);

}
