import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IPlanInfoStepSdmSuffix } from 'app/shared/model/plan-info-step-sdm-suffix.model';

@Component({
    selector: 'jhi-plan-info-step-sdm-suffix-detail',
    templateUrl: './plan-info-step-sdm-suffix-detail.component.html'
})
export class PlanInfoStepSdmSuffixDetailComponent implements OnInit {
    planInfoStep: IPlanInfoStepSdmSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planInfoStep }) => {
            this.planInfoStep = planInfoStep;
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
