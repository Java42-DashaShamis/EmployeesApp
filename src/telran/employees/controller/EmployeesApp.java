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
		
		EmployeesMethodsMapsImpl emp = new EmployeesMethodsMapsImpl();
		HashSet<String> options = new HashSet<String>(Arrays.asList("culture", "sport", "economics", "health", "defence"));
		ArrayList<Item> items = EmployeesActions.getEmployeesItems(emp, options);
		items.add(Item.exit());
		
		Menu menu = new Menu("Employees", items);
		menu.perform(io);
	}

}
