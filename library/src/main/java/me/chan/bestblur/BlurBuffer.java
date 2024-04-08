package me.chan.bestblur;

import android.graphics.Canvas;

interface BlurBuffer {

	Canvas lock(Canvas canvas, int width, int height);

	void unlock(Canvas canvas);

	void release();
}
