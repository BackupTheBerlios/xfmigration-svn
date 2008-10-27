package xfmigration.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import xfmigration.migration.IRepositoryService;

/**
 * A Controller to handle delete forge service request.
 */
public class DeleteServiceController implements Controller {

    /**
     * Service id parameter name.
     */
    private static final String SERVICE_ID_PARAM_NAME = "id";

    /**
     * Forge id parameter name.
     */
    private static final String FORGE_ID_PARAM_NAME = "id";

    /**
     * A reference to {@link IRepositoryService} implementation.
     */
    private IRepositoryService repositoryService;

    /**
     * A success view name.
     */
    private String successView;

    /**
     * A method to handle delete forge service request.
     * 
     * @param request
     *            A {@link HttpServletRequest} object reference.
     * @param response
     *            A {@link HttpServletResponse} object reference.
     * @return An instance of {@link ModelAndView}.
     * @throws Exception Processing exception.
     */
    @SuppressWarnings("unchecked")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModelMap model = new ModelMap();

        if (request.getParameter(SERVICE_ID_PARAM_NAME) != null) {

            String serviceId = request.getParameter(SERVICE_ID_PARAM_NAME);
            int id = Integer.valueOf(serviceId);

            int parentForgeId = getRepositoryService().deleteForgeService(id);
            model.put(FORGE_ID_PARAM_NAME, parentForgeId);
        }
        return new ModelAndView(getSuccessView(), model);
    }

    /**
     * A setter method of repositoryService reference.
     * 
     * @param repoService
     *            A reference to {@link IRepositoryService} implementation.
     */
    public void setRepositoryService(IRepositoryService repoService) {
        this.repositoryService = repoService;
    }

    /**
     * A getter method of {@link IRepositoryService} implementation.
     * 
     * @return A reference to {@link IRepositoryService} implementation.
     */
    public IRepositoryService getRepositoryService() {
        return repositoryService;
    }

    /**
     * A setter method of success view name.
     * 
     * @param successViewName
     *            String value of success view name.
     */
    public void setSuccessView(String successViewName) {
        this.successView = successViewName;
    }

    /**
     * A getter method of success view name.
     * 
     * @return A String value of a success view name.
     */
    public String getSuccessView() {
        return successView;
    }

}
