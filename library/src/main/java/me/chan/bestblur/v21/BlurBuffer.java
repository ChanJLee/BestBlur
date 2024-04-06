package me.chan.bestblur.v21;

import android.graphics.Canvas;

interface BlurBuffer {

	Canvas lock(Canvas canvas, int width, int height);

	void unlock(Canvas canvas);
}
