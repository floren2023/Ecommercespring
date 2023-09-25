package com.curso.ecommerce.model;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
	 
	 @OneToOne(mappedBy="orden")
	 private DetalleOrden detalle;
	 
	 
public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

public Orden() {
	// TODO Auto-generated constructor stub
}





public DetalleOrden getDetalle() {
	return detalle;
}

public void setDetalle(DetalleOrden detalle) {
	this.detalle = detalle;
}

public Orden(long id, String number, Date initDate, Date receiveDate, double total, Usuario usuario,
		DetalleOrden detalle) {
	super();
	this.id = id;
	this.number = number;
	this.initDate = initDate;
	this.receiveDate = receiveDate;
	this.total = total;
	this.usuario = usuario;
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
