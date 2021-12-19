package a.lixin.voiceview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.LogUtils;

import static android.animation.ValueAnimator.INFINITE;
import static android.animation.ValueAnimator.REVERSE;


public class TestActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity";
    /**
     * 联合动画
     */
    private AnimatorSet rotationSet, // rotationSet 旋转动画
            scaleSet_1, scaleSet_2, scaleSet_3;// 1 2 3 不通的缩放动画
    private RelativeLayout wbank_kftouxiang_rl;
    private ImageView inside_anim,outside_anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        wbank_kftouxiang_rl = findViewById(R.id.rl_layout);
        inside_anim = findViewById(R.id.inside_anim);
        outside_anim = findViewById(R.id.outside_anim);
        findViewById(R.id.button).setOnClickListener(v -> {
            inside_anim.post(new Runnable() {
                @Override
                public void run() {
                    startAnim(inside_anim,outside_anim,wbank_kftouxiang_rl);
                }
            });

        });
    }

    /**
     *  旋转和放大缩小动画
     * @param iv_inside 最内层iv，执行旋转动画
     * @param iv_outside    最外层iv，执行旋转动画
     * @param anim_root 根布局，执行缩放动画
     */
    public void startAnim(ImageView iv_inside, ImageView iv_outside,
                          RelativeLayout anim_root) {
        // 旋转动画
        ObjectAnimator rotationInside = ObjectAnimator.ofFloat(iv_inside, "rotation", 0f, 360f);
        rotationInside.setDuration(4000);
        rotationInside.setRepeatCount(INFINITE);
        rotationInside.setRepeatMode(REVERSE);
        rotationInside.setInterpolator(new LinearInterpolator());
        rotationInside.start();
        ObjectAnimator rotationOutside = ObjectAnimator.ofFloat(iv_outside, "rotation", 0f, -360f);
        rotationOutside.setDuration(4000);
        rotationOutside.setRepeatCount(-1);
        rotationOutside.setInterpolator(new LinearInterpolator());
        rotationOutside.start();
//        rotationSet = new AnimatorSet();
//        rotationSet.setInterpolator(new LinearInterpolator());
//        rotationSet.playTogether(rotationInside, rotationOutside);
//        rotationSet.addListener(new Animator.AnimatorListener() {
//
//            @Override
//            public void onAnimationStart(Animator animation) {
//                LogUtils.dTag(TAG, "旋转动画开始");
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                LogUtils.dTag(TAG, "旋转动画结束");
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                LogUtils.dTag(TAG, "旋转动画取消");
//                rotationSet.end();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//                LogUtils.dTag(TAG, "旋转动画重复");
//            }
//        });
//        rotationSet.start();
//        // 放大缩小动画
//        ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(anim_root, "scaleX", 1, 1.16f, 1);
//        scaleX1.setDuration(2400);
//        scaleX1.setInterpolator(new LinearInterpolator());
//        ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(anim_root, "scaleY", 1, 1.16f, 1);
//        scaleY1.setDuration(2400);
//        scaleY1.setInterpolator(new LinearInterpolator());
//
//        ObjectAnimator scaleX2 = ObjectAnimator.ofFloat(anim_root, "scaleX", 1, 1.2f, 1);
//        scaleX2.setDuration(2400);
//        scaleX2.setInterpolator(new LinearInterpolator());
//        ObjectAnimator scaleY2 = ObjectAnimator.ofFloat(anim_root, "scaleY", 1, 1.2f, 1);
//        scaleY2.setDuration(2400);
//        scaleY2.setInterpolator(new LinearInterpolator());
//
//        ObjectAnimator scaleX3 = ObjectAnimator.ofFloat(anim_root, "scaleX", 1, 1.2f, 1);
//        scaleX3.setDuration(3200);
//        scaleX3.setInterpolator(new LinearInterpolator());
//        ObjectAnimator scaleY3 = ObjectAnimator.ofFloat(anim_root, "scaleY", 1, 1.2f, 1);
//        scaleY3.setDuration(3200);
//        scaleY3.setInterpolator(new LinearInterpolator());
//
//        scaleSet_1 = new AnimatorSet();
//        scaleSet_1.playTogether(scaleX1, scaleY1);
//        scaleSet_1.addListener(new Animator.AnimatorListener() {
//            boolean cancelState = false;
//
//            @Override
//            public void onAnimationStart(Animator animation) {
//                LogUtils.dTag(TAG, "动画scaleSet_1开始，time = "+ System.currentTimeMillis());
//                cancelState = false;
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                LogUtils.dTag(TAG, "动画scaleSet_1结束");
//                if (!cancelState) {
//                    LogUtils.dTag(TAG, "开始播放动画 scaleSet_2");
//                    scaleSet_2.start();
//                }else {
//                    LogUtils.dTag(TAG,"动画scaleSet_1已取消");
//                }
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                LogUtils.dTag(TAG, "动画scaleSet_1取消");
//                cancelState = true;
//                scaleSet_1.end();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//                LogUtils.dTag(TAG, "动画scaleSet_1重复");
//            }
//        });
//        scaleSet_2 = new AnimatorSet();
//        scaleSet_2.playTogether(scaleX2, scaleY2);
//        scaleSet_2.addListener(new Animator.AnimatorListener() {
//            boolean cancelState = false;
//
//            @Override
//            public void onAnimationStart(Animator animation) {
//                LogUtils.dTag(TAG, "动画scaleSet_2开始，time = "+ System.currentTimeMillis());
//                cancelState = false;
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                LogUtils.dTag(TAG, "动画scaleSet_2结束");
//                if (!cancelState) {
//                    LogUtils.dTag(TAG, "开始播放动画 scaleSet_3");
//                    scaleSet_3.start();
//                }else {
//                    LogUtils.dTag(TAG,"动画scaleSet_2已取消");
//                }
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                LogUtils.dTag(TAG, "动画scaleSet_2取消");
//                cancelState = true;
//                scaleSet_2.end();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//                LogUtils.dTag(TAG, "动画scaleSet_2重复");
//            }
//        });
//        scaleSet_3 = new AnimatorSet();
//        scaleSet_3.playTogether(scaleX3, scaleY3);
//        scaleSet_3.addListener(new Animator.AnimatorListener() {
//            boolean cancelState = false;
//
//            @Override
//            public void onAnimationStart(Animator animation) {
//                LogUtils.dTag(TAG, "动画scaleSet_3开始，time = "+ System.currentTimeMillis());
//                cancelState = false;
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                LogUtils.dTag(TAG, "动画scaleSet_3结束");
//                if (!cancelState) {
//                    LogUtils.dTag(TAG, "开始播放动画 scaleSet_1");
//                    scaleSet_1.start();
//                }else {
//                    LogUtils.dTag(TAG,"动画scaleSet_3已取消");
//                }
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                LogUtils.dTag(TAG, "动画scaleSet_3取消");
//                cancelState = true;
//                scaleSet_3.end();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//                LogUtils.dTag(TAG, "动画scaleSet_3重复");
//            }
//        });
//        LogUtils.dTag(TAG, "开始播放动画 scaleSet_1 ");
//        scaleSet_1.start();
    }

    public void stopAnim(ImageView iv_inside, ImageView iv_outside,
                         RelativeLayout anim_root) {

        if (scaleSet_1 != null) {
            LogUtils.dTag(TAG, "取消缩放动画");
            scaleSet_1.cancel();
            scaleSet_1.removeAllListeners();
        }
        if (scaleSet_2 != null) {
            LogUtils.dTag(TAG, "取消缩放动画");
            scaleSet_2.cancel();
            scaleSet_2.removeAllListeners();
        }
        if (scaleSet_3 != null) {
            LogUtils.dTag(TAG, "取消缩放动画");
            scaleSet_3.cancel();
            scaleSet_3.removeAllListeners();
        }

        if (rotationSet != null) {
            LogUtils.dTag(TAG, "取消旋转动画");
            rotationSet.cancel();
            rotationSet.removeAllListeners();
        }
        if (iv_inside.getAnimation() != null) {
            iv_inside.clearAnimation();
        }
        if (iv_outside.getAnimation() != null) {
            iv_outside.clearAnimation();
        }
        if (anim_root.getAnimation() != null) {
            anim_root.clearAnimation();
        }
    }

    @Override
    protected void onDestroy() {
        stopAnim(inside_anim,outside_anim,wbank_kftouxiang_rl);
        super.onDestroy();
    }
}