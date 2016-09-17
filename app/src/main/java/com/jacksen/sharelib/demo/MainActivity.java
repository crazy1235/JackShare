package com.jacksen.sharelib.demo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jacksen.sharelibrary.JackShare;
import com.jacksen.sharelibrary.ShareListener;
import com.jacksen.sharelibrary.anno.PlatformScope;
import com.jacksen.sharelibrary.core.AuthListener;
import com.jacksen.sharelibrary.core.Platform;
import com.jacksen.sharelibrary.param.ShareImageParam;
import com.jacksen.sharelibrary.param.ShareMusicParam;
import com.jacksen.sharelibrary.param.ShareTextParam;
import com.jacksen.sharelibrary.param.ShareWebPageParam;

import java.io.File;

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
    @BindView(R.id.sina_btn)
    Button sinaBtn;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    @BindView(R.id.login_qq_iv)
    ImageView loginQqIv;
    @BindView(R.id.login_wx_iv)
    ImageView loginWxIv;
    @BindView(R.id.login_sina_iv)
    ImageView loginSinaIv;

    private int shareType = 0;

    private String localImgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        JackShare.init(this);

        String path = Environment.getExternalStorageDirectory().getPath();
        File file = new File(path + File.separator + "temp.jpg");
        if (file.exists()) {
            localImgPath = file.getAbsolutePath();
        } else {
            Toast.makeText(this, "请配置一张图片", Toast.LENGTH_SHORT).show();
        }

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
                ShareTextParam shareTextParam = new ShareTextParam("问句天几高心中志比天更高！");
                JackShare.share(this, Platform.WX_SESSION, shareTextParam, shareListener);

                break;
            case 1:
                ShareImageParam imageParam = new ShareImageParam();
//                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//                imageParam.setBitmap(bitmap);
                imageParam.setLocalImgPath(localImgPath);
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

    @OnClick(R.id.qq_btn)
    public void shareToQQ() {
        switch (shareType) {
            case 1:
                ShareImageParam imageParam = new ShareImageParam();
                imageParam.setLocalImgPath(localImgPath);
                JackShare.share(this, Platform.QQ, imageParam, shareListener);
                break;
            case 2:
                ShareMusicParam musicParam = new ShareMusicParam();
                musicParam.setTitle("好听的标题");
                musicParam.setTargetUrl("http://www.baidu.com");
                musicParam.setAudioUrl("http://mp3.haoduoge.com/s/2016-09-16/1473999813.mp3");
                musicParam.setContent("ssssss");
                musicParam.setImgPath(localImgPath);
                JackShare.share(this, Platform.QQ, musicParam, shareListener);
                break;
            case 4:
                ShareTextParam textParam = new ShareTextParam("123");
                JackShare.share(this, Platform.QQ, textParam, shareListener);
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.qzone_btn)
    public void shareToQZone(){
        switch (shareType){
            case 4:
                ShareWebPageParam webPageParam = new ShareWebPageParam("1111","2222");
                webPageParam.setTargetUrl("http://blog.csdn.net/crazy1235");
                ShareImageParam imageParam = new ShareImageParam();
                imageParam.setNetImgPath("http://img3.douban.com/lpic/s3635685.jpg");
                webPageParam.setImageParam(imageParam);
                JackShare.share(this, Platform.QZone, webPageParam, shareListener);
                break;
        }
    }

    /**
     * 分享监听回调
     */
    private ShareListener shareListener = new ShareListener() {
        @Override
        public void onPreShare(@PlatformScope String platform) {
            Toast.makeText(MainActivity.this, platform + "-准备开始分享...", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSuccess(@PlatformScope String platform) {
            Toast.makeText(MainActivity.this, platform + "-分享成功...", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(@PlatformScope String platform, String errMsg) {
            Toast.makeText(MainActivity.this, platform + "-分享失败... -- " + errMsg, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(@PlatformScope String platform) {
            Toast.makeText(MainActivity.this, platform + "-分享取消...", Toast.LENGTH_SHORT).show();
        }

    };

    @OnClick(R.id.login_qq_iv)
    public void loginWithQQ() {
        JackShare.login(this, Platform.QQ, authListener);
    }

    AuthListener authListener = new AuthListener() {
        @Override
        public void onPreAuth(@PlatformScope String platform) {

        }

        @Override
        public void onAuthSuccess(@PlatformScope String platform, String info) {

        }

        @Override
        public void onAuthError(@PlatformScope String platform, String errMsg) {

        }

        @Override
        public void onAuthCancel(@PlatformScope String platform) {

        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        JackShare.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
