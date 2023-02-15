package org.krk;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
@SuppressWarnings("all")
public class MultiTask {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Configuration cfg=new Configuration().configure();
		SessionFactory sf=cfg.buildSessionFactory();
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		System.out.println("Press 1 for Insert or Update, Press 2 for Delete, Press 3 for Find, Press 4 for Fetch all data");
		int n=sc.nextInt();
		switch (n) 
		{
		case 1:
			System.out.println("Again Press 1 for Insert or Press 2 for Termination");
			while(sc.hasNext()) 
			{
				if(sc.nextInt()==2) {
					System.err.println("Insert or Update Terminated");
					break;
				}
				else 
				{
					Employee e1=new Employee();
					System.out.println("Enter the Employee ID");
					e1.setEmpid(sc.nextInt());
					sc.nextLine();
					System.out.println("Enter your Employee Name");
					e1.setEname(sc.nextLine());
					System.out.println("Enter your Designation");
					e1.setDesignation(sc.nextLine());
					System.out.println("Enter your Mail ID");
					e1.setEmail(sc.nextLine());
					System.out.println("Enter your Password");
					e1.setPassword(sc.nextLine());
					System.out.println("Enter your Salary");
					e1.setSal(sc.nextInt());
					s.saveOrUpdate(e1);
					t.commit();
				}
				System.out.println("Enter 1 for Insert or 2 for Termination");
			}
			System.out.println("Insert or Update a Data Completed!!");
			break;
		case 2:
			System.out.println("Enter the ID for delete the Record");
			Employee e2=s.find(Employee.class, sc.nextInt());
			if(e2!=null) {
				s.delete(e2);
				System.out.println(e2.getEmpid()+": Record was Deleted");}
			else
				System.err.println("Record Not Found");
			break;
		case 3:
			System.out.println("Enter your Primary Key to Find a Record");
			Employee e3=s.find(Employee.class, sc.nextInt());
			if(e3!=null)
			{
				System.out.println("ID :"+e3.getEmpid());
				System.out.println("Name :"+e3.getEname());
				System.out.println("Designation :"+e3.getDesignation());
				System.out.println("Salary :"+e3.getSal());
				System.out.println("Email :"+e3.getEmail());
				System.out.println("Password :"+e3.getPassword());
			}
			break;
		case 4:

			String qry="select e from Employee e";
			Query<Employee> q=s.createQuery(qry);
			List<Employee> el=q.getResultList();
			for(Employee e:el)
			{
				System.out.println("-------------------------------------");
				System.out.println("ID :"+e.getEmpid());
				System.out.println("Name :"+e.getEname());
				System.out.println("Designation :"+e.getDesignation());
				System.out.println("Salary :"+e.getSal());
				System.out.println("Email :"+e.getEmail());
				System.out.println("Password :"+e.getPassword());
				System.out.println("-------------------------------------");
			}
			break;
		default:

			break;
		}
		s.close();
		sc.close();
		System.out.println("All Transactions Completed");
	}
}