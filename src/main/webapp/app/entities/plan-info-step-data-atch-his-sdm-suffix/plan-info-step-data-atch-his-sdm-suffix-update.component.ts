import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IPlanInfoStepDataAtchHisSdmSuffix } from 'app/shared/model/plan-info-step-data-atch-his-sdm-suffix.model';
import { PlanInfoStepDataAtchHisSdmSuffixService } from './plan-info-step-data-atch-his-sdm-suffix.service';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix';
import { IPlanInfoStepAtchSdmSuffix } from 'app/shared/model/plan-info-step-atch-sdm-suffix.model';
import { PlanInfoStepAtchSdmSuffixService } from 'app/entities/plan-info-step-atch-sdm-suffix';
import { IPlanInfoStepDataHisSdmSuffix } from 'app/shared/model/plan-info-step-data-his-sdm-suffix.model';
import { PlanInfoStepDataHisSdmSuffixService } from 'app/entities/plan-info-step-data-his-sdm-suffix';

@Component({
    selector: 'jhi-plan-info-step-data-atch-his-sdm-suffix-update',
    templateUrl: './plan-info-step-data-atch-his-sdm-suffix-update.component.html'
})
export class PlanInfoStepDataAtchHisSdmSuffixUpdateComponent implements OnInit {
    planInfoStepDataAtchHis: IPlanInfoStepDataAtchHisSdmSuffix;
    isSaving: boolean;

    rmsusers: IRmsUserSdmSuffix[];

    planinfostepatches: IPlanInfoStepAtchSdmSuffix[];

    planinfostepdatahis: IPlanInfoStepDataHisSdmSuffix[];
    validBegin: string;
    validEnd: string;
    insertTime: string;
    updateTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected planInfoStepDataAtchHisService: PlanInfoStepDataAtchHisSdmSuffixService,
        protected rmsUserService: RmsUserSdmSuffixService,
        protected planInfoStepAtchService: PlanInfoStepAtchSdmSuffixService,
        protected planInfoStepDataHisService: PlanInfoStepDataHisSdmSuffixService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ planInfoStepDataAtchHis }) => {
            this.planInfoStepDataAtchHis = planInfoStepDataAtchHis;
            this.validBegin =
                this.planInfoStepDataAtchHis.validBegin != null ? this.planInfoStepDataAtchHis.validBegin.format(DATE_TIME_FORMAT) : null;
            this.validEnd =
                this.planInfoStepDataAtchHis.validEnd != null ? this.planInfoStepDataAtchHis.validEnd.format(DATE_TIME_FORMAT) : null;
            this.insertTime =
                this.planInfoStepDataAtchHis.insertTime != null ? this.planInfoStepDataAtchHis.insertTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime =
                this.planInfoStepDataAtchHis.updateTime != null ? this.planInfoStepDataAtchHis.updateTime.format(DATE_TIME_FORMAT) : null;
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
        this.planInfoStepDataHisService.query().subscribe(
            (res: HttpResponse<IPlanInfoStepDataHisSdmSuffix[]>) => {
                this.planinfostepdatahis = res.body;
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
        this.dataUtils.clearInputImage(this.planInfoStepDataAtchHis, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.planInfoStepDataAtchHis.validBegin = this.validBegin != null ? moment(this.validBegin, DATE_TIME_FORMAT) : null;
        this.planInfoStepDataAtchHis.validEnd = this.validEnd != null ? moment(this.validEnd, DATE_TIME_FORMAT) : null;
        this.planInfoStepDataAtchHis.insertTime = this.insertTime != null ? moment(this.insertTime, DATE_TIME_FORMAT) : null;
        this.planInfoStepDataAtchHis.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.planInfoStepDataAtchHis.id !== undefined) {
            this.subscribeToSaveResponse(this.planInfoStepDataAtchHisService.update(this.planInfoStepDataAtchHis));
        } else {
            this.subscribeToSaveResponse(this.planInfoStepDataAtchHisService.create(this.planInfoStepDataAtchHis));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlanInfoStepDataAtchHisSdmSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPlanInfoStepDataAtchHisSdmSuffix>) => this.onSaveSuccess(),
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

    trackPlanInfoStepDataHisById(index: number, item: IPlanInfoStepDataHisSdmSuffix) {
        return item.id;
    }
}
