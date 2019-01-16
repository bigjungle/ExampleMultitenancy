import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IPlanInfoStepAtchSdmSuffix } from 'app/shared/model/plan-info-step-atch-sdm-suffix.model';
import { PlanInfoStepAtchSdmSuffixService } from './plan-info-step-atch-sdm-suffix.service';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix';
import { IPlanInfoStepSdmSuffix } from 'app/shared/model/plan-info-step-sdm-suffix.model';
import { PlanInfoStepSdmSuffixService } from 'app/entities/plan-info-step-sdm-suffix';

@Component({
    selector: 'jhi-plan-info-step-atch-sdm-suffix-update',
    templateUrl: './plan-info-step-atch-sdm-suffix-update.component.html'
})
export class PlanInfoStepAtchSdmSuffixUpdateComponent implements OnInit {
    planInfoStepAtch: IPlanInfoStepAtchSdmSuffix;
    isSaving: boolean;

    rmsusers: IRmsUserSdmSuffix[];

    planinfosteps: IPlanInfoStepSdmSuffix[];
    validBegin: string;
    validEnd: string;
    insertTime: string;
    updateTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected planInfoStepAtchService: PlanInfoStepAtchSdmSuffixService,
        protected rmsUserService: RmsUserSdmSuffixService,
        protected planInfoStepService: PlanInfoStepSdmSuffixService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ planInfoStepAtch }) => {
            this.planInfoStepAtch = planInfoStepAtch;
            this.validBegin = this.planInfoStepAtch.validBegin != null ? this.planInfoStepAtch.validBegin.format(DATE_TIME_FORMAT) : null;
            this.validEnd = this.planInfoStepAtch.validEnd != null ? this.planInfoStepAtch.validEnd.format(DATE_TIME_FORMAT) : null;
            this.insertTime = this.planInfoStepAtch.insertTime != null ? this.planInfoStepAtch.insertTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.planInfoStepAtch.updateTime != null ? this.planInfoStepAtch.updateTime.format(DATE_TIME_FORMAT) : null;
        });
        this.rmsUserService.query().subscribe(
            (res: HttpResponse<IRmsUserSdmSuffix[]>) => {
                this.rmsusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.planInfoStepService.query().subscribe(
            (res: HttpResponse<IPlanInfoStepSdmSuffix[]>) => {
                this.planinfosteps = res.body;
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
        this.dataUtils.clearInputImage(this.planInfoStepAtch, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.planInfoStepAtch.validBegin = this.validBegin != null ? moment(this.validBegin, DATE_TIME_FORMAT) : null;
        this.planInfoStepAtch.validEnd = this.validEnd != null ? moment(this.validEnd, DATE_TIME_FORMAT) : null;
        this.planInfoStepAtch.insertTime = this.insertTime != null ? moment(this.insertTime, DATE_TIME_FORMAT) : null;
        this.planInfoStepAtch.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.planInfoStepAtch.id !== undefined) {
            this.subscribeToSaveResponse(this.planInfoStepAtchService.update(this.planInfoStepAtch));
        } else {
            this.subscribeToSaveResponse(this.planInfoStepAtchService.create(this.planInfoStepAtch));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlanInfoStepAtchSdmSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPlanInfoStepAtchSdmSuffix>) => this.onSaveSuccess(),
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

    trackPlanInfoStepById(index: number, item: IPlanInfoStepSdmSuffix) {
        return item.id;
    }
}
