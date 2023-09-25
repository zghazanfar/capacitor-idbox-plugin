#import <Foundation/Foundation.h>
#import <Capacitor/Capacitor.h>

// Define the plugin using the CAP_PLUGIN Macro, and
// each method the plugin supports using the CAP_PLUGIN_METHOD macro.
CAP_PLUGIN(IdboxPluginPlugin, "IdboxPlugin",
CAP_PLUGIN_METHOD(initWithHawkCredentials, CAPPluginReturnPromise);
CAP_PLUGIN_METHOD(startListeningSignalR, CAPPluginReturnPromise);
CAP_PLUGIN_METHOD(startListeningVideoSignalR, CAPPluginReturnPromise);
CAP_PLUGIN_METHOD(registerRequest, CAPPluginReturnPromise);
CAP_PLUGIN_METHOD(getNextStep, CAPPluginReturnPromise);
CAP_PLUGIN_METHOD(getRequestId, CAPPluginReturnPromise);
CAP_PLUGIN_METHOD(pleaseWait, CAPPluginReturnPromise);
CAP_PLUGIN_METHOD(videoCallQueue, CAPPluginReturnPromise);
CAP_PLUGIN_METHOD(videoCall, CAPPluginReturnPromise);
CAP_PLUGIN_METHOD(videoCallVerification, CAPPluginReturnPromise);
CAP_PLUGIN_METHOD(createOtp, CAPPluginReturnPromise);
CAP_PLUGIN_METHOD(resendOtp, CAPPluginReturnPromise);
CAP_PLUGIN_METHOD(contractForm, CAPPluginReturnPromise);
CAP_PLUGIN_METHOD(contractFormAthex, CAPPluginReturnPromise);
CAP_PLUGIN_METHOD(scan1SIdentity, CAPPluginReturnPromise);
CAP_PLUGIN_METHOD(scan2SIdentity, CAPPluginReturnPromise);
CAP_PLUGIN_METHOD(scanAdditionalDocuments, CAPPluginReturnPromise);
CAP_PLUGIN_METHOD(scanSelfie, CAPPluginReturnPromise);




)
