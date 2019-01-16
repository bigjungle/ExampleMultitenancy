import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IParaClassSdmSuffix } from 'app/shared/model/para-class-sdm-suffix.model';

@Component({
    selector: 'jhi-para-class-sdm-suffix-detail',
    templateUrl: './para-class-sdm-suffix-detail.component.html'
})
export class ParaClassSdmSuffixDetailComponent implements OnInit {
    paraClass: IParaClassSdmSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ paraClass }) => {
            this.paraClass = paraClass;
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
