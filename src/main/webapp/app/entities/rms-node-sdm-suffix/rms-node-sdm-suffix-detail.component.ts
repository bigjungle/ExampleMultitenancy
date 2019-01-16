import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IRmsNodeSdmSuffix } from 'app/shared/model/rms-node-sdm-suffix.model';

@Component({
    selector: 'jhi-rms-node-sdm-suffix-detail',
    templateUrl: './rms-node-sdm-suffix-detail.component.html'
})
export class RmsNodeSdmSuffixDetailComponent implements OnInit {
    rmsNode: IRmsNodeSdmSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ rmsNode }) => {
            this.rmsNode = rmsNode;
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
