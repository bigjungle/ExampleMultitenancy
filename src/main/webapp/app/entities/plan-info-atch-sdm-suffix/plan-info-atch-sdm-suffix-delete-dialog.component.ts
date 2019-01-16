import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlanInfoAtchSdmSuffix } from 'app/shared/model/plan-info-atch-sdm-suffix.model';
import { PlanInfoAtchSdmSuffixService } from './plan-info-atch-sdm-suffix.service';

@Component({
    selector: 'jhi-plan-info-atch-sdm-suffix-delete-dialog',
    templateUrl: './plan-info-atch-sdm-suffix-delete-dialog.component.html'
})
export class PlanInfoAtchSdmSuffixDeleteDialogComponent {
    planInfoAtch: IPlanInfoAtchSdmSuffix;

    constructor(
        protected planInfoAtchService: PlanInfoAtchSdmSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.planInfoAtchService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'planInfoAtchListModification',
                content: 'Deleted an planInfoAtch'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-plan-info-atch-sdm-suffix-delete-popup',
    template: ''
})
export class PlanInfoAtchSdmSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planInfoAtch }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlanInfoAtchSdmSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.planInfoAtch = planInfoAtch;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
