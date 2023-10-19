package com.curso.ecommerce.service;

import java.util.Optional;

import com.curso.ecommerce.model.Usuario;

public interface IUsuarioService {
	Optional<Usuario> findById(Long id);
	Usuario save(Usuario usuario);
	Optional<Usuario> findByEmailId(String emailid);

}
