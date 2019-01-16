import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IParaStateSdmSuffix } from 'app/shared/model/para-state-sdm-suffix.model';

type EntityResponseType = HttpResponse<IParaStateSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IParaStateSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class ParaStateSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/para-states';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/para-states';

    constructor(protected http: HttpClient) {}

    create(paraState: IParaStateSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(paraState);
        return this.http
            .post<IParaStateSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(paraState: IParaStateSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(paraState);
        return this.http
            .put<IParaStateSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IParaStateSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IParaStateSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IParaStateSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(paraState: IParaStateSdmSuffix): IParaStateSdmSuffix {
        const copy: IParaStateSdmSuffix = Object.assign({}, paraState, {
            validBegin: paraState.validBegin != null && paraState.validBegin.isValid() ? paraState.validBegin.toJSON() : null,
            validEnd: paraState.validEnd != null && paraState.validEnd.isValid() ? paraState.validEnd.toJSON() : null,
            insertTime: paraState.insertTime != null && paraState.insertTime.isValid() ? paraState.insertTime.toJSON() : null,
            updateTime: paraState.updateTime != null && paraState.updateTime.isValid() ? paraState.updateTime.toJSON() : null,
            verifyTime: paraState.verifyTime != null && paraState.verifyTime.isValid() ? paraState.verifyTime.toJSON() : null
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
            res.body.forEach((paraState: IParaStateSdmSuffix) => {
                paraState.validBegin = paraState.validBegin != null ? moment(paraState.validBegin) : null;
                paraState.validEnd = paraState.validEnd != null ? moment(paraState.validEnd) : null;
                paraState.insertTime = paraState.insertTime != null ? moment(paraState.insertTime) : null;
                paraState.updateTime = paraState.updateTime != null ? moment(paraState.updateTime) : null;
                paraState.verifyTime = paraState.verifyTime != null ? moment(paraState.verifyTime) : null;
            });
        }
        return res;
    }
}
