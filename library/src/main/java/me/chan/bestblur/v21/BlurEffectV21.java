package me.chan.bestblur.v21;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import me.chan.bestblur.BestBlur;
import me.chan.bestblur.BlurEffect;

public class BlurEffectV21 implements BlurEffect {
	private final BestBlur.Builder mBuilder;
	private BestBlurWrapper mWrapper;

	public BlurEffectV21(BestBlur.Builder builder) {
		mBuilder = builder;
	}

	@Override
	public void show() {
		if (mWrapper != null) {
			mWrapper.showBlur();
			return;
		}

		View view = mBuilder.getView();
		ViewParent viewParent = view.getParent();
		if (viewParent == null) {
			return;
		}

		if (viewParent instanceof BestBlurWrapper) {
			mWrapper = (BestBlurWrapper) viewParent;
			mWrapper.showBlur();
			return;
		}

		if (!(viewParent instanceof ViewGroup))  {
			throw new IllegalStateException("show blur illegal state");
		}

		ViewGroup parent = (ViewGroup) viewParent;
		mWrapper = new BestBlurWrapper(view.getContext());
		ViewGroup.LayoutParams params = view.getLayoutParams();
		int index = parent.indexOfChild(view);
		parent.removeView(view);
		mWrapper.setLayoutParams(params);
		parent.addView(mWrapper, index);
		mWrapper.addView(view, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		mWrapper.showBlur();
	}

	@Override
	public void hide() {
		if (mWrapper != null) {
			mWrapper.hideBlur();
		}
	}
}
