package toplevel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import filter.Filter;
import originalimage.OriginalImageView;

public class ImageLabel extends Box {

	private static final long serialVersionUID = 1L;

	private static final int BORDER_THICKNESS = 2;

	OriginalImageView originalImageView;

	public ImageLabel(String fileName, BufferedImage image, BufferedImage previewImage, Filter filter) {
		super(BoxLayout.Y_AXIS);
		originalImageView = new OriginalImageView(fileName, image, filter);
		originalImageView.setLocationRelativeTo(this);
		JLabel imageLabel = new JLabel(new ImageIcon(Filter.filterImage(previewImage, filter)));
		JLabel textLabel = new JLabel("<html><div style='text-align: center;'>" + filter.getName() + "</div></html>");
		textLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(imageLabel);
		add(textLabel);
		// setBorder(BorderFactory.createCompoundBorder(BorderFactory.create,BorderFactory.createEmptyBorder(BORDER_THICKNESS,
		// BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS))); // I KILLED SIRIUS
		// BLACK
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS),
		        BorderFactory.createCompoundBorder(raisedbevel, loweredbevel)));
		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS),
				        BorderFactory.createCompoundBorder(loweredbevel, raisedbevel)));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS),
				        BorderFactory.createCompoundBorder(raisedbevel, loweredbevel)));
				originalImageView.setVisible(true);
			}
		});
	}
}