package xfmigration.web;

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
import xfmigration.migration.IXFMigrationService;
import xfmigration.web.util.RegisterForgeForm;

/**
 * A form controller which serves actions regarding forge registration form.
 */
public class RegisterForgeController extends SimpleFormController {

    /**
     * A reference to {@link IXFMigrationService} impl.
     */
    private IXFMigrationService migrationService;

    /**
     * Returns a form backing bean.
     * 
     * @param request
     *            Incoming {@link HttpServletRequest}.
     * @return An instance of {@link RegisterForgeForm}.
     * @throws Exception
     *             exception
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return new RegisterForgeForm();
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
        return model;
    }
    
    /**
     * Method to register binder for uploading files as byte arrays.
     * @param request HTTP request.
     * @param binder A request binder reference.
     * @throws Exception exception.
     */
    protected void initBinder(HttpServletRequest request,
    		ServletRequestDataBinder binder) throws Exception {
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
    @SuppressWarnings("unchecked")
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {
        Map map = errors.getModel();
        RegisterForgeForm cmd = (RegisterForgeForm) command;
        
        Forge forge = new Forge(cmd.getForgeName(), cmd.getForgeOntURL(), cmd.getForgeOntURL(), cmd
                .getForgeWSDL(), cmd.getForgeOWLS(), cmd.getForgeDescription(),
                cmd.getImportOwlsPath(), cmd.getOwlsCreateIndividualsSequencePath(), 
                cmd.getOwlsCreateObjPropertiesSequencePath());
        
/*        System.out.println(new String(cmd.getImpDataOwls()));
        System.out.println(new String(cmd.getExpIndividualsOwlsSeq()));
        System.out.println(new String(cmd.getExpPropertiesOwlsSeq()));
        getMigrationService().registerForge(forge, cmd.getImpDataOwls(), 
        		cmd.getExpIndividualsOwlsSeq(), cmd.getExpPropertiesOwlsSeq());
*/        
        getMigrationService().registerForge(forge);
        return new ModelAndView(getSuccessView(), map);
    }

    /**
     * Setter method of migratonService field.
     * 
     * @param migrationServiceRef
     *            A reference to {@link IXFMigrationService} impl.
     */
    public void setMigrationService(IXFMigrationService migrationServiceRef) {
        this.migrationService = migrationServiceRef;
    }

    /**
     * Getter method of migrationService field.
     * 
     * @return A reference to an {@link IXFMigrationService} impl.
     */
    public IXFMigrationService getMigrationService() {
        return migrationService;
    }
}
