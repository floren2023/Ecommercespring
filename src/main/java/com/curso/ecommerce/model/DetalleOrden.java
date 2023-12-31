package com.curso.ecommerce.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbdetallesorden")
public class DetalleOrden {
	@Id	
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id") 
	    private  Integer id ;
	
	@Column(name = "name")
	   private String name;
	@Column(name = "cant")
	   private double cant;
	@Column(name = "price")
	   private double price;
	@Column(name = "total")
	   private double total;
	@Column(name = "orden_id")
	   private Long orden_id;
	@ManyToOne
	private Producto producto;
	
	public DetalleOrden() {
		// TODO Auto-generated constructor stub
	}

	

	public DetalleOrden(Integer id, String name, double cant, double price, double total, long orden_id,
			Producto producto) {
		super();
		this.id = id;
		this.name = name;
		this.cant = cant;
		this.price = price;
		this.total = total;
		this.orden_id = orden_id;
		this.producto = producto;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCant() {
		return cant;
	}

	public void setCant(double cant) {
		this.cant = cant;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Long getOrden_id() {
		return orden_id;
	}



	public void setOrden_id(long orden_id) {
		this.orden_id = orden_id;
	}



	public Producto getProducto() {
		return producto;
	}



	public void setProducto(Producto producto) {
		this.producto = producto;
	}



	@Override
	public String toString() {
		return "DetalleOrden [id=" + id + ", name=" + name + ", cant=" + cant + ", price=" + price + ", total=" + total
				+ "]";
	}
	

}
