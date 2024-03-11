package com.action;

import java.io.IOException;
/**
 * 奖惩模块的控制层
 */
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DB;
import com.orm.TAdmin;
import com.orm.Tchengji;
import com.orm.Tjiangcheng;
import com.service.liuService;

public class jiangcheng_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		if(type.endsWith("jiangchengMana"))
		{
			jiangchengMana(req, res);
		}
		if(type.endsWith("jiangchengAdd"))
		{
			jiangchengAdd(req, res);
		}
		if(type.endsWith("jiangchengDel"))
		{
			jiangchengDel(req, res);
		}
		
		if(type.endsWith("jiangchengEditPre"))
		{
			jiangchengEditPre(req, res);
		}
		if(type.endsWith("jiangchengSearch"))
		{
			jiangchengSearch(req, res);
		}
	}
	
	
	public void jiangchengAdd(HttpServletRequest req,HttpServletResponse res)
	{
		int stu_id=Integer.parseInt(req.getParameter("stu_id"));
		String shijian=req.getParameter("shijian");
		String shuxing=req.getParameter("shuxing");
		String beizhu=req.getParameter("beizhu");
		String del="no";
		String sql="insert into t_jiangcheng values(default,?,?,?,?,?)";
		Object[] params={stu_id,shijian,shuxing,beizhu,del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "jiangcheng?type=jiangchengMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	public void jiangchengDel(HttpServletRequest req,HttpServletResponse res)
	{
		String sql="update t_jiangcheng set del='yes' where id="+Integer.parseInt(req.getParameter("id"));
		Object[] params={};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "jiangcheng?type=jiangchengMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}

	public void jiangchengMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List jiangchengList=new ArrayList();
		String sql="select * from t_jiangcheng where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tjiangcheng jiangcheng=new Tjiangcheng();
				jiangcheng.setId(rs.getInt("id"));
				jiangcheng.setStu_id(rs.getInt("stu_id"));
				jiangcheng.setShijian(rs.getString("shijian"));
				jiangcheng.setShuxing(rs.getString("shuxing"));
				jiangcheng.setBeizhu(rs.getString("beizhu"));
				jiangcheng.setStu_xuehao(liuService.getStuXuehao(rs.getInt("stu_id")));
				jiangchengList.add(jiangcheng);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("jiangchengList", jiangchengList);
		req.getRequestDispatcher("admin/jiangcheng/jiangchengMana.jsp").forward(req, res);
	}
	
	
	public void jiangchengEditPre(HttpServletRequest req,HttpServletResponse res)
	{
		Integer id=Integer.parseInt(req.getParameter("id"));
		
		int stu_id=Integer.parseInt(req.getParameter("stu_id"));
		String shijian=req.getParameter("shijian");
		String shuxing=req.getParameter("shuxing");
		String beizhu=req.getParameter("beizhu");
		String del="no";
		
		String sql="update t_jiangcheng set stu_id=?,shijian=?,shuxing=?,beizhu=?,del=? where id=?";
		Object[] params={stu_id,shijian,shuxing,beizhu,del,id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "jiangcheng?type=jiangchengMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	public void jiangchengSearch(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		int stu_id=Integer.parseInt(req.getParameter("stu_id"));
		String shuxing=req.getParameter("shuxing");
		
		List jiangchengList=new ArrayList();
		StringBuffer sql=new StringBuffer("select * from t_jiangcheng where del='no' and shuxing='"+shuxing+"'");
		if(stu_id !=0)
		{
			sql.append(" and stu_id="+stu_id);
		}
		System.out.println(sql.toString()+"^^^");
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql.toString(), params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tjiangcheng jiangcheng=new Tjiangcheng();
				jiangcheng.setId(rs.getInt("id"));
				jiangcheng.setStu_id(rs.getInt("stu_id"));
				jiangcheng.setShijian(rs.getString("shijian"));
				jiangcheng.setShuxing(rs.getString("shuxing"));
				jiangcheng.setBeizhu(rs.getString("beizhu"));
				jiangcheng.setStu_xuehao(liuService.getStuXuehao(rs.getInt("stu_id")));
				jiangchengList.add(jiangcheng);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("jiangchengList", jiangchengList);
		req.getRequestDispatcher("admin/jiangcheng/jiangchengMana.jsp").forward(req, res);
	}
	
	public void dispatch(String targetURI,HttpServletRequest request,HttpServletResponse response) 
	{
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(targetURI);
		try 
		{
		    dispatch.forward(request, response);
		    return;
		} 
		catch (ServletException e) 
		{
                    e.printStackTrace();
		} 
		catch (IOException e) 
		{
			
		    e.printStackTrace();
		}
	}
	public void init(ServletConfig config) throws ServletException 
	{
		super.init(config);
	}
	
	public void destroy() 
	{
		
	}
}
