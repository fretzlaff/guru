package guru.springframework.spring5webapp.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private PublisherRepository publisherRepository;

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent arg0) {
		initData();
	}

	private void initData() {
		final Author eric = new Author("Eric", "Evans");
		final Publisher harper = new Publisher("Harper Collins", "St. 123");
		final Book ddd = new Book("Domain Driven Design", "1234", harper);
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);

		authorRepository.save(eric);
		publisherRepository.save(harper);
		bookRepository.save(ddd);

		final Author rod = new Author("Rod", "Jonhson");
		final Publisher worx = new Publisher("Worx", "St. 456");
		final Book noEJB = new Book("J2EE Development without EJB", "23444", worx);
		rod.getBooks().add(noEJB);

		authorRepository.save(rod);
		publisherRepository.save(worx);
		bookRepository.save(noEJB);

	}

}
