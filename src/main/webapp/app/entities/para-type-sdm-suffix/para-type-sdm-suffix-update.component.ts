import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IParaTypeSdmSuffix } from 'app/shared/model/para-type-sdm-suffix.model';
import { ParaTypeSdmSuffixService } from './para-type-sdm-suffix.service';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix';

@Component({
    selector: 'jhi-para-type-sdm-suffix-update',
    templateUrl: './para-type-sdm-suffix-update.component.html'
})
export class ParaTypeSdmSuffixUpdateComponent implements OnInit {
    paraType: IParaTypeSdmSuffix;
    isSaving: boolean;

    rmsusers: IRmsUserSdmSuffix[];

    paratypes: IParaTypeSdmSuffix[];
    validBegin: string;
    validEnd: string;
    insertTime: string;
    updateTime: string;
    verifyTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected paraTypeService: ParaTypeSdmSuffixService,
        protected rmsUserService: RmsUserSdmSuffixService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ paraType }) => {
            this.paraType = paraType;
            this.validBegin = this.paraType.validBegin != null ? this.paraType.validBegin.format(DATE_TIME_FORMAT) : null;
            this.validEnd = this.paraType.validEnd != null ? this.paraType.validEnd.format(DATE_TIME_FORMAT) : null;
            this.insertTime = this.paraType.insertTime != null ? this.paraType.insertTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.paraType.updateTime != null ? this.paraType.updateTime.format(DATE_TIME_FORMAT) : null;
            this.verifyTime = this.paraType.verifyTime != null ? this.paraType.verifyTime.format(DATE_TIME_FORMAT) : null;
        });
        this.rmsUserService.query().subscribe(
            (res: HttpResponse<IRmsUserSdmSuffix[]>) => {
                this.rmsusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.paraTypeService.query().subscribe(
            (res: HttpResponse<IParaTypeSdmSuffix[]>) => {
                this.paratypes = res.body;
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
        this.dataUtils.clearInputImage(this.paraType, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.paraType.validBegin = this.validBegin != null ? moment(this.validBegin, DATE_TIME_FORMAT) : null;
        this.paraType.validEnd = this.validEnd != null ? moment(this.validEnd, DATE_TIME_FORMAT) : null;
        this.paraType.insertTime = this.insertTime != null ? moment(this.insertTime, DATE_TIME_FORMAT) : null;
        this.paraType.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        this.paraType.verifyTime = this.verifyTime != null ? moment(this.verifyTime, DATE_TIME_FORMAT) : null;
        if (this.paraType.id !== undefined) {
            this.subscribeToSaveResponse(this.paraTypeService.update(this.paraType));
        } else {
            this.subscribeToSaveResponse(this.paraTypeService.create(this.paraType));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IParaTypeSdmSuffix>>) {
        result.subscribe((res: HttpResponse<IParaTypeSdmSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackParaTypeById(index: number, item: IParaTypeSdmSuffix) {
        return item.id;
    }
}
