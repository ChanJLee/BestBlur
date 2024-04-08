package me.chan.bestblur;

import android.graphics.Canvas;

public interface RenderBuffer {

	Canvas lock(int width, int height, boolean hardwareAccelerateEnable);

	void unlock(Canvas canvas);

	void release();
}
