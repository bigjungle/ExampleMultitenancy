import { Moment } from 'moment';

export interface IVerifyRecSdmSuffix {
    id?: number;
    name?: string;
    descString?: string;
    verifyResult?: boolean;
    verifyScore?: number;
    remarks?: string;
    insertTime?: Moment;
    updateTime?: Moment;
    createdByUserName?: string;
    createdById?: number;
    modifiedByUserName?: string;
    modifiedById?: number;
}

export class VerifyRecSdmSuffix implements IVerifyRecSdmSuffix {
    constructor(
        public id?: number,
        public name?: string,
        public descString?: string,
        public verifyResult?: boolean,
        public verifyScore?: number,
        public remarks?: string,
        public insertTime?: Moment,
        public updateTime?: Moment,
        public createdByUserName?: string,
        public createdById?: number,
        public modifiedByUserName?: string,
        public modifiedById?: number
    ) {
        this.verifyResult = this.verifyResult || false;
    }
}
