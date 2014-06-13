package br.com.kamaleon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table( name = "t_user" )
@SequenceGenerator(name="gerador", sequenceName="s_user", allocationSize=1)
@XmlRootElement
public class User {
	
	@Id
	@GeneratedValue(generator = "gerador", strategy = GenerationType.SEQUENCE )
	private Integer id;
	
	private String username;
	
	private String password;
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + "]";
	}
	
	public void convertUser(Object[] objeto)
	{
		setId(((Integer) objeto[0]));
		setName((String) objeto[1]);
		setUsername((String) objeto[2]);
	}
	
}