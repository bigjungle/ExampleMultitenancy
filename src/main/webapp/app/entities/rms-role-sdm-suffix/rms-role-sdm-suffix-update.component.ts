import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IRmsRoleSdmSuffix } from 'app/shared/model/rms-role-sdm-suffix.model';
import { RmsRoleSdmSuffixService } from './rms-role-sdm-suffix.service';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix';
import { IRmsNodeSdmSuffix } from 'app/shared/model/rms-node-sdm-suffix.model';
import { RmsNodeSdmSuffixService } from 'app/entities/rms-node-sdm-suffix';

@Component({
    selector: 'jhi-rms-role-sdm-suffix-update',
    templateUrl: './rms-role-sdm-suffix-update.component.html'
})
export class RmsRoleSdmSuffixUpdateComponent implements OnInit {
    rmsRole: IRmsRoleSdmSuffix;
    isSaving: boolean;

    rmsusers: IRmsUserSdmSuffix[];

    rmsroles: IRmsRoleSdmSuffix[];

    rmsnodes: IRmsNodeSdmSuffix[];
    validBegin: string;
    validEnd: string;
    insertTime: string;
    updateTime: string;
    verifyTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected rmsRoleService: RmsRoleSdmSuffixService,
        protected rmsUserService: RmsUserSdmSuffixService,
        protected rmsNodeService: RmsNodeSdmSuffixService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ rmsRole }) => {
            this.rmsRole = rmsRole;
            this.validBegin = this.rmsRole.validBegin != null ? this.rmsRole.validBegin.format(DATE_TIME_FORMAT) : null;
            this.validEnd = this.rmsRole.validEnd != null ? this.rmsRole.validEnd.format(DATE_TIME_FORMAT) : null;
            this.insertTime = this.rmsRole.insertTime != null ? this.rmsRole.insertTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.rmsRole.updateTime != null ? this.rmsRole.updateTime.format(DATE_TIME_FORMAT) : null;
            this.verifyTime = this.rmsRole.verifyTime != null ? this.rmsRole.verifyTime.format(DATE_TIME_FORMAT) : null;
        });
        this.rmsUserService.query().subscribe(
            (res: HttpResponse<IRmsUserSdmSuffix[]>) => {
                this.rmsusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.rmsRoleService.query().subscribe(
            (res: HttpResponse<IRmsRoleSdmSuffix[]>) => {
                this.rmsroles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.rmsNodeService.query().subscribe(
            (res: HttpResponse<IRmsNodeSdmSuffix[]>) => {
                this.rmsnodes = res.body;
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
        this.dataUtils.clearInputImage(this.rmsRole, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.rmsRole.validBegin = this.validBegin != null ? moment(this.validBegin, DATE_TIME_FORMAT) : null;
        this.rmsRole.validEnd = this.validEnd != null ? moment(this.validEnd, DATE_TIME_FORMAT) : null;
        this.rmsRole.insertTime = this.insertTime != null ? moment(this.insertTime, DATE_TIME_FORMAT) : null;
        this.rmsRole.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        this.rmsRole.verifyTime = this.verifyTime != null ? moment(this.verifyTime, DATE_TIME_FORMAT) : null;
        if (this.rmsRole.id !== undefined) {
            this.subscribeToSaveResponse(this.rmsRoleService.update(this.rmsRole));
        } else {
            this.subscribeToSaveResponse(this.rmsRoleService.create(this.rmsRole));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRmsRoleSdmSuffix>>) {
        result.subscribe((res: HttpResponse<IRmsRoleSdmSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackRmsRoleById(index: number, item: IRmsRoleSdmSuffix) {
        return item.id;
    }

    trackRmsNodeById(index: number, item: IRmsNodeSdmSuffix) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
