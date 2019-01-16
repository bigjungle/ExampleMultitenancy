import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRmsRoleSdmSuffix } from 'app/shared/model/rms-role-sdm-suffix.model';
import { RmsRoleSdmSuffixService } from './rms-role-sdm-suffix.service';

@Component({
    selector: 'jhi-rms-role-sdm-suffix-delete-dialog',
    templateUrl: './rms-role-sdm-suffix-delete-dialog.component.html'
})
export class RmsRoleSdmSuffixDeleteDialogComponent {
    rmsRole: IRmsRoleSdmSuffix;

    constructor(
        protected rmsRoleService: RmsRoleSdmSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.rmsRoleService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'rmsRoleListModification',
                content: 'Deleted an rmsRole'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-rms-role-sdm-suffix-delete-popup',
    template: ''
})
export class RmsRoleSdmSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ rmsRole }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RmsRoleSdmSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.rmsRole = rmsRole;
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
