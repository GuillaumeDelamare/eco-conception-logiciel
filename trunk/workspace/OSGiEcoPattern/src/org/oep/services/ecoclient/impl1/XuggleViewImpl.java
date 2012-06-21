package org.oep.services.ecoclient.impl1;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;

import org.oep.services.ecoclient.api.xuggle.XuggleView;


public class XuggleViewImpl extends JComponent implements XuggleView {
	private static final long serialVersionUID = 5584422798735147930L;
	private Image mImage;
	private Dimension mSize;

	public XuggleViewImpl() {
		mSize = new Dimension(0, 0);
		setSize(mSize);
	}

	@Override
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