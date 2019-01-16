import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IParaClassSdmSuffix } from 'app/shared/model/para-class-sdm-suffix.model';
import { ParaClassSdmSuffixService } from './para-class-sdm-suffix.service';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix';

@Component({
    selector: 'jhi-para-class-sdm-suffix-update',
    templateUrl: './para-class-sdm-suffix-update.component.html'
})
export class ParaClassSdmSuffixUpdateComponent implements OnInit {
    paraClass: IParaClassSdmSuffix;
    isSaving: boolean;

    rmsusers: IRmsUserSdmSuffix[];

    paraclasses: IParaClassSdmSuffix[];
    validBegin: string;
    validEnd: string;
    insertTime: string;
    updateTime: string;
    verifyTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected paraClassService: ParaClassSdmSuffixService,
        protected rmsUserService: RmsUserSdmSuffixService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ paraClass }) => {
            this.paraClass = paraClass;
            this.validBegin = this.paraClass.validBegin != null ? this.paraClass.validBegin.format(DATE_TIME_FORMAT) : null;
            this.validEnd = this.paraClass.validEnd != null ? this.paraClass.validEnd.format(DATE_TIME_FORMAT) : null;
            this.insertTime = this.paraClass.insertTime != null ? this.paraClass.insertTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.paraClass.updateTime != null ? this.paraClass.updateTime.format(DATE_TIME_FORMAT) : null;
            this.verifyTime = this.paraClass.verifyTime != null ? this.paraClass.verifyTime.format(DATE_TIME_FORMAT) : null;
        });
        this.rmsUserService.query().subscribe(
            (res: HttpResponse<IRmsUserSdmSuffix[]>) => {
                this.rmsusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.paraClassService.query().subscribe(
            (res: HttpResponse<IParaClassSdmSuffix[]>) => {
                this.paraclasses = res.body;
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
        this.dataUtils.clearInputImage(this.paraClass, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.paraClass.validBegin = this.validBegin != null ? moment(this.validBegin, DATE_TIME_FORMAT) : null;
        this.paraClass.validEnd = this.validEnd != null ? moment(this.validEnd, DATE_TIME_FORMAT) : null;
        this.paraClass.insertTime = this.insertTime != null ? moment(this.insertTime, DATE_TIME_FORMAT) : null;
        this.paraClass.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        this.paraClass.verifyTime = this.verifyTime != null ? moment(this.verifyTime, DATE_TIME_FORMAT) : null;
        if (this.paraClass.id !== undefined) {
            this.subscribeToSaveResponse(this.paraClassService.update(this.paraClass));
        } else {
            this.subscribeToSaveResponse(this.paraClassService.create(this.paraClass));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IParaClassSdmSuffix>>) {
        result.subscribe((res: HttpResponse<IParaClassSdmSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackParaClassById(index: number, item: IParaClassSdmSuffix) {
        return item.id;
    }
}
