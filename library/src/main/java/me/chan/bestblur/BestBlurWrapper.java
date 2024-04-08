package me.chan.bestblur;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

class BestBlurWrapper extends FrameLayout {

	private boolean mEnableBlurEffect = false;
	private final RenderBuffer mBuffer = new RenderBufferImpl();


	public BestBlurWrapper(@NonNull Context context) {
		super(context);
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		if (!mEnableBlurEffect) {
			super.dispatchDraw(canvas);
			return;
		}

		Canvas copy = mBuffer.lock(getWidth(), getHeight(), canvas.isHardwareAccelerated());
		super.dispatchDraw(copy);
		mBuffer.unlock(canvas);
	}

	void renderBlur(boolean enable) {
		mEnableBlurEffect = enable;
		invalidate();
	}

	@Override
	protected void onDetachedFromWindow() {
		mBuffer.release();
		super.onDetachedFromWindow();
	}
}
