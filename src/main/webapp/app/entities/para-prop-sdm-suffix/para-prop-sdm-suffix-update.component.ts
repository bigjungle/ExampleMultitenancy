import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IParaPropSdmSuffix } from 'app/shared/model/para-prop-sdm-suffix.model';
import { ParaPropSdmSuffixService } from './para-prop-sdm-suffix.service';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix';

@Component({
    selector: 'jhi-para-prop-sdm-suffix-update',
    templateUrl: './para-prop-sdm-suffix-update.component.html'
})
export class ParaPropSdmSuffixUpdateComponent implements OnInit {
    paraProp: IParaPropSdmSuffix;
    isSaving: boolean;

    rmsusers: IRmsUserSdmSuffix[];

    paraprops: IParaPropSdmSuffix[];
    validBegin: string;
    validEnd: string;
    insertTime: string;
    updateTime: string;
    verifyTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected paraPropService: ParaPropSdmSuffixService,
        protected rmsUserService: RmsUserSdmSuffixService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ paraProp }) => {
            this.paraProp = paraProp;
            this.validBegin = this.paraProp.validBegin != null ? this.paraProp.validBegin.format(DATE_TIME_FORMAT) : null;
            this.validEnd = this.paraProp.validEnd != null ? this.paraProp.validEnd.format(DATE_TIME_FORMAT) : null;
            this.insertTime = this.paraProp.insertTime != null ? this.paraProp.insertTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.paraProp.updateTime != null ? this.paraProp.updateTime.format(DATE_TIME_FORMAT) : null;
            this.verifyTime = this.paraProp.verifyTime != null ? this.paraProp.verifyTime.format(DATE_TIME_FORMAT) : null;
        });
        this.rmsUserService.query().subscribe(
            (res: HttpResponse<IRmsUserSdmSuffix[]>) => {
                this.rmsusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.paraPropService.query().subscribe(
            (res: HttpResponse<IParaPropSdmSuffix[]>) => {
                this.paraprops = res.body;
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
        this.dataUtils.clearInputImage(this.paraProp, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.paraProp.validBegin = this.validBegin != null ? moment(this.validBegin, DATE_TIME_FORMAT) : null;
        this.paraProp.validEnd = this.validEnd != null ? moment(this.validEnd, DATE_TIME_FORMAT) : null;
        this.paraProp.insertTime = this.insertTime != null ? moment(this.insertTime, DATE_TIME_FORMAT) : null;
        this.paraProp.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        this.paraProp.verifyTime = this.verifyTime != null ? moment(this.verifyTime, DATE_TIME_FORMAT) : null;
        if (this.paraProp.id !== undefined) {
            this.subscribeToSaveResponse(this.paraPropService.update(this.paraProp));
        } else {
            this.subscribeToSaveResponse(this.paraPropService.create(this.paraProp));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IParaPropSdmSuffix>>) {
        result.subscribe((res: HttpResponse<IParaPropSdmSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackParaPropById(index: number, item: IParaPropSdmSuffix) {
        return item.id;
    }
}
