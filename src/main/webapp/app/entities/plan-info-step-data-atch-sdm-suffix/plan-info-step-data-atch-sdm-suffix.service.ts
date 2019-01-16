import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlanInfoStepDataAtchSdmSuffix } from 'app/shared/model/plan-info-step-data-atch-sdm-suffix.model';

type EntityResponseType = HttpResponse<IPlanInfoStepDataAtchSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IPlanInfoStepDataAtchSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class PlanInfoStepDataAtchSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/plan-info-step-data-atches';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/plan-info-step-data-atches';

    constructor(protected http: HttpClient) {}

    create(planInfoStepDataAtch: IPlanInfoStepDataAtchSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(planInfoStepDataAtch);
        return this.http
            .post<IPlanInfoStepDataAtchSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(planInfoStepDataAtch: IPlanInfoStepDataAtchSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(planInfoStepDataAtch);
        return this.http
            .put<IPlanInfoStepDataAtchSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPlanInfoStepDataAtchSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPlanInfoStepDataAtchSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPlanInfoStepDataAtchSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(planInfoStepDataAtch: IPlanInfoStepDataAtchSdmSuffix): IPlanInfoStepDataAtchSdmSuffix {
        const copy: IPlanInfoStepDataAtchSdmSuffix = Object.assign({}, planInfoStepDataAtch, {
            validBegin:
                planInfoStepDataAtch.validBegin != null && planInfoStepDataAtch.validBegin.isValid()
                    ? planInfoStepDataAtch.validBegin.toJSON()
                    : null,
            validEnd:
                planInfoStepDataAtch.validEnd != null && planInfoStepDataAtch.validEnd.isValid()
                    ? planInfoStepDataAtch.validEnd.toJSON()
                    : null,
            insertTime:
                planInfoStepDataAtch.insertTime != null && planInfoStepDataAtch.insertTime.isValid()
                    ? planInfoStepDataAtch.insertTime.toJSON()
                    : null,
            updateTime:
                planInfoStepDataAtch.updateTime != null && planInfoStepDataAtch.updateTime.isValid()
                    ? planInfoStepDataAtch.updateTime.toJSON()
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
            res.body.forEach((planInfoStepDataAtch: IPlanInfoStepDataAtchSdmSuffix) => {
                planInfoStepDataAtch.validBegin = planInfoStepDataAtch.validBegin != null ? moment(planInfoStepDataAtch.validBegin) : null;
                planInfoStepDataAtch.validEnd = planInfoStepDataAtch.validEnd != null ? moment(planInfoStepDataAtch.validEnd) : null;
                planInfoStepDataAtch.insertTime = planInfoStepDataAtch.insertTime != null ? moment(planInfoStepDataAtch.insertTime) : null;
                planInfoStepDataAtch.updateTime = planInfoStepDataAtch.updateTime != null ? moment(planInfoStepDataAtch.updateTime) : null;
            });
        }
        return res;
    }
}
