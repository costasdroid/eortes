package com.example.eortes;

import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;

/*
 * A class that listens swipes and coding the movement
 */
public final class SwipeListener extends SimpleOnGestureListener {

	private int direction;

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {

		return onDirection(e1.getX(), e2.getX(), e1.getY(), e2.getY(),
				velocityX, velocityY);
	}

	/*
	 * We define movement by moving the finger at least SENSITIVITY. The
	 * direction int are the 4 points of a compass
	 */
	private boolean onDirection(double x1, double x2, double y1, double y2,
			float vx, float vy) {

		final double SENSITIVITY = 50;
		final double PI = 3.14159;

		double ypoteinousa = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1)
				* (x2 - x1));

		if (ypoteinousa > SENSITIVITY) {

			double theta = ((x2 != x1) ? Math.atan((y2 - y1) / (x2 - x1))
					: PI / 2);

			if (Math.abs(theta) <= PI / 4)
				direction = (vx > 0) ? 1 : 3;
			else
				direction = (vy > 0) ? 2 : 0;

			return true;
		}
		return false;
	}

	public int getDirection() {
		return direction;
	}
}
