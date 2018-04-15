package com.example.administrator.verificationcodedemo;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vondear.rxtools.view.RxCaptcha;

public class MainActivity extends AppCompatActivity {

    private TextView refreshCodeTextView;
    private EditText enterConfirmationCodeEditText;
    private ImageView randomVerificationCodeImageView;
    private Button confirmButton;
    private Context context;
    public static String currentConfirmationCode = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        randomVerificationCodeImageView = findViewById(R.id.randomVerificationCodeImageView);
        refreshCodeTextView = findViewById(R.id.refreshCodeTextView);
        refreshCodeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxCaptcha.build()
                        .backColor(0xff000000)
                        .codeLength(4)
                        .type(RxCaptcha.TYPE.NUMBER)
                        .fontSize(55)
                        // .lineNumber(10)                                                          // 增加辨識難度
                        .size(200, 70)
                        .into(randomVerificationCodeImageView);
                currentConfirmationCode = RxCaptcha.build().getCode();
            }
        });

        new Handler().postDelayed(new Runnable() {
            public void run() {
                refreshCodeTextView.performClick();
            }
        }, 200);

        enterConfirmationCodeEditText = findViewById(R.id.enterConfirmationCodeEditText);
        confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /** equals(): 判断字符串是否相等 */
                if (enterConfirmationCodeEditText.getText().toString().equals(currentConfirmationCode)) {
                    Toast.makeText(context, "結帳中...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "請重新確認安全驗證碼 是否輸入正確", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
