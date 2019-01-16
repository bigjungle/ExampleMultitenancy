import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IPlanInfoStepSdmSuffix } from 'app/shared/model/plan-info-step-sdm-suffix.model';
import { PlanInfoStepSdmSuffixService } from './plan-info-step-sdm-suffix.service';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix';
import { IRmsDepSdmSuffix } from 'app/shared/model/rms-dep-sdm-suffix.model';
import { RmsDepSdmSuffixService } from 'app/entities/rms-dep-sdm-suffix';
import { IPlanInfoSdmSuffix } from 'app/shared/model/plan-info-sdm-suffix.model';
import { PlanInfoSdmSuffixService } from 'app/entities/plan-info-sdm-suffix';

@Component({
    selector: 'jhi-plan-info-step-sdm-suffix-update',
    templateUrl: './plan-info-step-sdm-suffix-update.component.html'
})
export class PlanInfoStepSdmSuffixUpdateComponent implements OnInit {
    planInfoStep: IPlanInfoStepSdmSuffix;
    isSaving: boolean;

    rmsusers: IRmsUserSdmSuffix[];

    rmsdeps: IRmsDepSdmSuffix[];

    planinfos: IPlanInfoSdmSuffix[];
    validBegin: string;
    validEnd: string;
    insertTime: string;
    updateTime: string;
    publishedTime: string;
    verifyTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected planInfoStepService: PlanInfoStepSdmSuffixService,
        protected rmsUserService: RmsUserSdmSuffixService,
        protected rmsDepService: RmsDepSdmSuffixService,
        protected planInfoService: PlanInfoSdmSuffixService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ planInfoStep }) => {
            this.planInfoStep = planInfoStep;
            this.validBegin = this.planInfoStep.validBegin != null ? this.planInfoStep.validBegin.format(DATE_TIME_FORMAT) : null;
            this.validEnd = this.planInfoStep.validEnd != null ? this.planInfoStep.validEnd.format(DATE_TIME_FORMAT) : null;
            this.insertTime = this.planInfoStep.insertTime != null ? this.planInfoStep.insertTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.planInfoStep.updateTime != null ? this.planInfoStep.updateTime.format(DATE_TIME_FORMAT) : null;
            this.publishedTime = this.planInfoStep.publishedTime != null ? this.planInfoStep.publishedTime.format(DATE_TIME_FORMAT) : null;
            this.verifyTime = this.planInfoStep.verifyTime != null ? this.planInfoStep.verifyTime.format(DATE_TIME_FORMAT) : null;
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
        this.planInfoService.query().subscribe(
            (res: HttpResponse<IPlanInfoSdmSuffix[]>) => {
                this.planinfos = res.body;
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
        this.dataUtils.clearInputImage(this.planInfoStep, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.planInfoStep.validBegin = this.validBegin != null ? moment(this.validBegin, DATE_TIME_FORMAT) : null;
        this.planInfoStep.validEnd = this.validEnd != null ? moment(this.validEnd, DATE_TIME_FORMAT) : null;
        this.planInfoStep.insertTime = this.insertTime != null ? moment(this.insertTime, DATE_TIME_FORMAT) : null;
        this.planInfoStep.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        this.planInfoStep.publishedTime = this.publishedTime != null ? moment(this.publishedTime, DATE_TIME_FORMAT) : null;
        this.planInfoStep.verifyTime = this.verifyTime != null ? moment(this.verifyTime, DATE_TIME_FORMAT) : null;
        if (this.planInfoStep.id !== undefined) {
            this.subscribeToSaveResponse(this.planInfoStepService.update(this.planInfoStep));
        } else {
            this.subscribeToSaveResponse(this.planInfoStepService.create(this.planInfoStep));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlanInfoStepSdmSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPlanInfoStepSdmSuffix>) => this.onSaveSuccess(),
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

    trackPlanInfoById(index: number, item: IPlanInfoSdmSuffix) {
        return item.id;
    }
}
