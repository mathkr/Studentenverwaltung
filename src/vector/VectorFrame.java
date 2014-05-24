package vector;

import java.awt.*;
import java.awt.event.*;
import util.*;

public class VectorFrame extends Frame implements Observer {
	private PrintVector<?> vector;
	private VectorManager vectorManager;
	private static final int SIDE_LENGTH = 10;
	private static final Color EMPTY = Color.BLUE;
	private static final Color FULL = Color.RED;

	VectorFrame(PrintVector<?> vector, VectorManager vm) {
		super("Vector visualization");
		setBounds(0,0,400,400);

		this.vectorManager = vm;

		this.vector = vector;
		vector.addObserver(this);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				repaint();
			}
		});

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();
				System.out.println(key);
				switch(key) {
					case 'b':
						vectorManager.push_back();
						break;
					case 'f':
						vectorManager.push_front();
						break;
					case 'B':
						vectorManager.double_back();
						break;
					case 'F':
						vectorManager.double_front();
						break;
				}
			}

		});

		setVisible(true);
	}

	@Override
	public void notifyObserver() {
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Object[] array = vector.getArray();
		Insets insets = getInsets();

		int cols = (getWidth() - insets.left - insets.right) /
		    SIDE_LENGTH;

		for (int i = 0; i < array.length; ++i) {
			int x = (i % cols) * SIDE_LENGTH;
			int y = (i / cols) * SIDE_LENGTH;

			g.setColor(array[i] != null ? FULL : EMPTY);
			g.fillRect(x, y, SIDE_LENGTH, SIDE_LENGTH);
		}
	}
}
