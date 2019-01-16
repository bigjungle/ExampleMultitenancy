import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlanInfoStepAtchSdmSuffix } from 'app/shared/model/plan-info-step-atch-sdm-suffix.model';

type EntityResponseType = HttpResponse<IPlanInfoStepAtchSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IPlanInfoStepAtchSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class PlanInfoStepAtchSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/plan-info-step-atches';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/plan-info-step-atches';

    constructor(protected http: HttpClient) {}

    create(planInfoStepAtch: IPlanInfoStepAtchSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(planInfoStepAtch);
        return this.http
            .post<IPlanInfoStepAtchSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(planInfoStepAtch: IPlanInfoStepAtchSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(planInfoStepAtch);
        return this.http
            .put<IPlanInfoStepAtchSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPlanInfoStepAtchSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPlanInfoStepAtchSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPlanInfoStepAtchSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(planInfoStepAtch: IPlanInfoStepAtchSdmSuffix): IPlanInfoStepAtchSdmSuffix {
        const copy: IPlanInfoStepAtchSdmSuffix = Object.assign({}, planInfoStepAtch, {
            validBegin:
                planInfoStepAtch.validBegin != null && planInfoStepAtch.validBegin.isValid() ? planInfoStepAtch.validBegin.toJSON() : null,
            validEnd: planInfoStepAtch.validEnd != null && planInfoStepAtch.validEnd.isValid() ? planInfoStepAtch.validEnd.toJSON() : null,
            insertTime:
                planInfoStepAtch.insertTime != null && planInfoStepAtch.insertTime.isValid() ? planInfoStepAtch.insertTime.toJSON() : null,
            updateTime:
                planInfoStepAtch.updateTime != null && planInfoStepAtch.updateTime.isValid() ? planInfoStepAtch.updateTime.toJSON() : null
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
            res.body.forEach((planInfoStepAtch: IPlanInfoStepAtchSdmSuffix) => {
                planInfoStepAtch.validBegin = planInfoStepAtch.validBegin != null ? moment(planInfoStepAtch.validBegin) : null;
                planInfoStepAtch.validEnd = planInfoStepAtch.validEnd != null ? moment(planInfoStepAtch.validEnd) : null;
                planInfoStepAtch.insertTime = planInfoStepAtch.insertTime != null ? moment(planInfoStepAtch.insertTime) : null;
                planInfoStepAtch.updateTime = planInfoStepAtch.updateTime != null ? moment(planInfoStepAtch.updateTime) : null;
            });
        }
        return res;
    }
}
