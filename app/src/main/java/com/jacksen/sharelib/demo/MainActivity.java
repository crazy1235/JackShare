package com.jacksen.sharelib.demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jacksen.sharelib.demo.util.Constants;
import com.jacksen.sharelibrary.JackShare;
import com.jacksen.sharelibrary.ShareListener;
import com.jacksen.sharelibrary.anno.PlatformScope;
import com.jacksen.sharelibrary.core.Platform;
import com.jacksen.sharelibrary.wx.param.ShareImageParam;
import com.jacksen.sharelibrary.wx.param.ShareTextParam;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text_rbtn)
    RadioButton textRbtn;
    @BindView(R.id.image_rbtn)
    RadioButton imageRbtn;
    @BindView(R.id.music_rbtn)
    RadioButton musicRbtn;
    @BindView(R.id.video_rbtn)
    RadioButton videoRbtn;
    @BindView(R.id.web_rbtn)
    RadioButton webRbtn;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.wechat_btn)
    Button wechatBtn;
    @BindView(R.id.moment_btn)
    Button momentBtn;
    @BindView(R.id.favorite_btn)
    Button favoriteBtn;
    @BindView(R.id.qq_btn)
    Button qqBtn;
    @BindView(R.id.qzone_btn)
    Button qzoneBtn;
    @BindView(R.id.qq_weibo_btn)
    Button qqWeiboBtn;
    @BindView(R.id.sina_btn)
    Button sinaBtn;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    private int shareType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        JackShare.init(this);

        JackShare.init(Constants.WX_APP_ID, Constants.WX_APP_SECRET);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.text_rbtn:
                        shareType = 0;
                        break;
                    case R.id.image_rbtn:
                        shareType = 1;
                        break;
                    case R.id.music_rbtn:
                        shareType = 2;
                        break;
                    case R.id.video_rbtn:
                        shareType = 3;
                        break;
                    case R.id.web_rbtn:
                        shareType = 4;
                        break;
                    default:
                        shareType = 0;
                        break;
                }
            }
        });
    }

    @OnClick(R.id.wechat_btn)
    public void shareToWechat() {
        switch (shareType) {
            case 0:

                ShareTextParam shareTextParam = new ShareTextParam("不要再逗留，人心太拥挤");
                JackShare.share(this, Platform.WX_SESSION, shareTextParam, shareListener);

                break;
            case 1:
                ShareImageParam imageParam = new ShareImageParam();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                imageParam.setBitmap(bitmap);
                JackShare.share(MainActivity.this, Platform.WX_SESSION, imageParam, shareListener);
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            default:
                break;
        }
    }

    @OnClick(R.id.moment_btn)
    public void shareToMoment() {
        switch (shareType) {
            case 0:

                ShareTextParam shareTextParam = new ShareTextParam("路还有汗流\n梦还没腐朽\n命运到最后");
                JackShare.share(this, Platform.WX_MOMENT, shareTextParam, shareListener);

                break;
            case 1:
                ShareImageParam imageParam = new ShareImageParam();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.logo);
                imageParam.setBitmap(bitmap);
                JackShare.share(MainActivity.this, Platform.WX_MOMENT, imageParam, shareListener);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }
    }

    /**
     * 分享监听回调
     */
    private ShareListener shareListener = new ShareListener() {
        @Override
        public void onPreShare(@PlatformScope String platform) {
            Toast.makeText(MainActivity.this, "准备开始分享...", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSuccess(@PlatformScope String platform) {
            Toast.makeText(MainActivity.this, "分享成功...", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(@PlatformScope String platform, String errMsg) {
            Toast.makeText(MainActivity.this, "分享失败... -- " + errMsg, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(@PlatformScope String platform) {
            Toast.makeText(MainActivity.this, "分享取消...", Toast.LENGTH_SHORT).show();
        }
       
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
