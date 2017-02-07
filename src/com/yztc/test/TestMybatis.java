package com.yztc.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.yztc.pojo.Person;

public class TestMybatis {
	private SqlSessionFactory factory;  //线程安全
	
	@Before
	public void init() throws IOException{
		//1.从文件中获取一个流
		InputStream is = Resources.getResourceAsStream("SqlSessoinConfig.xml");
		//2.通过流创建SqlSessionFactory对象
		factory = new SqlSessionFactoryBuilder().build(is);
	}
	
	@Test
	//通常mapper的id和调用的方法的方法名一致
	public void findAll(){
		//1.通过factory得到sqlSession
		SqlSession session = factory.openSession();  //线程不安全的
		//2.调用配置文件的findAll
		List<Person> persons = session.selectList("com.yztc.mapper.PersonMapper.findAll");
		
		for(Person p : persons){
			System.out.println(p);
		}
	}
	
	@Test
	//按照ID来查找用户
	public void findById(){
		SqlSession session = factory.openSession();
		Person p = session.selectOne("com.yztc.mapper.PersonMapper.findById",2);
		
//		Map<String,Integer> map = new HashMap<String,Integer>();
//		map.put("id", 3);
//		Person p = session.selectOne("com.yztc.mapper.PersonMapper.findById", map);
		
		System.out.println(p);
	}
	
	
	@Test
	//插入一条数据
	public void save(){
		SqlSession session = factory.openSession();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", 5);
		map.put("user_name", "zhangtai");
		map.put("age", 23);
		map.put("remark", "dalian");
		session.insert("com.yztc.mapper.PersonMapper.save",map);
		//必须我们自己提交
		session.commit();
	}
	
	
	@Test
	//更新一条记录
	public void update(){
		SqlSession session = factory.openSession();
		Person p = new Person();
		p.setId(6);
		p.setName("chenmingyang");
		p.setAge(32);
		p.setRemark("dashiqiao");
		session.update("com.yztc.mapper.PersonMapper.update",p);
		//提交
		session.commit();
	}
	
	@Test
	//删除一条记录
	public void delete(){
		SqlSession session = factory.openSession();
		session.delete("com.yztc.mapper.PersonMapper.delete", 6);
		session.commit();
	}
	
	@Test
	//删除多条记录
	public void deleteMore(){
		SqlSession session = factory.openSession();
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("startId", 5);
		map.put("endId", 7);
		
		session.delete("com.yztc.mapper.PersonMapper.deleteMore", map);
		session.commit();
	}
	
	@Test
	//统计记录数
	public void count(){
		SqlSession session = factory.openSession();
		int i = session.selectOne("com.yztc.mapper.PersonMapper.count");
		System.out.println("count="+i);
	}
	
}
