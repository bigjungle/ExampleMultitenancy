import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from './rms-user-sdm-suffix.service';

@Component({
    selector: 'jhi-rms-user-sdm-suffix-delete-dialog',
    templateUrl: './rms-user-sdm-suffix-delete-dialog.component.html'
})
export class RmsUserSdmSuffixDeleteDialogComponent {
    rmsUser: IRmsUserSdmSuffix;

    constructor(
        protected rmsUserService: RmsUserSdmSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.rmsUserService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'rmsUserListModification',
                content: 'Deleted an rmsUser'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-rms-user-sdm-suffix-delete-popup',
    template: ''
})
export class RmsUserSdmSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ rmsUser }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RmsUserSdmSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.rmsUser = rmsUser;
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
