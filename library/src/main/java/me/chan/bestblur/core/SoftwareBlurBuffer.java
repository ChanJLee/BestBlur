package me.chan.bestblur.core;

import android.graphics.Canvas;

import me.chan.bestblur.BlurBuffer;

public class SoftwareBlurBuffer implements BlurBuffer {

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