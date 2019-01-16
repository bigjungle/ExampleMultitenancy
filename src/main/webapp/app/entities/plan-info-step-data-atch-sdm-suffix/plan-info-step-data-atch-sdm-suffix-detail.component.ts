import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IPlanInfoStepDataAtchSdmSuffix } from 'app/shared/model/plan-info-step-data-atch-sdm-suffix.model';

@Component({
    selector: 'jhi-plan-info-step-data-atch-sdm-suffix-detail',
    templateUrl: './plan-info-step-data-atch-sdm-suffix-detail.component.html'
})
export class PlanInfoStepDataAtchSdmSuffixDetailComponent implements OnInit {
    planInfoStepDataAtch: IPlanInfoStepDataAtchSdmSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planInfoStepDataAtch }) => {
            this.planInfoStepDataAtch = planInfoStepDataAtch;
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
