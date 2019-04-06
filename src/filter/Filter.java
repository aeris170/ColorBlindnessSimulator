package filter;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Filter {

	public static final List<Filter> FILTERS = new ArrayList<>();

	static {
		FILTERS.add(new Filter("Normal Vision", new float[][] { { 1f, 0f, 0f }, { 0f, 1f, 0f }, { 0f, 0f, 1f } }));
		FILTERS.add(new Filter("Protanopia", new float[][] { { 0.567f, 0.433f, 0f }, { 0.558f, 0.442f, 0f }, { 0f, 0.242f, 0.758f } }));
		FILTERS.add(new Filter("Protanomaly", new float[][] { { 0.817f, 0.183f, 0f }, { 0.333f, 0.667f, 0f }, { 0f, 0.125f, 0.875f } }));
		FILTERS.add(new Filter("Deuteranopia", new float[][] { { 0.625f, 0.375f, 0f }, { 0.7f, 0.3f, 0f }, { 0f, 0.3f, 0.7f } }));
		FILTERS.add(new Filter("Deuteranomaly", new float[][] { { 0.8f, 0.2f, 0f }, { 0.258f, 0.742f, 0f }, { 0f, 0.142f, 0.858f } }));
		FILTERS.add(new Filter("Tritanopia", new float[][] { { 0.95f, 0.05f, 0f }, { 0f, 0.433f, 0.567f }, { 0f, 0.475f, 0.525f } }));
		FILTERS.add(new Filter("Tritanomaly", new float[][] { { 0.967f, 0.033f, 0f }, { 0f, 0.733f, 0.267f }, { 0f, 0.183f, 0.817f } }));
		FILTERS.add(new Filter("Achromatopsia", new float[][] { { 0.299f, 0.587f, 0.114f }, { 0.299f, 0.587f, 0.114f }, { 0.299f, 0.587f, 0.114f } }));
		FILTERS.add(new Filter("Achromatomaly", new float[][] { { 0.618f, 0.320f, 0.062f }, { 0.163f, 0.775f, 0.062f }, { 0.163f, 0.320f, 0.516f } }));
	}

	private String name;
	private float[][] imageFilter;

	public Filter(String name, float[][] filter) {
		this.name = name;
		this.imageFilter = filter;
	}

	public String getName() {
		return name;
	}

	public static BufferedImage filterImage(BufferedImage source, Filter f) {
		BufferedImage filteredImage = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int xx = 0; xx < source.getWidth(); xx++) {
			for (int yy = 0; yy < source.getHeight(); yy++) {
				Color pixelColor = new Color(source.getRGB(xx, yy));
				int red = pixelColor.getRed();
				int green = pixelColor.getGreen();
				int blue = pixelColor.getBlue();
				int[] newRGB = mult(new float[] { red, green, blue }, f.imageFilter);
				Color newPixelColor = new Color(newRGB[0], newRGB[1], newRGB[2]);
				filteredImage.setRGB(xx, yy, newPixelColor.getRGB());
			}
		}
		return filteredImage;
	}

	private static int[] mult(float[] vector, float[][] matrix) {
		int firstElement = (int) (matrix[0][0] * vector[0] + matrix[0][1] * vector[1] + matrix[0][2] * vector[2]);
		int secondElement = (int) (matrix[1][0] * vector[0] + matrix[1][1] * vector[1] + matrix[1][2] * vector[2]);
		int thirdElement = (int) (matrix[2][0] * vector[0] + matrix[2][1] * vector[1] + matrix[2][2] * vector[2]);
		return new int[] { firstElement, secondElement, thirdElement };
	}
}