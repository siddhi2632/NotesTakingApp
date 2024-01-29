package com.example.demo.entity;

import java.time.LocalDate;
import org.hibernate.annotations.ManyToAny;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Notes 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String description;
	private LocalDate date;
	
	@ManyToOne    // multiple notes 1 user ke hote he
	private User user;
	
	public Notes() {
		super();
	}
	public Notes(int id, String title, String description, LocalDate date, User user) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
		this.user = user;
	}
	@Override
	public String toString() {
		return "Notes [id=" + id + ", title=" + title + ", description=" + description + ", date=" + date + ", user="
				+ user + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
