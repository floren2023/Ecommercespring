package com.curso.ecommerce.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.ecommerce.model.Orden;
import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.service.IOrdenService;
import com.curso.ecommerce.service.IProductoService;
import com.curso.ecommerce.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private IProductoService productoService;
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IOrdenService ordenService;
	
  @GetMapping("")
	public String home(Model model) {	
	  
	  List<Producto> productos=productoService.findAll();
	  model.addAttribute("productos",productos);
		return "admin/home";
	}
  
  @GetMapping("/usuarios")
  public String usuarios(Model model) {
	  model.addAttribute("usuarios",usuarioService.findAll());
	  
	  return "admin/usuarios";
  }
  
  
  @GetMapping("/ordenes")
  public String ordenes(Model model) {
	  model.addAttribute("ordenes",ordenService.findAll());
	  
	  return "admin/ordenes";
  }
  
  @GetMapping("/detalle/{orden_id}")
	public String detalleCompra(@PathVariable Long orden_id,Model model) {			
	
		Orden orden=ordenService.findById(orden_id).get();
		model.addAttribute("detalles",orden.getDetalle());
		return "admin/detallecompra";
	}
}