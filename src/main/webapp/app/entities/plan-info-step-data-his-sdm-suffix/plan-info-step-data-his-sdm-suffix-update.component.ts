import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IPlanInfoStepDataHisSdmSuffix } from 'app/shared/model/plan-info-step-data-his-sdm-suffix.model';
import { PlanInfoStepDataHisSdmSuffixService } from './plan-info-step-data-his-sdm-suffix.service';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix';
import { IRmsDepSdmSuffix } from 'app/shared/model/rms-dep-sdm-suffix.model';
import { RmsDepSdmSuffixService } from 'app/entities/rms-dep-sdm-suffix';
import { IPlanInfoStepSdmSuffix } from 'app/shared/model/plan-info-step-sdm-suffix.model';
import { PlanInfoStepSdmSuffixService } from 'app/entities/plan-info-step-sdm-suffix';
import { IPlanInfoDataHisSdmSuffix } from 'app/shared/model/plan-info-data-his-sdm-suffix.model';
import { PlanInfoDataHisSdmSuffixService } from 'app/entities/plan-info-data-his-sdm-suffix';

@Component({
    selector: 'jhi-plan-info-step-data-his-sdm-suffix-update',
    templateUrl: './plan-info-step-data-his-sdm-suffix-update.component.html'
})
export class PlanInfoStepDataHisSdmSuffixUpdateComponent implements OnInit {
    planInfoStepDataHis: IPlanInfoStepDataHisSdmSuffix;
    isSaving: boolean;

    rmsusers: IRmsUserSdmSuffix[];

    rmsdeps: IRmsDepSdmSuffix[];

    planinfosteps: IPlanInfoStepSdmSuffix[];

    planinfodatahis: IPlanInfoDataHisSdmSuffix[];
    validBegin: string;
    validEnd: string;
    insertTime: string;
    updateTime: string;
    verifyTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected planInfoStepDataHisService: PlanInfoStepDataHisSdmSuffixService,
        protected rmsUserService: RmsUserSdmSuffixService,
        protected rmsDepService: RmsDepSdmSuffixService,
        protected planInfoStepService: PlanInfoStepSdmSuffixService,
        protected planInfoDataHisService: PlanInfoDataHisSdmSuffixService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ planInfoStepDataHis }) => {
            this.planInfoStepDataHis = planInfoStepDataHis;
            this.validBegin =
                this.planInfoStepDataHis.validBegin != null ? this.planInfoStepDataHis.validBegin.format(DATE_TIME_FORMAT) : null;
            this.validEnd = this.planInfoStepDataHis.validEnd != null ? this.planInfoStepDataHis.validEnd.format(DATE_TIME_FORMAT) : null;
            this.insertTime =
                this.planInfoStepDataHis.insertTime != null ? this.planInfoStepDataHis.insertTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime =
                this.planInfoStepDataHis.updateTime != null ? this.planInfoStepDataHis.updateTime.format(DATE_TIME_FORMAT) : null;
            this.verifyTime =
                this.planInfoStepDataHis.verifyTime != null ? this.planInfoStepDataHis.verifyTime.format(DATE_TIME_FORMAT) : null;
        });
        this.rmsUserService.query().subscribe(
            (res: HttpResponse<IRmsUserSdmSuffix[]>) => {
                this.rmsusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.rmsDepService.query().subscribe(
            (res: HttpResponse<IRmsDepSdmSuffix[]>) => {
                this.rmsdeps = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.planInfoStepService.query().subscribe(
            (res: HttpResponse<IPlanInfoStepSdmSuffix[]>) => {
                this.planinfosteps = res.body;
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
        this.dataUtils.clearInputImage(this.planInfoStepDataHis, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.planInfoStepDataHis.validBegin = this.validBegin != null ? moment(this.validBegin, DATE_TIME_FORMAT) : null;
        this.planInfoStepDataHis.validEnd = this.validEnd != null ? moment(this.validEnd, DATE_TIME_FORMAT) : null;
        this.planInfoStepDataHis.insertTime = this.insertTime != null ? moment(this.insertTime, DATE_TIME_FORMAT) : null;
        this.planInfoStepDataHis.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        this.planInfoStepDataHis.verifyTime = this.verifyTime != null ? moment(this.verifyTime, DATE_TIME_FORMAT) : null;
        if (this.planInfoStepDataHis.id !== undefined) {
            this.subscribeToSaveResponse(this.planInfoStepDataHisService.update(this.planInfoStepDataHis));
        } else {
            this.subscribeToSaveResponse(this.planInfoStepDataHisService.create(this.planInfoStepDataHis));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlanInfoStepDataHisSdmSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPlanInfoStepDataHisSdmSuffix>) => this.onSaveSuccess(),
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

    trackRmsDepById(index: number, item: IRmsDepSdmSuffix) {
        return item.id;
    }

    trackPlanInfoStepById(index: number, item: IPlanInfoStepSdmSuffix) {
        return item.id;
    }

    trackPlanInfoDataHisById(index: number, item: IPlanInfoDataHisSdmSuffix) {
        return item.id;
    }
}
