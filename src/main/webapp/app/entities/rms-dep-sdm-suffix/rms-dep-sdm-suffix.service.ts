import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRmsDepSdmSuffix } from 'app/shared/model/rms-dep-sdm-suffix.model';

type EntityResponseType = HttpResponse<IRmsDepSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IRmsDepSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class RmsDepSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/rms-deps';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/rms-deps';

    constructor(protected http: HttpClient) {}

    create(rmsDep: IRmsDepSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(rmsDep);
        return this.http
            .post<IRmsDepSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(rmsDep: IRmsDepSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(rmsDep);
        return this.http
            .put<IRmsDepSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRmsDepSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRmsDepSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRmsDepSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(rmsDep: IRmsDepSdmSuffix): IRmsDepSdmSuffix {
        const copy: IRmsDepSdmSuffix = Object.assign({}, rmsDep, {
            validBegin: rmsDep.validBegin != null && rmsDep.validBegin.isValid() ? rmsDep.validBegin.toJSON() : null,
            validEnd: rmsDep.validEnd != null && rmsDep.validEnd.isValid() ? rmsDep.validEnd.toJSON() : null,
            insertTime: rmsDep.insertTime != null && rmsDep.insertTime.isValid() ? rmsDep.insertTime.toJSON() : null,
            updateTime: rmsDep.updateTime != null && rmsDep.updateTime.isValid() ? rmsDep.updateTime.toJSON() : null,
            verifyTime: rmsDep.verifyTime != null && rmsDep.verifyTime.isValid() ? rmsDep.verifyTime.toJSON() : null
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
            res.body.forEach((rmsDep: IRmsDepSdmSuffix) => {
                rmsDep.validBegin = rmsDep.validBegin != null ? moment(rmsDep.validBegin) : null;
                rmsDep.validEnd = rmsDep.validEnd != null ? moment(rmsDep.validEnd) : null;
                rmsDep.insertTime = rmsDep.insertTime != null ? moment(rmsDep.insertTime) : null;
                rmsDep.updateTime = rmsDep.updateTime != null ? moment(rmsDep.updateTime) : null;
                rmsDep.verifyTime = rmsDep.verifyTime != null ? moment(rmsDep.verifyTime) : null;
            });
        }
        return res;
    }
}
