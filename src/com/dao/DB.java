package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 与数据库的连接层
 * @author Administrator
 *
 */
public class DB
{
	private Connection con;

	private PreparedStatement pstm;

	private String user = "root";//数据库的连接名

	private String password = "root";//数据库的连接密码，需要改成你自己的

//	private String className = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String className = "com.mysql.jdbc.Driver";

//	private String url = "jdbc:sqlserver://localhost:1433;databaseName=db_xueji";
	
	private String url = "jdbc:mysql://localhost:3000/student-status-system?useUnicode=true&characterEncoding=utf-8";
	
	/*
	 * private String url =
	 * "jdbc:mysql://localhost:3306/db_xueji?useUnicode=true&amp;amp;amp;amp;amp;amp;characterEncoding=utf-8";
	 */

	public DB()
	{
		try
		{
			Class.forName(className);
		} catch (ClassNotFoundException e)
		{
			System.out.println("加载数据库驱动失败！");
			e.printStackTrace();
		}
	}

	/** 创建数据库连接 */
	public Connection getCon()
	{
		try
		{
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e)
		{
			System.out.println("创建数据库连接失败！");
			con = null;
			e.printStackTrace();
		}
		return con;
	}

	public void doPstm(String sql, Object[] params)
	{
		if (sql != null && !sql.equals(""))
		{
			if (params == null)
				params = new Object[0];

			getCon();
			if (con != null)
			{
				try
				{
					System.out.println(sql);
					pstm = con.prepareStatement(sql,
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
					for (int i = 0; i < params.length; i++)
					{
						pstm.setObject(i + 1, params[i]);
					}
					pstm.execute();
				} catch (SQLException e)
				{
					System.out.println("doPstm()方法出错！");
					e.printStackTrace();
				}
			}
		}
	}

	public ResultSet getRs() throws SQLException
	{
		return pstm.getResultSet();
	}

	public int getCount() throws SQLException
	{
		return pstm.getUpdateCount();
	}

	public void closed()
	{
		try
		{
			if (pstm != null)
				pstm.close();
		} catch (SQLException e)
		{
			System.out.println("关闭pstm对象失败！");
			e.printStackTrace();
		}
		try
		{
			if (con != null)
			{
				con.close();
			}
		} catch (SQLException e)
		{
			System.out.println("关闭con对象失败！");
			e.printStackTrace();
		}
	}
}
