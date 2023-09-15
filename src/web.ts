import { WebPlugin } from '@capacitor/core';

import type { IdboxPluginPlugin } from './definitions';

export class IdboxPluginWeb extends WebPlugin implements IdboxPluginPlugin {
  async registerRequest(options:{options:String}): Promise<{options:String}> {
    console.log('registerRequestt', options);
    return {"options":"options"};
  }
  async initWithHawkCredentials(options:{options:String}): Promise<{options:String}> {
    console.log('registerRequestt', options);
    return {"options":"options"};
  }
  async getNextStep(): Promise<{options:any}> {
    //console.log('getNextStep', options);
    return {options:""}
  }
  async pleaseWait(): Promise<{options:any}> {
    return {options:""}
  }
  async videoCall(options:{options:String}): Promise<{options:any}> {
    console.log(options)
    return {options:""}
  }
  async videoCallQueue(options:{options:String}): Promise<{options:any}> {
    console.log(options)
    return {options:""}
  }
  async videoCallVerification(): Promise<{options:any}> {
    return {options:""}
  }
  async scan1SIdentity(options:{options:String}): Promise<{options:any}> {
    console.log('scan1SIdentity', options);
    return options;
  }
  async scan2SIdentity(options:{options:String}): Promise<{options:any}> {
    console.log('scan2SIdentity', options);
    return options;
  }
  async scanAdditionalDocuments(options:{options:String}): Promise<{options:any}> {
    console.log('scanAdditionalDocuments', options);
    return options;
  }
  async scanSelfie(options:{options:String}): Promise<{options:any}> {
    console.log('scanSelfie', options);
    return options;
  }
  async createOtp(): Promise<{options:any}> {
    return {options:""};
  }
  async resendOtp(): Promise<{options:any}> {
    return {options:""};
  }
  async contractForm(options:{options:String}): Promise<{Result:{
    Data:string
  }}> {
    console.log('contractForm', options);
    return {Result:{
      Data:""
    }};
  }
  async contractFormAthex(options:{options:String}): Promise<{Result:{
    Data:string
  }}> {
    console.log('contractFormAthex', options);
    return {Result:{
      Data:""
    }};
  }
  async startListeningSignalR(options:{options:String}): Promise<{options:any}> {
    console.log('startListeningSignalR', options);
    return options;
  }
  async startListeningVideoSignalR(options:{options:String}): Promise<{options:any}> {
    console.log('startListeningSignalR', options);
    return options;
  }
  async startVideoSession(options:{options:String}): Promise<{options:any}> {
    console.log('startVideoSession', options);
    return options;
  }
}
