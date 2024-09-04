package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.auth.Usuario;
import app.entity.Cliente;
import app.entity.Produto;
import app.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public String save (Cliente cliente) {
		this.clienteRepository.save(cliente);
				
		return cliente.getNomeCliente() + " Foi registrado";
	}
	
	public List <Cliente> findAll () {
	return this.clienteRepository.findAll();
		
	}
	
	
	
	public Cliente findById(long idCliente) {

		Cliente cliente = this.clienteRepository.findById(idCliente).get();
		return cliente;

	}
	
	public Cliente findByUsuarioId(long idUsuario) {
		
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(idUsuario);
		Optional<Cliente> cliente = this.clienteRepository.findByUsuario(usuario);
		if(cliente.isPresent())
		return cliente.get();
		else return null;

	}
	
	public String update (Cliente cliente, long idCliente) {
		cliente.setIdCliente(idCliente);
		
		Cliente clienteAntigo = clienteRepository.findById(idCliente).get();
		String nomeAntigo = clienteAntigo.getNomeCliente();
		String telefoneAntigo = clienteAntigo.getTelefoneCliente();
		String enderecoAntigo = clienteAntigo.getEnderecoCliente();
		String detalheNome = cliente.getNomeCliente();
		String detalheTelefone = cliente.getTelefoneCliente();
		String detalheEndereco = cliente.getEnderecoCliente();
        String formato = "Atualizado: nome antigo: %s novo: %s | telefone antigo: %S novo: %s | endereco antigo: %s novo: %s";
        String detalhes = String.format(formato, nomeAntigo, detalheNome, telefoneAntigo, detalheTelefone, enderecoAntigo, detalheEndereco);
        
        this.clienteRepository.save(cliente);
		return "O " + cliente.getNomeCliente() + " Foi atualizado";
		
	}
	
	public String delete (long idCliente) {
		
		Cliente cliente = clienteRepository.findById(idCliente).get();
		String detalheCliente = cliente.getNomeCliente();
        
        this.clienteRepository.deleteById(idCliente);
		
		return "Cliente deletado";
	}
	
}
