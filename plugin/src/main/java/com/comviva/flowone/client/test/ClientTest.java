package main.java.com.comviva.flowone.client.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;

import main.java.com.comviva.flowone.logger.DBLogger;
import main.java.com.comviva.flowone.logger.DBLoggerThread;

public class ClientTest {

	public static void main (String[] args) {
		
//		try {
//			String url = "http://localhost:8088/InstantLink?wsdl";
//			URL obj = new URL(url);
//			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//			con.setRequestMethod("POST");
//			con.setRequestProperty("Content-Type","application/soap+xml; charset=utf-8");
//			String countryCode="Canada";
//			String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
//			"<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\"> " +
//			" <soap12:Body> " +
//			" <GetHolidaysAvailable xmlns=\"http://www.holidaywebservice.com/HolidayService_v2/\"> " +
//			" <countryCode>"+countryCode+"</countryCode>" +
//			" </GetHolidaysAvailable>" +
//			" </soap12:Body>" +
//			"</soap12:Envelope>";
//			con.setDoOutput(true);
//			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//			wr.writeBytes(xml);
//			wr.flush();
//			wr.close();
//			String responseStatus = con.getResponseMessage();
//			System.out.println(responseStatus);
//			BufferedReader in = new BufferedReader(new InputStreamReader(
//			con.getInputStream()));
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//			while ((inputLine = in.readLine()) != null) {
//			response.append(inputLine);
//		}
		
		
//        String emptyWspPolicyXml = "<wsp:Policy wsu:Id=\"TransportToken\"\n" //
//        	    + "\txmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\"\n"
//        	//
//        	    + "\txmlns:wsp=\"http://schemas.xmlsoap.org/ws/2004/09/policy\"\n" //
//        	    + "\txmlns:sp=\"http://docs.oasis-open.org/ws-sx/ws-securitypolicy/200702\">\n"
//        	//
//        	    + "\t\t<wsp:ExactlyOne>\n" //
//        	    + "\t\t\t<wsp:All>\n" //
//        	    + "\t\t\t\t<sp:TransportBinding xmlns:sp=\"http://schemas.xmlsoap.org/ws/2005/07/securitypolicy\">\n"
//        	//
//        	    + "\t\t\t\t\t<wsp:Policy>\n" //
//        	    + "\t\t\t\t\t</wsp:Policy>" //
//        	    + "\t\t\t\t</sp:TransportBinding>\n" //
//        	    + "\t\t\t</wsp:All>\n" //
//        	    + "\t\t</wsp:ExactlyOne>\n" //
//        	    + "</wsp:Policy>\n" //
//        	    ;
		

//		String request = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ins=\"http://soa.comptel.com/2011/02/instantlink\">\n" //
//		+"<soap:Header xmlns:wsa=\"http://www.w3.org/2005/08/addressing\"> \n" //
//		+"<wsa:ReplyTo>\n" //
//		+"<wsa:Address>http://host.example.com:44006/ilws-response-mock-impl/ ResponseHandlerService?wsdl</wsa:Address> \n" //
//		+"</wsa:ReplyTo>\n" // 
//		+"</soap:Header>\n"
//		+"<soap:Body>\n"
//		+"<ins:CreateRequest>\n"  
//		+"     <ins:RequestHeader>\n"
//		+"    	<ins:NeType>CATALOG</ins:NeType>\n"
//		+"      </ins:RequestHeader>\n"
//		+"      	<ins:RequestParameters>\n"
//		+"			<ins:Parameter name=\"SO1_SERVICENAME\" value=\"PEPE\"/>\n"
//		+"			<ins:Parameter name=\"SO1_ACTIONCODE\" value=\"CREATE\"/>\n"
//		+"			<ins:Parameter name=\"IMSI\" value=\"724045900000885\"/>\n"
//		+"			<ins:Parameter name=\"MSISDN\" value=\"5551981136585\"/>\n"
//		+"			<ins:Parameter name=\"PROFILE\" value=\"PREPAID\"/>\n"
//		+"			<ins:Parameter name=\"ADKEY\" value=\"2\"/>\n"	
//		+"			<ins:Parameter name=\"KI\" value=\"PREH34YU234234JB34U34342234242CCC\"/>\n"
//		+"			<ins:Parameter name=\"FSETIND\" value=\"1\"/>\n"				
//		+"			<ins:Parameter name=\"PREPAID_TYPE\" value=\"OCS\"/>\n"			 
//		+"		</ins:RequestParameters>\n"
//		+"	</ins:CreateRequest>\n"
//		+"</soap:Body>\n"
//		+"</soap:Envelope>";
//		
//		try {
//	       // URL url = new URL("http://localhost:8088/InstantLink?wsdl");
//	        URL url = new URL("http://localhost:8088/InstantLink?wsdl");
//	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//	
//	        byte[] buffer = request.getBytes();
//	
//	        connection.setRequestProperty("Content-Length", String.valueOf(buffer.length));
//	        connection.setRequestProperty("Content-Type", "text/xml; charset=" + "UTF-8");
//	        connection.setRequestMethod("POST");
//	        connection.setDoOutput(true);
//	        connection.setDoInput(true);
//	
//	        connection.connect();
//	        //startDate = new Timestamp(new Date().getTime());
//	        String response = httpRead(connection, buffer);
//	        
//	        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(response)));
//	        
//	        NodeList errNodes = doc.getElementsByTagName("ins:Status");
//	        
//	        
//	        System.out.println(response);
//	        
//	        System.out.println(errNodes.item(0).getTextContent());
//	        
//	        //Response response1 = (Response)httpRead(connection, buffer);
//
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
       // endDate = new Timestamp(new Date().getTime());
		
				
//		byte[] byteArr = request.getBytes();
//		InputStream targetStream = new ByteArrayInputStream(request.getBytes());
		
//		try {
			
//			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
//			java.net.URL endpoint = new URL ("http://localhost:8088/InstantLink?wsdl");
//			SOAPConnection connection = soapConnectionFactory.createConnection();
//			MessageFactory factory = MessageFactory.newInstance();
//			SOAPMessage message = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(request.getBytes()));
//		    AttachmentPart attachment = message.createAttachmentPart();
//		    attachment.setContent("Content-Type", "application/soap+xml");
//		    attachment.setContentId("1.9f910338bf0cac0e783bfdec7e53be9237684caa8f8f4e6d@apache.org");
//		    message.addAttachmentPart(attachment);
//		    SOAPMessage response = connection.call(message, endpoint);
//		    ByteArrayOutputStream out = new ByteArrayOutputStream();
//		    response.writeTo(out);
//		    String strMsg = new String(out.toByteArray());
//			System.out.println(strMsg);
//			HttpClient client = new DefaultHttpClient();
//			HttpGet request1 = new HttpGet("http://localhost:8088/InstantLink?wsdl");
//			HttpResponse response = client.execute(request1);
//			response.getEntity();
//			
			
//			Socket con = new Socket("localhost",8088);
//			System.out.println(con.isConnected());
//			OutputStream reqStream = con.getOutputStream();
//			
//			reqStream.write(byteArr);
//			reqStream.flush();
//	    } catch (Exception e) {
//	    	System.out.println(e.getMessage());
//	    }	
			
//	        String soapEndpointUrl = "http://localhost:8088/InstantLink?wsdl";
//	        String soapAction = "http://localhost:8088/InstantLink/create";
//
//	        callSoapWebService("soapEndpointUrl", "soapAction");

		DBLogger asaLogs = new DBLogger("LoggerPort", "http://service.orchestrator.asa.ats_connection.com/", "http://localhost:8081/ASA_Orchestrator/LoggerService?wsdl");
		//DBLogger asaLogs = new DBLogger();
		new Thread(new DBLoggerThread(asaLogs, "FlowOne", "Calling to CreateAUCRequest", "InstantLinkWebServices",
				"1", "log", "", "ASAORCH", "5521965001409", "724022649991409",
				"89550326011921451409", "testflowone")).start();
		
	}

	   private static String httpRead(HttpURLConnection connection, byte[] requestSoap) throws IOException {

	        java.io.OutputStream out = null;
	        out = connection.getOutputStream();
	        BufferedReader in = null;
	        String response;

	        // System.out.println("[BUILD] - Envio SOAP REQUEST");
	        try{
	            out.write(requestSoap);
	            out.close();
	            //System.out.println("[BUILD] - READ RESPONSE");
	            int responseCode = connection.getResponseCode();
	            //System.out.println("[BUILD] - Comprobar -");
	            String responseString = "";
	            String outputString = "";

	            if (responseCode == HttpURLConnection.HTTP_OK) {
	                InputStreamReader isr = new InputStreamReader(connection.getInputStream(),"UTF-8");
	                in = new BufferedReader(isr);
	                while ((responseString = in.readLine()) != null) {
	                    outputString += responseString;
	                }
	                response = outputString;
	            }else {
	                //System.out.println("[BUILD] - httpRead -MAL-");
	                InputStreamReader isr = new InputStreamReader(connection.getErrorStream(),"UTF-8");
	                //System.out.println("[BUILD] - httpRead -MAL2-");
	                in = new BufferedReader(isr);
	                //System.out.println("[BUILD] - httpRead -MAL3-");
	                while ((responseString = in.readLine()) != null) {
	                    outputString += responseString;
	                }
	                response = outputString;

	            } //if-else
	        }finally{
	            try {
	                if(in != null){
	                    System.out.println("Close IN");
	                    in.close();
	                }
	            } catch (Exception e) {System.out.println("Error in close in"); }
	        }


	        return response;
	    }//httpRead
	   
	
//	    private static void createSoapEnvelope(SOAPMessage soapMessage) throws SOAPException {
//	        SOAPPart soapPart = soapMessage.getSOAPPart();
//	        System.out.println("1");
//	        String myNamespace = "http://soa.comptel.com/2011/02/instantlink";
//	        String myNamespaceURI = "http://soa.comptel.com/2011/02/instantlink";
//	        System.out.println("2");
//	        // SOAP Envelope
//	        SOAPEnvelope envelope = soapPart.getEnvelope();
//	        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
//	        System.out.println("3");
//	            /*
//	            Constructed SOAP Request Message:
//	            <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:myNamespace="http://www.webserviceX.NET">
//	                <SOAP-ENV:Header/>
//	                <SOAP-ENV:Body>
//	                    <myNamespace:GetInfoByCity>
//	                        <myNamespace:USCity>New York</myNamespace:USCity>
//	                    </myNamespace:GetInfoByCity>
//	                </SOAP-ENV:Body>
//	            </SOAP-ENV:Envelope>
//	            */
//
//	        // SOAP Body
//	        SOAPBody soapBody = envelope.getBody();
//	        SOAPElement soapBodyElem = soapBody.addChildElement("GetInfoByCity", myNamespace);
//	        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("USCity", myNamespace);
//	        soapBodyElem1.addTextNode("New York");
//	    }
//
//	    private static void callSoapWebService(String soapEndpointUrl, String soapAction) {
//	        try {
//	            // Create SOAP Connection
//	            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
//	            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
//
//	            // Send SOAP Message to SOAP Server
//	            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction), soapEndpointUrl);
//
//	            // Print the SOAP Response
//	            System.out.println("Response SOAP Message:");
//	            soapResponse.writeTo(System.out);
//	            System.out.println();
//
//	            soapConnection.close();
//	        } catch (Exception e) {
//	            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
//	            e.printStackTrace();
//	        }
//	    }
//
//	    private static SOAPMessage createSOAPRequest(String soapAction) throws Exception {
//	        MessageFactory messageFactory = MessageFactory.newInstance();
//	        SOAPMessage soapMessage = messageFactory.createMessage();
//
//	        createSoapEnvelope(soapMessage);
//
//	        MimeHeaders headers = soapMessage.getMimeHeaders();
//	        headers.addHeader("SOAPAction", soapAction);
//
//	        soapMessage.saveChanges();
//
//	        /* Print the request message, just for debugging purposes */
//	        System.out.println("Request SOAP Message:");
//	        soapMessage.writeTo(System.out);
//	        System.out.println("\n");
//
//	        return soapMessage;
//	    }
		
		
	   
	   
	   
	}
		


