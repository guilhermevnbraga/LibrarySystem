
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

		for (Customer customer : customers) {
			System.out.println(customer.toString());
		}
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
				Date date = null;
				try {
					date = dateFormat.parse(data.get(4));
				} catch (ParseException e) {
					System.err.println("Error parsing date: " + e.getMessage());
				}
				Customer customer = new Customer(
					Integer.parseInt(data.get(0)), data.get(1), data.get(2),
					Integer.parseInt(data.get(3)), date, new Date());
				customers.add(customer);
			});

		} catch (IOException e) {
			System.err.println("An error occurred: " + e.getMessage());
		}
		
		return customers;
	}
}
