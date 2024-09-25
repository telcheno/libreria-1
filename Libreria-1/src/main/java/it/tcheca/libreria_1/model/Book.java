package it.tcheca.libreria_1.model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity //define l'enetita della tabella
@Table(name = "book") //define ilnome della tabella
public class Book {
	
	@Id //define colllona della tabella come ID
	@GeneratedValue(strategy = GenerationType.IDENTITY) //define il valre autoincrement
	private Integer id; //la collona prende il nome del atributo

	//si specifica i campi che non devono essere NULL o BLANK
	@NonNull
	@NotBlank(message = "questo è un campo obligatorio")
	//per definire gli atrubuti nelle collone uso @Column
	@Column(name="title") 
	private String title;
	
	@NotNull
	@NotBlank(message = "questo è un campo obligatorio")
	// nullable define il vallore not null
	@Column(name="author", nullable = false)
	private String author;
	
	@NonNull
	@NotBlank(message = "questo è un campo obligatorio")
	//si puo dare specificazioni 
	@Size(min = 13, max = 13)
	//si puo personalizare il nome della collona del DB
	@Column(name="isbn_code", nullable = false, unique = true)
	private String isbn;

	//serbono i getter e setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


}
