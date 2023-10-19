package com.curso.ecommerce.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.curso.ecommerce.model.DetalleOrden;
import com.curso.ecommerce.model.Orden;
import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

import com.curso.ecommerce.service.IDetalleOrdenService;
import com.curso.ecommerce.service.IOrdenService;
import com.curso.ecommerce.service.IProductoService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private final Logger log=LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired	
	private IOrdenService ordenService;
	
	@Autowired	
	private IDetalleOrdenService detalleOrdenService;
	
	//lista de detalles de orden carrito compras
	List<DetalleOrden> detalles=new ArrayList<DetalleOrden>();
	
	//datos de la orden
	Orden orden=new Orden();
	
	@GetMapping("")
	public String home(Model model,HttpSession session) {
		log.info("Usuario session :{}",session.getAttribute("userid"));
		model.addAttribute("productos",productoService.findAll());
		return "usuario/home";
	}
	
	@GetMapping("productohome/{id}")
	public String productoHome(@PathVariable long id,Model model) {
		
		Producto producto=new Producto();
		Optional<Producto> productoOptional=productoService.get(id);
		producto=productoOptional.get();
		model.addAttribute("producto",producto);
		return "usuario/productohome";
	}
	
	@PostMapping("/cart")
	public String addCart( @RequestParam long id, @RequestParam Integer cantidad,Model model){
		    DetalleOrden detalleOrden=new DetalleOrden();
		    Producto producto=new Producto();		    
		    double sumaTotal=0;
		    Optional<Producto> optionalProducto=productoService.get(id);
		    log.info("producto añadido: {}"+optionalProducto.get());
		    log.info("cantidad: {}"+cantidad);
		    producto=optionalProducto.get();
		    detalleOrden.setCant(cantidad);
		    detalleOrden.setName(producto.getName());
		    detalleOrden.setPrice(producto.getPrice());
		    detalleOrden.setProducto(producto);
		    detalleOrden.setTotal(producto.getPrice()*cantidad);
		    //validar que el producto no se añada dos veces
		    long idProducto=producto.getId();
		    boolean existe=detalles.stream().anyMatch(p->p.getProducto().getId()==idProducto);
		    if (!existe){
		    
		    detalles.add(detalleOrden);
		    }
		    sumaTotal=detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
		    orden.setTotal(sumaTotal);
		    
		    //pasar a la vista
		    model.addAttribute("cart",detalles);
		    model.addAttribute("orden",orden);
			return "usuario/carrito";
	
	}
	//eliminar producto carrito
	@GetMapping("/delete/cart/{id}")
	public String deleteProductoCart(@PathVariable long id,Model model) {
		List<DetalleOrden> ordenesNueva=new ArrayList<DetalleOrden>();
		
		for(DetalleOrden detalleOrden :detalles) {
			if(detalleOrden.getProducto().getId()!=id) {
				ordenesNueva.add(detalleOrden);
			}
			}
		detalles=ordenesNueva;
		double sumaTotal=0;
		 sumaTotal=detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
		 orden.setTotal(sumaTotal);
		 //pasar a la vista
		    model.addAttribute("cart",detalles);
		    model.addAttribute("orden",orden);
		return "usuario/carrito";
	}
	
	
	@GetMapping("/getCart")
	public String getCart(Model model) {
		 model.addAttribute("cart",detalles);
		 model.addAttribute("orden",orden);
		return "usuario/carrito";
	}
	
	@GetMapping("/order")
	public String order(Model model,HttpSession session) {
		//usuario quemado
		Usuario usuario=usuarioService.findById( (Long) session.getAttribute("userid")).get();
		
		 model.addAttribute("cart",detalles);
		 model.addAttribute("orden",orden);
		 model.addAttribute("usuario",usuario);
		return "usuario/resumenorden";
	}
	//guardar la orden
	@GetMapping("/saveOrder")
	public String saveOrder(HttpSession session) {
		Date fechaCreacion=new Date();
		orden.setInitDate(fechaCreacion);
		orden.setNumber(ordenService.generarNumeroOrden());
		//usuario quemado
		Usuario usuario=usuarioService.findById((Long) session.getAttribute("userid")).get();
		orden.setUsuario(usuario);
		ordenService.save(orden);
		//guardar detalles
		for (DetalleOrden dt:detalles) {
			dt.setOrden_id(orden.getId());
			detalleOrdenService.save(dt);
			
		}
		//limpiar valores
		orden=new Orden();
		detalles.clear();
		
		return "redirect:/";
	}
	
	@PostMapping("/search")
	public String searchProduct(@RequestParam String nombre,Model model) {
	
		List<Producto> productos= productoService.findAll().stream().filter(p->p.getName().contains(nombre)).collect(Collectors.toList());
	
		model.addAttribute("productos",productos);
		return "usuario/home";
	}
	
}
