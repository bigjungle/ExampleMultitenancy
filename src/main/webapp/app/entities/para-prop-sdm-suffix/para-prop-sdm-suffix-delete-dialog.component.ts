import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IParaPropSdmSuffix } from 'app/shared/model/para-prop-sdm-suffix.model';
import { ParaPropSdmSuffixService } from './para-prop-sdm-suffix.service';

@Component({
    selector: 'jhi-para-prop-sdm-suffix-delete-dialog',
    templateUrl: './para-prop-sdm-suffix-delete-dialog.component.html'
})
export class ParaPropSdmSuffixDeleteDialogComponent {
    paraProp: IParaPropSdmSuffix;

    constructor(
        protected paraPropService: ParaPropSdmSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.paraPropService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'paraPropListModification',
                content: 'Deleted an paraProp'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-para-prop-sdm-suffix-delete-popup',
    template: ''
})
export class ParaPropSdmSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ paraProp }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ParaPropSdmSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.paraProp = paraProp;
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
