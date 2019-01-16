import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IParaTypeSdmSuffix } from 'app/shared/model/para-type-sdm-suffix.model';

type EntityResponseType = HttpResponse<IParaTypeSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IParaTypeSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class ParaTypeSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/para-types';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/para-types';

    constructor(protected http: HttpClient) {}

    create(paraType: IParaTypeSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(paraType);
        return this.http
            .post<IParaTypeSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(paraType: IParaTypeSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(paraType);
        return this.http
            .put<IParaTypeSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IParaTypeSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IParaTypeSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IParaTypeSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(paraType: IParaTypeSdmSuffix): IParaTypeSdmSuffix {
        const copy: IParaTypeSdmSuffix = Object.assign({}, paraType, {
            validBegin: paraType.validBegin != null && paraType.validBegin.isValid() ? paraType.validBegin.toJSON() : null,
            validEnd: paraType.validEnd != null && paraType.validEnd.isValid() ? paraType.validEnd.toJSON() : null,
            insertTime: paraType.insertTime != null && paraType.insertTime.isValid() ? paraType.insertTime.toJSON() : null,
            updateTime: paraType.updateTime != null && paraType.updateTime.isValid() ? paraType.updateTime.toJSON() : null,
            verifyTime: paraType.verifyTime != null && paraType.verifyTime.isValid() ? paraType.verifyTime.toJSON() : null
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
            res.body.forEach((paraType: IParaTypeSdmSuffix) => {
                paraType.validBegin = paraType.validBegin != null ? moment(paraType.validBegin) : null;
                paraType.validEnd = paraType.validEnd != null ? moment(paraType.validEnd) : null;
                paraType.insertTime = paraType.insertTime != null ? moment(paraType.insertTime) : null;
                paraType.updateTime = paraType.updateTime != null ? moment(paraType.updateTime) : null;
                paraType.verifyTime = paraType.verifyTime != null ? moment(paraType.verifyTime) : null;
            });
        }
        return res;
    }
}
