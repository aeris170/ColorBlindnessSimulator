package toplevel;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import previewimage.FilteredImagesPanel;

public class Frame extends JFrame {

	private static final long serialVersionUID = 3580029473316955128L;

	public static Image APP_ICON;
	static {
		try {
			APP_ICON = ImageIO.read(Frame.class.getResourceAsStream("/icon.png"));
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Error while initializing! Download again!", "ERROR", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}

	private FilteredImagesPanel fip;
	private MainMenuBar cmb;

	public Frame() {
		fip = new FilteredImagesPanel(this);
		cmb = new MainMenuBar(fip);
		setTitle("Color Blindness Simulator v0.2");
		getContentPane().add(fip);
		setJMenuBar(cmb);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setIconImage(APP_ICON);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
