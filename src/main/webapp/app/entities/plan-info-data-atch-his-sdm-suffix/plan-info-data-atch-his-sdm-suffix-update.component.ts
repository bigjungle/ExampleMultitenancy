import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IPlanInfoDataAtchHisSdmSuffix } from 'app/shared/model/plan-info-data-atch-his-sdm-suffix.model';
import { PlanInfoDataAtchHisSdmSuffixService } from './plan-info-data-atch-his-sdm-suffix.service';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix';
import { IPlanInfoAtchSdmSuffix } from 'app/shared/model/plan-info-atch-sdm-suffix.model';
import { PlanInfoAtchSdmSuffixService } from 'app/entities/plan-info-atch-sdm-suffix';
import { IPlanInfoDataHisSdmSuffix } from 'app/shared/model/plan-info-data-his-sdm-suffix.model';
import { PlanInfoDataHisSdmSuffixService } from 'app/entities/plan-info-data-his-sdm-suffix';

@Component({
    selector: 'jhi-plan-info-data-atch-his-sdm-suffix-update',
    templateUrl: './plan-info-data-atch-his-sdm-suffix-update.component.html'
})
export class PlanInfoDataAtchHisSdmSuffixUpdateComponent implements OnInit {
    planInfoDataAtchHis: IPlanInfoDataAtchHisSdmSuffix;
    isSaving: boolean;

    rmsusers: IRmsUserSdmSuffix[];

    planinfoatches: IPlanInfoAtchSdmSuffix[];

    planinfodatahis: IPlanInfoDataHisSdmSuffix[];
    validBegin: string;
    validEnd: string;
    insertTime: string;
    updateTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected planInfoDataAtchHisService: PlanInfoDataAtchHisSdmSuffixService,
        protected rmsUserService: RmsUserSdmSuffixService,
        protected planInfoAtchService: PlanInfoAtchSdmSuffixService,
        protected planInfoDataHisService: PlanInfoDataHisSdmSuffixService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ planInfoDataAtchHis }) => {
            this.planInfoDataAtchHis = planInfoDataAtchHis;
            this.validBegin =
                this.planInfoDataAtchHis.validBegin != null ? this.planInfoDataAtchHis.validBegin.format(DATE_TIME_FORMAT) : null;
            this.validEnd = this.planInfoDataAtchHis.validEnd != null ? this.planInfoDataAtchHis.validEnd.format(DATE_TIME_FORMAT) : null;
            this.insertTime =
                this.planInfoDataAtchHis.insertTime != null ? this.planInfoDataAtchHis.insertTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime =
                this.planInfoDataAtchHis.updateTime != null ? this.planInfoDataAtchHis.updateTime.format(DATE_TIME_FORMAT) : null;
        });
        this.rmsUserService.query().subscribe(
            (res: HttpResponse<IRmsUserSdmSuffix[]>) => {
                this.rmsusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.planInfoAtchService.query().subscribe(
            (res: HttpResponse<IPlanInfoAtchSdmSuffix[]>) => {
                this.planinfoatches = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.planInfoDataHisService.query().subscribe(
            (res: HttpResponse<IPlanInfoDataHisSdmSuffix[]>) => {
                this.planinfodatahis = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.planInfoDataAtchHis, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.planInfoDataAtchHis.validBegin = this.validBegin != null ? moment(this.validBegin, DATE_TIME_FORMAT) : null;
        this.planInfoDataAtchHis.validEnd = this.validEnd != null ? moment(this.validEnd, DATE_TIME_FORMAT) : null;
        this.planInfoDataAtchHis.insertTime = this.insertTime != null ? moment(this.insertTime, DATE_TIME_FORMAT) : null;
        this.planInfoDataAtchHis.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.planInfoDataAtchHis.id !== undefined) {
            this.subscribeToSaveResponse(this.planInfoDataAtchHisService.update(this.planInfoDataAtchHis));
        } else {
            this.subscribeToSaveResponse(this.planInfoDataAtchHisService.create(this.planInfoDataAtchHis));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlanInfoDataAtchHisSdmSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPlanInfoDataAtchHisSdmSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackRmsUserById(index: number, item: IRmsUserSdmSuffix) {
        return item.id;
    }

    trackPlanInfoAtchById(index: number, item: IPlanInfoAtchSdmSuffix) {
        return item.id;
    }

    trackPlanInfoDataHisById(index: number, item: IPlanInfoDataHisSdmSuffix) {
        return item.id;
    }
}
