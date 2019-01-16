import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IParaStateSdmSuffix } from 'app/shared/model/para-state-sdm-suffix.model';
import { ParaStateSdmSuffixService } from './para-state-sdm-suffix.service';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix';

@Component({
    selector: 'jhi-para-state-sdm-suffix-update',
    templateUrl: './para-state-sdm-suffix-update.component.html'
})
export class ParaStateSdmSuffixUpdateComponent implements OnInit {
    paraState: IParaStateSdmSuffix;
    isSaving: boolean;

    rmsusers: IRmsUserSdmSuffix[];

    parastates: IParaStateSdmSuffix[];
    validBegin: string;
    validEnd: string;
    insertTime: string;
    updateTime: string;
    verifyTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected paraStateService: ParaStateSdmSuffixService,
        protected rmsUserService: RmsUserSdmSuffixService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ paraState }) => {
            this.paraState = paraState;
            this.validBegin = this.paraState.validBegin != null ? this.paraState.validBegin.format(DATE_TIME_FORMAT) : null;
            this.validEnd = this.paraState.validEnd != null ? this.paraState.validEnd.format(DATE_TIME_FORMAT) : null;
            this.insertTime = this.paraState.insertTime != null ? this.paraState.insertTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.paraState.updateTime != null ? this.paraState.updateTime.format(DATE_TIME_FORMAT) : null;
            this.verifyTime = this.paraState.verifyTime != null ? this.paraState.verifyTime.format(DATE_TIME_FORMAT) : null;
        });
        this.rmsUserService.query().subscribe(
            (res: HttpResponse<IRmsUserSdmSuffix[]>) => {
                this.rmsusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.paraStateService.query().subscribe(
            (res: HttpResponse<IParaStateSdmSuffix[]>) => {
                this.parastates = res.body;
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
        this.dataUtils.clearInputImage(this.paraState, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.paraState.validBegin = this.validBegin != null ? moment(this.validBegin, DATE_TIME_FORMAT) : null;
        this.paraState.validEnd = this.validEnd != null ? moment(this.validEnd, DATE_TIME_FORMAT) : null;
        this.paraState.insertTime = this.insertTime != null ? moment(this.insertTime, DATE_TIME_FORMAT) : null;
        this.paraState.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        this.paraState.verifyTime = this.verifyTime != null ? moment(this.verifyTime, DATE_TIME_FORMAT) : null;
        if (this.paraState.id !== undefined) {
            this.subscribeToSaveResponse(this.paraStateService.update(this.paraState));
        } else {
            this.subscribeToSaveResponse(this.paraStateService.create(this.paraState));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IParaStateSdmSuffix>>) {
        result.subscribe((res: HttpResponse<IParaStateSdmSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackParaStateById(index: number, item: IParaStateSdmSuffix) {
        return item.id;
    }
}
