import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IParaCatSdmSuffix } from 'app/shared/model/para-cat-sdm-suffix.model';

@Component({
    selector: 'jhi-para-cat-sdm-suffix-detail',
    templateUrl: './para-cat-sdm-suffix-detail.component.html'
})
export class ParaCatSdmSuffixDetailComponent implements OnInit {
    paraCat: IParaCatSdmSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ paraCat }) => {
            this.paraCat = paraCat;
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
