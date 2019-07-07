package previewimage;

import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import filter.Filter;
import toplevel.Frame;
import toplevel.ImageLabel;

public class FilteredImagesPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public static BufferedImage STOCK;
	static {
		try {
			STOCK = ImageIO.read(FilteredImagesPanel.class.getResourceAsStream("/question_mark.png"));
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Error while initializing! Download again!", "ERROR", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}

	private List<ImageLabel> labels = new ArrayList<>();
	private Frame frame;

	public FilteredImagesPanel(Frame frame) {
		this.frame = frame;
		updateLabels("Question Mark?", STOCK, STOCK);
	}

	public void updateImage(String fileName, BufferedImage newImage) {
		BufferedImage resizedNewImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = resizedNewImage.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(newImage, 0, 0, resizedNewImage.getWidth(), resizedNewImage.getHeight(), null);
		g.dispose();
		updateLabels(fileName, newImage, resizedNewImage);
	}

	private void updateLabels(String fileName, BufferedImage newImage, BufferedImage previewImage) {
		removeAll();
		labels.clear();
		for (int i = 0; i < Filter.FILTERS.size(); i++) {
			ImageLabel l = new ImageLabel(fileName, newImage, previewImage, Filter.FILTERS.get(i));
			labels.add(l);
			add(l);
		}
		int grid = (int) Math.sqrt(labels.size());
		setLayout(new GridLayout(grid, grid));
		frame.revalidate();
		frame.validate();
		frame.repaint();
		frame.pack();
	}
}