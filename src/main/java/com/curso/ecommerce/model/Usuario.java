package com.curso.ecommerce.model;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbusuarios")
public class Usuario {
	 @Override
	public String toString() {
		return "Usuario [id=" + id + ", name=" + name + ", userName=" + userName + ", emailId=" + emailId + ", adress="
				+ adress + ", phone=" + phone + ", type=" + type + ", password=" + password + ", productos=" + productos
				+ ", ordenes=" + ordenes + "]";
	}



	@Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private long id;

	   @Column(name = "name")
	   private String name;

	   @Column(name = "user_name")
	   private String userName;

	   @Column(name = "email_id")
	   private String emailId;

	   @Column(name = "adress")
	   private String adress;
	   
	   @Column(name = "phone")
	   private String phone;
	   
	   //type attribute referencing the table roles
	   
	   @Column(name = "type")
	   private String type;
	   
	   @Column(name = "password")
	   private String password;
	   
	   @OneToMany(mappedBy="usuario")
	   private List<Producto> productos;
	   
	   @OneToMany(mappedBy="usuario")
	   private List<Orden> ordenes;
	   
	   //constructors
	   public Usuario() {
		// TODO Auto-generated constructor stub
	}
	   
	 
	public List<Orden> getOrdenes() {
		return ordenes;
	}


	public void setOrdenes(List<Orden> ordenes) {
		this.ordenes = ordenes;
	}

  public Usuario(long id, String name, String userName, String emailId, String adress, String phone, String type,
			String password, List<Producto> productos, List<Orden> ordenes) {
		super();
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.emailId = emailId;
		this.adress = adress;
		this.phone = phone;
		this.type = type;
		this.password = password;
		this.productos = productos;
		this.ordenes = ordenes;
	}







	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


  
	public List<Producto> getProductos() {
		return productos;
	}



	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

}
