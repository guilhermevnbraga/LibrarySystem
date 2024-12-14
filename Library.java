
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
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
				System.out.println("Welcome to the Library\n");
				System.out.println("1 - Login");
				System.out.println("2 - Register");
				System.out.println("3 - Exit");
				answer = input.nextLine();
				switch (answer) {
					case "1" -> {
						Boolean found = false;
						System.out.println("\nEnter your email:");
						String email = input.nextLine();
						System.out.println("Enter your password:");
						String password = input.nextLine();
						for (int i = 0; i < customers.size(); i++) {
							if (customers.get(i).getEmail().equals(email) && customers.get(i).getPassword().equals(password)) {
								System.out.println("\nWelcome " + customers.get(i).getName() + "!");
								loggedCustomer = customers.get(i);
								found = true;
							}
						}
						if(!found) {
							System.out.println("Invalid email or password\n");
						}
					}
					case "2" -> {
						System.out.println("\nEnter your name:");
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
							Customer newCustomer = new Customer(customers.size() + 1, name, email, password, date, false);
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
				if(loggedCustomer.getIsAdmin()) {
					System.out.println("\nAdmin Menu:");
					System.out.println("A - List all customers");
					System.out.println("B - Customer borrow history");
					System.out.println("C - Book borrow history");
				}

				System.out.println("\nLibrary Menu:");
				System.out.println("1 - Add Book");
				System.out.println("2 - Search Books");
				System.out.println("3 - My Borrowed Books");
				System.out.println("4 - Borrow Book");
				System.out.println("5 - Return Book");
				System.out.println("6 - Logout");
				System.out.println("7 - Exit");
				answer = input.nextLine();
				switch(answer) {
					case "A" -> {
						if(loggedCustomer.getIsAdmin()) {
							System.out.println("\nCustomers:");
							customers.forEach(customer -> System.out.println(customer));
						}
					}
					case "B" -> {
						if(loggedCustomer.getIsAdmin()) {
							System.out.println("\nEnter the customer ID:");
							int customerId = Integer.parseInt(input.nextLine());
							Boolean found = false;
							for (Customer customer : customers) {
								if (customer.getId() == customerId) {
									for (Borrow borrow : borrows) {
										if (borrow.getCustomerId() == customerId) {
											if(!found) System.out.println("\nBorrow history for " + customer.getName() + ":");
											System.out.println(borrow);
											found = true;
										}
									}
								}
							}
							if(!found) {
								System.out.println("\nNo borrow history found\n");
							}
						}
					}
					case "C" -> {
						if(loggedCustomer.getIsAdmin()) {
							System.out.println("\nEnter the book ID:");
							int bookId = Integer.parseInt(input.nextLine());
							Boolean found = false;
							for (Book book : books) {
								if (book.getId() == bookId) {
									for (Borrow borrow : borrows) {
										if (borrow.getBookId() == bookId) {
											if(!found) System.out.println("\nBorrow history for " + book.getTitle() + ":");
											System.out.println(borrow);
											found = true;
										}
									}
								}
							}
							if(!found) {
								System.out.println("\nNo borrow history found\n");
							}
						}
					}
					case "1" -> {
						System.out.println("\nEnter the book title:");
						String title = input.nextLine();
						System.out.println("Enter the book genre:");
						String genre = input.nextLine();
						System.out.println("Enter the author ID:");
						int authorId = Integer.parseInt(input.nextLine());
						authors.forEach(author -> {
							if (author.getId() == authorId) {
								Book newBook = new Book(books.size() + 1, title, genre, author, true, new Date(), null);
								books.add(newBook);

								Date now = new Date();
        						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        						String date = dateFormat.format(now);

								Path booksPath = Paths.get("./db/books.csv");
								try {
									Files.write(booksPath, Arrays.asList(newBook.getId() + ";" + newBook.getTitle() + ";" + genre + ";" + authorId + ";" + newBook.getIsAvailable() + ";" + date + ";" + null), StandardOpenOption.APPEND);
								} catch (IOException e) {
									System.err.println("An error occurred: " + e.getMessage());
								}

								System.out.println("Book added successfully!\n");
							}
						});
					}
					case "2" -> {
						System.out.println("\n1 - Search by title");
						System.out.println("2 - Search by author");
						System.out.println("3 - Search by genre");
						System.out.println("4 - Show recently added books");
						System.out.println("5 - Return");
						answer = input.nextLine();
						switch(answer) {
							case "1" -> {
								Boolean found = false;
								System.out.println("\nEnter the book title:");
								String title = input.nextLine();
								for (Book book : books) {
									if (book.getTitle().contains(title) && book.getIsAvailable()) {
										if(!found) System.out.println("\nBooks with title " + title + ":");
										System.out.println(book);
										found = true;
									}
								}
								if(!found) {
									System.out.println("\nNo books found\n");
								}
							}
							case "2" -> {
								Boolean found = false;
								System.out.println("\nEnter the author name:");
								String authorName = input.nextLine();
								for (Book book : books) {
									if (book.getAuthor().getName().contains(authorName) && book.getIsAvailable()) {
										if(!found) System.out.println("Books by " + authorName + ":");
										System.out.println(book);
										found = true;
									}
								}
								if(!found) {
									System.out.println("\nNo books found\n");
								}
							}
							case "3" -> {
								Boolean found = false;
								System.out.println("\nEnter the genre:");
								String genre = input.nextLine();
								for (Book book : books) {
									if (book.getGenre().contains(genre) && book.getIsAvailable()) {
										if(!found) System.out.println("\nBooks in genre " + genre + ":");
										System.out.println(book);
										found = true;
									}
								}
								if(!found) {
									System.out.println("\nNo books found\n");
								}
							}
							case "4" -> {
								Boolean found = false;
								for (Book book : books) {
									if (book.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isEqual(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
										if(!found) System.out.println("\nRecently added books:");
										System.out.println(book);
										found = true;
									}
								}
								if(!found) {
									System.out.println("\nNo books found\n");
								}
							}
							case "5" -> {
								continue;
							}
						}
					}
					case "3" -> {
						Boolean found = false;
						for (Borrow borrow : borrows) {
							if (borrow.getCustomerId() == loggedCustomer.getId()) {
								if(!found) System.out.println("\nMy borrowed books:");
								System.out.println(borrow);
								found = true;
							}
						}
						if(!found) {
							System.out.println("\nNo borrowed books\n");
						}
					}
					case "4" -> {
						System.out.println("\nEnter your name:");
						String name = input.nextLine();
						System.out.println("Enter the book ID:");
						int bookId = Integer.parseInt(input.nextLine());
						Boolean found = false;
						for (Book book : books) {
							if (book.getId() == bookId && book.getIsAvailable()) {
								Borrow newBorrow = new Borrow(bookId, loggedCustomer.getId(), new Date(), null);
								borrows.add(newBorrow);
								book.setIsAvailable(false);
								System.out.println("\nBook borrowed successfully!\n");
								Path borrowsPath = Paths.get("./db/borrows.csv");

        						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        						String borrowedAt = dateFormat.format(newBorrow.getBorrowedAt());
								String returnedAt = null;
								if (newBorrow.getReturnedAt() != null) returnedAt = dateFormat.format(newBorrow.getReturnedAt());
								try {
									Files.write(borrowsPath, Arrays.asList(newBorrow.getBookId() + ";" + newBorrow.getCustomerId() + ";" + borrowedAt + ";" + returnedAt), StandardOpenOption.APPEND);
								} catch (IOException e) {
									System.err.println("An error occurred: " + e.getMessage());
								}
								found = true;
							}
						}
						if(!found) {
							System.out.println("\nBook not found or not available\n");
						}
					}
					case "5" -> {
						System.out.println("\nEnter the book ID:");
						int bookId = Integer.parseInt(input.nextLine());
						for (Borrow borrow : borrows) {
							if (borrow.getCustomerId() == loggedCustomer.getId() && borrow.getBookId() == bookId) {
								borrow.setReturnedAt(new Date());
								for (Book book : books) {
									if (book.getId() == bookId) {
										book.setIsAvailable(true);
									}
								}
								System.out.println("\nBook returned successfully!\n");
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
					data.get(3), birthDate, Boolean.valueOf(data.get(5)));
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
					if (author.getId() == Integer.parseInt(data.get(3))) {
						bookAuthor = author;
					}
				}

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date createdAt = null;
				Date updatedAt = null;
				try {
					createdAt = dateFormat.parse(data.get(5));
					if(!data.get(6).equals("null")) updatedAt = dateFormat.parse(data.get(6));
				} catch (ParseException e) {
					System.err.println("Error parsing date: " + e.getMessage());
				}

				Book book = new Book(
					Integer.parseInt(data.get(0)), data.get(1), data.get(2), 
					bookAuthor, Boolean.valueOf(data.get(4)), createdAt, updatedAt);
					
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
