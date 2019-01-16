import { Moment } from 'moment';

export const enum ValidType {
    LONG = 'LONG',
    SCOPE = 'SCOPE'
}

export interface IRmsDepSdmSuffix {
    id?: number;
    depName?: string;
    depTypeId?: string;
    parentDepId?: string;
    repealFlag?: boolean;
    levelId?: string;
    depSort?: string;
    parentDepStr?: string;
    childDepStr?: string;
    depDesc?: string;
    tel?: string;
    fax?: string;
    address?: string;
    code?: string;
    internet?: string;
    mail?: string;
    by01?: string;
    by02?: string;
    by03?: string;
    by04?: string;
    by05?: string;
    by06?: string;
    by07?: string;
    by08?: string;
    by09?: string;
    by10?: string;
    validType?: ValidType;
    validBegin?: Moment;
    validEnd?: Moment;
    insertTime?: Moment;
    updateTime?: Moment;
    verifyTime?: Moment;
    serialNumber?: string;
    createdByUserName?: string;
    createdById?: number;
    modifiedByUserName?: string;
    modifiedById?: number;
    verifiedByUserName?: string;
    verifiedById?: number;
    parentDepName?: string;
    parentId?: number;
}

export class RmsDepSdmSuffix implements IRmsDepSdmSuffix {
    constructor(
        public id?: number,
        public depName?: string,
        public depTypeId?: string,
        public parentDepId?: string,
        public repealFlag?: boolean,
        public levelId?: string,
        public depSort?: string,
        public parentDepStr?: string,
        public childDepStr?: string,
        public depDesc?: string,
        public tel?: string,
        public fax?: string,
        public address?: string,
        public code?: string,
        public internet?: string,
        public mail?: string,
        public by01?: string,
        public by02?: string,
        public by03?: string,
        public by04?: string,
        public by05?: string,
        public by06?: string,
        public by07?: string,
        public by08?: string,
        public by09?: string,
        public by10?: string,
        public validType?: ValidType,
        public validBegin?: Moment,
        public validEnd?: Moment,
        public insertTime?: Moment,
        public updateTime?: Moment,
        public verifyTime?: Moment,
        public serialNumber?: string,
        public createdByUserName?: string,
        public createdById?: number,
        public modifiedByUserName?: string,
        public modifiedById?: number,
        public verifiedByUserName?: string,
        public verifiedById?: number,
        public parentDepName?: string,
        public parentId?: number
    ) {
        this.repealFlag = this.repealFlag || false;
    }
}
