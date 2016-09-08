package library;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("books")
public class MyResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
    public List<String> getBooks() {
    	List<String> books = new ArrayList<>();
    	
    	books.add("Harap Alb");
    	books.add("Harry Potter and the Philosopher's Stone");
    	books.add("Cartea 1");
    	
    	return books;
    }
}
