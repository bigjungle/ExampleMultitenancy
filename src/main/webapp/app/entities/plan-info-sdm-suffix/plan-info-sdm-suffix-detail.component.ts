import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IPlanInfoSdmSuffix } from 'app/shared/model/plan-info-sdm-suffix.model';

@Component({
    selector: 'jhi-plan-info-sdm-suffix-detail',
    templateUrl: './plan-info-sdm-suffix-detail.component.html'
})
export class PlanInfoSdmSuffixDetailComponent implements OnInit {
    planInfo: IPlanInfoSdmSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planInfo }) => {
            this.planInfo = planInfo;
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
