package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.RedirectView;

@SpringBootApplication
@Controller
public class StaticDemoApplication {
	
	@GetMapping("/")
	public String home() {
		return "forward:/static/index.html";
	}

	public static void main(String[] args) {
		SpringApplication.run(StaticDemoApplication.class, args);
	}
}


@Component
@ControllerAdvice
class Customizer {
	@ExceptionHandler(NoHandlerFoundException.class)
	public RedirectView notFound() {
		return new RedirectView("/");
	}

}