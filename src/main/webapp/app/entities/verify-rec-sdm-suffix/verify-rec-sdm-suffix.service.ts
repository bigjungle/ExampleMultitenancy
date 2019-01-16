import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IVerifyRecSdmSuffix } from 'app/shared/model/verify-rec-sdm-suffix.model';

type EntityResponseType = HttpResponse<IVerifyRecSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IVerifyRecSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class VerifyRecSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/verify-recs';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/verify-recs';

    constructor(protected http: HttpClient) {}

    create(verifyRec: IVerifyRecSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(verifyRec);
        return this.http
            .post<IVerifyRecSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(verifyRec: IVerifyRecSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(verifyRec);
        return this.http
            .put<IVerifyRecSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IVerifyRecSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IVerifyRecSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IVerifyRecSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(verifyRec: IVerifyRecSdmSuffix): IVerifyRecSdmSuffix {
        const copy: IVerifyRecSdmSuffix = Object.assign({}, verifyRec, {
            insertTime: verifyRec.insertTime != null && verifyRec.insertTime.isValid() ? verifyRec.insertTime.toJSON() : null,
            updateTime: verifyRec.updateTime != null && verifyRec.updateTime.isValid() ? verifyRec.updateTime.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.insertTime = res.body.insertTime != null ? moment(res.body.insertTime) : null;
            res.body.updateTime = res.body.updateTime != null ? moment(res.body.updateTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((verifyRec: IVerifyRecSdmSuffix) => {
                verifyRec.insertTime = verifyRec.insertTime != null ? moment(verifyRec.insertTime) : null;
                verifyRec.updateTime = verifyRec.updateTime != null ? moment(verifyRec.updateTime) : null;
            });
        }
        return res;
    }
}
