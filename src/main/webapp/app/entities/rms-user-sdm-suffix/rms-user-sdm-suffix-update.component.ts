import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from './rms-user-sdm-suffix.service';
import { IRmsRoleSdmSuffix } from 'app/shared/model/rms-role-sdm-suffix.model';
import { RmsRoleSdmSuffixService } from 'app/entities/rms-role-sdm-suffix';

@Component({
    selector: 'jhi-rms-user-sdm-suffix-update',
    templateUrl: './rms-user-sdm-suffix-update.component.html'
})
export class RmsUserSdmSuffixUpdateComponent implements OnInit {
    rmsUser: IRmsUserSdmSuffix;
    isSaving: boolean;

    rmsusers: IRmsUserSdmSuffix[];

    rmsroles: IRmsRoleSdmSuffix[];
    validBegin: string;
    validEnd: string;
    insertTime: string;
    updateTime: string;
    verifyTime: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected rmsUserService: RmsUserSdmSuffixService,
        protected rmsRoleService: RmsRoleSdmSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ rmsUser }) => {
            this.rmsUser = rmsUser;
            this.validBegin = this.rmsUser.validBegin != null ? this.rmsUser.validBegin.format(DATE_TIME_FORMAT) : null;
            this.validEnd = this.rmsUser.validEnd != null ? this.rmsUser.validEnd.format(DATE_TIME_FORMAT) : null;
            this.insertTime = this.rmsUser.insertTime != null ? this.rmsUser.insertTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.rmsUser.updateTime != null ? this.rmsUser.updateTime.format(DATE_TIME_FORMAT) : null;
            this.verifyTime = this.rmsUser.verifyTime != null ? this.rmsUser.verifyTime.format(DATE_TIME_FORMAT) : null;
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
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.rmsUser.validBegin = this.validBegin != null ? moment(this.validBegin, DATE_TIME_FORMAT) : null;
        this.rmsUser.validEnd = this.validEnd != null ? moment(this.validEnd, DATE_TIME_FORMAT) : null;
        this.rmsUser.insertTime = this.insertTime != null ? moment(this.insertTime, DATE_TIME_FORMAT) : null;
        this.rmsUser.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        this.rmsUser.verifyTime = this.verifyTime != null ? moment(this.verifyTime, DATE_TIME_FORMAT) : null;
        if (this.rmsUser.id !== undefined) {
            this.subscribeToSaveResponse(this.rmsUserService.update(this.rmsUser));
        } else {
            this.subscribeToSaveResponse(this.rmsUserService.create(this.rmsUser));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRmsUserSdmSuffix>>) {
        result.subscribe((res: HttpResponse<IRmsUserSdmSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
