import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IParaPropSdmSuffix } from 'app/shared/model/para-prop-sdm-suffix.model';

@Component({
    selector: 'jhi-para-prop-sdm-suffix-detail',
    templateUrl: './para-prop-sdm-suffix-detail.component.html'
})
export class ParaPropSdmSuffixDetailComponent implements OnInit {
    paraProp: IParaPropSdmSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ paraProp }) => {
            this.paraProp = paraProp;
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
