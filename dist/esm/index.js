import { registerPlugin } from '@capacitor/core';
const IdboxPlugin = registerPlugin('IdboxPlugin', {
    web: () => import('./web').then(m => new m.IdboxPluginWeb()),
});
export var ShootMode;
(function (ShootMode) {
    /**
   * Sets a mode of scanning a single side identity
   */
    ShootMode["SCAN_1S_ONLY"] = "SCAN_1S";
    /**
     * Sets a mode of scanning a double side identity
     */
    ShootMode["SCAN_2S_ONLY"] = "SCAN_2S";
    /**
     * Sets a mode of scanning the front side of a double side identity
     */
    /**
     * Sets a mode of scanning of the back side of a double side identity
     */
    /**
     * Sets a mode of scanning of an additional document
     */
    ShootMode["SCAN_1S_OTHER"] = "SCAN_1S_OTHER";
    /**
     * Sets a mode of capturing a selfie picture
     */
    ShootMode["PHOTO_ONLY"] = "PHOTO_ONLY";
    /**
    * Sets a mode of scanning an ID
    */
    ShootMode["SCAN_ID_ONLY"] = "SCAN_ID_ONLY";
})(ShootMode || (ShootMode = {}));
;
export var FontName;
(function (FontName) {
    FontName["FONT_DEFAULT"] = "Default";
    FontName["FONT_DEFAULT_BOLD"] = "Default_bold";
    FontName["FONT_SANS_SERIF_REGULAR"] = "sans_serif";
    FontName["FONT_SERIF_REGULAR"] = "serif";
    FontName["FONT_OPEN_SANS_REGULAR"] = "open_sans";
    FontName["FONT_OPEN_SANS_BOLD"] = "open_sans_bold";
    FontName["FONT_AVERTA_REGULAR"] = "averta_regular";
    FontName["FONT_AVERTA_BOLD"] = "averta_bold.otf";
})(FontName || (FontName = {}));
;
export var TransPortMethod;
(function (TransPortMethod) {
    TransPortMethod[TransPortMethod["ALL"] = 0] = "ALL";
    TransPortMethod[TransPortMethod["WEBSOCKETS"] = 1] = "WEBSOCKETS";
    TransPortMethod[TransPortMethod["LONG_POLLING"] = 2] = "LONG_POLLING";
})(TransPortMethod || (TransPortMethod = {}));
;
export var TextAlignment;
(function (TextAlignment) {
    TextAlignment[TextAlignment["LEFT"] = 0] = "LEFT";
    TextAlignment[TextAlignment["CENTER"] = 1] = "CENTER";
    TextAlignment[TextAlignment["RIGHT"] = 2] = "RIGHT";
    TextAlignment[TextAlignment["JUSTIFIED"] = 3] = "JUSTIFIED";
    TextAlignment[TextAlignment["NATURAL"] = 4] = "NATURAL";
})(TextAlignment || (TextAlignment = {}));
;
export * from './definitions';
export { IdboxPlugin };
//# sourceMappingURL=index.js.map