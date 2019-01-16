import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlanInfoDataSdmSuffix } from 'app/shared/model/plan-info-data-sdm-suffix.model';
import { PlanInfoDataSdmSuffixService } from './plan-info-data-sdm-suffix.service';

@Component({
    selector: 'jhi-plan-info-data-sdm-suffix-delete-dialog',
    templateUrl: './plan-info-data-sdm-suffix-delete-dialog.component.html'
})
export class PlanInfoDataSdmSuffixDeleteDialogComponent {
    planInfoData: IPlanInfoDataSdmSuffix;

    constructor(
        protected planInfoDataService: PlanInfoDataSdmSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.planInfoDataService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'planInfoDataListModification',
                content: 'Deleted an planInfoData'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-plan-info-data-sdm-suffix-delete-popup',
    template: ''
})
export class PlanInfoDataSdmSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planInfoData }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlanInfoDataSdmSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.planInfoData = planInfoData;
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
