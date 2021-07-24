package com.dci.berts.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.dci.berts.R;

public class CustomTextarea extends LinearLayout {

    public EditText edtContent;
    public TextView tvError;
    public TextView tvErrorDetail;
    public RelativeLayout rlContent;
    public String title;
    Context context;


    public void setTitle(String title) {
        this.title = title;
        tvError.setText(title);
        edtContent.setHint(title);
    }


    public CustomTextarea(Context context) {
        super(context);
    }

    public CustomTextarea(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public CustomTextarea(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    public void initView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CustomTextarea, 0, 0);
        try {
            title = typedArray.getString(R.styleable.CustomTextarea_hinText);
        } finally {
            typedArray.recycle();
        }


        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setOrientation(LinearLayout.VERTICAL);
        View view = inflater.inflate(R.layout.custom_textarea, this, true);

        edtContent = view.findViewById(R.id.editText);
        tvError = view.findViewById(R.id.tvMailError);
        tvErrorDetail = view.findViewById(R.id.tvMailErrorDetail);
        rlContent = view.findViewById(R.id.rlEmail);

        setTitle(title);

        rlContent.setOnClickListener(view12 -> edtContent.requestFocus());

        edtContent.setOnFocusChangeListener((view1, b) -> {
            if (!b) {
                if (edtContent.getText().toString().isEmpty()) {
                    tvError.setVisibility(GONE);
                }
                edtContent.setHint(title);
            } else {
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
                if (charSequence.length() != 0) {
                    setNormal(title);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void setError(String error) {
        rlContent.setBackgroundResource(R.drawable.bg_edt_error);
        tvError.setVisibility(VISIBLE);
        tvErrorDetail.setVisibility(VISIBLE);
        tvErrorDetail.setText(error);
    }

    public void setNormal(String title) {
        rlContent.setBackgroundResource(R.drawable.bg_edt_white);
        tvError.setVisibility(VISIBLE);
        tvErrorDetail.setVisibility(GONE);
        tvError.setText(title);

    }
}
