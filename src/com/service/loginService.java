package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;


import com.dao.DB;
import com.orm.TAdmin;
import com.orm.Tbanji;
import com.orm.Tkecheng;
import com.orm.Tstu;
import com.orm.Tzhuanye;

public class loginService
{
	/**
	 * 用户登陆的方法
	 * @param userName
	 * @param userPw
	 * @param userType
	 * @return
	 */
	public String login(String userName,String userPw,int userType)
	{
		System.out.println("userType"+userType);
		try
		{
			Thread.sleep(700);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String result="no";
		
		if(userType==0)//系统管理员登陆
		{
			String sql="select * from t_admin where userName=? and userPw=?";
			Object[] params={userName,userPw};
			DB mydb=new DB();
			mydb.doPstm(sql, params);
			try 
			{
				ResultSet rs=mydb.getRs();
				boolean mark=(rs==null||!rs.next()?false:true);
				if(mark==false)
				{
					 result="no";
				}
				else
				{
					 result="yes";
					 TAdmin admin=new TAdmin();
					 admin.setUserId(rs.getInt("userId"));
					 admin.setUserName(rs.getString("userName"));
					 admin.setUserPw(rs.getString("userPw"));
					 WebContext ctx = WebContextFactory.get(); 
					 HttpSession session=ctx.getSession(); 
					 session.setAttribute("userType", 0);
		             session.setAttribute("admin", admin);
				}
				rs.close();
			} 
			catch (SQLException e)
			{
				System.out.println("登录失败！");
				e.printStackTrace();
			}
			finally
			{
				mydb.closed();
			}
			
		}
		
		
		if(userType==1)
		{
		}
		if(userType==2)
		{
			
		}
		return result;
	}
	/**
	 * 用户修改密码的方法
	 * @param userPwNew
	 * @return
	 */
    public String adminPwEdit(String userPwNew)
    {
		System.out.println("DDDD");
    	try 
		{
			Thread.sleep(700);
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebContext ctx = WebContextFactory.get(); 
		HttpSession session=ctx.getSession(); 
		TAdmin admin=(TAdmin)session.getAttribute("admin");
		
		String sql="update t_admin set userPw=? where userId=?";
		Object[] params={userPwNew,admin.getUserId()};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		
		return "yes";
    }
    
    /**
     * 查询所有专业
     * @return
     */
    public List zhuanyeAll()
    {
    	try
		{
			Thread.sleep(700);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	List zhuanyeList=new ArrayList();
		String sql="select * from t_zhuanye where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tzhuanye zhuanye=new Tzhuanye();
				zhuanye.setId(rs.getInt("id"));
				zhuanye.setName(rs.getString("name"));
				zhuanye.setJieshao(rs.getString("jieshao"));
				zhuanyeList.add(zhuanye);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		return zhuanyeList;
    }
    
    /**
     * 查询所有班级
     * @return
     */
    public List banjiAll()
    {
    	try
		{
			Thread.sleep(700);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	List banjiList=new ArrayList();
		String sql="select * from t_banji where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tbanji banji=new Tbanji();
				banji.setId(rs.getInt("id"));
				banji.setName(rs.getString("name"));
				banjiList.add(banji);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		return banjiList;
    }

    /**
     * 查询所有学生
     * @return
     */
    public List stuAll()
    {
    	try
		{
			Thread.sleep(700);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	List stuList=new ArrayList();
		String sql="select * from t_stu where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tstu stu=new Tstu();
				stu.setId(rs.getInt("id"));
				stu.setXuehao(rs.getString("xuehao"));
				stuList.add(stu);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		return stuList;
    }

    /**
     * 查询所有的课程
     * @return
     */
    public List kechengAll()
    {
    	try
		{
			Thread.sleep(700);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	List kechengList=new ArrayList();
		String sql="select * from t_kecheng where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tkecheng kecheng=new Tkecheng();
				kecheng.setId(rs.getInt("id"));
				kecheng.setName(rs.getString("name"));
				kechengList.add(kecheng);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		return kechengList;
    }
    /**
     * 学生选择课程
     * @param stu_id
     * @param kecheng_id
     * @return
     */  
    public String xuankeAdd(int stu_id,int kecheng_id)
    {
    	try
		{
			Thread.sleep(700);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result="no";
		String sql="select * from t_stu_kecheng where stu_id=? and kecheng_id=?";
		Object[] params={stu_id,kecheng_id};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			if(rs.next()==true)
			{
				result="no";
			}
			else
			{
				result="yes";
				String sql1="insert into t_stu_kecheng values(?,?)";
				Object[] params1={stu_id,kecheng_id};
				mydb.doPstm(sql1, params1);
			}
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		return result;
    }
    
    /**
     * 删除学生选择的课程
     * @param stu_id
     * @param kecheng_id
     * @return
     */
    public String xuankeDel(int stu_id,int kecheng_id)
    {
    	try
		{
			Thread.sleep(700);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result="no";
		String sql="delete from t_stu_kecheng where stu_id=? and kecheng_id=?";
		Object[] params={stu_id,kecheng_id};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			int ii=mydb.getCount();
			if(ii==1)
			{
				result="yes";
			}
			else
			{
				result="no";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		return result;
    }
    
}
