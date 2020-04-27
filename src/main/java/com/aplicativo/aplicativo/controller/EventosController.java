package com.aplicativo.aplicativo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aplicativo.aplicativo.eventoRepository.EventoRepository;
import com.aplicativo.aplicativo.models.Eventos;

@Controller
public class EventosController {
	
	@Autowired
	private EventoRepository er;
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST)
	public String form(Eventos evento) {
		er.save(evento);// sava no datavbase
		return "redirect:/cadastrarEvento";//redireciona para o evento do GET
	}
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv= new ModelAndView("index");//
		Iterable<Eventos> eventos= er.findAll();//Interable porque vai ser uma lista de elementos
		mv.addObject("evento",eventos);
		
		return mv;	
	}

}
