package com.zengye.game2048.view;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout {

	private int num = 0;
	private TextView label;

	public TextView getLabel() {
		return label;
	}

	public Card(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

		label = new TextView(context);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		layoutParams.setMargins(10, 10, 0, 0);
		label.setGravity(Gravity.CENTER);
		label.setLayoutParams(layoutParams);
		// label.setTextColor(0xff000000);
		label.setBackgroundColor(0x33ffffff);
		label.setTextSize(30f);
		this.addView(label);

		setNum(0);
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
		if (num > 0) {
			label.setText("" + num);
			switch (num) {
			case 2 << 0:
				label.setBackgroundColor(0xffeee4da);
				break;
			case 2 << 1:
				label.setBackgroundColor(0xffede0c8);
				break;
			case 2 << 2:
				label.setBackgroundColor(0xfff2b179);
				break;
			case 2 << 3:
				label.setBackgroundColor(0xfff59563);
				break;
			case 2 << 4:
				label.setBackgroundColor(0xfff67c5f);
				break;
			case 2 << 5:
				label.setBackgroundColor(0xfff65e3b);
				break;
			case 2 << 6:
				label.setBackgroundColor(0xffedcf72);
				break;
			case 2 << 7:
				label.setBackgroundColor(0xffedcc61);
				break;
			case 2 << 8:
				label.setBackgroundColor(0xffedc850);
				break;
			case 2 << 9:
				label.setBackgroundColor(0xffedc53f);
				break;
			case 2 << 10:
				label.setBackgroundColor(0xffedc22e);
				break;
			default:
				label.setBackgroundColor(0xff3c3a32);
				break;
			}

		} else {
			label.setText("");
			label.setBackgroundColor(0x33ffffff);
		}
	}

	public boolean equals(Card c) {
		return num == c.getNum();
	}

}
