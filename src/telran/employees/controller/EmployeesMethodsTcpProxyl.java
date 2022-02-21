package telran.employees.controller;

import telran.employees.dto.DepartmentAndSalaryData;
import telran.employees.dto.Employee;
import static telran.employees.net.dto.ApiConstants.*;

import java.io.Serializable;

import telran.employees.services.EmployeesMethods;
import telran.employees.services.ReturnCode;
import telran.net.Sender;

public class EmployeesMethodsTcpProxyl implements EmployeesMethods {
	
	private Sender sender;
	private static final long serialVersionUID = 1L;
	
	public EmployeesMethodsTcpProxyl(Sender sender) {
		this.sender = sender;
	}

	@Override
	public ReturnCode addEmployee(Employee emp1) {
		return sender.send(ADD_EMPLOYEE, emp1);
	}

	@Override
	public ReturnCode removeEmployee(long id) {
		return sender.send(REMOVE_EMPLOYEE, id );
	}

	@Override
	public Iterable<Employee> getAllEmployees() {
		return sender.send(GET_EMPLOYEES, "" );
	}

	@Override
	public Employee getEmployee(long id) {
		return sender.send(GET_EMPLOYEE, id );
	}

	@Override
	public Iterable<Employee> getEmployeesByAge(int ageFrom, int ageTo) {
		return sender.send(GET_EMPLOYEES_BY_AGE, (Serializable) new DepartmentAndSalaryData("", 0, ageFrom, ageTo));
	}

	@Override
	public Iterable<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
		return sender.send(GET_EMPLOYEES_BY_SALARY, (Serializable) new DepartmentAndSalaryData("", 0, salaryFrom, salaryTo));
	}

	@Override
	public Iterable<Employee> getEmployeesByDepartment(String department) {
		return sender.send(GET_EMPLOYEES_BY_DEPARTMENT, department);
	}

	@Override
	public Iterable<Employee> getEmployeesByDepartmentAndSalary(String department, int salaryFrom, int salaryTo) {
		return sender.send(GET_EMPLOYEES_BY_DEPARTMENT_AND_SALARY, (Serializable) new DepartmentAndSalaryData(department, 0, salaryFrom, salaryTo));
	}

	@Override
	public ReturnCode updateSalary(long id, int newSalary) {
		return sender.send(UPDATE_SALARY, (Serializable) new DepartmentAndSalaryData("", id, 0, newSalary));
	}

	@Override
	public ReturnCode updateDepartment(long id, String newDepartment) {
		return sender.send(UPDATE_DEPARTMENT, (Serializable) new DepartmentAndSalaryData(newDepartment, id, 0, 0));
	}

	@Override
	public void restore() {
		sender.send(RESTORE, "");
	}

	@Override
	public void save() {
		sender.send(SAVE, "");
	}

}
