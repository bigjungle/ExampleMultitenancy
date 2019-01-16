import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlanInfoDataAtchSdmSuffix } from 'app/shared/model/plan-info-data-atch-sdm-suffix.model';
import { PlanInfoDataAtchSdmSuffixService } from './plan-info-data-atch-sdm-suffix.service';

@Component({
    selector: 'jhi-plan-info-data-atch-sdm-suffix-delete-dialog',
    templateUrl: './plan-info-data-atch-sdm-suffix-delete-dialog.component.html'
})
export class PlanInfoDataAtchSdmSuffixDeleteDialogComponent {
    planInfoDataAtch: IPlanInfoDataAtchSdmSuffix;

    constructor(
        protected planInfoDataAtchService: PlanInfoDataAtchSdmSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.planInfoDataAtchService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'planInfoDataAtchListModification',
                content: 'Deleted an planInfoDataAtch'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-plan-info-data-atch-sdm-suffix-delete-popup',
    template: ''
})
export class PlanInfoDataAtchSdmSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planInfoDataAtch }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlanInfoDataAtchSdmSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.planInfoDataAtch = planInfoDataAtch;
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
