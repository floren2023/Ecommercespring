package com.curso.ecommerce.controller;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.repository.IProductoRepository;
import com.curso.ecommerce.service.IProductoService;
import com.curso.ecommerce.service.UploadFileService;




@Controller

@RequestMapping("/productos")
public class ProductoController {
	@Autowired
	private IProductoService productoService; 
	@Autowired 
	private UploadFileService upload;

	private final Logger LOGGER=LoggerFactory.getLogger(ProductoController.class);
	
	@GetMapping(" ")
	public String show(Model modelo) {	
		
		modelo.addAttribute("productos",productoService.findAll());
		return "producto/show";
	}
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("producto",new Producto());
		return "producto/create";
	}
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable long id,Model model) {
		Producto producto=new Producto();
		Optional<Producto> optionalProducto=productoService.get(id);
		producto=optionalProducto.get();
		LOGGER.info("El valor es :{}",producto);
		model.addAttribute("producto",producto);
		return "producto/edit";
	}
	
	@PostMapping("/save")
	public String save(Producto producto,@RequestParam("img") MultipartFile file) throws IOException {	
		Usuario usuario=new Usuario(1,"","","","","","","",null,null);
		producto.setUsuario(usuario);
		
		//imagen
		if(producto.getId()==0) {//cuando se crea un producto la primera vez
			String nombreImagen=upload.saveImage(file);
			producto.setImage(nombreImagen);
		}
		
		productoService.save(producto);
		LOGGER.info("Este es el producto:{}",producto);
		return "redirect:/productos";
	}
	
	@PostMapping("/update")
	public String update(Producto producto,@RequestParam("img") MultipartFile file) throws IOException {
		Producto p=new Producto();
		p=productoService.get(producto.getId()).get();
		if(file.isEmpty()) {//si no hay archivo de imagen se actualiza con la misma imagen de antes
			
			producto.setImage(p.getImage());
		}else {
			
			if(!p.getImage().equals("default.jpg")) {
				upload.deleteImage(p.getImage());
				
			}
			String nombreimagen=upload.saveImage(file);
			producto.setImage(nombreimagen);
		}
		producto.setUsuario(p.getUsuario());
		productoService.update(producto);
		
		return "redirect:/productos";
		
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable long id) {
		Producto p=new Producto();
		p=productoService.get(id).get();
		//se elimina cuando no es la imagen por defecto
		if(!p.getImage().equals("default.jpg")) {
			upload.deleteImage(p.getImage());
			
		}
		productoService.delete(id);
		return "redirect:/productos";
	}
	
}
