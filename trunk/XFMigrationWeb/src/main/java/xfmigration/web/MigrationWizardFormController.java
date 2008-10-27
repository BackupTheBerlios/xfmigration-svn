package xfmigration.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.util.WebUtils;

import xfmigration.domain.Forge;
import xfmigration.domain.Mapping;
import xfmigration.domain.Service;
import xfmigration.migration.IRepositoryService;
import xfmigration.migration.IXFMigrationService;
import xfmigration.web.util.MigrationForm;

/**
 * A wizard style controller which extends {@link AbstractWizardFormController}.
 * A controller handles migration process form, which is a four page wizard
 * form.
 */
public class MigrationWizardFormController extends AbstractWizardFormController {

    /**
     * symbol of page no. 4
     */
    private static final int PAGE_04_CODE = 3;

    /**
     * symbol of page no. 3
     */
    private static final int PAGE_03_CODE = 2;

    /**
     * symbol of page no. 2
     */
    private static final int PAGE_02_CODE = 1;

    /**
     * symbol of page no. 1
     */
    private static final int PAGE_01_CODE = 0;

    /**
     * a reference to {@link IXFMigrationService} implementation.
     */
    private IXFMigrationService migrationService;

    /**
     * a reference to {@link IRepositoryService} implementation.
     */
    private IRepositoryService repositoryService;

    /**
     * a name of wizard page no.1 submit button.
     */
    private static final String PAGE_01_SUBMIT_BUTTON_NAME = "_target1";

    /**
     * a name of wizard page no.2 submit button.
     */
    private static final String PAGE_02_SUBMIT_BUTTON_NAME = "_target2";

    /**
     * a name of wizard page no.3 submit button.
     */
    private static final String PAGE_03_SUBMIT_BUTTON_NAME = "_finish";

    /**
     * {@inheritDoc}
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return new MigrationForm();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException arg3) throws Exception {

        MigrationForm cmd = (MigrationForm) command;
        Mapping m = getRepositoryService().getMapping(cmd.getMappingId());
        boolean remote = cmd.isRemotedata();

        getMigrationService().migrate(m, cmd.getProjectURL(), cmd.getOperations(), remote);

        Mapping mapping = getRepositoryService().getMapping(cmd.getMappingId());
        Map map = new ModelMap();
        map.put(getCommandName(), cmd);
        map.put("mapping", mapping);
        Forge destForge = getRepositoryService().getForgeByName(cmd.getDestForgeName());
        List<Service> executedServices = destForge.getServicesByName(cmd.getOperations());
        map.put("executedServices", executedServices);

        return new ModelAndView("migration/migration_page4", map);
    }

    /**
     * {@inheritDoc}
     */
    protected ModelAndView processCancel(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {
        return super.processCancel(request, response, command, errors);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    protected Map referenceData(HttpServletRequest request, Object command, Errors errors, int page)
            throws Exception {

        MigrationForm cmd = (MigrationForm) command;

        Map model = new ModelMap();
        List<Forge> forges = getRepositoryService().getForges();
        model.put("forges", forges);

        if (page == PAGE_01_CODE) {
            if (cmd.getSrcForgeName() != null && !cmd.getSrcForgeName().equals("UNSET")) {
                List destForges = getRepositoryService()
                        .getDestinationForges(cmd.getSrcForgeName());
                model.put("destForges", destForges);
            }
            if (cmd.getSrcForgeName() != null && !cmd.getSrcForgeName().equals("UNSET")
                    && cmd.getDestForgeName() != null && !cmd.getDestForgeName().equals("UNSET")) {
                List<Mapping> mappingsList = getRepositoryService().getMappings(
                        cmd.getSrcForgeName(), cmd.getDestForgeName());
                model.put("mappings", mappingsList);
                if (!cmd.isRemotedata()) {
                	List<String> localProjectsNames 
                		= getRepositoryService().getForgeLocalProjects(cmd.getSrcForgeName());
                	model.put("localProjectNames", localProjectsNames);
                }
            }
      
        } else if (page == PAGE_02_CODE) {
            if (!cmd.getSrcForgeName().equals("UNSET") && !cmd.getDestForgeName().equals("UNSET")) {

                Forge destForge = getRepositoryService().getForgeByName(cmd.getDestForgeName());
                List<Service> servicesList = new ArrayList<Service>(destForge.getServices());
                model.put("servicesList", servicesList);
            }
        } else if (page == PAGE_03_CODE) {
            if (cmd.getOperations() != null && !cmd.getOperations().isEmpty()) {
                Forge destForge = getRepositoryService().getForgeByName(cmd.getDestForgeName());
                List<Service> allowedServices = destForge.getServicesByName(cmd.getOperations());
                model.put("allowedServices", allowedServices);
            }
        }

        return model;
    }

    /**
     * {@inheritDoc}
     */
    protected int getTargetPage(HttpServletRequest request, Object command, Errors errors,
            int currentPage) {
        int pageNumber = 1;
        if (!WebUtils.hasSubmitParameter(request, PAGE_01_SUBMIT_BUTTON_NAME)
                && !WebUtils.hasSubmitParameter(request, PAGE_02_SUBMIT_BUTTON_NAME)
                && !WebUtils.hasSubmitParameter(request, PAGE_03_SUBMIT_BUTTON_NAME)
                && currentPage == PAGE_01_CODE) {
            pageNumber = PAGE_01_CODE;
        } else if (WebUtils.hasSubmitParameter(request, PAGE_01_SUBMIT_BUTTON_NAME)
                && currentPage == PAGE_01_CODE) {
            pageNumber = PAGE_02_CODE;
        } else if (WebUtils.hasSubmitParameter(request, PAGE_01_SUBMIT_BUTTON_NAME)
                && currentPage == PAGE_02_CODE) {
            pageNumber = PAGE_01_CODE;
        } else if (WebUtils.hasSubmitParameter(request, PAGE_02_SUBMIT_BUTTON_NAME)
                && currentPage == PAGE_02_CODE) {
            pageNumber = PAGE_03_CODE;
        } else if (WebUtils.hasSubmitParameter(request, PAGE_02_SUBMIT_BUTTON_NAME)
                && currentPage == PAGE_03_CODE) {
            pageNumber = PAGE_02_CODE;
        } else if (WebUtils.hasSubmitParameter(request, PAGE_03_SUBMIT_BUTTON_NAME)
                && currentPage == PAGE_03_CODE) {
            pageNumber = PAGE_04_CODE;
        }
        return pageNumber;
    }

    /**
     * {@inheritDoc}
     */
    protected void onBindAndValidate(HttpServletRequest request, Object command,
            BindException errors, int page) throws Exception {

        MigrationForm formBean = (MigrationForm) command;

        if (WebUtils.hasSubmitParameter(request, "_target1")) {
            if (formBean.getDestForgeName().equals("UNSET")) {
                errors.rejectValue("destForgeName", "error.not-specified", null,
                        "Please select a forge name");
            }
            if (formBean.getMappingId() == -1) {
                errors.rejectValue("mappingId", "error.not-specified", null,
                        "Please select a mapping");
            }
            if (formBean.getProjectURL() == null || formBean.getProjectURL().equals("")) {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "projectURL",
                        "error.field-required", "Please provide project name");
            }
            if (formBean.getSrcForgeName().equals("UNSET")) {
                errors.rejectValue("srcForgeName", "error.not-specified", null,
                        "Please select a forge name");
            }
        } else {
            super.onBindAndValidate(request, command, errors, page);
        }
    }

    /**
     * A getter method of {@link IXFMigrationService} implementation.
     * 
     * @return A reference to {@link IXFMigrationService} implementation.
     */
    public IXFMigrationService getMigrationService() {
        return migrationService;
    }

    /**
     * A setter method of {@link IXFMigrationService} implementation.
     * 
     * @param migService
     *            A reference to {@link IXFMigrationService} implementation.
     */
    public void setMigrationService(IXFMigrationService migService) {
        this.migrationService = migService;
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
     * A setter method of {@link IRepositoryService} implementation.
     * 
     * @param repoService {@link IRepositoryService} implementation reference.
     */
    public void setRepositoryService(IRepositoryService repoService) {
        this.repositoryService = repoService;
    }

}
