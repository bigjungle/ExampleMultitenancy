import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IPlanInfoStepAtchSdmSuffix } from 'app/shared/model/plan-info-step-atch-sdm-suffix.model';

@Component({
    selector: 'jhi-plan-info-step-atch-sdm-suffix-detail',
    templateUrl: './plan-info-step-atch-sdm-suffix-detail.component.html'
})
export class PlanInfoStepAtchSdmSuffixDetailComponent implements OnInit {
    planInfoStepAtch: IPlanInfoStepAtchSdmSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planInfoStepAtch }) => {
            this.planInfoStepAtch = planInfoStepAtch;
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
