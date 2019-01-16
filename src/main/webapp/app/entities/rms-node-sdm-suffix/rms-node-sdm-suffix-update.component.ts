import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IRmsNodeSdmSuffix } from 'app/shared/model/rms-node-sdm-suffix.model';
import { RmsNodeSdmSuffixService } from './rms-node-sdm-suffix.service';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix';
import { IRmsRoleSdmSuffix } from 'app/shared/model/rms-role-sdm-suffix.model';
import { RmsRoleSdmSuffixService } from 'app/entities/rms-role-sdm-suffix';

@Component({
    selector: 'jhi-rms-node-sdm-suffix-update',
    templateUrl: './rms-node-sdm-suffix-update.component.html'
})
export class RmsNodeSdmSuffixUpdateComponent implements OnInit {
    rmsNode: IRmsNodeSdmSuffix;
    isSaving: boolean;

    rmsusers: IRmsUserSdmSuffix[];

    rmsnodes: IRmsNodeSdmSuffix[];

    rmsroles: IRmsRoleSdmSuffix[];
    validBegin: string;
    validEnd: string;
    insertTime: string;
    updateTime: string;
    verifyTime: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected rmsNodeService: RmsNodeSdmSuffixService,
        protected rmsUserService: RmsUserSdmSuffixService,
        protected rmsRoleService: RmsRoleSdmSuffixService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ rmsNode }) => {
            this.rmsNode = rmsNode;
            this.validBegin = this.rmsNode.validBegin != null ? this.rmsNode.validBegin.format(DATE_TIME_FORMAT) : null;
            this.validEnd = this.rmsNode.validEnd != null ? this.rmsNode.validEnd.format(DATE_TIME_FORMAT) : null;
            this.insertTime = this.rmsNode.insertTime != null ? this.rmsNode.insertTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.rmsNode.updateTime != null ? this.rmsNode.updateTime.format(DATE_TIME_FORMAT) : null;
            this.verifyTime = this.rmsNode.verifyTime != null ? this.rmsNode.verifyTime.format(DATE_TIME_FORMAT) : null;
        });
        this.rmsUserService.query().subscribe(
            (res: HttpResponse<IRmsUserSdmSuffix[]>) => {
                this.rmsusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.rmsNodeService.query().subscribe(
            (res: HttpResponse<IRmsNodeSdmSuffix[]>) => {
                this.rmsnodes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.rmsRoleService.query().subscribe(
            (res: HttpResponse<IRmsRoleSdmSuffix[]>) => {
                this.rmsroles = res.body;
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
        this.dataUtils.clearInputImage(this.rmsNode, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.rmsNode.validBegin = this.validBegin != null ? moment(this.validBegin, DATE_TIME_FORMAT) : null;
        this.rmsNode.validEnd = this.validEnd != null ? moment(this.validEnd, DATE_TIME_FORMAT) : null;
        this.rmsNode.insertTime = this.insertTime != null ? moment(this.insertTime, DATE_TIME_FORMAT) : null;
        this.rmsNode.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        this.rmsNode.verifyTime = this.verifyTime != null ? moment(this.verifyTime, DATE_TIME_FORMAT) : null;
        if (this.rmsNode.id !== undefined) {
            this.subscribeToSaveResponse(this.rmsNodeService.update(this.rmsNode));
        } else {
            this.subscribeToSaveResponse(this.rmsNodeService.create(this.rmsNode));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRmsNodeSdmSuffix>>) {
        result.subscribe((res: HttpResponse<IRmsNodeSdmSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackRmsNodeById(index: number, item: IRmsNodeSdmSuffix) {
        return item.id;
    }

    trackRmsRoleById(index: number, item: IRmsRoleSdmSuffix) {
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
