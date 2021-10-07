/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;

import java.util.Scanner;

/**
 *
 * @author Kaine
 */
public class Menu {
	//Content & Size	
	private String[] choices;
	private int length;
	// Init menu
	private int n = 0;
	private static Scanner sc = new Scanner(System.in);

	public Menu(int size) {
		if (size < 1) {
			size = 10;
		}
		this.length = size;
		choices = new String[size];
	}

	public void add(String choice) {
		if (n < choices.length) {
			choices[n++] = choice;
		}
	}	

	public int getChoice() {
		int choice = 0;
		if (n > 0) {
			for (int i = 0; i < n; i++) {
				System.out.println((i+1) + ". " + choices[i]);
			}
			choice = Valdation.getInterger("Please select your option from " + 1 + " to " + n +": ", "Invalid", 1, n);
		}
		return choice;
	}

}
