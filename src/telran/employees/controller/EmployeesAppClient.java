package telran.employees.controller;
import java.util.*;
import java.util.stream.Collectors;

import telran.employees.services.*;
import telran.net.Sender;
import telran.net.TcpSender;
import telran.view.*;
import java.io.*;
public class EmployeesAppClient {
//private static final String DEFAULT_FILE_NAME = "employees.data";
//private static final Object DATA_FILE_PROPERTY = null;
//static Map<String, String> properties;
static InputOutput io = new ConsoleInputOutput();
	public static void main(String[] args) {
		
		Sender sender = null;
		try {
			sender = new TcpSender("localhost", 2000);
		} catch (Exception e) {
			io.writeObjectLine(e.toString());
		}
		EmployeesMethods employeesMethods = new EmployeesMethodsTcpProxyl(sender);
		employeesMethods.restore();
		HashSet<String> departments = new HashSet<>(Arrays.asList("QA", "Development", "HR", "Management"));
		Menu menu = new Menu("Employees Application", EmployeesActions.getActionItems(employeesMethods, departments));
		menu.perform(io);

	}

}