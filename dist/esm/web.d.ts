import { WebPlugin } from '@capacitor/core';
import type { IdboxPluginPlugin } from './definitions';
export declare class IdboxPluginWeb extends WebPlugin implements IdboxPluginPlugin {
    registerRequest(options: {
        options: String;
    }): Promise<{
        options: String;
    }>;
    initWithHawkCredentials(options: {
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
    scanSelfie(options: {
        options: String;
    }): Promise<{
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
    contractFormAthex(options: {
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
    startVideoSession(options: {
        options: String;
    }): Promise<{
        options: any;
    }>;
}
