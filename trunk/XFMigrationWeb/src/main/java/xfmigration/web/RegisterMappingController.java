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
import xfmigration.migration.IXFMigrationService;
import xfmigration.web.util.RegisterMappingForm;

/**
 * A controller which serves actions regarding mapping registration form.
 */
public class RegisterMappingController extends SimpleFormController {

    /**
     * A reference to {@link IXFMigrationService} impl.
     */
    private IXFMigrationService migrationService;

    /**
     * A reference to {@link IRepositoryService} impl.
     */
    private IRepositoryService repositoryService;

    /**
     * Overrides a standard formBackingObject method from
     * {@link SimpleFormController}.
     * 
     * @param request
     *            Incoming {@link HttpServletRequest}.
     * @return An instance of {@link RegisterMappingForm}.
     * @throws Exception
     *             exception
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return new RegisterMappingForm();
    }

    /**
     * Overrides a standard referenceData method from
     * {@link SimpleFormController}.
     * 
     * @param request
     *            Incoming {@link HttpServletRequest}.
     * @return A Map of data to be referenced in a view.
     * @throws Exception
     *             exception
     * 
     */
    @SuppressWarnings("unchecked")
    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map model = new ModelMap();
        List<Forge> forges = getRepositoryService().getForges();
        model.put("forges", forges);
        return model;
    }

    /**
     * A method to register a byte array binder.
     * 
     * @param request
     *            HTTP request reference.
     * @param binder
     *            Request binder reference.
     * @throws Exception
     *             thrown if execution failure occurs.
     */
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
            throws Exception {
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }

    /**
     * Overrides standard onSubmit method from {@link SimpleFormController}.
     * 
     * @param request
     *            Incoming {@link HttpServletRequest}.
     * @param response
     *            Outgoing {@link HttpServletResponse}.
     * @param command
     *            A reference to {@link RegisterMappingForm} instance.
     * @param errors
     *            An errors object.
     * @return An instance of {@link ModelAndView}.
     * @throws Exception
     *             exception
     */
    @SuppressWarnings("unchecked")
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {
        Map map = errors.getModel();
        RegisterMappingForm cmd = (RegisterMappingForm) command;
        getMigrationService().registerMapping(cmd.getMappingName(), cmd.getSrcForgeName(),
                cmd.getDestForgeName(), cmd.getDescription(), cmd.getMappingName(),
                cmd.getMappingFile());

        return new ModelAndView(getSuccessView(), map);

    }

    /**
     * Setter method of migrationService field.
     * 
     * @param migrationServRef
     *            A reference to {@link IXFMigrationService} impl.
     */
    public void setMigrationService(IXFMigrationService migrationServRef) {
        this.migrationService = migrationServRef;
    }

    /**
     * Getter method of migrationService field.
     * 
     * @return A reference to {@link IXFMigrationService} impl.
     */
    public IXFMigrationService getMigrationService() {
        return migrationService;
    }

    /**
     * Setter method of repositoryService field.
     * 
     * @param repositoryServRef
     *            A reference to {@link IRepositoryService} impl.
     */
    public void setRepositoryService(IRepositoryService repositoryServRef) {
        this.repositoryService = repositoryServRef;
    }

    /**
     * Getter of repositoryService field.
     * 
     * @return A reference to {@link IRepositoryService} impl.
     */
    public IRepositoryService getRepositoryService() {
        return repositoryService;
    }
}
