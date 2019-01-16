import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlanInfoSdmSuffix } from 'app/shared/model/plan-info-sdm-suffix.model';
import { PlanInfoSdmSuffixService } from './plan-info-sdm-suffix.service';

@Component({
    selector: 'jhi-plan-info-sdm-suffix-delete-dialog',
    templateUrl: './plan-info-sdm-suffix-delete-dialog.component.html'
})
export class PlanInfoSdmSuffixDeleteDialogComponent {
    planInfo: IPlanInfoSdmSuffix;

    constructor(
        protected planInfoService: PlanInfoSdmSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.planInfoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'planInfoListModification',
                content: 'Deleted an planInfo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-plan-info-sdm-suffix-delete-popup',
    template: ''
})
export class PlanInfoSdmSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ planInfo }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlanInfoSdmSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.planInfo = planInfo;
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
