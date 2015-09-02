package br.com.kamaleon.model.cliente;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table( name = "t_cliente" )
@SequenceGenerator(name="gerador", sequenceName="s_cliente", allocationSize=1)
public class Cliente implements Serializable {
	
	@Id
	@GeneratedValue(generator = "gerador", strategy = GenerationType.SEQUENCE )
	private Integer codigo;
	@ManyToOne
	@JoinColumn(name="IDPESSOA")	
	private Pessoa pessoa;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
