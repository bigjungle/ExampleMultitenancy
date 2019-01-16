import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IParaAttrSdmSuffix } from 'app/shared/model/para-attr-sdm-suffix.model';

type EntityResponseType = HttpResponse<IParaAttrSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IParaAttrSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class ParaAttrSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/para-attrs';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/para-attrs';

    constructor(protected http: HttpClient) {}

    create(paraAttr: IParaAttrSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(paraAttr);
        return this.http
            .post<IParaAttrSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(paraAttr: IParaAttrSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(paraAttr);
        return this.http
            .put<IParaAttrSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IParaAttrSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IParaAttrSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IParaAttrSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(paraAttr: IParaAttrSdmSuffix): IParaAttrSdmSuffix {
        const copy: IParaAttrSdmSuffix = Object.assign({}, paraAttr, {
            validBegin: paraAttr.validBegin != null && paraAttr.validBegin.isValid() ? paraAttr.validBegin.toJSON() : null,
            validEnd: paraAttr.validEnd != null && paraAttr.validEnd.isValid() ? paraAttr.validEnd.toJSON() : null,
            insertTime: paraAttr.insertTime != null && paraAttr.insertTime.isValid() ? paraAttr.insertTime.toJSON() : null,
            updateTime: paraAttr.updateTime != null && paraAttr.updateTime.isValid() ? paraAttr.updateTime.toJSON() : null,
            verifyTime: paraAttr.verifyTime != null && paraAttr.verifyTime.isValid() ? paraAttr.verifyTime.toJSON() : null
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
            res.body.forEach((paraAttr: IParaAttrSdmSuffix) => {
                paraAttr.validBegin = paraAttr.validBegin != null ? moment(paraAttr.validBegin) : null;
                paraAttr.validEnd = paraAttr.validEnd != null ? moment(paraAttr.validEnd) : null;
                paraAttr.insertTime = paraAttr.insertTime != null ? moment(paraAttr.insertTime) : null;
                paraAttr.updateTime = paraAttr.updateTime != null ? moment(paraAttr.updateTime) : null;
                paraAttr.verifyTime = paraAttr.verifyTime != null ? moment(paraAttr.verifyTime) : null;
            });
        }
        return res;
    }
}
