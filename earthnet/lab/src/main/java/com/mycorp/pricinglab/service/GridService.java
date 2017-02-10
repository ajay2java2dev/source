package com.vzwcorp.pricinglab.service;

import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.loader.profile.ubsr.repository.SubCustAcctMdnRepository;
import com.vzwcorp.pricinglab.profile.vo.SubCustAcctMdn;
import com.vzwcorp.pricinglab.utility.PricingLabUtility;
import com.vzwcorp.pricinglab.vo.PlabCust;
import com.vzwcorp.pricinglab.vo.key.PlabCustAcctMdnPK;
import com.vzwcorp.pricinglab.vo.repository.PlabCustRepository;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@RestController
public class GridService {

	private static String dateformat = "yyyy-MM-dd";
	private static String dateformatTs = "yyyy-MM-dd hh:mm:ss";
	static Logger logger = LogManager.getLogger(GridService.class);
	private static SimpleDateFormat GRID_TX_ID_FORMAT = new SimpleDateFormat("HHmmssSSS");
	
	@Autowired
	SubCustAcctMdnRepository subCustRepo;

	@Autowired
	PlabCustRepository plabCustRepo;
	
	@Autowired
	PricingLabUtility pricingLabUtility;

	@Value("${http.connection.timeout}")
	Integer connectionTimeOut;
	@Value("${http.connection.request.timeout}")
	Integer connectionRequestTimeOut;
	@Value("${http.connection.socket.timeout}")
	Integer connectionSocketTimeOut;

	@Value("${grid.url}")
	private String gridUrl;
	
	@Value("${grid.get.url}")
	private String gridGetUrl;
	
	DocumentBuilder builder;

	public int add(Long custId,Long acctNo,String mdn,Long indicator) {

		logger.debug("Add to Datagrid : MDN : " + mdn);						
		String postReqXML = generateGridXml(custId, acctNo, mdn, indicator,PlabConstants.GRID_ADD,PlabConstants.GRID_ADD_TRANSID);			
		//String responseString = postHttpRequest(gridUrl, postReqXML);
		String responseString = pricingLabUtility.postRequest(gridUrl, postReqXML, ContentType.APPLICATION_XML);
		logger.info("Grid response XML for add() for mdn : "+mdn+" is "+responseString);
		
		if(responseString==null ||(responseString!=null && responseString.startsWith("Exception"))){
			logger.error("Error adding mdn :"+mdn+ " to datagrid : "+responseString);
			return -1;
		}
		return 0;
	}

	public int remove(Long custId,Long acctNo) {
		
		logger.debug("Remove from Datagrid : custID : " + custId);		
		String postReqXML = generateGridXml(custId,acctNo,null,null, PlabConstants.GRID_DELETE,PlabConstants.GRID_DELETE_TRANSID);			
		//String responseString = postHttpRequest(gridUrl, postReqXML);
		String responseString = pricingLabUtility.postRequest(gridUrl, postReqXML, ContentType.APPLICATION_XML);
		logger.info("Grid response XML for remove() for custID : "+custId+" is "+responseString);
		
		if(responseString!=null && !responseString.startsWith("Exception")){
			logger.error("Error removing customer :"+custId+ "from datagrid : "+responseString);
			return -1;
		}
		return 0;
	}

	public int update(Long custId,Long acctNo,String mdn,Long indicator) {
		
		logger.debug("Update Datagrid : MDN : " + mdn);	
		String postReqXML = generateGridXml(custId,acctNo,mdn,indicator, PlabConstants.GRID_UPDATE,PlabConstants.GRID_UPDATE_TRANSID);			
		//String responseString = postHttpRequest(gridUrl, postReqXML);
		String responseString = pricingLabUtility.postRequest(gridUrl, postReqXML, ContentType.APPLICATION_XML);
		logger.info("Grid response XML for update() for mdn : "+mdn+" is "+responseString);
		
		if(responseString!=null && responseString.startsWith("Exception")){
			logger.error("Error updating indicator for mdn :"+mdn+ "from datagrid : "+responseString);
			return -1;
		}
		return 0;
	}

	@RequestMapping(value = "/grid/account/search/{mdn}", method = RequestMethod.POST)
	public String search(@PathVariable("mdn") String mdn) {

		logger.debug("Search Datagrid : MDN :" + mdn);

		String url = gridUrl;
		String custId = null;
		String acctNo = null;
		String responseString = null;
		Map<String,String> map = getDetails(mdn);
		if(null!=map && !map.isEmpty()){
			custId = map.get("custId");
			acctNo = map.get("acctNo");
			logger.debug("Search Datagrid : Cust ID : " + custId);
			logger.debug("Search Datagrid : Account Number : " + acctNo);
			if (url==null || (url!=null && url.isEmpty())) {
				//default url
				url = "http://saswdscpsat08.sdc.vzwcorp.com:9110/scmgridservices/GridDataServiceServlet";
			}
			StringBuffer strXML = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?><scm:mcselements xmlns:scm=\"http://www.vzw.com/namespaces/scm\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.vzw.com/namespaces/scm mcsdataservices.xsd\">");
			strXML.append("<scm:request><scm:transId>542</scm:transId><scm:getter><scm:type>PricingLabCustomer</scm:type><scm:seqNo>2</scm:seqNo>");
			strXML.append("<scm:criteria><scm:expr><scm:operator>IN</scm:operator><scm:attribute>bsCustId</scm:attribute>");
			strXML.append("<scm:value>");
			strXML.append(custId);
			strXML.append("</scm:value></scm:expr><scm:expr><scm:operator>IN</scm:operator><scm:attribute>bsAcctNum</scm:attribute><scm:value>");
			strXML.append(acctNo);
			strXML.append("</scm:value></scm:expr></scm:criteria></scm:getter></scm:request></scm:mcselements>");
			//responseString = postHttpRequest(url, strXML.toString());
			responseString = pricingLabUtility.postRequest(gridUrl, strXML.toString(), ContentType.APPLICATION_XML);
		}else{
			responseString = "Error";
			logger.debug("In search, No record found with MDN : "+mdn);
		}
		return responseString; 
	}
	
	@RequestMapping(value = "/grid/customer/{custId}", method = RequestMethod.GET)
	public String getCustomer(@PathVariable("custId") String custId) {

		logger.debug("Search Datagrid : custId :" + custId);

		String url = gridGetUrl;
		if(url == null || url.isEmpty())
			url = "http://saswdscpsat02.sdc.vzwcorp.com:9102/scmgridservices/GridDataServiceServlet";
		
		String responseString = null;

		StringBuffer strXML = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		strXML.append("<scm:mcselements xmlns:scm=\"http://www.vzw.com/namespaces/scm\" ");
		strXML.append("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
		strXML.append("xsi:schemaLocation=\"http://www.vzw.com/namespaces/scm mcsdataservices.xsd\">");
		strXML.append("<scm:request><scm:transId>");
		strXML.append(GRID_TX_ID_FORMAT.format(new Date())); 
		strXML.append("</scm:transId>");
		strXML.append("<scm:getter><scm:type>COPS_CustomerAccountDetails</scm:type>");
		strXML.append("<scm:seqNo>1</scm:seqNo>");
		strXML.append("<scm:criteria><scm:expr><scm:operator>EQ</scm:operator>");
		strXML.append("<scm:attribute>custId</scm:attribute>");
		strXML.append("<scm:value>");
		strXML.append(custId);
		strXML.append("</scm:value>");
		strXML.append("</scm:expr></scm:criteria></scm:getter></scm:request></scm:mcselements>");
		
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("requestXml", strXML.toString());
		
		responseString = pricingLabUtility.postHttpRequestWithURLParams(url, queryParams);

		return responseString;
	}

	@RequestMapping(value = "/grid/customer/details/{custId}/{acctNum}", method = RequestMethod.GET)
	public String getCustomerAccountDetails(@PathVariable("custId") String custId,@PathVariable("acctNum") String acctNum) {

		logger.debug("Search Datagrid : custId : {} , acctNum : {}",custId,acctNum);

		String url = gridGetUrl;
		if(url == null || url.isEmpty())
			url = "http://saswdscpsat02.sdc.vzwcorp.com:9102/scmgridservices/GridDataServiceServlet";

		String responseString = null;

		StringBuffer strXML = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		strXML.append("<scm:mcselements xmlns:scm=\"http://www.vzw.com/namespaces/scm\" ");
		strXML.append("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
		strXML.append("xsi:schemaLocation=\"http://www.vzw.com/namespaces/scm mcsdataservices.xsd\">");
		strXML.append("<scm:request><scm:transId>");
		strXML.append(GRID_TX_ID_FORMAT.format(new Date()));
		strXML.append("</scm:transId>");
		strXML.append("<scm:getter><scm:type>ReverseCustomerUnique</scm:type>");
		strXML.append("<scm:seqNo>1</scm:seqNo>");
		strXML.append("<scm:criteria><scm:expr><scm:operator>EQ</scm:operator>");
		//customer id
		strXML.append("<scm:attribute>bsCustId</scm:attribute>");
		strXML.append("<scm:value>");
		strXML.append(custId);
		strXML.append("</scm:value>");
		strXML.append("</scm:expr>");
		//acct num
		strXML.append("<scm:expr><scm:operator>EQ</scm:operator>");
		strXML.append("<scm:attribute>bsAcctNum</scm:attribute>");
		strXML.append("<scm:value>");
		strXML.append(acctNum);
		strXML.append("</scm:value>");

		strXML.append("</scm:expr></scm:criteria></scm:getter></scm:request></scm:mcselements>");

		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("requestXml", strXML.toString());

		responseString = pricingLabUtility.postHttpRequestWithURLParams(url, queryParams);

		return responseString;
	}

	private Map<String,String> getDetails(String mdn) {
		String acctNo = null;
		String custId = null;
		Timestamp mtnEffDtTs = null;
		String mtnEffDtStrDate = null;
		Map<String,String> map = null;
		SubCustAcctMdn subCustAcctMdn = null;
		SimpleDateFormat format = new SimpleDateFormat(dateformat);
		Date mtnEffDt = null;
		List<SubCustAcctMdn> subCustAcctMdnList =subCustRepo.getCustAcctDetails(mdn);
		if (subCustAcctMdnList != null && subCustAcctMdnList.size() > 0) {
			map = new HashMap<String,String>();
			subCustAcctMdn = subCustAcctMdnList.get(0);
			acctNo = String.valueOf(subCustAcctMdn.getAcctNo());
			custId = String.valueOf(subCustAcctMdn.getCustIdNo());
			mdn = subCustAcctMdn.getMdn();
			mtnEffDtTs = subCustAcctMdn.getMtnEffDt();
			mtnEffDt = new Date(mtnEffDtTs.getTime());
			mtnEffDtStrDate = format.format(mtnEffDt);
			map.put("acctNo", acctNo);
			map.put("custId", custId);
			map.put("mdn", mdn);
			map.put("mtnEffDate", mtnEffDtStrDate);
		}
		return map;		
	}

	
	@RequestMapping(value = "/grid/customer/{custId}/account/{accountNumber}", method = RequestMethod.GET)
	public String getCustomerBillCycle(@PathVariable("custId") Long custId, @PathVariable("accountNumber") Long accountNumber) {

		logger.debug("Customer BillCycle Datagrid : custId :" + custId+ " account: "+ accountNumber);

		String url = gridGetUrl;
		if(url == null || url.isEmpty())
			url = "http://saswdscpsat02.sdc.vzwcorp.com:9102/scmgridservices/GridDataServiceServlet";
		
		String responseString = null;

		StringBuffer strXML = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		strXML.append("<scm:mcselements xmlns:scm=\"http://www.vzw.com/namespaces/scm\" ");
		strXML.append("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
		strXML.append("xsi:schemaLocation=\"http://www.vzw.com/namespaces/scm mcsdataservices.xsd\">");
		strXML.append("<scm:request><scm:transId>");
		strXML.append(GRID_TX_ID_FORMAT.format(new Date())); 
		strXML.append("</scm:transId>");
		strXML.append("<scm:getter><scm:type>CustBill</scm:type>");
		strXML.append("<scm:seqNo>1</scm:seqNo>");
		strXML.append("<scm:criteria><scm:expr><scm:operator>EQ</scm:operator>");
		strXML.append("<scm:attribute>custId</scm:attribute>");
		strXML.append("<scm:value>");
		strXML.append(Long.toString(custId));
		strXML.append("</scm:value>");
		strXML.append("</scm:expr>");
		strXML.append("<scm:expr><scm:operator>EQ</scm:operator>");
		strXML.append("<scm:attribute>acctNo</scm:attribute>");
		strXML.append("<scm:value>");
		strXML.append(accountNumber);
		strXML.append("</scm:value>");
		strXML.append("</scm:expr></scm:criteria>");
		strXML.append("</scm:getter></scm:request></scm:mcselements>");
		
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("requestXml", strXML.toString());
		
		responseString = pricingLabUtility.postHttpRequestWithURLParams(url, queryParams);
		int n= responseString.indexOf("<blCycNo>");
		if ( n == -1 ){
			logger.debug(" No BillCycleNo element in response");
			return "0";
		}
		int m= responseString.indexOf("</blCycNo>", n);
		if ( m == 1 ){
			logger.debug(" No BillCycleNo element in response");
			return "0";
		}
		String billCycleNo= responseString.substring(n+"<blCycNo>".length(), m);
		// <blCycNo>02</blCycNo>
		return billCycleNo;
	}


	
	private String postHttpRequest(String url, String body) {
		logger.debug("Post Request: URL : " + url);				
		HttpPost post = null;
		String responseString = null;
		HttpClient client = null;
		URIBuilder builder = null;
		HttpEntity entity = null;
		HttpResponse response = null;
		logger.debug("Request : "+body);
		try {
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectionRequestTimeout(connectionRequestTimeOut)
					.setConnectTimeout(connectionTimeOut)
					.setSocketTimeout(connectionSocketTimeOut)
					.build();
			client = HttpClientBuilder.create().build();
			builder = new URIBuilder(url);
			post = new HttpPost(builder.build());
			post.addHeader("accept", "application/xml");
			entity = new ByteArrayEntity(body.getBytes("UTF-8"));
			post.setEntity(entity);
			post.setConfig(requestConfig);
			response = client.execute(post);
			logger.debug("Status Code : " + response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				logger.debug("Method failed: " + response.getStatusLine());
			}
			else{				
				responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			if (post!= null)
				post.releaseConnection();
		}
		logger.debug("Response : "+responseString);
		return responseString;
	}
	
	private String postHttpRequestWithURLParams(String url, Map<String, String> queryParams) {
		logger.debug("Post Request: URL : " + url);				
		HttpPost post = null;
		String responseString = null;
		HttpClient client = null;
		URIBuilder builder = null;
		HttpEntity entity = null;
		HttpResponse response = null;
		logger.debug("queryParams : "+queryParams);
		try {
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectionRequestTimeout(connectionRequestTimeOut)
					.setConnectTimeout(connectionTimeOut)
					.setSocketTimeout(connectionSocketTimeOut)
					.build();
			client = HttpClientBuilder.create().build();
			
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			
			for(Entry<String, String> entry:queryParams.entrySet()){
				urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}

			builder = new URIBuilder(url);
			post = new HttpPost(builder.build());
			entity = new UrlEncodedFormEntity(urlParameters);
			post.setEntity(entity);
			post.setConfig(requestConfig);
			
			logger.debug("URI: "+post.getURI());
			logger.debug("Entity: "+post.getEntity());
			response = client.execute(post);
			logger.debug("Status Code : " + response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				logger.debug("Method failed: " + response.getStatusLine());
			}
			else{				
				responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			if (post!= null)
				post.releaseConnection();
		}
		logger.debug("Response : "+responseString);
		return responseString;
	}

	
	public Document getParsedDocument(String xml) throws Exception{
		InputSource src = new InputSource();
		src.setCharacterStream(new StringReader(xml));
		Document doc = builder.parse(src);
		return doc;
	}
	
	@PostConstruct
	public void initializeDocumentBuilder(){
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			logger.fatal("Exception while creating the instance for DocumentBuilder",e);
		}
	}
	
	private String generateGridXml(Long custId,Long acctNo,String mdn,Long indicator,String operation,String transId){
		StringBuilder strXML = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		strXML.append("<scm:mcselements xmlns:scm=\"http://www.vzw.com/namespaces/scm\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.vzw.com/namespaces/scm mcsdataservices.xsd\">");
		strXML.append("<scm:request>");
		strXML.append("<scm:transId>"+transId+"</scm:transId>");
		strXML.append("<scm:setter>");
		strXML.append("<scm:type>PricingLabCustomer</scm:type>");
		strXML.append("<scm:operation>"+operation+"</scm:operation>");
		if(!operation.equalsIgnoreCase("DELETE")){
			strXML.append("<scm:row>");
			strXML.append("<scm:attributes>");
			strXML.append("<scm:attribute name=\"bsCustId\" value=\"" + custId + "\"/>");
			strXML.append("<scm:attribute name=\"bsAcctNum\" value=\"" + acctNo + "\"/>");
			strXML.append("<scm:attribute name=\"indicator\" value=\"" + indicator + "\"/>");
			strXML.append("</scm:attributes>");
			strXML.append("</scm:row>");
		}
		strXML.append("<scm:criteria>");
		strXML.append("<scm:expr>");
		strXML.append("<scm:operator>EQ</scm:operator>");
		strXML.append("<scm:attribute>bsCustId</scm:attribute>");
		strXML.append("<scm:value>" + custId + "</scm:value>");
		strXML.append("</scm:expr>");
		strXML.append("<scm:expr>");
		strXML.append("<scm:operator>EQ</scm:operator>");
		strXML.append("<scm:attribute>bsAcctNum</scm:attribute>");
		strXML.append("<scm:value>" + acctNo + "</scm:value>");
		strXML.append("</scm:expr>");
		strXML.append("</scm:criteria>");
		strXML.append("</scm:setter>");
		strXML.append("</scm:request>");
		strXML.append("</scm:mcselements>");
		logger.info("Grid request XML for mdn : "+mdn+" is "+strXML.toString());
		return strXML.toString();
		
	}
	
	
}