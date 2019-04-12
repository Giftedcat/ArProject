package com.bigkoo.alertview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.git.easylib.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayStringWheelAdapter;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;

/**
 * Created by Sai on 15/8/9.
 * 精仿iOSAlertViewController控件
 * 点击取消按钮返回 －1，其他按钮从0开始算
 */
public class AlertWheelView {
    private final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM
    );
    public static final int HORIZONTAL_BUTTONS_MAXCOUNT = 2;
    public static final int CANCELPOSITION = -1;//点击取消按钮返回 －1，其他按钮从0开始算

    private String title;
    private String msg;
    private String[] others;
    private List<String> mOthers;
    private int visibleItems;
    private String cancel;
    private ArrayList<String> mDatas = new ArrayList<>();
    private int posittion;


    private WeakReference<Context> contextWeak;
    private ViewGroup contentContainer;
    private ViewGroup decorView;//activity的根View
    private ViewGroup rootView;//AlertView 的 根View
    private ViewGroup loAlertHeader;//窗口headerView

    private OnDismissListener onDismissListener;
    private OnWheelClickListener onWheelClickListener;
    private boolean isShowing;

    private Animation outAnim;
    private Animation inAnim;
    private int gravity = Gravity.CENTER;

    public AlertWheelView(Builder builder) {
        this.contextWeak = new WeakReference<>(builder.context);
        this.title = builder.title;
        this.msg = builder.msg;
        this.cancel = builder.cancel;
        this.others = builder.others;
        this.onWheelClickListener = builder.onItemClickListener;

        initData(title, msg, cancel, visibleItems, others);
        initViews();
        init();
        initEvents();
    }

    /**
     *
     * @param title
     * @param msg
     * @param cancel
     * @param visibleItems
     * @param others
     * @param context
     * @param onItemClickListener
     */
    public AlertWheelView(String title, String msg, String cancel, int visibleItems, String[] others, Context context, OnWheelClickListener onItemClickListener){
        this.contextWeak = new WeakReference<>(context);
        this.onWheelClickListener = onItemClickListener;

        initData(title, msg, cancel, visibleItems, others);
        initViews();
        init();
        initEvents();
    }

    /**
     *
     * @param title
     * @param msg
     * @param cancel
     * @param visibleItems
     * @param others
     * @param context
     * @param onItemClickListener
     */
    public AlertWheelView(String title, String msg, String cancel, int visibleItems, List<String> others, Context context, OnWheelClickListener onItemClickListener){
        this.contextWeak = new WeakReference<>(context);
        this.onWheelClickListener = onItemClickListener;

        initData(title, msg, cancel, visibleItems, others);
        initViews();
        init();
        initEvents();
    }

    /**
     * 获取数据
     */
    protected void initData(String title, String msg, String cancel, int visibleItems, String[] others) {

        this.title = title;
        this.msg = msg;
        if (visibleItems <= 0){
            this.visibleItems = 3;
        }else{
            this.visibleItems = visibleItems;
        }
        if (others != null){
            this.mOthers = Arrays.asList(others);
            this.mDatas.addAll(mOthers);
        }
        if (cancel != null){
            this.cancel = cancel;
        }

    }

    /**
     *
     * @param title
     * @param msg
     * @param cancel
     * @param visibleItems
     * @param others
     */
    protected void initData(String title, String msg, String cancel, int visibleItems, List<String> others) {

        this.title = title;
        this.msg = msg;
        if (visibleItems <= 0){
            this.visibleItems = 3;
        }else{
            this.visibleItems = visibleItems;
        }
        if (others != null){
            this.mDatas.addAll(others);
        }
        if (cancel != null){
            this.cancel = cancel;
        }

    }

    /**
     *
     */
    protected void initViews(){
        Context context = contextWeak.get();
        if(context == null) return;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        decorView = (ViewGroup) ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);
        rootView = (ViewGroup) layoutInflater.inflate(R.layout.layout_alertview, decorView, false);
        rootView.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        ));
        contentContainer = (ViewGroup) rootView.findViewById(R.id.content_container);
        int margin_alert_left_right = 0;
        params.gravity = Gravity.BOTTOM;
        margin_alert_left_right = context.getResources().getDimensionPixelSize(R.dimen.margin_actionsheet_left_right);
        params.setMargins(margin_alert_left_right,0,margin_alert_left_right,margin_alert_left_right);
        contentContainer.setLayoutParams(params);
        gravity = Gravity.BOTTOM;
        initAlertWheelViews(layoutInflater);
    }


    /**
     *
     * @param viewGroup
     */
    protected void initHeaderView(ViewGroup viewGroup){
        loAlertHeader = (ViewGroup) viewGroup.findViewById(R.id.loAlertHeader);
        //标题和消息
        TextView tvAlertTitle = (TextView) viewGroup.findViewById(R.id.tvAlertTitle);
        TextView tvAlertMsg = (TextView) viewGroup.findViewById(R.id.tvAlertMsg);
        if(title != null) {
            tvAlertTitle.setText(title);
        }else{
            tvAlertTitle.setVisibility(View.GONE);
        }
        if(msg != null) {
            tvAlertMsg.setText(msg);
        }else{
            tvAlertMsg.setVisibility(View.GONE);
        }
    }

    //TODO
    protected void initAlertWheelView(){
        Context context = contextWeak.get();
        if(context == null) return;
        WheelView alertwheelView = (WheelView) contentContainer.findViewById(R.id.alertwheel);
        // 添加change事件
        alertwheelView.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                Log.d("AlertWheelView","" + newValue);
                posittion = newValue;
            }
        });
        alertwheelView.setViewAdapter(new ArrayStringWheelAdapter(context, mDatas));
        // 设置可见条目数量
        alertwheelView.setVisibleItems(visibleItems);
    }

    /**
     *
     * @param layoutInflater
     */
    protected void initAlertWheelViews(LayoutInflater layoutInflater) {
        ViewGroup viewGroup = (ViewGroup) layoutInflater.inflate(R.layout.layout_alertview_wheel, contentContainer);
        initHeaderView(viewGroup);

        initAlertWheelView();
        TextView tvAlertCancel = (TextView) contentContainer.findViewById(R.id.tvAlertCancel);
        if(cancel != null){
            tvAlertCancel.setVisibility(View.VISIBLE);
            tvAlertCancel.setText(cancel);
        }
        tvAlertCancel.setOnClickListener(new OnTextClickListener(CANCELPOSITION));

        TextView tvAlertOk = (TextView) contentContainer.findViewById(R.id.tvAlertOk);
        tvAlertOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onWheelClickListener.onItemClick(AlertWheelView.this , posittion);
                dismiss();
            }
        });
    }

    /**
     *
     */
    protected void init() {
        inAnim = getInAnimation();
        outAnim = getOutAnimation();
    }

    /**
     *
     */
    protected void initEvents() {
    }

    /**
     *
     * @param extView
     * @return
     */
    public AlertWheelView addExtView(View extView){
        loAlertHeader.addView(extView);
        return this;
    }

    /**
     * show的时候调用
     *
     * @param view 这个View
     */
    private void onAttached(View view) {
        isShowing = true;
        decorView.addView(view);
        contentContainer.startAnimation(inAnim);
    }
    /**
     * 添加这个View到Activity的根视图
     */
    public void show() {
        if (isShowing()) {
            return;
        }
        onAttached(rootView);
    }
    /**
     * 检测该View是不是已经添加到根视图
     *
     * @return 如果视图已经存在该View返回true
     */
    public boolean isShowing() {
        return rootView.getParent() != null && isShowing;
    }

    public void dismiss() {
        //消失动画
        outAnim.setAnimationListener(outAnimListener);
        contentContainer.startAnimation(outAnim);
    }

    public void dismissImmediately() {
        decorView.removeView(rootView);
        isShowing = false;
        if(onDismissListener != null){
            onDismissListener.onDismiss(this);
        }

    }

    public Animation getInAnimation() {
        Context context = contextWeak.get();
        if(context == null) return null;

        int res = AlertAnimateUtil.getAnimationResource(this.gravity, true);
        return AnimationUtils.loadAnimation(context, res);
    }

    public Animation getOutAnimation() {
        Context context = contextWeak.get();
        if(context == null) return null;

        int res = AlertAnimateUtil.getAnimationResource(this.gravity, false);
        return AnimationUtils.loadAnimation(context, res);
    }

    public AlertWheelView setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
        return this;
    }

    class OnTextClickListener implements View.OnClickListener{

        private int position;
        public OnTextClickListener(int position){
            this.position = position;
        }
        @Override
        public void onClick(View view) {
            if(onWheelClickListener != null) onWheelClickListener.onItemClick(AlertWheelView.this,position);
            dismiss();
        }

    }
    private Animation.AnimationListener outAnimListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            dismissImmediately();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    /**
     * 主要用于拓展View的时候有输入框，键盘弹出则设置MarginBottom往上顶，避免输入法挡住界面
     */
    public void setMarginBottom(int marginBottom){
        Context context = contextWeak.get();
        if(context == null) return;

        int margin_alert_left_right = context.getResources().getDimensionPixelSize(R.dimen.margin_alert_left_right);
        params.setMargins(margin_alert_left_right,0,margin_alert_left_right,marginBottom);
        contentContainer.setLayoutParams(params);
    }
    public AlertWheelView setCancelable(boolean isCancelable) {
        View view = rootView.findViewById(R.id.outmost_container);

        if (isCancelable) {
            view.setOnTouchListener(onCancelableTouchListener);
        }
        else{
            view.setOnTouchListener(null);
        }
        return this;
    }
    /**
     * Called when the user touch on black overlay in order to dismiss the dialog
     */
    private final View.OnTouchListener onCancelableTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                dismiss();
            }
            return false;
        }
    };

    /**
     * Builder for arguments
     */
    public static class Builder {
        private Context context;
        private String title;
        private String msg;
        private String cancel;
        private int visibleItems;
        private String[] others;
        private OnWheelClickListener onItemClickListener;

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder setCancelText(String cancel) {
            this.cancel = cancel;
            return this;
        }

        public Builder setVisibleItems(int visibleItems) {
            this.visibleItems = visibleItems;
            return this;
        }

        public Builder setOthers(String[] others) {
            this.others = others;
            return this;
        }

        public Builder setOnItemClickListener(OnWheelClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
            return this;
        }

        public AlertWheelView build() {
            return new AlertWheelView(this);
        }
    }
}
