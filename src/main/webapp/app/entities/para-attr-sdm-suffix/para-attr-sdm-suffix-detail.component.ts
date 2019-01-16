import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IParaAttrSdmSuffix } from 'app/shared/model/para-attr-sdm-suffix.model';

@Component({
    selector: 'jhi-para-attr-sdm-suffix-detail',
    templateUrl: './para-attr-sdm-suffix-detail.component.html'
})
export class ParaAttrSdmSuffixDetailComponent implements OnInit {
    paraAttr: IParaAttrSdmSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ paraAttr }) => {
            this.paraAttr = paraAttr;
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
