package telran.numbers.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

import telran.numbers.controller.CalculatorActions;
import telran.numbers.service.Calculator;
import telran.numbers.service.CalculatorProxy;
import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class CalculatorClientApp {
	private static final int PORT = 3000;
	static PrintStream writer;
	static BufferedReader reader;
	public static void main(String[] args) throws Exception {
		InputOutput io = new ConsoleInputOutput();
		Socket socket = new Socket("localhost", PORT); //establishing connection 
		writer = new PrintStream(socket.getOutputStream());
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		Calculator calculator = new CalculatorProxy(writer,reader);
		ArrayList<Item> items = CalculatorActions.getCalculatorItems(calculator);
		items.add(Item.of("Exit", iop->{
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}, true));
		
		Menu menu = new Menu("Calculator", items);
		menu.perform(io);
	}
}
