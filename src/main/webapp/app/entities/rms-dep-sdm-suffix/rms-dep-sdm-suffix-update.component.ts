import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IRmsDepSdmSuffix } from 'app/shared/model/rms-dep-sdm-suffix.model';
import { RmsDepSdmSuffixService } from './rms-dep-sdm-suffix.service';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix';

@Component({
    selector: 'jhi-rms-dep-sdm-suffix-update',
    templateUrl: './rms-dep-sdm-suffix-update.component.html'
})
export class RmsDepSdmSuffixUpdateComponent implements OnInit {
    rmsDep: IRmsDepSdmSuffix;
    isSaving: boolean;

    rmsusers: IRmsUserSdmSuffix[];

    rmsdeps: IRmsDepSdmSuffix[];
    validBegin: string;
    validEnd: string;
    insertTime: string;
    updateTime: string;
    verifyTime: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected rmsDepService: RmsDepSdmSuffixService,
        protected rmsUserService: RmsUserSdmSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ rmsDep }) => {
            this.rmsDep = rmsDep;
            this.validBegin = this.rmsDep.validBegin != null ? this.rmsDep.validBegin.format(DATE_TIME_FORMAT) : null;
            this.validEnd = this.rmsDep.validEnd != null ? this.rmsDep.validEnd.format(DATE_TIME_FORMAT) : null;
            this.insertTime = this.rmsDep.insertTime != null ? this.rmsDep.insertTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.rmsDep.updateTime != null ? this.rmsDep.updateTime.format(DATE_TIME_FORMAT) : null;
            this.verifyTime = this.rmsDep.verifyTime != null ? this.rmsDep.verifyTime.format(DATE_TIME_FORMAT) : null;
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
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.rmsDep.validBegin = this.validBegin != null ? moment(this.validBegin, DATE_TIME_FORMAT) : null;
        this.rmsDep.validEnd = this.validEnd != null ? moment(this.validEnd, DATE_TIME_FORMAT) : null;
        this.rmsDep.insertTime = this.insertTime != null ? moment(this.insertTime, DATE_TIME_FORMAT) : null;
        this.rmsDep.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        this.rmsDep.verifyTime = this.verifyTime != null ? moment(this.verifyTime, DATE_TIME_FORMAT) : null;
        if (this.rmsDep.id !== undefined) {
            this.subscribeToSaveResponse(this.rmsDepService.update(this.rmsDep));
        } else {
            this.subscribeToSaveResponse(this.rmsDepService.create(this.rmsDep));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRmsDepSdmSuffix>>) {
        result.subscribe((res: HttpResponse<IRmsDepSdmSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
