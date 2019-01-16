import { Moment } from 'moment';
import { IRmsRoleSdmSuffix } from 'app/shared/model//rms-role-sdm-suffix.model';

export const enum ValidType {
    LONG = 'LONG',
    SCOPE = 'SCOPE'
}

export interface IRmsUserSdmSuffix {
    id?: number;
    userName?: string;
    personId?: string;
    userPassword?: string;
    processPassword?: string;
    userSort?: string;
    userDesc?: string;
    userPasswordValiInstantTimes?: number;
    userPasswordLockFlag?: boolean;
    procPasswordValiInstantTimes?: number;
    procPasswordLockFlag?: string;
    userProp?: string;
    by01?: string;
    by02?: string;
    by03?: string;
    by04?: string;
    by05?: string;
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
    rmsRoles?: IRmsRoleSdmSuffix[];
}

export class RmsUserSdmSuffix implements IRmsUserSdmSuffix {
    constructor(
        public id?: number,
        public userName?: string,
        public personId?: string,
        public userPassword?: string,
        public processPassword?: string,
        public userSort?: string,
        public userDesc?: string,
        public userPasswordValiInstantTimes?: number,
        public userPasswordLockFlag?: boolean,
        public procPasswordValiInstantTimes?: number,
        public procPasswordLockFlag?: string,
        public userProp?: string,
        public by01?: string,
        public by02?: string,
        public by03?: string,
        public by04?: string,
        public by05?: string,
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
        public rmsRoles?: IRmsRoleSdmSuffix[]
    ) {
        this.userPasswordLockFlag = this.userPasswordLockFlag || false;
    }
}
