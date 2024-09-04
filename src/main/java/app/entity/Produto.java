package app.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idProduto;
	@NotBlank(message = "Nome do produto não pode estar vazio")
	private String nomeProduto;
	@NotNull(message = "Valor não pode estar vazio")
	private double valorProduto;
	@NotBlank(message = "descrição não pode estar vazio")
	private String descricaoProduto;
	
	
	
	
	//mapeamento item carrinho adicionado
	@OneToMany (mappedBy = "produto")
	private List<ItemCarrinho> itemCarrinho;
	
}
