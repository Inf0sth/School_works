// Base namespaces for essential functionalities
using System; // Provides fundamental types and base classes
using System.Collections.Generic; // Enables the use of generic collections like List<T>
using System.Net; // Provides networking capabilities, including HTTP status codes
using System.Net.Http; // Used for sending HTTP requests and receiving responses
using System.Net.Http.Headers; // Enables handling of HTTP headers
using System.Threading.Tasks; // Supports asynchronous programming with Task-based operations
using Newtonsoft.Json; // Handles JSON serialization and deserialization

namespace AuthAPIRest // Namespace for the authentication API REST class
{
    /// <summary>
    /// Class that executes authentication requests to the SecurId API.
    /// </summary>
    public class cls_AuthAPIRest
    {
        /// <summary>
        /// Constructor of the class.
        /// </summary>
        public cls_AuthAPIRest()
        {
        }

        /// <summary>
        /// Main method to execute authentication with SecurId API.
        /// </summary>
        /// <param name="str_clientId">Client identifier.</param>
        /// <param name="str_subjectName">Subject name.</param>
        /// <param name="str_name">Name parameter for authentication.</param>
        /// <param name="str_value">Value corresponding to the name parameter.</param>
        /// <param name="str_methodId">Method identifier.</param>
        /// <param name="str_authnAttemptId">Authentication attempt identifier.</param>
        /// <param name="str_messageId">Message identifier.</param>
        /// <param name="str_inResponseTo">Response reference identifier.</param>
        /// <param name="str_client_key">Client key for authentication.</param>
        /// <param name="str_URL">API URL.</param>
        /// <returns>API response as a string.</returns>
        public string ExecuteAuthAPISecurId(string str_clientId, string str_subjectName, string str_name, string str_value, string str_methodId, string str_authnAttemptId, string str_messageId, string str_inResponseTo, string str_client_key, string str_URL)
        {
            string str_Result = string.Empty; // Initializing the result string to empty string
            
            // Creating HTTP request object (not used directly here)
            HttpRequestMessage request = new HttpRequestMessage(); // Creating a new HTTP request message object
            request.Method = HttpMethod.Post; // Setting the HTTP method to POST for the request
            
            // Execute authentication asynchronously and wait for result 
            str_Result = RunAsync(str_clientId, str_subjectName, str_name, str_value, str_methodId, str_authnAttemptId, str_messageId, str_inResponseTo, str_client_key, str_URL).GetAwaiter().GetResult();

            return str_Result; // Returning the API response as a string
        }

        /// <summary>
        /// Asynchronous method that constructs the authentication request data.
        /// </summary>
        /// <returns>API response as a string.</returns>
        static async Task<string> RunAsync(string str_clientId, string str_subjectName, string str_name, string str_value, string str_methodId, string str_authnAttemptId, string str_messageId, string str_inResponseTo, string str_client_key, string str_URL)
        {
            string Response = string.Empty; // Initializing the response string to empty string
            try
            {
                // Constructing input credentials with name and value
                List<cls_collectedInputs> obj_lst_CollectedInputs = new List<cls_collectedInputs> 
                {
                    new cls_collectedInputs(str_name, str_value) // Name and value of the input credential
                };

                // Constructing subject credentials with method identifier and input credentials
                List<cls_subjectCredentials> obj_lst_subjectCredentials = new List<cls_subjectCredentials>
                {
                    new cls_subjectCredentials(str_methodId, obj_lst_CollectedInputs) // Method identifier and input credentials
                };

                // Constructing authentication context
                cls_context obj_context = new cls_context 
                {
                    authnAttemptId = str_authnAttemptId, // Authentication attempt identifier
                    messageId = str_messageId, // Message identifier
                    inResponseTo = str_inResponseTo // Response reference identifier
                };

                // Constructing full authentication request object
                cls_JsonCompleto obj_JsonCompleto = new cls_JsonCompleto
                {
                    clientId = str_clientId, // Client identifier
                    subjectName = str_subjectName, // Subject name
                    subjectCredentials = obj_lst_subjectCredentials, // Subject credentials
                    context = obj_context // Authentication context
                };

                Response = await RunAPI(obj_JsonCompleto, str_client_key, str_URL); // Executing the API request
            }
            catch (Exception e) // Catching exceptions
            {
                Console.WriteLine($"Exception: {e.Message}"); // Printing the exception message
            }

            return Response; // Returning the API response
        }

        /// <summary>
        /// Asynchronous method that executes the API REST request.
        /// </summary>
        /// <param name="obj">JSON object containing authentication data.</param>
        /// <param name="str_client_key">Client key for authentication.</param>
        /// <param name="str_URL">API URL.</param>
        /// <returns>API response as a string.</returns>
        static async Task<string> RunAPI(cls_JsonCompleto obj, string str_client_key, string str_URL) // Asynchronous method to execute the API request
        {
            string str_Return = string.Empty; // Initializing the return string
            try // Try block to catch exceptions
            {
                // Initializing HTTP client
                HttpClient client = new HttpClient()
                {
                    BaseAddress = new Uri(str_URL), // Setting the base URL
                };
                
                // Setting headers
                client.DefaultRequestHeaders.Accept.Clear(); // Clearing the default headers
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json")); // Adding the JSON media type
                client.DefaultRequestHeaders.Add("client-key", str_client_key); // Adding the client key header for authentication
                
                // Serializing the JSON object to a string content object for the request
                var jsonRequest = JsonConvert.SerializeObject(obj);
                
                // Sending HTTP request and getting response asynchronously and waiting for the result 
                HttpResponseMessage obj_Response = client.PostAsJsonAsync("mfa/v1_1/authn/initialize", obj).Result;
                
                // Handling response based on status code and getting the response content
                if (obj_Response.StatusCode == HttpStatusCode.OK)
                {
                    str_Return = await obj_Response.Content.ReadAsStringAsync(); // Getting the response content as a string
                }
                else // Handling other status codes
                {
                    str_Return = obj_Response.StatusCode.ToString(); // Getting the status code as a string
                }
            }
            catch (Exception ex) // Catching exceptions
            {
                Console.WriteLine($"ERROR: {ex.Message}"); // Printing the exception message to the console
            }
            return str_Return; // Returning the response as a string 
        }
    }
}
