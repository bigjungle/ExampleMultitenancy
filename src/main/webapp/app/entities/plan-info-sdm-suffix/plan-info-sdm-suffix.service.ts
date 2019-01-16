import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlanInfoSdmSuffix } from 'app/shared/model/plan-info-sdm-suffix.model';

type EntityResponseType = HttpResponse<IPlanInfoSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IPlanInfoSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class PlanInfoSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/plan-infos';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/plan-infos';

    constructor(protected http: HttpClient) {}

    create(planInfo: IPlanInfoSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(planInfo);
        return this.http
            .post<IPlanInfoSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(planInfo: IPlanInfoSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(planInfo);
        return this.http
            .put<IPlanInfoSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPlanInfoSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPlanInfoSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPlanInfoSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(planInfo: IPlanInfoSdmSuffix): IPlanInfoSdmSuffix {
        const copy: IPlanInfoSdmSuffix = Object.assign({}, planInfo, {
            validBegin: planInfo.validBegin != null && planInfo.validBegin.isValid() ? planInfo.validBegin.toJSON() : null,
            validEnd: planInfo.validEnd != null && planInfo.validEnd.isValid() ? planInfo.validEnd.toJSON() : null,
            insertTime: planInfo.insertTime != null && planInfo.insertTime.isValid() ? planInfo.insertTime.toJSON() : null,
            updateTime: planInfo.updateTime != null && planInfo.updateTime.isValid() ? planInfo.updateTime.toJSON() : null,
            publishedTime: planInfo.publishedTime != null && planInfo.publishedTime.isValid() ? planInfo.publishedTime.toJSON() : null,
            verifyTime: planInfo.verifyTime != null && planInfo.verifyTime.isValid() ? planInfo.verifyTime.toJSON() : null
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
            res.body.forEach((planInfo: IPlanInfoSdmSuffix) => {
                planInfo.validBegin = planInfo.validBegin != null ? moment(planInfo.validBegin) : null;
                planInfo.validEnd = planInfo.validEnd != null ? moment(planInfo.validEnd) : null;
                planInfo.insertTime = planInfo.insertTime != null ? moment(planInfo.insertTime) : null;
                planInfo.updateTime = planInfo.updateTime != null ? moment(planInfo.updateTime) : null;
                planInfo.publishedTime = planInfo.publishedTime != null ? moment(planInfo.publishedTime) : null;
                planInfo.verifyTime = planInfo.verifyTime != null ? moment(planInfo.verifyTime) : null;
            });
        }
        return res;
    }
}
