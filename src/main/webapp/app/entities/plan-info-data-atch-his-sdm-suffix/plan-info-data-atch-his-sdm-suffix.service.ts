import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlanInfoDataAtchHisSdmSuffix } from 'app/shared/model/plan-info-data-atch-his-sdm-suffix.model';

type EntityResponseType = HttpResponse<IPlanInfoDataAtchHisSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IPlanInfoDataAtchHisSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class PlanInfoDataAtchHisSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/plan-info-data-atch-his';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/plan-info-data-atch-his';

    constructor(protected http: HttpClient) {}

    create(planInfoDataAtchHis: IPlanInfoDataAtchHisSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(planInfoDataAtchHis);
        return this.http
            .post<IPlanInfoDataAtchHisSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(planInfoDataAtchHis: IPlanInfoDataAtchHisSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(planInfoDataAtchHis);
        return this.http
            .put<IPlanInfoDataAtchHisSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPlanInfoDataAtchHisSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPlanInfoDataAtchHisSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPlanInfoDataAtchHisSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(planInfoDataAtchHis: IPlanInfoDataAtchHisSdmSuffix): IPlanInfoDataAtchHisSdmSuffix {
        const copy: IPlanInfoDataAtchHisSdmSuffix = Object.assign({}, planInfoDataAtchHis, {
            validBegin:
                planInfoDataAtchHis.validBegin != null && planInfoDataAtchHis.validBegin.isValid()
                    ? planInfoDataAtchHis.validBegin.toJSON()
                    : null,
            validEnd:
                planInfoDataAtchHis.validEnd != null && planInfoDataAtchHis.validEnd.isValid()
                    ? planInfoDataAtchHis.validEnd.toJSON()
                    : null,
            insertTime:
                planInfoDataAtchHis.insertTime != null && planInfoDataAtchHis.insertTime.isValid()
                    ? planInfoDataAtchHis.insertTime.toJSON()
                    : null,
            updateTime:
                planInfoDataAtchHis.updateTime != null && planInfoDataAtchHis.updateTime.isValid()
                    ? planInfoDataAtchHis.updateTime.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.validBegin = res.body.validBegin != null ? moment(res.body.validBegin) : null;
            res.body.validEnd = res.body.validEnd != null ? moment(res.body.validEnd) : null;
            res.body.insertTime = res.body.insertTime != null ? moment(res.body.insertTime) : null;
            res.body.updateTime = res.body.updateTime != null ? moment(res.body.updateTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((planInfoDataAtchHis: IPlanInfoDataAtchHisSdmSuffix) => {
                planInfoDataAtchHis.validBegin = planInfoDataAtchHis.validBegin != null ? moment(planInfoDataAtchHis.validBegin) : null;
                planInfoDataAtchHis.validEnd = planInfoDataAtchHis.validEnd != null ? moment(planInfoDataAtchHis.validEnd) : null;
                planInfoDataAtchHis.insertTime = planInfoDataAtchHis.insertTime != null ? moment(planInfoDataAtchHis.insertTime) : null;
                planInfoDataAtchHis.updateTime = planInfoDataAtchHis.updateTime != null ? moment(planInfoDataAtchHis.updateTime) : null;
            });
        }
        return res;
    }
}
