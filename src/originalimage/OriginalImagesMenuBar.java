package originalimage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OriginalImagesMenuBar extends JMenuBar {

	private static final long serialVersionUID = 593789039799880511L;

	private JMenuItem save = new JMenuItem("Save");

	public OriginalImagesMenuBar(BufferedImage imageToSave, String defaultFileName) {
		add(save);
		save.addActionListener(e -> {
			JFileChooser fc = new JFileChooser();
			fc.removeChoosableFileFilter(fc.getFileFilter());
			fc.addChoosableFileFilter(new FileNameExtensionFilter("Portable Network Graphics (*.png)", "png"));
			fc.setSelectedFile(new File(defaultFileName + ".png"));
			if (fc.showSaveDialog(OriginalImagesMenuBar.this.getParent()) == JFileChooser.APPROVE_OPTION) {
				fc.setSelectedFile(fc.getSelectedFile().toString().endsWith(".png") ? fc.getSelectedFile() : new File(fc.getSelectedFile().toString() + ".png"));
				try {
					ImageIO.write(imageToSave, "png", fc.getSelectedFile());
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(this, "Error while saving image!", "ERROR", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});
	}

}
