import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IParaClassSdmSuffix } from 'app/shared/model/para-class-sdm-suffix.model';

type EntityResponseType = HttpResponse<IParaClassSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IParaClassSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class ParaClassSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/para-classes';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/para-classes';

    constructor(protected http: HttpClient) {}

    create(paraClass: IParaClassSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(paraClass);
        return this.http
            .post<IParaClassSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(paraClass: IParaClassSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(paraClass);
        return this.http
            .put<IParaClassSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IParaClassSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IParaClassSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IParaClassSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(paraClass: IParaClassSdmSuffix): IParaClassSdmSuffix {
        const copy: IParaClassSdmSuffix = Object.assign({}, paraClass, {
            validBegin: paraClass.validBegin != null && paraClass.validBegin.isValid() ? paraClass.validBegin.toJSON() : null,
            validEnd: paraClass.validEnd != null && paraClass.validEnd.isValid() ? paraClass.validEnd.toJSON() : null,
            insertTime: paraClass.insertTime != null && paraClass.insertTime.isValid() ? paraClass.insertTime.toJSON() : null,
            updateTime: paraClass.updateTime != null && paraClass.updateTime.isValid() ? paraClass.updateTime.toJSON() : null,
            verifyTime: paraClass.verifyTime != null && paraClass.verifyTime.isValid() ? paraClass.verifyTime.toJSON() : null
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
            res.body.forEach((paraClass: IParaClassSdmSuffix) => {
                paraClass.validBegin = paraClass.validBegin != null ? moment(paraClass.validBegin) : null;
                paraClass.validEnd = paraClass.validEnd != null ? moment(paraClass.validEnd) : null;
                paraClass.insertTime = paraClass.insertTime != null ? moment(paraClass.insertTime) : null;
                paraClass.updateTime = paraClass.updateTime != null ? moment(paraClass.updateTime) : null;
                paraClass.verifyTime = paraClass.verifyTime != null ? moment(paraClass.verifyTime) : null;
            });
        }
        return res;
    }
}
