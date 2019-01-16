import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRmsRoleSdmSuffix } from 'app/shared/model/rms-role-sdm-suffix.model';

type EntityResponseType = HttpResponse<IRmsRoleSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IRmsRoleSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class RmsRoleSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/rms-roles';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/rms-roles';

    constructor(protected http: HttpClient) {}

    create(rmsRole: IRmsRoleSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(rmsRole);
        return this.http
            .post<IRmsRoleSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(rmsRole: IRmsRoleSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(rmsRole);
        return this.http
            .put<IRmsRoleSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRmsRoleSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRmsRoleSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRmsRoleSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(rmsRole: IRmsRoleSdmSuffix): IRmsRoleSdmSuffix {
        const copy: IRmsRoleSdmSuffix = Object.assign({}, rmsRole, {
            validBegin: rmsRole.validBegin != null && rmsRole.validBegin.isValid() ? rmsRole.validBegin.toJSON() : null,
            validEnd: rmsRole.validEnd != null && rmsRole.validEnd.isValid() ? rmsRole.validEnd.toJSON() : null,
            insertTime: rmsRole.insertTime != null && rmsRole.insertTime.isValid() ? rmsRole.insertTime.toJSON() : null,
            updateTime: rmsRole.updateTime != null && rmsRole.updateTime.isValid() ? rmsRole.updateTime.toJSON() : null,
            verifyTime: rmsRole.verifyTime != null && rmsRole.verifyTime.isValid() ? rmsRole.verifyTime.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.validBegin = res.body.validBegin != null ? moment(res.body.validBegin) : null;
            res.body.validEnd = res.body.validEnd != null ? moment(res.body.validEnd) : null;
            res.body.insertTime = res.body.insertTime != null ? moment(res.body.insertTime) : null;
            res.body.updateTime = res.body.updateTime != null ? moment(res.body.updateTime) : null;
            res.body.verifyTime = res.body.verifyTime != null ? moment(res.body.verifyTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((rmsRole: IRmsRoleSdmSuffix) => {
                rmsRole.validBegin = rmsRole.validBegin != null ? moment(rmsRole.validBegin) : null;
                rmsRole.validEnd = rmsRole.validEnd != null ? moment(rmsRole.validEnd) : null;
                rmsRole.insertTime = rmsRole.insertTime != null ? moment(rmsRole.insertTime) : null;
                rmsRole.updateTime = rmsRole.updateTime != null ? moment(rmsRole.updateTime) : null;
                rmsRole.verifyTime = rmsRole.verifyTime != null ? moment(rmsRole.verifyTime) : null;
            });
        }
        return res;
    }
}
