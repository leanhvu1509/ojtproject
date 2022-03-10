package com.lavu.ojtsource.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product extends Auditable<String>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="product_name",length = 100,
			columnDefinition = "nvarchar(100) not null")
	private String name;
	
	@Column(name="price",nullable = false)
	private int price;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name="discount",nullable = false)
	private double discount;
	
	@Column(name ="image")
	private String image;
	
	@Lob
	@Column(name="description",columnDefinition = "text not null")
	private String description;
	
	@Column(name="publisher")
	private String publisher;
	
	@Column(name = "publisher_year")
	private short publisherYear;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "status")
	private short status;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public short getPublisherYear() {
		return publisherYear;
	}

	public void setPublisherYear(short publisherYear) {
		this.publisherYear = publisherYear;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	
}
