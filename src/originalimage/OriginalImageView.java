package originalimage;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import filter.Filter;
import toplevel.Frame;

public class OriginalImageView extends JFrame {

	private static final long serialVersionUID = 4229096366875630283L;

	public OriginalImageView(String fileName, BufferedImage image, Filter filter) {
		BufferedImage filteredImage = Filter.filterImage(image, filter);
		setJMenuBar(new OriginalImagesMenuBar(filteredImage,
		        fileName.substring(0, fileName.lastIndexOf('.') == -1 ? fileName.length() : fileName.lastIndexOf('.')) + "_" + filter.getName()));
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setTitle(fileName + " - " + filter.getName() + " - Original Size");
		add(new JScrollPane(new JLabel(new ImageIcon(filteredImage))));
		setIconImage(Frame.APP_ICON);
		setResizable(true);
		pack();
		int width = getWidth();
		int height = getHeight();
		DisplayMode dm = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
		int screenWidth = dm.getWidth();
		int screenHeight = dm.getHeight();
		if (width > screenWidth * 0.9f) {
			width = (int) (screenWidth * 0.9f);
		}
		if (height > screenHeight * 0.9f) {
			height = (int) (screenHeight * 0.9f);
		}
		setSize(width, height);
	}

}