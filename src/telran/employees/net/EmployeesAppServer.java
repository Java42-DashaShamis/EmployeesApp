package telran.employees.net;

import telran.employees.services.EmployeesMethods;
import telran.employees.services.EmployeesMethodsMapsImpl;
import telran.net.TcpServer;

public class EmployeesAppServer {
	
	public static void main(String[] args) throws Exception {
		EmployeesMethods employees = new EmployeesMethodsMapsImpl("employees.data");
		TcpServer server = new TcpServer(2000, new EmployeeProtocol(employees));
		server.run();
	}

}
