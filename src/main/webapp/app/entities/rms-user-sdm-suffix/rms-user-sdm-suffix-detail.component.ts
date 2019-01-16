import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';

@Component({
    selector: 'jhi-rms-user-sdm-suffix-detail',
    templateUrl: './rms-user-sdm-suffix-detail.component.html'
})
export class RmsUserSdmSuffixDetailComponent implements OnInit {
    rmsUser: IRmsUserSdmSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ rmsUser }) => {
            this.rmsUser = rmsUser;
        });
    }

    previousState() {
        window.history.back();
    }
}
