import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlanInfoDataAtchHisSdmSuffix } from 'app/shared/model/plan-info-data-atch-his-sdm-suffix.model';
import { PlanInfoDataAtchHisSdmSuffixService } from './plan-info-data-atch-his-sdm-suffix.service';

@Component({
    selector: 'jhi-plan-info-data-atch-his-sdm-suffix-delete-dialog',
    templateUrl: './plan-info-data-atch-his-sdm-suffix-delete-dialog.component.html'
})
export class PlanInfoDataAtchHisSdmSuffixDeleteDialogComponent {
    planInfoDataAtchHis: IPlanInfoDataAtchHisSdmSuffix;

    constructor(
        protected planInfoDataAtchHisService: PlanInfoDataAtchHisSdmSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.planInfoDataAtchHisService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'planInfoDataAtchHisListModification',
                content: 'Deleted an planInfoDataAtchHis'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-plan-info-data-atch-his-sdm-suffix-delete-popup',
    template: ''
})
export class PlanInfoDataAtchHisSdmSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planInfoDataAtchHis }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlanInfoDataAtchHisSdmSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.planInfoDataAtchHis = planInfoDataAtchHis;
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
