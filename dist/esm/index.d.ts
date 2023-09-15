import type { IdboxPluginPlugin } from './definitions';
declare const IdboxPlugin: IdboxPluginPlugin;
export declare enum ShootMode {
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
    SCAN_ID_ONLY = "SCAN_ID_ONLY"
}
export declare enum FontName {
    FONT_DEFAULT = "Default",
    FONT_DEFAULT_BOLD = "Default_bold",
    FONT_SANS_SERIF_REGULAR = "sans_serif",
    FONT_SERIF_REGULAR = "serif",
    FONT_OPEN_SANS_REGULAR = "open_sans",
    FONT_OPEN_SANS_BOLD = "open_sans_bold",
    FONT_AVERTA_REGULAR = "averta_regular",
    FONT_AVERTA_BOLD = "averta_bold.otf"
}
export declare enum TransPortMethod {
    ALL = 0,
    WEBSOCKETS = 1,
    LONG_POLLING = 2
}
export declare enum TextAlignment {
    LEFT = 0,
    CENTER = 1,
    RIGHT = 2,
    JUSTIFIED = 3,
    NATURAL = 4
}
interface CommonOptions {
    url: string;
    hawkId: string;
    hawkKey: string;
}
export interface OnBoardingApiOptions extends CommonOptions {
    requestId: string;
    port: number;
    apiPath: string;
    connectionTimeout?: number;
    readTimeout?: number;
    logging?: boolean;
}
export interface CameraScreenOptions extends CommonOptions {
    Mode: string;
    fontName?: string;
    fontSize?: number;
    fontColor?: string;
    autoCaptureTimeOut?: number;
    backVisibility?: boolean;
    cornersPathEffect?: boolean;
    dashPathEffect?: boolean;
    files?: string[];
    flashVisibility?: boolean;
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
    previewHeaderText?: string;
    previewFrameBottomText?: string;
    uploadButtonText?: string;
    retakeButtonText?: string;
    previewFontName?: string;
    previewFontSize?: number;
    previewFontColor?: string;
    withLayoutParams?: LayoutParams;
    apiCallLogging?: boolean;
    selfieScaleHeightFactor?: number;
    allowToFinishWithEmptyImageList?: boolean;
    previewBackgroundColor?: string;
    hasPreview?: boolean;
    useUploadImageButton?: boolean;
    bottomColor?: string;
    previewSliderColor?: string;
    previewBottomColor?: string;
    previewPromptTextFont?: number;
    allowTerminateOnPressingPreview?: boolean;
    terminateAddiotionalDocumentsOnFirst?: boolean;
    displayTopMessages?: boolean;
    displayBottomMessages?: boolean;
    takeDoubleSideMessage1?: string;
    takeDoubleSideMessage2?: string;
    displayMessages?: boolean;
    previewBottomMessage?: string;
    previewSliderRect?: CGReatRectangleOptions;
    previewPromptTextRect?: CGReatRectangleOptions;
    previewBoundingRect?: CGReatRectangleOptions;
    previewAcceptButtonRect?: CGReatRectangleOptions;
    smallDocumentRect?: CGReatRectangleOptions;
    bottomLabelRect?: CGReatRectangleOptions;
    bottomMessageAlignment?: number;
    previewPromptTextAlignment?: number;
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
    tokBoxSessionId: string;
    tokBoxApiKey: string;
    tokBoxToken: string;
}
export interface SignalROptions {
    url: string;
    port: number;
    apiPath: string;
    useSignalR: boolean;
    transportMethod?: number;
}
export * from './definitions';
export { IdboxPlugin };
