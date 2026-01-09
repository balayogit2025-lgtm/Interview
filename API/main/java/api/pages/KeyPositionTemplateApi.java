package api.pages;

import api.base.BaseApi;
import api.constants.EndPoints;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Page class for Key Position Template Management related API operations.
 * Handles CRUD operations for key position templates.
 */
public class KeyPositionTemplateApi extends BaseApi {

    /**
     * Default constructor - requires authentication
     */
    public KeyPositionTemplateApi() {
        super(true);
    }

    /**
     * Constructor with authentication option
     * @param requiresAuth whether authentication is required
     */
    public KeyPositionTemplateApi(boolean requiresAuth) {
        super(requiresAuth);
    }

    /**
     * Get all key position templates
     * @return API response with list of templates
     */
    public Response getAllTemplates() {
        System.out.println("Fetching all key position templates");

        Response response = getRequestSpec()
                .get(EndPoints.KEY_POSITION_TEMPLATES);

        logResponse(response);
        return response;
    }

    /**
     * Get templates with pagination
     * @param page page number
     * @param size page size
     * @return API response with paginated templates
     */
    public Response getAllTemplates(int page, int size) {
        System.out.println("Fetching templates - page: " + page + ", size: " + size);

        Response response = getRequestSpec()
                .queryParam("page", page)
                .queryParam("size", size)
                .get(EndPoints.KEY_POSITION_TEMPLATE_LIST);

        logResponse(response);
        return response;
    }

    /**
     * Get template by ID
     * @param templateId the template ID
     * @return API response with template details
     */
    public Response getTemplateById(String templateId) {
        System.out.println("Fetching template with ID: " + templateId);

        Response response = getRequestSpec()
                .pathParam("id", templateId)
                .get(EndPoints.KEY_POSITION_TEMPLATE_BY_ID);

        logResponse(response);
        return response;
    }

    /**
     * Get template by ID (integer)
     * @param templateId the template ID
     * @return API response with template details
     */
    public Response getTemplateById(int templateId) {
        return getTemplateById(String.valueOf(templateId));
    }

    /**
     * Create a new key position template
     * @param name template name
     * @param description template description
     * @param positions number of key positions
     * @return API response
     */
    public Response createTemplate(String name, String description, int positions) {
        System.out.println("Creating new template: " + name);

        Map<String, Object> templateDetails = new HashMap<>();
        templateDetails.put("name", name);
        templateDetails.put("description", description);
        templateDetails.put("positions", positions);

        Response response = getRequestSpec()
                .body(templateDetails)
                .post(EndPoints.KEY_POSITION_TEMPLATE_CREATE);

        logResponse(response);
        return response;
    }

    /**
     * Create a new template with position details
     * @param name template name
     * @param description template description
     * @param positionDetails list of position details
     * @return API response
     */
    public Response createTemplate(String name, String description, List<Map<String, Object>> positionDetails) {
        System.out.println("Creating new template: " + name + " with position details");

        Map<String, Object> templateDetails = new HashMap<>();
        templateDetails.put("name", name);
        templateDetails.put("description", description);
        templateDetails.put("positions", positionDetails);

        Response response = getRequestSpec()
                .body(templateDetails)
                .post(EndPoints.KEY_POSITION_TEMPLATE_CREATE);

        logResponse(response);
        return response;
    }

    /**
     * Create a new template with custom body
     * @param templateBody map containing template details
     * @return API response
     */
    public Response createTemplate(Map<String, Object> templateBody) {
        System.out.println("Creating new template with custom body");

        Response response = getRequestSpec()
                .body(templateBody)
                .post(EndPoints.KEY_POSITION_TEMPLATE_CREATE);

        logResponse(response);
        return response;
    }

    /**
     * Update template by ID
     * @param templateId the template ID
     * @param updateData map containing fields to update
     * @return API response
     */
    public Response updateTemplate(String templateId, Map<String, Object> updateData) {
        System.out.println("Updating template with ID: " + templateId);

        Response response = getRequestSpec()
                .pathParam("id", templateId)
                .body(updateData)
                .put(EndPoints.KEY_POSITION_TEMPLATE_UPDATE);

        logResponse(response);
        return response;
    }

    /**
     * Update template name
     * @param templateId the template ID
     * @param newName the new name
     * @return API response
     */
    public Response updateTemplateName(String templateId, String newName) {
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("name", newName);
        return updateTemplate(templateId, updateData);
    }

    /**
     * Delete template by ID
     * @param templateId the template ID
     * @return API response
     */
    public Response deleteTemplate(String templateId) {
        System.out.println("Deleting template with ID: " + templateId);

        Response response = getRequestSpec()
                .pathParam("id", templateId)
                .delete(EndPoints.KEY_POSITION_TEMPLATE_DELETE);

        logResponse(response);
        return response;
    }

    /**
     * Delete template by ID (integer)
     * @param templateId the template ID
     * @return API response
     */
    public Response deleteTemplate(int templateId) {
        return deleteTemplate(String.valueOf(templateId));
    }

    /**
     * Search templates by name
     * @param searchQuery the search query
     * @return API response with matching templates
     */
    public Response searchTemplates(String searchQuery) {
        System.out.println("Searching templates with query: " + searchQuery);

        Response response = getRequestSpec()
                .queryParam("search", searchQuery)
                .get(EndPoints.KEY_POSITION_TEMPLATES);

        logResponse(response);
        return response;
    }

    /**
     * Get template with invalid/non-existent ID for negative testing
     * @return API response
     */
    public Response getTemplateWithInvalidId() {
        return getTemplateById("invalid-id-12345");
    }
}
