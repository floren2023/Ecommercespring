package com.curso.ecommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired 
	private IUsuarioService usuarioService;
	private final Logger logger=LoggerFactory.getLogger(UsuarioController.class);
	
	@GetMapping("/registro")
	public String create(Model model) {
		Usuario usuario=new Usuario();
		 model.addAttribute("usuario",usuario);
		return "usuario/registro";
	}
	@PostMapping("/usuario/save")
	public String save(Usuario usuario) {
		logger.info("usuario={}",usuario.getName());
		usuario.setType("USER");
		usuarioService.save(usuario);
		return "redirect:/";
	}

}
