package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("===== TEST 1: seller findByID =====");
		Seller seller = sellerDao.findById(8);
		System.out.println(seller);
		
		System.out.println();
		System.out.println("===== TEST 2: seller findByDepartment =====");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		list.forEach(System.out::println);
		
		System.out.println();
		System.out.println("===== TEST 3: seller findAll =====");
		list = sellerDao.findAll();
		list.forEach(System.out::println);
		System.out.println("Seller quantity: " + list.size());
		
		System.out.println();
		System.out.println("===== TEST 4: seller insert =====");
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Sigin new Seller: ");
		System.out.print("Name: ");
		String name = sc.nextLine();
		System.out.print("Email: ");
		String email = sc.nextLine();
		System.out.print("BirthDate (dd/MM/yyyy): ");
		String date = sc.next();
		Date birthDate = new java.sql.Date(sdf.parse(date).getTime());
		System.out.print("BaseSalary: ");
		Double baseSalary = sc.nextDouble();
		sellerDao.insert(new Seller(name, email, birthDate, baseSalary, department));
		sc.close();

	}

}
