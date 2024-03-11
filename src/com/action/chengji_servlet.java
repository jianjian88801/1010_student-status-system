package com.action;

import java.io.IOException;
/**
 * 成绩模块的控制层
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
import com.service.liuService;

public class chengji_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		if(type.endsWith("chengjiMana"))
		{
			chengjiMana(req, res);
		}
		if(type.endsWith("chengjiAdd"))
		{
			chengjiAdd(req, res);
		}
		if(type.endsWith("chengjiDel"))
		{
			chengjiDel(req, res);
		}
	}
	
	
	public void chengjiAdd(HttpServletRequest req,HttpServletResponse res)
	{
		int stu_id=Integer.parseInt(req.getParameter("stu_id"));
		int kecheng_id=Integer.parseInt(req.getParameter("kecheng_id"));
		int chengji=Integer.parseInt(req.getParameter("chengji"));
		String xuenian=req.getParameter("xuenian");
		String del="no";
		String sql="insert into t_chengji values(default,?,?,?,?,?)";
		Object[] params={stu_id,kecheng_id,chengji,xuenian,del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "chengji?type=chengjiMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	public void chengjiDel(HttpServletRequest req,HttpServletResponse res)
	{
		String sql="update t_chengji set del='yes' where id="+Integer.parseInt(req.getParameter("id"));
		Object[] params={};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "chengji?type=chengjiMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}

	public void chengjiMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List chengjiList=new ArrayList();
		String sql="select * from t_chengji where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tchengji chengji=new Tchengji();
				chengji.setId(rs.getInt("id"));
				chengji.setStu_id(rs.getInt("stu_id"));
				chengji.setKecheng_id(rs.getInt("kecheng_id"));
				chengji.setChengji(rs.getInt("chengji"));
				chengji.setXuenian(rs.getString("xuenian"));
				chengji.setStu_xuehao(liuService.getStuXuehao(rs.getInt("stu_id")));
				chengji.setKecheng_name(liuService.getKechengName(rs.getInt("kecheng_id")));
				chengjiList.add(chengji);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("chengjiList", chengjiList);
		req.getRequestDispatcher("admin/chengji/chengjiMana.jsp").forward(req, res);
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
