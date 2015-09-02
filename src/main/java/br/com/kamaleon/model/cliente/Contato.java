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
@Table( name = "t_contato" )
@SequenceGenerator(name="gerador", sequenceName="s_contato", allocationSize=1)
public class Contato implements Serializable{
	
	@Id
	@GeneratedValue(generator = "gerador", strategy = GenerationType.SEQUENCE )
	private Integer codigo;
	
	private String descricao;	
	private int tipo;
	@ManyToOne
	@JoinColumn(name="IDPESSOA")
	private Pessoa pessoa;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
}
