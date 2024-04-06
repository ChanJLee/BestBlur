package me.chan.bestblur.v21;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

class BestBlurWrapper extends FrameLayout {

	private boolean mShowBlur = false;
	private final BlurBuffer mBuffer = new BlurBuffer();


	public BestBlurWrapper(@NonNull Context context) {
		super(context);
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		if (!mShowBlur) {
			super.dispatchDraw(canvas);
			return;
		}

		Canvas copy = mBuffer.lock(canvas, getWidth(), getHeight());
		super.dispatchDraw(copy);
		mBuffer.unlock(canvas);
	}

	void hideBlur() {
		mShowBlur = false;
		invalidate();
	}

	void showBlur() {
		mShowBlur = true;
		invalidate();
	}

	/* private static Bitmap rs(Context context, Bitmap bitmap, int radius) throws RSRuntimeException {
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
  }*/

/*
package com.example.android.hdrviewfinder;

import android.graphics.ImageFormat;
import android.os.Handler;
import android.os.HandlerThread;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.Type;
import android.util.Size;
import android.view.Surface;


	public class ViewfinderProcessor {

		private Allocation mInputHdrAllocation;
		private Allocation mInputNormalAllocation;
		private Allocation mPrevAllocation;
		private Allocation mOutputAllocation;

		private Handler mProcessingHandler;
		private ScriptC_hdr_merge mHdrMergeScript;

		public ProcessingTask mHdrTask;
		public ProcessingTask mNormalTask;

		private int mMode;

		public final static int MODE_NORMAL = 0;
		public final static int MODE_HDR = 2;

		public ViewfinderProcessor(RenderScript rs, Size dimensions) {
			Type.Builder yuvTypeBuilder = new Type.Builder(rs, Element.YUV(rs));
			yuvTypeBuilder.setX(dimensions.getWidth());
			yuvTypeBuilder.setY(dimensions.getHeight());
			yuvTypeBuilder.setYuvFormat(ImageFormat.YUV_420_888);
			mInputHdrAllocation = Allocation.createTyped(rs, yuvTypeBuilder.create(),
					Allocation.USAGE_IO_INPUT | Allocation.USAGE_SCRIPT);
			mInputNormalAllocation = Allocation.createTyped(rs, yuvTypeBuilder.create(),
					Allocation.USAGE_IO_INPUT | Allocation.USAGE_SCRIPT);

			Type.Builder rgbTypeBuilder = new Type.Builder(rs, Element.RGBA_8888(rs));
			rgbTypeBuilder.setX(dimensions.getWidth());
			rgbTypeBuilder.setY(dimensions.getHeight());
			mPrevAllocation = Allocation.createTyped(rs, rgbTypeBuilder.create(),
					Allocation.USAGE_SCRIPT);
			mOutputAllocation = Allocation.createTyped(rs, rgbTypeBuilder.create(),
					Allocation.USAGE_IO_OUTPUT | Allocation.USAGE_SCRIPT);

			HandlerThread processingThread = new HandlerThread("ViewfinderProcessor");
			processingThread.start();
			mProcessingHandler = new Handler(processingThread.getLooper());

			mHdrMergeScript = new ScriptC_hdr_merge(rs);

			mHdrMergeScript.set_gPrevFrame(mPrevAllocation);

			mHdrTask = new ProcessingTask(mInputHdrAllocation, dimensions.getWidth()/2, true);
			mNormalTask = new ProcessingTask(mInputNormalAllocation, 0, false);

			setRenderMode(MODE_NORMAL);
		}

		public Surface getInputHdrSurface() {
			return mInputHdrAllocation.getSurface();
		}

		public Surface getInputNormalSurface() {
			return mInputNormalAllocation.getSurface();
		}

		public void setOutputSurface(Surface output) {
			mOutputAllocation.setSurface(output);
		}

		public void setRenderMode(int mode) {
			mMode = mode;
		}


		class ProcessingTask implements Runnable, Allocation.OnBufferAvailableListener {
			private int mPendingFrames = 0;
			private int mFrameCounter = 0;
			private int mCutPointX;
			private boolean mCheckMerge;

			private Allocation mInputAllocation;

			public ProcessingTask(Allocation input, int cutPointX, boolean checkMerge) {
				mInputAllocation = input;
				mInputAllocation.setOnBufferAvailableListener(this);
				mCutPointX = cutPointX;
				mCheckMerge = checkMerge;
			}

			@Override
			public void onBufferAvailable(Allocation a) {
				synchronized(this) {
					mPendingFrames++;
					mProcessingHandler.post(this);
				}
			}

			@Override
			public void run() {

				// Find out how many frames have arrived
				int pendingFrames;
				synchronized(this) {
					pendingFrames = mPendingFrames;
					mPendingFrames = 0;

					// Discard extra messages in case processing is slower than frame rate
					mProcessingHandler.removeCallbacks(this);
				}

				// Get to newest input
				for (int i = 0; i < pendingFrames; i++) {
					mInputAllocation.ioReceive();
				}

				mHdrMergeScript.set_gFrameCounter(mFrameCounter++);
				mHdrMergeScript.set_gCurrentFrame(mInputAllocation);
				mHdrMergeScript.set_gCutPointX(mCutPointX);
				if (mCheckMerge && mMode == MODE_HDR) {
					mHdrMergeScript.set_gDoMerge(1);
				} else {
					mHdrMergeScript.set_gDoMerge(0);
				}

				// Run processing pass
				mHdrMergeScript.forEach_mergeHdrFrames(mPrevAllocation, mOutputAllocation);
				mOutputAllocation.ioSend();
			}
		}

	}*/
}
