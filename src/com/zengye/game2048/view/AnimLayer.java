package com.zengye.game2048.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import com.zengye.game2048.view.constant.Constants;

public class AnimLayer extends FrameLayout {
	private List<Card> cards = new ArrayList<Card>();

	public AnimLayer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public AnimLayer(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public AnimLayer(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public void createMoveAnim(final Card from,final Card to,int fromX,int toX,int fromY,int toY){
		int width = Constants.cardWidth;
		final Card card = getCard(from.getNum());
		LayoutParams params = new LayoutParams(width, width);
		params.setMargins(fromX * width, fromY * width, 0, 0);
		card.setLayoutParams(params);
		if (to.getNum()<=0) {
			to.getLabel().setVisibility(View.INVISIBLE);
		}
		
		TranslateAnimation ta = new TranslateAnimation(0, (toX - fromX) * width, 0, (toY - fromY) * width); 
		ta.setDuration(100);
		ta.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				to.getLabel().setVisibility(View.VISIBLE);
				recycleCard(card);
			}
		});
		card.setAnimation(ta);
	}
	
	private Card getCard(int num){
		Card card = null;
		if(cards.size() > 0) {
			card = cards.remove(0);
		} else {
			card = new Card(getContext());
			addView(card);
		}
		card.setVisibility(View.VISIBLE);
		card.setNum(num);
		return card;
	}
	private void recycleCard(Card card) {
		card.setVisibility(View.INVISIBLE);
		card.setAnimation(null);
		cards.add(card);
	}
	
	public void createScaleTo1(Card target){
		ScaleAnimation sa = new ScaleAnimation(0.1f, 1, 0.1f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		sa.setDuration(100);
		target.setAnimation(null);
		target.getLabel().startAnimation(sa);
	}
}
