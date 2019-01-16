import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlanInfoStepDataSdmSuffix } from 'app/shared/model/plan-info-step-data-sdm-suffix.model';

type EntityResponseType = HttpResponse<IPlanInfoStepDataSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IPlanInfoStepDataSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class PlanInfoStepDataSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/plan-info-step-data';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/plan-info-step-data';

    constructor(protected http: HttpClient) {}

    create(planInfoStepData: IPlanInfoStepDataSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(planInfoStepData);
        return this.http
            .post<IPlanInfoStepDataSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(planInfoStepData: IPlanInfoStepDataSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(planInfoStepData);
        return this.http
            .put<IPlanInfoStepDataSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPlanInfoStepDataSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPlanInfoStepDataSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPlanInfoStepDataSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(planInfoStepData: IPlanInfoStepDataSdmSuffix): IPlanInfoStepDataSdmSuffix {
        const copy: IPlanInfoStepDataSdmSuffix = Object.assign({}, planInfoStepData, {
            validBegin:
                planInfoStepData.validBegin != null && planInfoStepData.validBegin.isValid() ? planInfoStepData.validBegin.toJSON() : null,
            validEnd: planInfoStepData.validEnd != null && planInfoStepData.validEnd.isValid() ? planInfoStepData.validEnd.toJSON() : null,
            insertTime:
                planInfoStepData.insertTime != null && planInfoStepData.insertTime.isValid() ? planInfoStepData.insertTime.toJSON() : null,
            updateTime:
                planInfoStepData.updateTime != null && planInfoStepData.updateTime.isValid() ? planInfoStepData.updateTime.toJSON() : null,
            verifyTime:
                planInfoStepData.verifyTime != null && planInfoStepData.verifyTime.isValid() ? planInfoStepData.verifyTime.toJSON() : null
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
            res.body.forEach((planInfoStepData: IPlanInfoStepDataSdmSuffix) => {
                planInfoStepData.validBegin = planInfoStepData.validBegin != null ? moment(planInfoStepData.validBegin) : null;
                planInfoStepData.validEnd = planInfoStepData.validEnd != null ? moment(planInfoStepData.validEnd) : null;
                planInfoStepData.insertTime = planInfoStepData.insertTime != null ? moment(planInfoStepData.insertTime) : null;
                planInfoStepData.updateTime = planInfoStepData.updateTime != null ? moment(planInfoStepData.updateTime) : null;
                planInfoStepData.verifyTime = planInfoStepData.verifyTime != null ? moment(planInfoStepData.verifyTime) : null;
            });
        }
        return res;
    }
}
