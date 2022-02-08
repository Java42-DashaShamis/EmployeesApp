package telran.numbers.controller;

import telran.view.ConsoleInputOutput;

import java.util.ArrayList;

import telran.numbers.service.Calculator;
import telran.view.*;

public class CalculatorApp {

	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();
		Calculator calculator = new Calculator();
		ArrayList<Item> items = CalculatorActions.getCalculatorItems(calculator);
		items.add(Item.exit());
		
		Menu menu = new Menu("Calculator", items);
		menu.perform(io);
	}

}
