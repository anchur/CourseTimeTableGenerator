<%-- 
    Document   : newjsp
    Created on : 8 Feb, 2014, 10:36:05 AM
    Author     : anchuakhila
Lab allocation file uploading.......
Calling functions for further generation
--%>
<%@page import="org.mypackage.files.Generate"%>
<%@page import="org.mypackage.files.TakeInput"%>
<!DOCTYPE html> 
<html>

<head>
  <title>Timetabler</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
  <link rel="stylesheet" type="text/css" href="css/style.css" />
  <style type="text/css">
  #apDiv1 {
	position: absolute;
	width: 328px;
	height: 342px;
	z-index: 1;
	left: 675px;
	top: 567px;
}
  #apDiv2 {
	position: absolute;
	width: 307px;
	height: 347px;
	z-index: 2;
	top: 567px;
	left: 233px;
}
  #apDiv3 {
	position: absolute;
	width: 200px;
	height: 115px;
	z-index: 3;
	left: 563px;
	top: 923px;
}
  </style>
  <!-- modernizr enables HTML5 elements and feature detects -->
  <script type="text/javascript" src="js/modernizr-1.5.min.js"></script>
</head>
<body>

<div id="main">
    <header>
	  <div id="banner">
	    <div id="welcome">
	      <h2>TimeTabler</h2>
	    </div><!--close welcome-->			  	
	  </div><!--close banner-->	
	</header>

	<nav>
	  <div id="menubar">
        <ul id="nav">
          <li><a href="index.jsp">Home</a></li>
          <li class="current"><a href="in1.jsp">Load Inputs</a></li>
          <li><a href="testimonials.html">View Timetable</a></li>
          <li><a href="projects.html">Projects</a></li>
          <li><a href="contact.html">Contact Us</a></li>
        </ul>
      </div><!--close menubar-->	
	</nav>	
    
	<div id="site_content">		
	
      <div class="slideshow">
	    <ul class="slideshow">
          <li class="show"><img width="940" height="300" src="images/i3.jpg"  />
            <%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
          </li>
          <li><img width="940" height="300" src="images/i6.jpg"/></li>
        </ul> 
	  </div><!--close slideshow-->		

        <br/>
        <br/>
      <!--close sidebar_container-->	
	
	  <div id="content">
        <div class="content_item">
          
<p>&nbsp;</p>
<p>&nbsp;</p>
   
<!--
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <web-app>
....
<context-param> 
    <description>Location to store uploaded file</description> 
    <param-name>file-upload</param-name> 
    <param-value>
         D:\
     </param-value> 
</context-param>
....
</web-app>-->
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>

<%
   File file ;
   int maxFileSize = 5000 * 1024;
   int maxMemSize = 5000 * 1024;
   ServletContext context = pageContext.getServletContext();
   String filePath = context.getInitParameter("file-upload");

   // Verify the content type
   String contentType = request.getContentType();
   if ((contentType.indexOf("multipart/form-data") >= 0)) {

      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File("D:\\"));

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );
      try{ 
         // Parse the request to get file items.
         List fileItems = upload.parseRequest(request);

         // Process the uploaded file items
         Iterator i = fileItems.iterator();
         filePath="D:\\";
        /* out.println("<html>");
         out.println("<head>");
         out.println("<title>JSP File upload</title>");  
         out.println("</head>");
         out.println("<body>");*/
         while ( i.hasNext () ) 
         {
            FileItem fi = (FileItem)i.next();
            if ( !fi.isFormField () )	
            {
            // Get the uploaded file parameters
            String fieldName = fi.getFieldName();
            String fileName = fi.getName();
            boolean isInMemory = fi.isInMemory();
            long sizeInBytes = fi.getSize();
            // Write the file
            if( fileName.lastIndexOf("\\") >= 0 ){
            file = new File( filePath + 
            fileName.substring( fileName.lastIndexOf("\\"))) ;
            }else{
            file = new File( filePath + 
            fileName.substring(fileName.lastIndexOf("\\")+1)) ;
            }
            fi.write( file ) ;
            //out.println("<p color:#0000>Uploaded Filename: " + filePath + 
            //fileName + "<br></p>");
            }
         }
         out.println("<p color:#0000>Files successfully uploaded</p>");
         //out.println("</html>");
      }catch(Exception ex) {
         System.out.println(ex);
      }
   }else{
      //out.println("<html>");
      //out.println("<head>");
      out.println("<title>Servlet upload</title>");  
     // out.println("</head>");
      //out.println("<body>");
      out.println("<p>No file uploaded</p>"); 
     // out.println("</body>");
      //out.println("</html>");
   }
        //TakeInput.readSubDetails("S:\\syllabusformat.xlsx");
        //TakeInput.readTchrDetails("S:\\CSInput.xlsx");
        //TakeInput.readTSCD("S:\\CSInput.xlsx");
        //TakeInput.writeXLSXFile();
        TakeInput.laballoc("D:\\lab1.xlsx");
        TakeInput.sepallocate();
       Generate.interDept();
       TakeInput.classTSCDallocate();
       TakeInput.initial_mapping();
       TakeInput.display();
%>




              <p>&nbsp;</p>
 
          
          	
          	
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
          
            <p>              <br/>
            </p>
		  <p><!--close content_container--><!--close content_container--><!--close content_container--><!--close content_container-->		</p>
        </div>
        <!--close content_item-->
      </div>
	  <!--close content-->   
  </div><!--close site_content--></div><!--close main-->

  <!-- javascript at the bottom for fast page loading -->
  <script type="text/javascript" src="js/jquery.min.js"></script>
  <script type="text/javascript" src="js/image_slide.js"></script>	
  
</body>
</html>

