import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IPlanInfoAtchSdmSuffix } from 'app/shared/model/plan-info-atch-sdm-suffix.model';

@Component({
    selector: 'jhi-plan-info-atch-sdm-suffix-detail',
    templateUrl: './plan-info-atch-sdm-suffix-detail.component.html'
})
export class PlanInfoAtchSdmSuffixDetailComponent implements OnInit {
    planInfoAtch: IPlanInfoAtchSdmSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planInfoAtch }) => {
            this.planInfoAtch = planInfoAtch;
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
