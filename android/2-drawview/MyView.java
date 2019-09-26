package com.kyle.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyView extends View {

	Paint paint = new Paint();
	public MyView(Context context) {
		super(context);
		paint.setColor(Color.BLUE);
		paint.setTextSize(20);
		paint.setAntiAlias(true);
	}

	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		canvas.drawColor(Color.YELLOW);
		canvas.drawRect(10, 10, 110, 110, paint);
		canvas.drawText("hello world", 60, 170, paint);
	}
}
