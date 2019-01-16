import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlanInfoStepDataAtchHisSdmSuffix } from 'app/shared/model/plan-info-step-data-atch-his-sdm-suffix.model';
import { PlanInfoStepDataAtchHisSdmSuffixService } from './plan-info-step-data-atch-his-sdm-suffix.service';

@Component({
    selector: 'jhi-plan-info-step-data-atch-his-sdm-suffix-delete-dialog',
    templateUrl: './plan-info-step-data-atch-his-sdm-suffix-delete-dialog.component.html'
})
export class PlanInfoStepDataAtchHisSdmSuffixDeleteDialogComponent {
    planInfoStepDataAtchHis: IPlanInfoStepDataAtchHisSdmSuffix;

    constructor(
        protected planInfoStepDataAtchHisService: PlanInfoStepDataAtchHisSdmSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.planInfoStepDataAtchHisService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'planInfoStepDataAtchHisListModification',
                content: 'Deleted an planInfoStepDataAtchHis'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-plan-info-step-data-atch-his-sdm-suffix-delete-popup',
    template: ''
})
export class PlanInfoStepDataAtchHisSdmSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planInfoStepDataAtchHis }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlanInfoStepDataAtchHisSdmSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.planInfoStepDataAtchHis = planInfoStepDataAtchHis;
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
