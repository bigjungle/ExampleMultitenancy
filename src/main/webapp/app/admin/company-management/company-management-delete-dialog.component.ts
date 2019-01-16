import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Company } from './company.model';
import { CompanyService } from '../../shared';

@Component({
    selector: 'jhi-company-mgmt-delete-dialog',
    templateUrl: './company-management-delete-dialog.component.html'
})
export class CompanyMgmtDeleteDialogComponent {
    company: Company;

    constructor(private companyService: CompanyService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.companyService.delete(id).subscribe(response => {
            this.eventManager.broadcast({ name: 'companyListModification', content: 'Deleted a company' });
            this.activeModal.dismiss(true);
        });
    }
}
