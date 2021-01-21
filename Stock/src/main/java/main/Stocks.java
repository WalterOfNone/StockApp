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

	@SuppressWarnings({ "resource", "unused" })
	public Stocks() throws InterruptedException, IOException {
		final String ANSI_RED = "";
		final String ANSI_GREEN = "";
		System.out.println("   _   _   _   _   _   _   _   _   _  ");
		System.out.println("  / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ ");
		System.out.println(" ( S | t | o | c | k | - | C | L | I )");
		System.out.println("  \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ ");
		System.out.println("Powered by Yahoo.");
		System.out.println(ANSI_RED + "");
		Thread.sleep(1000);
		System.out.println("Type 'help' for info on using this program.");
		Thread.sleep(1000);
		System.out.println(ANSI_GREEN + "");
		System.out.println("Type 'exit' anytime to quit.");
		boolean run = true;
		int e = 0;
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
				System.out.println("'-e' Gets Earnings Information");
				System.out.println("'-d' Gets Adjusted Dividends");
				System.out.println("'-n' Gets Company's Name");
				System.out.println("'-v' Gets Stock Validity");
				System.out.println("'-err' Gets Error Amount");
				System.out.println("'-c' Gets Change From  Market Open");
				System.out.println("'-mc' Gets Market Capitalization");
				System.out.println("'-eps' Gets Earnings Per Share");
				System.out.println("'-open' Gets Opening Price");
				System.out.println("'-yh' Gets Year High Price");
				System.out.println("'-yl' Gets Year Low Price");
				System.out.println("");
			}
			else {
				// splits up input into separate strings to be parsed
				
				String[] splitStr = input.split("\\s+");
				Stock stock = YahooFinance.get(splitStr[0]);
				if (stock != null) {
					// returns current price
					if (splitStr.length > 1) {
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
						case "-e":
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
						case "-err":
							System.out.println("Errors: "+e);
							break;
						case "-c":
							BigDecimal change = stock.getQuote().getChangeInPercent();
							System.out.println("");
							System.out.println(splitStr[0]+"'s change from market open: " +change+"%");
							break;
						case "-mc":
							BigDecimal marketcap = stock.getStats().getMarketCap();
							System.out.println("");
							System.out.println(splitStr[0]+"'s market cap: $"+marketcap);
							break;
						case "-eps":
							BigDecimal eps = stock.getStats().getEps();
							System.out.println("");
							System.out.println(splitStr[0]+"'s earnings per share: $"+eps);
							break;
						case "-open":
							BigDecimal open = stock.getQuote().getOpen();
							System.out.println("");
							System.out.println(splitStr[0]+"'s opening price: $"+open);
							break;
						case "-yh":
							BigDecimal yh = stock.getQuote().getYearHigh();
							System.out.println("");
							System.out.println(splitStr[0]+"'s year high: $"+yh);
							break;
						case "-yl":
							BigDecimal yl = stock.getQuote().getYearLow();
							System.out.println("");
							System.out.println(splitStr[0]+"'s year low: $"+yl);
							break;
						
						default:
							System.out.println("Error: Invalid Action");
							e++;

						}
					}
					else {
						System.out.println("Error: action null");
						e++;
					}
				}
				if (stock == null) {
					System.out.println("Invalid Action");
					e++;
				}

			}
		}
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		new Stocks();
	}
}