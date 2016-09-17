package library;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jersey.repackaged.com.google.common.collect.Lists;

@Path("resursa")
public class Resursa {

	BooksRepository repository = new BooksRepository();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response nume() {
		List<Book> books = new ArrayList<Book>();
		try {
			books = repository.getBooks();
		} catch (Exception e) {
			return Response.status(500).build();
		}

		GenericEntity<List<Book>> entity = new GenericEntity<List<Book>>(Lists.newArrayList(books)) {
		};
		return Response.ok(entity).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Book cuvant) {
		try {
			Book book = repository.addBook(cuvant);
			return Response.status(201).entity(book).build();
		} catch (Exception e) {
			return Response.status(500).build();
		}
	}
}