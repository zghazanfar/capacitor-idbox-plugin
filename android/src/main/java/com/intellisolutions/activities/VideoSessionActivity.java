package com.intellisolutions.activities;

import static com.intellisolutions.capacitor.plugin.IdboxPluginPlugin.requestId;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.PersistableBundle;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.intellisolutions.idbox.views.VideoSessionFragment;
import com.intellisolutions.capacitor.plugin.R;


import org.json.JSONException;
import org.json.JSONObject;

public class VideoSessionActivity extends AppCompatActivity {

  private VideoSessionFragment mVideoSessionFragment;
  private JSONObject writableMap;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_video_session);

    setVideoSessionOptions();

  }
  private void setVideoSessionOptions(){
    try {
      JSONObject json = new JSONObject(getIntent().getStringExtra("VideoOptionsJson"));
      writableMap = json;

      // Create Video.Builder Object by setting up hawk id and hawk key
      String mHawkId = writableMap.getString("hawkId");
      String mHawkKey = writableMap.getString("hawkKey");
      String mBaseUrl = writableMap.getString("url");
//      String requestId = writableMap.getString(getString(R.string.ONBOARDING_OPTIONS_REQUESTID));
      String tokBoxSessionId = writableMap.getString("tokBoxSessionId");
      String tokBoxApiKey = writableMap.getString("tokBoxApiKey");
      String tokBoxToken = writableMap.getString("tokBoxToken");
      Log.d("VideoSessionActivity", "RequestId/" + requestId);
      Log.d("VideoSessionActivity", "mBaseUrl/" + mBaseUrl);
      Log.d("VideoSessionActivity", "mHawkKey/" + mHawkKey);
      Log.d("VideoSessionActivity", "mHawkId/" + mHawkId);
      Log.d("VideoSessionActivity", "TokBokSessionId/" + tokBoxSessionId);
      Log.d("VideoSessionActivity", "tokBoxApiKey/" + tokBoxApiKey);
      Log.d("VideoSessionActivity", "tokBoxToken/" + tokBoxToken);
      mVideoSessionFragment = new VideoSessionFragment.Builder(mHawkId, mHawkKey)
      .setUrl(mBaseUrl)
      .setVideoSessionConfig(tokBoxSessionId, tokBoxApiKey, tokBoxToken)
      .setRequestId(requestId)
      .setCallback(videoListener)
      .build();

      Handler mainHandler = new Handler(getApplicationContext().getMainLooper());
      Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
          showVideoFragment();
        } // This is your code
      };
      mainHandler.post(myRunnable);
    } catch (JSONException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  VideoSessionFragment.IVideoSessionFragmentListener videoListener = new VideoSessionFragment.IVideoSessionFragmentListener() {
    @Override
    public void onVideoSessionStarted() {
//      Log.d("onVideoSessionStarted", "");
    }

    @Override
    public void onVideoSessionCancel(int resultId, String resultMessage) {
//      Log.d("onVideoSessionCancel", "id "+resultId + " msg "+ resultMessage);
      ExitActivity(resultId, RESULT_CANCELED, resultMessage);
    }

    @Override
    public void onVideoSessionCompleted(int resultId, String resultMessage) {
//      Log.d("onVideoSessionCompleted", "id "+resultId + " msg "+ resultMessage);
      ExitActivity(resultId, RESULT_OK, resultMessage);
    }
  };

  private void ExitActivity(int resultId, int resultCode, String resultMessage) {
    Intent data = new Intent();
    data.putExtra(getString(R.string.RESULT_ID), resultId);
    data.putExtra(getString(R.string.RESULT_CODE), resultCode);
    data.putExtra(getString(R.string.RESULT_MESSAGE), resultMessage);
    setResult(resultCode, data);
    VideoSessionActivity.this.finish();
  }
  @Override
  public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
    super.onPostCreate(savedInstanceState, persistentState);

//    Handler mainHandler = new Handler(getApplicationContext().getMainLooper());
//    Runnable myRunnable = new Runnable() {
//      @Override
//      public void run() {
//        showVideoFragment();
//      } // This is your code
//    };
//    mainHandler.post(myRunnable);
  }

  private void showVideoFragment(){
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.replace(R.id.video_container, mVideoSessionFragment, "vid");
    ft.commit();
  }
  @Override
  protected void onResume() {
    super.onResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mVideoSessionFragment = null;
  }

}
