package telran.employees.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import telran.employees.services.EmployeesMethodsMapsImpl;
import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class EmployeesApp {

	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();
		
		if(args.length < 1) {
			io.writeObjectLine("Usage argument should contain configuration file name");
			return;
		}
		//Configuration file contains text like employeesDataFile = employees.data
		//Apply BufferedReader for reading configuration
		String fileName = getFileName(args[0]);
		
		EmployeesMethodsMapsImpl emp = new EmployeesMethodsMapsImpl(fileName);
		emp.restore();
		
		HashSet<String> options = new HashSet<String>(Arrays.asList("culture", "sport", "economics", "health", "defence"));
		ArrayList<Item> items = EmployeesActions.getActionItems(emp, options);
		items.add(Item.exit());
		
		Menu menu = new Menu("Employees", items);
		menu.perform(io);
	}

	private static String getFileName(String configFile) {
		// TODO Auto-generated method stub
		return null;
	}

}
