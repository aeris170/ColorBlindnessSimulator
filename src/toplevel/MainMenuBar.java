package toplevel;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import filter.Filter;
import previewimage.FilteredImagesPanel;

public class MainMenuBar extends JMenuBar {

	private static final long serialVersionUID = -4960426900534480969L;

	private JMenu file = new JMenu("File");
	private JMenu extra = new JMenu("Extra");

	private JMenuItem open = new JMenuItem("Open File...");
	private JMenuItem about = new JMenuItem("About...");
	private JMenuItem exit = new JMenuItem("Exit");
	private JMenuItem addCustomFilter = new JMenuItem("Add Custom Filter");
	private JMenuItem info = new JMenuItem("Info");

	private String lastLoadedFileName = "Question Mark?";
	private BufferedImage lastLoadedFile = FilteredImagesPanel.STOCK;

	public MainMenuBar(FilteredImagesPanel fip) {
		add(file);
		file.add(open);
		file.addSeparator();
		file.add(about);
		file.addSeparator();
		file.add(exit);
		add(extra);
		extra.add(addCustomFilter);
		extra.add(info);

		open.addActionListener(e -> {
			JFileChooser fc = new JFileChooser();
			fc.removeChoosableFileFilter(fc.getFileFilter());
			fc.addChoosableFileFilter(new FileNameExtensionFilter("Image Files (*.png, *.jpg, *.jpeg)", "png", "jpg", "jpeg"));
			if (fc.showOpenDialog(MainMenuBar.this.getParent()) == JFileChooser.APPROVE_OPTION) {
				try {
					File readFile = fc.getSelectedFile();
					lastLoadedFileName = readFile.getName();
					lastLoadedFile = ImageIO.read(readFile);
					fip.updateImage(lastLoadedFileName, lastLoadedFile);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(MainMenuBar.this.getParent(), "Error while reading image!", "ERROR", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});

		about.addActionListener(e -> JOptionPane.showMessageDialog(MainMenuBar.this.getParent(), "Doða Oruç || 21602022 || Bilkent University\naeris170@gmail.com",
		        "About...", JOptionPane.PLAIN_MESSAGE));

		exit.addActionListener(e -> System.exit(0));

		addCustomFilter.addActionListener(e -> showAddCustomFilterWindow(fip));

		info.addActionListener(
		        e -> JOptionPane.showMessageDialog(MainMenuBar.this.getParent(), "Will write how these results are acquired.", "Info...", JOptionPane.PLAIN_MESSAGE));
	}

	private void showAddCustomFilterWindow(FilteredImagesPanel fip) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		window.setTitle("Add Custom Filter");
		window.setIconImage(Frame.APP_ICON);
		window.setResizable(true);
		window.setLocationRelativeTo(this);

		Box outerHolder = new Box(BoxLayout.Y_AXIS);
		Box textFieldHolder = new Box(BoxLayout.X_AXIS);
		Box buttonHolder = new Box(BoxLayout.X_AXIS);
		buttonHolder.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel filterNameLabel = new JLabel("Filter Name: ");
		JTextField filterNameInput = new JTextField(15);

		List<JTextField> userInput = new ArrayList<>();
		JPanel gridPanel = new JPanel(new GridLayout(4, 4));
		gridPanel.add(new JLabel());
		gridPanel.add(new JLabel("R", SwingConstants.CENTER));
		gridPanel.add(new JLabel("G", SwingConstants.CENTER));
		gridPanel.add(new JLabel("B", SwingConstants.CENTER));
		for (int i = 0; i < 3; i++) {
			switch (i) {
				case 0:
					gridPanel.add(new JLabel("R", SwingConstants.CENTER));
					break;
				case 1:
					gridPanel.add(new JLabel("G", SwingConstants.CENTER));
					break;
				case 2:
					gridPanel.add(new JLabel("B", SwingConstants.CENTER));
					break;
				default:
					break;
			}
			for (int j = 0; j < 3; j++) {
				userInput.add((JTextField) gridPanel.add(new JTextField()));
			}
		}

		JButton addFilterButton = new JButton("Add Filter");
		addFilterButton.addActionListener(e -> {
			Filter.FILTERS.add(new Filter(filterNameInput.getText(),
			        new float[][] { { parseJTextFieldText(userInput.get(0)), parseJTextFieldText(userInput.get(1)), parseJTextFieldText(userInput.get(2)) },
			                { parseJTextFieldText(userInput.get(3)), parseJTextFieldText(userInput.get(4)), parseJTextFieldText(userInput.get(5)) },
			                { parseJTextFieldText(userInput.get(6)), parseJTextFieldText(userInput.get(7)), parseJTextFieldText(userInput.get(8)) } }));
			fip.updateImage(lastLoadedFileName, lastLoadedFile);
		});

		textFieldHolder.add(filterNameLabel);
		textFieldHolder.add(filterNameInput);
		buttonHolder.add(addFilterButton);
		outerHolder.add(textFieldHolder);
		outerHolder.add(gridPanel);
		outerHolder.add(buttonHolder);
		outerHolder.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		window.add(outerHolder);
		window.pack();
		window.setVisible(true);
	}

	private static float parseJTextFieldText(JTextField jtf) {
		return Math.min(Math.max(Float.parseFloat(jtf.getText()), 0), 1);
	}
}