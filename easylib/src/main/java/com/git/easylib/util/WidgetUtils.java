package com.git.easylib.util;

import android.app.Activity;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 获取常用控件实例工具类
 * Created by luzhuwen on 2017/5/15.
 */
public class WidgetUtils {

	private WidgetUtils() {}

	/** 获取ImageView实例 */
	public static ImageView getImageView(Activity activity, int viewId) {
		ImageView imageView = (ImageView) activity.findViewById(viewId);
	    return imageView;
	}

	/** 获取ImageView实例，带监听 */
	public static ImageView getImageView(Activity activity, int viewId, View.OnClickListener onClickListener) {
		ImageView imageView = (ImageView) activity.findViewById(viewId);
		imageView.setOnClickListener(onClickListener);
	    return imageView;
	}

	/** 在View中获取ImageView实例 */
	public static ImageView getImageViewByView(Activity activity, int viewId) {
		ImageView imageView = (ImageView) activity.findViewById(viewId);
		return imageView;
	}

	/** 在View中获取ImageView实例，带监听 */
	public static ImageView getImageViewByView(Activity activity, int viewId, View.OnClickListener onClickListener) {
		ImageView imageView = (ImageView) activity.findViewById(viewId);
		imageView.setOnClickListener(onClickListener);
		return imageView;
	}

	/** 获取TextView实例 */
	public static TextView getTextView(Activity activity, int viewId) {
		TextView textView = (TextView) activity.findViewById(viewId);
		return textView;
	}

	/** 获取TextView实例，带监听 */
	public static TextView getTextView(Activity activity, int viewId, View.OnClickListener onClickListener) {
		TextView textView = (TextView) activity.findViewById(viewId);
		textView.setOnClickListener(onClickListener);
		return textView;
	}

	/** 获取TextView实例，带监听，带背景设置 */
	public static TextView getTextView(Activity activity, int viewId, View.OnClickListener onClickListener, final int backgroundColor) {
		TextView textView = getTextView(activity, viewId, onClickListener);
		textView.setBackgroundResource(backgroundColor);
		textView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				return setClickBgColor(view, motionEvent, backgroundColor);
			}
		});
		return textView;
	}

	/** 在View中获取TextView实例 */
	public static TextView getTextViewByView(View view, int viewId) {
		TextView textView = (TextView) view.findViewById(viewId);
		return textView;
	}

	/** 在View中获取TextView实例，带监听 */
	public static TextView getTextViewByView(View view, int viewId, View.OnClickListener onClickListener) {
		TextView textView = (TextView) view.findViewById(viewId);
		textView.setOnClickListener(onClickListener);
		return textView;
	}

	/** 在View中获取TextView实例，带监听，带背景设置 */
	public static TextView getTextViewByView(View view, int viewId, View.OnClickListener onClickListener, final int backgroundColor) {
		TextView textView = getTextViewByView(view, viewId, onClickListener);
		textView.setBackgroundResource(backgroundColor);
		textView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				return setClickBgColor(view, motionEvent, backgroundColor);
			}
		});
		return textView;
	}

	/** 获取LinearLayout实例 */
	public static LinearLayout getLinearLayout(Activity activity, int viewId) {
		LinearLayout linearLayout = (LinearLayout) activity.findViewById(viewId);
		return linearLayout;
	}

	/** 获取LinearLayout实例，带监听 */
	public static LinearLayout getLinearLayout(Activity activity, int viewId, View.OnClickListener onClickListener) {
		LinearLayout linearLayout = (LinearLayout) activity.findViewById(viewId);
		linearLayout.setOnClickListener(onClickListener);
		return linearLayout;
	}

	/** 获取LinearLayout实例，带监听，带背景设置 */
	public static LinearLayout getLinearLayout(Activity activity, int viewId, View.OnClickListener onClickListener, final int backgroundColor) {
		LinearLayout linearLayout = getLinearLayout(activity, viewId, onClickListener);
		linearLayout.setBackgroundResource(backgroundColor);
		linearLayout.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				return setClickBgColor(view, motionEvent, backgroundColor);
			}
		});
		return linearLayout;
	}

	/** 在View中获取LinearLayout实例 */
	public static LinearLayout getLinearLayoutByView(View view, int viewId) {
		LinearLayout linearLayout = (LinearLayout) view.findViewById(viewId);
		return linearLayout;
	}

	/** 在View中获取LinearLayout实例，带监听 */
	public static LinearLayout getLinearLayoutByView(View view, int viewId, View.OnClickListener onClickListener) {
		LinearLayout linearLayout = (LinearLayout) view.findViewById(viewId);
		linearLayout.setOnClickListener(onClickListener);
		return linearLayout;
	}

	/** 在View中获取LinearLayout实例，带监听，带背景设置 */
	public static LinearLayout getLinearLayoutByView(View view, int viewId, View.OnClickListener onClickListener, final int backgroundColor) {
		LinearLayout linearLayout = getLinearLayoutByView(view, viewId, onClickListener);
		linearLayout.setBackgroundResource(backgroundColor);
		linearLayout.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				return setClickBgColor(view, motionEvent, backgroundColor);
			}
		});
		return linearLayout;
	}

	/** 获取RelativeLayout实例 */
	public static RelativeLayout getRelativeLayout(Activity activity, int viewId) {
		RelativeLayout relativeLayout = (RelativeLayout) activity.findViewById(viewId);
		return relativeLayout;
	}

	/** 获取RelativeLayout实例，带监听 */
	public static RelativeLayout getRelativeLayout(Activity activity, int viewId, View.OnClickListener onClickListener) {
		RelativeLayout relativeLayout = (RelativeLayout) activity.findViewById(viewId);
		relativeLayout.setOnClickListener(onClickListener);
		return relativeLayout;
	}

	/** 获取RelativeLayout实例，带监听，带背景设置 */
	public static RelativeLayout getRelativeLayout(Activity activity, int viewId, View.OnClickListener onClickListener, final int backgroundColor) {
		RelativeLayout relativeLayout = getRelativeLayout(activity, viewId, onClickListener);
		relativeLayout.setBackgroundResource(backgroundColor);
		relativeLayout.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				return setClickBgColor(view, motionEvent, backgroundColor);
			}
		});
		return relativeLayout;
	}

	/** 在View中获取RelativeLayout实例 */
	public static RelativeLayout getRelativeLayoutByView(View view, int viewId) {
		RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(viewId);
		return relativeLayout;
	}

	/** 在View中获取RelativeLayout实例，带监听 */
	public static RelativeLayout getRelativeLayoutByView(View view, int viewId, View.OnClickListener onClickListener) {
		RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(viewId);
		relativeLayout.setOnClickListener(onClickListener);
		return relativeLayout;
	}

	/** 在View中获取RelativeLayout实例，带监听，带背景设置 */
	public static RelativeLayout getRelativeLayoutByView(View view, int viewId, View.OnClickListener onClickListener, final int backgroundColor) {
		RelativeLayout relativeLayout = getRelativeLayoutByView(view, viewId, onClickListener);
		relativeLayout.setBackgroundResource(backgroundColor);
		relativeLayout.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				return setClickBgColor(view, motionEvent, backgroundColor);
			}
		});
		return relativeLayout;
	}

	/** 获取EditText实例 */
	public static EditText getEditText(Activity activity, int viewId) {
		EditText editText = (EditText) activity.findViewById(viewId);
		return editText;
	}

	/** 在View中获取EditText实例 */
	public static EditText getEditTextByView(View view, int viewId) {
		EditText editText = (EditText) view.findViewById(viewId);
		return editText;
	}

	/** 获取ListView实例 */
	public static ListView getListView(Activity activity, int viewId) {
		ListView listView = (ListView) activity.findViewById(viewId);
		return listView;
	}

	/** 在View中获取ListView实例 */
	public static ListView getListViewByView(View view, int viewId) {
		ListView listView = (ListView) view.findViewById(viewId);
		return listView;
	}

	/** 设置ListView的高度适度所有Item高度之和 */
	public static void setListViewHeightAdaptChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null)
			return;
		int s32TotalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			s32TotalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = s32TotalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	/**
	 * 设置控件点击时背景颜色变化的效果
	 * @param view
	 * @param event
	 * @param backgroundColor 未点击时的背景颜色
     * @return
     */
	private static boolean setClickBgColor(View view, MotionEvent event, int backgroundColor) {
		Drawable drawable = view.getResources().getDrawable(backgroundColor);
		if (drawable == null)
			return false;
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			drawable.setColorFilter(new ColorMatrixColorFilter(BT_SELECTED));
			view.setBackgroundDrawable(drawable);
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			drawable.setColorFilter(new ColorMatrixColorFilter(BT_NOT_SELECTED));
			view.setBackgroundDrawable(drawable);
		}
		return false;
	}

	/** 点击控件时背景颜色加深效果，数组里的负数越小，颜色越深 */
	private static final float[] BT_SELECTED = new float[] { 1, 0, 0, 0, -20, 0, 1, 0, 0, -20, 0, 0, 1, 0, -20, 0, 0, 0, 1, 0 };
	/** 手指离开控件时背景颜色还原效果 */
	private static final float[] BT_NOT_SELECTED = new float[] { 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 };

}
