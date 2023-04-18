package ATM_PROJECT;
import java.util.Scanner;

import java.sql.*;


public class ATM {

	public static void main(String[] args) {
		
		
	try {
	//	Class.forName("com.postgresql.jdbc.Driver");
							
		Connection con=DriverManager.getConnection("jdbc:postgresql://localhost/atm_db","postgres","muthu@123");
		 //  create statement is a function which is used to execute your code and creating this statement
		 Statement stm=con.createStatement();
							
		Scanner scanner=new Scanner(System.in);
		System.out.println("WELCOME TO ALL IN ONE ATM");
		System.out.println("Enter your pin number");
		int pin=scanner.nextInt();
	ResultSet rs=stm.executeQuery("SELECT * FROM atm_db where cus_pin="+pin);
	
	
			String cus_name=null;
			int cus_balance=0;
			int count=0;
		while(rs.next()){
				cus_name=rs.getString(3);
				cus_balance=rs.getInt(4);
				count++;
					}
		int addAmount=0;
		int takeAmount=0;
						
     	if(count>0) {
				System.out.println("Enter your name");
				cus_name=scanner.next();
				System.out.println("Welcome "+cus_name);
			while(true) {
									System.out.println("Press 1 to check your balance");
									System.out.println("Press 2 to addAmount");
									System.out.println("Press 3 to takeAmount");
									System.out.println("Press 4 to take resipt");
									System.out.println("Press 5 to exit /Quit");
									
									System.out.println();
									
									System.out.println("Enter the Option : ");
									int option=scanner.nextInt();
									
									switch(option) {
									case 1:
										System.out.println("your current balance is= "+cus_balance);
										break;
									case 2:
										System.out.println("How much amount did you want to add to your amount");
										addAmount=scanner.nextInt();
										cus_balance=addAmount+cus_balance;
										int bal=stm.executeUpdate("update atm_db SET cus_balance ="+cus_balance+" where cus_pin="+pin);
										
										// Syntax: UPDATE emp SET ename=? where empno=?"
										System.out.println("successfully credited= "+cus_balance);
										
										break;
									case 3:
										System.out.println("How much amount did you want to take");
										takeAmount=scanner.nextInt();
										if(takeAmount>cus_balance) {
											System.out.println("your balance is insuffient= "+cus_balance);
											System.out.println("try less than your available balance");
										}else {
											cus_balance=cus_balance-takeAmount;
											int sub=stm.executeUpdate("update  atm_db SET cus_balance ="+cus_balance+"where cus_pin="+pin);
											System.out.println("successfull taken= "+cus_balance);
										}
										break;
									case 4:
										System.out.println("welcome to all in one mini ATM");
										System.out.println("Available balance is= "+cus_balance);
										System.out.println("Amount desposited= "+addAmount);
										System.out.println("Amount taken= "+takeAmount);
										break;
									default:
										System.out.println("press the number below 5");
										break;
									}
									if(option==5) {
										System.out.println("thank you");
										break;
									}
								}
							}else {
								System.out.println("wrong pin pin number");
							}

						}catch(SQLException e){
							System.out.println(e);
						  }
						}
}


	
