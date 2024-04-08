package me.chan.bestblur;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RenderEffect;
import android.os.Build;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.S)
public class RenderBufferV31 extends BaseRenderBuffer {

	private RenderEffect mRenderEffect;

	@Override
	protected Canvas onLock(Bitmap bitmap, int width, int height, boolean hardwareAccelerateEnable) {
		return null;
	}

	@Override
	protected void onRelease() {
		super.onRelease();
	}

	@Override
	protected void onBitmapCreated(Bitmap bitmap, int width, int height) {
		mRenderEffect = RenderEffect.createBitmapEffect(bitmap);
	}
}
