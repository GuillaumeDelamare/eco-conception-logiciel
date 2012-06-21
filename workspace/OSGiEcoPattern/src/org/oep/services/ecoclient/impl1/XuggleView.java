package org.oep.services.ecoclient.impl1;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;


public class XuggleView extends JComponent {
	private static final long serialVersionUID = 5584422798735147930L;
	private Image mImage;
	private Dimension mSize;

	public XuggleView() {
		mSize = new Dimension(0, 0);
		setSize(mSize);
	}

	public void setImage(Image image) {
		mImage = image;
		Dimension newSize = new Dimension(mImage.getWidth(null), mImage.getHeight(null));

		if (!newSize.equals(mSize)){
			mSize = newSize;
			setSize(mImage.getWidth(null), mImage.getHeight(null));
			setVisible(true);
		}
		repaint();
	}

	public synchronized void paint(Graphics g) {
		if (mImage != null)
			g.drawImage(mImage, 0, 0, this);
	}
}