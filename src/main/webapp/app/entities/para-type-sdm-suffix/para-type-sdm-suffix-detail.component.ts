import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IParaTypeSdmSuffix } from 'app/shared/model/para-type-sdm-suffix.model';

@Component({
    selector: 'jhi-para-type-sdm-suffix-detail',
    templateUrl: './para-type-sdm-suffix-detail.component.html'
})
export class ParaTypeSdmSuffixDetailComponent implements OnInit {
    paraType: IParaTypeSdmSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ paraType }) => {
            this.paraType = paraType;
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
