import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVerifyRecSdmSuffix } from 'app/shared/model/verify-rec-sdm-suffix.model';
import { VerifyRecSdmSuffixService } from './verify-rec-sdm-suffix.service';

@Component({
    selector: 'jhi-verify-rec-sdm-suffix-delete-dialog',
    templateUrl: './verify-rec-sdm-suffix-delete-dialog.component.html'
})
export class VerifyRecSdmSuffixDeleteDialogComponent {
    verifyRec: IVerifyRecSdmSuffix;

    constructor(
        protected verifyRecService: VerifyRecSdmSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.verifyRecService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'verifyRecListModification',
                content: 'Deleted an verifyRec'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-verify-rec-sdm-suffix-delete-popup',
    template: ''
})
export class VerifyRecSdmSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ verifyRec }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(VerifyRecSdmSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.verifyRec = verifyRec;
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
