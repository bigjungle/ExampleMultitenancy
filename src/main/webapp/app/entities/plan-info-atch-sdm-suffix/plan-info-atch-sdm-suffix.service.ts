import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlanInfoAtchSdmSuffix } from 'app/shared/model/plan-info-atch-sdm-suffix.model';

type EntityResponseType = HttpResponse<IPlanInfoAtchSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IPlanInfoAtchSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class PlanInfoAtchSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/plan-info-atches';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/plan-info-atches';

    constructor(protected http: HttpClient) {}

    create(planInfoAtch: IPlanInfoAtchSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(planInfoAtch);
        return this.http
            .post<IPlanInfoAtchSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(planInfoAtch: IPlanInfoAtchSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(planInfoAtch);
        return this.http
            .put<IPlanInfoAtchSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPlanInfoAtchSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPlanInfoAtchSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPlanInfoAtchSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(planInfoAtch: IPlanInfoAtchSdmSuffix): IPlanInfoAtchSdmSuffix {
        const copy: IPlanInfoAtchSdmSuffix = Object.assign({}, planInfoAtch, {
            validBegin: planInfoAtch.validBegin != null && planInfoAtch.validBegin.isValid() ? planInfoAtch.validBegin.toJSON() : null,
            validEnd: planInfoAtch.validEnd != null && planInfoAtch.validEnd.isValid() ? planInfoAtch.validEnd.toJSON() : null,
            insertTime: planInfoAtch.insertTime != null && planInfoAtch.insertTime.isValid() ? planInfoAtch.insertTime.toJSON() : null,
            updateTime: planInfoAtch.updateTime != null && planInfoAtch.updateTime.isValid() ? planInfoAtch.updateTime.toJSON() : null,
            publishedTime:
                planInfoAtch.publishedTime != null && planInfoAtch.publishedTime.isValid() ? planInfoAtch.publishedTime.toJSON() : null
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
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((planInfoAtch: IPlanInfoAtchSdmSuffix) => {
                planInfoAtch.validBegin = planInfoAtch.validBegin != null ? moment(planInfoAtch.validBegin) : null;
                planInfoAtch.validEnd = planInfoAtch.validEnd != null ? moment(planInfoAtch.validEnd) : null;
                planInfoAtch.insertTime = planInfoAtch.insertTime != null ? moment(planInfoAtch.insertTime) : null;
                planInfoAtch.updateTime = planInfoAtch.updateTime != null ? moment(planInfoAtch.updateTime) : null;
                planInfoAtch.publishedTime = planInfoAtch.publishedTime != null ? moment(planInfoAtch.publishedTime) : null;
            });
        }
        return res;
    }
}
