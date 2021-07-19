
package com.cognixia.jump.JavaFinalProject.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
//import java.util.Collection;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//import com.sun.org.apache.xerces.internal.xs.StringList;

public class EmployeeManagementSystem {
	// attributes of the class
	static String txtFile = "resources/employee.txt";
	static String csvFile = "resources/employee.csv";
	static ArrayList<Employee> employeeList = new ArrayList<Employee>();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// grab data list from txt file
		fileReader(txtFile);
		//System.out.println("hi");
		// immediately put data in csv file for manipulation
		fileWriter(csvFile);

		// prompt user for input/direction
		inputPrompt();
		
		sc.close();
	}

	public static void fileReader(String fileName) {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try {

			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);

			// System.out.println("** CONTENTS OF 'employee.txt' **\n");

			String line;

			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				String[] data = line.split(",");

				// grab data from inputed string and make a new employee object
				// and add to list of employees
				int id = Integer.parseInt(data[0]);
				String fn = data[1];
				String ln = data[2];
				int age = Integer.parseInt(data[3]);
				int sal = Integer.parseInt(data[4]);
				String dep = data[5];
				Employee e = new Employee(id, fn, ln, age, sal, dep);
				employeeList.add(e);

			}

		} catch (FileNotFoundException e) {
			System.out.println("*** FILE NOT FOUND EXCEPTION ***");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("*** IO EXCEPTION ***");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("*** GENERAL EXCEPTION ***");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				bufferedReader.close();
				// System.out.println("SUCCESSFULLY closed file reader stream");
			} catch (IOException e) {
				System.out.println("ERR: FAILED to close file reader stream");
				e.printStackTrace();
			}
		}
	} // fileReader Method
	public static void fileWriter(String fileName) {

		File file = null;
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		PrintWriter printWriter = null;

		try {

			file = new File(fileName);

//			if (file.createNewFile()) {
//				System.out.println("SUCCESS: employee.csv created");
//			} else {
//				System.out.println("FAILURE: employee.csv NOT created");
//			}
			file.createNewFile();

			// They wrap into each other: printW> bufferedW> fileW
			fileWriter = new FileWriter(file, false);
			//bufferedWriter = new BufferedWriter(fileWriter);
			//printWriter = new PrintWriter(bufferedWriter);

			
			
			writeToFile(fileWriter);
			//appendToFile(bufferedWriter, "Hi");
			//writeUsingPrintWriter(printWriter, "Hello World");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

//			if (bufferedWriter != null) {
//				try {
//					bufferedWriter.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
//			if (printWriter != null) {
//				printWriter.close();
//
//			}

		} // finally

	} // fileWriter method
	public static void writeToFile(FileWriter writer) throws IOException {
		// change content ======================================================
		for (Employee emp : employeeList) {
			StringBuffer sb = new StringBuffer();
			sb.append(emp.getId());
			sb.append(",");
			sb.append(emp.getFirstName());
			sb.append(",");
			sb.append(emp.getLastName());
			sb.append(",");
			sb.append(emp.getAge());
			sb.append(",");
			sb.append(emp.getSalary());
			sb.append(",");
			sb.append(emp.getDepartment()+"\n");
			writer.write(sb.toString());
		}
		

	} // writeToFile

	public static void appendToFile(BufferedWriter writer, String str) throws IOException {
		// change content ====================================================
		for (int i = 0; i < 3; i++) {
			writer.append(str);
		}
	} //append to file

	public static void writeUsingPrintWrite(PrintWriter writer, String str) {
		// change content ======================================================
		writer.println();
		writer.print(str);
		writer.println(str);

	} // writeUsingPrintWrite
	
	public static void inputPrompt() {
		//System.out.println("Select an option of the Employee Management System: \n1.Add Employee\n2.Update Employee\n3.Remove Employee\n4.List Employee Information\n5.Save and Exit\n");
		
		try {
			boolean stillInput=true;
			while(stillInput) {
				StringBuffer sb = new StringBuffer();
				sb.append("-------------------------------------------------------\n");
				sb.append("Select an option of the Employee Management System: \n");
				sb.append("1.Add Employee\n");
				sb.append("2.Update Employee\n");
				sb.append("3.Remove Employee\n");
				sb.append("4.List Employee Information\n");
				sb.append("5.Display Department List\n");
				sb.append("6.Display Employee Ids\n");
				sb.append("7.Save and Exit\n");
				System.out.println(sb);
				//Scanner sc = new Scanner(System.in);
				int userInput = sc.nextInt();
				//System.out.println("CHOSE OPTION : "+ userInput);

				//switch statement for options
				switch(userInput) {
					case 1: 
						//ADD EMPLOYEE
						addEmployee();
						break;
					case 2: 
						//UPDATE EMPLOYEE
						updateEmployee();
						break;
					case 3: 
						//REMOVE EMPLOYEE
						removeEmployee();
						break;
					case 4: 
						//LIST EMPLOYEE INFO
						listEmployeeInfo();
						break;
					case 5: 
						displayDepartmentList();
					case 6: 
						displayEmployeeIds();
						break;
					case 7: 
						//save info and be done
						stillInput=false;
						fileWriter(csvFile);
						fileWriter(txtFile);
						//System.out.println("Wrote to file and done with program");
						//sc.close();
						break;
					default: 
						System.out.println("Did not select an available selection from the list, please try again\n1. Add Employee\n2.Update Employee\n3.Remove Employee\n4.List Employee Information\n5.Save and Exit\n");
						break;
						
				}//switch
			}//while
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	} //input prompt method

	
	public static void addEmployee() {
		try {
			//ID
			//Scanner scId = new Scanner(System.in);
			System.out.println("Enter employee id");
			int empId=0;
			try {
				empId = sc.nextInt();
			}catch(InputMismatchException in) {
				System.out.println("Please enter a valid number");
				return;
			}
			//scId.close();
			
			//FIRST NAME
			//Scanner scFn = new Scanner(System.in);
			System.out.println("Enter employee's first name");
			String empFn = sc.next();
			//scFn.close();
			
			//LAST NAME
			//Scanner scLn = new Scanner(System.in);
			System.out.println("Enter employee's last name");
			String empLn = sc.next();
			//scLn.close();
			
			//AGE
			//Scanner scAge = new Scanner(System.in);
			System.out.println("Enter employee's age");
			int empAge = sc.nextInt();
			//scAge.close();
			
			//SALARY
			//Scanner scSal = new Scanner(System.in);
			System.out.println("Enter employee's salary");
			int empSal = sc.nextInt();
			//scSal.close();
			
			//DEPARTMENT
			//Scanner scDep = new Scanner(System.in);
			System.out.println("Enter employee's department");
			String empDep = sc.next();
			//scDep.close();
			System.out.println("DDD " + empId);
			Employee e = new Employee(empId, empFn, empLn, empAge, empSal, empDep);
			employeeList.add(e);
			System.out.println(employeeList);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	} //add employee
	
	public static void updateEmployee() {
		try {
			//Scanner scId = new Scanner(System.in);
			System.out.println("-------------------------------------------------------\nEnter employee's ID number to update their info");
			int empId = sc.nextInt();
			for (Employee emp : employeeList) {
				if(emp.getId()== empId) {
					//ask for updates in switch statement
					//System.out.println("Select an option to update an employee: \n1.Update first name\n2.Update last name\n3.Update age\n4.Update salary\n5.Update department\n6.Finish update\n");
					boolean stillInput=true;
					while(stillInput) {
						StringBuffer sb = new StringBuffer();
						sb.append("-------------------------------------------------------\n");
						sb.append("Select an option to update an employee: \n");
						sb.append("1.Update first name\n");
						sb.append("2.Update last name\n");
						sb.append("3.Update age\n");
						sb.append("4.Update salary\n");
						sb.append("5.Update department\n");
						sb.append("6.Finish Update\n");
						System.out.println(sb);
						//Scanner sc = new Scanner(System.in);
						int userInput = sc.nextInt();

						//switch statement for options
						switch(userInput) {
							case 1: 
								//UPDATE FIRST NAME
								//Scanner scFn= new Scanner(System.in);
								System.out.println("Enter employee's first name");
								String empFn = sc.next();
								emp.setFirstName(empFn);
								//scFn.close();
								break;
							case 2: 
								//UPDATE LAST NAME
								//Scanner scLn= new Scanner(System.in);
								System.out.println("Enter employee's last name");
								String empLn = sc.next();
								emp.setLastName(empLn);
								//scLn.close();
								break;
							case 3: 
								//UPDATE AGE
								//Scanner scAge= new Scanner(System.in);
								System.out.println("Enter employee's age");
								int empAge = sc.nextInt();
								emp.setAge(empAge);
								//scAge.close();
								break;
							case 4: 
								//UPDATE SALARY
								//Scanner scSal= new Scanner(System.in);
								System.out.println("Enter employee's salary");
								int empSal = sc.nextInt();
								emp.setSalary(empSal);
								//scSal.close();
								break;
							case 5: 
								//UPDATE DEPARTMENT
								//Scanner scDep= new Scanner(System.in);
								System.out.println("Enter employee's department");
								String empDep = sc.next();
								emp.setDepartment(empDep);
								//scDep.close();
								break;
							case 6: 
								//SAVE AND CLOSE
								stillInput=false;
								System.out.println("Successfully updated employee: "+ emp.getFirstName() + " "+ emp.getLastName()+"\n");
								System.out.println("-------------------------------------------------------");
								//scId.close();
								break;
							default: 
								System.out.println("Did not select an available selection from the list, please try again\nSelect an option to update an employee: \n1. Update first name\n2.Update last name\n3.Update age\n4.Update salary\n5.Update department\n6.Finish update\n");
								break;
								
						}//switch
					}//while
				}
			}
			//switch statement with scanner
		}catch(Exception e) {
			e.printStackTrace();
		}
	} //update employee
	public static void removeEmployee() {
		try {
			//Scanner scId = new Scanner(System.in);
			System.out.println("Enter employee's ID number to remove");
			int empId = sc.nextInt();
			for (Employee emp : employeeList) {
				if(emp.getId()== empId) {
					employeeList.remove(emp);
					System.out.println("Employee "+ emp.getFirstName() + " "+ emp.getLastName()+ " was successfully removed");
					break;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	} //remove employee
	
	public static void listEmployeeInfo() {
		try {
			//Scanner scId = new Scanner(System.in);
			System.out.println("Enter employee's ID to display their information");
			int empId = sc.nextInt();
			for (Employee emp : employeeList) {
				if(emp.getId()== empId) {
					System.out.println("-------------------------------------------------------\nEMPLOYEE INFO: \nID: "+ emp.getId() + "\nNAME: "+ emp.getFirstName() + " " + emp.getLastName() + "\nAGE: "+ emp.getAge()+ "\nSALARY: "+ emp.getSalary() + "\nDEPARTMENT: "+ emp.getDepartment() + "\n");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	} //list employee info
	
	public static void saveAndExit() {
		//save to csv
		fileWriter(csvFile);
		
		
	} //save and exit
	
	public static void displayDepartmentList() {
		HashMap<String,String> depMap = new HashMap<String,String>();
		for(Employee emp : employeeList) {
			if(!depMap.containsKey(emp.getDepartment())) {
				depMap.put(emp.getDepartment(), emp.getDisplayName());
				
			}else {
				String tempEmp = depMap.get(emp.getDepartment());
				depMap.put(emp.getDepartment(), tempEmp+ ", " + emp.getDisplayName());
			}
		}
		depMap.entrySet().forEach(entry -> {
			StringBuffer sb = new StringBuffer();
			sb.append("-------------------------------------------------------\n");
			sb.append("DEPARTMENT: "+ entry.getKey() + " \n");
			sb.append("EMPLOYEES: " + entry.getValue());
		    System.out.println(sb);
		});
		
		Map<String,String> result = (Map<String, String>) depMap.entrySet()
					.stream()
					//.filter(map->"it".equals(map.getKey()))
					.collect(Collectors.toMap(map->map.getKey(),map->map.getValue()));
		System.out.println(result);
		
		
		
	}
	
	public static void displayEmployeeIds() {
		Map<Integer, List<Employee>> employeeById = employeeList
				.stream()
				.collect(Collectors.groupingBy(e -> e.getId()));
		
		employeeById
			.forEach((id,e)-> System.out.format("ID: %s - %s\n", id,e.get(0).getDisplayName()));
	}
	
}
