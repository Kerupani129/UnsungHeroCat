package net.kerupani129.herocat;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;

import net.kerupani129.herocat.util.SystemInfo;

public class Main {

	//
	// メインメソッド
	//
	public static void main(String[] args) {
		new Main();
	}

	//
	// コンストラクタ
	//
	private Main() {


		// メインウィンドウ
		/* 生成 */
		JFrame frame = new JFrame();

		/* 基本 */
		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* デザイン */
		frame.setTitle("Unsung Hero Cat");
		frame.getContentPane().setPreferredSize(new Dimension(640, 480));
		frame.pack();

		/* 描画 */
		frame.setVisible(true);


		// test
		MouseListenerMoveComponent listener = new MouseListenerMoveComponent();
		for (Rectangle bounds : SystemInfo.getMaximumWindowBoundsArray()) {

			/* 生成 */
			JDialog dialog = new JDialog();

			/* リスナー登録 */
			dialog.addMouseListener(listener);
			dialog.addMouseMotionListener(listener);

			/* デザイン */
			dialog.setUndecorated(true);
			dialog.setBounds(bounds.x, bounds.y, 240, 240);

			/* 描画 */
			dialog.setVisible(true);

		}


	}

	//
	// MouseListener : コンポーネントを移動する
	//
	private class MouseListenerMoveComponent extends MouseAdapter {

		// 定数
		private static final int margin = 40;

		// 変数
		private int dx;
		private int dy;

		private Rectangle[] xBounds;
		private Rectangle[] yBounds;

		// ドラッグ時
		@Override
		public void mouseDragged(MouseEvent e) {

			// 基本位置取得
			int x = e.getXOnScreen() - dx;
			int y = e.getYOnScreen() - dy;

			// フィットさせる
			for (Rectangle bounds : xBounds) {
				if (bounds.contains(x, y)) x = (int)bounds.getCenterX();
			}

			for (Rectangle bounds : yBounds) {
				if (bounds.contains(x, y)) y = (int)bounds.getCenterY();
			}

			// 位置決定
			e.getComponent().setLocation(x, y);

		}

		// 押し下げ時
		@Override
		public void mousePressed(MouseEvent e) {

			// マウス押し下げ相対位置取得
			dx = e.getXOnScreen() - e.getComponent().getX();
			dy = e.getYOnScreen() - e.getComponent().getY();

			// フィットさせる領域取得
			Rectangle[] boundsArray = SystemInfo.getMaximumWindowBoundsArray();

			xBounds = new Rectangle[boundsArray.length * 2];
			yBounds = new Rectangle[boundsArray.length * 2];

			for (int i = 0; i < boundsArray.length; i++) {

				Rectangle bounds = boundsArray[i];

				bounds.width -= e.getComponent().getWidth();
				bounds.height -= e.getComponent().getHeight();

				xBounds[i*2  ] = new Rectangle((int)bounds.getMinX(), (int)bounds.getMinY(), 0, bounds.height);
				yBounds[i*2  ] = new Rectangle((int)bounds.getMinX(), (int)bounds.getMinY(), bounds.width, 0);
				xBounds[i*2+1] = new Rectangle((int)bounds.getMaxX(), (int)bounds.getMinY(), 0, bounds.height);
				yBounds[i*2+1] = new Rectangle((int)bounds.getMinX(), (int)bounds.getMaxY(), bounds.width, 0);

				xBounds[i*2  ].grow(margin, margin);
				yBounds[i*2  ].grow(margin, margin);
				xBounds[i*2+1].grow(margin, margin);
				yBounds[i*2+1].grow(margin, margin);

			}

		}

	}

}
