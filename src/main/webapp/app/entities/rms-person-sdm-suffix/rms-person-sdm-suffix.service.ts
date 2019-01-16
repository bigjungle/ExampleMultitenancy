import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRmsPersonSdmSuffix } from 'app/shared/model/rms-person-sdm-suffix.model';

type EntityResponseType = HttpResponse<IRmsPersonSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IRmsPersonSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class RmsPersonSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/rms-people';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/rms-people';

    constructor(protected http: HttpClient) {}

    create(rmsPerson: IRmsPersonSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(rmsPerson);
        return this.http
            .post<IRmsPersonSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(rmsPerson: IRmsPersonSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(rmsPerson);
        return this.http
            .put<IRmsPersonSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRmsPersonSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRmsPersonSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRmsPersonSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(rmsPerson: IRmsPersonSdmSuffix): IRmsPersonSdmSuffix {
        const copy: IRmsPersonSdmSuffix = Object.assign({}, rmsPerson, {
            birthday: rmsPerson.birthday != null && rmsPerson.birthday.isValid() ? rmsPerson.birthday.toJSON() : null,
            validBegin: rmsPerson.validBegin != null && rmsPerson.validBegin.isValid() ? rmsPerson.validBegin.toJSON() : null,
            validEnd: rmsPerson.validEnd != null && rmsPerson.validEnd.isValid() ? rmsPerson.validEnd.toJSON() : null,
            insertTime: rmsPerson.insertTime != null && rmsPerson.insertTime.isValid() ? rmsPerson.insertTime.toJSON() : null,
            updateTime: rmsPerson.updateTime != null && rmsPerson.updateTime.isValid() ? rmsPerson.updateTime.toJSON() : null,
            verifyTime: rmsPerson.verifyTime != null && rmsPerson.verifyTime.isValid() ? rmsPerson.verifyTime.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.birthday = res.body.birthday != null ? moment(res.body.birthday) : null;
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
            res.body.forEach((rmsPerson: IRmsPersonSdmSuffix) => {
                rmsPerson.birthday = rmsPerson.birthday != null ? moment(rmsPerson.birthday) : null;
                rmsPerson.validBegin = rmsPerson.validBegin != null ? moment(rmsPerson.validBegin) : null;
                rmsPerson.validEnd = rmsPerson.validEnd != null ? moment(rmsPerson.validEnd) : null;
                rmsPerson.insertTime = rmsPerson.insertTime != null ? moment(rmsPerson.insertTime) : null;
                rmsPerson.updateTime = rmsPerson.updateTime != null ? moment(rmsPerson.updateTime) : null;
                rmsPerson.verifyTime = rmsPerson.verifyTime != null ? moment(rmsPerson.verifyTime) : null;
            });
        }
        return res;
    }
}
