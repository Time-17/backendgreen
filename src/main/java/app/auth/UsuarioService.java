package app.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import app.config.JwtServiceGenerator;


@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
    private JwtServiceGenerator jwtService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private BCryptPasswordEncoder bCrypt;
    
        

    
    
    public String login(Autenticador autenticador) {
        // Autentica o usuário
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		autenticador.getUsername(),
                		autenticador.getPassword()
                )
        );
        
        // Busca o usuário no repositório
        Usuario user = usuarioRepository.findByEmailUsuario(autenticador.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Gera o token JWT
        String jwtToken = jwtService.generateToken(user);
        
        return jwtToken;
    }
    
	
	public String save (Usuario usuario) {
		
		String senhaCriptografada = this.bCrypt.encode(usuario.getSenhaUsuario());
		usuario.setSenhaUsuario(senhaCriptografada);
		
		this.usuarioRepository.save(usuario);

        
		return usuario.getEmailUsuario() + " Foi registrado";
	}
	
	public List <Usuario> findAll () {
		return this.usuarioRepository.findAll();
	}
	
	
	
	public Usuario findById(long idUsuario) {

		Usuario usuario= this.usuarioRepository.findById(idUsuario).get();
		return usuario;

	}
	
	public String update (Usuario usuario, long idUsuario) {
		usuario.setIdUsuario(idUsuario);
		
		this.usuarioRepository.save(usuario);
		
        
		return "O " + usuario.getEmailUsuario() + " Foi atualizado";
		
	}
	
	public String delete (long idUsuario) {
	
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
		
		if(usuarioOptional.isPresent()) {
		
			this.usuarioRepository.deleteById(idUsuario);
			return "Usuario deletado";
		}else
			return "usuario nao encontrado";
		
		
	}
	public Optional<Usuario> findByEmail(String emailUsuario){
		return this.usuarioRepository.findByEmailUsuario(emailUsuario);
	}
	public List<Usuario> findByClienteNome(String clienteNome){
		return null;
		//return this.usuarioRepository.findByClienteNomeCliente(clienteNome);
	}
}
