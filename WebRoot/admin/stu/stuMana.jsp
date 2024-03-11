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
           function stuDel(id)
           {
               if(confirm('您确定删除吗？'))
               {
                   window.location.href="<%=path %>/stu?type=stuDel&id="+id;
               }
           }
           
           function stuAdd()
           {
                 var url="<%=path %>/admin/stu/stuAdd.jsp";
				 window.location.href=url;
           }
           function p()
           {
              window.print();
           }
           
           
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
           
           function check1()
           {
              if(document.form11.xuehao.value=="")
              {
                  alert("请输入学号");
                  return false;
              }
              return true;
           }
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="10" background="<%=path %>/img/tbg.gif">&nbsp;学生学籍管理&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="10%">学号</td>
					<td width="10%">姓名</td>
					<td width="10%">性别</td>
					<td width="10%">年龄</td>
					<td width="10%">班级</td>
					<td width="10%">入学时间</td>
					<td width="10%">毕业时间</td>
					<td width="10%">学制</td>
					<td width="10%">学校名称</td>
					<td width="10%">操作</td>
		        </tr>	
		        <c:if test="${requestScope.msg==0}">
		            <tr>
		               <td colspan="10" align="center" style="color: red">学生不存在。请重新查询</td>
		            </tr>
		        </c:if>
				<c:forEach items="${requestScope.stuList}" var="stu">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						<a href="<%=path %>/stu?type=stuDetail&id=${stu.id}">${stu.xuehao}</a>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${stu.name1}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${stu.sex}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${stu.age}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${stu.banji_name}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${stu.ruxueshijian}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${stu.biyeshijian}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${stu.xuezhi}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${stu.xuexiao}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <form action="<%=path %>/admin/stu/stuEditPre.jsp" method="post">
						    <input type="button" value="删除" onclick="stuDel(${stu.id})" class="pn-loperator"/>
						    <input type="hidden" name="id" value="${stu.id}"/>
						    <input type="hidden" name="xuehao" value="${stu.xuehao}"/>
						    <input type="hidden" name="name1" value="${stu.name1}"/>
						    <input type="hidden" name="sex" value="${stu.sex}"/>
						    <input type="hidden" name="age" value="${stu.age}"/>
						    <input type="hidden" name="banji_name" value="${stu.banji_name}"/>
						    <input type="hidden" name="ruxueshijian" value="${stu.ruxueshijian}"/>
						    <input type="hidden" name="biyeshijian" value="${stu.biyeshijian}"/>
						    <input type="hidden" name="xuezhi" value="${stu.xuezhi}"/>
						    <input type="hidden" name=xuexiao value="${stu.xuexiao}"/>
						    <input type="submit" value="修改"/>
						</form>
					</td>
				</tr>
				</c:forEach>
			</table>
			
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 5px;">
			  <tr>
			    <td>
			         <input type="button" value="添加" style="width: 80px;" onclick="stuAdd()" />
			         <input type="button" value="打印" style="width: 80px;" onclick="p()" />
			    </td>
			  </tr>
			</table>
			<br/>
			
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 5px;">
			  <tr>
			    <td>
			         <form action="<%=path %>/stu?type=stuSearchByXuehao" name="form11" method="post">
			                         学号：
			             <input type="text" name="xuehao" style="width: 130px;"/>
			             <input type="submit" value="查询" onclick="return check1()"/>
			         </form>
			    </td>
			  </tr>
			  <tr>
			    <td>
			         <form action="<%=path %>/stu?type=stuTongji" name="fomr1" method="post">
			                班级： 
		                  <select name="banji_id" id="banji_id" onclick="banjiAll()" style="width: 136px;">
				              <option value="0">请选择班级</option>
				          </select>
				          <input type="submit" value="统计"/>
				          <img id="indicator" src="<%=path %>/img/loading.gif" style="display:none"/>
			         </form>
			    </td>
			  </tr>
		    </table>
	</body>
</html>
