import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRmsNodeSdmSuffix } from 'app/shared/model/rms-node-sdm-suffix.model';
import { RmsNodeSdmSuffixService } from './rms-node-sdm-suffix.service';

@Component({
    selector: 'jhi-rms-node-sdm-suffix-delete-dialog',
    templateUrl: './rms-node-sdm-suffix-delete-dialog.component.html'
})
export class RmsNodeSdmSuffixDeleteDialogComponent {
    rmsNode: IRmsNodeSdmSuffix;

    constructor(
        protected rmsNodeService: RmsNodeSdmSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.rmsNodeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'rmsNodeListModification',
                content: 'Deleted an rmsNode'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-rms-node-sdm-suffix-delete-popup',
    template: ''
})
export class RmsNodeSdmSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ rmsNode }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RmsNodeSdmSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.rmsNode = rmsNode;
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
