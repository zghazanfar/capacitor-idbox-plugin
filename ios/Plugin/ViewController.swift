//
//  ViewController.swift
//  MyApp
//
//  Created by apple on 03/05/2023.
//

import Foundation
import IdboxSwiftFramwork
import OpenTok

class ViewController: UIViewController, OnBoardHttpDelegate, OnBoardCameraDelegate, DocCameraDelegate,IdCameraDelegate,VideoDelegate,DoubleIdCameraDelegate, UIImagePickerControllerDelegate,UINavigationControllerDelegate
{
    
    

    var signalrMessageFunction = "ReceiveMessage"
    var onBoardHttp = OnBoardHttp()
    var cameracontroller = CameraController()
    var cameraDocController = BigDocCameraController()
    var cameraIdController = IdCameraController()
    var videoController = VideoController()
    var cameraDoubleSideIdController = DoubleSideIdController()
    var prefixUrl = "/orchestration"
    var idboxModule = PromiseResponse()
    @IBOutlet weak var textLabel:UILabel?
    
    override func viewDidLoad()
    {
        super.viewDidLoad()

    // onBoardHttp.delegate = self
//        cameracontroller.cameradelegate = module
//        cameraIdController.cameradelegate = module
//        cameraDocController.cameraDocDelegate = module
//        
//        //onBoardHttp.setViewController(controller: self)
//        videoController.videoDelegate = module as! any VideoDelegate
        //self.idboxModule = PromiseResponse()
        
        
    }
    
    @IBAction func onButtonTap(sender: UIButton)
    {
    }
    func setPrefixUrl(prefix:String){
        self.prefixUrl = prefix
    }
    // MARK: - Upload Single ID Action
    func UploadIdAction(options:Dictionary<String, Any>,root:UIViewController) {
        do{
        var urlString = options["url"]
        var url = URL(string: urlString as! String)
        var domain = url?.standardized
        cameraDoubleSideIdController.pageControlColor = UIColor.red
        cameraDoubleSideIdController.backgroundColorView = .black
        cameraDoubleSideIdController.titleButtonColor = .black
        if((options["smallDocumentRect"]) != nil){
            var selfieRect = options["smallDocumentRect"] as! Dictionary<String, Any>
            var cgrect = CGRect(x:selfieRect["xCordinate"] as! Int,y:selfieRect["yCordinate"] as! Int,width:selfieRect["width"] as! Int,height:selfieRect["height"] as! Int)
            cameraDoubleSideIdController.smallDocumentRect = cgrect
        }
        if((options["previewBoundingRect"]) != nil){
            var carouselRect = options["previewBoundingRect"] as! Dictionary<String, Any>
            var cgrect = CGRect(x:carouselRect["xCordinate"] as! Int,y:carouselRect["yCordinate"] as! Int,width:carouselRect["width"] as! Int,height:carouselRect["height"] as! Int)
            cameraDoubleSideIdController.previewBoundingRect = cgrect
        }
        if((options["headerText"]) != nil){
            cameraDoubleSideIdController.headerText = options["headerText"] as! String
        }
        if((options["headerTextFontSize"]) != nil)
        {
            cameraDoubleSideIdController.headerTextFontSize = options["headerTextFontSize"] as! CGFloat
        }
        if(options["headerTextFontColor"] != nil)
        {
            cameraDoubleSideIdController.headerTextFontColor = options["headerTextFontColor"] as! String
        }
        if(options["autoCaptureTimeOut"] != nil)
        {
            cameraDoubleSideIdController.autoCaptureTimeOut = options["autoCaptureTimeOut"] as! Double
        }
        if(options["footerText"] != nil)
        {
            cameraDoubleSideIdController.footerText = options["footerText"] as! String
        }
        if(options["iconCameraBase64Dark"] != nil)
        {
            cameraDoubleSideIdController.iconCameraBase64Dark = options["iconCameraBase64Dark"] as! String
        }
        if(options["previewHeaderText"] != nil)
        {
            cameraDoubleSideIdController.previewHeaderText = options["previewHeaderText"] as! String
        }
        if(options["previewHeaderTextFontColor"] != nil)
        {
            cameraDoubleSideIdController.previewHeaderTextFontColor = options["previewHeaderTextFontColor"] as! String
        }
        if(options["previewHeaderTextFontSize"] != nil)
        {
            cameraDoubleSideIdController.previewHeaderTextFontSize = options["previewHeaderTextFontSize"] as! Int
        }
        if(options["previewHeaderMessageAlignment"] != nil)
        {
            cameraDoubleSideIdController.previewHeaderMessageAlignment = options["previewHeaderMessageAlignment"] as! Int
        }
        if((options["previewBottomText"]) != nil)
        {
            cameraDoubleSideIdController.previewBottomText = options["previewBottomText"] as! String
        }
        if((options["previewBottomMessageAlignment"]) != nil)
        {
            cameraDoubleSideIdController.previewBottomMessageAlignment = options["previewBottomMessageAlignment"]as! NSTextAlignment
        }
        if(options["previewBottomMessageFontSize"] != nil)
        {
            cameraDoubleSideIdController.previewBottomMessageFontSize = options["previewBottomMessageFontSize"] as! Int
        }
        if(options["previewBottomMessageFontColor"] != nil)
        {
            cameraDoubleSideIdController.previewBottomMessageFontColor = options["previewBottomMessageFontColor"] as! String
        }
        if(options["previewBottomTextRect"] != nil)
        {
            cameraDoubleSideIdController.previewBottomTextRect = options["previewBottomTextRect"] as! CGRect
        }
        if(options["retakeButtonText"] != nil)
        {
            cameraDoubleSideIdController.retakeButtonText = options["retakeButtonText"] as! String
        }
        if(options["previewRetakeTextRect"] != nil)
        {
            // cameraDoubleSideIdController.previewRetakeTextRect = options["previewRetakeTextRect"] as! CGRect
        }
        if(options["previewRetakeTextFontSize"] != nil)
        {
            //cameraDoubleSideIdController.previewRetakeTextFontSize = options["previewRetakeTextFontSize"] as! Float
        }
        if(options["previewRetakeTextBackColor"] != nil)
        {
            cameraDoubleSideIdController.previewRetakeTextBackColor = options["previewRetakeTextBackColor"] as! String
        }
        if(options["previewAcceptButtonText"] != nil)
        {
            cameraDoubleSideIdController.previewAcceptButtonText = options["previewAcceptButtonText"] as! String
        }
        if(options["keepButtonText"] != nil)
        {
            cameraDoubleSideIdController.keepButtonText = options["keepButtonText"] as! String
        }
        if(options["previewAcceptTextRect"] != nil)
        {
            cameraDoubleSideIdController.previewAcceptTextRect = options["previewAcceptTextRect"] as! CGRect
        }
        if(options["previewAcceptTextFontColor"] != nil)
        {
            cameraDoubleSideIdController.previewAcceptTextFontColor = options["previewAcceptTextFontColor"] as! String
        }
        if(options["previewAcceptTextBackColor"] != nil)
        {
            cameraDoubleSideIdController.previewAcceptTextBackColor = options["previewAcceptTextBackColor"] as! String
        }
        if(options["previewButtonsCornerRadius"] != nil)
        {
            cameraDoubleSideIdController.previewButtonsCornerRadius = options["previewButtonsCornerRadius"] as! CGFloat
        }
        if(options["previewButtonsBorderColor"] != nil)
        {
            //cameraDoubleSideIdController.previewButtonsBorderColor = options["previewButtonsBorderColor"] as! CGColor
        }
        if(options["previewButtonsBorderWidth"] != nil)
        {
            cameraDoubleSideIdController.previewButtonsBorderWidth = options["previewButtonsBorderWidth"] as! CGFloat
        }
        if(options["previewBackgroundColor"] != nil)
        {
            cameraDoubleSideIdController.previewBackgroundColor = options["previewBackgroundColor"] as! String
        }
        if(options["previewSliderColor"] != nil)
        {
            cameraDoubleSideIdController.previewSliderColor = options["previewSliderColor"] as! String
        }
        if(options["previewHeaderTextRect"] != nil)
        {
            var headerTextRect = options["previewHeaderTextRect"] as! Dictionary<String, Any>
            var cgrect = CGRect(x:headerTextRect["xCordinate"] as! Int,y:headerTextRect["yCordinate"] as! Int,width:headerTextRect["width"] as! Int,height:headerTextRect["height"] as! Int)
            cameraDoubleSideIdController.previewHeaderTextRect = cgrect
        }
        if(options["previewHeaderTextAlignment"] != nil)
        {
            cameraDoubleSideIdController.previewHeaderTextAlignment = options["previewHeaderTextAlignment"] as! NSTextAlignment
        }
        if(options["moveCameraCloserText"] != nil)
        {
            cameraDoubleSideIdController.moveCameraCloserText = options["moveCameraCloserText"] as! String
        }
        if((options["keepCameraSteadyText"]) != nil){
            cameraDoubleSideIdController.keepCameraSteadyText = options["keepCameraSteadyText"] as! String
        }
        if((options["frontFrameBottomText"]) != nil){
            cameraDoubleSideIdController.frontFrameBottomText = options["frontFrameBottomText"] as! String
        }
        if((options["switchingManualModeText"]) != nil){
            cameraDoubleSideIdController.switchingManualModeText = options["switchingManualModeText"] as! String
        }
        
        
        
        cameraDoubleSideIdController.initDoubleIdCameraController(urlPrefix: "\(prefixUrl)/UploadIdentity2Sides/", server: domain?.absoluteString as! String, port: options["port"] as! Int, hawkId: options["hawkId"] as! String, hawkKey: options["hawkKey"] as! String, baseController: root)
        
        cameraDoubleSideIdController.captureDoubleSideIdImages()
    }catch let error {
        
    }
    }
    // MARK: - Uploading Single Side ID Action
    func UploadSingleSideId(options:Dictionary<String, Any>,root:UIViewController) {
        var urlString = options["url"]
        var url = URL(string: urlString as! String)
        var domain = url?.standardized
        cameraIdController.pageControlColor = UIColor.red
        cameraIdController.backgroundColorView = .black.withAlphaComponent(0.8)
        cameraIdController.titleButtonColor = .black
        if((options["smallDocumentRect"]) != nil){
            var selfieRect = options["smallDocumentRect"] as! Dictionary<String, Any>
            var cgrect = CGRect(x:selfieRect["xCordinate"] as! Int,y:selfieRect["yCordinate"] as! Int,width:selfieRect["width"] as! Int,height:selfieRect["height"] as! Int)
            cameraIdController.smallDocumentRect = cgrect
        }
        if((options["previewBoundingRect"]) != nil){
            var carouselRect = options["previewBoundingRect"] as! Dictionary<String, Any>
            var cgrect = CGRect(x:carouselRect["xCordinate"] as! Int,y:carouselRect["yCordinate"] as! Int,width:carouselRect["width"] as! Int,height:carouselRect["height"] as! Int)
            cameraIdController.previewBoundingRect = cgrect
        }
        if((options["headerText"]) != nil){
            cameraIdController.headerText = options["headerText"] as! String
        }
        if((options["headerTextFontSize"]) != nil)
        {
            cameraIdController.headerTextFontSize = options["headerTextFontSize"] as! CGFloat
        }
        if(options["headerTextFontColor"] != nil)
        {
            cameraIdController.headerTextFontColor = options["headerTextFontColor"] as! String
        }
        if(options["autoCaptureTimeOut"] != nil)
        {
            cameraIdController.autoCaptureTimeOut = options["autoCaptureTimeOut"] as! Double
        }
        if(options["footerText"] != nil)
        {
            cameraIdController.footerText = options["footerText"] as! String
        }
        if(options["iconCameraBase64Dark"] != nil)
        {
            cameraIdController.iconCameraBase64Dark = options["iconCameraBase64Dark"] as! String
        }
        if(options["previewHeaderText"] != nil)
        {
            cameraIdController.previewHeaderText = options["previewHeaderText"] as! String
        }
        if(options["previewHeaderTextFontColor"] != nil)
        {
            cameraIdController.previewHeaderTextFontColor = options["previewHeaderTextFontColor"] as! String
        }
        if(options["previewHeaderTextFontSize"] != nil)
        {
            cameraIdController.previewHeaderTextFontSize = options["previewHeaderTextFontSize"] as! Int
        }
        if(options["previewHeaderMessageAlignment"] != nil)
        {
            cameraIdController.previewHeaderMessageAlignment = options["previewHeaderMessageAlignment"] as! Int
        }
        if((options["previewBottomText"]) != nil)
        {
            cameraIdController.previewBottomText = options["previewBottomText"] as! String
        }
        if((options["previewBottomMessageAlignment"]) != nil)
        {
            cameraIdController.previewBottomMessageAlignment = options["previewBottomMessageAlignment"]as! NSTextAlignment
        }
        if(options["previewBottomMessageFontSize"] != nil)
        {
            cameraIdController.previewBottomMessageFontSize = options["previewBottomMessageFontSize"] as! Int
        }
        if(options["previewBottomMessageFontColor"] != nil)
        {
            cameraIdController.previewBottomMessageFontColor = options["previewBottomMessageFontColor"] as! String
        }
        if(options["previewBottomTextRect"] != nil)
        {
            cameraIdController.previewBottomTextRect = options["previewBottomTextRect"] as! CGRect
        }
        if(options["retakeButtonText"] != nil)
        {
            cameraIdController.retakeButtonText = options["retakeButtonText"] as! String
        }
        if(options["previewRetakeTextRect"] != nil)
        {
           // cameraIdController.previewRetakeTextRect = options["previewRetakeTextRect"] as! CGRect
        }
        if(options["previewRetakeTextFontSize"] != nil)
        {
            //cameraIdController.previewRetakeTextFontSize = options["previewRetakeTextFontSize"] as! Float
        }
        if(options["previewRetakeTextBackColor"] != nil)
        {
            cameraIdController.previewRetakeTextBackColor = options["previewRetakeTextBackColor"] as! String
        }
        if(options["previewAcceptButtonText"] != nil)
        {
            cameraIdController.previewAcceptButtonText = options["previewAcceptButtonText"] as! String
        }
        if(options["keepButtonText"] != nil)
        {
            cameraIdController.keepButtonText = options["keepButtonText"] as! String
        }
        if(options["previewAcceptTextRect"] != nil)
        {
            cameraIdController.previewAcceptTextRect = options["previewAcceptTextRect"] as! CGRect
        }
        if(options["previewAcceptTextFontColor"] != nil)
        {
            cameraIdController.previewAcceptTextFontColor = options["previewAcceptTextFontColor"] as! String
        }
        if(options["previewAcceptTextBackColor"] != nil)
        {
            cameraIdController.previewAcceptTextBackColor = options["previewAcceptTextBackColor"] as! String
        }
        if(options["previewButtonsCornerRadius"] != nil)
        {
            cameraIdController.previewButtonsCornerRadius = options["previewButtonsCornerRadius"] as! CGFloat
        }
        if(options["previewButtonsBorderColor"] != nil)
        {
            //cameraIdController.previewButtonsBorderColor = options["previewButtonsBorderColor"] as! CGColor
        }
        if(options["previewButtonsBorderWidth"] != nil)
        {
            cameraIdController.previewButtonsBorderWidth = options["previewButtonsBorderWidth"] as! CGFloat
        }
        if(options["previewBackgroundColor"] != nil)
        {
            cameraIdController.previewBackgroundColor = options["previewBackgroundColor"] as! String
        }
        if(options["previewSliderColor"] != nil)
        {
            cameraIdController.previewSliderColor = options["previewSliderColor"] as! String
        }
        if(options["previewHeaderTextRect"] != nil)
        {
            var headerTextRect = options["previewHeaderTextRect"] as! Dictionary<String, Any>
            var cgrect = CGRect(x:headerTextRect["xCordinate"] as! Int,y:headerTextRect["yCordinate"] as! Int,width:headerTextRect["width"] as! Int,height:headerTextRect["height"] as! Int)
            cameraIdController.previewHeaderTextRect = cgrect
        }
        if(options["previewHeaderTextAlignment"] != nil)
        {
            cameraIdController.previewHeaderTextAlignment = options["previewHeaderTextAlignment"] as! NSTextAlignment
        }
        if(options["moveCameraCloserText"] != nil)
        {
            cameraIdController.moveCameraCloserText = options["moveCameraCloserText"] as! String
        }
        if((options["keepCameraSteadyText"]) != nil){
            cameraIdController.keepCameraSteadyText = options["keepCameraSteadyText"] as! String
        }
        if((options["frontFrameBottomText"]) != nil){
            cameraIdController.frontFrameBottomText = options["frontFrameBottomText"] as! String
        }
        if((options["switchingManualModeText"]) != nil){
            cameraIdController.switchingManualModeText = options["switchingManualModeText"] as! String
        }
        cameraIdController.carouselRect = CGRect(x: 5, y:20, width: self.view.frame.width, height: self.view.frame.height/2+80)
        cameraIdController.initIdCameraController(urlPrefix: "\(prefixUrl)/UploadIdentitySingleSide/", server: domain?.absoluteString as! String, port: options["port"] as! Int, hawkId: options["hawkId"] as! String, hawkKey: options["hawkKey"] as! String, documentType: "singleside", baseController: root)
        cameraIdController.uploadId()
        
    }
    // MARK: - Uploading Docs Action
    func UploadDocsAction(options:Dictionary<String, Any>,root:UIViewController) {
        var urlString = options["url"]
        var url = URL(string: urlString as! String)
        var domain = url?.standardized
        cameraDocController.pageControlColor = UIColor.red
        cameraDocController.backgroundColorView = .black.withAlphaComponent(1.0)
        cameraDocController.titleButtonColor = .black
        if((options["smallDocumentRect"]) != nil){
            var selfieRect = options["smallDocumentRect"] as! Dictionary<String, Any>
            var cgrect = CGRect(x:selfieRect["xCordinate"] as! Int,y:selfieRect["yCordinate"] as! Int,width:selfieRect["width"] as! Int,height:selfieRect["height"] as! Int)
            cameraDocController.smallDocumentRect = cgrect
        }
        if((options["previewBoundingRect"]) != nil){
            var carouselRect = options["previewBoundingRect"] as! Dictionary<String, Any>
            var cgrect = CGRect(x:carouselRect["xCordinate"] as! Int,y:carouselRect["yCordinate"] as! Int,width:carouselRect["width"] as! Int,height:carouselRect["height"] as! Int)
            cameraDocController.previewBoundingRect = cgrect
        }
        if((options["headerText"]) != nil){
            cameraDocController.headerText = options["headerText"] as! String
        }
        if((options["headerTextFontSize"]) != nil)
        {
            cameraDocController.headerTextFontSize = options["headerTextFontSize"] as! CGFloat
        }
        if(options["headerTextFontColor"] != nil)
        {
            cameraDocController.headerTextFontColor = options["headerTextFontColor"] as! String
        }
        if(options["autoCaptureTimeOut"] != nil)
        {
            cameraDocController.autoCaptureTimeOut = options["autoCaptureTimeOut"] as! Double
        }
        if(options["footerText"] != nil)
        {
            cameraDocController.footerText = options["footerText"] as! String
        }
        if(options["iconCameraBase64Dark"] != nil)
        {
            cameraDocController.iconCameraBase64Dark = options["iconCameraBase64Dark"] as! String
        }
        if(options["previewHeaderText"] != nil)
        {
            cameraDocController.previewHeaderText = options["previewHeaderText"] as! String
        }
        if(options["previewHeaderTextFontColor"] != nil)
        {
            cameraDocController.previewHeaderTextFontColor = options["previewHeaderTextFontColor"] as! String
        }
        if(options["previewHeaderTextFontSize"] != nil)
        {
            cameraDocController.previewHeaderTextFontSize = options["previewHeaderTextFontSize"] as! Int
        }
        if(options["previewHeaderMessageAlignment"] != nil)
        {
            cameraDocController.previewHeaderMessageAlignment = options["previewHeaderMessageAlignment"] as! Int
        }
        if((options["previewBottomText"]) != nil)
        {
            cameraDocController.previewBottomText = options["previewBottomText"] as! String
        }
        if((options["previewBottomMessageAlignment"]) != nil)
        {
            cameraDocController.previewBottomMessageAlignment = options["previewBottomMessageAlignment"]as! NSTextAlignment
        }
        if(options["previewBottomMessageFontSize"] != nil)
        {
            cameraDocController.previewBottomMessageFontSize = options["previewBottomMessageFontSize"] as! Int
        }
        if(options["previewBottomMessageFontColor"] != nil)
        {
            cameraDocController.previewBottomMessageFontColor = options["previewBottomMessageFontColor"] as! String
        }
        if(options["previewBottomTextRect"] != nil)
        {
            cameraDocController.previewBottomTextRect = options["previewBottomTextRect"] as! CGRect
        }
        if(options["retakeButtonText"] != nil)
        {
            cameraDocController.retakeButtonText = options["retakeButtonText"] as! String
        }
        if(options["previewRetakeTextRect"] != nil)
        {
           // cameraDocController.previewRetakeTextRect = options["previewRetakeTextRect"] as! CGRect
        }
        if(options["previewRetakeTextFontSize"] != nil)
        {
            //cameraDocController.previewRetakeTextFontSize = options["previewRetakeTextFontSize"] as! Float
        }
        if(options["previewRetakeTextBackColor"] != nil)
        {
            cameraDocController.previewRetakeTextBackColor = options["previewRetakeTextBackColor"] as! String
        }
        if(options["previewAcceptButtonText"] != nil)
        {
            cameraDocController.previewAcceptButtonText = options["previewAcceptButtonText"] as! String
        }
        if(options["keepButtonText"] != nil)
        {
            cameraDocController.keepButtonText = options["keepButtonText"] as! String
        }
        if(options["previewAcceptTextRect"] != nil)
        {
            cameraDocController.previewAcceptTextRect = options["previewAcceptTextRect"] as! CGRect
        }
        if(options["previewAcceptTextFontColor"] != nil)
        {
            cameraDocController.previewAcceptTextFontColor = options["previewAcceptTextFontColor"] as! String
        }
        if(options["previewAcceptTextBackColor"] != nil)
        {
            cameraDocController.previewAcceptTextBackColor = options["previewAcceptTextBackColor"] as! String
        }
        if(options["previewButtonsCornerRadius"] != nil)
        {
            cameraDocController.previewButtonsCornerRadius = options["previewButtonsCornerRadius"] as! CGFloat
        }
        if(options["previewButtonsBorderColor"] != nil)
        {
            //cameraDocController.previewButtonsBorderColor = options["previewButtonsBorderColor"] as! CGColor
        }
        if(options["previewButtonsBorderWidth"] != nil)
        {
            cameraDocController.previewButtonsBorderWidth = options["previewButtonsBorderWidth"] as! CGFloat
        }
        if(options["previewBackgroundColor"] != nil)
        {
            cameraDocController.previewBackgroundColor = options["previewBackgroundColor"] as! String
        }
        if(options["previewSliderColor"] != nil)
        {
            cameraDocController.previewSliderColor = options["previewSliderColor"] as! String
        }
        if(options["previewHeaderTextRect"] != nil)
        { var headerTextRect = options["previewHeaderTextRect"] as! Dictionary<String, Any>
            var cgrect = CGRect(x:headerTextRect["xCordinate"] as! Int,y:headerTextRect["yCordinate"] as! Int,width:headerTextRect["width"] as! Int,height:headerTextRect["height"] as! Int)
            cameraDocController.previewHeaderTextRect = cgrect
        }
        if(options["previewHeaderTextAlignment"] != nil)
        {
            cameraDocController.previewHeaderTextAlignment = options["previewHeaderTextAlignment"] as! NSTextAlignment
        }
        if(options["moveCameraCloserText"] != nil)
        {
            cameraDocController.moveCameraCloserText = options["moveCameraCloserText"] as! String
        }
        if((options["keepCameraSteadyText"]) != nil){
            cameraDocController.keepCameraSteadyText = options["keepCameraSteadyText"] as! String
        }
        if((options["frontFrameBottomText"]) != nil){
            cameraDocController.frontFrameBottomText = options["frontFrameBottomText"] as! String
        }
        if((options["switchingManualModeText"]) != nil){
            cameraDocController.switchingManualModeText = options["switchingManualModeText"] as! String
        }
        cameraDocController.hasPreview = true
        cameraDocController.hasUpload = true
        cameraDocController.trackImgManualRect = CGRect(x: 200, y: 550, width: 100, height: 100)
        cameraDocController.initDocCameraController(urlPrefix: "\(prefixUrl)/UploadAdditionalDocuments/", server: domain?.absoluteString as! String, port: options["port"] as! Int, hawkId: options["hawkId"] as! String, hawkKey: options["hawkKey"] as! String, baseController: root)
        cameraDocController.captureDocsImages()
    }
    // MARK: - Upload Selfie Action
    func openCameraController(options:Dictionary<String, Any>,root:UIViewController){
        do{
            var urlString = options["url"]
            var url = URL(string: urlString as! String)
            var domain = url?.standardized
            cameracontroller.backgroundColorView = .black.withAlphaComponent(0.8)
            cameracontroller.backgroundButtonColor = .blue
            cameracontroller.titleButtonColor = .white
            
            
            if((options["selfieRect"]) != nil){
                var selfieRect = options["selfieRect"] as! Dictionary<String, Any>
                var cgrect = CGRect(x:selfieRect["xCordinate"] as! Int,y:selfieRect["yCordinate"] as! Int,width:selfieRect["width"] as! Int,height:selfieRect["height"] as! Int)
                cameracontroller.selfieRect = cgrect
            }
            if((options["previewBoundingRect"]) != nil){
                var carouselRect = options["previewBoundingRect"] as! Dictionary<String, Any>
                var cgrect = CGRect(x:carouselRect["xCordinate"] as! Int,y:carouselRect["yCordinate"] as! Int,width:carouselRect["width"] as! Int,height:carouselRect["height"] as! Int)
                cameracontroller.previewBoundingRect = cgrect
            }
            if((options["headerText"]) != nil){
                cameracontroller.headerText = options["headerText"] as! String
            }
            if((options["keepCameraSteadyText"]) != nil){
                cameracontroller.keepCameraSteadyText = options["keepCameraSteadyText"] as! String
            }
            if((options["frontFrameBottomText"]) != nil){
                cameracontroller.frontFrameBottomText = options["frontFrameBottomText"] as! String
            }
            if((options["switchingManualModeText"]) != nil){
                cameracontroller.switchingManualModeText = options["switchingManualModeText"] as! String
            }
            if((options["headerTextFontSize"]) != nil)
            {
                cameracontroller.headerTextFontSize = options["headerTextFontSize"] as! CGFloat
            }
            if(options["headerTextFontColor"] != nil)
            {
                cameracontroller.headerTextFontColor = options["headerTextFontColor"] as! String
            }
            if(options["autoCaptureTimeOut"] != nil)
            {
                cameracontroller.autoCaptureTimeOut = options["autoCaptureTimeOut"] as! Double
            }
            if(options["footerText"] != nil)
            {
                cameracontroller.footerText = options["footerText"] as! String
            }
            if(options["iconCameraBase64Dark"] != nil)
            {
                cameracontroller.iconCameraBase64Dark = options["iconCameraBase64Dark"] as! String
            }
            if(options["previewHeaderText"] != nil)
            {
                cameracontroller.previewHeaderText = options["previewHeaderText"] as! String
            }
            if(options["previewHeaderTextFontColor"] != nil)
            {
                cameracontroller.previewHeaderTextFontColor = options["previewHeaderTextFontColor"] as! String
            }
            if(options["previewHeaderTextFontSize"] != nil)
            {
                cameracontroller.previewHeaderTextFontSize = options["previewHeaderTextFontSize"] as! Int
            }
            if(options["previewHeaderMessageAlignment"] != nil)
            {
                cameracontroller.previewHeaderMessageAlignment = options["previewHeaderMessageAlignment"] as! Int
            }
            if((options["previewBottomText"]) != nil)
            {
                cameracontroller.previewBottomText = options["previewBottomText"] as! String
            }
            if((options["previewBottomMessageAlignment"]) != nil)
            {
                cameracontroller.previewBottomMessageAlignment = options["previewBottomMessageAlignment"]as! NSTextAlignment
            }
            if(options["previewBottomMessageFontSize"] != nil)
            {
                cameracontroller.previewBottomMessageFontSize = options["previewBottomMessageFontSize"] as! Int
            }
            if(options["previewBottomMessageFontColor"] != nil)
            {
                cameracontroller.previewBottomMessageFontColor = options["previewBottomMessageFontColor"] as! String
            }
            if(options["previewBottomTextRect"] != nil)
            {
                cameracontroller.previewBottomTextRect = options["previewBottomTextRect"] as! CGRect
            }
            if(options["retakeButtonText"] != nil)
            {
                cameracontroller.retakeButtonText = options["retakeButtonText"] as! String
            }
            if(options["previewRetakeTextRect"] != nil)
            {
                //cameracontroller.previewRetakeTextRect = options["previewRetakeTextRect"] as! CGRect
            }
            if(options["previewRetakeTextFontSize"] != nil)
            {
                cameracontroller.previewRetakeTextFontSize = options["previewRetakeTextFontSize"] as! Float
            }
            if(options["previewRetakeTextBackColor"] != nil)
            {
                cameracontroller.previewRetakeTextBackColor = options["previewRetakeTextBackColor"] as! String
            }
            if(options["previewAcceptButtonText"] != nil)
            {
                cameracontroller.previewAcceptButtonText = options["previewAcceptButtonText"] as! String
            }
            if(options["keepButtonText"] != nil)
            {
                cameracontroller.keepButtonText = options["keepButtonText"] as! String
            }
            if(options["previewAcceptTextRect"] != nil)
            {
                // cameracontroller.previewAcceptTextRect = options["previewAcceptTextRect"] as! CGRect
            }
            if(options["previewAcceptTextFontColor"] != nil)
            {
                cameracontroller.previewAcceptTextFontColor = options["previewAcceptTextFontColor"] as! String
            }
            if(options["previewAcceptTextBackColor"] != nil)
            {
                cameracontroller.previewAcceptTextBackColor = options["previewAcceptTextBackColor"] as! String
            }
            if(options["previewButtonsCornerRadius"] != nil)
            {
                cameracontroller.previewButtonsCornerRadius = options["previewButtonsCornerRadius"] as! CGFloat
            }
            if(options["previewButtonsBorderColor"] != nil)
            {
                cameracontroller.previewButtonsBorderColor = options["previewButtonsBorderColor"] as! CGColor
            }
            if(options["previewButtonsBorderWidth"] != nil)
            {
                cameracontroller.previewButtonsBorderWidth = options["previewButtonsBorderWidth"] as! CGFloat
            }
            if(options["previewBackgroundColor"] != nil)
            {
                cameracontroller.previewBackgroundColor = options["previewBackgroundColor"] as! String
            }
            if(options["previewSliderColor"] != nil)
            {
                cameracontroller.previewSliderColor = options["previewSliderColor"] as! String
            }
            if(options["previewHeaderTextRect"] != nil)
            {
                var headerTextRect = options["previewHeaderTextRect"] as! Dictionary<String, Any>
                var cgrect = CGRect(x:headerTextRect["xCordinate"] as! Int,y:headerTextRect["yCordinate"] as! Int,width:headerTextRect["width"] as! Int,height:headerTextRect["height"] as! Int)
                cameracontroller.previewHeaderTextRect = cgrect
            }
            if(options["previewHeaderTextAlignment"] != nil)
            {
                cameracontroller.previewHeaderTextAlignment = options["previewHeaderTextAlignment"] as! NSTextAlignment
            }
            if(options["moveCameraCloserText"] != nil)
            {
                cameracontroller.moveCameraCloserText = options["moveCameraCloserText"] as! String
            }
            cameracontroller.initCameraController(urlPrefix: "\(prefixUrl)/UploadSelfie/", server: domain?.absoluteString as! String, port: options["port"] as! Int, hawkId: options["hawkId"] as! String, hawkKey: options["hawkKey"] as! String, baseController: root)
            cameracontroller.uploadSelfie()
        }catch let error{
            print(error)
        }
    }
    // MARK: - Video Call Action
    func openCameraControllerForVideo(options:Dictionary<String, Any>,root:UIViewController){
        var urlString = options["url"]
        var url = URL(string: urlString as! String)
        var domain = url?.standardized
        videoController.initVideoController(urlPrefix: "\(prefixUrl)/VideoCall/", server: domain?.absoluteString as! String, port: options["port"] as! Int, hawkId: options["hawkId"] as! String, hawkKey: options["hawkKey"] as! String,baseController: root)
        videoController.backgroundColor = .gray
        videoController.videoCall()
    }
    // MARK: - Video Call Queue Action
    func videoCallQueueAction(options:Dictionary<String, Any>) {
        videoController.useNetworkPublish = false
        videoController.endSignalR()
        videoController.signalrHub = options["apiPathVideo"] as! String
        videoController.startSignalR()
        var urlString = options["url"]
        var url = URL(string: urlString as! String)
        var domain = url?.standardized
        videoController.initVideoCallQueue(urlPrefix: "\(prefixUrl)/VideoCallQueue/", server: domain?.absoluteString as! String,  port: options["port"] as! Int, hawkId: options["hawkId"] as! String, hawkKey: options["hawkKey"] as! String, baseController: self)
        videoController.videoCallQueue()
    }
    // MARK: - SignalR Action
    func startSignalR(options:Dictionary<String, Any>,token:String) {
        var urlString = options["url"]
        var url = URL(string: urlString as! String)
        var domain = url?.standardized
        videoController.signalrURL = domain?.absoluteString as! String
        videoController.signalrPort = options["port"] as! Int
        videoController.signalrMessageFunction = signalrMessageFunction
        videoController.useSIGNALR = options["useSignalR"] as! Bool
        videoController.signalrHub = options["apiPath"] as! String
        print("Tokenn\(token)")
        videoController.signalrToken = token
        videoController.startSignalR()
    }
}


extension ViewController {
    
    // MARK: - Set Meta Data Method
    func returnProgress(progress: Double) {
        //ujh
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
        onBoardHttp.setBodyParamMeta(bodyParamMeta: dict)
        onBoardHttp.setRequestMetaData()
    }
    
    // MARK: - Video Session Dissconnect
    
    func onVideoSessionDisconnect(message: String) {
        print("\(message)")
    }
    
    // MARK: - Video Call Finish with Code
    
    func OnVideoCallFinishedWithCode(title: String, andResponse: Int, response: String) {
        
        print("response \(response)")
        
        if response.contains("TokBoxApiKey") {
            let res = response.components(separatedBy: "|")
            let TokBoxApiKey = res[1].components(separatedBy: ":")
            videoController.kApiKey = TokBoxApiKey[1]
            let TokBoxSessionId = res[0].components(separatedBy: ":")
            videoController.kSessionId = TokBoxSessionId[1]
            let TokBoxToken = res[2].components(separatedBy: ":")
            videoController.kToken = TokBoxToken[1]
            videoController.startSession()
            present(videoController, animated: true, completion: nil)
            
        }
        
    }
    
    // MARK: - Vide Call Queue Finished With Code
    
    func OnVideoCallQueueFinishedWithCode(title: String, andResponse: Int, response: String) {
        
//        let alert = UIAlertController(title: "call queue \(title) response code: \(andResponse)", message: "\(response)", preferredStyle: .alert)
//        alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: { (action: UIAlertAction!) in
//            print("Handle Ok Logic here")
//            if andResponse == 200 {
//
//            }
//        }))
//
//        self.present(alert, animated: true)

       self.idboxModule.returnPromise(message:" message: \(response)")

    }
    
    // MARK: - Video Session Connected
    
    func onVideoSessionConnect() {
        
    }
    
    // MARK: - Connection Error
    
    func errorMessage(message: String){
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
    
    func onFinish(message: String) {
        print("message \(message)")
        //self.idboxModule.returnPromise(message:message)
    }
    
    // MARK: - Signal R Video Call Queue order Alert Message
    
    func onSignalrMessageWithOrder(order: Int, andWaitingTime: Int) {
        //        customAlert(title: "Video Call Queue order: \(order)", message: "waiting time: \(andWaitingTime)")
        self.idboxModule.returnPromise(message:"Video Call Queue order: \(order) waiting time: \(andWaitingTime)")
        
    }
    
    // MARK: - Singla R Alert Verification Message Method
    
    func onSignalrMessage(message: String) {
        self.idboxModule.returnPromise(message:"Verification is : \(message)")
        //customAlert(title: "Verification is ", message: message)
    }
    
    // MARK: - Disable Micraphone
    
    func onMicrophoneDisabled() {
        
    }
    
    // MARK: - Camera Finish After Taking Images of Docs
    
    func OnCameraDocFinished(title: String, withImageList: Array<UIImage>, prototypeImage: Array<UIImage>) {
        print("CameraDOC has finished with title: %@ and list size: %lu  and the prototype image width is: %f",title,withImageList.count,prototypeImage.count);
    }
    
    // MARK: - Camera Finish with title, list size and prototype image width
    
    func OnCameraFinished(title: String, withImageCroped: UIImage, prototypeImage: UIImage) {
        print("Camera has finished with title: %@ and list size: %lu  and the prototype image width is: %f",title,withImageCroped.size,prototypeImage.size);
    }
    
    // MARK: - Camera Finish with ID title, list size and prototype image width
    
    func OnCameraIdFinished(title: String, withImageList: Array<UIImage>, prototypeImage: Array<UIImage>) {
        print("CameraID has finished with title: %@ and list size: %lu  and the prototype image width is: %f",title,withImageList.count,prototypeImage.count);
    }
    
    func OnCameraDoubleIdFinished(title: String, withImageList: Array<UIImage>, prototypeImage: Array<UIImage>) {
        print("DoubleIDWithLIST \(title)")
    }
    func OnUploadDoubleIdFinishedWithCode(code: Int, andResponse: String) {
//        let alert = UIAlertController(title: "Upload Double Side Id", message: "\(andResponse)", preferredStyle: .alert)
//
//        alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
//
//        self.present(alert, animated: true)
        print("DoubleID \(andResponse)")
        self.idboxModule.returnPromise(message:andResponse)

    }
    
    
    
    // MARK: - Upload ID Finish Alert Showing Responce
    
    func OnUploadIdFinishedWithCode(code: Int, andResponse: String) {
        //customAlert(title: "Upload Id", message: "\(andResponse)")
        self.idboxModule.returnPromise(message:andResponse)
        
    }
    
    
    
    // MARK: - Uplading Docs Finished Alert Showing Responce
    
    func OnUploadDocFinishedWithCode(code: Int, andResponse: String) {
        //customAlert(title: "Upload Docs", message: "\(andResponse)")
        self.idboxModule.returnPromise(message:andResponse)
        
    }
    
    // MARK: - Uplading Selfie Finished Alert Showing Responce
    
    func OnUploadSelfieFinishedWithCode(code: Int, andResponse: String) {
        //customAlert(title: "Upload Selfie", message: "\(code) and response \(andResponse)")
        self.idboxModule.returnPromise(message:andResponse)
        
    }
    
    // MARK: - Api Call Finished Alert Showing Responce
    
    func OnApiCallFinished(title: String, withCode: Int, andResponse: String, andImageSize: Int) {
        //        if title == "RegisterRequest" {
        //            customAlert(title: title, message:  "status \(withCode) \(andResponse)")
        //        }
        //        else if title == "PleaseWait" {
        //            customAlert(title: title, message:  "status \(withCode) \(andResponse)")
        //        }
        //        else if title == "SetRequestMetaData" {
        //            customAlert(title: title, message:  "status \(withCode) \(andResponse)")
        //        }
        //        else if title == "Next Step" {
        //            customAlert(title: title, message:  "status \(withCode) \(andResponse)")
        //        }
        //        else if title == "Get Batch Source" {
        //            customAlert(title: title, message:  "status \(withCode) \(andResponse)")
        //        }
        //        else if title == "Upload Double Side Id" {
        //            customAlert(title: title, message:  "status \(withCode) \(andResponse)")
        //        }
        //self.idboxModule.returnPromise(message:andResponse)
        
    }
    
    // MARK: - Connection Error
    
    func onConnectionError() {
        
    }
    
    
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
        print("Imgpicker \(info)")
        picker.dismiss(animated: true, completion: nil)
        
        if let image = info[.originalImage] as? UIImage {
            //imageToString =  convertImageToBase64(image: image)
        }
        
        
    }
    
    func onConnectError(message: String) {
        print("Error connection \(message)")
    }
    
    func onPublisherError(message: String) {
        print("Error publish \(message)")
    }
    
    func onSubscribeError(message: String) {
        print("Error subscribe \(message)")
    }
    
    
    func onProgressVideoBandwidthSubscriber(bandwidth_video: UInt64, packetLost: Float) {
        print("bandwidth_video \(bandwidth_video)  packetLost \(packetLost)")
    }
    
    func networkTestDidCompleteWithResult(result: IdboxSwiftFramwork.OTNetworkTestResult, error: OTError) {
        var resultMessage:String = ""
        
        if(result == IdboxSwiftFramwork.OTNetworkTestResult.OTNetworkTestResultVideoAndVoice)
        {
            resultMessage = "Result : OTNetworkTestResultVideoAndVoice";
            
        }
        else if(result == IdboxSwiftFramwork.OTNetworkTestResult.OTNetworkTestResultVoiceOnly)
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


//extension ViewController: UIPickerViewDelegate, UIPickerViewDataSource {
//
//
//    func numberOfComponents(in pickerView: UIPickerView) -> Int {
//        return 1
//    }
//
//    public func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
//        if pickerView == jsonPickerView {
//            return items.count
//        }
//        else {
//            return bankList.count
//        }
//
//    }
//
//    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
//        if pickerView == jsonPickerView {
//            return items[row]
//        }
//        else {
//            return bankList[row]
//        }
//    }
//
//    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
//
//        if pickerView == jsonPickerView {
//            currentJson = items[row]
//            btnBankJson.setTitle(items[row], for: .normal)
//            currentJson = items[row]
//            latestBank = items[row]
//        }
//        else {
//            btnBankMenu.setTitle(bankList[row], for: .normal)
//            btnBankJson.setTitle("Select Json", for: .normal)
//            latestBank = ""
//            imageToString = ""
//            if bankList[row] == "Eurobank" {
//                currentBank = "Eurobank"
//            }
//            else if bankList[row] == "Alpha bank" {
//                currentBank = "Alpha bank"
//            }
//            else if bankList[row] == "Piraeus" {
//                currentBank = "Piraeus"
//            }
//        }
//    }
//}
