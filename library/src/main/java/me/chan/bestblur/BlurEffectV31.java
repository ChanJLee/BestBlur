package me.chan.bestblur;

import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;

import androidx.annotation.RequiresApi;

import me.chan.bestblur.BestBlur;
import me.chan.bestblur.BlurEffect;

@RequiresApi(api = Build.VERSION_CODES.S)
public class BlurEffectV31 implements BlurEffect {

	private final BestBlur.Builder mBuilder;

	private final RenderEffect mRenderEffect;

	public BlurEffectV31(BestBlur.Builder builder) {
		mBuilder = builder;
		final float radius = builder.getRadius();
		mRenderEffect = RenderEffect.createBlurEffect(radius, radius, Shader.TileMode.CLAMP);
	}

	@Override
	public void show() {
		mBuilder.getView().setRenderEffect(mRenderEffect);
	}

	@Override
	public void hide() {
		mBuilder.getView().setRenderEffect(null);
	}
}
