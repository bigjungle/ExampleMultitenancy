import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IParaPropSdmSuffix } from 'app/shared/model/para-prop-sdm-suffix.model';

type EntityResponseType = HttpResponse<IParaPropSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IParaPropSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class ParaPropSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/para-props';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/para-props';

    constructor(protected http: HttpClient) {}

    create(paraProp: IParaPropSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(paraProp);
        return this.http
            .post<IParaPropSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(paraProp: IParaPropSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(paraProp);
        return this.http
            .put<IParaPropSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IParaPropSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IParaPropSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IParaPropSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(paraProp: IParaPropSdmSuffix): IParaPropSdmSuffix {
        const copy: IParaPropSdmSuffix = Object.assign({}, paraProp, {
            validBegin: paraProp.validBegin != null && paraProp.validBegin.isValid() ? paraProp.validBegin.toJSON() : null,
            validEnd: paraProp.validEnd != null && paraProp.validEnd.isValid() ? paraProp.validEnd.toJSON() : null,
            insertTime: paraProp.insertTime != null && paraProp.insertTime.isValid() ? paraProp.insertTime.toJSON() : null,
            updateTime: paraProp.updateTime != null && paraProp.updateTime.isValid() ? paraProp.updateTime.toJSON() : null,
            verifyTime: paraProp.verifyTime != null && paraProp.verifyTime.isValid() ? paraProp.verifyTime.toJSON() : null
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
            res.body.forEach((paraProp: IParaPropSdmSuffix) => {
                paraProp.validBegin = paraProp.validBegin != null ? moment(paraProp.validBegin) : null;
                paraProp.validEnd = paraProp.validEnd != null ? moment(paraProp.validEnd) : null;
                paraProp.insertTime = paraProp.insertTime != null ? moment(paraProp.insertTime) : null;
                paraProp.updateTime = paraProp.updateTime != null ? moment(paraProp.updateTime) : null;
                paraProp.verifyTime = paraProp.verifyTime != null ? moment(paraProp.verifyTime) : null;
            });
        }
        return res;
    }
}
