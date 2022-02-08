package telran.employees.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

import telran.employees.dto.Employee;
import telran.employees.services.EmployeesMethodsMapsImpl;
import telran.employees.services.ReturnCode;
import telran.view.InputOutput;
import telran.view.Item;

public class EmployeesActions {
	private static EmployeesMethodsMapsImpl emp;
	private static HashSet<String> departmentOptions;
	private EmployeesActions(){
	}
	
	public static ArrayList<Item> getEmployeesItems(EmployeesMethodsMapsImpl emp, HashSet<String> departmentOptions){
		EmployeesActions.emp = emp;
		EmployeesActions.departmentOptions = departmentOptions;
		ArrayList<Item> items = new ArrayList<>();
		items.add(Item.of("Add Employee", EmployeesActions::addEmployee));
		items.add(Item.of("Remove Employee", EmployeesActions::removeEmployee));
		items.add(Item.of("Get Employee", EmployeesActions::getEmployee));
		items.add(Item.of("Get all Employees", EmployeesActions::getAllEmployees));
		items.add(Item.of("Get Employees by Age", EmployeesActions::getEmployeesByAge));
		items.add(Item.of("Get Employees by Salary", EmployeesActions::getEmployeesBySalary));
		items.add(Item.of("Get Employees by Department", EmployeesActions::getEmployeesByDepartment));
		items.add(Item.of("Get Employees by Department and Salary", EmployeesActions::getEmployeesByDepartmentAndSalary));
		items.add(Item.of("Update Salary", EmployeesActions::updateSalary));
		items.add(Item.of("Update Department", EmployeesActions::updateDepartment));
		return items;
	}
	
	
	private static Long getID(InputOutput io) {
		return io.readLong("Enter ID");
	}
	private static String getName(InputOutput io) {
		return io.readStringPredicate("Enter name using only letters with capital first letter", "There are not only letters with capital first letter", str -> str.matches("[a-zA-Z]+") && Character.isUpperCase(str.charAt(0)));
	}
	private static LocalDate getBirthDate(InputOutput io) {
		return io.readDate("Enter date of birth as dd/MM/yyyy", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	private static int getSalary(InputOutput io) {
		return io.readInt("Enter salary from 0 to 1000000", 0, 1000000);
	}
	private static String getDepartment(InputOutput io, HashSet<String> options) {
		return io.readStringOption("Enter department" + options.toString(), options );
	}
	
	private static Employee getEmployeeFromInput(InputOutput io) {
		Long ID = getID(io);
		String name = getName(io);
		LocalDate birthDate = getBirthDate(io);
		int salary = getSalary(io);
		String department = getDepartment(io, departmentOptions);
		return new Employee(ID,name,birthDate,salary,department);
	}
	
	private static void addEmployee(InputOutput io) {
		Employee employee = getEmployeeFromInput(io);
		if(ReturnCode.OK == emp.addEmployee(employee)) {
			io.writeObjectLine("Added: " + employee);
		}else {
			io.writeObjectLine("Addition failed");
		}
	}
	
	private static void removeEmployee(InputOutput io) {
		Employee employee = getEmployee(io);
		if(employee!=null && ReturnCode.OK == emp.removeEmployee(employee.id)) {
			io.writeObjectLine("Removed: " + employee);
		}else {
			io.writeObjectLine("Remove failed");
		}
	}
	
	private static Employee getEmployee(InputOutput io) {
		Employee employee = emp.getEmployee(getID(io));
		if(employee != null) {
			io.writeObjectLine("Required: " + employee);
		}else {
			io.writeObjectLine("No employee is found by this ID");
		}
		return employee;
	}
	
	private static void writeResult(InputOutput io, ArrayList<Employee> employees) {
		if(!employees.isEmpty()) {
			io.writeObjectLine("Required: " + employees);
		}else {
			io.writeObjectLine("No employees are found");
		}
	}
	
	private static Iterable<Employee> getAllEmployees(InputOutput io) {
		ArrayList<Employee> employees = (ArrayList<Employee>) emp.getAllEmployees();
		writeResult(io,employees);
		return employees;
	}
	
	private static Iterable<Employee> getEmployeesByAge(InputOutput io) {
		int[] ages = new int[] {
				io.readInt("Enter minimum age"), io.readInt("Enter maximum age")
		};
		ArrayList<Employee> employees = (ArrayList<Employee>) emp.getEmployeesByAge(ages[0], ages[1]);
		writeResult(io,employees);
		return employees;
	}
	
	private static Iterable<Employee> getEmployeesBySalary(InputOutput io) {
		int[] salaries = new int[] {
				io.readInt("Enter minimum salary"), io.readInt("Enter maximum salary")
		};
		ArrayList<Employee> employees = (ArrayList<Employee>) emp.getEmployeesByAge(salaries[0], salaries[1]);
		writeResult(io,employees);
		return employees;
	}
	
	private static Iterable<Employee> getEmployeesByDepartment(InputOutput io) {
		ArrayList<Employee> employees = (ArrayList<Employee>) emp.getEmployeesByDepartment(getDepartment(io, departmentOptions));
		writeResult(io,employees);
		return employees;
	}
	
	private static Iterable<Employee> getEmployeesByDepartmentAndSalary(InputOutput io) {
		int[] salaries = new int[] {
				io.readInt("Enter minimum salary"), io.readInt("Enter maximum salary")
		};
		ArrayList<Employee> employees = (ArrayList<Employee>) emp.getEmployeesByDepartmentAndSalary(getDepartment(io, departmentOptions), salaries[0], salaries[1]);
		writeResult(io,employees);
		return employees;
	}
	
	private static void update(InputOutput io, Employee employee, ReturnCode code) {
		if(employee!=null && ReturnCode.OK == code) {
			io.writeObjectLine("Updated: " + emp.getEmployee(employee.id));
		}else {
			io.writeObjectLine("Update is failed");
		}
	}
	
	private static void updateSalary(InputOutput io) {
		Employee employee = getEmployee(io);
		update(io, employee, emp.updateSalary(employee.id, io.readInt("Enter new salary")));
	}
	
	private static void updateDepartment(InputOutput io) {
		Employee employee = getEmployee(io);
		update(io, employee, emp.updateDepartment(getID(io), io.readStringOption("Enter new department" + departmentOptions.toString(), departmentOptions)));
	}

}
