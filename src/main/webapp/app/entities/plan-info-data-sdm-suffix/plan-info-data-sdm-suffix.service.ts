import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlanInfoDataSdmSuffix } from 'app/shared/model/plan-info-data-sdm-suffix.model';

type EntityResponseType = HttpResponse<IPlanInfoDataSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IPlanInfoDataSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class PlanInfoDataSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/plan-info-data';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/plan-info-data';

    constructor(protected http: HttpClient) {}

    create(planInfoData: IPlanInfoDataSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(planInfoData);
        return this.http
            .post<IPlanInfoDataSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(planInfoData: IPlanInfoDataSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(planInfoData);
        return this.http
            .put<IPlanInfoDataSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPlanInfoDataSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPlanInfoDataSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPlanInfoDataSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(planInfoData: IPlanInfoDataSdmSuffix): IPlanInfoDataSdmSuffix {
        const copy: IPlanInfoDataSdmSuffix = Object.assign({}, planInfoData, {
            validBegin: planInfoData.validBegin != null && planInfoData.validBegin.isValid() ? planInfoData.validBegin.toJSON() : null,
            validEnd: planInfoData.validEnd != null && planInfoData.validEnd.isValid() ? planInfoData.validEnd.toJSON() : null,
            insertTime: planInfoData.insertTime != null && planInfoData.insertTime.isValid() ? planInfoData.insertTime.toJSON() : null,
            updateTime: planInfoData.updateTime != null && planInfoData.updateTime.isValid() ? planInfoData.updateTime.toJSON() : null,
            verifyTime: planInfoData.verifyTime != null && planInfoData.verifyTime.isValid() ? planInfoData.verifyTime.toJSON() : null
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
            res.body.forEach((planInfoData: IPlanInfoDataSdmSuffix) => {
                planInfoData.validBegin = planInfoData.validBegin != null ? moment(planInfoData.validBegin) : null;
                planInfoData.validEnd = planInfoData.validEnd != null ? moment(planInfoData.validEnd) : null;
                planInfoData.insertTime = planInfoData.insertTime != null ? moment(planInfoData.insertTime) : null;
                planInfoData.updateTime = planInfoData.updateTime != null ? moment(planInfoData.updateTime) : null;
                planInfoData.verifyTime = planInfoData.verifyTime != null ? moment(planInfoData.verifyTime) : null;
            });
        }
        return res;
    }
}
