package br.com.springboot.curso_jdev_treinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired //Injeção de dependência
	private UsuarioRepository usuarioRepository;
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    }
    
    @RequestMapping(value = "/helloworld/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String helloWorld(@PathVariable String name) {
    	Usuario usuario = new Usuario();
    	usuario.setNome(name);
    	
    	usuarioRepository.save(usuario);
    	
        return "Hello world,  " + name + "!";
    }
    
    @GetMapping(value = "usuarios")
    @ResponseBody //Retorna os dados para o corpo da resposta
    public ResponseEntity<List<Usuario>> listaUsuarios(){
    	
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); //Retorna lista em JSON
    }
    
    @PostMapping(value = "usuario") // Mapeia a URL
    @ResponseBody //Descrição da resposta
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario){ // Recebe os dados para salvar
    	
    	Usuario usuarioSalvo = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.CREATED);
    	
    }
    
    @DeleteMapping(value = "usuario") // Mapeia a URL
    @ResponseBody //Descrição da resposta
    public ResponseEntity<String> deletarUsuario(@RequestParam Long id){ // Recebe os dados para salvar
    	
    	usuarioRepository.deleteById(id);
    	
    	return new ResponseEntity<String>("Usuario deletado com sucesso", HttpStatus.OK);
    	
    }

    
}
