import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Company } from './company.model';
import { CompanyService } from '../../shared';

@Component({
    selector: 'jhi-company-mgmt-update',
    templateUrl: './company-management-update.component.html'
})
export class CompanyMgmtUpdateComponent implements OnInit {
    company: Company;
    isSaving: boolean;
    isEditing: boolean;

    constructor(private route: ActivatedRoute, private router: Router, private companyService: CompanyService) {}

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({ company }) => {
            this.company = company.body ? company.body : company;
        });
    }

    previousState() {
        this.router.navigate(['/admin/company-management']);
    }

    save() {
        this.isSaving = true;
        if (this.company.id !== undefined) {
            this.companyService.update(this.company).subscribe(response => this.onSaveSuccess(response), () => this.onSaveError());
        } else {
            this.companyService.create(this.company).subscribe(response => this.onSaveSuccess(response), () => this.onSaveError());
        }
    }

    private onSaveSuccess(result) {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
