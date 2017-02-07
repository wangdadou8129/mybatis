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
	private SqlSessionFactory factory;  //�̰߳�ȫ
	
	@Before
	public void init() throws IOException{
		//1.���ļ��л�ȡһ����
		InputStream is = Resources.getResourceAsStream("SqlSessoinConfig.xml");
		//2.ͨ��������SqlSessionFactory����
		factory = new SqlSessionFactoryBuilder().build(is);
	}
	
	@Test
	//ͨ��mapper��id�͵��õķ����ķ�����һ��
	public void findAll(){
		//1.ͨ��factory�õ�sqlSession
		SqlSession session = factory.openSession();  //�̲߳���ȫ��
		//2.���������ļ���findAll
		List<Person> persons = session.selectList("com.yztc.mapper.PersonMapper.findAll");
		
		for(Person p : persons){
			System.out.println(p);
		}
	}
	
	@Test
	//����ID�������û�
	public void findById(){
		SqlSession session = factory.openSession();
		Person p = session.selectOne("com.yztc.mapper.PersonMapper.findById",2);
		
//		Map<String,Integer> map = new HashMap<String,Integer>();
//		map.put("id", 3);
//		Person p = session.selectOne("com.yztc.mapper.PersonMapper.findById", map);
		
		System.out.println(p);
	}
	
	
	@Test
	//����һ������
	public void save(){
		SqlSession session = factory.openSession();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", 5);
		map.put("user_name", "zhangtai");
		map.put("age", 23);
		map.put("remark", "dalian");
		session.insert("com.yztc.mapper.PersonMapper.save",map);
		//���������Լ��ύ
		session.commit();
	}
	
	
	@Test
	//����һ����¼
	public void update(){
		SqlSession session = factory.openSession();
		Person p = new Person();
		p.setId(6);
		p.setName("chenmingyang");
		p.setAge(32);
		p.setRemark("dashiqiao");
		session.update("com.yztc.mapper.PersonMapper.update",p);
		//�ύ
		session.commit();
	}
	
	@Test
	//ɾ��һ����¼
	public void delete(){
		SqlSession session = factory.openSession();
		session.delete("com.yztc.mapper.PersonMapper.delete", 6);
		session.commit();
	}
	
	@Test
	//ɾ��������¼
	public void deleteMore(){
		SqlSession session = factory.openSession();
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("startId", 5);
		map.put("endId", 7);
		
		session.delete("com.yztc.mapper.PersonMapper.deleteMore", map);
		session.commit();
	}
	
	@Test
	//ͳ�Ƽ�¼��
	public void count(){
		SqlSession session = factory.openSession();
		int i = session.selectOne("com.yztc.mapper.PersonMapper.count");
		System.out.println("count="+i);
	}
	
}
