package com.sapient.aem.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * When the servlet is instantiated( during first request), it invokes init()
 * method which will store the init values in ServletConfig object. The web container
 * creates ServletConfig object during instantiation of the servlet
 */
//http://localhost:9090/first-web-app/hello


//http://localhost:9090/first-web-app/hello?userid=Srini&password=Srini@123
@WebServlet(name="/HelloWorldServlet",urlPatterns = "/hello",   
		initParams = {
		@WebInitParam(name="param1",value = "hello"),
		@WebInitParam(name="param2", value="Welcome")
		
})
public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out =response.getWriter();
			//write into response object using PrintWriter object
			//generating dynamic content
//			out.println("<html><body><h1><font color='red'>"+"Request Header Information"+"</font></h1></body></html>");
//			//enumeration object contains request header names
//			Enumeration<String> enumeration= request.getHeaderNames();
//			while(enumeration.hasMoreElements()) {
//				String headerName = enumeration.nextElement();
//				//request.getHeader(headerName) returns value in the request header
//				String headerValue=request.getHeader(headerName);
//				out.println("<h2>" + headerName+ ":"+headerValue+ "<h2>");
//			}
			//default: text/html
//			response.setContentType("text/xml");
//			response.setContentType("text/plain");
			String queryString= request.getQueryString();
			out.println("<html><body><h2>"+"Query String : " +queryString+"</h2>");
			String userid = request.getParameter("userid");
			String password= request.getParameter("password");
			out.println("<h2>"+"Userid: " +userid+"</h2>");
			out.println("<h2>"+"Password : " +password+"</h2></body></html>");
			
			//get the reference of ServletConfig object
			ServletConfig config= this.getServletConfig();	
			
			String param1= config.getInitParameter("param1");
			String param2= config.getInitParameter("param2");
			out.println("<h3>"+ param1+"," +param2  +  "</h3>");
		   
			//get the reference of ServletContext object
			ServletContext context= this.getServletContext();
			String databaseName= context.getInitParameter("database");
			out.println("<h2>"+databaseName+"</h2>");

		}catch(Exception e) {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}


