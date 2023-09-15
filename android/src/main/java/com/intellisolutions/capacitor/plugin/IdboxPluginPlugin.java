package com.intellisolutions.capacitor.plugin;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.PluginResult;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.JsonReader;
import android.util.Log;

import androidx.activity.result.ActivityResult;
import androidx.appcompat.app.AlertDialog;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.intellisolutions.idbox.api.OnBoardingAPI;
import com.intellisolutions.idbox.api.OnBoardingEventListener;
import com.intellisolutions.idbox.api.base.TransportMethod;
import com.intellisolutions.activities.FragmentHandlerActivity;
import com.intellisolutions.activities.VideoSessionActivity;
import org.apache.cordova.Idbox_Plugin.util.MapUtil;
import org.apache.cordova.Idbox_Plugin.util.TransportMethodsValues;
import com.intellisolutions.capacitor.plugin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


@CapacitorPlugin(name = "IdboxPlugin")
public class IdboxPluginPlugin extends Plugin {
    public static OnBoardingAPI onBoardingAPI;
    private OnBoardingEventListener onBoardingEventListener = null;
    private OnBoardingEventListener videoOnBoardingEventListener = null;
    private OnBoardingAPI.Builder onBoardingApiBuilder;
    public Context context ;
    public static String requestId;
    public Activity currentActivity;
    private final  int REQUEST_SCAN_MEDIA = 1, REQUEST_VIDEO_SESSION= 2;
    private JSONObject VideoSession = new JSONObject();
    private String action;
    private File file;
    private PluginCall call;


    private IdboxPlugin implementation = new IdboxPlugin();

    // @PluginMethod
    // public void echo(PluginCall call) {
    //     String value = call.getString("value");

    //     JSObject ret = new JSObject();
    //     ret.put("value", implementation.echo(value));
    //     call.resolve(ret);
    // }
//    @PluginMethod
//    public void registerRequest(PluginCall call) {
//        String value = call.getString("response");
//
//        JSObject ret = new JSObject(value);
//        //ret.put("value", implementation.echo(value));
//        call.resolve(ret);
//    }

    public File saveImage(final String imageData,String fileName) {
        final byte[] imgBytesData = android.util.Base64.decode(imageData,
                android.util.Base64.DEFAULT);
        final FileOutputStream fileOutputStream;
        try {
          this.context = getContext();
            this.file = new File(this.context.getFilesDir()+"/"+fileName+".pdf");
            fileOutputStream = new FileOutputStream(this.file);

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }

        final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                fileOutputStream);
        try {
            bufferedOutputStream.write(imgBytesData);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
  @PluginMethod
    public void initWithHawkCredentials(PluginCall call) {
    this.call = call;

    // TODO: Initialize OnBoarding Api object
//    Log.d("initWithHawkCredentials", "Instance Called");
        try {
          String data = call.getString("options");

          JSONObject options = new JSONObject(data);
            // Instantiate the OnBoarding Builder object
            onBoardingApiBuilder = new OnBoardingAPI.Builder(context);
      /*
        Set all OnBoardingApiBuilder methods here but set only those whose keys are
        passing from RN application.
       */
            onBoardingApiBuilder = new OnBoardingAPI.Builder(context);
            requestId = options.getString("requestId");
            Log.e("Android UUID", requestId);

            if(options.has("url")){
                String baseUrl = options.getString("url");
                int port = options.getInt("port");
                String apiPath=  options.getString("apiPath");
                String serverUrl = baseUrl + port + apiPath;
                onBoardingApiBuilder.setUrl(serverUrl);
            }
            if(options.has("hawkId"))
                onBoardingApiBuilder.setHawkId(options.getString("hawkId"));
            if(options.has("hawkKey"))
                onBoardingApiBuilder.setHawkKey(options.getString("hawkKey"));
//      if(options.has(reactContext.getString(R.string.ONBOARDING_OPTIONS_REQUESTID)))
//        onBoardingApiBuilder.setRequestId(options.getString(reactContext.getString(R.string.ONBOARDING_OPTIONS_REQUESTID)));
            onBoardingApiBuilder.setRequestId(requestId);
            if(options.has("connectionTimeout"))
                onBoardingApiBuilder.setConnectTimeout(options.getInt("connectionTimeout"));
            if(options.has("readTimeout"))
                onBoardingApiBuilder.setReadTimeout(options.getInt("readTimeout"));
            if(options.has("logging"))
                onBoardingApiBuilder.setLogging(options.getBoolean("logging"));
            onBoardingApiBuilder.setCallback(apiCallBacks);

            onBoardingAPI = onBoardingApiBuilder.build();
            call.resolve();
        }
        catch (Exception e) {
            call.reject(e.getMessage());
        }
    }

    OnBoardingAPI.IOnBoardingApiCall apiCallBacks = new OnBoardingAPI.IOnBoardingApiCall() {
        @Override
        public void onApiCallStarted(String taskId) {
            //      Log.d("onApiCallStarted", taskId);
        }

        @Override
        public void onApiCallRunning(String taskId) {
            //      Log.d("onApiCallRunning", taskId);
        }

        public void onApiCallCompleted(String taskId, boolean success, int resultCode, String resultMessage) {
            //      Log.d("onApiCallCompleted", "taskId: "+ taskId + " " + "Success: "+ success
            //        + " resultCode: "+ resultMessage );
            try {
                JSONObject result = new JSONObject();
                result.put("TaskId",taskId);
                result.put("success",success);
                result.put("resultCode",resultCode);
                result.put("resultMessage",resultMessage);
                if(taskId == "idBox.api.VideoCall" && success) {
                    JSONObject temp = new JSONObject(resultMessage);
                    VideoSession = temp.getJSONObject("Result");
                    Log.d("resultt", resultMessage.toString());
                }
                if (call.getMethodName().equals("contractForm") || call.getMethodName().equals("contractFormAthex")){
                    try{
                        if(file!=null){
                            file.delete();
                        }
                    }
                    catch (Exception e){
                        Log.e("Error deleting file", "onApiCallCompleted: ", e );
                    }
                }
                Log.d("resultt", resultMessage);
                if(call.getMethodName().equals("videoCallQueue")){
//                  PluginResult pResult = new PluginResult(PluginResult.Status.OK,resultMessage);
//                  pResult.setKeepCallback(true);
//                  Log.d("pluginResultOnApiFini", resultMessage);
//                 callbackContext.sendPluginResult(pResult);
                  call.setKeepAlive(true);
                  call.resolve(new JSObject(resultMessage));
              }

//                else if (call.getMethodName().equals("contractForm") || call.getMethodName().equals("contractFormAthex"))
//                 {
//                 JSONObject temp = new JSONObject(resultMessage);
//                 JSONObject resultContractFile = temp.getJSONObject("Result");
//                 Log.d("signdocument", resultContractFile.getString("Data"));
//                   if(resultContractFile.getString("Data")!=null){
//                       call.resolve(new JSObject(resultContractFile.getString("Data")));
//                  }else{
//                    call.resolve(new JSObject(resultMessage));
//                 }
//               }
                else{
                    call.resolve(new JSObject(resultMessage));
                }
//                }
//                else
//                {
//                    call.reject(resultMessage);
//                }

            } catch (Exception e){
                call.reject(e.getMessage());
            }
        }
    };

  @PluginMethod
    public void registerRequest(PluginCall call){
        try {
          this.call = call;
          String data = call.getString("options");
            onBoardingAPI.registerRequest(data);
        }catch (Exception e){
            call.reject(e.getMessage());
            //call.reject( e.getMessage());
        }
    }
  @PluginMethod
    public void getNextStep(PluginCall call){
        try {
            this.call = call;
            onBoardingAPI.getNextStep();
        }catch (Exception e){
            call.reject(e.getMessage());
        }
    }
  @PluginMethod
  public void createOtp(PluginCall call){
        try {
            this.call = call;
            onBoardingAPI.createOTP();
        }catch (Exception e){
            call.reject(e.getMessage());
        }
    }
  @PluginMethod
  public void resendOtp(PluginCall call){
        try {
            this.call = call;
            onBoardingAPI.resendOTP();
        }catch (Exception e){
            call.reject(e.getMessage());
        }
    }
  @PluginMethod
  public void videoCallVerification(PluginCall call){
        try {
           this.call = call;
            onBoardingAPI.videoCallVerification();
        }catch (Exception e){
            call.reject(e.getMessage());
        }
    }
  @PluginMethod
  public void contractForm(PluginCall call){
        try {
            this.call = call;
          String data = call.getString("options");

          JSONObject options = new JSONObject(data);
            File file = this.saveImage(options.getString("base64"),options.getString("templateName"));
            Log.d("filepath", file.getPath());

            onBoardingAPI.contractForm(file.getPath(),options.getString("templateName"));
        }catch (Exception e){
            call.reject(e.getMessage());
        }
    }
  @PluginMethod
  public void contractFormAthex(PluginCall call){
        try {
          String data = call.getString("options");
          JSONObject options = new JSONObject(data);
            File file = this.saveImage(options.getString("base64"),options.getString("templateName"));
            onBoardingAPI.contractFormAthex(file.getPath() ,options.getString("templateName"),options.getString("otp"));
        }catch (Exception e){
            call.reject(e.getMessage());
        }
    }

    // API Call Method

    public void skipDocumentStep(PluginCall call){
        try {
          this.call = call;
            onBoardingAPI.skipNextStep();
        }catch (Exception e){
            call.reject(e.getMessage());
        }
    }

    // API Call Method
    @PluginMethod
    public void pleaseWait(PluginCall call){
        try {
          this.call = call;
          onBoardingAPI.pleaseWait();
        }catch (Exception e){
            call.reject(e.getMessage());
        }
    }

    // API Call Method
//
//    public void setRequestMetaData(String requestBody,PluginCall call){
//        try {
//
//            onBoardingAPI.setRequestMetadata(requestBody);
//        }catch (Exception e){
//            call.reject(e.getMessage());
//        }
//    }

    // API Call Method
//
//  public void skipNextStepCall(PluginCall call){
//    try {
//
//      onBoardingAPI.skipNextStep();
//    }catch (Exception e){
//     call.reject(e.getMessage());
//    }
//  }

    // API Call Method

    public void getBatchSourceUploadDocs(PluginCall call){
        try {

            onBoardingAPI.getBatchSourceUploadDocs();
        }catch (Exception e){
            call.reject(e.getMessage());
        }
    }

    // API Call Method

    public void uploadSelfie(String filePath ,PluginCall call){
        try {
            onBoardingAPI.uploadSelfie(filePath);
        }catch (Exception e){
            call.reject(e.getMessage());
        }
    }
  @PluginMethod
  public void scanSelfie(PluginCall call ){
        this.call = call;
        String data = call.getString("options");
    try {
      JSONObject options = new JSONObject(data);
      openFragmentHandlerActivity(options);
    }
    catch (JSONException e) {
      Log.i("error", e.getMessage());
    }
    }


    // API Call Method

    public void uploadIdentitySingleSide(String filePath ,PluginCall call){
        try {

            onBoardingAPI.uploadIdentitySingleSide(filePath);
        }catch (Exception e){
            call.reject(e.getMessage());
        }
    }

    // API Call Method

    public void uploadIdentity2Sides(String frontFileName ,String backFileName,PluginCall call){
        try {

            onBoardingAPI.uploadIdentity2Sides(frontFileName, backFileName);
        }catch (Exception e){
            call.reject(e.getMessage());
        }
    }

    // API Call Method

    public void uploadAdditionalDocuments(ArrayList<String> files, PluginCall call){
        try {

            onBoardingAPI.uploadAdditionalDocuments(files);
        }catch (Exception e){
            call.reject(e.getMessage());
        }
    }

    // API Call Method
    @PluginMethod
    public void videoCallQueue(PluginCall call){
        try {
            this.call = call;
            onBoardingAPI.videoCallQueue();
        }catch (Exception e){
            call.reject(e.getMessage());
        }
    }

    // API Call Method
    @PluginMethod
    public void videoCall(PluginCall call){
        try {
            this.call = call;
            onBoardingAPI.videoCall();
        }catch (Exception e){
            call.reject(e.getMessage());
        }
    }

    // API Call Method

    public void startProcess(PluginCall call){
        try {
            onBoardingAPI.startProcess();
        } catch (Exception e){
            call.reject(e.getMessage());
        }
    }

    // API Call Method

    public void uploadAdditionalDocumentsBranch(ArrayList<String> files,PluginCall call){
        try {

            onBoardingAPI.uploadAdditionalDocumentsBranch(files);
        }catch (Exception e){
            call.reject(e.getMessage());
        }
    }
    // APi Call Method

    public void getRequestMetadata(PluginCall call){
        try {
            onBoardingAPI.getRequestMetadata();
        }catch (Exception e){
            call.reject( e.getMessage());
        }
    }

    // Get server url only

    public void getUrl(PluginCall call){
        try {

            call.reject(onBoardingAPI.getUrl());
        }catch (Exception e){
            call.reject( e.getMessage());
        }
    }

    // Get Hawk Id only

    public void getHawkId(PluginCall call){
        try {
            call.reject(onBoardingAPI.getHawkId());
        }catch (Exception e){
            call.reject( e.getMessage());
        }
    }

    // Get Hawk key only

    public void getHawkKey(PluginCall call){
        try {

            call.reject(onBoardingAPI.getHawkKey());
        }catch (Exception e){
            call.reject( e.getMessage());
        }
    }

    // Get Server Time only

//    public void getServerTime(PluginCall call){
//      try {
//
//        call.reject(String(onBoardingAPI.getServerTime()));
//      }catch (Exception e){
//        call.reject( e.getMessage());
//      }
//    }

    // Get the string of flow current step only

    public void getFlowCurrentStep(PluginCall call){
        try {

            call.reject(onBoardingAPI.getFlowCurrentStep());
        }catch (Exception e){
            call.reject( e.getMessage());
        }
    }


    public void getFlowNextStep(PluginCall call){
        try {

            call.reject(onBoardingAPI.getFlowNextStep());
        }catch (Exception e){
            call.reject( e.getMessage());
        }
    }

    // Get video session id only

    public void getVideoSessionId(PluginCall call){
        try {

            call.reject(onBoardingAPI.getVideoSessionId());
        }catch (Exception e){
            call.reject( e.getMessage());
        }
    }

    // Get video session key only

    public void getVideoSessionKey(PluginCall call){
        try {

            call.reject(onBoardingAPI.getVideoSessionId());
        }catch (Exception e){
            call.reject( e.getMessage());
        }
    }

    // Get Video Session Token only

    public void getVideoSessionToken(PluginCall call){
        try {

            call.reject(onBoardingAPI.getVideoSessionToken());
        }catch (Exception e){
            call.reject( e.getMessage());
        }
    }
    // Get Request Id only
@PluginMethod
    public void getRequestId(PluginCall call){
        try {
this.call = call;
            call.resolve(new JSObject(requestId));
        }catch (Exception e){
            call.reject( e.getMessage());
        }
    }
    //    public void scanIdentity(JSONObject options ){
//        try{
//            mPromise.resolve(MapUtil.getMapResolverStrResponse(reactContext,reactContext.getString(R.string.NO_SUPPORT_MSG)));
//        } catch (Exception e){
//            mPromise.reject(reactContext.getString(R.string.RESOLVER_MAP_API_ERROR_KEY), e.getMessage());
//        }
//    }
  @PluginMethod
  public void scan1SIdentity(PluginCall call ){
    this.call = call;
    String data = call.getString("options");
    try {
      JSONObject options = new JSONObject(data);
      openFragmentHandlerActivity(options);
    }
    catch (JSONException e) {
      Log.i("error", e.getMessage());
    }
  }
@PluginMethod
public void scan2SIdentity(PluginCall call ){
  this.call = call;
  String data = call.getString("options");
  try {
    JSONObject options = new JSONObject(data);
    openFragmentHandlerActivity(options);
  }
  catch (JSONException e) {
    Log.i("error", e.getMessage());
  }
}
  @PluginMethod
  public void scanAdditionalDocuments(PluginCall call ){
    this.call = call;
    String data = call.getString("options");
    try {
      JSONObject options = new JSONObject(data);
      openFragmentHandlerActivity(options);
    }
    catch (JSONException e) {
      Log.i("error", e.getMessage());
    }
  }
    public void scan2SFrontDocuments(JSONObject options){
        openFragmentHandlerActivity(options);
    }
    public void scan2SBackDocuments(JSONObject options){
        openFragmentHandlerActivity(options);
    }
    //    private final ActivityEventListener mActivityResultListener = new ActivityEventListener() {
//        @Override
//        public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent intent) {
//            try {
//                if(requestCode == REQUEST_SCAN_MEDIA){
//                    WritableMap map = Arguments.createMap();
//                    map.putString(reactContext.getString(R.string.MAP_API_RESPONSE_TASK), intent.getStringExtra(reactContext.getString(R.string.MAP_API_RESPONSE_TASK)));
//                    map.putString(reactContext.getString(R.string.RESULT_MESSAGE), intent.getStringExtra(reactContext.getString(R.string.RESULT_MESSAGE)));
//                    map.putInt(reactContext.getString(R.string.API_RESULT_CODE), intent.getIntExtra(reactContext.getString(R.string.API_RESULT_CODE), 0));
//                    map.putInt(reactContext.getString(R.string.RESULT_CODE), intent.getIntExtra(reactContext.getString(R.string.RESULT_CODE), 0));
//                    mPromise.resolve(map);
//                }
//                else if(requestCode == REQUEST_VIDEO_SESSION){
////          Log.e("Video Intent", "");
//                    WritableMap map = Arguments.createMap();
//                    map.putInt(reactContext.getString(R.string.RESULT_ID), intent.getIntExtra(reactContext.getString(R.string.RESULT_ID), 0));
//                    map.putInt(reactContext.getString(R.string.RESULT_CODE), intent.getIntExtra(reactContext.getString(R.string.RESULT_CODE), 0));
//                    map.putString(reactContext.getString(R.string.RESULT_MESSAGE), intent.getStringExtra(reactContext.getString(R.string.RESULT_MESSAGE)));
//                    mPromise.resolve(map);
//                }
//            } catch (Exception e){
//                e.printStackTrace();
//                call.reject(e.getMessage());
//            }
//        }
//
//        @Override
//        public void onNewIntent(Intent intent) {
////      Log.e("onNewIntent", "");
//
//        }
//    };
    @ActivityCallback
    public void onActivityResult(PluginCall call, ActivityResult result) {
        try {
          Intent intent = result.getData();
          Log.d("activityResult", result.toString());

//          if(requestCode == REQUEST_SCAN_MEDIA){
                JSONObject map = new JSONObject();
                map.put("String.valueOf(R.string.MAP_API_RESPONSE_TASK)", intent.getStringExtra(String.valueOf(R.string.MAP_API_RESPONSE_TASK)));
                map.put(String.valueOf(R.string.RESULT_MESSAGE), intent.getStringExtra(String.valueOf(R.string.RESULT_MESSAGE)));
                map.put(String.valueOf(R.string.API_RESULT_CODE), intent.getIntExtra(String.valueOf(R.string.API_RESULT_CODE), 0));
                map.put(String.valueOf(R.string.RESULT_CODE), intent.getIntExtra(String.valueOf(R.string.RESULT_CODE), 0));
                Log.d("activityResult", intent.getStringExtra("response"));
                call.resolve(new JSObject(intent.getStringExtra("response")));
//            }
//            else if(requestCode == REQUEST_VIDEO_SESSION){
////          Log.e("Video Intent", "");
//                JSONObject map = new JSONObject();
//                map.put(String.valueOf(R.string.RESULT_ID), intent.getIntExtra(String.valueOf(R.string.RESULT_ID), 0));
//                map.put(String.valueOf(R.string.RESULT_CODE), intent.getIntExtra(String.valueOf(R.string.RESULT_CODE), 0));
//                map.put(String.valueOf(R.string.RESULT_MESSAGE), intent.getStringExtra(String.valueOf(R.string.RESULT_MESSAGE)));
//              call.resolve(new JSObject(intent.getStringExtra("response")));
//            }
        } catch (Exception e){
            e.printStackTrace();
            call.reject(e.getMessage());
        }
    }
  @PluginMethod
  public void startListeningVideoSignalR(PluginCall call){
        try {
          this.call = call;
          String data = call.getString("options");
          JSONObject options = new JSONObject(data);

//      Log.e("Vid SignalR", options.toString());
            if(videoOnBoardingEventListener == null){
                setUpVideoSignalR(options);
            }
            boolean isSignalROn = true;
            if(isSignalROn){
//        Log.d("isSignalROn", "enalbe");
                videoOnBoardingEventListener.enable();
            }
            else {
                if(videoOnBoardingEventListener != null){
//          Log.d("isSignalROn", "disable");
                    videoOnBoardingEventListener.disable();
                }
            }
        } catch (Exception e){
            this.call.reject(e.getMessage());
        }
    }
  @PluginMethod
  public void startListeningSignalR(PluginCall call){
        try {
          this.call = call;
          String data = call.getString("options");
          JSONObject options = new JSONObject(data);

//      Log.e("singlaRO", options.toString());
            if(onBoardingEventListener == null){
                setUpSignalR(options);
            }
            boolean isSignalROn = options.getBoolean("useSignalR");
            if(isSignalROn){
//        Log.d("isSignalROn", "enalbe");
                onBoardingEventListener.enable();
            }
            else {
//        Log.d("isSignalROn", "disable");
                if(onBoardingEventListener != null)
                    onBoardingEventListener.disable();
            }
        } catch (Exception e){
            call.reject(e.getMessage());
        }
    }
    public void setUpVideoSignalR(JSONObject options){
        try {
//      Log.e("setUpVideoSignalR", "Called");
            String baseUrl = options.getString("url");
            int port = options.getInt("port");
            String apiPath = options.getString("apiPath");
            String serverUrl = baseUrl + port + "/" +apiPath;
//      Log.e("SignalR Url", serverUrl);
      /*
        initialize OnBoardingEventListener based on Transport Method
        All = 0
        WebSocket = 1
        Long_polling = 2
       */
            if(options.has("transportMethod")){
                int transportValue = options.getInt("transportMethod");
//        Log.e("transportMethod", transportValue+ "");
                if(transportValue == TransportMethodsValues.ALL.getValue())
                    initVideoOnBoardingEventWithTransport(serverUrl, TransportMethod.ALL);
                else if(transportValue == TransportMethodsValues.WEBSOCKETS.getValue())
                    initVideoOnBoardingEventWithTransport(serverUrl, TransportMethod.WEBSOCKETS);
                else if(transportValue == TransportMethodsValues.LONG_POLLING.getValue())
                    initVideoOnBoardingEventWithTransport(serverUrl, TransportMethod.LONG_POLLING);
            }
            else {
                initVideoOnBoardingEvent(serverUrl);
            }
        }
        catch (Exception e){
            call.reject(e.getMessage());
        }
    }
    public void setUpSignalR(JSONObject options){
        try {
//      Log.e("setUpVideoSignalR", "Called");
            String baseUrl = options.getString("url");
            int port = options.getInt("port");
            String apiPath = options.getString("apiPath");
            String serverUrl = baseUrl + port + "/" +apiPath;
//      Log.e("SignalR Url", serverUrl);
      /*
        initialize OnBoardingEventListener based on Transport Method
        All = 0
        WebSocket = 1
        Long_polling = 2
       */
            if(options.has("transportMethod")){
                int transportValue = options.getInt("transportMethod");
//        Log.e("transportMethod", transportValue+ "");
                if(transportValue == TransportMethodsValues.ALL.getValue())
                    initVideoOnBoardingEventWithTransport(serverUrl, TransportMethod.ALL);
                else if(transportValue == TransportMethodsValues.WEBSOCKETS.getValue())
                    initVideoOnBoardingEventWithTransport(serverUrl, TransportMethod.WEBSOCKETS);
                else if(transportValue == TransportMethodsValues.LONG_POLLING.getValue())
                    initVideoOnBoardingEventWithTransport(serverUrl, TransportMethod.LONG_POLLING);
            }
            else {
                initOnBoardingEvent(serverUrl);
            }
        }
        catch (Exception e){
            call.reject(e.getMessage());
        }
    }
    private void initVideoOnBoardingEvent(String serverUrl){
//    Log.e("initOnBoardingEvent", "Called");
      try {
        videoOnBoardingEventListener = new OnBoardingEventListener(serverUrl, requestId) {
          @Override
          public void onMessageReceived(String sender, String message) {
//        Log.e("onMessageRec", sender + " msg "+ message);
            JSONObject jsonObject = new JSONObject();
            jsonObject.optString(String.valueOf(R.string.RESOLVER_MAP_MSG_KEY), message);
            jsonObject.optString(String.valueOf(R.string.SENDER), sender);
            sendEvent(String.valueOf((R.string.EVENT_NAME_MESSAGE)), jsonObject);
            Log.d("msgRecievedVideo", message + "Sender" + sender);
            if (call.getMethodName().equals("VideoCallQueue")) {
//                    PluginResult result = new PluginResult(PluginResult.Status.OK,message);
//                    result.setKeepCallback(true);
//                    Log.d("pluginREsult", message);
//                    callbackContext.sendPluginResult(result);
              call.setKeepAlive(true);
              try {
                call.resolve(new JSObject("{'message':"+message+"}"));
              } catch (JSONException e) {
                e.printStackTrace();
              }
            } else {
              call.reject(message);
            }

          }

          @Override
          public void onStateChanged(ConnectionState connectionState, Throwable throwable) {
//        Log.e("onStateChanged", connectionState + " t "+ throwable);
            JSONObject jsonObject = new JSONObject();
            jsonObject.optString(String.valueOf(R.string.SIGNALR_CONNECTION_STATE), String.valueOf(connectionState));
            jsonObject.optString(String.valueOf(R.string.RESOLVER_MAP_API_ERROR_KEY), throwable != null ? throwable.getMessage() : "");
            sendEvent(String.valueOf(R.string.EVENT_NAME_STATE), jsonObject);
            //showAlert(connectionState.toString());
            Log.d("signalrStateChange", jsonObject.toString());
            try {
              call.resolve(new JSObject("{'state':"+connectionState.toString()+"}"));
            } catch (JSONException e) {
              e.printStackTrace();
            }

          }
        };
      }catch (Exception e){
        Log.i("error", e.getMessage());
      }
    }
    private void initOnBoardingEvent(String serverUrl){
//    Log.e("initOnBoardingEvent", "Called");
        onBoardingEventListener = new OnBoardingEventListener(serverUrl,requestId) {
            @Override
            public void onMessageReceived(String sender, String message) {
                Log.d("msgRecievedOnboarding", message  + "Sender" + sender);
                JSONObject jsonObject = new JSONObject();
                jsonObject.optString(String.valueOf(R.string.RESOLVER_MAP_MSG_KEY) , message);
                jsonObject.optString(String.valueOf(R.string.SENDER), sender);
                sendEvent(String.valueOf((R.string.EVENT_NAME_MESSAGE)), jsonObject);
                Log.d(String.valueOf(R.string.EVENT_NAME_MESSAGE), jsonObject.toString());

            }

            @Override
            public void onStateChanged(ConnectionState connectionState, Throwable throwable) {
//        Log.e("onStateChanged", connectionState + " t "+ throwable);
                JSONObject jsonObject = new JSONObject();
                jsonObject.optString(String.valueOf(R.string.SIGNALR_CONNECTION_STATE),String.valueOf(connectionState));
                jsonObject.optString(String.valueOf(R.string.RESOLVER_MAP_API_ERROR_KEY), throwable != null ? throwable.getMessage() : "");
                sendEvent(String.valueOf(R.string.EVENT_NAME_STATE), jsonObject);
            }

        };
    }
    private void initVideoOnBoardingEventWithTransport(String serverUrl, TransportMethod mMethod){
        videoOnBoardingEventListener = new OnBoardingEventListener(serverUrl,requestId, mMethod) {
            @Override
            public void onMessageReceived(String sender, String message) {
//        Log.e("onMessageRec", "VideoTrans "+sender + " msg "+ message);
                JSONObject jsonObject = new JSONObject();
                jsonObject.optString(String.valueOf(R.string.RESOLVER_MAP_MSG_KEY) , message);
                jsonObject.optString(String.valueOf(R.string.SENDER), sender);
                Log.d("messageRecievedTranspor", message  + "Sender" + sender);
                if(call.getMethodName().equals("videoCallQueue")){
//                    PluginResult result = new PluginResult(PluginResult.Status.OK,"Waiting Sequence of queue:"+message);
//                    if(message.endsWith("0")){
//                        result = new PluginResult(PluginResult.Status.OK,"NextStep:Start Video Session");
//                    }
//                    result.setKeepCallback(true);
//                    Log.d("pluginREsult", message);
//                    callbackContext.sendPluginResult(result);
                  String msg = "Waiting Sequence of queue:"+message;
                  if(message.endsWith("0")){
                    msg = "NextStep:Start Video Call";
                  }
                  call.setKeepAlive(true);
                  try {
                    call.resolve(new JSObject("{'state':"+msg+"}"));
                  } catch (JSONException e) {
                    e.printStackTrace();
                  }
                }
                else{
                  try {
                    call.resolve(new JSObject("{'message':"+message+"}"));
                  } catch (JSONException e) {
                    e.printStackTrace();
                  }
                }

            }

            @Override
            public void onStateChanged(ConnectionState connectionState, Throwable throwable) {
//        Log.e("onStateChanged", "VideoTrans "+ connectionState + " " + throwable);
                JSONObject jsonObject = new JSONObject();
                jsonObject.optString(String.valueOf(R.string.SIGNALR_CONNECTION_STATE),String.valueOf(connectionState));
                jsonObject.optString(String.valueOf(R.string.RESOLVER_MAP_API_ERROR_KEY), throwable != null ? throwable.getMessage() : "");
                Log.d("signalrStateChange", jsonObject.toString());
              try {
                call.resolve(new JSObject("{'state':"+connectionState.toString()+"}"));
              } catch (JSONException e) {
                e.printStackTrace();
              }
            }
        };
    }
    private void sendEvent(String eventName,
                           JSONObject params) {
        try {
//            PluginResult result = new PluginResult(PluginResult.Status.valueOf(eventName),params);
//            result.setKeepCallback(true);
//            callbackContext.sendPluginResult(result);
          call.setKeepAlive(true);
          call.resolve(new JSObject(params.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void openFragmentHandlerActivity(JSONObject options){
        try {
           // cordova.setActivityResultCallback(this);

            currentActivity = getActivity();
            Intent intent = new Intent(currentActivity, FragmentHandlerActivity.class);

//      Log.e("JSON OBJ", jsonObject.toString());
            intent.putExtra("CameraOptionsJson", options.toString());
            this.startActivityForResult(this.call,intent, "onActivityResult");
//      mPromise.resolve(MapUtil.getMapResolverStrResponse(reactContext,reactContext.getString(R.string.CAMERA_MODE_OPEN_SUCCESS)));
        } catch (Exception e){
            this.call.reject(e.getMessage());
        }
    }
@PluginMethod
    public void startVideoSession(PluginCall call){
        try {
          this.call = call;
          String data = call.getString("options");
          JSONObject options = new JSONObject(data);
           // context.setActivityResultCallback(this);
            currentActivity = getActivity();
            Intent intent = new Intent(currentActivity, VideoSessionActivity.class);

            options.put("tokBoxSessionId",VideoSession.getString("TokBoxSessionId"));
            options.put("tokBoxApiKey",VideoSession.getString("TokBoxApiKey"));
            options.put("tokBoxToken",VideoSession.getString("TokBoxToken"));
//            options.put("tokBoxSessionId","TokBoxSessionId");
//            options.put("tokBoxApiKey","TokBoxApiKey");
//            options.put("tokBoxToken","TokBoxToken");
//            Log.e("Video Json OBJ", jsonObject.toString());
            intent.putExtra("VideoOptionsJson", options.toString());
            currentActivity.startActivityForResult(intent, REQUEST_VIDEO_SESSION);

        } catch (Exception e){
            call.reject(e.getMessage());
        }
    }
}
