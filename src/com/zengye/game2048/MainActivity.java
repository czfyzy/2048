package com.zengye.game2048;

import com.zengye.game2048.view.AnimLayer;
import com.zengye.game2048.view.GameView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	
	public MainActivity() {
		super();
		// TODO Auto-generated constructor stub\
		this.mainActivity = this;
	}
	private TextView score;
	private int scoreNum = 0;
	private GameView gameView;
	private AnimLayer animLayer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		score = (TextView) findViewById(R.id.score_num);
		gameView = (GameView) findViewById(R.id.game_view);
		animLayer = (AnimLayer) findViewById(R.id.anim_layer);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	private static MainActivity mainActivity = null;
	
	public static MainActivity getMainActivity() {
		return mainActivity;
	}
	public void addScore(int s) {
		scoreNum += s;
		showScore();
	}
	
	public void showScore() {
		this.score.setText(scoreNum + "");
	}
	
	public void clearScore(){
		scoreNum = 0;
		showScore();
	}
	
	public void reStart(View view) {
		clearScore();
		gameView.startGame();
	}
	
	public AnimLayer getAnimLayer() {
		return animLayer;
	}
}
