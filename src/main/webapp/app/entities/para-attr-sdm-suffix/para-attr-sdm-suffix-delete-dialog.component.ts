import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IParaAttrSdmSuffix } from 'app/shared/model/para-attr-sdm-suffix.model';
import { ParaAttrSdmSuffixService } from './para-attr-sdm-suffix.service';

@Component({
    selector: 'jhi-para-attr-sdm-suffix-delete-dialog',
    templateUrl: './para-attr-sdm-suffix-delete-dialog.component.html'
})
export class ParaAttrSdmSuffixDeleteDialogComponent {
    paraAttr: IParaAttrSdmSuffix;

    constructor(
        protected paraAttrService: ParaAttrSdmSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.paraAttrService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'paraAttrListModification',
                content: 'Deleted an paraAttr'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-para-attr-sdm-suffix-delete-popup',
    template: ''
})
export class ParaAttrSdmSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ paraAttr }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ParaAttrSdmSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.paraAttr = paraAttr;
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
