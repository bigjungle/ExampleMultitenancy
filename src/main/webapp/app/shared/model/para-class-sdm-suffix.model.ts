import { Moment } from 'moment';

export const enum ValidType {
    LONG = 'LONG',
    SCOPE = 'SCOPE'
}

export interface IParaClassSdmSuffix {
    id?: number;
    name?: string;
    serialNumber?: string;
    sortString?: string;
    descString?: string;
    imageBlobContentType?: string;
    imageBlob?: any;
    imageBlobName?: string;
    usingFlag?: boolean;
    remarks?: string;
    validType?: ValidType;
    validBegin?: Moment;
    validEnd?: Moment;
    insertTime?: Moment;
    updateTime?: Moment;
    verifyTime?: Moment;
    createdByUserName?: string;
    createdById?: number;
    modifiedByUserName?: string;
    modifiedById?: number;
    verifiedByUserName?: string;
    verifiedById?: number;
    parentName?: string;
    parentId?: number;
}

export class ParaClassSdmSuffix implements IParaClassSdmSuffix {
    constructor(
        public id?: number,
        public name?: string,
        public serialNumber?: string,
        public sortString?: string,
        public descString?: string,
        public imageBlobContentType?: string,
        public imageBlob?: any,
        public imageBlobName?: string,
        public usingFlag?: boolean,
        public remarks?: string,
        public validType?: ValidType,
        public validBegin?: Moment,
        public validEnd?: Moment,
        public insertTime?: Moment,
        public updateTime?: Moment,
        public verifyTime?: Moment,
        public createdByUserName?: string,
        public createdById?: number,
        public modifiedByUserName?: string,
        public modifiedById?: number,
        public verifiedByUserName?: string,
        public verifiedById?: number,
        public parentName?: string,
        public parentId?: number
    ) {
        this.usingFlag = this.usingFlag || false;
    }
}
