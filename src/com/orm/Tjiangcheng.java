package com.orm;
/**
 * 奖惩信息的实体类
 * @author Administrator
 *
 */
public class Tjiangcheng
{
	private int id;
	private int stu_id;
	private String shijian;
	private String shuxing;
	private String beizhu;
	
	private String stu_xuehao;
	
	public String getBeizhu()
	{
		return beizhu;
	}
	public void setBeizhu(String beizhu)
	{
		this.beizhu = beizhu;
	}
	public String getShijian()
	{
		return shijian;
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getStu_xuehao()
	{
		return stu_xuehao;
	}
	public void setStu_xuehao(String stu_xuehao)
	{
		this.stu_xuehao = stu_xuehao;
	}
	public void setShijian(String shijian)
	{
		this.shijian = shijian;
	}
	public String getShuxing()
	{
		return shuxing;
	}
	public void setShuxing(String shuxing)
	{
		this.shuxing = shuxing;
	}
	public int getStu_id()
	{
		return stu_id;
	}
	public void setStu_id(int stu_id)
	{
		this.stu_id = stu_id;
	}
}
