
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
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

		Boolean running = true;
		Scanner input = new Scanner(System.in);
		String answer;
		Customer loggedCustomer = null;

		while(running) {
			if(loggedCustomer == null) {
				System.out.println("Welcome to the Library");
				System.out.println("1 - Login");
				System.out.println("2 - Register");
				System.out.println("3 - Exit");
				answer = input.nextLine();
				switch (answer) {
					case "1" -> {
						Boolean found = false;
						System.out.println("Enter your email:");
						String email = input.nextLine();
						System.out.println("Enter your password:");
						String password = input.nextLine();
						for (int i = 0; i < customers.size(); i++) {
							if (customers.get(i).getEmail().equals(email) && customers.get(i).getPassword().equals(password)) {
								System.out.println("Welcome " + customers.get(i).getName() + "!");
								loggedCustomer = customers.get(i);
								found = true;
							}
						}
						if(!found) {
							System.out.println("Invalid email or password");
						}
					}
					case "2" -> {
						System.out.println("Enter your name:");
                        String name = input.nextLine();
                        System.out.println("Enter your email:");
                        String email = input.nextLine();
                        System.out.println("Enter your password:");
                        String password = input.nextLine();
                        System.out.println("Enter your birth date (yyyy-mm-dd):");
                        String birthDate = input.nextLine();
						Date date = null;
                        boolean emailExists = false;
                        for (Customer customer : customers) {
                            if (customer.getEmail().equals(email)) {
                                System.out.println("Email already registered");
                                emailExists = true;
                                break;
							}
						}

						if(!emailExists) {
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							try {
								date = dateFormat.parse(birthDate);
							} catch (ParseException e) {
								System.err.println("Error parsing date: " + e.getMessage());
							}
							Customer newCustomer = new Customer(customers.size() + 1, name, email, password, date);
							customers.add(newCustomer);

							Path customersPath = Paths.get("./db/customers.csv");
							try {
								Files.write(customersPath, Arrays.asList(newCustomer.getId() + ";" + newCustomer.getName() + ";" + newCustomer.getEmail() + ";" + newCustomer.getPassword() + ";" + birthDate), StandardOpenOption.APPEND);
							} catch (IOException e) {
								System.err.println("An error occurred: " + e.getMessage());
							}

							loggedCustomer = newCustomer;
						} else continue;
					}
					case "3" -> {
						running = false;
						continue;
					}
				}
			} else {
				System.out.println("1 - Add Book");
				System.out.println("2 - Search Books");
				System.out.println("3 - My Borrowed Books");
				System.out.println("4 - Borrow Book");
				System.out.println("5 - Return Book");
				System.out.println("6 - Logout");
				System.out.println("7 - Exit");
				answer = input.nextLine();
				switch(answer) {
					case "1" -> {
						System.out.println("Enter the book title:");
						String title = input.nextLine();
						System.out.println("Enter the author ID:");
						int authorId = Integer.parseInt(input.nextLine());
						authors.forEach(author -> {
							if (author.getId() == authorId) {
								Book newBook = new Book(books.size() + 1, title, author, true);
								books.add(newBook);

								Path booksPath = Paths.get("./db/books.csv");
								try {
									Files.write(booksPath, Arrays.asList(newBook.getId() + ";" + newBook.getTitle() + ";" + authorId + ";" + newBook.getIsAvailable()), StandardOpenOption.APPEND);
								} catch (IOException e) {
									System.err.println("An error occurred: " + e.getMessage());
								}

								System.out.println("Book added successfully!");
							}
						});
					}
					case "2" -> {
						System.out.println("1 - Search by title");
						System.out.println("2 - Search by author");
						System.out.println("3 - Return");
						answer = input.nextLine();
						switch(answer) {
							case "1" -> {
								Boolean found = false;
								System.out.println("Enter the book title:");
								String title = input.nextLine();
								for (Book book : books) {
									if (book.getTitle().contains(title) && book.getIsAvailable()) {
										if(!found) System.out.println("Books with title " + title + ":");
										System.out.println(book);
										found = true;
									}
								}
								if(!found) {
									System.out.println("No books found");
								}
							}
							case "2" -> {
								Boolean found = false;
								System.out.println("Enter the author name:");
								String authorName = input.nextLine();
								for (Book book : books) {
									if (book.getAuthor().getName().contains(authorName) && book.getIsAvailable()) {
										if(!found) System.out.println("Books by " + authorName + ":");
										System.out.println(book);
										found = true;
									}
								}
								if(!found) {
									System.out.println("No books found");
								}
							}
							case "3" -> {
								continue;
							}
						}
					}
					case "3" -> {
						for (Borrow borrow : borrows) {
							if (borrow.getCustomerId() == loggedCustomer.getId()) {
								System.out.println(borrow);
							}
						}
					}
					case "4" -> {
						System.out.println("Enter the book ID:");
						int bookId = Integer.parseInt(input.nextLine());
						Boolean found = false;
						for (Book book : books) {
							if (book.getId() == bookId && book.getIsAvailable()) {
								Borrow newBorrow = new Borrow(bookId, loggedCustomer.getId(), new Date(), null);
								borrows.add(newBorrow);
								book.setIsAvailable(false);
								System.out.println("Book borrowed successfully!");
								Path borrowsPath = Paths.get("./db/borrows.csv");
								try {
									Files.write(borrowsPath, Arrays.asList(newBorrow.getBookId() + ";" + newBorrow.getCustomerId() + ";" + newBorrow.getBorrowedAt() + ";" + newBorrow.getReturnedAt()), StandardOpenOption.APPEND);
								} catch (IOException e) {
									System.err.println("An error occurred: " + e.getMessage());
								}
								found = true;
							}
						}
						if(!found) {
							System.out.println("Book not found or not available");
						}
					}
					case "5" -> {
						System.out.println("Enter the book ID:");
						int bookId = Integer.parseInt(input.nextLine());
						for (Borrow borrow : borrows) {
							if (borrow.getCustomerId() == loggedCustomer.getId() && borrow.getBookId() == bookId) {
								borrow.setReturnedAt(new Date());
								for (Book book : books) {
									if (book.getId() == bookId) {
										book.setIsAvailable(true);
									}
								}
								System.out.println("Book returned successfully!");
								Path borrowsPath = Paths.get("./db/borrows.csv");
								try {
									Files.readAllLines(borrowsPath).forEach(line -> {
										ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(";")));
										if (Integer.parseInt(data.get(0)) == borrow.getBookId() && Integer.parseInt(data.get(1)) == borrow.getCustomerId()) {
											try {
												Files.write(borrowsPath, Arrays.asList(borrow.getBookId() + ";" + borrow.getCustomerId() + ";" + borrow.getBorrowedAt() + ";" + borrow.getReturnedAt()));
											} catch (IOException e) {
												System.err.println("An error occurred: " + e.getMessage());
											}
										} else {
											try {
												Files.write(borrowsPath, Arrays.asList(line));
											} catch (IOException e) {
												System.err.println("An error occurred: " + e.getMessage());
											}
										}
									});
								} catch (IOException e) {
									System.err.println("An error occurred: " + e.getMessage());
								}
								break;
							}
						}
					}
					case "6" -> {
						loggedCustomer = null;
						continue;
					}
					case "7" -> {
						running = false;
						continue;
					}
				}
			}
		}

		input.close();
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
				
				try {
					birthDate = dateFormat.parse(data.get(4));
				} catch (ParseException e) {
					System.err.println("Error parsing date: " + e.getMessage());
				}
				
				Customer customer = new Customer(
					Integer.parseInt(data.get(0)), data.get(1), data.get(2),
					data.get(3), birthDate);
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
				Author bookAuthor = null;
				for (Author author : getAuthors()) {
					if (author.getId() == Integer.parseInt(data.get(2))) {
						bookAuthor = author;
					}
				}

				Book book = new Book(
					Integer.parseInt(data.get(0)), data.get(1),
					bookAuthor, Boolean.valueOf(data.get(3)));
					
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
