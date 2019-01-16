import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IParaSourceSdmSuffix } from 'app/shared/model/para-source-sdm-suffix.model';

@Component({
    selector: 'jhi-para-source-sdm-suffix-detail',
    templateUrl: './para-source-sdm-suffix-detail.component.html'
})
export class ParaSourceSdmSuffixDetailComponent implements OnInit {
    paraSource: IParaSourceSdmSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ paraSource }) => {
            this.paraSource = paraSource;
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
