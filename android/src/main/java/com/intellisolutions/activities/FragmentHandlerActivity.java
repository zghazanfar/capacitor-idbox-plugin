package com.intellisolutions.activities;

import static com.intellisolutions.capacitor.plugin.IdboxPluginPlugin.requestId;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

//import com.facebook.react.bridge.ReadableType;
//import com.facebook.react.bridge.WritableMap;
import com.intellisolutions.idbox.base.Const;
import com.intellisolutions.idbox.views.CameraFragment;
import com.intellisolutions.idbox.views.PreviewMediaFragment;
import com.intellisolutions.capacitor.plugin.R;
import org.apache.cordova.Idbox_Plugin.util.ArrayUtil;
import org.apache.cordova.Idbox_Plugin.util.LayoutParameters;
import org.apache.cordova.Idbox_Plugin.util.SubHeaderText;
import org.apache.cordova.Idbox_Plugin.util.MapUtil;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*
  The objective of this activity is to handle the multiple Fragments.
  we have to add/remove fragments that based on performed tasks.
  For example, open cameraFragment, PreviewMediaFragment etc.
*/
public class FragmentHandlerActivity extends AppCompatActivity implements CameraFragment.ICameraFragmentListener,
  PreviewMediaFragment.IPreviewMediaFragmentListener{

  private CameraFragment     mCameraFragment;
  private PreviewMediaFragment mPreviewMediaFragment;
  private CameraFragment.Builder cameraBuilder;
  private PreviewMediaFragment.Builder previewBuilder;
  private String mBaseUrl, mHawkId, mHawkKey, mReferenceId;
  private ArrayList<String> mFileNames;
  private JSONObject jsonObj;

  private LinearLayout.LayoutParams layoutParams;

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fragment_handler);
    setCameraBuilderOptions();
  }
  @RequiresApi(api = Build.VERSION_CODES.O)
  private void setCameraBuilderOptions(){
    try {
      JSONObject json = new JSONObject(getIntent().getStringExtra(getString(R.string.CAMERA_OPTIONS_JSON_KEY)));
      jsonObj = json;

      // Create Camera.Builder Object by setting up hawk id and hawk key
      if(jsonObj.has(getResources().getString(R.string.ONBOARDING_OPTIONS_HAWKKEY))
        && jsonObj.has(getResources().getString(R.string.ONBOARDING_OPTIONS_HAWKID))) {
//        Log.e("IdBoxModule URL", " IF");
        cameraBuilder = new CameraFragment.Builder(jsonObj.getString(getString(R.string.ONBOARDING_OPTIONS_HAWKID)),
          jsonObj.getString(getString(R.string.ONBOARDING_OPTIONS_HAWKKEY)));

        setCameraFragmentBuilderOptions();
      }
    } catch (JSONException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  public void setCameraFragmentBuilderOptions() {
    try {
      mHawkId = jsonObj.getString(getString(R.string.ONBOARDING_OPTIONS_HAWKID));
      mHawkKey = jsonObj.getString(getString(R.string.ONBOARDING_OPTIONS_HAWKKEY));
      mBaseUrl = jsonObj.getString(getString(R.string.ONBOARDING_OPTIONS_URL));
      cameraBuilder.setRequestId(requestId);
      //Post instantiate camera builder. Now Set builder options based on receiving keys options getting from RN applications.
//      if(jsonObj.has(getResources().getString(R.string.ONBOARDING_OPTIONS_REQUESTID))) {
//        mReferenceId = jsonObj.getString(getString(R.string.ONBOARDING_OPTIONS_REQUESTID));
  //      Log.e("RequestID",requestId);
//      }
//      if(jsonObj.has(getResources().getString(R.string.ONBOARDING_OPTIONS_URL))){
        cameraBuilder.setUrl(jsonObj.getString(getString(R.string.ONBOARDING_OPTIONS_URL)));
//        Log.e("URL","");
//      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_FONT_NAME))){
        cameraBuilder.fontName(jsonObj.getString(getString(R.string.CAMERA_OPTIONS_FONT_NAME)));
//        Log.e("FontName",jsonObj.getString(getString(R.string.CAMERA_OPTIONS_FONT_NAME)));
      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_FONT_SIZE))){
        cameraBuilder.fontSize(jsonObj.getInt(getString(R.string.CAMERA_OPTIONS_FONT_SIZE)));
//        Log.e("FontSize",jsonObj.getInt(getString(R.string.CAMERA_OPTIONS_FONT_SIZE))+"");
      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_FONT_COLOR))){
        int fontColor = parseHexColorCode(jsonObj.getString(getString(R.string.CAMERA_OPTIONS_FONT_COLOR)));
        cameraBuilder.fontColor(fontColor);
//        Log.e("FontColor",jsonObj.getString(getString(R.string.CAMERA_OPTIONS_FONT_COLOR))+"");
      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_AUTO_CAPTURE_TIMEOUT))){
        cameraBuilder.setAutoCaptureTimeOut(jsonObj.getInt(getString(R.string.CAMERA_OPTIONS_AUTO_CAPTURE_TIMEOUT)));
       // Log.DEBUG(jsonObj.getInt(getString(R.string.CAMERA_OPTIONS_AUTO_CAPTURE_TIMEOUT)));
      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_BACK_VISIBILITY))){
        cameraBuilder.setBackVisibility(jsonObj.getBoolean(getString(R.string.CAMERA_OPTIONS_BACK_VISIBILITY)));
//        Log.e("BackVisibility","");
      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_CORNER_PATH_EFFECT))){
        cameraBuilder.setCornersFocusFrame(jsonObj.getBoolean(getString(R.string.CAMERA_OPTIONS_CORNER_PATH_EFFECT)));
//        Log.e("CornerPathEffect","");
      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_DASH_PATH_EFFECT))){
        cameraBuilder.setDashedOutline(jsonObj.getBoolean(getString(R.string.CAMERA_OPTIONS_DASH_PATH_EFFECT)));
//        Log.e("DashPathEffect","");
      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_FLASH_VISIBILITY))){
        cameraBuilder.setFlashVisibility(jsonObj.getBoolean(getString(R.string.CAMERA_OPTIONS_FLASH_VISIBILITY)));
//        Log.e("FlashVisibility","");
      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_FOCUS_CONTINUE_PICTURE))){
        cameraBuilder.setFocusContinuePicture(jsonObj.getBoolean(getString(R.string.CAMERA_OPTIONS_FOCUS_CONTINUE_PICTURE)));
//        Log.e("FocusContinuePicture","");
      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_FOOTER_TEXT))){
          cameraBuilder.setFooterText(jsonObj.getString(getString(R.string.CAMERA_OPTIONS_FOOTER_TEXT)));
      }
      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_FOOTER_TEXT_LINES))){
        cameraBuilder.setFooterText(ArrayUtil.toStringArray(jsonObj.getJSONArray(getString(R.string.CAMERA_OPTIONS_FOOTER_TEXT_LINES))));
        Log.e("FooterTextLines","");
      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_FRONT_BOTTOM_TEXT))){
            cameraBuilder.setFrontFrameBottomText(jsonObj.getString(getString(R.string.CAMERA_OPTIONS_FRONT_BOTTOM_TEXT)));
        //        Log.e("FrontBottomText","");
      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_BACK_BOTTOM_TEXT))){
          cameraBuilder.setBackFrameBottomText(jsonObj.getString(getString(R.string.CAMERA_OPTIONS_BACK_BOTTOM_TEXT)));
      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_HEADER_TEXT))){

          cameraBuilder.setHeaderText(jsonObj.getString(getString(R.string.CAMERA_OPTIONS_HEADER_TEXT)));
      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_MAX_FRAMES))){
        cameraBuilder.setMaxFrames(jsonObj.getInt(getString(R.string.CAMERA_OPTIONS_MAX_FRAMES)));
//        Log.e("MaxFrames","");
      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_SHOOT_MODE))){
        cameraBuilder.setMode(jsonObj.getString(getString(R.string.CAMERA_OPTIONS_SHOOT_MODE)));
//        Log.e("ShootMode",jsonObj.getString(getString(R.string.CAMERA_OPTIONS_SHOOT_MODE)));
      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_OVERLAY_COLOR))){
        int fontColor = parseHexColorCode(jsonObj.getString(getString(R.string.CAMERA_OPTIONS_OVERLAY_COLOR)));
        cameraBuilder.setOverlayColor(fontColor);
//        Log.e("OverlayColor","");
      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_OVERLAY_ALPHA))){
        cameraBuilder.setOverlayTransparency(jsonObj.getInt(getString(R.string.CAMERA_OPTIONS_OVERLAY_ALPHA)));
//        Log.e("OverlayAlpha","");
      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_ROUNDED_FOCUS_FRAME))){
        cameraBuilder.setRoundedFocusFrame(jsonObj.getBoolean(getString(R.string.CAMERA_OPTIONS_ROUNDED_FOCUS_FRAME)));
//        Log.e("RoundedFocusFrame","");
      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_SHUTTER_BUTTON_LIGHT))){
        cameraBuilder.setShutterButtonLight(jsonObj.getBoolean(getString(R.string.CAMERA_OPTIONS_SHUTTER_BUTTON_LIGHT)));
//        Log.e("ShutterButtonLight","");

      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_THUMBNAIL_VISIBILITY))){
        cameraBuilder.setThumbnailVisibility(jsonObj.getBoolean(getString(R.string.CAMERA_OPTIONS_THUMBNAIL_VISIBILITY)));
//        Log.e("ThumbnailVisibility","");

      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_VIDEO_TOKEN_LANG))){
        cameraBuilder.setVideoTokenLang(jsonObj.getString(getString(R.string.CAMERA_OPTIONS_VIDEO_TOKEN_LANG)));
//        Log.e("VideoTokenLang","");
      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_META_DATA))){
        cameraBuilder.setMetadata(jsonObj.getString(getString(R.string.CAMERA_OPTIONS_META_DATA)));
//        Log.e("MetaData","");

      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_FRONT_RECTANGLE_ROTATED))){
        cameraBuilder.setFrontRectangleRotated(jsonObj.getBoolean(getString(R.string.CAMERA_OPTIONS_FRONT_RECTANGLE_ROTATED)));
//        Log.e("FrontRectangleRotated","");

      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_BACK_RECTANGLE_ROTATED))){
        cameraBuilder.setBackRectangleRotated(jsonObj.getBoolean(getString(R.string.CAMERA_OPTIONS_BACK_RECTANGLE_ROTATED)));
//        Log.e("BackRectangleRotated","");

      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_FRONT_SUB_HEADER_TEXT))){
//        Log.e("FrontSubHeaderText","");
//       //SubHeaderText subHeaderTextObj = MapUtil.toSubHeader(this,jsonObj.optJSONObject(getString(R.string.CAMERA_OPTIONS_FRONT_SUB_HEADER_TEXT)));
//        int drawableResId = getDrawableResId(subHeaderTextObj.getDrawable());
////        int resId = getResources().getIdentifier("src_assets_tom_gerry", "drawable", getPackageName());
//        if(subHeaderTextObj.getSubHeaderTextArr() != null && subHeaderTextObj.getSubHeaderTextArr().length > 0)
//          cameraBuilder.setFrontSubHeaderText(subHeaderTextObj.getSubHeaderTextArr(), drawableResId);
//        else
//          cameraBuilder.setFrontSubHeaderText(subHeaderTextObj.getSubHeaderText(), drawableResId);
      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_BACK_SUB_HEADER_TEXT))){
//        Log.e("BackSubHeaderText","");
//        SubHeaderText subHeaderTextObj = MapUtil.toSubHeader(this,jsonObj.optJSONObject(getString(R.string.CAMERA_OPTIONS_BACK_SUB_HEADER_TEXT)));
//        int drawableResId = getDrawableResId(subHeaderTextObj.getDrawable());
//        if(subHeaderTextObj.getSubHeaderTextArr() != null && subHeaderTextObj.getSubHeaderTextArr().length > 0)
//          cameraBuilder.setBackSubHeaderText(subHeaderTextObj.getSubHeaderTextArr(), drawableResId);
//        else
//          cameraBuilder.setBackSubHeaderText(subHeaderTextObj.getSubHeaderText(), drawableResId);
      }

      if(jsonObj.has(getResources().getString(R.string.CAMERA_OPTIONS_FILES))){
//        Log.e("Files","");
        cameraBuilder.setFiles(ArrayUtil.toArrayAsList(jsonObj.optJSONArray(getString(R.string.CAMERA_OPTIONS_FILES))));
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override
  public void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    Handler mainHandler = new Handler(getApplicationContext().getMainLooper());
    Runnable myRunnable = new Runnable() {
      @Override
      public void run() {
        showCameraFragment();
      } // This is your code
    };
    mainHandler.post(myRunnable);
//    new Handler().postDelayed(new Runnable() {
//      public void run() {
//        // do something...
//      }
//    }, 100);
  }
  private void releaseCameraFragment() {
//    Log.e("releaseCameraFragment", "Called");
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.remove(mCameraFragment);
    ft.commit();
    mCameraFragment = null;
  }
  private void showCapturedPhotosFragment(String taskId, ArrayList<String> photos) throws JSONException {
    if (mCameraFragment!=null)
      releaseCameraFragment();

    previewBuilder = new PreviewMediaFragment.Builder(mHawkId, mHawkKey);
//    if(!TextUtils.isEmpty(mReferenceId) )
      previewBuilder.setRequestId(requestId);

    if(jsonObj.has(getResources().getString(R.string.PREVIEW_OPTIONS_HEADER_TEXT))) {
      Log.e("previewHeaderText", jsonObj.getString(getString(R.string.PREVIEW_OPTIONS_HEADER_TEXT)));
      previewBuilder.setHeaderText(jsonObj.getString(getString(R.string.PREVIEW_OPTIONS_HEADER_TEXT)));
    }
    if(jsonObj.has(getResources().getString(R.string.PREVIEW_OPTIONS_FRAME_BOTTOM_TEXT))) {
//      Log.e("previewFrameBottomText", jsonObj.getString(getString(R.string.PREVIEW_OPTIONS_FRAME_BOTTOM_TEXT)));
      previewBuilder.setFrameBottomText(jsonObj.getString(getString(R.string.PREVIEW_OPTIONS_FRAME_BOTTOM_TEXT)));
    }
    if(jsonObj.has(getResources().getString(R.string.PREVIEW_OPTIONS_UPLOAD_BUTTON_TEXT))) {
//      Log.e("uploadButtonText", jsonObj.getString(getString(R.string.PREVIEW_OPTIONS_UPLOAD_BUTTON_TEXT)));
      previewBuilder.setUploadButtonText(jsonObj.getString(getString(R.string.PREVIEW_OPTIONS_UPLOAD_BUTTON_TEXT)));
    }
    if(jsonObj.has(getResources().getString(R.string.PREVIEW_OPTIONS_RETAKE_BUTTON_TEXT))) {
//      Log.e("retakeButtonText", jsonObj.getString(getString(R.string.PREVIEW_OPTIONS_RETAKE_BUTTON_TEXT)));
      previewBuilder.setRetakeButtonText(jsonObj.getString(getString(R.string.PREVIEW_OPTIONS_RETAKE_BUTTON_TEXT)));
    }
    if(jsonObj.has(getResources().getString(R.string.PREVIEW_OPTIONS_FONT_NAME))) {
//      Log.e("previewFontName", jsonObj.getString(getString(R.string.PREVIEW_OPTIONS_FONT_NAME))+"");
      previewBuilder.fontName(jsonObj.getString(getString(R.string.PREVIEW_OPTIONS_FONT_NAME)));
    }
    if(jsonObj.has(getResources().getString(R.string.PREVIEW_OPTIONS_FONT_SIZE))) {
//      Log.e("previewFontSize", ""+jsonObj.getInt(getString(R.string.PREVIEW_OPTIONS_FONT_SIZE)));
      previewBuilder.fontSize(jsonObj.getInt(getString(R.string.PREVIEW_OPTIONS_FONT_SIZE)));
    }
    if(jsonObj.has(getResources().getString(R.string.PREVIEW_OPTIONS_FONT_COLOR))) {
//      Log.e("previewFontColor", "");
      int fontColor =parseHexColorCode(jsonObj.getString(getString(R.string.PREVIEW_OPTIONS_FONT_COLOR)));
      previewBuilder.fontColor(fontColor);
    }
    if(jsonObj.has(getResources().getString(R.string.PREVIEW_OPTIONS_API_LOGGING))) {
//      Log.e("previewApiCalling", ""+jsonObj.getBoolean(getString(R.string.PREVIEW_OPTIONS_API_LOGGING)));
      previewBuilder.withApiCallLogging(jsonObj.optBoolean(getString(R.string.PREVIEW_OPTIONS_API_LOGGING)));
    }
//   if(jsonObj.has(getResources().getString(R.string.PREVIEW_OPTIONS_PARAMS))) {
////     Log.e("Preview_param", "");
//     LayoutParameters layoutParameters = MapUtil.toLayoutParams(this,
//       jsonObj.optJSONObject(getString(R.string.PREVIEW_OPTIONS_PARAMS)));
//     layoutParams = new LinearLayout.LayoutParams(layoutParameters.getWidth(), layoutParameters.getHeight());
//     previewBuilder.withLayoutParams(layoutParams);
//   }
    previewBuilder.setUrl(mBaseUrl);
    previewBuilder.setTaskId(taskId);
    previewBuilder.setFiles(photos);
    previewBuilder.setCallback(this);
    mPreviewMediaFragment = previewBuilder.build();


    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.replace(R.id.container, mPreviewMediaFragment,"pho");
    ft.commit();
  }

  private void releaseCapturedPhotosFragment() {
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.remove(mPreviewMediaFragment);
    ft.commit();
    mPreviewMediaFragment = null;
  }

  private void showCameraFragment() {
    if (mPreviewMediaFragment != null)
      releaseCapturedPhotosFragment();

    cameraBuilder.setCallback(this);
    mCameraFragment = cameraBuilder.build();
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.replace(R.id.container, mCameraFragment, "cam");
    ft.commit();
  }

  @Override
  public void onCancel(int i, String resultMessage) {
//    Log.d("onCameraCancel", "resultMessage " + resultMessage);
    ExitActivity("camera cancel", 99, RESULT_CANCELED, resultMessage);
  }

  @Override
  public void onCompleted(String taskId, ArrayList<String> files) {
//    Log.e("onCompleted", taskId);
    mFileNames = files;
    try {
      showCapturedPhotosFragment(taskId, files);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  private void ExitActivity(String taskId, int apiResult, int result, String resultMessage) {
    Intent data = new Intent();
    data.putExtra(getString(R.string.MAP_API_RESPONSE_TASK), taskId);
    data.putExtra(getString(R.string.API_RESULT_CODE), apiResult);
    data.putExtra(getString(R.string.RESULT_CODE), result);
    data.putExtra(getString(R.string.RESULT_MESSAGE), resultMessage);
    data.setData(Uri.parse(resultMessage));
    setResult(result, data);
    FragmentHandlerActivity.this.finish();
  }

  @Override
  public void onPreviewReTake(String s) {
    cameraBuilder.setFiles(null);
    showCameraFragment();
  }

  @Override
  public void onPreviewCancel(String taskId, int resultId, String resultMessage) {
//    Log.d("onPreviewCancel", "Called");
    ExitActivity(taskId, resultId, RESULT_OK, resultMessage);

  }

  @Override
  public void onPreviewCompleted(String taskId, int resultId, String resultMessage) {
//    Log.d("onPreviewCompleted", "Called");
    ExitActivity(taskId, resultId, RESULT_OK, resultMessage);
  }

  public int parseHexColorCode(String hexCode){
//    Log.e("HexCode", hexCode);
//    String hexColor = String.format("#%06X", (0xFFFFFF & getResources().getColor(R.color.grey)));
    int colorCodeInt = Color.parseColor(hexCode); // ResourcesCompat.getColor(getResources(), mHeaderText.FontColor,null);
//    Log.e("colorCodeInt", colorCodeInt+"");
    return colorCodeInt;
  }
  private int getDrawableResId(String drawableImageName){
    return getResources().getIdentifier( drawableImageName, "drawable", getPackageName());
  }
}
