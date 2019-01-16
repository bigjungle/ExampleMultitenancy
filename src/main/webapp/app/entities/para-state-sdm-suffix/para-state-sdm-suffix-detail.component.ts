import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IParaStateSdmSuffix } from 'app/shared/model/para-state-sdm-suffix.model';

@Component({
    selector: 'jhi-para-state-sdm-suffix-detail',
    templateUrl: './para-state-sdm-suffix-detail.component.html'
})
export class ParaStateSdmSuffixDetailComponent implements OnInit {
    paraState: IParaStateSdmSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ paraState }) => {
            this.paraState = paraState;
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
