package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import app.entity.Produto;
import app.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	 public String save(Produto produto) {
	        // Salvar o produto no banco de dados
	        produtoRepository.save(produto);
	        
	        
	        return produto.getNomeProduto() + " salvo com sucesso!";
	    }
	
	public List<Produto> listAll() {
		return produtoRepository.findAll();
	}
	
	public Produto findById(long idProduto) {
		return produtoRepository.findById(idProduto).get();
	}
	
	public String update(long idProduto, Produto produtoAtualizado) {
		
		// Buscar o produto atual do banco de dados
        Produto produtoAntigo = produtoRepository.findById(idProduto).get();
       
        // Atualizar o ID do produto no objeto produtoAtualizado
        produtoAtualizado.setIdProduto(idProduto);

        // Salvar o produto atualizado no repositório
        produtoRepository.save(produtoAtualizado);
        

        return "O produto " + produtoAtualizado.getNomeProduto() + " foi atualizado com sucesso!";
    }
	
	
	public String delete(long idProduto) {
	    
	    Optional<Produto> produtoOptional = produtoRepository.findById(idProduto);
	    
	   
	    if (produtoOptional.isPresent()) {
	        produtoRepository.deleteById(idProduto);
	        
	        return "Produto deletado com sucesso!";
	    } else {     
	        return "Produto não encontrado!";
	    }
	}

}
