import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IPlanInfoDataSdmSuffix } from 'app/shared/model/plan-info-data-sdm-suffix.model';

@Component({
    selector: 'jhi-plan-info-data-sdm-suffix-detail',
    templateUrl: './plan-info-data-sdm-suffix-detail.component.html'
})
export class PlanInfoDataSdmSuffixDetailComponent implements OnInit {
    planInfoData: IPlanInfoDataSdmSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planInfoData }) => {
            this.planInfoData = planInfoData;
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
