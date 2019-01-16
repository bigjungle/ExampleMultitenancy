import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IPlanInfoDataAtchSdmSuffix } from 'app/shared/model/plan-info-data-atch-sdm-suffix.model';

@Component({
    selector: 'jhi-plan-info-data-atch-sdm-suffix-detail',
    templateUrl: './plan-info-data-atch-sdm-suffix-detail.component.html'
})
export class PlanInfoDataAtchSdmSuffixDetailComponent implements OnInit {
    planInfoDataAtch: IPlanInfoDataAtchSdmSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planInfoDataAtch }) => {
            this.planInfoDataAtch = planInfoDataAtch;
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
