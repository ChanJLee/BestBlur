package me.chan.bestblur;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

class BlurEffectImpl implements BlurEffect {
	private final BestBlur.Builder mBuilder;
	private BestBlurWrapper mWrapper;

	public BlurEffectImpl(BestBlur.Builder builder) {
		mBuilder = builder;
	}

	private void renderEffect() {
		if (mWrapper != null) {
			mWrapper.renderBlur(true);
			return;
		}

		View view = mBuilder.getView();
		ViewParent viewParent = view.getParent();
		if (viewParent == null) {
			return;
		}

		if (viewParent instanceof BestBlurWrapper) {
			throw new IllegalStateException("create blur effect on same view");
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
		mWrapper.renderBlur(true);
	}

	private void hideEffect() {
		if (mWrapper != null) {
			mWrapper.renderBlur(false);
		}
	}

	@Override
	public void renderBlur(boolean enable) {
		if (enable) {
			renderEffect();
			return;
		}

		hideEffect();
	}
}
