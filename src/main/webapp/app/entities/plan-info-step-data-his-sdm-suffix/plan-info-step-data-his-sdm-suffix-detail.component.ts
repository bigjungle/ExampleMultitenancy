import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IPlanInfoStepDataHisSdmSuffix } from 'app/shared/model/plan-info-step-data-his-sdm-suffix.model';

@Component({
    selector: 'jhi-plan-info-step-data-his-sdm-suffix-detail',
    templateUrl: './plan-info-step-data-his-sdm-suffix-detail.component.html'
})
export class PlanInfoStepDataHisSdmSuffixDetailComponent implements OnInit {
    planInfoStepDataHis: IPlanInfoStepDataHisSdmSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planInfoStepDataHis }) => {
            this.planInfoStepDataHis = planInfoStepDataHis;
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
