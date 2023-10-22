package com.curso.ecommerce.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbordenes")
public class Orden {
	@Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private long id;
	 @Column(name = "number")
	   private String number;
	 @Column(name = "initDate")
	   private Date initDate;
	 @Column(name = "receiveDate")
	   private Date receiveDate;
	 
	 @Column(name = "total")
	   private double total;
	 
	 @ManyToOne
	 private Usuario usuario;
	 
	 @OneToMany(cascade=CascadeType.ALL)
	 @JoinColumn(name="orden_id",referencedColumnName="id")
	 List<DetalleOrden> detalle=new ArrayList<DetalleOrden>();
	 
	 
public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

public Orden() {
	// TODO Auto-generated constructor stub
}




public List<DetalleOrden> getDetalle() {
	return detalle;
}

public void setDetalle(List<DetalleOrden> detalle) {
	this.detalle = detalle;
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getNumber() {
	return number;
}

public void setNumber(String number) {
	this.number = number;
}

public Date getInitDate() {
	return initDate;
}

public void setInitDate(Date initDate) {
	this.initDate = initDate;
}

public Date getReceiveDate() {
	return receiveDate;
}

public void setReceiveDate(Date receiveDate) {
	this.receiveDate = receiveDate;
}

public double getTotal() {
	return total;
}

public void setTotal(double total) {
	this.total = total;
}

@Override
public String toString() {
	return "Orden [id=" + id + ", number=" + number + ", initDate=" + initDate + ", receiveDate=" + receiveDate
			+ ", total=" + total + "]";
}

}
