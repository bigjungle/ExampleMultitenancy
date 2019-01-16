import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRmsPersonSdmSuffix } from 'app/shared/model/rms-person-sdm-suffix.model';
import { RmsPersonSdmSuffixService } from './rms-person-sdm-suffix.service';

@Component({
    selector: 'jhi-rms-person-sdm-suffix-delete-dialog',
    templateUrl: './rms-person-sdm-suffix-delete-dialog.component.html'
})
export class RmsPersonSdmSuffixDeleteDialogComponent {
    rmsPerson: IRmsPersonSdmSuffix;

    constructor(
        protected rmsPersonService: RmsPersonSdmSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.rmsPersonService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'rmsPersonListModification',
                content: 'Deleted an rmsPerson'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-rms-person-sdm-suffix-delete-popup',
    template: ''
})
export class RmsPersonSdmSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ rmsPerson }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RmsPersonSdmSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.rmsPerson = rmsPerson;
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
