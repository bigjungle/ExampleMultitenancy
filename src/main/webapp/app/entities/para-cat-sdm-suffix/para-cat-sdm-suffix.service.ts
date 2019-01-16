import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IParaCatSdmSuffix } from 'app/shared/model/para-cat-sdm-suffix.model';

type EntityResponseType = HttpResponse<IParaCatSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IParaCatSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class ParaCatSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/para-cats';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/para-cats';

    constructor(protected http: HttpClient) {}

    create(paraCat: IParaCatSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(paraCat);
        return this.http
            .post<IParaCatSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(paraCat: IParaCatSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(paraCat);
        return this.http
            .put<IParaCatSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IParaCatSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IParaCatSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IParaCatSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(paraCat: IParaCatSdmSuffix): IParaCatSdmSuffix {
        const copy: IParaCatSdmSuffix = Object.assign({}, paraCat, {
            validBegin: paraCat.validBegin != null && paraCat.validBegin.isValid() ? paraCat.validBegin.toJSON() : null,
            validEnd: paraCat.validEnd != null && paraCat.validEnd.isValid() ? paraCat.validEnd.toJSON() : null,
            insertTime: paraCat.insertTime != null && paraCat.insertTime.isValid() ? paraCat.insertTime.toJSON() : null,
            updateTime: paraCat.updateTime != null && paraCat.updateTime.isValid() ? paraCat.updateTime.toJSON() : null,
            verifyTime: paraCat.verifyTime != null && paraCat.verifyTime.isValid() ? paraCat.verifyTime.toJSON() : null
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
            res.body.forEach((paraCat: IParaCatSdmSuffix) => {
                paraCat.validBegin = paraCat.validBegin != null ? moment(paraCat.validBegin) : null;
                paraCat.validEnd = paraCat.validEnd != null ? moment(paraCat.validEnd) : null;
                paraCat.insertTime = paraCat.insertTime != null ? moment(paraCat.insertTime) : null;
                paraCat.updateTime = paraCat.updateTime != null ? moment(paraCat.updateTime) : null;
                paraCat.verifyTime = paraCat.verifyTime != null ? moment(paraCat.verifyTime) : null;
            });
        }
        return res;
    }
}
