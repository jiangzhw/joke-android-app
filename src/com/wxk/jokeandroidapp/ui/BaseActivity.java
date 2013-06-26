package com.wxk.jokeandroidapp.ui;

import com.wxk.jokeandroidapp.AppManager;
import com.wxk.jokeandroidapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.GestureDetector.OnGestureListener;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class BaseActivity extends Activity {
	protected final String TAG = "activity";
	protected ProgressBar pbLoad;
	protected TextView txtvPageTitle;
	protected ImageButton imgbAppIcon;
	protected ViewGroup titleBar;
	protected GestureDetector gestureDetector;

	protected void showToast(String showText) {
		Toast toast = Toast.makeText(this, showText, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gestureDetector = new GestureDetector(this, onGestureListener);
		AppManager.getInstance().addActivity(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		AppManager.getInstance().finishActivity(this);
	}

	protected void showToast(int resId) {
		showToast(this.getString(resId));
	}

	protected void initTitleBar() {
		titleBar = (ViewGroup) findViewById(R.id.titlebar);
		if (titleBar != null) {
			pbLoad = (ProgressBar) titleBar
					.findViewById(R.id.titlebar_progress);
			txtvPageTitle = (TextView) titleBar
					.findViewById(R.id.titlebar_text);
			imgbAppIcon = (ImageButton) titleBar
					.findViewById(R.id.titlebar_app_icon);
		}

	}

	protected void onFlingLeft() {
	}

	protected void onFlingRight() {

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return gestureDetector.onTouchEvent(event);
	}

	OnGestureListener onGestureListener = new OnGestureListener() {
		@Override
		public boolean onDown(MotionEvent arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onFling(MotionEvent e0, MotionEvent e1, float v0,
				float v1) {
			if (null == e0 || e1 == null)
				return false;
			//
			float x = e0.getX() - e1.getX();
			float y = e0.getY() - e1.getY();
			// 滑动最小距离
			float x_limit = 50;
			float y_limit = 50;
			float abs_x = Math.abs(x);
			float abs_y = Math.abs(y);
			if (abs_x >= abs_y) {
				// left or right
				if (x > x_limit || x < -x_limit) {
					if (x > 0) {
						// left
						onFlingLeft();
						return true;
					} else {
						// right
						onFlingRight();
						return true;
					}
				}
			} else {
				if (y > y_limit || y < -y_limit) {
					if (y > 0) {
						// up
						Log.v(TAG, "up");
					} else
						// down
						Log.v(TAG, "down");
				}
			}
			return false;
		}

		@Override
		public void onLongPress(MotionEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
				float arg3) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onShowPress(MotionEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean onSingleTapUp(MotionEvent arg0) {
			// TODO Auto-generated method stub
			return false;
		}
	};
}