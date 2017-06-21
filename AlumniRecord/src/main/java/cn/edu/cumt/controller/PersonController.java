package cn.edu.cumt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.cumt.domain.Person;
import cn.edu.cumt.service.PersonService;

/**
 * 处理图书请求控制器
 * */
@Controller
public class PersonController {
	
	/**
	 * 自动注入BookService
	 * */
	@Autowired
	@Qualifier("personService")
	private PersonService personService;

	/**
	 * 处理/main请求
	 * */
	@RequestMapping(value="/loginForm")
	 public String main(Model model, String personId, String password){
		// 获得所有图书集合
		Person person = personService.getByPersonId(personId);
		// 将集合添加到model当中
		model.addAttribute("book_list", "");
		// 跳转到main页面
		return "main";
	}
	
}
