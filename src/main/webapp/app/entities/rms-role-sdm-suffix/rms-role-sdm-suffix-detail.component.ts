import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IRmsRoleSdmSuffix } from 'app/shared/model/rms-role-sdm-suffix.model';

@Component({
    selector: 'jhi-rms-role-sdm-suffix-detail',
    templateUrl: './rms-role-sdm-suffix-detail.component.html'
})
export class RmsRoleSdmSuffixDetailComponent implements OnInit {
    rmsRole: IRmsRoleSdmSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ rmsRole }) => {
            this.rmsRole = rmsRole;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
