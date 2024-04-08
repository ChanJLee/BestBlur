package me.chan.bestblur;

import android.graphics.Canvas;

public class BlurBufferWrapper implements BlurBuffer{

	@Override
	public Canvas lock(Canvas canvas, int width, int height) {
		return null;
	}

	@Override
	public void unlock(Canvas canvas) {

	}

	@Override
	public void release() {

	}
}
