import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlanInfoStepSdmSuffix } from 'app/shared/model/plan-info-step-sdm-suffix.model';

type EntityResponseType = HttpResponse<IPlanInfoStepSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IPlanInfoStepSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class PlanInfoStepSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/plan-info-steps';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/plan-info-steps';

    constructor(protected http: HttpClient) {}

    create(planInfoStep: IPlanInfoStepSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(planInfoStep);
        return this.http
            .post<IPlanInfoStepSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(planInfoStep: IPlanInfoStepSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(planInfoStep);
        return this.http
            .put<IPlanInfoStepSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPlanInfoStepSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPlanInfoStepSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPlanInfoStepSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(planInfoStep: IPlanInfoStepSdmSuffix): IPlanInfoStepSdmSuffix {
        const copy: IPlanInfoStepSdmSuffix = Object.assign({}, planInfoStep, {
            validBegin: planInfoStep.validBegin != null && planInfoStep.validBegin.isValid() ? planInfoStep.validBegin.toJSON() : null,
            validEnd: planInfoStep.validEnd != null && planInfoStep.validEnd.isValid() ? planInfoStep.validEnd.toJSON() : null,
            insertTime: planInfoStep.insertTime != null && planInfoStep.insertTime.isValid() ? planInfoStep.insertTime.toJSON() : null,
            updateTime: planInfoStep.updateTime != null && planInfoStep.updateTime.isValid() ? planInfoStep.updateTime.toJSON() : null,
            publishedTime:
                planInfoStep.publishedTime != null && planInfoStep.publishedTime.isValid() ? planInfoStep.publishedTime.toJSON() : null,
            verifyTime: planInfoStep.verifyTime != null && planInfoStep.verifyTime.isValid() ? planInfoStep.verifyTime.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.validBegin = res.body.validBegin != null ? moment(res.body.validBegin) : null;
            res.body.validEnd = res.body.validEnd != null ? moment(res.body.validEnd) : null;
            res.body.insertTime = res.body.insertTime != null ? moment(res.body.insertTime) : null;
            res.body.updateTime = res.body.updateTime != null ? moment(res.body.updateTime) : null;
            res.body.publishedTime = res.body.publishedTime != null ? moment(res.body.publishedTime) : null;
            res.body.verifyTime = res.body.verifyTime != null ? moment(res.body.verifyTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((planInfoStep: IPlanInfoStepSdmSuffix) => {
                planInfoStep.validBegin = planInfoStep.validBegin != null ? moment(planInfoStep.validBegin) : null;
                planInfoStep.validEnd = planInfoStep.validEnd != null ? moment(planInfoStep.validEnd) : null;
                planInfoStep.insertTime = planInfoStep.insertTime != null ? moment(planInfoStep.insertTime) : null;
                planInfoStep.updateTime = planInfoStep.updateTime != null ? moment(planInfoStep.updateTime) : null;
                planInfoStep.publishedTime = planInfoStep.publishedTime != null ? moment(planInfoStep.publishedTime) : null;
                planInfoStep.verifyTime = planInfoStep.verifyTime != null ? moment(planInfoStep.verifyTime) : null;
            });
        }
        return res;
    }
}
