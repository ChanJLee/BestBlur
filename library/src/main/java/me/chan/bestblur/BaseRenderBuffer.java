package me.chan.bestblur;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import androidx.annotation.CallSuper;

abstract class BaseRenderBuffer implements RenderBuffer {

	private Bitmap mBitmap;

	protected final BestBlur.Builder mBuilder;

	public BaseRenderBuffer(BestBlur.Builder builder) {
		mBuilder = builder;
	}

	@Override
	public final Canvas lock(int width, int height, boolean hardwareAccelerateEnable) {
		if (mBitmap == null || mBitmap.getHeight() != height || mBitmap.getWidth() != width) {
			if (mBitmap != null) {
				release();
			}
			mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
			onBitmapCreated(mBitmap, width, height);
		}

		return onLock(mBitmap, width, height, hardwareAccelerateEnable);
	}

	protected abstract Canvas onLock(Bitmap bitmap, int width, int height, boolean hardwareAccelerateEnable);

	@Override
	public final void unlock(Canvas canvas) {
		canvas.drawBitmap(mBitmap, 0, 0, null);
	}

	@Override
	public final void release() {
		onRelease();
		mBitmap.recycle();
		mBitmap = null;
	}

	@CallSuper
	protected void onRelease() {}

	protected abstract void onBitmapCreated(Bitmap bitmap, int width, int height);
}
