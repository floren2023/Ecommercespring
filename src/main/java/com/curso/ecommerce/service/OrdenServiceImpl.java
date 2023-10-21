package com.curso.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.ecommerce.model.Orden;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.repository.IOrdenRepository;

@Service

public class OrdenServiceImpl implements IOrdenService{
	@Autowired
	private IOrdenRepository ordenRepository;

	@Override
	public Orden save(Orden orden) {
		
		return ordenRepository.save(orden);
	}

	@Override
	public List<Orden> findByUsuario(Usuario usuario) {
		
		return ordenRepository.findByUsuario(usuario);
	}

	@Override
	public List<Orden> findAll() {
		
		return ordenRepository.findAll();
	}

	//establecer numero orden de forma '0000010' es un string
	@Override
	public String generarNumeroOrden() {
		int numero=0;
		String nrconcatenado="";
		List<Orden> ordenes=findAll();
		List<Integer> numeros=new ArrayList<Integer>();
		ordenes.stream().forEach(o->numeros.add(Integer.parseInt(o.getNumber())));
		
		if(ordenes.isEmpty()) {
			numero=1;
		}
		else {
			numero=numeros.stream().max(Integer::compare).get();
			numero++;
		}
		if (numero<10){
			nrconcatenado="000000000"+String.valueOf(numero);
		}
		else if(numero<100) {
			nrconcatenado="00000000"+String.valueOf(numero);
			
		}
		else if(numero<1000) {
			nrconcatenado="0000000"+String.valueOf(numero);
			
		}
		else if(numero<10000) {
			nrconcatenado="000000"+String.valueOf(numero);
			
		}
		return nrconcatenado;
	}

	@Override
	public Optional<Orden> findById(Long id) {
		
		return ordenRepository.findById(id);
	}
	
}