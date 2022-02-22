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
/* V.R. The following stack appears in case of "11" selection in menu
 * Try to understand why it is happened and what is necessary to do
 * to prevent it.
java.net.SocketException: Connection reset
	at java.base/sun.nio.ch.NioSocketImpl.implRead(NioSocketImpl.java:323)
	at java.base/sun.nio.ch.NioSocketImpl.read(NioSocketImpl.java:350)
	at java.base/sun.nio.ch.NioSocketImpl$1.read(NioSocketImpl.java:803)
	at java.base/java.net.Socket$SocketInputStream.read(Socket.java:966)
	at java.base/java.net.Socket$SocketInputStream.read(Socket.java:961)
	at java.base/java.io.ObjectInputStream$PeekInputStream.peek(ObjectInputStream.java:2853)
	at java.base/java.io.ObjectInputStream$BlockDataInputStream.peek(ObjectInputStream.java:3180)
	at java.base/java.io.ObjectInputStream$BlockDataInputStream.peekByte(ObjectInputStream.java:3190)
	at java.base/java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1693)
	at java.base/java.io.ObjectInputStream.readObject(ObjectInputStream.java:514)
	at java.base/java.io.ObjectInputStream.readObject(ObjectInputStream.java:472)
	at telran.net.TcpClientServer.run(TcpClientServer.java:27)
	at telran.net.TcpServer.run(TcpServer.java:26)
	at telran.employees.net.EmployeesAppServer.main(EmployeesAppServer.java:12)

 */
}
