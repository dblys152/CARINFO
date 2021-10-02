package com.ys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@SpringBootApplication
public class CarinfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarinfoApplication.class, args);
	}

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String root() {
		return "redirect:carInfo/list";
	}

}
