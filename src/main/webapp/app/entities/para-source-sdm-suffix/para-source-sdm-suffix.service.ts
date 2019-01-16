import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IParaSourceSdmSuffix } from 'app/shared/model/para-source-sdm-suffix.model';

type EntityResponseType = HttpResponse<IParaSourceSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IParaSourceSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class ParaSourceSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/para-sources';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/para-sources';

    constructor(protected http: HttpClient) {}

    create(paraSource: IParaSourceSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(paraSource);
        return this.http
            .post<IParaSourceSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(paraSource: IParaSourceSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(paraSource);
        return this.http
            .put<IParaSourceSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IParaSourceSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IParaSourceSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IParaSourceSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(paraSource: IParaSourceSdmSuffix): IParaSourceSdmSuffix {
        const copy: IParaSourceSdmSuffix = Object.assign({}, paraSource, {
            validBegin: paraSource.validBegin != null && paraSource.validBegin.isValid() ? paraSource.validBegin.toJSON() : null,
            validEnd: paraSource.validEnd != null && paraSource.validEnd.isValid() ? paraSource.validEnd.toJSON() : null,
            insertTime: paraSource.insertTime != null && paraSource.insertTime.isValid() ? paraSource.insertTime.toJSON() : null,
            updateTime: paraSource.updateTime != null && paraSource.updateTime.isValid() ? paraSource.updateTime.toJSON() : null,
            verifyTime: paraSource.verifyTime != null && paraSource.verifyTime.isValid() ? paraSource.verifyTime.toJSON() : null
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
            res.body.forEach((paraSource: IParaSourceSdmSuffix) => {
                paraSource.validBegin = paraSource.validBegin != null ? moment(paraSource.validBegin) : null;
                paraSource.validEnd = paraSource.validEnd != null ? moment(paraSource.validEnd) : null;
                paraSource.insertTime = paraSource.insertTime != null ? moment(paraSource.insertTime) : null;
                paraSource.updateTime = paraSource.updateTime != null ? moment(paraSource.updateTime) : null;
                paraSource.verifyTime = paraSource.verifyTime != null ? moment(paraSource.verifyTime) : null;
            });
        }
        return res;
    }
}
