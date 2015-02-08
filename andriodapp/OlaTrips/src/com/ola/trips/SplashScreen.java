package com.ola.trips;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;

public class SplashScreen extends Activity {

	protected final int m_splashTime = 1000;
	private MyCount m_splashtimer;
	protected static int s_timerCount = 0;
	protected static Boolean s_isTimerPaused = false, s_splashFinish = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		s_splashFinish = false;
		s_timerCount = m_splashTime;
	}

	private class MyCount extends CountDownTimer {
		public MyCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			s_splashFinish = true;
			Intent intent = new Intent(getApplicationContext(), MainActivity.class);
			startActivity(intent);
			finish();

			return;
		}

		@Override
		public void onTick(long arg0) {
			s_timerCount -= 100;
			return;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			s_splashFinish = true;
			m_splashtimer.cancel();
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (!s_splashFinish)
			pauseTimer();
		return;
	}

	public void pauseTimer() {
		m_splashtimer.cancel();
		return;
	}

	@Override
	protected void onResume() {
		super.onResume();
		resumeTimer();
		s_splashFinish = false;
		return;
	}

	/**
	 * This method resumes timer with the remaining mTimerCount.
	 */
	public void resumeTimer() {
		m_splashtimer = new MyCount(s_timerCount, 100);
		m_splashtimer.start();
		return;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		return;
	}
}