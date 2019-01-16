import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IPlanInfoDataAtchSdmSuffix } from 'app/shared/model/plan-info-data-atch-sdm-suffix.model';
import { PlanInfoDataAtchSdmSuffixService } from './plan-info-data-atch-sdm-suffix.service';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix';
import { IPlanInfoAtchSdmSuffix } from 'app/shared/model/plan-info-atch-sdm-suffix.model';
import { PlanInfoAtchSdmSuffixService } from 'app/entities/plan-info-atch-sdm-suffix';
import { IPlanInfoDataSdmSuffix } from 'app/shared/model/plan-info-data-sdm-suffix.model';
import { PlanInfoDataSdmSuffixService } from 'app/entities/plan-info-data-sdm-suffix';

@Component({
    selector: 'jhi-plan-info-data-atch-sdm-suffix-update',
    templateUrl: './plan-info-data-atch-sdm-suffix-update.component.html'
})
export class PlanInfoDataAtchSdmSuffixUpdateComponent implements OnInit {
    planInfoDataAtch: IPlanInfoDataAtchSdmSuffix;
    isSaving: boolean;

    rmsusers: IRmsUserSdmSuffix[];

    planinfoatches: IPlanInfoAtchSdmSuffix[];

    planinfodata: IPlanInfoDataSdmSuffix[];
    validBegin: string;
    validEnd: string;
    insertTime: string;
    updateTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected planInfoDataAtchService: PlanInfoDataAtchSdmSuffixService,
        protected rmsUserService: RmsUserSdmSuffixService,
        protected planInfoAtchService: PlanInfoAtchSdmSuffixService,
        protected planInfoDataService: PlanInfoDataSdmSuffixService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ planInfoDataAtch }) => {
            this.planInfoDataAtch = planInfoDataAtch;
            this.validBegin = this.planInfoDataAtch.validBegin != null ? this.planInfoDataAtch.validBegin.format(DATE_TIME_FORMAT) : null;
            this.validEnd = this.planInfoDataAtch.validEnd != null ? this.planInfoDataAtch.validEnd.format(DATE_TIME_FORMAT) : null;
            this.insertTime = this.planInfoDataAtch.insertTime != null ? this.planInfoDataAtch.insertTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.planInfoDataAtch.updateTime != null ? this.planInfoDataAtch.updateTime.format(DATE_TIME_FORMAT) : null;
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
        this.dataUtils.clearInputImage(this.planInfoDataAtch, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.planInfoDataAtch.validBegin = this.validBegin != null ? moment(this.validBegin, DATE_TIME_FORMAT) : null;
        this.planInfoDataAtch.validEnd = this.validEnd != null ? moment(this.validEnd, DATE_TIME_FORMAT) : null;
        this.planInfoDataAtch.insertTime = this.insertTime != null ? moment(this.insertTime, DATE_TIME_FORMAT) : null;
        this.planInfoDataAtch.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.planInfoDataAtch.id !== undefined) {
            this.subscribeToSaveResponse(this.planInfoDataAtchService.update(this.planInfoDataAtch));
        } else {
            this.subscribeToSaveResponse(this.planInfoDataAtchService.create(this.planInfoDataAtch));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlanInfoDataAtchSdmSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPlanInfoDataAtchSdmSuffix>) => this.onSaveSuccess(),
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

    trackPlanInfoDataById(index: number, item: IPlanInfoDataSdmSuffix) {
        return item.id;
    }
}
