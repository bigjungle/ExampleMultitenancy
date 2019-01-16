import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IPlanInfoStepDataAtchSdmSuffix } from 'app/shared/model/plan-info-step-data-atch-sdm-suffix.model';
import { PlanInfoStepDataAtchSdmSuffixService } from './plan-info-step-data-atch-sdm-suffix.service';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix';
import { IPlanInfoStepAtchSdmSuffix } from 'app/shared/model/plan-info-step-atch-sdm-suffix.model';
import { PlanInfoStepAtchSdmSuffixService } from 'app/entities/plan-info-step-atch-sdm-suffix';
import { IPlanInfoStepDataSdmSuffix } from 'app/shared/model/plan-info-step-data-sdm-suffix.model';
import { PlanInfoStepDataSdmSuffixService } from 'app/entities/plan-info-step-data-sdm-suffix';

@Component({
    selector: 'jhi-plan-info-step-data-atch-sdm-suffix-update',
    templateUrl: './plan-info-step-data-atch-sdm-suffix-update.component.html'
})
export class PlanInfoStepDataAtchSdmSuffixUpdateComponent implements OnInit {
    planInfoStepDataAtch: IPlanInfoStepDataAtchSdmSuffix;
    isSaving: boolean;

    rmsusers: IRmsUserSdmSuffix[];

    planinfostepatches: IPlanInfoStepAtchSdmSuffix[];

    planinfostepdata: IPlanInfoStepDataSdmSuffix[];
    validBegin: string;
    validEnd: string;
    insertTime: string;
    updateTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected planInfoStepDataAtchService: PlanInfoStepDataAtchSdmSuffixService,
        protected rmsUserService: RmsUserSdmSuffixService,
        protected planInfoStepAtchService: PlanInfoStepAtchSdmSuffixService,
        protected planInfoStepDataService: PlanInfoStepDataSdmSuffixService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ planInfoStepDataAtch }) => {
            this.planInfoStepDataAtch = planInfoStepDataAtch;
            this.validBegin =
                this.planInfoStepDataAtch.validBegin != null ? this.planInfoStepDataAtch.validBegin.format(DATE_TIME_FORMAT) : null;
            this.validEnd = this.planInfoStepDataAtch.validEnd != null ? this.planInfoStepDataAtch.validEnd.format(DATE_TIME_FORMAT) : null;
            this.insertTime =
                this.planInfoStepDataAtch.insertTime != null ? this.planInfoStepDataAtch.insertTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime =
                this.planInfoStepDataAtch.updateTime != null ? this.planInfoStepDataAtch.updateTime.format(DATE_TIME_FORMAT) : null;
        });
        this.rmsUserService.query().subscribe(
            (res: HttpResponse<IRmsUserSdmSuffix[]>) => {
                this.rmsusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.planInfoStepAtchService.query().subscribe(
            (res: HttpResponse<IPlanInfoStepAtchSdmSuffix[]>) => {
                this.planinfostepatches = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.planInfoStepDataService.query().subscribe(
            (res: HttpResponse<IPlanInfoStepDataSdmSuffix[]>) => {
                this.planinfostepdata = res.body;
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
        this.dataUtils.clearInputImage(this.planInfoStepDataAtch, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.planInfoStepDataAtch.validBegin = this.validBegin != null ? moment(this.validBegin, DATE_TIME_FORMAT) : null;
        this.planInfoStepDataAtch.validEnd = this.validEnd != null ? moment(this.validEnd, DATE_TIME_FORMAT) : null;
        this.planInfoStepDataAtch.insertTime = this.insertTime != null ? moment(this.insertTime, DATE_TIME_FORMAT) : null;
        this.planInfoStepDataAtch.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.planInfoStepDataAtch.id !== undefined) {
            this.subscribeToSaveResponse(this.planInfoStepDataAtchService.update(this.planInfoStepDataAtch));
        } else {
            this.subscribeToSaveResponse(this.planInfoStepDataAtchService.create(this.planInfoStepDataAtch));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlanInfoStepDataAtchSdmSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPlanInfoStepDataAtchSdmSuffix>) => this.onSaveSuccess(),
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

    trackPlanInfoStepAtchById(index: number, item: IPlanInfoStepAtchSdmSuffix) {
        return item.id;
    }

    trackPlanInfoStepDataById(index: number, item: IPlanInfoStepDataSdmSuffix) {
        return item.id;
    }
}
