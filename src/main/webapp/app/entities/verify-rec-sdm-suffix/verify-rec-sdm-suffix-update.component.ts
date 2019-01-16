import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IVerifyRecSdmSuffix } from 'app/shared/model/verify-rec-sdm-suffix.model';
import { VerifyRecSdmSuffixService } from './verify-rec-sdm-suffix.service';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix';

@Component({
    selector: 'jhi-verify-rec-sdm-suffix-update',
    templateUrl: './verify-rec-sdm-suffix-update.component.html'
})
export class VerifyRecSdmSuffixUpdateComponent implements OnInit {
    verifyRec: IVerifyRecSdmSuffix;
    isSaving: boolean;

    rmsusers: IRmsUserSdmSuffix[];
    insertTime: string;
    updateTime: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected verifyRecService: VerifyRecSdmSuffixService,
        protected rmsUserService: RmsUserSdmSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ verifyRec }) => {
            this.verifyRec = verifyRec;
            this.insertTime = this.verifyRec.insertTime != null ? this.verifyRec.insertTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.verifyRec.updateTime != null ? this.verifyRec.updateTime.format(DATE_TIME_FORMAT) : null;
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
        this.verifyRec.insertTime = this.insertTime != null ? moment(this.insertTime, DATE_TIME_FORMAT) : null;
        this.verifyRec.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.verifyRec.id !== undefined) {
            this.subscribeToSaveResponse(this.verifyRecService.update(this.verifyRec));
        } else {
            this.subscribeToSaveResponse(this.verifyRecService.create(this.verifyRec));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IVerifyRecSdmSuffix>>) {
        result.subscribe((res: HttpResponse<IVerifyRecSdmSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
