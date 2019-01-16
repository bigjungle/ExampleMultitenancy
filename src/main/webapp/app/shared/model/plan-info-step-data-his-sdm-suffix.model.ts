import { Moment } from 'moment';

export const enum ValidType {
    LONG = 'LONG',
    SCOPE = 'SCOPE'
}

export const enum ViewPermission {
    PRIVATEVIEW = 'PRIVATEVIEW',
    DEFAULTVIEW = 'DEFAULTVIEW',
    PROTECTVIEW = 'PROTECTVIEW',
    PUBLICVIEW = 'PUBLICVIEW',
    CUSTOMVIEW = 'CUSTOMVIEW'
}

export interface IPlanInfoStepDataHisSdmSuffix {
    id?: number;
    name?: string;
    sortString?: string;
    descString?: string;
    jsonString?: string;
    remarks?: string;
    attachmentPath?: string;
    attachmentBlobContentType?: string;
    attachmentBlob?: any;
    attachmentName?: string;
    textBlob?: any;
    imageBlobContentType?: string;
    imageBlob?: any;
    imageBlobName?: string;
    usingFlag?: boolean;
    validType?: ValidType;
    validBegin?: Moment;
    validEnd?: Moment;
    insertTime?: Moment;
    updateTime?: Moment;
    verifyTime?: Moment;
    verifyNeed?: boolean;
    verifyOpinion?: string;
    viewCount?: number;
    viewPermission?: ViewPermission;
    viewPermPersion?: string;
    serialNumber?: string;
    versionNumber?: string;
    stepOrder?: number;
    createdByUserName?: string;
    createdById?: number;
    createdDepByDepName?: string;
    createdDepById?: number;
    modifiedByUserName?: string;
    modifiedById?: number;
    modifiedDepByDepName?: string;
    modifiedDepById?: number;
    verifiedByUserName?: string;
    verifiedById?: number;
    verifiedDepByDepName?: string;
    verifiedDepById?: number;
    planInfoStepName?: string;
    planInfoStepId?: number;
    planInfoDataHisName?: string;
    planInfoDataHisId?: number;
}

export class PlanInfoStepDataHisSdmSuffix implements IPlanInfoStepDataHisSdmSuffix {
    constructor(
        public id?: number,
        public name?: string,
        public sortString?: string,
        public descString?: string,
        public jsonString?: string,
        public remarks?: string,
        public attachmentPath?: string,
        public attachmentBlobContentType?: string,
        public attachmentBlob?: any,
        public attachmentName?: string,
        public textBlob?: any,
        public imageBlobContentType?: string,
        public imageBlob?: any,
        public imageBlobName?: string,
        public usingFlag?: boolean,
        public validType?: ValidType,
        public validBegin?: Moment,
        public validEnd?: Moment,
        public insertTime?: Moment,
        public updateTime?: Moment,
        public verifyTime?: Moment,
        public verifyNeed?: boolean,
        public verifyOpinion?: string,
        public viewCount?: number,
        public viewPermission?: ViewPermission,
        public viewPermPersion?: string,
        public serialNumber?: string,
        public versionNumber?: string,
        public stepOrder?: number,
        public createdByUserName?: string,
        public createdById?: number,
        public createdDepByDepName?: string,
        public createdDepById?: number,
        public modifiedByUserName?: string,
        public modifiedById?: number,
        public modifiedDepByDepName?: string,
        public modifiedDepById?: number,
        public verifiedByUserName?: string,
        public verifiedById?: number,
        public verifiedDepByDepName?: string,
        public verifiedDepById?: number,
        public planInfoStepName?: string,
        public planInfoStepId?: number,
        public planInfoDataHisName?: string,
        public planInfoDataHisId?: number
    ) {
        this.usingFlag = this.usingFlag || false;
        this.verifyNeed = this.verifyNeed || false;
    }
}
