import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IPlanInfoStepDataAtchHisSdmSuffix } from 'app/shared/model/plan-info-step-data-atch-his-sdm-suffix.model';

@Component({
    selector: 'jhi-plan-info-step-data-atch-his-sdm-suffix-detail',
    templateUrl: './plan-info-step-data-atch-his-sdm-suffix-detail.component.html'
})
export class PlanInfoStepDataAtchHisSdmSuffixDetailComponent implements OnInit {
    planInfoStepDataAtchHis: IPlanInfoStepDataAtchHisSdmSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planInfoStepDataAtchHis }) => {
            this.planInfoStepDataAtchHis = planInfoStepDataAtchHis;
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
