package br.com.springboot.curso_jdev_treinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
    @GetMapping(value = "usuario") // Mapeia a URL
    @ResponseBody //Descrição da resposta
    public ResponseEntity<Usuario> buscarUsuario(@RequestParam (name = "id")Long id){ // Recebe os dados para salvar
    	
    	Usuario usuario = usuarioRepository.findById(id).get();
    	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    	
    }
    
    @PutMapping(value = "usuario") // Mapeia a URL
    @ResponseBody //Descrição da resposta
    public ResponseEntity<?> atualizarUsuario(@RequestBody Usuario usuario){ // Recebe os dados para salvar
    	
    	if(usuario.getId() == null) {
    		return new ResponseEntity<String>("Id não foi informado para atualização", HttpStatus.BAD_REQUEST);
    	}
    	
    	Usuario usuarioSalvo = usuarioRepository.saveAndFlush(usuario); //
    	
    	return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
    	
    }
    
    @GetMapping(value = "usuario/nome") // Mapeia a URL
    @ResponseBody //Descrição da resposta
    public ResponseEntity<List<Usuario>> buscarUsuarioPorNome(@RequestParam (name = "nome")String nome){ // Recebe os dados para salvar
    	
    	List<Usuario> usuarios = usuarioRepository.buscarPorNome(nome.trim().toLowerCase());
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    	
    }

    
}
