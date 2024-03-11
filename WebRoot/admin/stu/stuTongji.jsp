<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
		 <script type='text/javascript' src='<%=path %>/dwr/interface/loginService.js'></script>
		<script type='text/javascript' src='<%=path %>/dwr/engine.js'></script>
		<script type='text/javascript' src='<%=path %>/dwr/util.js'></script>
        <script language="javascript">
           
           var i=0;
           function banjiAll()
           {
               if(i==0)
               {
                   document.getElementById("indicator").style.display="block";
                   loginService.banjiAll(callback);
                   i=1;
               }
               
           }
           function callback(data)
           {
               document.getElementById("indicator").style.display="none";
               DWRUtil.addOptions("banji_id",data,"id","name");
           }
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
		   <br></br>
		   <form action="<%=path %>/stu?type=stuTongji" name="fomr1" method="post">
		    <table border="0">
		           <tr> 
		               <td> 
		                  班级： 
		                  <select name="banji_id" id="banji_id" onclick="banjiAll()">
				              <option value="0">请选择班级</option>
				          </select>
				          <input type="submit" value="统计"/>
		               </td>
		               <td>
		                  <img id="indicator" src="<%=path %>/img/loading.gif" style="display:none"/>
		               </td>
		               
		           </tr>
		    </table>
		   </form>
	</body>
</html>
