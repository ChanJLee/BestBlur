package me.chan.bestblur.v21;

import android.graphics.Canvas;

public class HardwareBlurBuffer implements BlurBuffer {
	@Override
	public Canvas lock(Canvas canvas, int width, int height) {
		return null;
	}

	@Override
	public void unlock(Canvas canvas) {

	}
}
