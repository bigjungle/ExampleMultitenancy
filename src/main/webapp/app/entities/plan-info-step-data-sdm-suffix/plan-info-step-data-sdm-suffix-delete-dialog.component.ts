import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlanInfoStepDataSdmSuffix } from 'app/shared/model/plan-info-step-data-sdm-suffix.model';
import { PlanInfoStepDataSdmSuffixService } from './plan-info-step-data-sdm-suffix.service';

@Component({
    selector: 'jhi-plan-info-step-data-sdm-suffix-delete-dialog',
    templateUrl: './plan-info-step-data-sdm-suffix-delete-dialog.component.html'
})
export class PlanInfoStepDataSdmSuffixDeleteDialogComponent {
    planInfoStepData: IPlanInfoStepDataSdmSuffix;

    constructor(
        protected planInfoStepDataService: PlanInfoStepDataSdmSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.planInfoStepDataService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'planInfoStepDataListModification',
                content: 'Deleted an planInfoStepData'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-plan-info-step-data-sdm-suffix-delete-popup',
    template: ''
})
export class PlanInfoStepDataSdmSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planInfoStepData }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlanInfoStepDataSdmSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.planInfoStepData = planInfoStepData;
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
