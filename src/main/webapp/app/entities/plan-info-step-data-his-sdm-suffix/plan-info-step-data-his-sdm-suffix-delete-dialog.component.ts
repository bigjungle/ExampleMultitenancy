import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlanInfoStepDataHisSdmSuffix } from 'app/shared/model/plan-info-step-data-his-sdm-suffix.model';
import { PlanInfoStepDataHisSdmSuffixService } from './plan-info-step-data-his-sdm-suffix.service';

@Component({
    selector: 'jhi-plan-info-step-data-his-sdm-suffix-delete-dialog',
    templateUrl: './plan-info-step-data-his-sdm-suffix-delete-dialog.component.html'
})
export class PlanInfoStepDataHisSdmSuffixDeleteDialogComponent {
    planInfoStepDataHis: IPlanInfoStepDataHisSdmSuffix;

    constructor(
        protected planInfoStepDataHisService: PlanInfoStepDataHisSdmSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.planInfoStepDataHisService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'planInfoStepDataHisListModification',
                content: 'Deleted an planInfoStepDataHis'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-plan-info-step-data-his-sdm-suffix-delete-popup',
    template: ''
})
export class PlanInfoStepDataHisSdmSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planInfoStepDataHis }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlanInfoStepDataHisSdmSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.planInfoStepDataHis = planInfoStepDataHis;
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
