import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRmsDepSdmSuffix } from 'app/shared/model/rms-dep-sdm-suffix.model';

@Component({
    selector: 'jhi-rms-dep-sdm-suffix-detail',
    templateUrl: './rms-dep-sdm-suffix-detail.component.html'
})
export class RmsDepSdmSuffixDetailComponent implements OnInit {
    rmsDep: IRmsDepSdmSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ rmsDep }) => {
            this.rmsDep = rmsDep;
        });
    }

    previousState() {
        window.history.back();
    }
}
