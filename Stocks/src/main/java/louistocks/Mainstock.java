//_____________________________________________________//
//            Yahoo Finance API Interface
//			   A simple central dashboard.
//			Programmed by: Louis Quatrocchi
//			Moral support by: Alex Schwarz 
//_____________________________________________________//
package louistocks;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

//Do note this program is WIP and consists of spaghetti code
public class Mainstock extends Frame {
	// Asks for parameters passed onto stock interface
	public static class Terminal {
		public Terminal() {
			final String stocksurl = "yahoo.finance.com/api/calls/";
			System.out.println("Connecting to: " + stocksurl + "...");
			try {
				Stock googprice = YahooFinance.get("GOOG");
				BigDecimal price = googprice.getQuote(true).getPrice();
				System.out.println("Connected to Yahoo Finance API: Google's price today is: " + price);
				Scanner input = new Scanner(System.in);
				System.out.print("Would you like to customize your experience? (y/n): ");
				String yn = input.next();
				if (yn.equals("y")) {
				} else {
					System.out.println("Your loss.");
					try {
						Thread.sleep(1000);
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
				Scanner systeminput = new Scanner(System.in);
				System.out.print("Would you like to run a compatibility check? (y/n): ");
				String system = systeminput.nextLine();
				if (system.equals("y")) {
					String os = System.getProperty("os.name");
					System.out.println("Youy are running " + os + ", A compatible system");
				}
				System.out.println("Launching...");
				try {
					Thread.sleep(400);
					new Mainstock(name);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

	public Mainstock(String name) {
		final Frame s = new Frame(name + "'s Personalized Dashboard");

		final TextField tf = new TextField();
		tf.setBounds(1100, 100, 60, 20);
		Button b = new Button("Add");
		b.setBounds(1180, 100, 40, 20);
		int counter = 0;
		int y = 100 + counter * 50;
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String data = tf.getText();
				Button btn = new Button(data);
				btn.setBounds(1100,100,60,20);
				s.add(btn);
				s.setVisible(true);
			}
		});

		// adds and creates everything to window and sets visible
		Color clr1 = new Color(27, 36, 42);
		s.add(b);
		s.add(tf);
		s.setSize(1280, 720);
		s.setLayout(null);
		s.setBackground(clr1);
		s.setResizable(false);
		s.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		s.setVisible(true);
		// graph

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// new Mainstock();
		new Terminal();

	}

}
