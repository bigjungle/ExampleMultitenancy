import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlanInfoDataHisSdmSuffix } from 'app/shared/model/plan-info-data-his-sdm-suffix.model';
import { PlanInfoDataHisSdmSuffixService } from './plan-info-data-his-sdm-suffix.service';

@Component({
    selector: 'jhi-plan-info-data-his-sdm-suffix-delete-dialog',
    templateUrl: './plan-info-data-his-sdm-suffix-delete-dialog.component.html'
})
export class PlanInfoDataHisSdmSuffixDeleteDialogComponent {
    planInfoDataHis: IPlanInfoDataHisSdmSuffix;

    constructor(
        protected planInfoDataHisService: PlanInfoDataHisSdmSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.planInfoDataHisService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'planInfoDataHisListModification',
                content: 'Deleted an planInfoDataHis'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-plan-info-data-his-sdm-suffix-delete-popup',
    template: ''
})
export class PlanInfoDataHisSdmSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planInfoDataHis }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlanInfoDataHisSdmSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.planInfoDataHis = planInfoDataHis;
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
