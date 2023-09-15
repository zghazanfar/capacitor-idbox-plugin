import Foundation
import Capacitor
import IdBoxFramwork
import SwiftyJSON
import OpenTok

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(IdboxPluginPlugin)
public class IdboxPluginPlugin: CAPPlugin,OnBoardHttpDelegate,OnBoardCameraDelegate, DocCameraDelegate,IdCameraDelegate, VideoDelegate, DoubleIdCameraDelegate {
    private let implementation = IdboxPlugin()
    private var onBoardhttp=OnBoardHttp()
    var promiseRes = PromiseResponse()
    var prefixUrl = ""
    var viewcontroller = ViewController()
    required public override init() {
  //
        super.init()
      }
     
        @objc func initWithHawkCredentials(_ call: CAPPluginCall) {
                do{
                    
                    var arg = call.getString("options") ?? "" as! String
                    var jsonBody = try (JSONSerialization.jsonObject(with: arg.data(using: String.Encoding.utf8)!, options: []) as? [String:Any])!
                    var urlString = jsonBody["url"]
                    var url = URL(string: urlString as! String)
                    var domain = url?.standardized
                    print("\(domain)")
                    print("INITJSON: \(jsonBody)")
                    print("ApiPath: \(jsonBody["apiPath"] as! String)")
                    self.prefixUrl = jsonBody["apiPath"] as! String
                    self.promiseRes = PromiseResponse()
                    self.promiseRes.setCallback(call)
                    self.viewcontroller = ViewController()
                    self.onBoardhttp = OnBoardHttp()
                    self.viewcontroller.cameraDoubleSideIdController.cameraDoubleIdDelegate = self
                    self.viewcontroller.cameracontroller.cameradelegate = self
                    self.viewcontroller.cameraIdController.cameradelegate = self
                    self.viewcontroller.cameraDocController.cameraDocDelegate = self
                    self.viewcontroller.videoController.videoDelegate = self
                    self.viewcontroller.idboxModule = promiseRes
                    self.onBoardhttp.delegate = self
                    try?self.onBoardhttp.initWithId(urlPrefix: "\(jsonBody["apiPath"] as! String)/RegisterRequest/", server: domain?.absoluteString as! String, port: jsonBody["port"] as! Int, hawkId: jsonBody["hawkId"] as! String, hawkKey: jsonBody["hawkKey"] as! String)
                    //self.promiseRes.returnPromise(message: "Success")
                }
                    catch let error{
                        print(error)
                        //pluginResult = CDVPluginResult(status: CDVCommandStatus_OK, messageAs: error.localizedDescription)
                    // self.commandDelegate!.send(pluginResult, callbackId: call.callbackId)
                    }
            }


        @objc func registerRequest(_ call: CAPPluginCall) {
            do{
                //
                promiseRes.setCallback(call)
                var arg = call.getString("options") ?? "" as! String
                var jsonBody = try (JSONSerialization.jsonObject(with: arg.data(using: String.Encoding.utf8)!, options: []) as? [String:Any])!
                print("JSONEEQUEST \(jsonBody)")
                self.onBoardhttp.setBodyParam(bodyParam:jsonBody)
                self.onBoardhttp.setViewController(controller: viewcontroller)
                print("UUID WAS \(self.onBoardhttp.getUuid)")
                print("URL WAS \(self.onBoardhttp.basePath)")
                try?self.onBoardhttp.registerRequest()
            }
            catch let error{
                promiseRes.returnPromise(message: "Something Went wrong while registering")

            }
        }
        @objc func getRequestId(_ call: CAPPluginCall) {
            do{
                var requestId = ""
                try?requestId = onBoardhttp.getUuid
                promiseRes.setCallback(call)
                promiseRes.returnPromise(message: requestId)
            }
            catch let error{
                
            }
        }
        @objc func getNextStep(_ call: CAPPluginCall) {
            do{
                promiseRes.setCallback(call)
                onBoardhttp.setPrefixUrlGetNextStep(prefixurl: "\(prefixUrl)/GetNextStep/")
                try?onBoardhttp.getNextStep()
            }
            catch let error{
                
            }

        }
        @objc func createOtp(_ call: CAPPluginCall) {
            do{
                promiseRes.setCallback(call)
                onBoardhttp.setPrefixCreateOtp(prefixurl: "\(prefixUrl)/CreateOTP/")
                try?onBoardhttp.createOtp();
            }
            catch let error{
                
            }

        }
        @objc func resendOtp(_ call: CAPPluginCall) {
            do{
    //            promiseRes.setCallback(call)
    //            onBoardhttp.setPrefixResendOtp(prefixurl: "\(prefixUrl)/ResendOTP/")
    //            try?onBoardhttp.resendOtp()
                promiseRes.setCallback(call)
                onBoardhttp.setPrefixCreateOtp(prefixurl: "\(prefixUrl)/CreateOTP/")
                try?onBoardhttp.createOtp();
            }
            catch let error{
                
            }

        }
        @objc func contractForm(_ call: CAPPluginCall) {
            do{
                var jsonbody = call.getString("options") ?? ""
                var file = try (JSONSerialization.jsonObject(with:jsonbody.data(using: String.Encoding.utf8)!, options: []) as? [String:Any])!
                var base64 = file["base64"] as! String
                var templateName = file["templateName"] as! String
                let remainder = base64.count % 4
                if remainder > 0 {
                    base64 = base64.padding(toLength: base64.count + 4 - remainder,
                                                  withPad: "=",
                                                  startingAt: 0)
                }
                let convertedData = try Data(base64Encoded: base64,options: .ignoreUnknownCharacters) ?? Data()
                promiseRes.setCallback(call)
                onBoardhttp.setPrefixContractForm(prefixurl: "\(prefixUrl)/ContractForm/")
                try?onBoardhttp.ContractForm(data:convertedData, templateName: templateName)
            }
            catch let error{
                print(error.localizedDescription)

            }

        }
        @objc func contractFormAthex(_ call: CAPPluginCall) {
            do{
                promiseRes.setCallback(call)
                var jsonbody = call.getString("options") ?? ""
                var file = try (JSONSerialization.jsonObject(with:jsonbody.data(using: String.Encoding.utf8)!, options: []) as? [String:Any])!
                var base64 = file["base64"] as! String
                var otp = file["otp"] as! String
                var templateName = file["templateName"] as! String
                let remainder = base64.count % 4
                if remainder > 0 {
                    base64 = base64.padding(toLength: base64.count + 4 - remainder,
                                                  withPad: "=",
                                                  startingAt: 0)
                }
                let convertedData = Data(base64Encoded: base64,options: .ignoreUnknownCharacters) ?? Data()
                onBoardhttp.setPrefixContractFormAthex(prefixurl: "\(prefixUrl)/ContractFormAthex/")
                try?onBoardhttp.ContractFormAthex(data:convertedData, otp: otp, templateName: templateName)

            }
            catch let error{

            }

        }
        @objc func videoCallVerification(_ call: CAPPluginCall) {
            do{
                promiseRes.setCallback(call)
                onBoardhttp.setPrefixVideoCallVerification(prefixurl: "\(prefixUrl)/VideoCallVerification/")
                try?onBoardhttp.videoCallVerification()
            }
            catch let error{
                
            }

        }
        @objc func skipDocumentStep(_ call: CAPPluginCall) {
            do{
                promiseRes.setCallback(call)
                self.onBoardhttp.setPrefixSkipStep(prefixurl: "\(prefixUrl)/SkipDocumentStep/")
                try?self.onBoardhttp.skipNextStep()
            }
            catch let error{
                
            }
        }
        @objc func pleaseWait(_ call: CAPPluginCall) {
            do{
                promiseRes.setCallback(call)
                self.onBoardhttp.setPrefixPreaseWait(prefixurl: "\(prefixUrl)/PleaseWait/")
                try?self.onBoardhttp.pleaseWait()
            }
            catch let error{
                
            }
        }
        @objc(setRequestMetaData:) func setRequestMetaData(_ call: CAPPluginCall) {
            do{
                var arg = call.getString("options") ?? "" as! String
                var jsonBody = try (JSONSerialization.jsonObject(with: arg.data(using: String.Encoding.utf8)!, options: []) as? [String:Any])!
                self.onBoardhttp.setBodyParamMeta(bodyParamMeta: jsonBody as! NSMutableDictionary)
                try?self.onBoardhttp.setRequestMetaData()
            }
            catch let error{
                
            }
        }
        @objc func videoCallQueue(_ call: CAPPluginCall) {
            do{
                DispatchQueue.main.async {
                    try?self.openIdBoxViewControllerForVideoQueue(call)
                }
            }
            catch let error{
                
            }
        }
        @objc func videoCall(_ call: CAPPluginCall) {
            do{
//                DispatchQueue.main.async {
                    try?self.openIdBoxViewControllerForVideo(call)
//                }
            }
            catch let error{
                
            }
        }
        @objc func scanSelfie(_ call: CAPPluginCall) {
            do{
                DispatchQueue.main.async {
                    try?self.openIdBoxViewController(call)
                            }
            }
            catch let error{
                
            }
        }
        @objc func scan1SIdentity(_ call: CAPPluginCall) {
            do{
                DispatchQueue.main.async {
                   try? self.openIdBoxViewControllerFor1SIdentity(call)
                }
            }
            catch let error{
                
            }

        }
        @objc func scan2SIdentity(_ call: CAPPluginCall) {
            do{
                DispatchQueue.main.async {
                    try?self.openIdBoxViewControllerFor2SIdentity(call)
                }
            }
            catch let error{
                
            }
        }
        @objc(scanAdditionalDocuments:) func scanAdditionalDocuments(_ call: CAPPluginCall) {
            do{
                DispatchQueue.main.async {
                   try? self.openIdBoxViewControllerForAdditionalDocumewnt(call)
                }
            }
            catch let error{
                
            }
        }
        @objc func startListeningVideoSignalR(_ call: CAPPluginCall) {
            do{
                viewcontroller.idboxModule.setCallback(call)
                var arg = call.getString("options") ?? "" as! String
                var options = try (JSONSerialization.jsonObject(with: arg.data(using: .utf8)!, options: []) as? [String:Any])!
                try?self.viewcontroller.startSignalR(options:options,token:self.onBoardhttp.getUuid)
            }
            catch let error{
                
            }
        }
        @objc(startVideoSession:) func startVideoSession(_ call: CAPPluginCall) {
            do{
//                self.pluginResult = CDVPluginResult(status: CDVCommandStatus_OK, messageAs: "Method not Supported")
//                self.commandDelegate!.send(self.pluginResult, callbackId: call.callbackId)
            }
            catch let error{
                
            }
        }
        func openIdBoxViewController (_ call: CAPPluginCall) throws{
            viewcontroller.idboxModule.setCallback(call)
                var arg = call.getString("options") ?? "" as! String
                var options = try (JSONSerialization.jsonObject(with: arg.data(using: .utf8)!, options: []) as? [String:Any])!
            let root = self.bridge?.viewController;
            root?.modalPresentationStyle = UIModalPresentationStyle.fullScreen;
            viewcontroller.openCameraController(options: options,root: root!);

        }
        func openIdBoxViewControllerFor1SIdentity(_ call: CAPPluginCall)throws{
            viewcontroller.idboxModule.setCallback(call)

                var arg = call.getString("options") ?? "" as! String
                var options = try (JSONSerialization.jsonObject(with: arg.data(using: .utf8)!, options: []) as? [String:Any])!
            let root = self.bridge?.viewController;
            root?.modalPresentationStyle = UIModalPresentationStyle.fullScreen;
            viewcontroller.UploadSingleSideId(options: options,root: root!)
        }
        func openIdBoxViewControllerFor2SIdentity(_ call: CAPPluginCall)throws{
            viewcontroller.idboxModule.setCallback(call)
            self.viewcontroller.cameraDoubleSideIdController.cameraDoubleIdDelegate = self


                var arg = call.getString("options") ?? "" as! String
                var options = try (JSONSerialization.jsonObject(with: arg.data(using: .utf8)!, options: []) as? [String:Any])!
            let root = self.bridge?.viewController;
            root?.modalPresentationStyle = UIModalPresentationStyle.fullScreen;
            viewcontroller.UploadIdAction(options: options,root: root!)
        }
        func openIdBoxViewControllerForAdditionalDocumewnt(_ call: CAPPluginCall)throws{
            viewcontroller.idboxModule.setCallback(call)

                var arg = call.getString("options") ?? "" as! String
                var options = try (JSONSerialization.jsonObject(with: arg.data(using: .utf8)!, options: []) as? [String:Any])!
            let root = self.bridge?.viewController;
            root?.modalPresentationStyle = UIModalPresentationStyle.fullScreen;
            viewcontroller.UploadDocsAction(options: options,root: root!)
        }
        func openIdBoxViewControllerForVideo(_ call: CAPPluginCall)throws{
            //self.bridge?.viewController!.dismiss(animated: true);
            viewcontroller.idboxModule.setCallback(call)

            let arg = call.getString("options") ?? ""
            let options = try (JSONSerialization.jsonObject(with: arg.data(using: .utf8)!, options: []) as? [String:Any])!
//            DispatchQueue.main.async {
                let root = self.bridge?.viewController;
                root?.modalPresentationStyle = UIModalPresentationStyle.fullScreen;
                self.viewcontroller.openCameraControllerForVideo(options: options,root: root!);
//            }
            
        }
        func openIdBoxViewControllerForVideoQueue(_ call: CAPPluginCall)throws{
            viewcontroller.idboxModule.setCallback(call)
                var arg = call.getString("options") ?? "" as! String
                var options = try (JSONSerialization.jsonObject(with: arg.data(using: .utf8)!, options: []) as? [String:Any])!
                viewcontroller.videoCallQueueAction(options: options)
        }
    
    
    
    ///sssss
    ///
    ///
    
    // MARK: - Set Meta Data Method
    public func returnProgress(progress: Double) {
        //kk
    }
    func setMetaFunction()
    {
        let dict = NSMutableDictionary()
        dict.setValue("Student", forKey: "ProfessionName")
        let MandatoryDocumentTypes = NSMutableArray()
        MandatoryDocumentTypes.add("PoI")
        MandatoryDocumentTypes.add("PoT")
        MandatoryDocumentTypes.add("PoP")
        dict.setValue(MandatoryDocumentTypes, forKey: "MandatoryDocumentTypes")
        dict.setValue("O06", forKey: "ProfessionCode")
        onBoardhttp.setBodyParamMeta(bodyParamMeta: dict)
        onBoardhttp.setRequestMetaData()
    }
    


    public func OnApiCallFinished(title: String, withCode: Int, andResponse: String, andImageSize: Int) {
        
        if title == "ContractForm" || title == "ContractFormAthex" {
            var dict1 = Dictionary<String, Any>()
//
            if let data = andResponse.data(using: String.Encoding.utf8) {
                do {
                    dict1 = try (JSONSerialization.jsonObject(with: data, options: []) as? [String:Any])!
                    //                            print(" select json AlphaBank_flow_1 dict--->> \(dict)")

                } catch let error as NSError {
                    print(error)
                }
            }
//            print("serverBase64")
////            if(dict1["Data"] != nil){
//                print(dict1["Data"])
//                self.promiseRes.returnPromise(message:dict1)
////            }else{
////                self.promiseRes.returnPromise(message:andResponse)
////            }
            self.promiseRes.callback.resolve(dict1)

        }
        else{
            self.promiseRes.returnPromise(message:andResponse)
        }
    }
    
    // MARK: - Connection Error

    public func onConnectionError() {
        
    }
    
    

        // MARK: - Video Session Dissconnect
        
    public func onVideoSessionDisconnect(message: String) {
            self.promiseRes.returnPromise(message:message)
        }
        
        // MARK: - Video Call Finish with Code
        
    public func OnVideoCallFinishedWithCode(title: String, andResponse: Int, response: String) {
            
            print("response \(response)")
//
            if response.contains("TokBoxApiKey") {
                let res = response.components(separatedBy: "|")
                let TokBoxApiKey = res[1].components(separatedBy: ":")
                self.viewcontroller.videoController.kApiKey = TokBoxApiKey[1]
                let TokBoxSessionId = res[0].components(separatedBy: ":")
                self.viewcontroller.videoController.kSessionId = TokBoxSessionId[1]
                let TokBoxToken = res[2].components(separatedBy: ":")
                self.viewcontroller.videoController.kToken = TokBoxToken[1]
                self.viewcontroller.videoController.startSession()
                let root = self.bridge?.viewController;
                root!.modalPresentationStyle = UIModalPresentationStyle.fullScreen;
                root!.present(self.viewcontroller.videoController, animated: true, completion: nil)

            }
            
        }
        
        // MARK: - Vide Call Queue Finished With Code
        
    public func OnVideoCallQueueFinishedWithCode(title: String, andResponse: Int, response: String) {
            
    //        let alert = UIAlertController(title: "call queue \(title) response code: \(andResponse)", message: "\(response)", preferredStyle: .alert)
    //        alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: { (action: UIAlertAction!) in
    //            print("Handle Ok Logic here")
    //            if andResponse == 200 {
    //
    //            }
    //        }))
    //
    //        self.present(alert, animated: true)

           self.promiseRes.returnPromise(message:" message: \(response)")

        }
        
        // MARK: - Video Session Connected
        
    public func onVideoSessionConnect() {
            
        }
        
        // MARK: - Connection Error
        
    public func errorMessage(message: String){
            print("ERROR MSG\(message)")
            
        }
        
        
        // MARK: - Alert Method
        
        //    func customAlert(title:String,message:String){
        //        let alert = UIAlertController(title: title, message: message, preferredStyle: .alert)
        //        alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: {_ in
        //        }))
        //        self.present(alert, animated: true)
        //    }
        
        
        // MARK: - Finish Alert Message
        
    public func onFinish(message: String) {
            print("message \(message)")
            self.promiseRes.returnPromise(message:message)
        }
        
        // MARK: - Signal R Video Call Queue order Alert Message
        
    public func onSignalrMessageWithOrder(order: Int, andWaitingTime: Int) {
            //        customAlert(title: "Video Call Queue order: \(order)", message: "waiting time: \(andWaitingTime)")
            self.promiseRes.returnPromise(message:"Video Call Queue order: \(order) waiting time: \(andWaitingTime)")
            
        }
        
        // MARK: - Singla R Alert Verification Message Method
        
    public func onSignalrMessage(message: String) {
            self.promiseRes.returnPromise(message:"Verification is : \(message)")
            //customAlert(title: "Verification is ", message: message)
        }
        
        // MARK: - Disable Micraphone
        
    public func onMicrophoneDisabled() {
            
        }
        
        // MARK: - Camera Finish After Taking Images of Docs
        
    public func OnCameraDocFinished(title: String, withImageList: Array<UIImage>, prototypeImage: Array<UIImage>) {
//            print("CameraDOC has finished with title: %@ and list size: %lu  and the prototype image width is: %f",title,withImageList.count,prototypeImage.count);
//            if(withImageList.count<3){
//                self.promiseRes.returnPromise(message: "CameraDOC has finished with title: %@ and list size: %lu  and the prototype image width is: %f\(title)\(withImageList.count)\(prototypeImage.count)")
//            }
        }
        
        // MARK: - Camera Finish with title, list size and prototype image width
        
    public func OnCameraFinished(title: String, withImageCroped: UIImage, prototypeImage: UIImage) {
//            print("Camera has finished with title: %@ and list size: %lu  and the prototype image width is: %f\(title)\(withImageList.count)\(prototypeImage.count)");
            
        }
        
        // MARK: - Camera Finish with ID title, list size and prototype image width
        
    public func OnCameraIdFinished(title: String, withImageList: Array<UIImage>, prototypeImage: Array<UIImage>) {
//            print("CameraID has finished with title: %@ and list size: %lu  and the prototype image width is: %f",title,withImageList.count,prototypeImage.count);
            if(withImageList.count<1){
                self.promiseRes.returnPromise(message: "CameraID has finished with title: %@ and list size: %lu  and the prototype image width is: %f\(title)\(withImageList.count)\(prototypeImage.count)" )
            }
        }
        
    public func OnCameraDoubleIdFinished(title: String, withImageList: Array<UIImage>, prototypeImage: Array<UIImage>) {
            //self.promiseRes.returnPromise(message:andResponse)
//            print("CameraDoubleID has finished with title: %@ and list size: %lu  and the prototype image width is: %f",title,withImageList.count,prototypeImage.count);
            if(withImageList.count<2){
                self.promiseRes.returnPromise(message: "CameraDoubleID has finished with title: %@ and list size: %lu  and the prototype image width is: %f\(title)\(withImageList.count)\(prototypeImage.count)" )
            }
            
        }
    
    public func OnUploadDoubleIdFinishedWithCode(code: Int, andResponse: String) {
                print("DoubleID \(andResponse)")
                self.promiseRes.returnPromise(message:andResponse)
        }
        // MARK: - Upload ID Finish Alert Showing Responce
        
    public func OnUploadIdFinishedWithCode(code: Int, andResponse: String) {
            //customAlert(title: "Upload Id", message: "\(andResponse)")
            self.promiseRes.returnPromise(message:andResponse)
            
        }
        
        
        
        // MARK: - Uplading Docs Finished Alert Showing Responce
        
    public func OnUploadDocFinishedWithCode(code: Int, andResponse: String) {
            //customAlert(title: "Upload Docs", message: "\(andResponse)")
            self.promiseRes.returnPromise(message:andResponse)
            
        }
        
        // MARK: - Uplading Selfie Finished Alert Showing Responce
        
    public func OnUploadSelfieFinishedWithCode(code: Int, andResponse: String) {
            //customAlert(title: "Upload Selfie", message: "\(code) and response \(andResponse)")
            self.promiseRes.returnPromise(message:andResponse)
            
        }
        
       
        // MARK: - Api Call Finished Alert Showing Responce
        

        
    public func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
            print("Imgpicker \(info)")
            picker.dismiss(animated: true, completion: nil)
            
            if let image = info[.originalImage] as? UIImage {
                //imageToString =  convertImageToBase64(image: image)
            }
            
            
        }
        
    public func onConnectError(message: String) {
            print("Error connection \(message)")
        }
        
    public  func onPublisherError(message: String) {
            print("Error publish \(message)")
        }
        
    public  func onSubscribeError(message: String) {
            print("Error subscribe \(message)")
        }
        
        
    public  func onProgressVideoBandwidthSubscriber(bandwidth_video: UInt64, packetLost: Float) {
            print("bandwidth_video \(bandwidth_video)  packetLost \(packetLost)")
        }
        
     public func networkTestDidCompleteWithResult(result: IdBoxFramwork.OTNetworkTestResult, error: OTError) {
            var resultMessage:String = ""
            
            if(result == IdBoxFramwork.OTNetworkTestResult.OTNetworkTestResultVideoAndVoice)
            {
                resultMessage = "Result : OTNetworkTestResultVideoAndVoice";
                
            }
            else if(result == IdBoxFramwork.OTNetworkTestResult.OTNetworkTestResultVoiceOnly)
            {
                resultMessage = String(format: "Result : OTNetworkTestResultVoiceOnly,Error %@", arguments: [error.localizedDescription])
            }
            else
            {
                resultMessage = String(format: "Result : OTNetworkTestResultNotGood,Error %@", arguments: [error.localizedDescription])
            }
            print(resultMessage);
        }
}
extension IdboxPluginPlugin {
   
        
    
    
}
class PromiseResponse : CAPPlugin{
//    var call = CDVInvokedUrlCommand()
//    var pluginResult = CDVPluginResult(status: CDVCommandStatus_ERROR)
//
    var callback = CAPPluginCall()
    func setCallback (_ call: CAPPluginCall){
        self.callback = call
    }
    func returnPromise(message:String){
        do{
        let jsonObject: NSMutableDictionary = NSMutableDictionary()
//        var jsonBody = try (JSONSerialization.jsonObject(with: message.data(using: String.Encoding.utf8)!, options: []) as? [String:Any])!
       // self.callback.keepAlive = true
            jsonObject.setValue(message, forKey: "response")
            self.callback.resolve(jsonObject as! PluginCallResultData)
//        self.pluginResult = CDVPluginResult(status: CDVCommandStatus_OK, messageAs: message)
//        self.pluginResult?.setKeepCallbackAs(true)
//        self.commandDelegate!.send(self.pluginResult, callbackId: self.call.callbackId)
    } catch let error as NSError {
        print(error)
    }
    }
}
