package com.yztc.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.yztc.pojo.Book;
import com.yztc.pojo.Person;

public class TestMybatis2 {
	public SqlSessionFactory factory;   //线程安全
	
	@Before
	public void init() throws IOException{
		InputStream is = Resources.getResourceAsStream("SqlSessoinConfig.xml");
		factory = new SqlSessionFactoryBuilder().build(is);
	}
	
	@Test
	public void findTwoTable(){
		SqlSession session = factory.openSession();
		List<Person> persons = session.selectList("com.yztc.mapper.PersonInfoMapper.findTwoTable");
		for(Person p : persons){
			System.out.println(p);
			System.out.println(p.getPersonInfo());
		}
	}
	
	@Test
	public void findPersonAndBook(){
		SqlSession session = factory.openSession();
		List<Person> persons = session.selectList("com.yztc.mapper.PersonInfoMapper.findPersonAndBook");
		for(Person p : persons){
			System.out.println(p);
			
			List<Book> books = p.getBooks();
			
			if(books!=null && books.size()>0){
				for(Book b : books){
					System.out.println("bookId:"+b.getId()+",bookName:"+b.getName()+",bookMoney:"+b.getMoney());
				}
			}
		}
	}
	
	@Test
	public void findPersonAndPersonInfoAndBook(){
		SqlSession session = factory.openSession();
		List<Person> persons = session.selectList("com.yztc.mapper.PersonInfoMapper.findPersonAndPersonInfoAndBook");
		for(Person p : persons){
			System.out.println(p);
			System.out.println(p.getPersonInfo());
			List<Book> books = p.getBooks();
			
			if(books!=null && books.size()>0){
				for(Book b : books){
					System.out.println("bookId:"+b.getId()+",bookName:"+b.getName()+",bookMoney:"+b.getMoney());
				}
			}
		}
		
	}
}
