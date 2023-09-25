import { PluginListenerHandle } from "@capacitor/core";
export interface IdboxPluginPlugin {
    initWithHawkCredentials(options: {
        options: String;
    }): Promise<{
        options: String;
    }>;
    registerRequest(options: {
        options: String;
    }): Promise<{
        options: String;
    }>;
    getNextStep(): Promise<{
        options: any;
    }>;
    getRequestId(): Promise<{
        response: any;
    }>;
    pleaseWait(): Promise<{
        options: any;
    }>;
    scanSelfie(options: {
        options: String;
    }): Promise<{
        options: any;
    }>;
    videoCall(options: {
        options: String;
    }): Promise<{
        options: any;
    }>;
    videoCallQueue(options: {
        options: String;
    }): Promise<{
        options: any;
    }>;
    videoCallVerification(): Promise<{
        options: any;
    }>;
    createOtp(): Promise<{
        options: any;
    }>;
    resendOtp(): Promise<{
        options: any;
    }>;
    contractForm(options: {
        options: String;
    }): Promise<{
        Result: {
            Data: string;
        };
    }>;
    startListeningSignalR(options: {
        options: String;
    }): Promise<{
        options: any;
    }>;
    startListeningVideoSignalR(options: {
        options: String;
    }): Promise<{
        options: any;
    }>;
    scan1SIdentity(options: {
        options: String;
    }): Promise<{
        options: any;
    }>;
    scan2SIdentity(options: {
        options: String;
    }): Promise<{
        options: any;
    }>;
    scanAdditionalDocuments(options: {
        options: String;
    }): Promise<{
        options: any;
    }>;
    startVideoSession(options: {
        options: String;
    }): Promise<{
        options: any;
    }>;
    contractFormAthex(options: {
        options: String;
    }): Promise<{
        Result: {
            Data: string;
        };
    }>;
    addListener(eventName: 'videoCallQueue', listenerFunc: (info: any) => void): Promise<PluginListenerHandle> & PluginListenerHandle;
}
