package net.kerupani129.herocat.util;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class SystemInfo {

	// 変数
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();

    //
    // スクリーンサイズを取得
    //
	public static Dimension getScreenSize() {
		return toolkit.getScreenSize();
	}

	//
	// ワークエリアを取得
	//
	public static Rectangle getMaximumWindowBounds() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
	}

    //
    // スクリーンを取得 (マルチディスプレイ用)
    //
	public static Rectangle[] getScreenBoundsArray() {

		GraphicsDevice[] gds = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
		Rectangle[] rects = new Rectangle[gds.length];

		for (int i = 0; i < gds.length; i++) {
			GraphicsConfiguration gc = gds[i].getDefaultConfiguration();
			rects[i] = gc.getBounds();
		}

		return rects;

	}

	//
	// ワークエリアを取得 (マルチディスプレイ用)
	//
	public static Rectangle[] getMaximumWindowBoundsArray() {

		GraphicsDevice[] gds = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
		Rectangle[] rects = new Rectangle[gds.length];

		for (int i = 0; i < gds.length; i++) {
			GraphicsConfiguration gc = gds[i].getDefaultConfiguration();

			Rectangle bounds = gc.getBounds();
			Insets insets = toolkit.getScreenInsets(gc);

			bounds.x += insets.left;
			bounds.y += insets.top;
			bounds.width -= insets.left + insets.right;
			bounds.height -= insets.top + insets.bottom;

			rects[i] = bounds;
		}

		return rects;

	}

}
