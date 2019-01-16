import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlanInfoStepSdmSuffix } from 'app/shared/model/plan-info-step-sdm-suffix.model';
import { PlanInfoStepSdmSuffixService } from './plan-info-step-sdm-suffix.service';

@Component({
    selector: 'jhi-plan-info-step-sdm-suffix-delete-dialog',
    templateUrl: './plan-info-step-sdm-suffix-delete-dialog.component.html'
})
export class PlanInfoStepSdmSuffixDeleteDialogComponent {
    planInfoStep: IPlanInfoStepSdmSuffix;

    constructor(
        protected planInfoStepService: PlanInfoStepSdmSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.planInfoStepService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'planInfoStepListModification',
                content: 'Deleted an planInfoStep'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-plan-info-step-sdm-suffix-delete-popup',
    template: ''
})
export class PlanInfoStepSdmSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planInfoStep }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlanInfoStepSdmSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.planInfoStep = planInfoStep;
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
