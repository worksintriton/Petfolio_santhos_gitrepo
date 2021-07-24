package com.dci.berts.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.dci.berts.R;

public class CustomEditText extends LinearLayout {

    public ImageView imvError;
    public EditText edtContent;
    public TextView tvError;
    public TextView tvErrorDetail;
    public RelativeLayout rlContent;
    public String title;
    boolean hiddenPass = true;
    public int inputType = 2;  //0:email ,1: pass, 2: normal, 3: number
    private boolean isTypePass = false;
    private Context context;


    public void setTitle(String title){
        this.title = title;
        tvError.setText(title);
        edtContent.setHint(title);
        edtContent.setHintTextColor(getResources().getColor(R.color.hint_color));
    }


    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    public CustomEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    public void initView(Context context, AttributeSet attrs){
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CustomEditText, 0, 0);
        try {
            title = typedArray.getString(R.styleable.CustomEditText_hint);
            inputType = typedArray.getInteger(R.styleable.CustomEditText_inputType,2);
        } finally {
            typedArray.recycle();
        }


        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setOrientation(LinearLayout.VERTICAL);
        View view = inflater.inflate(R.layout.custom_edittext, this, true);

        imvError = view.findViewById(R.id.imvEmailError);
        edtContent = view.findViewById(R.id.editText);
        tvError = view.findViewById(R.id.tvMailError);
        tvErrorDetail = view.findViewById(R.id.tvMailErrorDetail);
        rlContent = view.findViewById(R.id.rlEmail);

        setTitle(title);
        setInputType(inputType);

        rlContent.setOnClickListener(view12 -> edtContent.requestFocus());

        edtContent.setOnFocusChangeListener((view1, b) -> {
            if(!b){
                if(edtContent.getText().toString().isEmpty()){
                    tvError.setVisibility(GONE);
                }
                edtContent.setHint(title);
            }else {
                tvError.setVisibility(VISIBLE);
//                    edtContent.setHint("");
            }
        });

        edtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()!=0){
                    setNormal(title);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void setError(String error){
        rlContent.setBackgroundResource(R.drawable.bg_edt_error);
        if(inputType == 1){
            imvError.setVisibility(VISIBLE);
        }else {
            imvError.setVisibility(GONE);
        }

//        imvError.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
//        imvError.setColorFilter(ContextCompat.getColor(context, R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);
        tvError.setVisibility(VISIBLE);
        tvErrorDetail.setVisibility(VISIBLE);
        tvErrorDetail.setText(error);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void setNormal(String title){
        rlContent.setBackgroundResource(R.drawable.bg_edt_white);
        imvError.setVisibility(INVISIBLE);
        if(isTypePass){
            imvError.setVisibility(VISIBLE);
            imvError.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye_open));
            if(hiddenPass){
                imvError.setColorFilter(ContextCompat.getColor(context, R.color.txt_common), android.graphics.PorterDuff.Mode.SRC_IN);
            }else {
                imvError.setColorFilter(ContextCompat.getColor(context, R.color.blue), android.graphics.PorterDuff.Mode.SRC_IN);
            }
        }else{
            imvError.setVisibility(GONE);
        }
        tvError.setVisibility(VISIBLE);
        tvErrorDetail.setVisibility(GONE);
//        tvError.setTextColor(getResources().getColor(R.color.txt_common));
        tvError.setText(title);

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setInputType(int type){
        switch (type){
            case 0:
                edtContent.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
            case 1:
                edtContent.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case 2:
                edtContent.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case 3:
                edtContent.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case 4:
                edtContent.setInputType(InputType.TYPE_NULL);
                break;
        }

        if(type == 1){
            imvError.setVisibility(VISIBLE);
            isTypePass = true;
            edtContent.setTransformationMethod(PasswordTransformationMethod.getInstance());
            imvError.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye_open));
            imvError.setOnClickListener(view -> {
                if(hiddenPass){
                    edtContent.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imvError.setColorFilter(ContextCompat.getColor(context, R.color.blue), android.graphics.PorterDuff.Mode.SRC_IN);
                    imvError.setVisibility(VISIBLE);
                }else {
                    edtContent.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    imvError.setColorFilter(ContextCompat.getColor(context, R.color.txt_common), android.graphics.PorterDuff.Mode.SRC_IN);
                }
                hiddenPass = !hiddenPass;
                edtContent.setSelection(edtContent.getText().length());
            });
        }else {
            imvError.setVisibility(GONE);
            imvError.setOnClickListener(null);
        }
    }
}
