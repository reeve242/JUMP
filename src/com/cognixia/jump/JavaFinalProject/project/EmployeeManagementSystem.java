
package com.cognixia.jump.JavaFinalProject.project;

import java.io.BufferedReader;


//import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.PrintWriter;
import java.util.ArrayList;
//import java.util.Collection;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
//import java.util.stream.Stream;

//import com.sun.org.apache.xerces.internal.xs.StringList;

public class EmployeeManagementSystem {
	// attributes of the class
	static String txtFile = "resources/employee.txt";
	static String csvFile = "resources/employee.csv";
	static ArrayList<Employee> employeeList = new ArrayList<Employee>();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// grab data list from txt file
		fileReader(txtFile);
		
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

			String line;

			while ((line = bufferedReader.readLine()) != null) {
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

		try {

			file = new File(fileName);

			file.createNewFile();

			fileWriter = new FileWriter(file, false);			
			
			writeToFile(fileWriter);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} // finally

	} // fileWriter method
	public static void writeToFile(FileWriter writer) throws IOException {
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
	
	public static void inputPrompt() {
		
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
				
				int userInput = sc.nextInt();

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
						//DISPLAY DEPARTMENT INFO
						displayDepartmentList();
						break;
					case 6: 
						//DISPLAY EMPLOYEE IDS
						displayEmployeeIds();
						break;
					case 7: 
						//SAVE INFO AND BE DONE
						stillInput=false;
						fileWriter(csvFile);
						fileWriter(txtFile);
						System.out.println("COMPLETION: Employee Management System Managed.");
						break;
					default: 
						System.out.println("Did not select an available selection from the list, please try again\n"+sb);
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
			System.out.println("Enter employee's first name");
			String empFn;
			try {
				empFn = sc.next();
			}catch(InputMismatchException in) {
				System.out.println("Please enter a valid name");
				return;
			}
			
			//LAST NAME
			System.out.println("Enter employee's last name");
			String empLn;
			try {
				empLn=sc.next();
			}catch(InputMismatchException in) {
				System.out.println("Please enter a valid name");
				return;
			}
			
			//AGE
			System.out.println("Enter employee's age");
			int empAge=0;
			try {
				empAge=sc.nextInt();
			}catch(InputMismatchException in) {
				System.out.println("Please enter a valid age");
			}
			
			//SALARY
			System.out.println("Enter employee's salary");
			int empSal=0;
			try {
				empSal=sc.nextInt();
			}catch(InputMismatchException in) {
				System.out.println("Please enter a valid salary");
			}
			
			//DEPARTMENT
			System.out.println("Enter employee's department");
			String empDep="";
			try {
				empDep=sc.next();
			}catch(InputMismatchException in) {
				System.out.println("Please enter a valid department");
			}

			Employee e = new Employee(empId, empFn, empLn, empAge, empSal, empDep);
			employeeList.add(e);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	} //add employee
	
	public static void updateEmployee() {
		try {
			System.out.println("-------------------------------------------------------\nEnter employee's ID number to update their info");
			int empId = sc.nextInt();
			for (Employee emp : employeeList) {
				if(emp.getId()== empId) {
					//ask for updates in switch statement
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
						int userInput = sc.nextInt();

						//switch statement for options
						switch(userInput) {
							case 1: 
								//UPDATE FIRST NAME
								System.out.println("Enter employee's first name");
								String empFn = sc.next();
								emp.setFirstName(empFn);
								break;
							case 2: 
								//UPDATE LAST NAME
								System.out.println("Enter employee's last name");
								String empLn = sc.next();
								emp.setLastName(empLn);
								break;
							case 3: 
								//UPDATE AGE
								System.out.println("Enter employee's age");
								int empAge = sc.nextInt();
								emp.setAge(empAge);
								break;
							case 4: 
								//UPDATE SALARY
								System.out.println("Enter employee's salary");
								int empSal = sc.nextInt();
								emp.setSalary(empSal);
								break;
							case 5: 
								//UPDATE DEPARTMENT
								System.out.println("Enter employee's department");
								String empDep = sc.next();
								emp.setDepartment(empDep);
								break;
							case 6: 
								//SAVE AND CLOSE
								stillInput=false;
								System.out.println("Successfully updated employee: "+ emp.getFirstName() + " "+ emp.getLastName()+"\n");
								System.out.println("-------------------------------------------------------");
								break;
							default: 
								System.out.println("Did not select an available selection from the list, please try again\n"+sb);
								break;
								
						}//switch
					}//while
				}
			}//switch statement with scanner
		}catch(Exception e) {
			e.printStackTrace();
		}
	} //update employee
	
	public static void removeEmployee() {
		try {
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
			sb.append("DEPARTMENT: "+ entry.getKey().toUpperCase() + " \n");
			sb.append("EMPLOYEES: " + entry.getValue());
		    System.out.println(sb);
		});
		
//		Map<String,String> result = (Map<String, String>) depMap.entrySet()
//					.stream()
//					//.filter(map->"it".equals(map.getKey()))
//					.collect(Collectors.toMap(map->map.getKey(),map->map.getValue()));
//		System.out.println(result);
//		
	}
	
	public static void displayEmployeeIds() {
		Map<Integer, List<Employee>> employeeById = employeeList
				.stream()
				.collect(Collectors.groupingBy(e -> e.getId()));
		
		employeeById
			.forEach((id,e)-> System.out.format("ID: %s - %s\n", id,e.get(0).getDisplayName()));
	}
	
}
