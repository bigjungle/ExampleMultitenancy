import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IParaSourceSdmSuffix } from 'app/shared/model/para-source-sdm-suffix.model';
import { ParaSourceSdmSuffixService } from './para-source-sdm-suffix.service';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix';

@Component({
    selector: 'jhi-para-source-sdm-suffix-update',
    templateUrl: './para-source-sdm-suffix-update.component.html'
})
export class ParaSourceSdmSuffixUpdateComponent implements OnInit {
    paraSource: IParaSourceSdmSuffix;
    isSaving: boolean;

    rmsusers: IRmsUserSdmSuffix[];

    parasources: IParaSourceSdmSuffix[];
    validBegin: string;
    validEnd: string;
    insertTime: string;
    updateTime: string;
    verifyTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected paraSourceService: ParaSourceSdmSuffixService,
        protected rmsUserService: RmsUserSdmSuffixService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ paraSource }) => {
            this.paraSource = paraSource;
            this.validBegin = this.paraSource.validBegin != null ? this.paraSource.validBegin.format(DATE_TIME_FORMAT) : null;
            this.validEnd = this.paraSource.validEnd != null ? this.paraSource.validEnd.format(DATE_TIME_FORMAT) : null;
            this.insertTime = this.paraSource.insertTime != null ? this.paraSource.insertTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.paraSource.updateTime != null ? this.paraSource.updateTime.format(DATE_TIME_FORMAT) : null;
            this.verifyTime = this.paraSource.verifyTime != null ? this.paraSource.verifyTime.format(DATE_TIME_FORMAT) : null;
        });
        this.rmsUserService.query().subscribe(
            (res: HttpResponse<IRmsUserSdmSuffix[]>) => {
                this.rmsusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.paraSourceService.query().subscribe(
            (res: HttpResponse<IParaSourceSdmSuffix[]>) => {
                this.parasources = res.body;
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
        this.dataUtils.clearInputImage(this.paraSource, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.paraSource.validBegin = this.validBegin != null ? moment(this.validBegin, DATE_TIME_FORMAT) : null;
        this.paraSource.validEnd = this.validEnd != null ? moment(this.validEnd, DATE_TIME_FORMAT) : null;
        this.paraSource.insertTime = this.insertTime != null ? moment(this.insertTime, DATE_TIME_FORMAT) : null;
        this.paraSource.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        this.paraSource.verifyTime = this.verifyTime != null ? moment(this.verifyTime, DATE_TIME_FORMAT) : null;
        if (this.paraSource.id !== undefined) {
            this.subscribeToSaveResponse(this.paraSourceService.update(this.paraSource));
        } else {
            this.subscribeToSaveResponse(this.paraSourceService.create(this.paraSource));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IParaSourceSdmSuffix>>) {
        result.subscribe((res: HttpResponse<IParaSourceSdmSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackParaSourceById(index: number, item: IParaSourceSdmSuffix) {
        return item.id;
    }
}
