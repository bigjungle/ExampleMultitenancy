import { User } from './../../core';

export interface ICompany {
    id?: any;
    name?: any;
    users?: any;
}

export class Company implements ICompany {
    constructor(public id?: number, public name?: string, public users?: User[]) {}
}
