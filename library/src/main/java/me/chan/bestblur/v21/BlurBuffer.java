package me.chan.bestblur.v21;

import android.graphics.Canvas;

class BlurBuffer {

	public Canvas lock(Canvas canvas, int width, int height) {
		return canvas;
	}

	public void unlock(Canvas canvas) {
		/* noop */
	}
}
