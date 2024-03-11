
package com.orm;
/**
 * 成绩的实体类
 * @author Administrator
 *
 */
public class Tchengji
{
	private int id;
	private int stu_id;
	private int kecheng_id;
    private int chengji;
	private String xuenian;
	private String stu_xuehao;
	private String kecheng_name;
	
	public int getChengji()
	{
		return chengji;
	}
	public void setChengji(int chengji)
	{
		this.chengji = chengji;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getStu_id()
	{
		return stu_id;
	}
	public void setStu_id(int stu_id)
	{
		this.stu_id = stu_id;
	}
	public String getXuenian()
	{
		return xuenian;
	}
	public void setXuenian(String xuenian)
	{
		this.xuenian = xuenian;
	}
	
	public String getKecheng_name()
	{
		return kecheng_name;
	}
	public void setKecheng_name(String kecheng_name)
	{
		this.kecheng_name = kecheng_name;
	}
	public int getKecheng_id()
	{
		return kecheng_id;
	}
	public void setKecheng_id(int kecheng_id)
	{
		this.kecheng_id = kecheng_id;
	}
	public String getStu_xuehao()
	{
		return stu_xuehao;
	}
	public void setStu_xuehao(String stu_xuehao)
	{
		this.stu_xuehao = stu_xuehao;
	}
	
}
