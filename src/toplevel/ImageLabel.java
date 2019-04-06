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
import javax.swing.SwingUtilities;

import filter.Filter;
import originalimage.OriginalImageView;

public class ImageLabel extends Box {

	private static final long serialVersionUID = 7914101552449066251L;

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
		setBorder(BorderFactory.createEmptyBorder(BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS));
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				SwingUtilities.invokeLater(() -> originalImageView.setVisible(true));
			}
		});
	}
}