package br.com.projetofinal.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.projetofinal.dao.*;

import br.com.projetofinal.beans.Usuario;
@CrossOrigin("*")
@RestController
public class UsuarioController {

	@Autowired // objeto gerenciavel, não precisa de new
	private UsuarioDAO dao;
	/*
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> getAll() { // ResponseEntity retorna no padrão http e não um objeto

		List<Usuario> list = (List<Usuario>) dao.findAll(); // Array list que faz um select de tudo

		if (list.size() == 0) {
			return ResponseEntity.status(404).build();
		}

		return ResponseEntity.ok(list);
	
	}*/
	
	@GetMapping("/usuario/{cod}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable int cod) { // ResponseEntity retorna no padrão http e não um objeto

		Usuario resposta =  dao.findById(cod).orElse(null); // Array list que faz um select de tudo

		if (resposta == null) {
			return ResponseEntity.status(404).build();
		}

		return ResponseEntity.ok(resposta);
	
	}
	
	//método para adicionar usuário
	@PostMapping("/novousuario")
	public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario objeto){
		try {
			dao.save(objeto);
			return ResponseEntity.ok(objeto);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(403).build();
		}
	}
	
	//método para criar a rota para login
	@PostMapping("/login")
	public ResponseEntity<Usuario> logar(@RequestBody Usuario objeto){
		Usuario resposta = dao.findByEmailAndSenha(objeto.getEmail(), objeto.getSenha());
		
		if(resposta == null) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(objeto);
	}
	
	
	
	
	

}