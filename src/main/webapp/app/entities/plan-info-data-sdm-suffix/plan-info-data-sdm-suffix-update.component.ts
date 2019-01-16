import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IPlanInfoDataSdmSuffix } from 'app/shared/model/plan-info-data-sdm-suffix.model';
import { PlanInfoDataSdmSuffixService } from './plan-info-data-sdm-suffix.service';
import { IVerifyRecSdmSuffix } from 'app/shared/model/verify-rec-sdm-suffix.model';
import { VerifyRecSdmSuffixService } from 'app/entities/verify-rec-sdm-suffix';
import { IParaTypeSdmSuffix } from 'app/shared/model/para-type-sdm-suffix.model';
import { ParaTypeSdmSuffixService } from 'app/entities/para-type-sdm-suffix';
import { IParaClassSdmSuffix } from 'app/shared/model/para-class-sdm-suffix.model';
import { ParaClassSdmSuffixService } from 'app/entities/para-class-sdm-suffix';
import { IParaCatSdmSuffix } from 'app/shared/model/para-cat-sdm-suffix.model';
import { ParaCatSdmSuffixService } from 'app/entities/para-cat-sdm-suffix';
import { IParaStateSdmSuffix } from 'app/shared/model/para-state-sdm-suffix.model';
import { ParaStateSdmSuffixService } from 'app/entities/para-state-sdm-suffix';
import { IParaSourceSdmSuffix } from 'app/shared/model/para-source-sdm-suffix.model';
import { ParaSourceSdmSuffixService } from 'app/entities/para-source-sdm-suffix';
import { IParaAttrSdmSuffix } from 'app/shared/model/para-attr-sdm-suffix.model';
import { ParaAttrSdmSuffixService } from 'app/entities/para-attr-sdm-suffix';
import { IParaPropSdmSuffix } from 'app/shared/model/para-prop-sdm-suffix.model';
import { ParaPropSdmSuffixService } from 'app/entities/para-prop-sdm-suffix';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix';
import { IRmsDepSdmSuffix } from 'app/shared/model/rms-dep-sdm-suffix.model';
import { RmsDepSdmSuffixService } from 'app/entities/rms-dep-sdm-suffix';
import { IPlanInfoSdmSuffix } from 'app/shared/model/plan-info-sdm-suffix.model';
import { PlanInfoSdmSuffixService } from 'app/entities/plan-info-sdm-suffix';

@Component({
    selector: 'jhi-plan-info-data-sdm-suffix-update',
    templateUrl: './plan-info-data-sdm-suffix-update.component.html'
})
export class PlanInfoDataSdmSuffixUpdateComponent implements OnInit {
    planInfoData: IPlanInfoDataSdmSuffix;
    isSaving: boolean;

    verifyrecs: IVerifyRecSdmSuffix[];

    paratypes: IParaTypeSdmSuffix[];

    paraclasses: IParaClassSdmSuffix[];

    paracats: IParaCatSdmSuffix[];

    parastates: IParaStateSdmSuffix[];

    parasources: IParaSourceSdmSuffix[];

    paraattrs: IParaAttrSdmSuffix[];

    paraprops: IParaPropSdmSuffix[];

    rmsusers: IRmsUserSdmSuffix[];

    rmsdeps: IRmsDepSdmSuffix[];

    planinfodata: IPlanInfoDataSdmSuffix[];

    planinfos: IPlanInfoSdmSuffix[];
    validBegin: string;
    validEnd: string;
    insertTime: string;
    updateTime: string;
    verifyTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected planInfoDataService: PlanInfoDataSdmSuffixService,
        protected verifyRecService: VerifyRecSdmSuffixService,
        protected paraTypeService: ParaTypeSdmSuffixService,
        protected paraClassService: ParaClassSdmSuffixService,
        protected paraCatService: ParaCatSdmSuffixService,
        protected paraStateService: ParaStateSdmSuffixService,
        protected paraSourceService: ParaSourceSdmSuffixService,
        protected paraAttrService: ParaAttrSdmSuffixService,
        protected paraPropService: ParaPropSdmSuffixService,
        protected rmsUserService: RmsUserSdmSuffixService,
        protected rmsDepService: RmsDepSdmSuffixService,
        protected planInfoService: PlanInfoSdmSuffixService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ planInfoData }) => {
            this.planInfoData = planInfoData;
            this.validBegin = this.planInfoData.validBegin != null ? this.planInfoData.validBegin.format(DATE_TIME_FORMAT) : null;
            this.validEnd = this.planInfoData.validEnd != null ? this.planInfoData.validEnd.format(DATE_TIME_FORMAT) : null;
            this.insertTime = this.planInfoData.insertTime != null ? this.planInfoData.insertTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.planInfoData.updateTime != null ? this.planInfoData.updateTime.format(DATE_TIME_FORMAT) : null;
            this.verifyTime = this.planInfoData.verifyTime != null ? this.planInfoData.verifyTime.format(DATE_TIME_FORMAT) : null;
        });
        this.verifyRecService.query().subscribe(
            (res: HttpResponse<IVerifyRecSdmSuffix[]>) => {
                this.verifyrecs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.paraTypeService.query().subscribe(
            (res: HttpResponse<IParaTypeSdmSuffix[]>) => {
                this.paratypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.paraClassService.query().subscribe(
            (res: HttpResponse<IParaClassSdmSuffix[]>) => {
                this.paraclasses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.paraCatService.query().subscribe(
            (res: HttpResponse<IParaCatSdmSuffix[]>) => {
                this.paracats = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.paraStateService.query().subscribe(
            (res: HttpResponse<IParaStateSdmSuffix[]>) => {
                this.parastates = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.paraSourceService.query().subscribe(
            (res: HttpResponse<IParaSourceSdmSuffix[]>) => {
                this.parasources = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.paraAttrService.query().subscribe(
            (res: HttpResponse<IParaAttrSdmSuffix[]>) => {
                this.paraattrs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.paraPropService.query().subscribe(
            (res: HttpResponse<IParaPropSdmSuffix[]>) => {
                this.paraprops = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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
        this.planInfoDataService.query().subscribe(
            (res: HttpResponse<IPlanInfoDataSdmSuffix[]>) => {
                this.planinfodata = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.planInfoService.query().subscribe(
            (res: HttpResponse<IPlanInfoSdmSuffix[]>) => {
                this.planinfos = res.body;
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
        this.dataUtils.clearInputImage(this.planInfoData, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.planInfoData.validBegin = this.validBegin != null ? moment(this.validBegin, DATE_TIME_FORMAT) : null;
        this.planInfoData.validEnd = this.validEnd != null ? moment(this.validEnd, DATE_TIME_FORMAT) : null;
        this.planInfoData.insertTime = this.insertTime != null ? moment(this.insertTime, DATE_TIME_FORMAT) : null;
        this.planInfoData.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        this.planInfoData.verifyTime = this.verifyTime != null ? moment(this.verifyTime, DATE_TIME_FORMAT) : null;
        if (this.planInfoData.id !== undefined) {
            this.subscribeToSaveResponse(this.planInfoDataService.update(this.planInfoData));
        } else {
            this.subscribeToSaveResponse(this.planInfoDataService.create(this.planInfoData));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlanInfoDataSdmSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPlanInfoDataSdmSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
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

    trackVerifyRecById(index: number, item: IVerifyRecSdmSuffix) {
        return item.id;
    }

    trackParaTypeById(index: number, item: IParaTypeSdmSuffix) {
        return item.id;
    }

    trackParaClassById(index: number, item: IParaClassSdmSuffix) {
        return item.id;
    }

    trackParaCatById(index: number, item: IParaCatSdmSuffix) {
        return item.id;
    }

    trackParaStateById(index: number, item: IParaStateSdmSuffix) {
        return item.id;
    }

    trackParaSourceById(index: number, item: IParaSourceSdmSuffix) {
        return item.id;
    }

    trackParaAttrById(index: number, item: IParaAttrSdmSuffix) {
        return item.id;
    }

    trackParaPropById(index: number, item: IParaPropSdmSuffix) {
        return item.id;
    }

    trackRmsUserById(index: number, item: IRmsUserSdmSuffix) {
        return item.id;
    }

    trackRmsDepById(index: number, item: IRmsDepSdmSuffix) {
        return item.id;
    }

    trackPlanInfoDataById(index: number, item: IPlanInfoDataSdmSuffix) {
        return item.id;
    }

    trackPlanInfoById(index: number, item: IPlanInfoSdmSuffix) {
        return item.id;
    }
}
