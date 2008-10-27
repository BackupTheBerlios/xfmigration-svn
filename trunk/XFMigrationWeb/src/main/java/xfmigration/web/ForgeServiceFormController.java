package xfmigration.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import xfmigration.domain.Service;
import xfmigration.migration.IRepositoryService;
import xfmigration.web.util.ForgeServiceForm;

/**
 * A Controller to handle Forge service form.
 */
public class ForgeServiceFormController extends SimpleFormController {

    /**
     * A forge id parameter name.
     */
    private static final String FORGE_ID_PARAM_NAME = "id";

    /**
     * A reference to {@link IRepositoryService} implementation.
     */
    private IRepositoryService repositoryService;

    /**
     * {@inheritDoc}
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {

        ForgeServiceForm bean = new ForgeServiceForm();

        if (request.getParameter(FORGE_ID_PARAM_NAME) != null) {
            String forgeId = request.getParameter(FORGE_ID_PARAM_NAME);
            int id = Integer.valueOf(forgeId);
            bean.setForgeId(id);
        }
        return bean;
    }
    
    /*protected void initBinder(HttpServletRequest request,
    		ServletRequestDataBinder binder) throws Exception {
    	binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }
*/
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {

        ForgeServiceForm cmd = (ForgeServiceForm) command;
        if (cmd.getForgeId() > 0) {
            Service service = new Service(cmd.getServiceName(), cmd.getServicePath(), cmd
                    .getForgeId(), cmd.getDescription());
            getRepositoryService().updateForgeService(cmd.getForgeId(), service);
//          getRepositoryService().addRepositoryService(f, service, cmd.getServiceOwls());
        }
        
        ModelMap model = new ModelMap();
        model.put(FORGE_ID_PARAM_NAME, cmd.getForgeId());

        return new ModelAndView(getSuccessView(), model);
    }

    /**
     * A setter method of {@link IRepositoryService} implementation.
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

}
