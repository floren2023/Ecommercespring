package com.curso.ecommerce.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbproductos")
public class Producto {
	@Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private long id;
	
	   @Column(name = "name")
	   private String name;
	   @Column(name = "description")
	   private String description;
	   @Column(name = "image")
	   private String image;
	   @Column(name = "price")
	   private double price;
	   @Column(name = "cant")
	   private int cant;
	   
	  @ManyToOne
	   private Usuario usuario;
	   
	   public Producto() {
		// TODO Auto-generated constructor stub
	}
	
	public Producto(long id, String name, String description, String image, double price, int cant, Usuario usuario) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.price = price;
		this.cant = cant;
		this.usuario = usuario;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCant() {
		return cant;
	}
	public void setCant(int cant) {
		this.cant = cant;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@Override
	public String toString() {
		return "Producto [id=" + id + ", name=" + name + ", description=" + description + ", image=" + image
				+ ", price=" + price + ", cant=" + cant + "]";
	}
	   
	   

}
