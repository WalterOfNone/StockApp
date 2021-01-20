//__________________________________________//
//             Stock-API
//	  Programmed by: Louis Quattrocchi
//     Moral Support by: Alex Schwarz
//  A simple CLI interface for Yahoo Finance
//__________________________________________//
package main;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockDividend;
import yahoofinance.quotes.stock.StockStats;

public class Stocks {

	public Stocks() throws InterruptedException, IOException {
		final String ANSI_RED = "\u001B[31m";
		final String ANSI_GREEN = "\u001B[32m";
		System.out.println("   _   _   _   _   _   _   _   _   _  ");
		System.out.println("  / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ ");
		System.out.println(" ( S | t | o | c | k | - | C | L | I )");
		System.out.println("  \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ ");
		System.out.println("Powered by Yahoo.");
		System.out.println("");
		Thread.sleep(1000);
		System.out.println("Type 'help' for info on using this program.");
		Thread.sleep(1000);
		System.out.println("");
		System.out.println("Type 'exit' anytime to quit.");
		boolean run = true;
		while (run = true) {
			System.out.print(":");
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();
			if (input.equals("exit")) {
				Thread.sleep(100);
				System.out.println("Exiting...");
				Thread.sleep(200);
				System.exit(0);
			}
			if (input.equals("help")) {
				System.out.println("");
				System.out.println("Syntax used is [TICKER] + [ACTION]");
				System.out.println("Actions: ");
				System.out.println("'-p' Gets Current Price");
				System.out.println("'-s' Gets Earnings Information");
				System.out.println("'-d' Gets Adjusted Dividends");
				System.out.println("'-n' Gets Company's Name");
				System.out.println("'-v' Gets Stock Validity");
				System.out.println("");
			}

			else {
				// splits up input into separate strings to be parsed
				String[] splitStr = input.split("\\s+");
				Stock stock = YahooFinance.get(splitStr[0]);
				System.out.println("error1");
				if (stock != null) {
					// returns current price
					System.out.println("error2");
					if (splitStr[1] != null) {
						switch (splitStr[1]) {
						case "-p":
							BigDecimal price = stock.getQuote(true).getPrice();
							System.out.println("");
							System.out.println(splitStr[0] + "'s price is currently $" + price);
							break;
						// returns dividends if applicable
						case "-d":
							StockDividend dividend = stock.getDividend(true);
							String divStr = dividend.toString();
							String[] splitStock = divStr.split("\\s+");
							if (splitStock[2].equals("/,")) {
								System.out.println("This stock does not have dividends.");
							} else {
								System.out.println("");
								System.out.println(splitStr[0] + "'s dividend info:  " + dividend);
							}
							break;
						// returns earning info
						case "-s":
							StockStats ei = stock.getStats(true);
							System.out.println("");
							System.out.println(splitStr[0] + "'s earnings info: " + ei);
							break;
						// returns ticker name
						case "-n":
							String cname = stock.getName();
							System.out.println("");
							System.out.println(splitStr[0] + "'s name: " + cname);
							break;
						case "-v":
							if (stock != null) {
								boolean valid = stock.isValid();
								System.out.println("");
								System.out.println(splitStr[0] + ": " + valid);
								break;
							}
						default:
							System.out.println("Error: Invalid Ticker");

						}
					}
					else {
						System.out.println("Error: action null");
					}
				}
				if (stock == null) {
					System.out.println("Invalid Action");
				}

			}
		}
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		new Stocks();
	}
}