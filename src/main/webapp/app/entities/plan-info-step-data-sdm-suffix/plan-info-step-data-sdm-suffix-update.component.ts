import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IPlanInfoStepDataSdmSuffix } from 'app/shared/model/plan-info-step-data-sdm-suffix.model';
import { PlanInfoStepDataSdmSuffixService } from './plan-info-step-data-sdm-suffix.service';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix';
import { IRmsDepSdmSuffix } from 'app/shared/model/rms-dep-sdm-suffix.model';
import { RmsDepSdmSuffixService } from 'app/entities/rms-dep-sdm-suffix';
import { IPlanInfoStepSdmSuffix } from 'app/shared/model/plan-info-step-sdm-suffix.model';
import { PlanInfoStepSdmSuffixService } from 'app/entities/plan-info-step-sdm-suffix';
import { IPlanInfoDataSdmSuffix } from 'app/shared/model/plan-info-data-sdm-suffix.model';
import { PlanInfoDataSdmSuffixService } from 'app/entities/plan-info-data-sdm-suffix';

@Component({
    selector: 'jhi-plan-info-step-data-sdm-suffix-update',
    templateUrl: './plan-info-step-data-sdm-suffix-update.component.html'
})
export class PlanInfoStepDataSdmSuffixUpdateComponent implements OnInit {
    planInfoStepData: IPlanInfoStepDataSdmSuffix;
    isSaving: boolean;

    rmsusers: IRmsUserSdmSuffix[];

    rmsdeps: IRmsDepSdmSuffix[];

    planinfosteps: IPlanInfoStepSdmSuffix[];

    planinfodata: IPlanInfoDataSdmSuffix[];
    validBegin: string;
    validEnd: string;
    insertTime: string;
    updateTime: string;
    verifyTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected planInfoStepDataService: PlanInfoStepDataSdmSuffixService,
        protected rmsUserService: RmsUserSdmSuffixService,
        protected rmsDepService: RmsDepSdmSuffixService,
        protected planInfoStepService: PlanInfoStepSdmSuffixService,
        protected planInfoDataService: PlanInfoDataSdmSuffixService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ planInfoStepData }) => {
            this.planInfoStepData = planInfoStepData;
            this.validBegin = this.planInfoStepData.validBegin != null ? this.planInfoStepData.validBegin.format(DATE_TIME_FORMAT) : null;
            this.validEnd = this.planInfoStepData.validEnd != null ? this.planInfoStepData.validEnd.format(DATE_TIME_FORMAT) : null;
            this.insertTime = this.planInfoStepData.insertTime != null ? this.planInfoStepData.insertTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.planInfoStepData.updateTime != null ? this.planInfoStepData.updateTime.format(DATE_TIME_FORMAT) : null;
            this.verifyTime = this.planInfoStepData.verifyTime != null ? this.planInfoStepData.verifyTime.format(DATE_TIME_FORMAT) : null;
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
        this.planInfoDataService.query().subscribe(
            (res: HttpResponse<IPlanInfoDataSdmSuffix[]>) => {
                this.planinfodata = res.body;
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
        this.dataUtils.clearInputImage(this.planInfoStepData, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.planInfoStepData.validBegin = this.validBegin != null ? moment(this.validBegin, DATE_TIME_FORMAT) : null;
        this.planInfoStepData.validEnd = this.validEnd != null ? moment(this.validEnd, DATE_TIME_FORMAT) : null;
        this.planInfoStepData.insertTime = this.insertTime != null ? moment(this.insertTime, DATE_TIME_FORMAT) : null;
        this.planInfoStepData.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        this.planInfoStepData.verifyTime = this.verifyTime != null ? moment(this.verifyTime, DATE_TIME_FORMAT) : null;
        if (this.planInfoStepData.id !== undefined) {
            this.subscribeToSaveResponse(this.planInfoStepDataService.update(this.planInfoStepData));
        } else {
            this.subscribeToSaveResponse(this.planInfoStepDataService.create(this.planInfoStepData));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlanInfoStepDataSdmSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPlanInfoStepDataSdmSuffix>) => this.onSaveSuccess(),
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

    trackPlanInfoDataById(index: number, item: IPlanInfoDataSdmSuffix) {
        return item.id;
    }
}
