var capacitorIdboxPlugin = (function (exports, core) {
   'use strict';

   const IdboxPlugin = core.registerPlugin('IdboxPlugin', {
       web: () => Promise.resolve().then(function () { return web; }).then(m => new m.IdboxPluginWeb()),
   });
   exports.ShootMode = void 0;
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
   })(exports.ShootMode || (exports.ShootMode = {}));
   exports.FontName = void 0;
   (function (FontName) {
       FontName["FONT_DEFAULT"] = "Default";
       FontName["FONT_DEFAULT_BOLD"] = "Default_bold";
       FontName["FONT_SANS_SERIF_REGULAR"] = "sans_serif";
       FontName["FONT_SERIF_REGULAR"] = "serif";
       FontName["FONT_OPEN_SANS_REGULAR"] = "open_sans";
       FontName["FONT_OPEN_SANS_BOLD"] = "open_sans_bold";
       FontName["FONT_AVERTA_REGULAR"] = "averta_regular";
       FontName["FONT_AVERTA_BOLD"] = "averta_bold.otf";
   })(exports.FontName || (exports.FontName = {}));
   exports.TransPortMethod = void 0;
   (function (TransPortMethod) {
       TransPortMethod[TransPortMethod["ALL"] = 0] = "ALL";
       TransPortMethod[TransPortMethod["WEBSOCKETS"] = 1] = "WEBSOCKETS";
       TransPortMethod[TransPortMethod["LONG_POLLING"] = 2] = "LONG_POLLING";
   })(exports.TransPortMethod || (exports.TransPortMethod = {}));
   exports.TextAlignment = void 0;
   (function (TextAlignment) {
       TextAlignment[TextAlignment["LEFT"] = 0] = "LEFT";
       TextAlignment[TextAlignment["CENTER"] = 1] = "CENTER";
       TextAlignment[TextAlignment["RIGHT"] = 2] = "RIGHT";
       TextAlignment[TextAlignment["JUSTIFIED"] = 3] = "JUSTIFIED";
       TextAlignment[TextAlignment["NATURAL"] = 4] = "NATURAL";
   })(exports.TextAlignment || (exports.TextAlignment = {}));

   class IdboxPluginWeb extends core.WebPlugin {
       async registerRequest(options) {
           console.log('registerRequestt', options);
           return { "options": "options" };
       }
       async initWithHawkCredentials(options) {
           console.log('registerRequestt', options);
           return { "options": "options" };
       }
       async getNextStep() {
           //console.log('getNextStep', options);
           return { options: "" };
       }
       async pleaseWait() {
           return { options: "" };
       }
       async videoCall(options) {
           console.log(options);
           return { options: "" };
       }
       async videoCallQueue(options) {
           console.log(options);
           return { options: "" };
       }
       async videoCallVerification() {
           return { options: "" };
       }
       async scan1SIdentity(options) {
           console.log('scan1SIdentity', options);
           return options;
       }
       async scan2SIdentity(options) {
           console.log('scan2SIdentity', options);
           return options;
       }
       async scanAdditionalDocuments(options) {
           console.log('scanAdditionalDocuments', options);
           return options;
       }
       async scanSelfie(options) {
           console.log('scanSelfie', options);
           return options;
       }
       async createOtp() {
           return { options: "" };
       }
       async resendOtp() {
           return { options: "" };
       }
       async contractForm(options) {
           console.log('contractForm', options);
           return { Result: {
                   Data: ""
               } };
       }
       async contractFormAthex(options) {
           console.log('contractFormAthex', options);
           return { Result: {
                   Data: ""
               } };
       }
       async startListeningSignalR(options) {
           console.log('startListeningSignalR', options);
           return options;
       }
       async startListeningVideoSignalR(options) {
           console.log('startListeningSignalR', options);
           return options;
       }
       async startVideoSession(options) {
           console.log('startVideoSession', options);
           return options;
       }
   }

   var web = /*#__PURE__*/Object.freeze({
      __proto__: null,
      IdboxPluginWeb: IdboxPluginWeb
   });

   exports.IdboxPlugin = IdboxPlugin;

   Object.defineProperty(exports, '__esModule', { value: true });

   return exports;

})({}, capacitorExports);
//# sourceMappingURL=plugin.js.map
