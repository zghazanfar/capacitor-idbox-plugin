import { registerPlugin } from '@capacitor/core';

import type { IdboxPluginPlugin } from './definitions';

const IdboxPlugin = registerPlugin<IdboxPluginPlugin>('IdboxPlugin', {
  web: () => import('./web').then(m => new m.IdboxPluginWeb()),
});
export enum ShootMode {
    
  /**
 * Sets a mode of scanning a single side identity
 */
 SCAN_1S_ONLY = "SCAN_1S",
 /**
  * Sets a mode of scanning a double side identity
  */
  SCAN_2S_ONLY = "SCAN_2S",
 /**
  * Sets a mode of scanning the front side of a double side identity
  */
 /**
  * Sets a mode of scanning of the back side of a double side identity
  */
 /**
  * Sets a mode of scanning of an additional document
  */
  SCAN_1S_OTHER = "SCAN_1S_OTHER",
 /**
  * Sets a mode of capturing a selfie picture
  */
  PHOTO_ONLY = "PHOTO_ONLY",
  /**
  * Sets a mode of scanning an ID 
  */
  SCAN_ID_ONLY = "SCAN_ID_ONLY",
};

export enum FontName {
 FONT_DEFAULT = "Default",
   FONT_DEFAULT_BOLD = "Default_bold",
   FONT_SANS_SERIF_REGULAR = "sans_serif",
   FONT_SERIF_REGULAR = "serif",
   FONT_OPEN_SANS_REGULAR = "open_sans",
   FONT_OPEN_SANS_BOLD = "open_sans_bold",
   FONT_AVERTA_REGULAR = "averta_regular",
   FONT_AVERTA_BOLD = "averta_bold.otf",
};

export enum TransPortMethod {
 ALL= 0,
 WEBSOCKETS= 1,
 LONG_POLLING= 2,
};
export enum TextAlignment {
 LEFT = 0,
 CENTER = 1,
 RIGHT = 2,
 JUSTIFIED = 3,
 NATURAL = 4,
};


/*
  These are all Builder methods as described interfaces options inside wrapper. 
  So, we have to import them in RN and then we pass the values in form of objects
  from the RN side and reterive them in the wrapper.
  As we know these are all builder methods options, so some of them values are set as with ?
  which indicates they are optional. 
*/

interface CommonOptions {
  url: string;
  hawkId: string;
  hawkKey: string;
}

export interface OnBoardingApiOptions extends CommonOptions {
  /*
    Declare all OnBoardingApiOptions for both of the platforms
    Android: Builders methods as options
    IOS: Properties as options
  */
  requestId: string;
  port: number;
  apiPath: string;
  connectionTimeout?: number;
  readTimeout?: number;
  logging?: boolean;
}

export interface CameraScreenOptions extends CommonOptions {
  /*
    Declare all cameraScreenOptions for both of the platforms
    Android: Builders methods as options
    IOS: Properties as options
  */
  /* Android only Options */
  Mode: string;
  fontName?: string;
  fontSize?: number;  // ios ( bottomMessageFont ) - android
  fontColor?: string;
  autoCaptureTimeOut?: number;  // ios - android
  backVisibility?: boolean;  // ios - android
  cornersPathEffect?: boolean;
  dashPathEffect?: boolean;
  files?: string[];
  flashVisibility?: boolean;  // ios ( displayFlash )- android
  focusContinuePicture?: boolean;
  footerText?: string | string[];
  frontFrameBottomText?: string | string[];
  backFrameBottomText?: string | string[];
  frontSubHeaderText?: SubHeaderText;
  backSubHeaderText?: SubHeaderText;
  headerText?: string | string[];
  maxFrames?: number;
  overlayColor?: string;
  overlayAlpha?: number;
  roundedFocusFrame?: boolean;
  shutterButtonLight?: boolean;
  thumbnailVisibility?: boolean;
  videoTokenLang?: string;
  metadata?: string;
  frontRectangleRotated?: boolean;
  backRectangleRotated?: boolean;

  /*
    Declare all PreviewScreenOptions for both of the platforms
    Android: Builders methods as options
    IOS: Properties as options
  */
  previewHeaderText?: string;
  previewFrameBottomText?: string; // ios ( bottomMessage ) - android 
  uploadButtonText?: string;  // ios - android
  retakeButtonText?: string;
  previewFontName?: string;
  previewFontSize?: number;
  previewFontColor?: string;  // ios (preview_bottomColor) - android
  withLayoutParams?: LayoutParams;
  apiCallLogging?: boolean;
  /*
   Only IOS options
  */
  selfieScaleHeightFactor?: number;
  allowToFinishWithEmptyImageList?: boolean;
  previewBackgroundColor?: string;  // preview_backgroundColor
  hasPreview?: boolean;
  useUploadImageButton?: boolean;
  bottomColor?: string;
  previewSliderColor?: string;  // preview_sliderColor
  previewBottomColor?: string;  // preview_bottomColor
  previewPromptTextFont?: number;  // preview_promptTextFont
  allowTerminateOnPressingPreview?: boolean;
  terminateAddiotionalDocumentsOnFirst?: boolean;
  displayTopMessages?: boolean;
  displayBottomMessages?: boolean;
  takeDoubleSideMessage1?: string;
  takeDoubleSideMessage2?: string;
  displayMessages?: boolean;
  previewBottomMessage?: string;  // preview_bottomMessage
  previewSliderRect?: CGReatRectangleOptions;  // preview_sliderRect
  previewPromptTextRect?: CGReatRectangleOptions ;  // preview_promptTextRect
  previewBoundingRect?: CGReatRectangleOptions;  // preview_boundingRect
  previewAcceptButtonRect?: CGReatRectangleOptions ;  //  preview_acceptButtonRect
  smallDocumentRect?: CGReatRectangleOptions;
  bottomLabelRect?: CGReatRectangleOptions;
  bottomMessageAlignment?: number;
  previewPromptTextAlignment?: number;  //  preview_promptTextAlignment
  
}
export interface CGReatRectangleOptions {
  xCordinate: number;
  yCordinate: number;
  width: number;
  height: number;

}
export interface LayoutParams {
  width: number;
  height: number;
}
export interface SubHeaderText {
  subHeaderText: string | string[];
  drawable: string;
}

export interface VideoSessionOptions extends CommonOptions {
  /*
    Declare all VideoSessionOptions for both of the platforms
    Android: Builders methods as options
    IOS: Properties as options
  */
  tokBoxSessionId: string;
  tokBoxApiKey: string;
  tokBoxToken: string;

}

export interface SignalROptions {
  /*
    Declare all SignalROptions for both of the platforms
    Android: Builders methods as options
    IOS: Properties as options
  */
  url: string;
  port: number;
  apiPath: string;
  useSignalR: boolean;
  transportMethod?: number;
}


export * from './definitions';
export { IdboxPlugin };
