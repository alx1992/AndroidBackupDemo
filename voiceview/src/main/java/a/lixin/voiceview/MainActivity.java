package a.lixin.voiceview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    TextView wbank_dr_btn_release;
    LinearLayout wbank_dr_btn_hold_layout;
    ImageView wbank_dr_btn_hold_anim;
    AnimationDrawable anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wbank_dr_btn_release =findViewById(R.id.wbank_dr_btn_release);
        wbank_dr_btn_hold_layout = findViewById(R.id.wbank_dr_btn_hold_layout);
        wbank_dr_btn_hold_anim = findViewById(R.id.wbank_dr_btn_hold_anim);

        anim = (AnimationDrawable) wbank_dr_btn_hold_anim.getBackground();

        wbank_dr_btn_release.setOnTouchListener((v, event) -> {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    wbank_dr_btn_hold_layout.setVisibility(View.VISIBLE);
                    wbank_dr_btn_release.setVisibility(View.GONE);
                    startAnim();
                    return true;
                case MotionEvent.ACTION_UP:
                    wbank_dr_btn_release.setVisibility(View.VISIBLE);
                    wbank_dr_btn_hold_layout.setVisibility(View.GONE);
                    stopAnim();
                    return true;
            }
            return true;
        });
    }

    private void stopAnim() {
        anim.selectDrawable(0);      //选择当前动画的第一帧，然后停止
        anim.stop();
    }

    private void startAnim() {
        anim.start();
    }
}