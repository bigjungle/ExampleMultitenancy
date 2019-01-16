import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IParaAttrSdmSuffix } from 'app/shared/model/para-attr-sdm-suffix.model';
import { ParaAttrSdmSuffixService } from './para-attr-sdm-suffix.service';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix';

@Component({
    selector: 'jhi-para-attr-sdm-suffix-update',
    templateUrl: './para-attr-sdm-suffix-update.component.html'
})
export class ParaAttrSdmSuffixUpdateComponent implements OnInit {
    paraAttr: IParaAttrSdmSuffix;
    isSaving: boolean;

    rmsusers: IRmsUserSdmSuffix[];

    paraattrs: IParaAttrSdmSuffix[];
    validBegin: string;
    validEnd: string;
    insertTime: string;
    updateTime: string;
    verifyTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected paraAttrService: ParaAttrSdmSuffixService,
        protected rmsUserService: RmsUserSdmSuffixService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ paraAttr }) => {
            this.paraAttr = paraAttr;
            this.validBegin = this.paraAttr.validBegin != null ? this.paraAttr.validBegin.format(DATE_TIME_FORMAT) : null;
            this.validEnd = this.paraAttr.validEnd != null ? this.paraAttr.validEnd.format(DATE_TIME_FORMAT) : null;
            this.insertTime = this.paraAttr.insertTime != null ? this.paraAttr.insertTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.paraAttr.updateTime != null ? this.paraAttr.updateTime.format(DATE_TIME_FORMAT) : null;
            this.verifyTime = this.paraAttr.verifyTime != null ? this.paraAttr.verifyTime.format(DATE_TIME_FORMAT) : null;
        });
        this.rmsUserService.query().subscribe(
            (res: HttpResponse<IRmsUserSdmSuffix[]>) => {
                this.rmsusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.paraAttrService.query().subscribe(
            (res: HttpResponse<IParaAttrSdmSuffix[]>) => {
                this.paraattrs = res.body;
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
        this.dataUtils.clearInputImage(this.paraAttr, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.paraAttr.validBegin = this.validBegin != null ? moment(this.validBegin, DATE_TIME_FORMAT) : null;
        this.paraAttr.validEnd = this.validEnd != null ? moment(this.validEnd, DATE_TIME_FORMAT) : null;
        this.paraAttr.insertTime = this.insertTime != null ? moment(this.insertTime, DATE_TIME_FORMAT) : null;
        this.paraAttr.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        this.paraAttr.verifyTime = this.verifyTime != null ? moment(this.verifyTime, DATE_TIME_FORMAT) : null;
        if (this.paraAttr.id !== undefined) {
            this.subscribeToSaveResponse(this.paraAttrService.update(this.paraAttr));
        } else {
            this.subscribeToSaveResponse(this.paraAttrService.create(this.paraAttr));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IParaAttrSdmSuffix>>) {
        result.subscribe((res: HttpResponse<IParaAttrSdmSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackParaAttrById(index: number, item: IParaAttrSdmSuffix) {
        return item.id;
    }
}
