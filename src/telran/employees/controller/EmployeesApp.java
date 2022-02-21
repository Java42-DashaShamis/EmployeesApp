package telran.employees.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

import telran.employees.services.EmployeesMethodsMapsImpl;
import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class EmployeesApp {
	private static final String DEFAULT_FILE_NAME = "employees.data";
	private static final Object DATA_FILE_PROPERTY = null;
	static Map<String, String> properties;
	static InputOutput io = new ConsoleInputOutput();
	public static void main(String[] args) {
		
		
		if(args.length < 1) {
			try {
				io.writeObjectLine("Usage argument should contain configuration file name");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

		if(properties == null) {
			
			try(BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
				properties = reader.lines().map(str -> str.split("[ =:]+"))
						.collect(Collectors.toMap(tokens -> tokens[0], tokens -> tokens[1]));
				
			} catch (Exception e) {
				io.writeObjectLine(e.getMessage());
				return null;
			}
		}
		return properties.getOrDefault(DATA_FILE_PROPERTY, DEFAULT_FILE_NAME);
	}

}
