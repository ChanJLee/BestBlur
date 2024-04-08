package me.chan.bestblur;

import android.graphics.Canvas;
import android.os.Build;

class RenderBufferImpl implements RenderBuffer {

	private final RenderBuffer mProxy;

	public RenderBufferImpl() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
			mProxy = new RenderBufferV31();
		} else {
			mProxy = new RenderBufferV21();
		}
	}

	@Override
	public Canvas lock(int width, int height, boolean hardwareAccelerateEnable) {
		return mProxy.lock(width, height, hardwareAccelerateEnable);
	}

	@Override
	public void unlock(Canvas canvas) {
		mProxy.unlock(canvas);
	}

	@Override
	public void release() {
		mProxy.release();
	}
}
