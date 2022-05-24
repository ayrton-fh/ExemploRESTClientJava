package br.ufs.dcomp.ExemploRESTClientJava;

import com.google.gson.Gson;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RESTClient 
{
    public static void getGroupUsers(String groupName) {
        try {
            String username = "ayrton-fh";
            String password = "notrabbitadmin";
            
            String usernameAndPassword = username + ":" + password;
            String authorizationHeaderName = "Authorization";
            String authorizationHeaderValue = "Basic " + java.util.Base64.getEncoder().encodeToString(usernameAndPassword.getBytes());
     
            String restResource = "http://rabbitmq-sd-lb-87e8287f560a91f2.elb.us-east-1.amazonaws.com";
            
            Client client = ClientBuilder.newClient();
            
            Response response = client.target(restResource)
                .path("/api/exchanges/%2f/" + groupName + "/bindings/destination")
            	.request(MediaType.APPLICATION_JSON)
                .header(authorizationHeaderName, authorizationHeaderValue)
                .get();
           
            if (response.getStatus() == 200) {
            	String json = response.readEntity(String.class);
            	
                System.out.println(json);
            	
            	Gson gson = new Gson();
            	
            	ExchangesResponse[] jsonResponse = gson.fromJson(json, ExchangesResponse[].class);
            	
            	System.out.println("SOURCE: " + jsonResponse[0].source);
            	
            	for (ExchangesResponse r : jsonResponse) {
            	    System.out.println("DESTINATION: " + r.destination);
            	}
            } 
            
            else System.out.println(response.getStatus());
        }
        catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static void getUserBindings(String userName) {
        try {
            String username = "ayrton-fh";
            String password = "notrabbitadmin";
            
            String usernameAndPassword = username + ":" + password;
            String authorizationHeaderName = "Authorization";
            String authorizationHeaderValue = "Basic " + java.util.Base64.getEncoder().encodeToString(usernameAndPassword.getBytes());
     
            String restResource = "http://rabbitmq-sd-lb-87e8287f560a91f2.elb.us-east-1.amazonaws.com";
            
            Client client = ClientBuilder.newClient();
            
            Response response = client.target(restResource)
                .path("/api/queues/%2f/" + userName + "/bindings")
            	.request(MediaType.APPLICATION_JSON)
                .header(authorizationHeaderName, authorizationHeaderValue)
                .get();
           
            if (response.getStatus() == 200) {
            	String json = response.readEntity(String.class);
            	
                System.out.println(json);
            	
            	Gson gson = new Gson();
            	
            	ExchangesResponse[] jsonResponse = gson.fromJson(json, ExchangesResponse[].class);
            	
            	System.out.println("USER: " + jsonResposta[0].destination);
            	
            	for (ExchangesResponse r : jsonResposta) {
            	    if (!r.source.equals("")) {
            	        System.out.println("SOURCE: " + r.source);
            	    }
            	}
            } 
            else System.out.println(response.getStatus());
        }
        catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static void main(String[] args) {
        try {
            // JAVA 8 como pré-requisito (ver README.md)
            String username = "ayrton-fh";
            String password = "notrabbitadmin";
     
            String usernameAndPassword = username + ":" + password;
            String authorizationHeaderName = "Authorization";
            String authorizationHeaderValue = "Basic " + java.util.Base64.getEncoder().encodeToString(usernameAndPassword.getBytes());
     
            // Perform a request
            String restResource = "http://rabbitmq-sd-lb-87e8287f560a91f2.elb.us-east-1.amazonaws.com";
            
            Client client = ClientBuilder.newClient();
            
            Response resposta = client.target(restResource)
            	// .path("/api/exchanges/%2f/ufs/bindings/source") // lista todos os binds que tem o exchange "ufs" como source	
                // .path("/api/exchanges/%2f/ufs_msg/bindings/destination")
                .path("/api/queues/%2f/bob_msg/bindings")
                // .path("/api/queues")
            	.request(MediaType.APPLICATION_JSON)
                .header(authorizationHeaderName, authorizationHeaderValue) // The basic authentication header goes here
                .get(); // Perform a post with the form values
           
            if (resposta.getStatus() == 200) {
            	String json = resposta.readEntity(String.class);
            	
                // System.out.println(json);
            	
            	Gson gson = new Gson();
            	
            	ExchangesResponse[] jsonResposta = gson.fromJson(json, ExchangesResponse[].class);
            	
            	System.out.println("USER: " + jsonResposta[0].destination);
            	
            	for (ExchangesResponse r : jsonResposta) {
            	    if (!r.source.equals("")) {
            	        System.out.println("SOURCE: " + r.source);
            	    }
            	}
            
            } 
            else System.out.println(resposta.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
