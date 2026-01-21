package api.pages;

import api.base.BaseApi;
import api.constants.EndPoints;
import io.restassured.http.ContentType;
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
     *
     * @param requiresAuth whether authentication is required
     */
    public KeyPositionTemplateApi(boolean requiresAuth) {
        super(requiresAuth);
    }

    /**
     * Get all key position templates
     *
     * @return API response with list of templates
     */
    public Response getAllTemplates(String page) {
        System.out.println("Fetching all key position templates");

        Response response = getRequestSpec()
                .queryParam("page", page)
                .get(EndPoints.KEY_POSITION_TEMPLATES);

        logResponse(response);
        return response;
    }

    /**
     * Get templates with pagination
     *
     * @param page page number
     * @param size page size
     * @return API response with paginated templates
     */
    public Response getAllTemplates(int page, int size) {
        System.out.println("Fetching templates - page: " + page + ", size: " + size);

        Response response = getRequestSpec()
                .queryParam("page", page)
                .queryParam("size", size)
                .get(EndPoints.KEY_POSITION_TEMPLATES);

        logResponse(response);
        return response;
    }


    public Response getTemplateByName(String search) {
        System.out.println("Fetching template with ID: " + search);

        Response response = getRequestSpec()
                .queryParam("search", search)
                .get(EndPoints.KEY_POSITION_TEMPLATE_BY_NAME);

        logResponse(response);
        return response;
    }
    public Response createkeypositiontemplate(String name,List<Map<String, Object>> patterns) {
        System.out.println("Creating a new key position template");
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", name);
        requestBody.put("patterns", patterns);

        Response response = getRequestSpec()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .post(EndPoints.KEY_POSITION_TEMPLATE_CREATE);

        logResponse(response);
        return response;
    }

    public Response updatekeypositiontemplate(int id, String name,List<Map<String, Object>> patterns) {
        System.out.println("Update a key positio template details");
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", id);
        requestBody.put("name", name);
        requestBody.put("patterns", patterns);


        Response response = getRequestSpec()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .patch(EndPoints.KEY_POSITION_TEMPLATE_UPDATE);

        logResponse(response);
        return response;
    }



    /**
     * Search templates by name
     *
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


}