import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IPlanInfoStepDataSdmSuffix } from 'app/shared/model/plan-info-step-data-sdm-suffix.model';

@Component({
    selector: 'jhi-plan-info-step-data-sdm-suffix-detail',
    templateUrl: './plan-info-step-data-sdm-suffix-detail.component.html'
})
export class PlanInfoStepDataSdmSuffixDetailComponent implements OnInit {
    planInfoStepData: IPlanInfoStepDataSdmSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planInfoStepData }) => {
            this.planInfoStepData = planInfoStepData;
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
