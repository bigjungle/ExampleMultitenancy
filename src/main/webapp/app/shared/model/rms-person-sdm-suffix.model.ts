import { Moment } from 'moment';

export const enum ValidType {
    LONG = 'LONG',
    SCOPE = 'SCOPE'
}

export interface IRmsPersonSdmSuffix {
    id?: number;
    personName?: string;
    firstName?: string;
    jobId?: string;
    lastName?: string;
    otherName?: string;
    sex?: boolean;
    birthday?: Moment;
    pic?: string;
    icon?: string;
    mobile?: string;
    depAddress?: string;
    depCode?: string;
    dutyId?: string;
    dimissionFlag?: boolean;
    headerFlag?: boolean;
    satrapFlag?: boolean;
    leaderFlag?: boolean;
    postFlag1?: boolean;
    postFlag2?: boolean;
    postFlag3?: boolean;
    officeTel?: string;
    fax?: string;
    mail1?: string;
    mail2?: string;
    familyTel?: string;
    familyAdd?: string;
    familyCode?: string;
    personDesc?: string;
    idCode?: string;
    pop3?: string;
    smtp?: string;
    mailUsername?: string;
    mailPassword?: string;
    mailKeepFlag?: boolean;
    personSort?: string;
    levelNum?: number;
    personStateId?: string;
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
}

export class RmsPersonSdmSuffix implements IRmsPersonSdmSuffix {
    constructor(
        public id?: number,
        public personName?: string,
        public firstName?: string,
        public jobId?: string,
        public lastName?: string,
        public otherName?: string,
        public sex?: boolean,
        public birthday?: Moment,
        public pic?: string,
        public icon?: string,
        public mobile?: string,
        public depAddress?: string,
        public depCode?: string,
        public dutyId?: string,
        public dimissionFlag?: boolean,
        public headerFlag?: boolean,
        public satrapFlag?: boolean,
        public leaderFlag?: boolean,
        public postFlag1?: boolean,
        public postFlag2?: boolean,
        public postFlag3?: boolean,
        public officeTel?: string,
        public fax?: string,
        public mail1?: string,
        public mail2?: string,
        public familyTel?: string,
        public familyAdd?: string,
        public familyCode?: string,
        public personDesc?: string,
        public idCode?: string,
        public pop3?: string,
        public smtp?: string,
        public mailUsername?: string,
        public mailPassword?: string,
        public mailKeepFlag?: boolean,
        public personSort?: string,
        public levelNum?: number,
        public personStateId?: string,
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
        public verifiedById?: number
    ) {
        this.sex = this.sex || false;
        this.dimissionFlag = this.dimissionFlag || false;
        this.headerFlag = this.headerFlag || false;
        this.satrapFlag = this.satrapFlag || false;
        this.leaderFlag = this.leaderFlag || false;
        this.postFlag1 = this.postFlag1 || false;
        this.postFlag2 = this.postFlag2 || false;
        this.postFlag3 = this.postFlag3 || false;
        this.mailKeepFlag = this.mailKeepFlag || false;
    }
}
