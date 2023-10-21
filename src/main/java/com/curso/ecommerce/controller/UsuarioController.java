package com.curso.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.ecommerce.model.Orden;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.service.IOrdenService;
import com.curso.ecommerce.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired 
	private IUsuarioService usuarioService;
	@Autowired 
	private IOrdenService ordenService;
	
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
	@GetMapping("/login")
	public String login() {
		
		return "usuario/login";
	}
	
	@PostMapping("/acceder")
	public String acceder(Usuario usuario,HttpSession session) {
		logger.info("usuario={}",usuario.getEmailId());
		
		Optional<Usuario> user=usuarioService.findByEmailId(usuario.getEmailId());
		//logger.info("usuario desde db={}",user.get().getName());
		if(user.isPresent()) {
			session.setAttribute("userid", user.get().getId());
			if(user.get().getType().equals("ADMIN")){
			  return "redirect:/admin";
			}else {
				return "redirect:/";
			}
		}else {
			logger.info("Usuario no existe");
		}
				
		return "redirect:/";
	}
	
	@GetMapping("/compras")
	public String obtenerCompras(Model model,HttpSession session) {
		model.addAttribute("sesion",session.getAttribute("userid"));
		Usuario usuario=usuarioService.findById((Long) session.getAttribute("userid")).get();
		List<Orden> ordenes=ordenService.findByUsuario(usuario);
		model.addAttribute("ordenes",ordenes);
		return "usuario/compras";
	}
	
	@GetMapping("/detalle/{id}")
	public String detalleCompra(@PathVariable Long id,HttpSession session,Model model) {
		//session
		model.addAttribute("sesion",session.getAttribute("userid"));
		logger.info("ordenid={}",id);
		Optional<Orden> orden=ordenService.findById(id);
		model.addAttribute("detalles",orden.get().getDetalle());
		return "usuario/detallecompra";
	}
	@GetMapping("/cerrar")
	public String cerrarSesion(HttpSession session) {
		session.removeAttribute("userid");
		return "redirect:/";
	}
}
