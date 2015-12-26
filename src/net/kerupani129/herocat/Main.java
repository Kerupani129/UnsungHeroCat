package net.kerupani129.herocat;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

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

		/* リスナー登録 */
		MouseListenerMoveWindow listener = new MouseListenerMoveWindow();
		frame.addMouseListener(listener);
		frame.addMouseMotionListener(listener);

		/* デザイン */
		frame.setTitle("Unsung Hero Cat");
		frame.getContentPane().setPreferredSize(new Dimension(640, 480));
		frame.pack();

		/* 描画 */
		frame.setVisible(true);


		// test
		/*
		for (Rectangle bounds : SystemInfo.getMaximumWindowBoundsArray()) {

			System.out.println(bounds);

			JFrame f = new JFrame();

			f.setBounds(bounds);
			f.setUndecorated(true);
			f.setVisible(true);

		}
		*/


	}

	//
	// MouseListener : ウィンドウを移動する
	//
	private class MouseListenerMoveWindow extends MouseAdapter {

		// 変数
		private int dx;
		private int dy;

		// ドラッグ時
		public void mouseDragged(MouseEvent e) {
			int x = e.getXOnScreen() - dx;
			int y = e.getYOnScreen() - dy;
			e.getComponent().setLocation(x, y);
		}

		// 押し下げ時
		public void mousePressed(MouseEvent e) {
			dx = e.getXOnScreen() - e.getComponent().getX();
			dy = e.getYOnScreen() - e.getComponent().getY();
		}

	}

}
