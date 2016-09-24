package library;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import jersey.repackaged.com.google.common.collect.Lists;

@Path("books")
public class Resursa {

	BooksRepository repository = new BooksRepository();

	// @GET
	// @Produces(MediaType.APPLICATION_JSON)
	// public Response getBooks() {
	// List<Book> books = new ArrayList<Book>();
	// try {
	// books = repository.getBooks();
	// } catch (Exception e) {
	// return Response.status(500).build();
	// }
	//
	// GenericEntity<List<Book>> entity = new
	// GenericEntity<List<Book>>(Lists.newArrayList(books)) {
	// };
	// return Response.ok(entity).build();
	// }

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBook(Book cuvant) {
		try {
			Book book = repository.addBook(cuvant);
			return Response.status(201).entity(book).build();
		} catch (Exception e) {
			return Response.status(500).build();
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBook(@PathParam("id") int id) {
		Book book = new Book();
		try {
			book = repository.getBook(id);
			return Response.status(201).entity(book).build();
		} catch (Exception e) {
			return Response.status(500).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id) {
		try {
			repository.deleteBook(id);
			return Response.status(201).build();
		} catch (Exception e) {
			return Response.status(500).build();
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response update(@PathParam("id") int id, Book book) {
		try {
			repository.updateBook(id, book);
			return Response.status(201).build();
		} catch (Exception e) {
			return Response.status(500).build();
		}
	}

	// *********************************

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooks(@QueryParam("author") String author, @QueryParam("genre") String genre) {

		List<Book> books = new ArrayList<Book>();
		if (author == null && genre != null) {
			try {
				
				System.out.println("intra 1");
				books = repository.getBooksAuthorGenre(author, genre);
			} catch (Exception e) {
				return Response.status(500).build();
			}

			GenericEntity<List<Book>> entity = new GenericEntity<List<Book>>(Lists.newArrayList(books)) {
			};
			return Response.ok(entity).build();

		}

		if (author != null) {
			try {
				System.out.println("intra 1");
				books = repository.getBooksAuthorGenre(author, genre);
			} catch (Exception e) {
				return Response.status(500).build();
			}

			GenericEntity<List<Book>> entity = new GenericEntity<List<Book>>(Lists.newArrayList(books)) {
			};
			return Response.ok(entity).build();

		} else {
			try {
				books = repository.getBooks();
			} catch (Exception e) {
				return Response.status(500).build();
			}

			GenericEntity<List<Book>> entity = new GenericEntity<List<Book>>(Lists.newArrayList(books)) {
			};
			return Response.ok(entity).build();
		}

	}

}