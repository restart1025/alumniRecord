package cn.edu.cumt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.cumt.domain.Person;
import cn.edu.cumt.mapper.PersonMapper;
import cn.edu.cumt.service.PersonService;

/**
 * Book服务层接口实现类
 * @Service("bookService")用于将当前类注释为一个Spring的bean，名为bookService
 * */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("personService")
public class PersonServiceImpl implements PersonService {
	
	/**
	 * 自动注入PersonMapper
	 * */
	@Autowired
	private PersonMapper personMapper;

	/**
	 * BookService接口getByPersonId方法实现
	 * @see { BookService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public Person getByPersonId(String personId) {
		return personMapper.getByPersonId(personId);
	}

}
