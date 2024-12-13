
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import models.Author;
import models.Book;
import models.Borrow;
import models.Customer;

public class Library {
    public static void main(String[] args) {
		ArrayList<Customer> customers = new ArrayList<>(getCustomers());
		ArrayList<Author> authors = new ArrayList<>(getAuthors());
		ArrayList<Book> books = new ArrayList<>(getBooks());
		ArrayList<Borrow> borrows = new ArrayList<>(getBorrows());
		
		System.out.println("Customers:");
		customers.forEach(customer -> {
			System.out.println(customer.toString());
		});

		System.out.println("\nAuthors:");
		authors.forEach(author -> {
			System.out.println(author.toString());
		});

		System.out.println("\nBooks:");
		books.forEach(book -> {
			System.out.println(book.toString());
		});

		System.out.println("\nBorrows:");
		borrows.forEach(borrow -> {
			System.out.println(borrow.toString());
		});
    }

	public static ArrayList<Customer> getCustomers() {
		ArrayList<Customer> customers = new ArrayList<>();

		try {
			Path customersPath = Paths.get("./db/customers.csv");
			if (!Files.exists(customersPath)) {
				Files.createFile(customersPath);
			}
			
			
			Files.readAllLines(customersPath).forEach(line -> {
				ArrayList<String> data =
				new ArrayList<>(Arrays.asList(line.split(";")));
				
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date birthDate = null;
				Date createdAt = null;
				Date updatedAt = null;
				
				try {
					birthDate = dateFormat.parse(data.get(4));
					createdAt = dateFormat.parse(data.get(5));
					if(data.size() == 7) updatedAt = dateFormat.parse(data.get(6));
				} catch (ParseException e) {
					System.err.println("Error parsing date: " + e.getMessage());
				}
				
				Customer customer = new Customer(
					Integer.parseInt(data.get(0)), data.get(1), data.get(2),
					Integer.parseInt(data.get(3)), birthDate, createdAt, updatedAt);
				customers.add(customer);
			});

		} catch (IOException e) {
			System.err.println("An error occurred: " + e.getMessage());
		}
		
		return customers;
	}

	public static ArrayList<Author> getAuthors() {
		ArrayList<Author> authors = new ArrayList<>();

		try {
			Path authorsPath = Paths.get("./db/authors.csv");
			if (!Files.exists(authorsPath)) {
				Files.createFile(authorsPath);
			}

			Files.readAllLines(authorsPath).forEach(line -> {
				ArrayList<String> data =
				new ArrayList<>(Arrays.asList(line.split(";")));

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {
					date = dateFormat.parse(data.get(2));
				} catch (ParseException e) {
					System.err.println("Error parsing date: " + e.getMessage());
				}
				Author author = new Author(
					Integer.parseInt(data.get(0)), data.get(1), date);
				authors.add(author);
			});

		} catch (IOException e) {
			System.err.println("An error occurred: " + e.getMessage());
		}
		
		return authors;
	}

	public static ArrayList<Book> getBooks() {
		ArrayList<Book> books = new ArrayList<>();

		try {
			Path booksPath = Paths.get("./db/books.csv");
			if(!Files.exists(booksPath)) {
				Files.createFile(booksPath);
			}

			Files.readAllLines(booksPath).forEach(line -> {
				ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(";")));

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date createdAt = null;
				Date updatedAt = null;

				try {
					createdAt = dateFormat.parse(data.get(4));
					if(data.size() == 4) updatedAt = dateFormat.parse(data.get(5));
				} catch (ParseException e) {
					System.err.println("Error parsing date: " + e.getMessage());
				}

				Book book = new Book(
					Integer.parseInt(data.get(0)), data.get(1),
					Integer.parseInt(data.get(2)), Boolean.valueOf(data.get(3)),
					createdAt, updatedAt);
					
				books.add(book);
			});
		} catch (IOException e) {
			System.err.println("An error occurred: " + e.getMessage());
		}

		return books;
	}

	public static ArrayList<Borrow> getBorrows() {
		ArrayList<Borrow> borrows = new ArrayList<>();

		try {
			Path borrowsPath = Paths.get("./db/borrows.csv");
			if(!Files.exists(borrowsPath)) {
				Files.createFile(borrowsPath);
			}

			Files.readAllLines(borrowsPath).forEach(line -> {
				ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(";")));

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date borrowedAt = null;
				Date returnedAt = null;

				try {
					borrowedAt = dateFormat.parse(data.get(2));
					if(data.size() == 3) returnedAt = dateFormat.parse(data.get(3));
				} catch (ParseException e) {
					System.err.println("Error parsing date: " + e.getMessage());
				}

				Borrow borrow = new Borrow(
					Integer.parseInt(data.get(0)), Integer.parseInt(data.get(1)),
					borrowedAt, returnedAt);
					
				borrows.add(borrow);
			});
		} catch (IOException e) {
			System.err.println("An error occurred: " + e.getMessage());
		}

		return borrows;
	}
}
