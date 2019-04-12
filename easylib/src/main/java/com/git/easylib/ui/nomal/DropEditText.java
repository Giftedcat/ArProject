package com.git.easylib.ui.nomal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.git.easylib.R;

/**
 * Created by Yannis on 2016/7/23.
 */
public class DropEditText extends FrameLayout implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText mEditText;  // 输入框
    private ImageView mDropImage; // 右边的图片按钮
    private PopupWindow mPopup; // 点击图片弹出popupwindow
    private WrapListView mPopView; // popupwindow的布局

    private int mDrawableLeft;
    private int mDropMode; // flow_parent or wrap_content
    private String mHit;
    private TextChangelistener textChangelistener;

    public DropEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.edit_layout, this);
        mPopView = (WrapListView) LayoutInflater.from(context).inflate(R.layout.pop_view, null);
        textChangelistener = (TextChangelistener) context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DropEditText, defStyle, 0);
        mDrawableLeft = ta.getResourceId(R.styleable.DropEditText_drawableRight, R.drawable.np_numberpicker_down_normal_holo_dark);
        mDropMode = ta.getInt(R.styleable.DropEditText_dropMode, 0);
        mHit = ta.getString(R.styleable.DropEditText_hint);



        ta.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mEditText = (EditText) findViewById(R.id.dropview_edit);
        mDropImage = (ImageView) findViewById(R.id.dropview_image);
        mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>=15) {
                    String s1 = String.format(getContext().getString(R.string.can_input_count_words), (20 - s.length()) + "");
//                    NewUICommonUtil.showSingleToast(getContext(),s1);
                }
                if(!mEditText.getText().toString().equals("")){
                    textChangelistener.textChange(true);
                } else {
                    textChangelistener.textChange(false);
                }
            }
        });

        mEditText.setSelectAllOnFocus(true);
        mDropImage.setImageResource(mDrawableLeft);

        if(!TextUtils.isEmpty(mHit)) {
            mEditText.setHint(mHit);
        }

        mDropImage.setOnClickListener(this);
        mPopView.setOnItemClickListener(this);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        // 如果布局发生改
        // 并且dropMode是flower_parent
        // 则设置ListView的宽度
        if(changed && 0 == mDropMode) {
            mPopView.setListWidth(getMeasuredWidth());
        }
    }

    /**
     * 设置Adapter
     * @param adapter ListView的Adapter
     */
    public void setAdapter(BaseAdapter adapter) {
        mPopView.setAdapter(adapter);

        mPopup = new PopupWindow(mPopView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopup.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        mPopup.setFocusable(true); // 让popwin获取焦点
        mPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                    mDropImage.setImageResource(R.drawable.np_numberpicker_down_normal_holo_dark);
            }
        });
    }

    /**
     * 获取输入框内的内容
     * @return String content
     */
    public String getText() {
        return mEditText.getText().toString();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.dropview_image) {
            if(mPopup.isShowing()) {
                mPopup.dismiss();
                return;
            }
            mPopup.showAsDropDown(this, 0, 5);
            mDropImage.setImageResource(R.drawable.np_numberpicker_up_normal_holo_dark);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mEditText.setText(mPopView.getAdapter().getItem(position).toString());
        mEditText.setSelection(mEditText.getText().length());
        mPopup.dismiss();
    }

    public void setText(String text){
        mEditText.setText(text);
    }


    public interface TextChangelistener{

        void textChange(boolean change);


    }


}
