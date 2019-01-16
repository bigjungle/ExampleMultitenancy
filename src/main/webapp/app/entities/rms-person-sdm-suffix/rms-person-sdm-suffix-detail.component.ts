import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRmsPersonSdmSuffix } from 'app/shared/model/rms-person-sdm-suffix.model';

@Component({
    selector: 'jhi-rms-person-sdm-suffix-detail',
    templateUrl: './rms-person-sdm-suffix-detail.component.html'
})
export class RmsPersonSdmSuffixDetailComponent implements OnInit {
    rmsPerson: IRmsPersonSdmSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ rmsPerson }) => {
            this.rmsPerson = rmsPerson;
        });
    }

    previousState() {
        window.history.back();
    }
}
