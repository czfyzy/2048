package com.zengye.game2048.view;

import java.util.ArrayList;
import java.util.List;

import com.zengye.game2048.MainActivity;
import com.zengye.game2048.view.constant.Constants;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.GridLayout;
import android.widget.Toast;

public class GameView extends GridLayout {

	private static final String TAG = "GameView";
	private boolean isAdd;

	private Card[][] cards = new Card[Constants.LINES][Constants.LINES];

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initGameView();
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGameView();
	}

	public GameView(Context context) {
		super(context);
		initGameView();
	}

	public void initGameView() {
		setColumnCount(Constants.LINES);
		setBackgroundColor(0xffbbada0);
		setOnTouchListener(new OnTouchListener() {
			float startX, startY, offsetX, offsetY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				isAdd = false;
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					startX = event.getX();
					startY = event.getY();
					break;
				case MotionEvent.ACTION_UP:
					offsetX = event.getX() - startX;
					offsetY = event.getY() - startY;
					if (Math.abs(offsetX) > Math.abs(offsetY)) {
						if (offsetX < -5) {
							swipeLeft();
						} else if (offsetX > 5) {
							swipeRight();
						}
					} else {
						if (offsetY < -5) {
							swipeUp();
						} else if (offsetY > 5) {
							swipeDown();
						}
					}
					break;
				case MotionEvent.ACTION_MOVE:

					break;
				}
				if (isAdd) {
					addRandomNum();
					checkComplete();
				}
				return true;
			}
		});

	}

	private void swipeRight() {
		for (int y = 0; y < Constants.LINES; y++) {
			for (int x = Constants.LINES - 1; x >= 0; x--) {
				for (int x1 = x - 1; x1 >= 0; x1--) {
					if (cards[x1][y].getNum() > 0) {
						if (cards[x][y].getNum() <= 0) {
							MainActivity.getMainActivity().getAnimLayer().createMoveAnim(cards[x1][y], cards[x][y],x1, x, y, y);
							cards[x][y].setNum(cards[x1][y].getNum());
							cards[x1][y].setNum(0);
							x++;
							isAdd = true;
						} else if (cards[x][y].equals(cards[x1][y])) {
							MainActivity.getMainActivity().getAnimLayer().createMoveAnim(cards[x1][y], cards[x][y],x1, x, y, y);
							cards[x][y].setNum(cards[x][y].getNum() * 2);
							cards[x1][y].setNum(0);
							MainActivity.getMainActivity().addScore(
									cards[x][y].getNum());
							isAdd = true;
						}
						break;
					}
				}
			}
		}
	}

	private void swipeLeft() {
		for (int y = 0; y < Constants.LINES; y++) {
			for (int x = 0; x < Constants.LINES; x++) {
				for (int x1 = x + 1; x1 < Constants.LINES; x1++) {
					if (cards[x1][y].getNum() > 0) {
						if (cards[x][y].getNum() <= 0) {
							MainActivity.getMainActivity().getAnimLayer().createMoveAnim(cards[x1][y],cards[x][y], x1, x, y, y);
							cards[x][y].setNum(cards[x1][y].getNum());
							cards[x1][y].setNum(0);
							x--;
							isAdd = true;
						} else if (cards[x][y].equals(cards[x1][y])) {
							MainActivity.getMainActivity().getAnimLayer().createMoveAnim(cards[x1][y],cards[x][y], x1, x, y, y);
							cards[x][y].setNum(cards[x][y].getNum() * 2);
							cards[x1][y].setNum(0);
							MainActivity.getMainActivity().addScore(
									cards[x][y].getNum());
							isAdd = true;
						}
						break;
					}
				}
			}
		}
	}

	private void swipeDown() {
		Log.i(TAG, "swipeUp");
		for (int x = 0; x < Constants.LINES; x++) {
			for (int y = Constants.LINES - 1; y >= 0; y--) {
				for (int y1 = y - 1; y1 >= 0; y1--) {
					if (cards[x][y1].getNum() > 0) {
						if (cards[x][y].getNum() <= 0) {
							MainActivity.getMainActivity().getAnimLayer().createMoveAnim(cards[x][y1], cards[x][y], x, x, y1, x);
							cards[x][y].setNum(cards[x][y1].getNum());
							cards[x][y1].setNum(0);
							y++;
							isAdd = true;
						} else if (cards[x][y].equals(cards[x][y1])) {
							MainActivity.getMainActivity().getAnimLayer().createMoveAnim(cards[x][y1], cards[x][y], x, x, y1, x);
							cards[x][y].setNum(cards[x][y].getNum() * 2);
							cards[x][y1].setNum(0);
							MainActivity.getMainActivity().addScore(
									cards[x][y].getNum());
							isAdd = true;
						}
						break;
					}
				}
			}
		}
	}

	private void swipeUp() {
		Log.i(TAG, "swipeDown");
		for (int x = 0; x < Constants.LINES; x++) {
			for (int y = 0; y < Constants.LINES; y++) {
				for (int y1 = y + 1; y1 < Constants.LINES; y1++) {
					if (cards[x][y1].getNum() > 0) {
						if (cards[x][y].getNum() <= 0) {
							MainActivity.getMainActivity().getAnimLayer().createMoveAnim(cards[x][y1], cards[x][y], x, x, y1, x);
							cards[x][y].setNum(cards[x][y1].getNum());
							cards[x][y1].setNum(0);
							y--;
							isAdd = true;
						} else if (cards[x][y].equals(cards[x][y1])) {
							MainActivity.getMainActivity().getAnimLayer().createMoveAnim(cards[x][y1], cards[x][y], x, x, y1, x);
							cards[x][y].setNum(cards[x][y].getNum() * 2);
							cards[x][y1].setNum(0);
							MainActivity.getMainActivity().addScore(
									cards[x][y].getNum());
							isAdd = true;
						}
						break;
					}
				}
			}
		}
	}

	private void checkComplete() {
		boolean complete = true;
		ALL:
		for (int y = 0; y < Constants.LINES; y++) {
			for (int x = 0; x < Constants.LINES; x++) {
				if ((cards[x][y].getNum() == 0)
						|| (x > 0 && cards[x][y].equals(cards[x - 1][y]))
						|| (x < Constants.LINES - 1 && cards[x][y].equals(cards[x + 1][y]))
						|| (y > 0 && cards[x][y].equals(cards[x][y - 1]))
						|| (y < Constants.LINES - 1 && cards[x][y].equals(cards[x][y + 1]))

				) {
					complete = false;
					break ALL;
				}
				
			}
		}
		
		if (complete) {
			new AlertDialog.Builder(getContext()).setTitle("你好").setMessage("游戏结束").setPositiveButton("重新开始", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					startGame();
					
				}
			}).show();
		}
	}

	public void startGame() {
		// TODO Auto-generated method stub
		MainActivity.getMainActivity().clearScore();
		for (int y = 0; y < Constants.LINES; y++) {
			for (int x = 0; x < Constants.LINES; x++) {
				cards[x][y].setNum(0);
			}
		}
		addRandomNum();
		addRandomNum();

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		Constants.cardWidth = (Math.min(w, h) - 10) / Constants.LINES;

		createdCards(Constants.cardWidth, Constants.cardWidth);
		startGame();
	}

	private void createdCards(int cardW, int cardH) {
		// TODO Auto-generated method stub
		Card card;
		for (int y = 0; y < Constants.LINES; y++) {
			for (int x = 0; x < Constants.LINES; x++) {
				card = new Card(getContext());
				card.setNum(0);
				this.addView(card, cardW, cardH);
				cards[x][y] = card;
			}
		}
	}

	private List<Point> emptyCards = new ArrayList<Point>();

	private void addRandomNum() {
		emptyCards.clear();
		for (int y = 0; y < Constants.LINES; y++) {
			for (int x = 0; x < Constants.LINES; x++) {
				if (cards[x][y].getNum() <= 0) {
					emptyCards.add(new Point(x, y));
				}
			}
		}

		Point point = emptyCards.get((int) (Math.random() * emptyCards.size()));
		int num = Math.random() > 0.1 ? 2 : Constants.LINES;
		Log.i("GameView", "" + num);
		cards[point.x][point.y].setNum(num);
		cards[point.x][point.y].clearAnimation();
		ScaleAnimation animation = new ScaleAnimation(0, 1, 0, 1,Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		animation.setDuration(300);
		cards[point.x][point.y].setAnimation(animation);
	}
}
