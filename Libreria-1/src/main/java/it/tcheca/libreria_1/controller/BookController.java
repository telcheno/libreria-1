package it.tcheca.libreria_1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.tcheca.libreria_1.model.Book;
import it.tcheca.libreria_1.repository.BookRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

 	//define la implementazione dellla classe concreta della interfacia tramite IoC 
	//e la classe viene ignetata tramite Dependency Injection
	@Autowired
	private BookRepository repositoryBook;
	
	@GetMapping
	//classe che specifica gli atributti con cui si valoriserà il oggeto 
	public String index(Model model,
			@RequestParam(name ="author", required = false) String author) {
	      
	List<Book> libri = new ArrayList<>();
	
	if(author == null || author.isBlank()) {
		
		libri = repositoryBook.findAll();//si asegna la entita del DB alla lista
	}else {
		
		libri = repositoryBook.findByAuthorOrderByIdDesc(author);
	}
			
	model.addAttribute("list", libri);// si pasa al model per la 
	 
	return "/books/index";
	}
	
	//metodo di visualisazione
	@GetMapping("/show/{id}")
	public String show(@PathVariable("id") Integer idBook, Model model) {
		
		model.addAttribute("book", repositoryBook.findById(idBook).get());
		
		return "/books/show";
	}
	
	//metodo di crezione
	@GetMapping("/create")
	public String create(Model model) {
		
		model.addAttribute("book", new Book());
		
		return "books/create";
	}
		
	@PostMapping("/create")
	public String store(
		@Valid 
		@ModelAttribute("book") Book formBook,
	   BindingResult bindingResult,
	   Model model){
		
	   if(bindingResult.hasErrors()) {
	      return "/books/create";
	   }

	   repositoryBook.save(formBook);

	   return "redirect:/books";
	}
	
	//metodo di aggiornamento
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer idEdit, Model model) {
		
		model.addAttribute("book", repositoryBook.findById(idEdit).get());
		return "/books/edit";
	}
	
	
	@PostMapping("/edit/{id}")
	public String update(
			@Valid @ModelAttribute("book") Book book,
			BindingResult bindingResult,
			Model model){
		
		if(bindingResult.hasErrors()) {
			return "/books/edit";
		}
		
		repositoryBook.save(book);
		
		return"redirect:/books";
	}

	//metodo di cancelazione
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer idDelete) {
		
		repositoryBook.deleteById(idDelete);
		
		return "/redirect:/books";
	}
}
