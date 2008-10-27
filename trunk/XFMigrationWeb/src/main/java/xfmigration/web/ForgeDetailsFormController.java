package xfmigration.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import xfmigration.domain.Forge;
import xfmigration.migration.IRepositoryService;
import xfmigration.web.util.ForgeDetailsForm;

/**
 * A controller to handle form for forge details update.
 */
public class ForgeDetailsFormController extends SimpleFormController {

    /**
     * A reference to {@link IRepositoryService} implementation.
     */
    private IRepositoryService repositoryService;

    /**
     * Returns a form backing bean.
     * 
     * @param request
     *            Incoming {@link HttpServletRequest}.
     * @return An instance of {@link ForgeDetailsForm}.
     * @throws Exception
     *             exception
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {

        ForgeDetailsForm formBean = null;
        if (request.getParameter("id") != null) {
            String forgeIdStr = request.getParameter("id");
            int forgeId = Integer.valueOf(forgeIdStr);
            Forge forge = getRepositoryService().getForgeById(forgeId, false);
            formBean = new ForgeDetailsForm(forge);
        }
        return formBean;
    }

    /**
     * Method to provide model data to a form.
     * 
     * @param request
     *            Incoming {@link HttpServletRequest}.
     * @return An instance of {@link ModelMap}.
     * @throws Exception
     *             exception
     */
    @SuppressWarnings("unchecked")
    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map model = new ModelMap();
        String forgeIdStr = request.getParameter("id");
        List<String> projectsNames = getRepositoryService().getForgeLocalProjects(
                Integer.valueOf(forgeIdStr));
        model.put("projectsNames", projectsNames);
        return model;
    }

    /**
     * A method to register a byte array binder for files uploading.
     * @param request HTTP request reference.
     * @param binder A request binder reference.
     * @throws Exception exception while registering.
     */
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
            throws Exception {
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }

    /**
     * Overrides an onSubmit action.
     * 
     * @param request
     *            Incoming {@link HttpServletRequest}.
     * @param response
     *            Outgoing {@link HttpServletResponse}.
     * @param command
     *            Form backing bean object.
     * @param errors
     *            Errors.
     * @return An instance of {@link ModelAndView}.
     * @throws Exception
     *             exception
     */
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {

        ForgeDetailsForm cmd = (ForgeDetailsForm) command;
        Forge forge = cmd.getForge();
        getRepositoryService().updateForge(forge);
        /*
         * getRepositoryService().updateRepositoryEntry(forge,
         * cmd.getImpDataOwls(), cmd.getExpIndividualsOwlsSeq(),
         * cmd.getExpPropertiesOwlsSeq());
         */
        return new ModelAndView(getSuccessView());
    }

    /**
     * A setter method of {@link IRepositoryService} implementation.
     * 
     * @param repoService
     *            a reference to {@link IRepositoryService} implementation.
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
