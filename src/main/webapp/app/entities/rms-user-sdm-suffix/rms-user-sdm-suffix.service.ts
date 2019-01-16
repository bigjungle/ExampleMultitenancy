import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';

type EntityResponseType = HttpResponse<IRmsUserSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IRmsUserSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class RmsUserSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/rms-users';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/rms-users';

    constructor(protected http: HttpClient) {}

    create(rmsUser: IRmsUserSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(rmsUser);
        return this.http
            .post<IRmsUserSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(rmsUser: IRmsUserSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(rmsUser);
        return this.http
            .put<IRmsUserSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRmsUserSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRmsUserSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRmsUserSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(rmsUser: IRmsUserSdmSuffix): IRmsUserSdmSuffix {
        const copy: IRmsUserSdmSuffix = Object.assign({}, rmsUser, {
            validBegin: rmsUser.validBegin != null && rmsUser.validBegin.isValid() ? rmsUser.validBegin.toJSON() : null,
            validEnd: rmsUser.validEnd != null && rmsUser.validEnd.isValid() ? rmsUser.validEnd.toJSON() : null,
            insertTime: rmsUser.insertTime != null && rmsUser.insertTime.isValid() ? rmsUser.insertTime.toJSON() : null,
            updateTime: rmsUser.updateTime != null && rmsUser.updateTime.isValid() ? rmsUser.updateTime.toJSON() : null,
            verifyTime: rmsUser.verifyTime != null && rmsUser.verifyTime.isValid() ? rmsUser.verifyTime.toJSON() : null
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
            res.body.forEach((rmsUser: IRmsUserSdmSuffix) => {
                rmsUser.validBegin = rmsUser.validBegin != null ? moment(rmsUser.validBegin) : null;
                rmsUser.validEnd = rmsUser.validEnd != null ? moment(rmsUser.validEnd) : null;
                rmsUser.insertTime = rmsUser.insertTime != null ? moment(rmsUser.insertTime) : null;
                rmsUser.updateTime = rmsUser.updateTime != null ? moment(rmsUser.updateTime) : null;
                rmsUser.verifyTime = rmsUser.verifyTime != null ? moment(rmsUser.verifyTime) : null;
            });
        }
        return res;
    }
}
