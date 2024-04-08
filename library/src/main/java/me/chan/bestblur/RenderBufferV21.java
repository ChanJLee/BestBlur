package me.chan.bestblur;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RSRuntimeException;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

public class RenderBufferV21 extends BaseRenderBuffer {
	private RenderScript mRenderScript;
	private Allocation mInputAllocation;
	private Allocation mOutputAllocation;
	private ScriptIntrinsicBlur mBlur;

	private final Context mContext;

	public RenderBufferV21(Context context, BestBlur.Builder builder) {
		super(builder);
		mContext = context;
	}

	@Override
	protected Canvas onLock(Bitmap bitmap, int width, int height, boolean hardwareAccelerateEnable) {
		return null;
	}

	@Override
	protected void onBitmapCreated(Bitmap bitmap, int width, int height) {
//		mRenderScript = RenderScript.create(mContext);
//		mRenderScript.setMessageHandler(new RenderScript.RSMessageHandler());
//		mInputAllocation = Allocation.createFromBitmap(mRenderScript, bitmap, Allocation.MipmapControl.MIPMAP_NONE,
//				Allocation.USAGE_SCRIPT);
//		mOutputAllocation = Allocation.createTyped(mRenderScript, mInputAllocation.getType());
//		mBlur = ScriptIntrinsicBlur.create(mRenderScript, Element.U8_4(mRenderScript));
	}

	@Override
	protected void onRelease() {
		if (mBlur != null) {
			mBlur.destroy();
			mBlur = null;
		}

		if (mOutputAllocation != null) {
			mOutputAllocation.destroy();
			mOutputAllocation = null;
		}

		if (mInputAllocation != null) {
			mInputAllocation.destroy();
			mInputAllocation = null;
		}

		if (mRenderScript != null) {
			mRenderScript.destroy();
			mRenderScript = null;
		}
		super.onRelease();
	}

	private static Bitmap rs(Context context, Bitmap bitmap, int radius) throws RSRuntimeException {
		RenderScript rs = null;
		Allocation input = null;
		Allocation output = null;
		ScriptIntrinsicBlur blur = null;
		try {
			rs = RenderScript.create(context);
			rs.setMessageHandler(new RenderScript.RSMessageHandler());
			input = Allocation.createFromBitmap(rs, bitmap, Allocation.MipmapControl.MIPMAP_NONE,
					Allocation.USAGE_SCRIPT);
			output = Allocation.createTyped(rs, input.getType());
			blur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

			blur.setInput(input);
			blur.setRadius(radius);
			blur.forEach(output);
			output.copyTo(bitmap);
		} finally {
			if (rs != null) {
				rs.destroy();
			}
			if (input != null) {
				input.destroy();
			}
			if (output != null) {
				output.destroy();
			}
			if (blur != null) {
				blur.destroy();
			}
		}

		return bitmap;
	}
}
