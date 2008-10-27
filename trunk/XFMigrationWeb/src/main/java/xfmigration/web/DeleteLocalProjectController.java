package xfmigration.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import xfmigration.migration.IRepositoryService;

/**
 * A controller to serve deleting of local project meta data registry. 
 */
public class DeleteLocalProjectController implements Controller {

	/**
     * A reference to an implementation of {@link IRepositoryService}.
     */
    private IRepositoryService repositoryService;

    /**
     * A view name where to redirect after success.
     */
    private String redirectViewName;

    /**
     * An implementation to handle a request.
     * 
     * @param request
     *            a {@link HttpServletRequest} object reference.
     * @param response
     *            a {@link HttpServletResponse} object reference.
     *            @return An instance of {@link ModelAndView}.
     *            @throws Exception A processing exception.
     */
    @SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	Map model = new ModelMap();
        if (request.getParameter("name") != null && request.getParameter("forgeId") != null) {
            String localProjName = request.getParameter("name");
            int forgeId = Integer.valueOf(request.getParameter("forgeId"));
            getRepositoryService().deleteLocalProjectEntry(localProjName, forgeId);
            model.put("id", forgeId);
        }
        return new ModelAndView(getRedirectViewName(), model);
    }

    /**
     * A setter method for repositoryService field.
     * 
     * @param repoService
     *            A reference to {@link IRepositoryService} implementation.
     */
    public void setRepositoryService(IRepositoryService repoService) {
        this.repositoryService = repoService;
    }

    /**
     * A getter method of repositoryService.
     * 
     * @return An implementation of {@link IRepositoryService}.
     */
    public IRepositoryService getRepositoryService() {
        return repositoryService;
    }

    /**
     * A setter method of redirectViewName field.
     * 
     * @param redirectViewNameValue
     *            String value of a view name, where redirected after successful
     *            handleRequest action.
     */
    public void setRedirectViewName(String redirectViewNameValue) {
        this.redirectViewName = redirectViewNameValue;
    }

    /**
     * A getter method of redirect view name.
     * 
     * @return A String value of redirect view name.
     */
    public String getRedirectViewName() {
        return redirectViewName;
    }
	
}
