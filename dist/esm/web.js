import { WebPlugin } from '@capacitor/core';
export class IdboxPluginWeb extends WebPlugin {
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
//# sourceMappingURL=web.js.map