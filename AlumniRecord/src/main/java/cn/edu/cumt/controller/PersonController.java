package cn.edu.cumt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.cumt.domain.Person;
import cn.edu.cumt.service.PersonService;

/**
 * 处理人员请求
 * */
@Controller
@RequestMapping(value="/")
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
		// 根据编号查询人员
		Person person = personService.getByPersonId("123456");
		// 将集合添加到model当中
//		model.addAttribute("personName", person.getPersonName());
		// 跳转到main页面
		return "loginForm";
	}
	
}
