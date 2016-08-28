package com.jacksen.sharelibrary.wx;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;

import com.jacksen.sharelibrary.BaseShareHandler;
import com.jacksen.sharelibrary.PlatformConfigHelper;
import com.jacksen.sharelibrary.R;
import com.jacksen.sharelibrary.ShareListener;
import com.jacksen.sharelibrary.core.ShareConstants;
import com.jacksen.sharelibrary.core.ShareStatus;
import com.jacksen.sharelibrary.exception.ConfigErrorException;
import com.jacksen.sharelibrary.exception.InvalidParamException;
import com.jacksen.sharelibrary.exception.ShareException;
import com.jacksen.sharelibrary.exception.UnsupportedOperateException;
import com.jacksen.sharelibrary.util.BitmapUtil;
import com.jacksen.sharelibrary.util.ConstUtil;
import com.jacksen.sharelibrary.wx.param.ShareImageParam;
import com.jacksen.sharelibrary.wx.param.ShareMusicParam;
import com.jacksen.sharelibrary.wx.param.ShareTextParam;
import com.jacksen.sharelibrary.wx.param.ShareVideoParam;
import com.jacksen.sharelibrary.wx.param.ShareWebPageParam;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by Admin on 2016/8/24.
 */

public abstract class BaseWXShareHandler extends BaseShareHandler {

    private String appId;
    private IWXAPI iwxapi;

    public BaseWXShareHandler(Context context) {
        super(context);
        //
//        IntentFilter intentFilter = new IntentFilter(ShareConstants.ACTION_SHARE_RESULT);
//        context.registerReceiver(shareResultReceiver, intentFilter);
    }


    @Override
    protected void checkConfig() throws Exception {
        appId = PlatformConfigHelper.getAppId();
        if (TextUtils.isEmpty(appId)) {
            throw new ConfigErrorException(context.getString(R.string.error_wx_app_id_invalid));
        }
    }

    @Override
    protected void initShare() throws ShareException {
        iwxapi = WXAPIFactory.createWXAPI(context, appId, true);
        if (iwxapi.isWXAppInstalled()) {
            iwxapi.registerApp(appId);
        } else {
            getShareListener().onError(context.getString(R.string.error_wx_no_platform));
            throw new ConfigErrorException(context.getString(R.string.error_wx_no_platform), ShareStatus.StatusCode.CODE_SHARE_ERROR_NOT_INSTALL);
        }
    }

    @Override
    protected void checkPlatform() throws ShareException {
        if (SendMessageToWX.Req.WXSceneTimeline == getShareType()) {
            int wxVersion = iwxapi.getWXAppSupportAPI();
            if (wxVersion < ConstUtil.MOMENT_SUPPORTED_VERSION) {
                getShareListener().onError(context.getString(R.string.error_wx_unsupported_version));
                throw new UnsupportedOperateException(context.getString(R.string.error_wx_unsupported_version));
            }
        }
    }

    @Override
    protected void shareText(ShareTextParam textParam) throws ShareException {
        WXTextObject textObject = new WXTextObject();
        textObject.text = textParam.getContent(); // 分享的文本内容

        // check args
        if (!textObject.checkArgs()) {
            throw new InvalidParamException(context.getString(R.string.error_wx_param_text_invalid));
        }

        WXMediaMessage textMsg = new WXMediaMessage();
        textMsg.mediaObject = textObject;
        textMsg.description = textParam.getContent(); // description

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction();
        req.message = textMsg;
        req.scene = getShareType();

        boolean result = iwxapi.sendReq(req);
        judgeShareResult(result);
    }

    @Override
    protected void shareImage(ShareImageParam imageParam) throws ShareException {
        WXImageObject imageObject = new WXImageObject(imageParam.getBitmap());


        if (!imageObject.checkArgs()) {
            throw new InvalidParamException(context.getString(R.string.error_wx_param_text_invalid));
        }

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imageObject;
        Bitmap thumb = Bitmap.createScaledBitmap(imageParam.getBitmap(), 150, 150, true);
        msg.setThumbImage(thumb);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction();
        req.message = msg;
        req.scene = getShareType();

        boolean result = iwxapi.sendReq(req);
        judgeShareResult(result);

    }

    @Override
    protected void shareMusic(ShareMusicParam musicParam) throws ShareException {

    }

    @Override
    protected void shareVideo(ShareVideoParam videoParam) throws ShareException {

    }

    @Override
    protected void shareWebPage(ShareWebPageParam webPageParam) throws ShareException {
        if (TextUtils.isEmpty(webPageParam.getTargetUrl())) {
            throw new InvalidParamException(context.getString(R.string.error_wx_param_url_invalid));
        }
        WXWebpageObject webpageObject = new WXWebpageObject();
        webpageObject.webpageUrl = webPageParam.getTargetUrl(); // 网页url

        WXMediaMessage message = new WXMediaMessage(webpageObject);
        message.title = webPageParam.getTitle(); // 网页标题
        message.description = webPageParam.getDescription(); // 网页描述
        message.thumbData = BitmapUtil.bitmapToByteArray(webPageParam.getThumbBitmap());

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.message = message;
        req.transaction = buildTransaction();
        req.scene = getShareType(); // 分享到朋友或者朋友圈

        boolean result = iwxapi.sendReq(req);
        judgeShareResult(result);
    }


    /**
     * 判断发送分享的结果
     *
     * @param result
     * @throws ShareException
     */
    private void judgeShareResult(boolean result) throws ShareException {
        if (!result) {
            getShareListener().onError(context.getString(R.string.error_wx_share_failure));
            throw new ShareException(context.getString(R.string.error_wx_share_failure));
        }
    }


    @Override
    public void release() {
        if (context != null) {
            context.unregisterReceiver(shareResultReceiver);
        }
        super.release();
    }

    protected abstract int getShareType();


    public void onReq(BaseReq baseReq) {
    }

    /**
     * 回调处理
     *
     * @param baseResp
     */
    public void onResp(BaseResp baseResp) {
        ShareListener shareListener = getShareListener();
        if (shareListener == null) {
            return;
        }
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                shareListener.onSuccess();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                shareListener.onCancel();
                break;
            case BaseResp.ErrCode.ERR_SENT_FAILED:
                shareListener.onError(baseResp.errStr);
                break;
        }
    }

    private BroadcastReceiver shareResultReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ShareListener shareListener = getShareListener();
            if (intent == null || shareListener == null) {
                return;
            }
            int statusCode = intent.getIntExtra(ShareConstants.STATUS_CODE, -1);
            switch (statusCode) {
                case ShareStatus.StatusCode.CODE_SHARE_SUCCESS:
                    shareListener.onSuccess();
                    break;
                case ShareStatus.StatusCode.CODE_SHARE_CANCEL:
                    shareListener.onCancel();
                    break;
                case ShareStatus.StatusCode.CODE_SHARE_ERROR:
                    shareListener.onError("");
                    break;
            }

        }
    };
}
