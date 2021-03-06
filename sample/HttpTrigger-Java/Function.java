package Microsoft.Azure.WebJobs.Script.Tests.EndToEnd;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with HTTP Trigger.
 * Create jar file for this function following instructions at:
 * https://docs.microsoft.com/en-us/azure/azure-functions/functions-create-first-java-maven
 * groupId: Microsoft.Azure.WebJobs.Script.Tests.EndToEnd
 * artifactId: HttpTrigger-Java
 * Accept defaults for rest of the identifiers
 * Run mvn clean package
 */
public class Function {
    /**
     * This function listens at endpoint "/api/hello". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/hello
     * 2. curl {your host}/api/HttpTrigger-Java?name=HTTP%20Query
     */
    @FunctionName("HttpTrigger-Java")
    public HttpResponseMessage<String> HttpTriggerJava(
            @HttpTrigger(name = "req", methods = {"get", "post"}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Parse query parameter
        String query = request.getQueryParameters().get("name");
        String name = request.getBody().orElse(query);

        if (name == null) {
            return request.createResponse(400, "Please pass a name on the query string or in the request body");
        } else {
            return request.createResponse(200, "Hello " + name);
        }
    }
}
