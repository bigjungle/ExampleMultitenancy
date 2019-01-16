import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IPlanInfoDataHisSdmSuffix } from 'app/shared/model/plan-info-data-his-sdm-suffix.model';

@Component({
    selector: 'jhi-plan-info-data-his-sdm-suffix-detail',
    templateUrl: './plan-info-data-his-sdm-suffix-detail.component.html'
})
export class PlanInfoDataHisSdmSuffixDetailComponent implements OnInit {
    planInfoDataHis: IPlanInfoDataHisSdmSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planInfoDataHis }) => {
            this.planInfoDataHis = planInfoDataHis;
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
