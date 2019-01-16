import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlanInfoDataHisSdmSuffix } from 'app/shared/model/plan-info-data-his-sdm-suffix.model';

type EntityResponseType = HttpResponse<IPlanInfoDataHisSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IPlanInfoDataHisSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class PlanInfoDataHisSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/plan-info-data-his';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/plan-info-data-his';

    constructor(protected http: HttpClient) {}

    create(planInfoDataHis: IPlanInfoDataHisSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(planInfoDataHis);
        return this.http
            .post<IPlanInfoDataHisSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(planInfoDataHis: IPlanInfoDataHisSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(planInfoDataHis);
        return this.http
            .put<IPlanInfoDataHisSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPlanInfoDataHisSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPlanInfoDataHisSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPlanInfoDataHisSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(planInfoDataHis: IPlanInfoDataHisSdmSuffix): IPlanInfoDataHisSdmSuffix {
        const copy: IPlanInfoDataHisSdmSuffix = Object.assign({}, planInfoDataHis, {
            validBegin:
                planInfoDataHis.validBegin != null && planInfoDataHis.validBegin.isValid() ? planInfoDataHis.validBegin.toJSON() : null,
            validEnd: planInfoDataHis.validEnd != null && planInfoDataHis.validEnd.isValid() ? planInfoDataHis.validEnd.toJSON() : null,
            insertTime:
                planInfoDataHis.insertTime != null && planInfoDataHis.insertTime.isValid() ? planInfoDataHis.insertTime.toJSON() : null,
            updateTime:
                planInfoDataHis.updateTime != null && planInfoDataHis.updateTime.isValid() ? planInfoDataHis.updateTime.toJSON() : null,
            verifyTime:
                planInfoDataHis.verifyTime != null && planInfoDataHis.verifyTime.isValid() ? planInfoDataHis.verifyTime.toJSON() : null
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
            res.body.forEach((planInfoDataHis: IPlanInfoDataHisSdmSuffix) => {
                planInfoDataHis.validBegin = planInfoDataHis.validBegin != null ? moment(planInfoDataHis.validBegin) : null;
                planInfoDataHis.validEnd = planInfoDataHis.validEnd != null ? moment(planInfoDataHis.validEnd) : null;
                planInfoDataHis.insertTime = planInfoDataHis.insertTime != null ? moment(planInfoDataHis.insertTime) : null;
                planInfoDataHis.updateTime = planInfoDataHis.updateTime != null ? moment(planInfoDataHis.updateTime) : null;
                planInfoDataHis.verifyTime = planInfoDataHis.verifyTime != null ? moment(planInfoDataHis.verifyTime) : null;
            });
        }
        return res;
    }
}
