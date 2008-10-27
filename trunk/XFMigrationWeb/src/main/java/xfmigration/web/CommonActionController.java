package xfmigration.web;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import xfmigration.domain.Forge;
import xfmigration.domain.Mapping;
import xfmigration.migration.IRepositoryService;

/**
 * An extension of MultiActionController to handle common requests.
 */
public class CommonActionController extends MultiActionController {

    /**
     * A reference to {@link IRepositoryService} implementation.
     */
    private IRepositoryService repositoryService;

    /**
     * request handling.
     * 
     * @param request
     *            Incoming {@link HttpServletRequest}.
     * @param response
     *            Outgoing {@link HttpServletResponse}.
     * @return A reference to {@link ModelAndView} instance.
     */
    public ModelAndView mainframeRedirectHandle(HttpServletRequest request,
            HttpServletResponse response) {
        return new ModelAndView("common/mainframe");
    }

    /**
     * request handling.
     * 
     * @param request
     *            Incoming {@link HttpServletRequest}.
     * @param response
     *            Outgoing {@link HttpServletResponse}.
     * @return A reference to {@link ModelAndView} instance.
     */
    public ModelAndView menuViewHandle(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("common/menu");
    }

    /**
     * request handling.
     * 
     * @param request
     *            Incoming {@link HttpServletRequest}.
     * @param response
     *            Outgoing {@link HttpServletResponse}.
     * @return A reference to {@link ModelAndView} instance.
     */
    public ModelAndView headerViewHandle(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("common/header");
    }

    /**
     * request handling.
     * 
     * @param request
     *            Incoming {@link HttpServletRequest}.
     * @param response
     *            Outgoing {@link HttpServletResponse}.
     * @return A reference to {@link ModelAndView} instance.
     */
    public ModelAndView mainViewHandle(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("common/main");
    }

    /**
     * request handling.
     * 
     * @param request
     *            Incoming {@link HttpServletRequest}.
     * @param response
     *            Outgoing {@link HttpServletResponse}.
     * @return A reference to {@link ModelAndView} instance.
     */
    @SuppressWarnings("unchecked")
    public ModelAndView forgesViewHandle(HttpServletRequest request, HttpServletResponse response) {
        Map model = new ModelMap();
        List<Forge> forges = getRepositoryService().getForges();
        model.put("forges", forges);
        return new ModelAndView("forges/forges", model);
    }

    /**
     * request handling.
     * 
     * @param request
     *            Incoming {@link HttpServletRequest}.
     * @param response
     *            Outgoing {@link HttpServletResponse}.
     * @return A reference to {@link ModelAndView} instance.
     */
    @SuppressWarnings("unchecked")
    public ModelAndView mappingsViewHandle(HttpServletRequest request,
            HttpServletResponse response) {
        Map model = new ModelMap();
        List<Mapping> mappings = getRepositoryService().getMappings();
        model.put("mappings", mappings);
        return new ModelAndView("mappings/mappings", model);
    }

    /**
     * Setter of repositoryService field.
     * 
     * @param repoService
     *            An implementation of {@link IRepositoryService}.
     */
    public void setRepositoryService(IRepositoryService repoService) {
        this.repositoryService = repoService;
    }

    /**
     * Getter of {@link IRepositoryService} implementation.
     * 
     * @return A reference to an {@link IRepositoryService} implementation.
     */
    public IRepositoryService getRepositoryService() {
        return repositoryService;
    }
}
