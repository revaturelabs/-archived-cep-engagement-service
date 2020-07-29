package com.cepengagementservice.Controllers;


	import java.security.KeyManagementException;
	import java.security.KeyStoreException;
	import java.security.NoSuchAlgorithmException;
	import java.security.cert.X509Certificate;
	import java.util.HashMap;
	import java.util.Map;

	import javax.net.ssl.SSLContext;

	import org.apache.http.conn.ssl.NoopHostnameVerifier;
	import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
	import org.apache.http.conn.ssl.TrustStrategy;
	import org.apache.http.impl.client.CloseableHttpClient;
	import org.apache.http.impl.client.HttpClients;
	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.RestController;
	import org.springframework.web.client.RestTemplate;



	@RestController
	public class AssociateGraphController {
	
		
		@Autowired
		public RestTemplate restTemplate()  throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
		    //Override certification verification. Always return true.
		    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
		    //Create SSLContext with null key  store and override.
		    SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
		    //Remember the NoopHostNameVerifier for nothrow  of alternate name SSLException
		    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
		    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
		    //Set httpclient with custom ssl verification inside request factory.
		    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		    //Return rest template with custom requestfactory.
		    RestTemplate restTemplate = new RestTemplate(requestFactory);
		    return restTemplate;
		 }
		
		

		 @GetMapping(value="/graph/associate/{batchId}/{associateEmail}")
		 @Cacheable("GetAssociateGrade")
		    public Object getAssociateGrade(@PathVariable String associateEmail,@PathVariable String batchId) {
		        String uri = "http://34.82.182.44:80/mock/evaluation/grades/reports/{params2}/spider/{params}";
		        
		        Map<String, String> anotherId = new HashMap<String, String>();
		        anotherId.put("params", associateEmail);
		        anotherId.put("params2", batchId);
		        try{		     
		        //Grab the custom Rest Template.
		        RestTemplate obj = restTemplate();	
		        //TODO: Map the received data (JSON) to AssociateGraph.class
		        //If fields not found from AssociateGraph model, fields will be empty.
		        //If not able to cast a field, will throw!
		        Object fetched = obj.getForObject(uri, Object.class ,anotherId);
	
		        return fetched;

		    }catch(Exception exception){System.out.println(exception.getMessage()); }
		        return null;
		    }

}
