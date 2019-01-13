<%-- 
    Document   : in1
    Created on : Feb 1, 2014, 11:06:15 AM
    Author     : akhianchu
Second web page...... 
Uploading Input files......
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <br/>
        <a href="Inputformat.xls">SAMPLE INPUT</a>
       <h3>File Upload:</h3>
Select a file to upload: <br />
<form action="newjsp.jsp" method="post"
                        enctype="multipart/form-data">
<input type="file" name="file" size="50" />
<input type="file" name="file 2" value="" />
<input type="file" name="xx" value="" />
<br />
<input type="submit" value="Upload File" />
</form>

    </body>
</html>--%>



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
  
        </ul>
      </div><!--close menubar-->	
	</nav>	
    
	<div id="site_content">		
	
      <div class="slideshow">
	    <ul class="slideshow">
          <li class="show"><img width="940" height="300" src="images/i8.jpg"  /></li>
          <li><img width="940" height="300" src="images/i9.jpg" /></li>
        </ul> 
	  </div><!--close slideshow-->		

        <br/>
        <br/>
      <!--close sidebar_container-->	
	
	  <div id="content">
        <div class="content_item"><!--Here goes uploading code.................-->
          <form action="upload1.jsp" method="post"
                        enctype="multipart/form-data">
           
          <div id="apDiv1">
            <p>Upload subject allocation details here:</p>
            <p><a href="syllabusformat.xls"><strong>SAMPLE SYLLABUS FORMAT</strong></a> </p>
            <p>&nbsp;</p>
            <p>
              <strong>AEI	  :</strong>
              <strong>
              <input type="file" name="file4" value="" />
            </strong></p>
            <p><strong>CE:</strong>
              <input type="file" name="file5" value="" />
            </p>
            <p><strong>CSE:</strong>
<input type="file" name="file6" value="" />
            </p>
            <p>
              <strong>ECE:</strong>
              <input type="file" name="file7" value="" />
            </p>
            <p>
              <strong>EEE:</strong>
              <input type="file" name="file8" value="" />
            </p>
            <p>
              <strong>IT:</strong>
              <input type="file" name="file9" value="" />
            </p>
            <p><strong>ME:</strong>
<input type="file" name="file3" value="" />
            </p>
          </div>
          <div id="apDiv2">
            <p>Upload subject allocation details here:</p>
            <p><a href="Inputformat.xls">SAMPLE I<strong>NPUT</strong></a></p>
            <p><strong><br />
            </strong></p>
            <p>
              <strong>AEI:</strong>
              <input type="file" name="file1" value="" />
              </p>
              <p><strong>CE:</strong>
<input type="file" name="file2" value="" />
              </p>
              <p>
                <strong>CSE:</strong>
                <input type="file" name="file12" value="" />
              </p>
              <p>
                <strong>ECE:</strong>
                <input type="file" name="file10" value="" />
              </p>
              <p>
                <strong>EEE:</strong>
                <input type="file" name="file13" value="" />
              </p>
              <p>
                <strong>IT:</strong>
<input type="file" name="file11" value="" />
              </p>
              <p>
                <strong>ME:</strong>
                <input type="file" name="file14" value="" />
              </p>
             </div>
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
            <p>&nbsp;</p>
            <div id="apDiv3">
              <input type="submit" value="Upload File" />
            </div>
            </form>
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

