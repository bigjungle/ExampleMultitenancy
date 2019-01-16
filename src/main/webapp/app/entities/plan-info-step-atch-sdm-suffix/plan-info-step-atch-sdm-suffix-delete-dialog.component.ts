import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlanInfoStepAtchSdmSuffix } from 'app/shared/model/plan-info-step-atch-sdm-suffix.model';
import { PlanInfoStepAtchSdmSuffixService } from './plan-info-step-atch-sdm-suffix.service';

@Component({
    selector: 'jhi-plan-info-step-atch-sdm-suffix-delete-dialog',
    templateUrl: './plan-info-step-atch-sdm-suffix-delete-dialog.component.html'
})
export class PlanInfoStepAtchSdmSuffixDeleteDialogComponent {
    planInfoStepAtch: IPlanInfoStepAtchSdmSuffix;

    constructor(
        protected planInfoStepAtchService: PlanInfoStepAtchSdmSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.planInfoStepAtchService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'planInfoStepAtchListModification',
                content: 'Deleted an planInfoStepAtch'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-plan-info-step-atch-sdm-suffix-delete-popup',
    template: ''
})
export class PlanInfoStepAtchSdmSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planInfoStepAtch }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlanInfoStepAtchSdmSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.planInfoStepAtch = planInfoStepAtch;
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
