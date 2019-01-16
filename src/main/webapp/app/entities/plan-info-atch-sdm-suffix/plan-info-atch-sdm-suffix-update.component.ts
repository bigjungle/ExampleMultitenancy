import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IPlanInfoAtchSdmSuffix } from 'app/shared/model/plan-info-atch-sdm-suffix.model';
import { PlanInfoAtchSdmSuffixService } from './plan-info-atch-sdm-suffix.service';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix';
import { IPlanInfoSdmSuffix } from 'app/shared/model/plan-info-sdm-suffix.model';
import { PlanInfoSdmSuffixService } from 'app/entities/plan-info-sdm-suffix';

@Component({
    selector: 'jhi-plan-info-atch-sdm-suffix-update',
    templateUrl: './plan-info-atch-sdm-suffix-update.component.html'
})
export class PlanInfoAtchSdmSuffixUpdateComponent implements OnInit {
    planInfoAtch: IPlanInfoAtchSdmSuffix;
    isSaving: boolean;

    rmsusers: IRmsUserSdmSuffix[];

    planinfos: IPlanInfoSdmSuffix[];
    validBegin: string;
    validEnd: string;
    insertTime: string;
    updateTime: string;
    publishedTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected planInfoAtchService: PlanInfoAtchSdmSuffixService,
        protected rmsUserService: RmsUserSdmSuffixService,
        protected planInfoService: PlanInfoSdmSuffixService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ planInfoAtch }) => {
            this.planInfoAtch = planInfoAtch;
            this.validBegin = this.planInfoAtch.validBegin != null ? this.planInfoAtch.validBegin.format(DATE_TIME_FORMAT) : null;
            this.validEnd = this.planInfoAtch.validEnd != null ? this.planInfoAtch.validEnd.format(DATE_TIME_FORMAT) : null;
            this.insertTime = this.planInfoAtch.insertTime != null ? this.planInfoAtch.insertTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.planInfoAtch.updateTime != null ? this.planInfoAtch.updateTime.format(DATE_TIME_FORMAT) : null;
            this.publishedTime = this.planInfoAtch.publishedTime != null ? this.planInfoAtch.publishedTime.format(DATE_TIME_FORMAT) : null;
        });
        this.rmsUserService.query().subscribe(
            (res: HttpResponse<IRmsUserSdmSuffix[]>) => {
                this.rmsusers = res.body;
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
        this.dataUtils.clearInputImage(this.planInfoAtch, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.planInfoAtch.validBegin = this.validBegin != null ? moment(this.validBegin, DATE_TIME_FORMAT) : null;
        this.planInfoAtch.validEnd = this.validEnd != null ? moment(this.validEnd, DATE_TIME_FORMAT) : null;
        this.planInfoAtch.insertTime = this.insertTime != null ? moment(this.insertTime, DATE_TIME_FORMAT) : null;
        this.planInfoAtch.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        this.planInfoAtch.publishedTime = this.publishedTime != null ? moment(this.publishedTime, DATE_TIME_FORMAT) : null;
        if (this.planInfoAtch.id !== undefined) {
            this.subscribeToSaveResponse(this.planInfoAtchService.update(this.planInfoAtch));
        } else {
            this.subscribeToSaveResponse(this.planInfoAtchService.create(this.planInfoAtch));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlanInfoAtchSdmSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPlanInfoAtchSdmSuffix>) => this.onSaveSuccess(),
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

    trackPlanInfoById(index: number, item: IPlanInfoSdmSuffix) {
        return item.id;
    }
}
