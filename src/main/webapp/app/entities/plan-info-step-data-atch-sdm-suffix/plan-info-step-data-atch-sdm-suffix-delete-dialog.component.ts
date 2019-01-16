import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlanInfoStepDataAtchSdmSuffix } from 'app/shared/model/plan-info-step-data-atch-sdm-suffix.model';
import { PlanInfoStepDataAtchSdmSuffixService } from './plan-info-step-data-atch-sdm-suffix.service';

@Component({
    selector: 'jhi-plan-info-step-data-atch-sdm-suffix-delete-dialog',
    templateUrl: './plan-info-step-data-atch-sdm-suffix-delete-dialog.component.html'
})
export class PlanInfoStepDataAtchSdmSuffixDeleteDialogComponent {
    planInfoStepDataAtch: IPlanInfoStepDataAtchSdmSuffix;

    constructor(
        protected planInfoStepDataAtchService: PlanInfoStepDataAtchSdmSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.planInfoStepDataAtchService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'planInfoStepDataAtchListModification',
                content: 'Deleted an planInfoStepDataAtch'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-plan-info-step-data-atch-sdm-suffix-delete-popup',
    template: ''
})
export class PlanInfoStepDataAtchSdmSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planInfoStepDataAtch }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlanInfoStepDataAtchSdmSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.planInfoStepDataAtch = planInfoStepDataAtch;
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
