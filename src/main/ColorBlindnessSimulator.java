package main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import toplevel.Frame;

public class ColorBlindnessSimulator {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> configureGUI());
	}

	private static void configureGUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
		new Frame();
	}
}
