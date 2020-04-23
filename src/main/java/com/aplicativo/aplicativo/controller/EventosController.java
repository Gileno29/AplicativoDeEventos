package com.aplicativo.aplicativo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventosController {
	
	@RequestMapping("/cadastrarEvento")
	public String eventoController() {
		return "evento/formEvento";
	}

}
