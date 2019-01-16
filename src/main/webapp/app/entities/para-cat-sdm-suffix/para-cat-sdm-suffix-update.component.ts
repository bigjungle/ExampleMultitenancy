import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IParaCatSdmSuffix } from 'app/shared/model/para-cat-sdm-suffix.model';
import { ParaCatSdmSuffixService } from './para-cat-sdm-suffix.service';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix';

@Component({
    selector: 'jhi-para-cat-sdm-suffix-update',
    templateUrl: './para-cat-sdm-suffix-update.component.html'
})
export class ParaCatSdmSuffixUpdateComponent implements OnInit {
    paraCat: IParaCatSdmSuffix;
    isSaving: boolean;

    rmsusers: IRmsUserSdmSuffix[];

    paracats: IParaCatSdmSuffix[];
    validBegin: string;
    validEnd: string;
    insertTime: string;
    updateTime: string;
    verifyTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected paraCatService: ParaCatSdmSuffixService,
        protected rmsUserService: RmsUserSdmSuffixService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ paraCat }) => {
            this.paraCat = paraCat;
            this.validBegin = this.paraCat.validBegin != null ? this.paraCat.validBegin.format(DATE_TIME_FORMAT) : null;
            this.validEnd = this.paraCat.validEnd != null ? this.paraCat.validEnd.format(DATE_TIME_FORMAT) : null;
            this.insertTime = this.paraCat.insertTime != null ? this.paraCat.insertTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.paraCat.updateTime != null ? this.paraCat.updateTime.format(DATE_TIME_FORMAT) : null;
            this.verifyTime = this.paraCat.verifyTime != null ? this.paraCat.verifyTime.format(DATE_TIME_FORMAT) : null;
        });
        this.rmsUserService.query().subscribe(
            (res: HttpResponse<IRmsUserSdmSuffix[]>) => {
                this.rmsusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.paraCatService.query().subscribe(
            (res: HttpResponse<IParaCatSdmSuffix[]>) => {
                this.paracats = res.body;
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
        this.dataUtils.clearInputImage(this.paraCat, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.paraCat.validBegin = this.validBegin != null ? moment(this.validBegin, DATE_TIME_FORMAT) : null;
        this.paraCat.validEnd = this.validEnd != null ? moment(this.validEnd, DATE_TIME_FORMAT) : null;
        this.paraCat.insertTime = this.insertTime != null ? moment(this.insertTime, DATE_TIME_FORMAT) : null;
        this.paraCat.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        this.paraCat.verifyTime = this.verifyTime != null ? moment(this.verifyTime, DATE_TIME_FORMAT) : null;
        if (this.paraCat.id !== undefined) {
            this.subscribeToSaveResponse(this.paraCatService.update(this.paraCat));
        } else {
            this.subscribeToSaveResponse(this.paraCatService.create(this.paraCat));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IParaCatSdmSuffix>>) {
        result.subscribe((res: HttpResponse<IParaCatSdmSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackParaCatById(index: number, item: IParaCatSdmSuffix) {
        return item.id;
    }
}
