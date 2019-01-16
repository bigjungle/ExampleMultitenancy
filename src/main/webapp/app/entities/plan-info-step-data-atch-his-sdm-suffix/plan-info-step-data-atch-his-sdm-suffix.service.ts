import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlanInfoStepDataAtchHisSdmSuffix } from 'app/shared/model/plan-info-step-data-atch-his-sdm-suffix.model';

type EntityResponseType = HttpResponse<IPlanInfoStepDataAtchHisSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IPlanInfoStepDataAtchHisSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class PlanInfoStepDataAtchHisSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/plan-info-step-data-atch-his';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/plan-info-step-data-atch-his';

    constructor(protected http: HttpClient) {}

    create(planInfoStepDataAtchHis: IPlanInfoStepDataAtchHisSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(planInfoStepDataAtchHis);
        return this.http
            .post<IPlanInfoStepDataAtchHisSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(planInfoStepDataAtchHis: IPlanInfoStepDataAtchHisSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(planInfoStepDataAtchHis);
        return this.http
            .put<IPlanInfoStepDataAtchHisSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPlanInfoStepDataAtchHisSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPlanInfoStepDataAtchHisSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPlanInfoStepDataAtchHisSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(planInfoStepDataAtchHis: IPlanInfoStepDataAtchHisSdmSuffix): IPlanInfoStepDataAtchHisSdmSuffix {
        const copy: IPlanInfoStepDataAtchHisSdmSuffix = Object.assign({}, planInfoStepDataAtchHis, {
            validBegin:
                planInfoStepDataAtchHis.validBegin != null && planInfoStepDataAtchHis.validBegin.isValid()
                    ? planInfoStepDataAtchHis.validBegin.toJSON()
                    : null,
            validEnd:
                planInfoStepDataAtchHis.validEnd != null && planInfoStepDataAtchHis.validEnd.isValid()
                    ? planInfoStepDataAtchHis.validEnd.toJSON()
                    : null,
            insertTime:
                planInfoStepDataAtchHis.insertTime != null && planInfoStepDataAtchHis.insertTime.isValid()
                    ? planInfoStepDataAtchHis.insertTime.toJSON()
                    : null,
            updateTime:
                planInfoStepDataAtchHis.updateTime != null && planInfoStepDataAtchHis.updateTime.isValid()
                    ? planInfoStepDataAtchHis.updateTime.toJSON()
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
            res.body.forEach((planInfoStepDataAtchHis: IPlanInfoStepDataAtchHisSdmSuffix) => {
                planInfoStepDataAtchHis.validBegin =
                    planInfoStepDataAtchHis.validBegin != null ? moment(planInfoStepDataAtchHis.validBegin) : null;
                planInfoStepDataAtchHis.validEnd =
                    planInfoStepDataAtchHis.validEnd != null ? moment(planInfoStepDataAtchHis.validEnd) : null;
                planInfoStepDataAtchHis.insertTime =
                    planInfoStepDataAtchHis.insertTime != null ? moment(planInfoStepDataAtchHis.insertTime) : null;
                planInfoStepDataAtchHis.updateTime =
                    planInfoStepDataAtchHis.updateTime != null ? moment(planInfoStepDataAtchHis.updateTime) : null;
            });
        }
        return res;
    }
}
