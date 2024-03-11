
package com.orm;
/**
 * 学籍的实体类
 * @author Administrator
 *
 */
public class Tstu
{
	private int id;
	private String xuehao;
	private String name1;
	private String sex;
	private int age;
    private int banji_id;
    private String ruxueshijian;
    private String biyeshijian;
    private String xuezhi;
    private String xuexiao;
    
    private String banji_name;

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}


	public String getBanji_name()
	{
		return banji_name;
	}

	public void setBanji_name(String banji_name)
	{
		this.banji_name = banji_name;
	}

	public int getBanji_id()
	{
		return banji_id;
	}

	public void setBanji_id(int banji_id)
	{
		this.banji_id = banji_id;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getBiyeshijian()
	{
		return biyeshijian;
	}

	public void setBiyeshijian(String biyeshijian)
	{
		this.biyeshijian = biyeshijian;
	}

	public String getXuexiao()
	{
		return xuexiao;
	}

	public void setXuexiao(String xuexiao)
	{
		this.xuexiao = xuexiao;
	}

	public String getXuezhi()
	{
		return xuezhi;
	}

	public void setXuezhi(String xuezhi)
	{
		this.xuezhi = xuezhi;
	}

	public String getName1()
	{
		return name1;
	}

	public void setName1(String name1)
	{
		this.name1 = name1;
	}

	public String getRuxueshijian()
	{
		return ruxueshijian;
	}

	public void setRuxueshijian(String ruxueshijian)
	{
		this.ruxueshijian = ruxueshijian;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getXuehao()
	{
		return xuehao;
	}

	public void setXuehao(String xuehao)
	{
		this.xuehao = xuehao;
	}
    
}
