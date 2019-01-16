import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IParaCatSdmSuffix } from 'app/shared/model/para-cat-sdm-suffix.model';
import { ParaCatSdmSuffixService } from './para-cat-sdm-suffix.service';

@Component({
    selector: 'jhi-para-cat-sdm-suffix-delete-dialog',
    templateUrl: './para-cat-sdm-suffix-delete-dialog.component.html'
})
export class ParaCatSdmSuffixDeleteDialogComponent {
    paraCat: IParaCatSdmSuffix;

    constructor(
        protected paraCatService: ParaCatSdmSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.paraCatService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'paraCatListModification',
                content: 'Deleted an paraCat'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-para-cat-sdm-suffix-delete-popup',
    template: ''
})
export class ParaCatSdmSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ paraCat }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ParaCatSdmSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.paraCat = paraCat;
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
