package me.chan.bestblur;

import android.app.Application;
import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;

import me.chan.bestblur.v21.BlurEffectV21;
import me.chan.bestblur.v31.BlurEffectV31;

public class BestBlur {

	public static void init(Application application) {

	}

	public static BlurEffect create(@NonNull View view) {
		return new Builder(view)
				.build();
	}

	public static class Builder {
		private final View mView;
		private float mRadius = 16f;

		public Builder(View view) {
			mView = view;
		}

		public Builder radius(float radius) {
			mRadius = radius;
			return this;
		}

		public View getView() {
			return mView;
		}

		public float getRadius() {
			return mRadius;
		}

		public BlurEffect build() {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
				return new BlurEffectV31(this);
			} else {
				return new BlurEffectV21(this);
			}
		}
	}
}
