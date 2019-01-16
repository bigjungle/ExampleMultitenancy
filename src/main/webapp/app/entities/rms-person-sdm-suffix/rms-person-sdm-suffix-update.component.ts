import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IRmsPersonSdmSuffix } from 'app/shared/model/rms-person-sdm-suffix.model';
import { RmsPersonSdmSuffixService } from './rms-person-sdm-suffix.service';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix';

@Component({
    selector: 'jhi-rms-person-sdm-suffix-update',
    templateUrl: './rms-person-sdm-suffix-update.component.html'
})
export class RmsPersonSdmSuffixUpdateComponent implements OnInit {
    rmsPerson: IRmsPersonSdmSuffix;
    isSaving: boolean;

    rmsusers: IRmsUserSdmSuffix[];
    birthday: string;
    validBegin: string;
    validEnd: string;
    insertTime: string;
    updateTime: string;
    verifyTime: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected rmsPersonService: RmsPersonSdmSuffixService,
        protected rmsUserService: RmsUserSdmSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ rmsPerson }) => {
            this.rmsPerson = rmsPerson;
            this.birthday = this.rmsPerson.birthday != null ? this.rmsPerson.birthday.format(DATE_TIME_FORMAT) : null;
            this.validBegin = this.rmsPerson.validBegin != null ? this.rmsPerson.validBegin.format(DATE_TIME_FORMAT) : null;
            this.validEnd = this.rmsPerson.validEnd != null ? this.rmsPerson.validEnd.format(DATE_TIME_FORMAT) : null;
            this.insertTime = this.rmsPerson.insertTime != null ? this.rmsPerson.insertTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.rmsPerson.updateTime != null ? this.rmsPerson.updateTime.format(DATE_TIME_FORMAT) : null;
            this.verifyTime = this.rmsPerson.verifyTime != null ? this.rmsPerson.verifyTime.format(DATE_TIME_FORMAT) : null;
        });
        this.rmsUserService.query().subscribe(
            (res: HttpResponse<IRmsUserSdmSuffix[]>) => {
                this.rmsusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.rmsPerson.birthday = this.birthday != null ? moment(this.birthday, DATE_TIME_FORMAT) : null;
        this.rmsPerson.validBegin = this.validBegin != null ? moment(this.validBegin, DATE_TIME_FORMAT) : null;
        this.rmsPerson.validEnd = this.validEnd != null ? moment(this.validEnd, DATE_TIME_FORMAT) : null;
        this.rmsPerson.insertTime = this.insertTime != null ? moment(this.insertTime, DATE_TIME_FORMAT) : null;
        this.rmsPerson.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        this.rmsPerson.verifyTime = this.verifyTime != null ? moment(this.verifyTime, DATE_TIME_FORMAT) : null;
        if (this.rmsPerson.id !== undefined) {
            this.subscribeToSaveResponse(this.rmsPersonService.update(this.rmsPerson));
        } else {
            this.subscribeToSaveResponse(this.rmsPersonService.create(this.rmsPerson));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRmsPersonSdmSuffix>>) {
        result.subscribe((res: HttpResponse<IRmsPersonSdmSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
