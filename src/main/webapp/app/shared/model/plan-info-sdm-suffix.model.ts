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

export interface IPlanInfoSdmSuffix {
    id?: number;
    name?: string;
    sortString?: string;
    descString?: string;
    jsonString?: string;
    remarks?: string;
    refEvent?: string;
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
    publishedTime?: Moment;
    verifyTime?: Moment;
    verifyNeed?: boolean;
    verifyOpinion?: string;
    viewCount?: number;
    viewPermission?: ViewPermission;
    viewPermPersion?: string;
    serialNumber?: string;
    versionNumber?: string;
    paraSourceString?: string;
    featureKeyword?: string;
    suggestion?: string;
    releaseScope?: string;
    verifyRecName?: string;
    verifyRecId?: number;
    paraTypeName?: string;
    paraTypeId?: number;
    paraClassName?: string;
    paraClassId?: number;
    paraCatName?: string;
    paraCatId?: number;
    paraStateName?: string;
    paraStateId?: number;
    paraSourceName?: string;
    paraSourceId?: number;
    paraAttrName?: string;
    paraAttrId?: number;
    paraPropName?: string;
    paraPropId?: number;
    createdByUserName?: string;
    createdById?: number;
    createdDepByDepName?: string;
    createdDepById?: number;
    ownerByUserName?: string;
    ownerById?: number;
    ownerDepByDepName?: string;
    ownerDepById?: number;
    modifiedByUserName?: string;
    modifiedById?: number;
    modifiedDepByDepName?: string;
    modifiedDepById?: number;
    verifiedByUserName?: string;
    verifiedById?: number;
    verifiedDepByDepName?: string;
    verifiedDepById?: number;
    publishedByUserName?: string;
    publishedById?: number;
    publishedDepByDepName?: string;
    publishedDepById?: number;
    parentName?: string;
    parentId?: number;
}

export class PlanInfoSdmSuffix implements IPlanInfoSdmSuffix {
    constructor(
        public id?: number,
        public name?: string,
        public sortString?: string,
        public descString?: string,
        public jsonString?: string,
        public remarks?: string,
        public refEvent?: string,
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
        public publishedTime?: Moment,
        public verifyTime?: Moment,
        public verifyNeed?: boolean,
        public verifyOpinion?: string,
        public viewCount?: number,
        public viewPermission?: ViewPermission,
        public viewPermPersion?: string,
        public serialNumber?: string,
        public versionNumber?: string,
        public paraSourceString?: string,
        public featureKeyword?: string,
        public suggestion?: string,
        public releaseScope?: string,
        public verifyRecName?: string,
        public verifyRecId?: number,
        public paraTypeName?: string,
        public paraTypeId?: number,
        public paraClassName?: string,
        public paraClassId?: number,
        public paraCatName?: string,
        public paraCatId?: number,
        public paraStateName?: string,
        public paraStateId?: number,
        public paraSourceName?: string,
        public paraSourceId?: number,
        public paraAttrName?: string,
        public paraAttrId?: number,
        public paraPropName?: string,
        public paraPropId?: number,
        public createdByUserName?: string,
        public createdById?: number,
        public createdDepByDepName?: string,
        public createdDepById?: number,
        public ownerByUserName?: string,
        public ownerById?: number,
        public ownerDepByDepName?: string,
        public ownerDepById?: number,
        public modifiedByUserName?: string,
        public modifiedById?: number,
        public modifiedDepByDepName?: string,
        public modifiedDepById?: number,
        public verifiedByUserName?: string,
        public verifiedById?: number,
        public verifiedDepByDepName?: string,
        public verifiedDepById?: number,
        public publishedByUserName?: string,
        public publishedById?: number,
        public publishedDepByDepName?: string,
        public publishedDepById?: number,
        public parentName?: string,
        public parentId?: number
    ) {
        this.usingFlag = this.usingFlag || false;
        this.verifyNeed = this.verifyNeed || false;
    }
}
