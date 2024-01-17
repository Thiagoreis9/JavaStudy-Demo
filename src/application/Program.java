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
		
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
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
		System.out.print("Want run TEST 4 (s/n)? ");
		char resp = sc.next().charAt(0);
		sc.nextLine();
		if(resp == 's') {
			System.out.println("Sigin new Seller: ");
			System.out.print("Name: ");
			String name1 = sc.nextLine();
			System.out.print("Email: ");
			String email1 = sc.nextLine();
			System.out.print("BirthDate (dd/MM/yyyy): ");
			String date1 = sc.next();
			Date birthDate1 = new java.sql.Date(sdf.parse(date1).getTime());
			System.out.print("BaseSalary: ");
			Double baseSalary1 = sc.nextDouble();
			sellerDao.insert(new Seller(name1, email1, birthDate1, baseSalary1, department));
		}

		System.out.println();
		System.out.println("===== TEST 5: seller update =====");
		System.out.print("Want run TEST 5 (s/n)? ");
		resp = sc.next().charAt(0);
		sc.nextLine();
		if(resp == 's') {
			System.out.print("Insert the id of the seller to be updated: ");
			int sellerId = sc.nextInt();
			System.out.println("Update current Seller #" + sellerId + ": ");
			System.out.print("Name: ");
			sc.nextLine();
			String name = sc.nextLine();
			System.out.print("Email: ");
			String email = sc.nextLine();
			System.out.print("BirthDate (dd/MM/yyyy): ");
			String date = sc.next();
			Date birthDate = new java.sql.Date(sdf.parse(date).getTime());
			System.out.print("BaseSalary: ");
			Double baseSalary = sc.nextDouble();
			seller = sellerDao.findById(sellerId);
			seller.setName(name);
			seller.setEmail(email);
			seller.setBirthDate(birthDate);
			seller.setBaseSalary(baseSalary);
			sellerDao.update(seller);
			System.out.println("Update Completed!");
		}
		
		sc.close();

	}

}
