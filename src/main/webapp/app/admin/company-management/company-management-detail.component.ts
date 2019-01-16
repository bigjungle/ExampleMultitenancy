import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Company } from './company.model';

@Component({
    selector: 'jhi-company-mgmt-detail',
    templateUrl: './company-management-detail.component.html'
})
export class CompanyMgmtDetailComponent implements OnInit {
    company: Company;

    constructor(private route: ActivatedRoute) {}

    ngOnInit() {
        this.route.data.subscribe(({ company }) => {
            this.company = company.body ? company.body : company;
        });
    }
}
