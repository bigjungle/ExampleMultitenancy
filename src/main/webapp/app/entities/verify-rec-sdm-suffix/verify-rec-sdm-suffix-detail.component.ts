import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVerifyRecSdmSuffix } from 'app/shared/model/verify-rec-sdm-suffix.model';

@Component({
    selector: 'jhi-verify-rec-sdm-suffix-detail',
    templateUrl: './verify-rec-sdm-suffix-detail.component.html'
})
export class VerifyRecSdmSuffixDetailComponent implements OnInit {
    verifyRec: IVerifyRecSdmSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ verifyRec }) => {
            this.verifyRec = verifyRec;
        });
    }

    previousState() {
        window.history.back();
    }
}
