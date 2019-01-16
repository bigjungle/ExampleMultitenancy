import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRmsNodeSdmSuffix } from 'app/shared/model/rms-node-sdm-suffix.model';

type EntityResponseType = HttpResponse<IRmsNodeSdmSuffix>;
type EntityArrayResponseType = HttpResponse<IRmsNodeSdmSuffix[]>;

@Injectable({ providedIn: 'root' })
export class RmsNodeSdmSuffixService {
    public resourceUrl = SERVER_API_URL + 'api/rms-nodes';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/rms-nodes';

    constructor(protected http: HttpClient) {}

    create(rmsNode: IRmsNodeSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(rmsNode);
        return this.http
            .post<IRmsNodeSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(rmsNode: IRmsNodeSdmSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(rmsNode);
        return this.http
            .put<IRmsNodeSdmSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRmsNodeSdmSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRmsNodeSdmSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRmsNodeSdmSuffix[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(rmsNode: IRmsNodeSdmSuffix): IRmsNodeSdmSuffix {
        const copy: IRmsNodeSdmSuffix = Object.assign({}, rmsNode, {
            validBegin: rmsNode.validBegin != null && rmsNode.validBegin.isValid() ? rmsNode.validBegin.toJSON() : null,
            validEnd: rmsNode.validEnd != null && rmsNode.validEnd.isValid() ? rmsNode.validEnd.toJSON() : null,
            insertTime: rmsNode.insertTime != null && rmsNode.insertTime.isValid() ? rmsNode.insertTime.toJSON() : null,
            updateTime: rmsNode.updateTime != null && rmsNode.updateTime.isValid() ? rmsNode.updateTime.toJSON() : null,
            verifyTime: rmsNode.verifyTime != null && rmsNode.verifyTime.isValid() ? rmsNode.verifyTime.toJSON() : null
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
            res.body.forEach((rmsNode: IRmsNodeSdmSuffix) => {
                rmsNode.validBegin = rmsNode.validBegin != null ? moment(rmsNode.validBegin) : null;
                rmsNode.validEnd = rmsNode.validEnd != null ? moment(rmsNode.validEnd) : null;
                rmsNode.insertTime = rmsNode.insertTime != null ? moment(rmsNode.insertTime) : null;
                rmsNode.updateTime = rmsNode.updateTime != null ? moment(rmsNode.updateTime) : null;
                rmsNode.verifyTime = rmsNode.verifyTime != null ? moment(rmsNode.verifyTime) : null;
            });
        }
        return res;
    }
}
