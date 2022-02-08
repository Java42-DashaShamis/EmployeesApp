package telran.numbers.controller;

import java.util.ArrayList;

import telran.numbers.service.Calculator;
import telran.view.InputOutput;
import telran.view.Item;

public class CalculatorActions {
	private static Calculator calculator;
	private CalculatorActions() {
		
	}
	public static ArrayList<Item> getCalculatorItems(Calculator calculator){
		CalculatorActions.calculator = calculator;
		ArrayList<Item> items = new ArrayList<>();
		items.add(Item.of("Add two numbers", CalculatorActions::add));
		items.add(Item.of("Subtract two numbers", CalculatorActions::subtract));
		items.add(Item.of("Devide two numbers", CalculatorActions::devide));
		items.add(Item.of("Multiply two numbers", CalculatorActions::multiply));
		
		return items;
	}
	private static double[] enterTwoNumbers(InputOutput io) {
		return new double[] {
				io.readDouble("Enter first number"), io.readDouble("Enter second number")
		};
	}
	private static void add(InputOutput io) {
		double[] numbers = enterTwoNumbers(io);
		io.writeObjectLine(calculator.compute("+", numbers[0], numbers[1]));
	}
	private static void subtract(InputOutput io) {
		double[] numbers = enterTwoNumbers(io);
		io.writeObjectLine(calculator.compute("-", numbers[0], numbers[1]));
	}
	private static void devide(InputOutput io) {
		double[] numbers = enterTwoNumbers(io);
		io.writeObjectLine(calculator.compute("/", numbers[0], numbers[1]));
	}
	private static void multiply(InputOutput io) {
		double[] numbers = enterTwoNumbers(io);
		io.writeObjectLine(calculator.compute("*", numbers[0], numbers[1]));
	}
}
