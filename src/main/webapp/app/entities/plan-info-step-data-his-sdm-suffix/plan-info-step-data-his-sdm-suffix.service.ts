import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlanInfoStepDataHisSdmSuffix } from 'app/shared/model/plan-info-step-data-his-sdm-suffix.model';

type EntityResponseType = HttpResponse<IPlanInfoStepDataHisSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IPlanInfoStepDataHisSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class PlanInfoStepDataHisSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/plan-info-step-data-his';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/plan-info-step-data-his';

    constructor(protected http: HttpClient) {}

    create(planInfoStepDataHis: IPlanInfoStepDataHisSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(planInfoStepDataHis);
        return this.http
            .post<IPlanInfoStepDataHisSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(planInfoStepDataHis: IPlanInfoStepDataHisSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(planInfoStepDataHis);
        return this.http
            .put<IPlanInfoStepDataHisSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPlanInfoStepDataHisSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPlanInfoStepDataHisSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPlanInfoStepDataHisSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(planInfoStepDataHis: IPlanInfoStepDataHisSdmSuffix): IPlanInfoStepDataHisSdmSuffix {
        const copy: IPlanInfoStepDataHisSdmSuffix = Object.assign({}, planInfoStepDataHis, {
            validBegin:
                planInfoStepDataHis.validBegin != null && planInfoStepDataHis.validBegin.isValid()
                    ? planInfoStepDataHis.validBegin.toJSON()
                    : null,
            validEnd:
                planInfoStepDataHis.validEnd != null && planInfoStepDataHis.validEnd.isValid()
                    ? planInfoStepDataHis.validEnd.toJSON()
                    : null,
            insertTime:
                planInfoStepDataHis.insertTime != null && planInfoStepDataHis.insertTime.isValid()
                    ? planInfoStepDataHis.insertTime.toJSON()
                    : null,
            updateTime:
                planInfoStepDataHis.updateTime != null && planInfoStepDataHis.updateTime.isValid()
                    ? planInfoStepDataHis.updateTime.toJSON()
                    : null,
            verifyTime:
                planInfoStepDataHis.verifyTime != null && planInfoStepDataHis.verifyTime.isValid()
                    ? planInfoStepDataHis.verifyTime.toJSON()
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
            res.body.verifyTime = res.body.verifyTime != null ? moment(res.body.verifyTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((planInfoStepDataHis: IPlanInfoStepDataHisSdmSuffix) => {
                planInfoStepDataHis.validBegin = planInfoStepDataHis.validBegin != null ? moment(planInfoStepDataHis.validBegin) : null;
                planInfoStepDataHis.validEnd = planInfoStepDataHis.validEnd != null ? moment(planInfoStepDataHis.validEnd) : null;
                planInfoStepDataHis.insertTime = planInfoStepDataHis.insertTime != null ? moment(planInfoStepDataHis.insertTime) : null;
                planInfoStepDataHis.updateTime = planInfoStepDataHis.updateTime != null ? moment(planInfoStepDataHis.updateTime) : null;
                planInfoStepDataHis.verifyTime = planInfoStepDataHis.verifyTime != null ? moment(planInfoStepDataHis.verifyTime) : null;
            });
        }
        return res;
    }
}
