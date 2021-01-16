//_____________________________________________________//
//            Yahoo Finance API Interface
//			   A simple central dashboard.
//			Programmed by: Louis Quatrocchi
//			Moral support by: Alex Schwarz 
//_____________________________________________________//
package louistocks;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class Mainstock extends Frame {
	//Asks for parameters passed onto stock interface
	public static class Terminal {
		public Terminal() {
			final String stocksurl = "yahoo.finance.com/api/calls/";
			System.out.println("Connecting to: " + stocksurl + "...");
			try {
				Stock googprice = YahooFinance.get("GOOG");
				BigDecimal price = googprice.getQuote(true).getPrice();
				System.out.println("Connected to Yahoo Finance API: Google's price today is: " + price);
				Scanner input = new Scanner(System.in);
				System.out.print("Would you like to customize your experience? (y/n):");
				String yn = input.next();
				if (yn.equals("y")) {
				}
				else {
					System.out.println("Your loss.");
					try {
						Thread.sleep(2000);
						System.out.println("Exiting...");
						Thread.sleep(2000);
						System.exit(0);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Scanner nameinput = new Scanner(System.in);
				System.out.print("Enter Your Name: ");
				String name = nameinput.nextLine();
				Scanner telemetryinput = new Scanner(System.in);
				System.out.print("Would you like to enable telemetry? (y/n): ");
				String telemetry = telemetryinput.nextLine();
				if (telemetry.equals("y")) {
					try {
						System.out.println("Connecting to facebook...");
						Thread.sleep(1000);
						System.out.println("Connected.");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Mainstock() {
		setSize(1280, 720);
		setLayout(null);
		setVisible(true);
		Button examplebuton = new Button("Buton");
		examplebuton.setBounds(400, 360, 450, 430);
		add(examplebuton);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// new Mainstock();
		new Terminal();

	}

}
